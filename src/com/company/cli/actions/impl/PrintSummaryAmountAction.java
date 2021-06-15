package com.company.cli.actions.impl;

import com.company.cli.actions.Action;
import com.company.facade.Facade;

public class PrintSummaryAmountAction implements Action {
    Facade facade;

    public PrintSummaryAmountAction(Facade facade){
        this.facade = facade;
    }

    @Override
    public void execute() {
        System.out.println("Всего заработано: " + facade.getSummaryAmount());
    }
}
