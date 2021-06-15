package com.company.cli.actions.impl;

import com.company.cli.actions.Action;
import com.company.facade.Facade;

import java.util.InputMismatchException;
import java.util.Scanner;

public class AddBookAction implements Action {
    Facade facade;

    public AddBookAction(Facade facade){
        this.facade = facade;
    }

    @Override
    public void execute(){
        Scanner in = new Scanner(System.in);
        try {
            facade.addBook(in.nextInt());
        }
        catch (InputMismatchException ex){
            System.out.println("Некорректный ввод.");
        }
    }

}
