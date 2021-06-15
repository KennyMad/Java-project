package com.company.repository.impl;

import com.company.comparators.BookComparator;
import com.company.exceptions.IdException;
import com.company.models.Book;
import com.company.models.StorageStatus;
import com.company.repository.StorageDao;

import java.lang.reflect.Array;
import java.util.*;

public class StorageDaoImpl implements StorageDao {

    Map<Integer, Book> books;
    Map<Integer, StorageStatus> storageStatuses;
    Map<Integer, Date> lastBookPurchase;

    @Override
    public void initialize (){
        books = new HashMap<>();
        storageStatuses = new HashMap<>();
        lastBookPurchase = new HashMap<>();
    }

    @Override
    public Book getBook(int id) throws IdException {
        Book book;
        try {
            book = books.get(id);
            if (book == null)
                throw new IdException(id);
            return book;
        }
        catch (NullPointerException ex){
            throw new IdException(id);
        }
    }

    @Override
    public StorageStatus getBookStatus(int id) throws IdException {
        try {
            StorageStatus storageStatus = storageStatuses.get(id);
            if (storageStatus == null)
                throw new IdException(id);
            return storageStatus;
        }
        catch (NullPointerException ex){
            throw new IdException(id);
        }
    }

    @Override
    public void changeBookStatus(int id, StorageStatus storageStatus) throws IdException {
        try {
            this.storageStatuses.replace(id, storageStatus);
            this.books.get(id).setStorageStatus(storageStatus);
        }
        catch (NullPointerException ex){
            throw new IdException(id);
        }
    }

    @Override
    public Collection<Book> readAllBooks() {
        return books.values();
    }

    @Override
    public Collection<Book> readAllBooks(BookComparator.By by) {
        Comparator comparator = new BookComparator(by);
        List books = new ArrayList(this.books.values());
        books.sort(comparator);
        return books;
    }

    @Override
    public Collection<StorageStatus> readAllStatuses() {
        return storageStatuses.values();
    }

    @Override
    public int getRequest(int id) throws IdException {
        try {
            return books.get(id).getRequests();
        }
        catch (NullPointerException ex){
            throw new IdException(id);
        }
    }

    @Override
    public void addRequest(int id) throws IdException {
        try {
            books.get(id).setRequests(books.get(id).getRequests() + 1);
        }
        catch (NullPointerException ex){
            throw new IdException(id);
        }
    }

    @Override
    public void closeRequest(int id) throws IdException{
        try {
            books.get(id).setRequests(0);
        }
        catch (NullPointerException ex){
            throw new IdException(id);
        }
    }

    @Override
    public Date getLastBookPurchase(int id) throws IdException {
        Date date;
        try {
            date = lastBookPurchase.get(id);
            return date;
        }
        catch (NullPointerException ex){
            throw new IdException(id);
        }
    }

    @Override
    public void setLastBookPurchase(int id, Date date) throws IdException{
        try {
            lastBookPurchase.replace(id, date);
        }
        catch (NullPointerException ex){
            throw new IdException(id);
        }
    }

    @Override
    public void importBook(Book book, Date lastPurchase){
        if (books.get(book.getId()) != null){
            books.replace(book.getId(),book);
            storageStatuses.replace(book.getId(),book.getStorageStatus());
            lastBookPurchase.replace(book.getId(),lastPurchase);
        }
        else {
            books.put(book.getId(), book);
            storageStatuses.put(book.getId(),book.getStorageStatus());
            lastBookPurchase.put(book.getId(),lastPurchase);
        }
    }
}
