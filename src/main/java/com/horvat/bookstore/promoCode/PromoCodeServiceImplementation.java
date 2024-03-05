package com.horvat.bookstore.promoCode;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.horvat.bookstore.appUser.UserModel;
import com.horvat.bookstore.appUser.UserRepository;
import com.horvat.bookstore.appUser.exceptions.UserNotFoundException;
import com.horvat.bookstore.order.OrderModel;
import com.horvat.bookstore.promoCode.dtos.responses.ResPromoDto;
import com.horvat.bookstore.promoCode.exception.PromoCodeAlreadyUsed;
import com.horvat.bookstore.promoCode.exception.PromoCodeExpired;
import com.horvat.bookstore.promoCode.exception.PromoCodeNotFound;


@Service
public class PromoCodeServiceImplementation implements PromoCodeService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PromoCodeRepository promoCodeRepository;

    @Override
    public ResPromoDto validateCode(String id, String promoCode) {
        if(this.ordersContainCode(promoCode, this.getUserOrders(id))){
            StringBuilder sb = new StringBuilder();
            sb.append("Promo Code ").append(promoCode).append(" Already Used");
            throw new PromoCodeAlreadyUsed(sb.toString());
        }
        
        return ResPromoDto.fromEntity(this.exists(promoCode));
    }

    private PromoCodeModel exists(String code){
        List<PromoCodeModel> codes= this.promoCodeRepository.findByCode(code);
        for(PromoCodeModel c:codes){
            if(c.getCode().equals(code) && c.getExpired()) {
                StringBuilder sb = new StringBuilder();
                sb.append("We are sorry but Promo Code ").append(code).append(" Expired");
                throw new PromoCodeExpired(sb.toString());
            }
            if(c.getCode().equals(code)) return c;
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append("Promo Code ").append(code).append(" Does Not Exist");
        throw new PromoCodeNotFound(sb.toString());
    }

    private Set<OrderModel> getUserOrders(String id){
        List<UserModel> userQueryResult = this.userRepository.findByUid(id);

        if(userQueryResult == null || userQueryResult.isEmpty()){
            StringBuilder sb = new StringBuilder();
            sb.append("User with Id: ").append(id.toString()).append(" NotFound");
            throw new UserNotFoundException(sb.toString());
        }

        return userQueryResult.get(0).getOrders();
    }

    private boolean ordersContainCode(String code, Set<OrderModel> orders){
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
