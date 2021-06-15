package com.company.cli.actions.impl;

import com.company.cli.actions.Action;
import com.company.facade.Facade;

import java.util.InputMismatchException;
import java.util.Scanner;

public class CreateRequestAction implements Action {
    Facade facade;

    public CreateRequestAction(Facade facade){
        this.facade = facade;
    }

    @Override
    public void execute(){
        Scanner in = new Scanner(System.in);
        try {
            facade.createRequest(in.nextInt());
        }
        catch (InputMismatchException ex){
            System.out.println("Некорректный ввод");
        }
    }

}
