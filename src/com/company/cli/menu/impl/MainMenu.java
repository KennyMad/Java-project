package com.company.cli.menu.impl;

import com.company.cli.items.MenuItem;
import com.company.cli.items.impl.main.ImportExportItem;
import com.company.cli.items.impl.main.OrderControlItem;
import com.company.cli.items.impl.main.StorageControlItem;
import com.company.cli.menu.Menu;
import com.company.facade.Facade;

import java.util.ArrayList;
import java.util.Collection;

public class MainMenu implements Menu {

    final String title = "Главное меню";
    Collection<MenuItem> items;

    public MainMenu (Facade facade){
        items = new ArrayList<>();
        items.add(new OrderControlItem(facade));
        items.add(new StorageControlItem(facade));
        items.add(new ImportExportItem(facade));
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public Collection<MenuItem> getItems() {
        return items;
    }
}
