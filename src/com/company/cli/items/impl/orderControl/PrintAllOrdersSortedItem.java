package com.company.cli.items.impl.orderControl;

import com.company.cli.actions.Action;
import com.company.cli.actions.impl.PrintAllOrdersAction;
import com.company.cli.items.MenuItem;
import com.company.cli.menu.Menu;
import com.company.comparators.OrderComparator;
import com.company.facade.Facade;

import java.util.InputMismatchException;
import java.util.Scanner;

public class PrintAllOrdersSortedItem implements MenuItem {
    final String title = "Вывести все заказы (отсортированные)";

    Action action;

    public  PrintAllOrdersSortedItem(Facade facade){
        this.action = new PrintAllOrdersAction(facade,null);
    }

    @Override
    public void doAction() {
        Scanner in = new Scanner(System.in);

        System.out.println("1) По дате заказа \n" +
                "2) По цене \n" +
                "3) По статусу заказа");

        try {
            switch (in.nextInt()){
                case 1:
                    ((PrintAllOrdersAction)action).setSort(OrderComparator.By.ORDER_DATE);
                    break;
                case 2:
                    ((PrintAllOrdersAction)action).setSort(OrderComparator.By.PRICE);
                    break;
                case 3:
                    ((PrintAllOrdersAction)action).setSort(OrderComparator.By.ORDER_STATUS);
                    break;
                default:
                    System.out.println("Некорректный ввод.");
                    return;
            }
        }
        catch (InputMismatchException exception){
            System.out.println("Некорректный ввод.");
            return;
        }
        action.execute();
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public Menu getNextMenu() {
        return null;
    }
}
