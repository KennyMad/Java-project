package com.company.cli.items.impl.main;

import com.company.cli.items.MenuItem;
import com.company.cli.menu.Menu;
import com.company.cli.menu.impl.ImportExportMenu;
import com.company.facade.Facade;

public class ImportExportItem implements MenuItem {
    String title = "Импорт / Экспорт";

    Menu nextMenu;

    public ImportExportItem(Facade facade){
        nextMenu = new ImportExportMenu(facade);
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
