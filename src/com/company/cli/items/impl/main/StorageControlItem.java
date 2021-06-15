package com.company.cli.items.impl.main;

import com.company.cli.items.MenuItem;
import com.company.cli.menu.Menu;
import com.company.cli.menu.impl.StorageMenu;
import com.company.facade.Facade;

public class StorageControlItem implements MenuItem {
    final String title = "Управление складом";
    Menu nextMenu;

    public StorageControlItem (Facade facade){
        nextMenu = new StorageMenu(facade);
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
