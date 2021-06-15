package com.company.cli.navigator;

import com.company.cli.items.MenuItem;
import com.company.cli.menu.Menu;

import java.util.EmptyStackException;
import java.util.Stack;

public class MenuNavigator {

    Menu currentMenu;

    Stack<Menu> queueMenu;

    public MenuNavigator (Menu menu){
        currentMenu = menu;
        queueMenu = new Stack<>();
    }

    public void printMenu(){
        System.out.println(currentMenu.getTitle() + ":");

        int i = 1;
        for (MenuItem menuItem: currentMenu.getItems()){
            System.out.println( "\t" + i++ + ") " + menuItem.getTitle());
        }
        try {
            queueMenu.peek();
            System.out.println("\t" + i + ") Назад.");
        }
        catch (EmptyStackException ex) {
            System.out.println("\t" + i + ") Выход.");
        }
    }

    public void navigate(int index){
        if (index == currentMenu.getItems().size()){
            try {
                currentMenu = queueMenu.pop();
            }
            catch (EmptyStackException ex){
                currentMenu = null;
            }
            return;
        }

        if (index > currentMenu.getItems().size()){
            System.out.println("Некорректный ввод.");
            return;
        }

        MenuItem menuItem = (MenuItem) (currentMenu.getItems().toArray()[index]);
        menuItem.doAction();
        if (menuItem.getNextMenu() != null){
            queueMenu.push(currentMenu);
            currentMenu = menuItem.getNextMenu();
        }
    }

    public Menu getCurrentMenu() {
        return currentMenu;
    }
}
