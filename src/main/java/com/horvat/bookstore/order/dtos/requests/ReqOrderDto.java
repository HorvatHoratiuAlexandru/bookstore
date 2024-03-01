package com.horvat.bookstore.order.dtos.requests;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class ReqOrderDto {
    @Size(min=5)
    private String address;
    private String promoCode;
    //bookId and qty
    @NotEmpty(message = "No items selected")
    private Map<Integer, Integer> items;

    public String toString(){
        StringBuilder sb = new StringBuilder();

        sb.append("order: \n").append("address:").append(this.address).append("\n")
        .append("promo code:").append(this.promoCode).append("\n");

        Iterator<Entry<Integer, Integer>> itemIterator = items.entrySet().iterator();

        while(itemIterator.hasNext()){
            Map.Entry<Integer,Integer> item = itemIterator.next();
            sb.append("BookId:" + item.getKey()).append(" qty:" + item.getValue()).append("\n");
        }


        return sb.toString();
    }

}
