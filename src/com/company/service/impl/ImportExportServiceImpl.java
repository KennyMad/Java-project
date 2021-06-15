package com.company.service.impl;

import com.company.exceptions.IdException;
import com.company.exceptions.WrongPathException;
import com.company.exceptions.WrongTypeException;
import com.company.logger.Logger;
import com.company.models.Book;
import com.company.models.Order;
import com.company.models.OrderStatus;
import com.company.models.StorageStatus;
import com.company.repository.OrderDao;
import com.company.repository.StorageDao;
import com.company.service.ImportExportService;
import com.company.service.StorageService;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class ImportExportServiceImpl implements ImportExportService {

    OrderDao orderDao;

    StorageDao storageDao;

    final int bookItemsCount = 8;

    final int orderItemsCount = 6;

    public ImportExportServiceImpl(OrderDao orderDao, StorageDao storageDao){
        this.orderDao = orderDao;
        this.storageDao = storageDao;
    }

    @Override
    public void importBooks(String path) throws WrongTypeException, WrongPathException {
        File booksFile = new File(path);
        if (!booksFile.exists()){
            throw new WrongPathException(path);
        }

        int stringNmb = 1;
        try {
            FileReader fileReader = new FileReader(booksFile);

            BufferedReader reader = new BufferedReader(fileReader);

            String line = reader.readLine();
            while (line != null){
                String[] data = line.split(";");

                if (data.length != bookItemsCount)
                    throw new WrongTypeException(stringNmb, "Book");

                Book book = new Book();
                book.setId(Integer.parseInt(data[0]));
                book.setName(data[1]);
                book.setPrice(Double.parseDouble(data[2]));
                book.setPublishDate(DateFormat.getDateInstance().parse(data[3]));
                book.setDescription(data[4]);
                switch (data[5]){
                    case "IN_STOCK":
                        book.setStorageStatus(StorageStatus.IN_STOCK);
                        break;
                    case "ABSENT":
                        book.setStorageStatus(StorageStatus.ABSENT);
                        break;
                    default:
                        throw new WrongTypeException(stringNmb, "Book");
                }
                book.setRequests(Integer.parseInt(data[6]));
                storageDao.importBook(book,DateFormat.getDateInstance().parse(data[7]));

                line = reader.readLine();
                stringNmb++;
            }
        }
        catch (IOException exception){
            Logger.log(exception.getLocalizedMessage());
        }
        catch (NumberFormatException numberFormatException){
            throw new WrongTypeException(stringNmb, "Book");
        }
        catch (ParseException parseException){
            throw new WrongTypeException(stringNmb, "Book");
        }
    }

    @Override
    public void exportBooks() {
        File exportFile = new File(getExportDestination() + File.separator + "books.csv");

        try {
            FileWriter fileWriter = new FileWriter(exportFile);

            for (Book book: storageDao.readAllBooks()){
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

                fileWriter.append(Integer.toString(book.getId())).append(";");
                fileWriter.append(book.getName()).append(";");
                fileWriter.append(Double.toString(book.getPrice())).append(";");
                fileWriter.append(dateFormat.format(book.getPublishDate())).append(";");
                fileWriter.append(book.getDescription()).append(";");

                switch (book.getStorageStatus()){
                    case IN_STOCK:
                        fileWriter.append("IN_STOCK;");
                        break;
                    case ABSENT:
                        fileWriter.append("ABSENT;");
                        break;
                }

                fileWriter.append(Integer.toString(book.getRequests())).append(";");
                fileWriter.append(dateFormat.format(storageDao.getLastBookPurchase(book.getId()))).append("\n");
            }
            fileWriter.close();
        }
        catch (IOException ioException){
            Logger.log(ioException.getLocalizedMessage());
        }
        catch (IdException idException){
            Logger.log(idException.getMessage() + idException.getId());
        }
    }

    @Override
    public void importOrders(String path) throws WrongTypeException, WrongPathException{
        File ordersFile = new File(path);
        if (!ordersFile.exists()){
            throw new WrongPathException(path);
        }

        int stringNmb = 1;
        try {
            FileReader fileReader = new FileReader(ordersFile);

            BufferedReader reader = new BufferedReader(fileReader);

            String line = reader.readLine();
            while (line != null){
                String[] data = line.split(";");

                if (data.length != orderItemsCount)
                    throw new WrongTypeException(stringNmb, "Order");

                Order order = new Order();
                order.setId(Integer.parseInt(data[0]));

                List<Integer> bookIds = new ArrayList<>();
                for (String id: data[1].split(",")){
                    bookIds.add(Integer.parseInt(id));
                }
                order.setBookIds(bookIds);

                switch (data[2]){
                    case "NEW":
                        order.setOrderStatus(OrderStatus.NEW);
                        break;
                    case "COMPLETED":
                        order.setOrderStatus(OrderStatus.COMPLETED);
                        break;
                    case "CANCELED":
                        order.setOrderStatus(OrderStatus.CANCELED);
                        break;
                    default:
                        throw new WrongTypeException(stringNmb, "Order");
                }

                order.setOrderDate(DateFormat.getDateInstance().parse(data[3]));
                order.setPrice(Double.parseDouble(data[4]));

                List<String> customerData = new ArrayList<>(Arrays.asList(data[5].split(",")));
                order.setCustomerData(customerData);

                line = reader.readLine();
                stringNmb++;
            }
        }
        catch (IOException exception){
            Logger.log(exception.getLocalizedMessage());
        }
        catch (NumberFormatException numberFormatException){
            throw new WrongTypeException(stringNmb, "Order");
        }
        catch (ParseException parseException){
            throw new WrongTypeException(stringNmb, "Book");
        }
    }

    @Override
    public void exportOrders() {
        File exportFile = new File(getExportDestination() + File.separator + "orders.csv");

        try {
            FileWriter fileWriter = new FileWriter(exportFile);

            for (Order order: orderDao.readAll()){
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

                fileWriter.append(Integer.toString(order.getId())).append(";");
                for (Integer id: order.getBookIds()){
                    fileWriter.append(id.toString());
                    if (order.getBookIds().indexOf(id) == (order.getBookIds().size() - 1)){
                        fileWriter.append(";");
                    }
                    else
                        fileWriter.append(",");
                }
                switch (order.getOrderStatus()){
                    case NEW:
                        fileWriter.append("NEW;");
                        break;
                    case CANCELED:
                        fileWriter.append("CANCELED;");
                        break;
                    case COMPLETED:
                        fileWriter.append("COMPLETED;");
                        break;
                }
                fileWriter.append(dateFormat.format(order.getOrderDate())).append(";");
                fileWriter.append(Double.toString(order.getPrice()));
                for (String customerData: order.getCustomerData()){
                    fileWriter.append(customerData);
                    if (order.getCustomerData().indexOf(customerData) == (order.getCustomerData().size() - 1))
                        fileWriter.append("\n");
                    else
                        fileWriter.append(",");
                }
            }

            fileWriter.close();
        }
        catch (IOException ioException){
            Logger.log(ioException.getLocalizedMessage());
        }
    }

    @Override
    public String getExportDestination() {
        return System.getProperty("user.dir");
    }
}
