package com.company.cli.items.impl.importExport;

import com.company.cli.actions.Action;
import com.company.cli.actions.impl.ExportBooksAction;
import com.company.cli.items.MenuItem;
import com.company.cli.menu.Menu;
import com.company.facade.Facade;

public class ExportBooksItem implements MenuItem {
    final String title = "Экспорт книг";

    final Action action;

    public ExportBooksItem(Facade facade){
        action = new ExportBooksAction(facade);
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
