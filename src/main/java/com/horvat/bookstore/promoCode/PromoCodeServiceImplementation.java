package com.horvat.bookstore.promoCode;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.horvat.bookstore.appUser.UserModel;
import com.horvat.bookstore.appUser.UserRepository;
import com.horvat.bookstore.order.OrderModel;
import com.horvat.bookstore.promoCode.dtos.responses.ResPromoDto;

@Service
public class PromoCodeServiceImplementation implements PromoCodeService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PromoCodeRepository promoCodeRepository;

    @Override
    public ResPromoDto validateCode(Integer id, String promoCode) {
        if(this.ordersContainCode(promoCode, this.getUserOrders(id))){
            //TODO: throw code already used
            return ResPromoDto.fromEntity(null);
        }
        
        return ResPromoDto.fromEntity(this.exists(promoCode));
    }

    private PromoCodeModel exists(String code){
        List<PromoCodeModel> codes= this.promoCodeRepository.findByCode(code);
        for(PromoCodeModel c:codes){
            if(c.getCode().equals(code)) return c;
        }
        // TODO: throw promo code does not exist
        return null;
    }

    private Set<OrderModel> getUserOrders(Integer id){
        Optional<UserModel> userOptional = this.userRepository.findById(id);

        if(!userOptional.isPresent()){
            // TODO: user not found exception    
            return null;
        }

        return userOptional.get().getOrders();
    }

    private boolean ordersContainCode(String code, Set<OrderModel> orders){
        if(orders == null) return false;

        Iterator<OrderModel> ordersIterator = orders.iterator();
        
        while(ordersIterator.hasNext()){
            OrderModel order = ordersIterator.next();
            PromoCodeModel orderCode = order.getPromoCode();
            if(orderCode == null) continue;
            if(orderCode.getCode().equals(code)) return true;
        }

        return false;
    }

}
