package com.hari.service;

import com.hari.domin.OrderType;
import com.hari.model.Coin;
import com.hari.model.Order;
import com.hari.model.OrderItem;
import com.hari.model.User;

import java.util.List;

public interface OrderService {
    Order createOrder(User user, OrderItem orderItem, OrderType orderType);
    Order getOrderById(Long orderId) throws Exception;
    List<Order> getAllOrdersOfUser(Long userId,OrderType orderType,String assetSymbol);
    Order processOrder(Coin coin,double quantity,OrderType orderType,User user) throws Exception;


}
