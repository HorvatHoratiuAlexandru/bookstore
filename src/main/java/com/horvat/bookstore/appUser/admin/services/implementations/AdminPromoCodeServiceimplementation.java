package com.horvat.bookstore.appUser.admin.services.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.horvat.bookstore.appUser.admin.dtos.PromoCodeCreateDto;
import com.horvat.bookstore.appUser.admin.services.AdminPromoCodeService;
import com.horvat.bookstore.promoCode.PromoCodeModel;
import com.horvat.bookstore.promoCode.PromoCodeRepository;
import com.horvat.bookstore.promoCode.dtos.responses.ResPromoDto;
import com.horvat.bookstore.promoCode.exception.PromoCodeNotFound;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class AdminPromoCodeServiceimplementation implements AdminPromoCodeService {
    @Autowired
    PromoCodeRepository promoCodeRepository;

    public Boolean createPromoCode(PromoCodeCreateDto promoCodeDto){
        this.promoCodeRepository.save(promoCodeDto.getEntity());
        log.info("Promocode: " + promoCodeDto.getCode() + " created");

        return true;
    }

    public List<ResPromoDto> getPromoCodes(){
        return ResPromoDto.fromEntityList(this.promoCodeRepository.findAll());
    }

    public ResPromoDto updatePromoCode(String code){
        List<PromoCodeModel> queryResult = this.promoCodeRepository.findByCode(code);

        if(queryResult == null || queryResult.isEmpty()){
            String message = "Promo Code: " + code + " Not Found";
            log.error(message); 
            throw new PromoCodeNotFound(message);
        }

        PromoCodeModel entity = queryResult.get(0);

        entity.setExpired(!entity.getExpired());
        this.promoCodeRepository.save(entity);

        return ResPromoDto.fromEntity(entity);
    }

}
