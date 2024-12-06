package com.fawry;

@FunctionalInterface  //Optional for announcing that this is a functional interface(one abstract method)
public interface ExampleInterface {
    void exampleMethod(String msg);

    default void defautMethod(String msg){
        System.out.println("msg");
    }

    static void staticMethod(String msg) {
        System.out.println(msg);
    }
}
