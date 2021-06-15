package com.company.cli.items.impl.storageControl;

import com.company.cli.actions.Action;
import com.company.cli.actions.impl.PrintOutdatedBooksAction;
import com.company.cli.items.MenuItem;
import com.company.cli.menu.Menu;
import com.company.facade.Facade;

public class PrintOutdatedBooksItem implements MenuItem {
    final String title = "Показать все залежавшиеся книги";

    final Action action;

    public PrintOutdatedBooksItem(Facade facade){
        action = new PrintOutdatedBooksAction(facade,null);
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
