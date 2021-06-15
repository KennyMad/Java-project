package com.company.comparators;

import com.company.models.Order;

import java.util.Comparator;

public class OrderComparator implements Comparator<Order> {

    public enum By {
        ORDER_DATE,
        PRICE,
        ORDER_STATUS
    }

    By by;

    public OrderComparator(){
        this.by = By.ORDER_DATE;
    }

    public OrderComparator (By by){
        this.by = by;
    }

    @Override
    public int compare(Order o1, Order o2) {
        switch (by){
            case PRICE:
                if (o1.getPrice() > o2.getPrice())
                    return 1;
                else if (o1.getPrice() < o2.getPrice())
                    return -1;
                else
                    return 0;
            case ORDER_DATE:
                return o1.getOrderDate().compareTo(o2.getOrderDate());
            case ORDER_STATUS:
                return o1.getOrderStatus().compareTo(o2.getOrderStatus());
        }
        return 0;
    }
}
