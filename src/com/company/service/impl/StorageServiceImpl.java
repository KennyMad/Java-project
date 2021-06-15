package com.company.service.impl;

import com.company.comparators.BookComparator;
import com.company.exceptions.IdException;
import com.company.logger.Logger;
import com.company.models.Book;
import com.company.models.StorageStatus;
import com.company.repository.StorageDao;
import com.company.service.StorageService;

import java.util.*;

public class StorageServiceImpl implements StorageService {

    StorageDao storageDao;

    public StorageServiceImpl (StorageDao storageDao){
        this.storageDao = storageDao;
    }

    @Override
    public void writeOffBook(int id) {
        try {
            storageDao.changeBookStatus(id, StorageStatus.ABSENT);
            Logger.log("Книга списана со склада.");
        }
        catch (IdException ex){
            Logger.log("Ошибка списания книги со склада." + ex.getMessage() + ex.getId());
        }
    }

    @Override
    public void addBook(int id) {
        try {
            storageDao.changeBookStatus(id, StorageStatus.IN_STOCK);
            storageDao.closeRequest(id);
            Logger.log("Книга добавлена на склад.");
            Logger.log("Запросы закрыты.");
        }
        catch (IdException ex){
            Logger.log("Ошибка добавления книги." + ex.getMessage() + ex.getId());
        }
    }

    @Override
    public void createRequest(int id){
        try {
            if (storageDao.getBookStatus(id) == StorageStatus.ABSENT) {
                storageDao.addRequest(id);
                Logger.log("Добавлен запрос на книгу.");
            }
        }
        catch (IdException ex){
            Logger.log("Ошибка добавления запроса." + ex.getMessage() + ex.getId());
        }
    }

    @Override
    public Collection<Book> getOutdatedBooks(){
        Collection<Book> outdatedBooks = new ArrayList<>();
        Date today = new Date();
        try {
            for (Book book : storageDao.readAllBooks()) {
                if (storageDao.getLastBookPurchase(book.getId()) != null) {
                    long difference = today.getTime() - storageDao.getLastBookPurchase(book.getId()).getTime();
                    if (difference / (24 * 60 * 60 * 1000) > 180)
                        outdatedBooks.add(book);
                }
            }
        }
        catch (IdException ex){
            Logger.log("Ошибка получения залежавшейся книги." + ex.getMessage() + ex.getId());
        }
        return outdatedBooks;
    }

    @Override
    public Collection<Book> getOutdatedBooks(BookComparator.By by){
        List<Book> outDatedBooks = new ArrayList<>(getOutdatedBooks());
        Comparator comparator = new BookComparator(by);
        outDatedBooks.sort(comparator);
        return outDatedBooks;
    }

    @Override
    public Collection<Book> getAllBooks(){
        return storageDao.readAllBooks();
    }

    @Override
    public Collection<Book> getAllBooks(BookComparator.By by) {
        return storageDao.readAllBooks(by);
    }

    @Override
    public String getBookDescription(int id){
        String description = "";
        try {
            description = storageDao.getBook(id).getDescription();
        }
        catch (IdException ex){
            Logger.log("Ошибка получения описания книги. " + ex.getMessage() + ex.getId());
        }
        return description;
    }
}
