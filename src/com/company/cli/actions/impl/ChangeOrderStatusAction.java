package com.company.cli.actions.impl;

import com.company.cli.actions.Action;
import com.company.facade.Facade;
import com.company.models.OrderStatus;
import javafx.scene.control.TextFormatter;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ChangeOrderStatusAction implements Action {
    Facade facade;

    public ChangeOrderStatusAction(Facade facade){
        this.facade = facade;
    }

    @Override
    public  void execute(){
        Scanner in = new Scanner(System.in);

        int id;
        try {
            id = in.nextInt();
        }
        catch (InputMismatchException ex){
            System.out.println("Некорректный ввод.");
            return;
        }

        System.out.println("Выберите статус: \n" +
                "1) Новый\n" +
                "2) Завершён\n" +
                "3) Отменён");
        OrderStatus status = OrderStatus.NEW;
        try {
            switch (in.nextInt()){
                case 1:
                    status = OrderStatus.NEW;
                    break;
                case 2:
                    status = OrderStatus.COMPLETED;
                    break;
                case 3:
                    status = OrderStatus.CANCELED;
                    break;
                default:
                    System.out.println("Некорректный ввод.");
                    return;
            }
        }
        catch (InputMismatchException ex){
            System.out.println("Некорректный ввод.");
        }

        facade.changeOrderStatus(id,status);
    }
}
