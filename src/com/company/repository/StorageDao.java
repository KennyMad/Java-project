package com.company.repository;

import com.company.comparators.BookComparator;
import com.company.exceptions.IdException;
import com.company.models.Book;
import com.company.models.StorageStatus;

import java.util.Collection;
import java.util.Date;

public interface StorageDao {

    void initialize();

    Book getBook(int id) throws IdException;

    StorageStatus getBookStatus(int id) throws IdException;

    void changeBookStatus(int id, StorageStatus storageStatus) throws IdException;

    Collection<Book> readAllBooks();

    Collection<Book> readAllBooks(BookComparator.By by);

    Collection<StorageStatus> readAllStatuses();

    int getRequest(int id) throws IdException;

    void addRequest(int id) throws IdException;

    void closeRequest(int id) throws IdException;

    Date getLastBookPurchase(int id) throws IdException;

    void setLastBookPurchase(int id, Date date) throws IdException;

    void importBook(Book book, Date lastPurchase);
}
