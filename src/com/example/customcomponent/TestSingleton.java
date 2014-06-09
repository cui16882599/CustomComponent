package com.example.customcomponent;

public class TestSingleton {

    private static TestSingleton mInstance = new TestSingleton();
    
    public static TestSingleton getInstance(){
        return mInstance;
    }
    
}
