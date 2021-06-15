package com.company.cli.menu.impl;

import com.company.cli.items.MenuItem;
import com.company.cli.items.impl.orderControl.*;
import com.company.cli.menu.Menu;
import com.company.facade.Facade;

import java.util.ArrayList;
import java.util.Collection;

public class OrderMenu implements Menu {
    final String title = "Управление заказами";

    Collection<MenuItem> menuItems;

    public OrderMenu(Facade facade){
        this.menuItems = new ArrayList<>();
        this.menuItems.add(new CancelOrderItem(facade));
        this.menuItems.add(new ChangeOrderStatusItem(facade));
        this.menuItems.add(new CreateOrderItem(facade));
        this.menuItems.add(new PrintAllOrdersItem(facade));
        this.menuItems.add(new PrintAllOrdersSortedItem(facade));
        this.menuItems.add(new PrintOrderDetailsItem(facade));
        this.menuItems.add(new PrintSummaryAmountItem(facade));
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public Collection<MenuItem> getItems() {
        return menuItems;
    }
}
