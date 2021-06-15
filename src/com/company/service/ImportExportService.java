package com.company.service;

import com.company.exceptions.WrongPathException;
import com.company.exceptions.WrongTypeException;
import com.company.repository.OrderDao;
import com.company.repository.StorageDao;

public interface ImportExportService {

    void importBooks(String path) throws WrongTypeException, WrongPathException;

    void exportBooks();

    void importOrders(String path) throws WrongTypeException, WrongPathException;

    void exportOrders();

    String getExportDestination();
}
