package example.store;


import example.account.AccountManager;
import example.account.Customer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class StoreWithMokitoTest {
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

}
