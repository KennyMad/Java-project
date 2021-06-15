package com.company.cli.items.impl.orderControl;

import com.company.cli.actions.Action;
import com.company.cli.actions.impl.CancelOrderAction;
import com.company.cli.actions.impl.PrintAllOrdersAction;
import com.company.cli.items.MenuItem;
import com.company.cli.menu.Menu;
import com.company.facade.Facade;

public class PrintAllOrdersItem implements MenuItem {
    final String title = "Вывести все заказы";

    final Action action;

    public  PrintAllOrdersItem(Facade facade){
        this.action = new PrintAllOrdersAction(facade,null);
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
