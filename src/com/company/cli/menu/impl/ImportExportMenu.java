package com.company.cli.menu.impl;

import com.company.cli.items.MenuItem;
import com.company.cli.items.impl.importExport.ExportBooksItem;
import com.company.cli.items.impl.importExport.ExportOrdersItem;
import com.company.cli.items.impl.importExport.ImportBooksItem;
import com.company.cli.items.impl.importExport.ImportOrdersItem;
import com.company.cli.menu.Menu;
import com.company.facade.Facade;

import java.util.ArrayList;
import java.util.Collection;

public class ImportExportMenu implements Menu {
    String title = "Импорт / Экспорт";

    Collection<MenuItem> menuItems;

    public ImportExportMenu(Facade facade){
        menuItems = new ArrayList<>();
        menuItems.add(new ExportBooksItem(facade));
        menuItems.add(new ExportOrdersItem(facade));
        menuItems.add(new ImportBooksItem(facade));
        menuItems.add(new ImportOrdersItem(facade));
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
