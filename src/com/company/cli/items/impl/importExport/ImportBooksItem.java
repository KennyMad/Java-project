package com.company.cli.items.impl.importExport;

import com.company.cli.actions.Action;
import com.company.cli.actions.impl.ImportBooksAction;
import com.company.cli.items.MenuItem;
import com.company.cli.menu.Menu;
import com.company.facade.Facade;

public class ImportBooksItem implements MenuItem {
    final String title = "Импорт книг";

    final Action action;

    public ImportBooksItem(Facade facade){
        action = new ImportBooksAction(facade);
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
