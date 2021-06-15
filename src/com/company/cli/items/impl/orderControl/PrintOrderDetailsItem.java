package com.company.cli.items.impl.orderControl;

import com.company.cli.actions.Action;
import com.company.cli.actions.impl.CreateOrderAction;
import com.company.cli.actions.impl.PrintOrderDetailsAction;
import com.company.cli.items.MenuItem;
import com.company.cli.menu.Menu;
import com.company.facade.Facade;

public class PrintOrderDetailsItem implements MenuItem {
    final String title = "Вывести детали заказа";

    final Action action;

    public PrintOrderDetailsItem(Facade facade){
        this.action = new PrintOrderDetailsAction(facade);
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
