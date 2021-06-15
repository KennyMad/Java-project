package com.company.cli.actions.impl;

import com.company.cli.actions.Action;
import com.company.facade.Facade;

public class ExportOrdersAction implements Action {
    Facade facade;

    public ExportOrdersAction(Facade facade){
        this.facade = facade;
    }

    @Override
    public void execute(){
        facade.exportOrders();
        System.out.println("Файл находится в:" + facade.getExportDestination());
    }
}
