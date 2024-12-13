package org.example;

public class Counter {
    public int count;
    private static final int LIMIT=10;

    public int count(int num){
        if (num<0) {
            return count;
        }
        count += num;
        count%=LIMIT;
        return count;
    }



}
