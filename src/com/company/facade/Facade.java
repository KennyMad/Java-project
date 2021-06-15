package com.company.facade;

import com.company.comparators.BookComparator;
import com.company.comparators.OrderComparator;
import com.company.exceptions.WrongPathException;
import com.company.exceptions.WrongTypeException;
import com.company.models.Book;
import com.company.models.Order;
import com.company.models.OrderStatus;
import javafx.print.Collation;

import java.util.Collection;
import java.util.Date;
import java.util.List;

public interface Facade {

    void writeOffBook(int id);

    void addBook(int id);

    void createRequest(int id);

    void createOrder(List<Integer> bookIds, List<String> customerData);

    void cancelOrder(int id);

    boolean changeOrderStatus (int id, OrderStatus orderStatus);

    Collection<Book> getAllBooks();

    Collection<Book> getAllBooks (BookComparator.By by);

    Collection<Order> getAllOrders();

    Collection<Order> getAllOrders(OrderComparator.By by);

    Collection<Order> getCompletedOrders(Date start, Date finish);

    Collection<Order> getCompletedOrders(Date start, Date finish, OrderComparator.By by);

    double getSummaryAmount();

    Collection<Book> getOutdatedBooks();

    Collection<Book> getOutdatedBooks(BookComparator.By by);

    List<String> getOrderDetails(int id);

    String getBookDescription(int id);

    void importBooks(String path) throws WrongTypeException, WrongPathException;

    void exportBooks();

    void importOrders(String path) throws WrongTypeException, WrongPathException;

    void exportOrders();

    String getExportDestination();
}
