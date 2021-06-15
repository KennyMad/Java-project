package com.company.application;

import com.company.cli.controller.MenuController;
import com.company.comparators.BookComparator;
import com.company.context.Context;
import com.company.facade.Facade;
import com.company.facade.impl.FacadeImpl;
import com.company.models.Book;
import com.company.repository.OrderDao;
import com.company.repository.StorageDao;
import com.company.repository.impl.OrderDaoImpl;
import com.company.repository.impl.StorageDaoImpl;
import com.company.service.OrderService;
import com.company.service.StorageService;
import com.company.service.impl.OrderServiceImpl;
import com.company.service.impl.StorageServiceImpl;

public class Application {

    public static void main(String[] args){
        Context context = new Context();
        context.initialize();
        context.getMenuController().run();
    }
}
