package com.company.cli.items.impl.storageControl;

import com.company.cli.actions.Action;
import com.company.cli.actions.impl.WriteOffBookAction;
import com.company.cli.items.MenuItem;
import com.company.cli.menu.Menu;
import com.company.facade.Facade;

public class WriteOffBookItem implements MenuItem {
    final String title = "Списать книгу со склада.";

    final Action action;

    public WriteOffBookItem(Facade facade){
        action = new WriteOffBookAction(facade);
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
