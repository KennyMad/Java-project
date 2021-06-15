package com.company.cli.items;

import com.company.cli.menu.Menu;

public interface MenuItem {

    void doAction();

    String getTitle();

    Menu getNextMenu();

}
