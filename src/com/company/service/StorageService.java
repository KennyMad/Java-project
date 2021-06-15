package com.company.service;

import com.company.comparators.BookComparator;
import com.company.models.Book;

import java.util.Collection;

public interface StorageService {

    void writeOffBook(int id);

    void addBook(int id);

    void createRequest(int id);

    Collection<Book> getOutdatedBooks();

    Collection<Book> getOutdatedBooks(BookComparator.By by);

    Collection<Book> getAllBooks();

    Collection<Book> getAllBooks(BookComparator.By by);

    String getBookDescription(int id);
}
