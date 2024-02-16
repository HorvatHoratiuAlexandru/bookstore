package com.horvat.bookstore.order;

import java.util.List;
import java.util.Map;

public interface ItemService {
    public List<ItemModel> createOrderItems(Map<Integer, Integer> orderItemsBookAndQty);
}
