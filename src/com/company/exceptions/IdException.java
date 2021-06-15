package com.company.exceptions;

public class IdException extends  Exception{

    int id;
    public int getId (){
        return id;
    }
    public IdException( int id){
        super("Несуществующий id ");
        this.id = id;
    }

}
