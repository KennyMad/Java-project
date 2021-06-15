package com.company.cli.items.impl.orderControl;

import com.company.cli.actions.Action;
import com.company.cli.actions.impl.CreateOrderAction;
import com.company.cli.items.MenuItem;
import com.company.cli.menu.Menu;
import com.company.facade.Facade;

public class CreateOrderItem implements MenuItem {
    final String title = "Создать заказ";

    final Action action;

    public  CreateOrderItem(Facade facade){
        this.action = new CreateOrderAction(facade);
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
