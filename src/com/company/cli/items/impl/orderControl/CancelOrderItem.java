package com.company.cli.items.impl.orderControl;

import com.company.cli.actions.Action;
import com.company.cli.actions.impl.CancelOrderAction;
import com.company.cli.items.MenuItem;
import com.company.cli.menu.Menu;
import com.company.facade.Facade;

public class CancelOrderItem implements MenuItem {
    final String title = "Отменить заказ";

    final Action action;

    public  CancelOrderItem(Facade facade){
        this.action = new CancelOrderAction(facade);
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
