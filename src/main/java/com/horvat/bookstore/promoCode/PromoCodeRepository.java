package com.horvat.bookstore.promoCode;

import org.springframework.data.repository.Repository;
import java.util.List;


public interface PromoCodeRepository extends Repository<PromoCodeModel, Integer> {
    List<PromoCodeModel> findByCode(String code);
    PromoCodeModel save(PromoCodeModel code);
    List<PromoCodeModel> findAll();

}
