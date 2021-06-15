package com.company.cli.items.impl.orderControl;

import com.company.cli.actions.Action;
import com.company.cli.actions.impl.PrintAllOrdersAction;
import com.company.cli.actions.impl.PrintCompletedOrdersAction;
import com.company.cli.items.MenuItem;
import com.company.cli.menu.Menu;
import com.company.comparators.OrderComparator;
import com.company.facade.Facade;

import java.util.InputMismatchException;
import java.util.Scanner;

public class PrintCompleteOrdersSortedItem implements MenuItem {
    final String title = "Вывести завершённые заказы (отсортированные)";

    Action action;

    public  PrintCompleteOrdersSortedItem(Facade facade){
        this.action = new PrintCompletedOrdersAction(facade,null);
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
                    ((PrintCompletedOrdersAction)action).setSort(OrderComparator.By.ORDER_DATE);
                    break;
                case 2:
                    ((PrintCompletedOrdersAction)action).setSort(OrderComparator.By.PRICE);
                    break;
                case 3:
                    ((PrintCompletedOrdersAction)action).setSort(OrderComparator.By.ORDER_STATUS);
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
