package com.company.cli.actions.impl;

import com.company.cli.actions.Action;
import com.company.facade.Facade;
import sun.java2d.InvalidPipeException;

import java.util.InputMismatchException;
import java.util.Scanner;

public class PrintBookDescriptionAction implements Action {
    Facade facade;

    public PrintBookDescriptionAction(Facade facade){
        this.facade = facade;
    }

    @Override
    public void execute() {
        Scanner in = new Scanner(System.in);

        try {
            System.out.println(facade.getBookDescription(in.nextInt()));
        }
        catch (InputMismatchException ex){
            System.out.println("Некорректный ввод.");
        }
    }
}
