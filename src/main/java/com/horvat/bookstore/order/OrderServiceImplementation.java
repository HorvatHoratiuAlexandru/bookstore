package com.horvat.bookstore.order;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.horvat.bookstore.appUser.UserModel;
import com.horvat.bookstore.appUser.UserRepository;
import com.horvat.bookstore.order.dtos.requests.ReqOrderDto;
import com.horvat.bookstore.order.dtos.requests.ReqOrderProcessing;
import com.horvat.bookstore.order.dtos.responses.OrderRegistered;
import com.horvat.bookstore.promoCode.PromoCodeModel;
import com.horvat.bookstore.promoCode.PromoCodeRepository;

@Service
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
    public OrderRegistered placeOrder(Integer id, ReqOrderDto orderDto) {
        List<ItemModel> items = this.itemService.createOrderItems(orderDto.getItems());
        Optional<PromoCodeModel> promoCodeOptional = Optional.empty();
        
        if(orderDto.getPromoCode() != null){
            // Should be good it gets checked in controller and should also be checked in FE
            promoCodeOptional = Optional.ofNullable(promoCodeRepository.findByCode(orderDto.getPromoCode()).get(0));

            // TODO: throw a server error if optional is null
        }

        Float totalPrice = (promoCodeOptional.isPresent()) ? this.calcOrderTotal(items, promoCodeOptional.get().getDiscount()) : this.calcOrderTotal(items, 1.0f);

        
        return OrderRegistered.fromEntity(this.create(items, promoCodeOptional, totalPrice, id, orderDto.getAddress()));
    }

    @Override
    public OrderRegistered processOrder(Integer id, Integer orderId, ReqOrderProcessing billingDto) {
        //TODO: verify biling data?
        Optional<OrderModel> orderOptional = this.orderRepository.findById(orderId);

        if(!orderOptional.isPresent()){
            // TODO: throw a server error
        }

        orderOptional.get().setIsProcessed(true);
        this.orderRepository.save(orderOptional.get());
        // TODO: push to a rabbitmq queue that sends emails to the users
        
        return OrderRegistered.fromEntity(orderOptional.get());
    }

    private Float calcOrderTotal(List<ItemModel> items, Float discount){
        Float total = 0.0f;
        for(ItemModel item: items){
            total += item.getPrice()*item.getQty();
        }

        return (total-(total*discount));
    }

    private OrderModel create(List<ItemModel> items, Optional<PromoCodeModel> promoCode, Float totalPrice, Integer id, String address){
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
        Optional<UserModel> userOptional = this.userRepository.findById(id);
        if(userOptional.isPresent()){
            order.setUser(userOptional.get());
        }else{
            //TODO: throw server error or user not found
        }
        order.setPrice(totalPrice);

        userOptional.get().getOrders().add(order);

        order = this.orderRepository.save(order);
        return order;
    }

}
