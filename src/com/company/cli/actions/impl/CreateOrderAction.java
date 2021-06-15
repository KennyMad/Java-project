package com.company.cli.actions.impl;

import com.company.cli.actions.Action;
import com.company.facade.Facade;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CreateOrderAction implements Action {
    Facade facade;

    public CreateOrderAction(Facade facade){
        this.facade = facade;
    }

    @Override
    public void execute(){
        Scanner in = new Scanner(System.in);
        List<Integer> bookIDs = new ArrayList<>();
        List<String> customerData = new ArrayList<>();
        System.out.println("Введите id книг (? если завершено):");

        String data;
        try {
            while (!(data = in.next()).equals("?")) {
                bookIDs.add(Integer.parseInt(data));
            }
        }
        catch (NumberFormatException ex){
            System.out.println("Некорректный ввод.");
            return;
        }

        System.out.println("Введите данные покупателя (? если завершено):");
        while (!(data = in.next()).equals("?")){
            customerData.add(data);
        }
        if (bookIDs.size() == 0 || customerData.size() == 0) {
            System.out.println("Некорректный ввод.");
            return;
        }

        facade.createOrder(bookIDs,customerData);
    }

}
