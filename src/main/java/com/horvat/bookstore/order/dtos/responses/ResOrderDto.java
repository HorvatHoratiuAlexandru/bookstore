package com.horvat.bookstore.order.dtos.responses;

import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResOrderDto {
    private Integer id;
    private String date;
    private Float totalPrice;
    private Map<String, Float> items;
    private String address;
    private Boolean processed;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Order ID: ").append(id).append("\n")
                .append("Date: ").append(date).append("\n")
                .append("Total Price: ").append(totalPrice).append("\n")
                .append("Items:\n");
        for (Map.Entry<String, Float> entry : items.entrySet()) {
            sb.append("    ").append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
        }
        sb.append("Address: ").append(address).append("\n")
                .append("Processed: ").append(processed).append("\n");
        return sb.toString();
    }
}
