package com.company.cli.items.impl.importExport;

import com.company.cli.actions.Action;
import com.company.cli.actions.impl.ExportOrdersAction;
import com.company.cli.items.MenuItem;
import com.company.cli.menu.Menu;
import com.company.facade.Facade;

public class ExportOrdersItem implements MenuItem {
    final String title = "Экспорт заказов";

    final Action action;

    public ExportOrdersItem(Facade facade){
        action = new ExportOrdersAction(facade);
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
