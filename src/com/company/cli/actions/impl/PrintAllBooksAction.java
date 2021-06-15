package com.company.cli.actions.impl;

import com.company.cli.actions.Action;
import com.company.comparators.BookComparator;
import com.company.facade.Facade;
import com.company.models.Book;

import java.util.Collection;

public class PrintAllBooksAction implements Action {
    Facade facade;
    BookComparator.By by;

    public PrintAllBooksAction(Facade facade, BookComparator.By by){
        this.facade = facade;
        this.by = by;
    }

    public void setSort(BookComparator.By by){
        this.by = by;
    }

    @Override
    public void execute(){
        Collection<Book> books;
        if (by == null) {
            books = facade.getAllBooks();
        }
        else {
            books = facade.getAllBooks(by);
        }

        for (Book book: books){
            System.out.println("Название: " + book.getName());
            System.out.println("Дата поступления: " + book.getPublishDate().toString());
            System.out.println("ID: " + book.getId());
            System.out.println("Описание: " + book.getDescription());
            System.out.println("Цена: " + book.getPrice());
            switch (book.getStorageStatus()){
                case ABSENT:
                    System.out.println("Статус: Отсутствует.");
                    System.out.println("Запросов: " + book.getRequests());
                    break;
                case IN_STOCK:
                    System.out.println("Статус: На складе.");
                    break;
            }
        }
    }
}
