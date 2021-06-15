package com.company.cli.items.impl.importExport;

import com.company.cli.actions.Action;
import com.company.cli.actions.impl.ImportOrdersAction;
import com.company.cli.items.MenuItem;
import com.company.cli.menu.Menu;
import com.company.facade.Facade;

public class ImportOrdersItem implements MenuItem {
    final String title = "Импорт заказов";

    final Action action;

    public ImportOrdersItem(Facade facade){
        action = new ImportOrdersAction(facade);
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
