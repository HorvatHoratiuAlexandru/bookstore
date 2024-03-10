package com.horvat.bookstore.appUser.admin.services;

import java.util.List;

import com.horvat.bookstore.appUser.admin.dtos.PromoCodeCreateDto;
import com.horvat.bookstore.promoCode.dtos.responses.ResPromoDto;

public interface AdminPromoCodeService {
    public Boolean createPromoCode(PromoCodeCreateDto promoCodeDto);
    public List<ResPromoDto> getPromoCodes();
    public ResPromoDto updatePromoCode(String code);

}
