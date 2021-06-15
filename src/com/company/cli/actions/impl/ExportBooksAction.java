package com.company.cli.actions.impl;

import com.company.cli.actions.Action;
import com.company.facade.Facade;

public class ExportBooksAction implements Action {
    Facade facade;

    public ExportBooksAction(Facade facade){
        this.facade = facade;
    }

    @Override
    public void execute(){
        facade.exportBooks();
        System.out.println("Файл находится в:" + facade.getExportDestination());
    }
}
