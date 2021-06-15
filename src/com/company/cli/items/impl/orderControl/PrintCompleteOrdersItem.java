package com.company.cli.items.impl.orderControl;

import com.company.cli.actions.Action;
import com.company.cli.actions.impl.CreateOrderAction;
import com.company.cli.actions.impl.PrintCompletedOrdersAction;
import com.company.cli.items.MenuItem;
import com.company.cli.menu.Menu;
import com.company.facade.Facade;

public class PrintCompleteOrdersItem implements MenuItem {
    final String title = "Вывести завершённые заказы";

    final Action action;

    public  PrintCompleteOrdersItem(Facade facade){
        this.action = new PrintCompletedOrdersAction(facade,null);
    }

    @Override
    public void doAction() {
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
