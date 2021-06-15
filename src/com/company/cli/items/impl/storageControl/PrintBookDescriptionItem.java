package com.company.cli.items.impl.storageControl;

import com.company.cli.actions.Action;
import com.company.cli.actions.impl.AddBookAction;
import com.company.cli.actions.impl.PrintBookDescriptionAction;
import com.company.cli.items.MenuItem;
import com.company.cli.menu.Menu;
import com.company.facade.Facade;

public class PrintBookDescriptionItem implements MenuItem {
    final String title = "Показать описание книги";

    final Action action;

    public PrintBookDescriptionItem(Facade facade){
        action = new PrintBookDescriptionAction(facade);
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
