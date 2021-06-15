package com.company.comparators;

import com.company.models.Book;

import java.util.Comparator;

public class BookComparator implements Comparator<Book> {

    public enum By{
        ALPHABET,
        PUBLISH_DATE,
        PRICE,
        STORAGE_STATUS,
        REQUESTS
    }

    By by;

    public BookComparator(){
        this.by = By.ALPHABET;
    }

    public BookComparator(By by){
        this.by = by;
    }

    @Override
    public int compare(Book o1, Book o2) {
        switch (this.by){
            case ALPHABET:
                return o1.getName().compareTo(o2.getName());
            case PRICE:
                if (o1.getPrice() > o2.getPrice())
                    return 1;
                else if (o1.getPrice() < o2.getPrice())
                    return -1;
                else
                    return 0;
            case PUBLISH_DATE:
                return o1.getPublishDate().compareTo(o2.getPublishDate());
            case STORAGE_STATUS:
                return o1.getStorageStatus().compareTo(o2.getStorageStatus());
            case REQUESTS:
                if (o1.getRequests() > o2.getRequests())
                    return 1;
                else if (o1.getRequests() < o2.getRequests())
                    return -1;
                else
                    return 0;
        }
        return 0;
    }
}
