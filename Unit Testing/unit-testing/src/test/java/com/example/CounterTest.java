package com.example;

import org.example.Counter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CounterTest {


    public CounterTest(){
        System.out.println("Counter created");
    }

    Counter counter=  new Counter();
    @Test
    void initialValue() {

        int result = counter.count(5);
        if (result!=5)
            throw new RuntimeException("result should be 5!");
    }

    @Test
    void incrementWithinLimit() {
        // Arrange
        counter.count(5); // initial value

        // Act
        int result = counter.count(2);

        // Assert
        Assertions.assertEquals(7,result);
    }

    @Test
    void incrementOverLimit() {
        // Arrange
        counter.count(5);
        // Act
        int actualResult = counter.count(7);
        // Assert
        if (actualResult != 2)
            throw new RuntimeException("Test2 exceed limit - result should be 2!");
    }
}
