package com.company.exceptions;

public class WrongTypeException extends Exception{

    int stringNumber;
    String type;

    public String getType() {
        return type;
    }

    public int getStringNumber() {
        return stringNumber;
    }

    public WrongTypeException(int stringNumber, String type){
        super("Неправильный тип " + type + " в строке " + stringNumber);
        this.stringNumber = stringNumber;
        this.type = type;
    }
}
