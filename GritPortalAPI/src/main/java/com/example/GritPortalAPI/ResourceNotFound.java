package com.example.GritPortalAPI;

public class ResourceNotFound extends RuntimeException{

    public ResourceNotFound (String message) {
        super(message);
    }

}
