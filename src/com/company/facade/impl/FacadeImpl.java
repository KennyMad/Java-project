package com.company.facade.impl;

import com.company.comparators.BookComparator;
import com.company.comparators.OrderComparator;
import com.company.exceptions.WrongPathException;
import com.company.exceptions.WrongTypeException;
import com.company.facade.Facade;
import com.company.models.Book;
import com.company.models.Order;
import com.company.models.OrderStatus;
import com.company.service.ImportExportService;
import com.company.service.OrderService;
import com.company.service.StorageService;
import com.company.service.impl.ImportExportServiceImpl;

import java.util.Collection;
import java.util.Date;
import java.util.List;

public class FacadeImpl implements Facade {

    final OrderService orderService;
    final StorageService storageService;
    final ImportExportService importExportService;

    public FacadeImpl(OrderService orderService, StorageService storageService, ImportExportService importExportService){
        this.orderService = orderService;
        this.storageService = storageService;
        this.importExportService = importExportService;
    }

    @Override
    public void writeOffBook(int id) {
        storageService.writeOffBook(id);
    }

    @Override
    public void addBook(int id) {
        storageService.addBook(id);
    }

    @Override
    public void createRequest(int id) {
        storageService.createRequest(id);
    }

    @Override
    public void createOrder(List<Integer> bookIds, List<String> customerData) {
        orderService.createOrder(bookIds, customerData);
    }

    @Override
    public void cancelOrder(int id) {
        orderService.cancelOrder(id);
    }

    @Override
    public boolean changeOrderStatus(int id, OrderStatus orderStatus) {
        return orderService.changeStatus(id, orderStatus);
    }

    @Override
    public Collection<Book> getAllBooks() {
        return storageService.getAllBooks();
    }

    @Override
    public Collection<Book> getAllBooks(BookComparator.By by){
        return storageService.getAllBooks(by);
    }

    @Override
    public Collection<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @Override
    public Collection<Order> getAllOrders(OrderComparator.By by){
        return orderService.getAllOrders(by);
    }

    @Override
    public Collection<Order> getCompletedOrders(Date start, Date finish) {
        return orderService.getCompletedOrdersByDate(start, finish);
    }

    @Override
    public Collection<Order> getCompletedOrders(Date start, Date finish, OrderComparator.By by){
        return orderService.getCompletedOrdersByDate(start,finish,by);
    }

    @Override
    public double getSummaryAmount() {
        return orderService.getSummaryAmount();
    }

    @Override
    public Collection<Book> getOutdatedBooks() {
        return storageService.getOutdatedBooks();
    }

    @Override
    public Collection<Book> getOutdatedBooks(BookComparator.By by) {
        return storageService.getOutdatedBooks(by);
    }

    @Override
    public  List<String> getOrderDetails(int id) {
        return orderService.getDetails(id);
    }

    @Override
    public String getBookDescription(int id) {
        return storageService.getBookDescription(id);
    }

    @Override
    public void importBooks(String path) throws WrongTypeException, WrongPathException {
        importExportService.importBooks(path);
    }

    @Override
    public void exportBooks() {
        importExportService.exportBooks();
    }

    @Override
    public void importOrders(String path) throws WrongTypeException, WrongPathException {
        importExportService.importOrders(path);
    }

    @Override
    public void exportOrders() {
        importExportService.exportOrders();
    }

    @Override
    public String getExportDestination() {
        return importExportService.getExportDestination();
    }
}
