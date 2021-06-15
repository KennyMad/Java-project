package com.company.cli.controller;

import com.company.cli.menu.impl.MainMenu;
import com.company.cli.navigator.MenuNavigator;
import com.company.facade.Facade;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuController {

    MenuNavigator menuNavigator;

    Facade facade;

    public MenuController(Facade facade){
        this.facade = facade;

        menuNavigator = new MenuNavigator(new MainMenu(facade));
    }

    public void run(){
        Scanner in = new Scanner(System.in);
        while (menuNavigator.getCurrentMenu() != null){
            menuNavigator.printMenu();
            try {
                menuNavigator.navigate(in.nextInt() - 1);
            }
            catch (InputMismatchException ex){
                System.out.println("Некорректный ввод.");
            }
        }
    }

}
