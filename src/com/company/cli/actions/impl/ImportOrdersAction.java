package com.company.cli.actions.impl;

import com.company.cli.actions.Action;
import com.company.exceptions.WrongPathException;
import com.company.exceptions.WrongTypeException;
import com.company.facade.Facade;

import java.util.Scanner;

public class ImportOrdersAction implements Action {
    Facade facade;

    public ImportOrdersAction(Facade facade){
        this.facade = facade;
    }

    @Override
    public void execute() {
        Scanner in = new Scanner(System.in);
        System.out.println("Введите путь к файлу");
        try {
            facade.importOrders(in.next());
        }
        catch (WrongTypeException wrongTypeException){
            System.out.println(wrongTypeException.getMessage());
        }
        catch (WrongPathException wrongPathException){
            System.out.println(wrongPathException.getMessage());
        }
    }
}
