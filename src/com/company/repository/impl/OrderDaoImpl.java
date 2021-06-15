package com.company.repository.impl;

import com.company.comparators.OrderComparator;
import com.company.exceptions.IdException;
import com.company.models.Order;
import com.company.models.OrderStatus;
import com.company.repository.OrderDao;

import java.util.*;

public class OrderDaoImpl implements OrderDao {

    Map<Integer, Order> orders;

    @Override
    public void initialize(){
        orders = new HashMap<>();
    }

    @Override
    public OrderStatus getOrderStatus(int id) throws IdException {
        Order order = null;
        try {
            order = orders.get(id);
            return order.getOrderStatus();
        }
        catch (NullPointerException ex){
            throw new IdException(id);
        }
    }

    @Override
    public Order getOrder(int id) throws  IdException{
        Order order = null;
        try {
            order = orders.get(id);
            if (order == null)
                throw new IdException(id);
            return order;
        }
        catch (NullPointerException ex){
            throw new IdException(id);
        }
    }

    @Override
    public void changeOrderStatus(int id, OrderStatus orderStatus) throws IdException {
        try {
            orders.get(id).setOrderStatus(orderStatus);
        }
        catch (NullPointerException ex){
            throw new IdException(id);
        }
    }

    @Override
    public Collection<Order> readAll(){
        return orders.values();
    }

    @Override
    public Collection<Order> readAll(OrderComparator.By by){
        List orders = new ArrayList(this.orders.values());

        Comparator comparator = new OrderComparator(by);

        orders.sort(comparator);

        return orders;
    }

    @Override
    public void addOrder(Order order){
        orders.put(order.getId(),order);
    }

    @Override
    public void importOrder(Order order){
        if (orders.get(order.getId()) != null)
            orders.replace(order.getId(),order);
        else
            orders.put(order.getId(),order);
    }
}
