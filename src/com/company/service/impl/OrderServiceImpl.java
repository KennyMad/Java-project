package com.company.service.impl;

import com.company.comparators.OrderComparator;
import com.company.exceptions.IdException;
import com.company.logger.Logger;
import com.company.models.Book;
import com.company.models.Order;
import com.company.models.OrderStatus;
import com.company.models.StorageStatus;
import com.company.repository.OrderDao;
import com.company.repository.StorageDao;
import com.company.service.OrderService;

import java.util.*;

public class OrderServiceImpl implements OrderService {

    OrderDao orderDao;
    StorageDao storageDao;

    public OrderServiceImpl (OrderDao orderDao, StorageDao storageDao){
        this.orderDao = orderDao;
        this.storageDao = storageDao;
    }

    int generateId(){
        int id = 0;
        try {
            while (orderDao.getOrder(id) != null) id++;
        }
        catch (IdException ex){

        }
        return id;
    }

    @Override
    public void createOrder(List<Integer> bookIds, List <String> customerData) {
        try {
            Order order = new Order();
            order.setCustomerData(customerData);
            order.setId(generateId());
            order.setBookIds(bookIds);
            order.setOrderStatus(OrderStatus.NEW);
            double price = 0;
            for (int booksId : bookIds) {
                if (storageDao.getBookStatus(booksId) == StorageStatus.ABSENT) {
                    storageDao.addRequest(booksId);
                    Logger.log("Добавлен запрос на книгу " + booksId + ".");
                }
                price += storageDao.getBook(booksId).getPrice();

                storageDao.setLastBookPurchase(booksId, new Date());
            }
            order.setPrice(price);
            order.setOrderDate(new Date());
            orderDao.addOrder(order);

            Logger.log("Заказ " + order.getId() + " создан.");
        }
        catch (IdException ex){
            Logger.log("Ошибка создания заказа." + ex.getMessage() + ex.getId());
        }
    }

    @Override
    public void cancelOrder(int id) {
        try {
            orderDao.changeOrderStatus(id, OrderStatus.CANCELED);
            Logger.log("Заказ " + id + " закрыт.");
        }
        catch (IdException ex){
            Logger.log("Ошибка завершения заказа. " + ex.getMessage() + " " + ex.getId());
        }
    }

    @Override
    public boolean changeStatus(int id, OrderStatus orderStatus) {
        if (orderStatus == OrderStatus.COMPLETED){
            boolean bookIsMissing = false;
            try {
                for (int booksId : orderDao.getOrder(id).getBookIds()) {
                    if (storageDao.getRequest(booksId) != 0) {
                        bookIsMissing = true;
                        Logger.log("Ошибка изменения статуса из-за отсутствия книги " + booksId + ".");
                    }
                }
            }
            catch (IdException ex){
                Logger.log("Ошибка изменения статуса заказа. " + ex.getMessage() + ex.getId());
            }
            if (bookIsMissing) {
                return false;
            }
        }
        try {
            orderDao.changeOrderStatus(id, orderStatus);
            Logger.log("Статус изменён.");
        }
        catch (IdException ex){
            Logger.log("Ошибка изменения статуса заказа. " + ex.getMessage() + " " + ex.getId());
        }
        return true;
    }

    @Override
    public Collection<Order> getCompletedOrdersByDate(Date start, Date finish) {
        Collection<Order> orders = new ArrayList<Order>();
        for (Order order: orderDao.readAll()){
            if (order.getOrderDate().after(start) && order.getOrderDate().before(finish) && order.getOrderStatus() == OrderStatus.COMPLETED)
                orders.add(order);
        }
        return orders;
    }

    @Override
    public Collection<Order> getCompletedOrdersByDate(Date start, Date finish, OrderComparator.By by) {
        ArrayList<Order> orders = new ArrayList<>(getCompletedOrdersByDate(start,finish));

        Comparator<Order> comparator = new OrderComparator(by);
        orders.sort(comparator);

        return orders;
    }

    @Override
    public double getSummaryAmount(){
        double amount = 0;
        for (Order order: orderDao.readAll()){
            if (order.getOrderStatus() == OrderStatus.COMPLETED)
                amount += order.getPrice();
        }
        return amount;
    }

    @Override
    public List<String> getDetails(int id){
        ArrayList<String> details = new ArrayList<>();
        try {
            details.addAll(orderDao.getOrder(id).getCustomerData());
            for (int bookId : orderDao.getOrder(id).getBookIds()) {
                details.add(storageDao.getBook(bookId).getName());
            }
            return details;
        }
        catch (IdException ex){
            Logger.log("Ошибка получения деталей заказа." + ex.getMessage() + " " + ex.getId());
        }
        return details;
    }

    @Override
    public Collection<Order> getAllOrders(){
        return orderDao.readAll();
    }

    @Override
    public Collection<Order> getAllOrders(OrderComparator.By by){
        return orderDao.readAll(by);
    }
}
