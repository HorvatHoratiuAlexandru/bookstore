package com.horvat.bookstore.order;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.horvat.bookstore.appUser.UserModel;
import com.horvat.bookstore.appUser.UserRepository;
import com.horvat.bookstore.appUser.exceptions.DontOwnTheResourceException;
import com.horvat.bookstore.appUser.exceptions.UserNotFoundException;
import com.horvat.bookstore.order.dtos.requests.ReqOrderDto;
import com.horvat.bookstore.order.dtos.requests.ReqOrderProcessing;
import com.horvat.bookstore.order.dtos.responses.OrderRegistered;
import com.horvat.bookstore.order.dtos.responses.ResOrderDto;
import com.horvat.bookstore.order.exception.OrderNotFound;
import com.horvat.bookstore.promoCode.PromoCodeModel;
import com.horvat.bookstore.promoCode.PromoCodeRepository;
import com.horvat.bookstore.promoCode.exception.PromoCodeNotFound;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class OrderServiceImplementation implements OrderService{
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ItemService itemService;
    @Autowired
    private PromoCodeRepository promoCodeRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public ResOrderDto getOrder(Integer orderId, String userUid){
        Optional<OrderModel> orderOptional = this.orderRepository.findById(orderId);
        if(!orderOptional.isPresent()){
            String message = "Order with id: " + orderId + " Not Found";
            log.error(message); 
            throw new OrderNotFound(message);
        }

        if(!orderOptional.get().getUser().getUid().equals(userUid)){
            String message = "You don't own this resource";
            log.error(message); 
            throw new DontOwnTheResourceException(message);
        }

        return ResOrderDto.fromEntity(orderOptional.get());
    }

    @Override
    public List<ResOrderDto> getOrders(String userUid){
        List<UserModel> userQueryResult = this.userRepository.findByUid(userUid);

        if(userQueryResult == null || userQueryResult.isEmpty()){
            String message = "User with id: " + userUid + " Not Found";
            log.error(message);
            throw new UserNotFoundException(message);
        }

        List<OrderModel> orders = this.orderRepository.findByUser(userQueryResult.get(0));

        return ResOrderDto.fromEntityList(orders);
    }

    @Override
    public OrderRegistered placeOrder(String id, ReqOrderDto orderDto) {
        List<ItemModel> items = this.itemService.createOrderItems(orderDto.getItems());
        Optional<PromoCodeModel> promoCodeOptional = Optional.empty();
        
        if(orderDto.getPromoCode() != null){
            // Should be good it gets checked in controller and should also be checked in FE
            promoCodeOptional = Optional.ofNullable(promoCodeRepository.findByCode(orderDto.getPromoCode()).get(0));
            if(!promoCodeOptional.isPresent()){
                StringBuilder sb = new StringBuilder();
                sb.append("Promo Code ").append(orderDto.getPromoCode()).append(" Does Not Exist");
                log.error(sb.toString());
                throw new PromoCodeNotFound(sb.toString());
            }
        }

        Float totalPrice = (promoCodeOptional.isPresent()) ? this.calcOrderTotal(items, promoCodeOptional.get().getDiscount()) : this.calcOrderTotal(items, 0.0f);

        
        return OrderRegistered.fromEntity(this.create(items, promoCodeOptional, totalPrice, id, orderDto.getAddress()));
    }

    @Override
    public OrderRegistered processOrder(String id, Integer orderId, ReqOrderProcessing billingDto) {
        Optional<OrderModel> orderOptional = this.orderRepository.findById(orderId);

        if(!orderOptional.isPresent()){
            StringBuilder sb = new StringBuilder();
            sb.append("Order:").append(orderId.toString()).append("Not Found Please Try Again");
            log.error(sb.toString());
            throw new OrderNotFound(sb.toString());
        }
        // TODO: verify biling data?
        // TODO: push to a rabbitmq queue that sends emails to the users
        orderOptional.get().setIsPayed(true);
        this.orderRepository.save(orderOptional.get());
        
        return OrderRegistered.fromEntity(orderOptional.get());
    }

    private Float calcOrderTotal(List<ItemModel> items, Float discount){
        Float total = 0.0f;
        for(ItemModel item: items){
            total += item.getPrice()*item.getQty();
        }

        return (total-(total*discount));
    }

    private OrderModel create(List<ItemModel> items, Optional<PromoCodeModel> promoCode, Float totalPrice, String id, String address){
        OrderModel order = new OrderModel();

        for(ItemModel item : items){
            item.setOrder(order);
        }

        order.setItems(new HashSet<ItemModel>(items));
        
        if(promoCode.isPresent()){
            order.setPromoCode(promoCode.get());
            promoCode.get().getOrders().add(order);
        }

        order.setAddress(address);
        order.setIsProcessed(false);
        order.setIsFinished(false);
        order.setIsPayed(false);
        order.setCreationTime(new Date());

        List<UserModel> userQueryResult = this.userRepository.findByUid(id);

        if(userQueryResult != null && !userQueryResult.isEmpty()){
            order.setUser(userQueryResult.get(0));
        }else{
            StringBuilder sb = new StringBuilder();
            sb.append("User with Id: ").append(id.toString()).append(" NotFound");
            throw new UserNotFoundException(sb.toString());
        }

        order.setPrice(totalPrice);

        userQueryResult.get(0).getOrders().add(order);

        order = this.orderRepository.save(order);
        
        return order;
    }

}
