package com.company.repository;

import com.company.comparators.OrderComparator;
import com.company.exceptions.IdException;
import com.company.models.Order;
import com.company.models.OrderStatus;

import java.util.Collection;

public interface OrderDao {

    void initialize ();

    OrderStatus getOrderStatus(int id) throws IdException;

    Order getOrder(int id) throws IdException;

    void  changeOrderStatus(int id, OrderStatus orderStatus) throws IdException;

    Collection<Order> readAll();

    Collection<Order> readAll(OrderComparator.By by);

    void addOrder(Order order);

    void importOrder(Order order);
}
