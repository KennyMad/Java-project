package com.company.cli.actions.impl;

import com.company.cli.actions.Action;
import com.company.comparators.OrderComparator;
import com.company.facade.Facade;
import com.company.models.Order;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Collection;
import java.util.Date;
import java.util.Scanner;

public class PrintCompletedOrdersAction implements Action {
    Facade facade;

    OrderComparator.By by;

    public PrintCompletedOrdersAction(Facade facade, OrderComparator.By by){
        this.facade = facade;
        this.by = by;
    }

    public void setSort(OrderComparator.By by){
        this.by = by;
    }

    @Override
    public void execute() {
        Scanner in = new Scanner(System.in);
        System.out.println("Введите период:");

        Date start = null, end = null;
        try {
            start = DateFormat.getDateInstance().parse(in.next());
            end = DateFormat.getDateInstance().parse(in.next());

            if (start.after(end)){
                System.out.println("Некорректные даты.");
                return;
            }
        }
        catch (ParseException ex){
            System.out.println("Некорректный ввод.");
            return;
        }

        Collection<Order> orders;
        if (by == null)
            orders = facade.getCompletedOrders(start,end);
        else
            orders = facade.getCompletedOrders(start, end, by);

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
