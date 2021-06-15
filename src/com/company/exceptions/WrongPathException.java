package com.company.exceptions;

public class WrongPathException extends Exception{

    String path;

    public WrongPathException(String path){
        super("Неправильный путь к файлу: " + path);
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
