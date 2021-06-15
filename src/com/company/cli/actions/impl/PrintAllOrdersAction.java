package com.company.cli.actions.impl;

import com.company.cli.actions.Action;
import com.company.comparators.OrderComparator;
import com.company.facade.Facade;
import com.company.models.Order;

import java.util.Collection;

public class PrintAllOrdersAction implements Action {
    Facade facade;

    OrderComparator.By by;

    public PrintAllOrdersAction(Facade facade, OrderComparator.By by){
        this.facade = facade;
        this.by = by;
    }

    public void setSort(OrderComparator.By by){
        this.by = by;
    }

    @Override
    public void execute() {
        Collection<Order> orders;

        if (by == null)
            orders = facade.getAllOrders();
        else
            orders = facade.getAllOrders(by);

        for (Order order: orders){
            System.out.println("ID: " + order.getId());
            System.out.println("Дата заказа: " + order.getOrderDate().toString());
            System.out.println("Данные заказчика:");
            for (String data: order.getCustomerData())
                System.out.println(data);
            System.out.println("ID заказанных книг:");
            for (int id: order.getBookIds())
                System.out.println(id);
            System.out.println("Итоговая цена: " + order.getPrice());
            System.out.print("Статус: ");
            switch (order.getOrderStatus()){
                case NEW:
                    System.out.println("Новый");
                    break;
                case COMPLETED:
                    System.out.println("Завершён");
                    break;
                case CANCELED:
                    System.out.println("Отменён");
                    break;
            }
        }
    }
}
