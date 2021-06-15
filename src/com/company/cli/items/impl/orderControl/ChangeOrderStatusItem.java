package com.company.cli.items.impl.orderControl;

import com.company.cli.actions.Action;
import com.company.cli.actions.impl.ChangeOrderStatusAction;
import com.company.cli.actions.impl.PrintOrderDetailsAction;
import com.company.cli.items.MenuItem;
import com.company.cli.menu.Menu;
import com.company.facade.Facade;

public class ChangeOrderStatusItem implements MenuItem {
    final String title = "Изменить статус заказа";

    final Action action;

    public ChangeOrderStatusItem(Facade facade){
        this.action = new ChangeOrderStatusAction(facade);
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
