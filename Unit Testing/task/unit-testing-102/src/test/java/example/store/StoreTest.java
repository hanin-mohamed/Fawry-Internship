package example.store;

import example.account.AccountManager;
import example.account.Customer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StoreTest {


    @Test
    void test(){

        //Arrange
        Product product=new Product();
        product.setQuantity(5);
        Customer customer=new Customer();
        AccountManager accountManager= new AlwaysSuccessAccountManager();
        Store store =new StoreImpl(accountManager);

        // Act
        store.buy(product,customer);

        // Assert
        Assertions.assertEquals(4,product.getQuantity());

    }

    static class AlwaysSuccessAccountManager implements AccountManager {
        @Override
        public String deposit(Customer customer, int amount) {return "success"; }
        @Override
        public String withdraw(Customer customer, int amount) {
            return "success";
        }
    }
}