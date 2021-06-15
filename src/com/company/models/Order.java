package com.company.models;

import java.util.Date;
import java.util.List;

public class Order {

    int id;

    List<Integer> bookIds;

    OrderStatus orderStatus;

    Date orderDate;

    double price;

    List<String> customerData;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public List<Integer> getBookIds() {
        return bookIds;
    }

    public void setBookIds(List<Integer> bookIds) {
        this.bookIds = bookIds;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public List<String> getCustomerData() {
        return customerData;
    }

    public void setCustomerData(List <String> customerData) {
        this.customerData = customerData;
    }
}
