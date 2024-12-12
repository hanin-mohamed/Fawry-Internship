package org.example;

public class TestRunner {
    int succeeded,failed;
    public void execute(String title, Runnable test){
        try {
            // TEST CASE
            test.run();
            succeeded++;
        }
        catch (RuntimeException e){
            System.out.println("Test " +title+ " Failed!");
            failed++;
        }
    }
    public void finish(){
         System.out.println("Unit Testing Finished! with, "+succeeded+" Testes Succeeded & "+failed+"  Failed ");
    }
}
