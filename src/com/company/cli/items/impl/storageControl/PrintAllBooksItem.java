package com.company.cli.items.impl.storageControl;

import com.company.cli.actions.Action;
import com.company.cli.actions.impl.AddBookAction;
import com.company.cli.actions.impl.PrintAllBooksAction;
import com.company.cli.items.MenuItem;
import com.company.cli.menu.Menu;
import com.company.facade.Facade;

public class PrintAllBooksItem implements MenuItem {
    final String title = "Показать все книги";

    final Action action;

    public PrintAllBooksItem(Facade facade){
        action = new PrintAllBooksAction(facade,null);
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
