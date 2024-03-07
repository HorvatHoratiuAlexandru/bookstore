package com.horvat.bookstore.order.dtos.responses;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.horvat.bookstore.order.ItemModel;
import com.horvat.bookstore.order.OrderModel;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Getter
@Setter
@Log4j2
public class ResOrderDto {
    private Integer id;
    private String date;
    private Float totalPrice;
    private Map<String, Integer> items;
    private String address;
    private String promoCode;
    private Boolean isPayed;
    private Boolean isProcessed;
    private Boolean isFinished;

    public static ResOrderDto fromEntity(OrderModel entity){
        ResOrderDto response = new ResOrderDto();

        response.setId(entity.getId());
        response.setDate(entity.getCreationTime().toString());
        response.setTotalPrice(entity.getPrice());
        response.setItems(entity.getItems()
                          .stream()
                          .collect(Collectors.toMap(item -> item.getBook().getTitle(), ItemModel::getQty)));
        response.setAddress(entity.getAddress());
        if(entity.getPromoCode() != null){
            response.setPromoCode(entity.getPromoCode().toString());
        }else {
            response.setPromoCode("NA");
        }
        response.setIsPayed(entity.getIsPayed());
        response.setIsProcessed(entity.getIsProcessed());
        response.setIsFinished(entity.getIsFinished());

        log.info(entity.getItems());

        return response;

    }

    public static List<ResOrderDto> fromEntityList(List<OrderModel> entities){
        List<ResOrderDto> response = new LinkedList<>();

        for(OrderModel entity : entities){
            response.add(ResOrderDto.fromEntity(entity));
        }

        return response;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Order ID: ").append(id).append("\n")
                .append("Date: ").append(date).append("\n")
                .append("Total Price: ").append(totalPrice).append("\n")
                .append("Items:\n");
        for (Map.Entry<String, Integer> entry : items.entrySet()) {
            sb.append("    ").append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
        }
        sb.append("Address: ").append(address).append("\n")
                .append("Payed: ").append(this.isPayed).append("\n");
        return sb.toString();
    }
}
