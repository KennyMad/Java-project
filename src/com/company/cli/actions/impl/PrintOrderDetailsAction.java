package com.company.cli.actions.impl;

import com.company.cli.actions.Action;
import com.company.facade.Facade;

import java.util.InputMismatchException;
import java.util.Scanner;

public class PrintOrderDetailsAction implements Action {
    Facade facade;

    public PrintOrderDetailsAction (Facade facade){
        this.facade = facade;
    }

    @Override
    public void execute() {
        Scanner in = new Scanner(System.in);

        try {
            for (String data : facade.getOrderDetails(in.nextInt())) {
                System.out.println(data);
            }
        }
        catch (InputMismatchException ex){
            System.out.println("Некорректный ввод.");
        }

    }
}
