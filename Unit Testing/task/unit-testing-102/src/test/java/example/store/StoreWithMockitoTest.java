package example.store;


import example.account.AccountManager;
import example.account.Customer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class StoreWithMockitoTest {
    @Test
    void test(){

        //Arrange
        Product product=new Product();
        product.setQuantity(5);
        product.setPrice(200);
        Customer customer=new Customer();
        AccountManager accountManager= Mockito.mock(AccountManager.class);
        Mockito.when(accountManager.withdraw(customer,product.getPrice()))
                .thenReturn("success");
        Store store =new StoreImpl(accountManager);
        // Act
        store.buy(product,customer);

        // Assert
        Assertions.assertEquals(4,product.getQuantity());

    }


    @Test
    void buySuccess_thenReduceQuantity(){
        // Arrange
        Product product = new Product();
        product.setQuantity(5);
        product.setPrice(200);
        Customer customer = new Customer();
        AccountManager accountManager = Mockito.mock(AccountManager.class);
        Mockito.when(accountManager.withdraw(customer, product.getPrice()))
                .thenReturn("success");
        Store store = new StoreImpl(accountManager);
        // Act

        store.buy(product, customer);

        // Assert
        Assertions.assertEquals(4, product.getQuantity());
    }

    @Test
    void buyOutOfStock_thenFailure(){
        // Arrange
        Product product = new Product();
        product.setQuantity(0);
        product.setPrice(80);
        Customer customer = new Customer();
        AccountManager accountManager = Mockito.mock(AccountManager.class);
        Store store = new StoreImpl(accountManager);
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () ->
                store.buy(product, customer)
        );

        Assertions.assertEquals("Product out of stock", exception.getMessage());
        Mockito.verifyZeroInteractions(accountManager);
    }

    @Test
    void buyPaymentFailure_thenFailure() {
        // Arrange
        Product product = new Product();
        product.setQuantity(1);
        product.setPrice(590);

        Customer customer = new Customer();
        AccountManager accountManager = Mockito.mock(AccountManager.class);

        // Mock withdraw to fail
        Mockito.when(accountManager.withdraw(customer, product.getPrice()))
                .thenReturn("failure");

        Store store = new StoreImpl(accountManager);

        // Act & Assert
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            store.buy(product, customer);
        });

        Assertions.assertEquals("Payment failure: failure", exception.getMessage());

        // Quantity unchanged
        Assertions.assertEquals(1, product.getQuantity());

    }


}
