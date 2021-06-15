package com.company.cli.items.impl.storageControl;

import com.company.cli.actions.impl.PrintOutdatedBooksAction;
import com.company.cli.items.MenuItem;
import com.company.cli.menu.Menu;
import com.company.comparators.BookComparator;
import com.company.facade.Facade;

import java.util.InputMismatchException;
import java.util.Scanner;

public class PrintOutdatedBooksSortedItem implements MenuItem {

    final String title = "Показать все залежавшиеся книги (сортированные)";

    PrintOutdatedBooksAction action;

    public PrintOutdatedBooksSortedItem (Facade facade){
        action = new PrintOutdatedBooksAction(facade,null);
    }


    @Override
    public void doAction() {
        Scanner in  = new Scanner(System.in);
        System.out.println("1) По алфавиту.\n" +
                "2) По дате поступления.\n" +
                "3) По цене.\n" +
                "4) По наличию.\n" +
                "5) По запросам.");
        try {
            switch (in.nextInt()){
                case 1:
                    action.setSort(BookComparator.By.ALPHABET);
                    break;
                case 2:
                    action.setSort(BookComparator.By.PUBLISH_DATE);
                    break;
                case 3:
                    action.setSort(BookComparator.By.PRICE);
                    break;
                case 4:
                    action.setSort(BookComparator.By.STORAGE_STATUS);
                    break;
                case 5:
                    action.setSort(BookComparator.By.REQUESTS);
                    break;
                default:
                    System.out.println("Некорректный ввод.");
                    break;
            }
        }
        catch (InputMismatchException exception){
            System.out.println("Некорректный ввод.");
            return;
        }

        action.execute();
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public Menu getNextMenu() {
        return null;
    }

}
