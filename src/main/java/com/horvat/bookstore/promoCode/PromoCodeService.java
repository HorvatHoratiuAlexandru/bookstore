package com.horvat.bookstore.promoCode;

import com.horvat.bookstore.promoCode.dtos.responses.ResPromoDto;

public interface PromoCodeService {
    ResPromoDto validateCode(Integer id, String promoCode);
}
