package com.horvat.bookstore.promoCode;

import com.horvat.bookstore.promoCode.dtos.responses.ResPromoDto;

public interface PromoCodeService {
    ResPromoDto validateCode(String id, String promoCode);
}
