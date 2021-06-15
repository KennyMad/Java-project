package com.company.context;

import com.company.cli.controller.MenuController;
import com.company.facade.Facade;
import com.company.facade.impl.FacadeImpl;
import com.company.repository.OrderDao;
import com.company.repository.StorageDao;
import com.company.repository.impl.OrderDaoImpl;
import com.company.repository.impl.StorageDaoImpl;
import com.company.service.ImportExportService;
import com.company.service.OrderService;
import com.company.service.StorageService;
import com.company.service.impl.ImportExportServiceImpl;
import com.company.service.impl.OrderServiceImpl;
import com.company.service.impl.StorageServiceImpl;

public class Context {

    OrderService orderService;
    StorageService storageService;
    ImportExportService importExportService;

    OrderDao orderDao;
    StorageDao storageDao;

    MenuController menuController;

    Facade facade;

    public void initialize(){
        orderDao = new OrderDaoImpl();
        storageDao = new StorageDaoImpl();

        orderDao.initialize();
        storageDao.initialize();

        orderService = new OrderServiceImpl(orderDao, storageDao);
        storageService = new StorageServiceImpl(storageDao);
        importExportService = new ImportExportServiceImpl(orderDao,storageDao);

        facade = new FacadeImpl(orderService, storageService, importExportService);

        menuController = new MenuController(facade);
    }

    public Facade getFacade() {
        return facade;
    }

    public OrderDao getOrderDao() {
        return orderDao;
    }

    public OrderService getOrderService() {
        return orderService;
    }

    public StorageDao getStorageDao() {
        return storageDao;
    }

    public StorageService getStorageService() {
        return storageService;
    }

    public ImportExportService getImportExportService() {
        return importExportService;
    }

    public MenuController getMenuController() {
        return menuController;
    }
}
