package com.company.cli.menu;

import com.company.cli.items.MenuItem;

import java.util.Collection;

public interface Menu {

    String getTitle();

    Collection<MenuItem> getItems();

}
