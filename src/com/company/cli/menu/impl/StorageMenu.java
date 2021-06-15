package com.company.cli.menu.impl;

import com.company.cli.items.MenuItem;
import com.company.cli.items.impl.storageControl.*;
import com.company.cli.menu.Menu;
import com.company.facade.Facade;

import java.util.ArrayList;
import java.util.Collection;

public class StorageMenu implements Menu {
    final String title = "Управление складом";

    Collection<MenuItem> menuItems;

    public StorageMenu (Facade facade){
        menuItems = new ArrayList<>();
        menuItems.add(new AddBookItem(facade));
        menuItems.add(new CreateRequestItem(facade));
        menuItems.add(new PrintAllBooksItem(facade));
        menuItems.add(new PrintAllBooksSortedItem(facade));
        menuItems.add(new PrintOutdatedBooksItem(facade));
        menuItems.add(new PrintOutdatedBooksSortedItem(facade));
        menuItems.add(new WriteOffBookItem(facade));
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
