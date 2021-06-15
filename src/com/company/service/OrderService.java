package com.company.service;

import com.company.comparators.OrderComparator;
import com.company.models.Order;
import com.company.models.OrderStatus;

import java.util.Collection;
import java.util.Date;
import java.util.List;

public interface OrderService {

    void createOrder(List <Integer> bookIds, List <String> customerData);

    void cancelOrder(int id);

    boolean changeStatus (int id, OrderStatus orderStatus);

    Collection<Order> getAllOrders();

    Collection<Order> getAllOrders(OrderComparator.By by);

    Collection<Order> getCompletedOrdersByDate(Date start, Date finish);

    Collection<Order> getCompletedOrdersByDate(Date start, Date finish, OrderComparator.By by);

    double getSummaryAmount();

    List<String> getDetails(int id);
}
