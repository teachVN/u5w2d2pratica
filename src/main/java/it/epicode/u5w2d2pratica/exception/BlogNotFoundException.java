package it.epicode.u5w2d2pratica.exception;

public class BlogNotFoundException extends RuntimeException{

    public BlogNotFoundException(String message){
        super(message);
    }
}
