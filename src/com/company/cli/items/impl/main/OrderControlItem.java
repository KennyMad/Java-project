package com.company.cli.items.impl.main;

import com.company.cli.actions.Action;
import com.company.cli.items.MenuItem;
import com.company.cli.menu.Menu;
import com.company.cli.menu.impl.OrderMenu;
import com.company.facade.Facade;

public class OrderControlItem implements MenuItem {
    final String title = "Управление заказами";
    Menu nextMenu;

    public OrderControlItem(Facade facade){
        this.nextMenu = new OrderMenu(facade);
    }

    @Override
    public void doAction() {

    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public Menu getNextMenu() {
        return nextMenu;
    }
}
