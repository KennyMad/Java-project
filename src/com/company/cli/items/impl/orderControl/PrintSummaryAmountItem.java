package com.company.cli.items.impl.orderControl;

import com.company.cli.actions.Action;
import com.company.cli.actions.impl.PrintOrderDetailsAction;
import com.company.cli.actions.impl.PrintSummaryAmountAction;
import com.company.cli.items.MenuItem;
import com.company.cli.menu.Menu;
import com.company.facade.Facade;

public class PrintSummaryAmountItem implements MenuItem {
    final String title = "Вывести всю заработанную сумму";

    final Action action;

    public PrintSummaryAmountItem(Facade facade){
        this.action = new PrintSummaryAmountAction(facade);
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
