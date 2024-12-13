package example.account;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AccountManagerTest {
    AccountManager accountManager=new AccountManagerImpl();


    @Test
    void  depositNegativeAmount_thenFailure() {
        // Arrange
        Customer customer=new Customer();
        customer.setBalance(0);

        // Act
        String result=accountManager.deposit(customer,-200);

        // Assert
        Assertions.assertEquals("Negative amount Not Allowed",result);
        Assertions.assertEquals(0,customer.getBalance());
    }

    @Test
    void depositZeroAmount_thenFailure() {

        // Arrange
        Customer customer=new Customer();
        customer.setBalance(500);

        // Act
        String result=accountManager.deposit(customer,0);

        // Assert
        Assertions.assertEquals("Zero amount Not Allowed",result);
        Assertions.assertEquals(500,customer.getBalance());
    }

    @Test
    void  withdrawNegativeAmount_thenFailure() {
        // Arrange
        Customer customer=new Customer();
        customer.setBalance(100);

        // Act
        String result=accountManager.withdraw(customer,-100);

        // Assert
        Assertions.assertEquals("Negative amount Not Allowed",result);
        Assertions.assertEquals(100,customer.getBalance());
    }

    @Test
    void withdrawZeroAmount_thenFailure() {

        // Arrange
        Customer customer=new Customer();
        customer.setBalance(100);

        // Act
        String result=accountManager.withdraw(customer,0);

        // Assert
        Assertions.assertEquals("Zero amount Not Allowed",result);
        Assertions.assertEquals(100,customer.getBalance());
    }


    @Test
    void givenCustomerWithEnoughBalance_whenWithdraw_thenSuccess() {
        // Arrange
        Customer customer = new Customer();
        customer.setBalance(100);

        // Act
        String result = accountManager.withdraw(customer,30);

        // Assert
        Assertions.assertEquals("success",result);
        Assertions.assertEquals(70,customer.getBalance());
    }

    @Test
    void givenNonVIPCustomerWithEnoughBalance_whenWithdraw_thenSuccess() {
        // Arrange
        Customer customer = new Customer();
        customer.setBalance(200);
        customer.setVip(false);
        // Act
        String result = accountManager.withdraw(customer,150);

        // Assert
        Assertions.assertEquals("success",result);
        Assertions.assertEquals(50,customer.getBalance());
        }

    @Test
    void givenNonVIPCustomerWithNotEnoughBalanceAndCreditNotAllowed_whenWithdraw_thenFailure() {

        // Arrange
        Customer customer=new Customer();
        customer.setBalance(100);
        customer.setCreditAllowed(false);
        customer.setVip(false);

        // Act
        String result=accountManager.withdraw(customer,200);

        // Assert
        Assertions.assertEquals("insufficient account balance",result);
        Assertions.assertEquals(100,customer.getBalance());
    }

    @Test
    void givenNonVIPCustomerWithNotEnoughBalanceWithinCreditLimit_whenWithdraw_thenSuccess() {
        // Arrange
        Customer customer=new Customer();
        customer.setBalance(100);
        customer.setCreditAllowed(true);
        customer.setVip(false);

        // Act
        String result=accountManager.withdraw(customer,900);

        // Assert
        Assertions.assertEquals("success",result);
        Assertions.assertEquals(-800,customer.getBalance());
    }

    @Test
    void givenNonVIPCustomerWithNotEnoughBalanceExceedingCreditLimit_whenWithdraw_thenFailure() {
        // Arrange
        Customer customer=new Customer();
        customer.setBalance(100);
        customer.setCreditAllowed(true);
        customer.setVip(false);

        // Act
        String result=accountManager.withdraw(customer,1900);

        // Assert
        Assertions.assertEquals("maximum credit exceeded",result);
        Assertions.assertEquals(100,customer.getBalance());
    }

    @Test
    void givenVIPCustomerWithNotEnoughBalance_whenWithdraw_thenSuccess() {
        // Arrange
        Customer customer=new Customer();
        customer.setBalance(100);
        customer.setVip(true);
//        customer.setCreditAllowed(true);

        // Act
        String result=accountManager.withdraw(customer,1000);

        // Assert
        Assertions.assertEquals("success",result);
        Assertions.assertEquals(-900,customer.getBalance());
    }

    @Test
    void givenVIPCustomerWithNegativeBalance_whenWithdraw_thenSuccess() {
        // Arrange
        Customer customer=new Customer();
        customer.setBalance(-1000);
        customer.setVip(true);
//        customer.setCreditAllowed(true);

        // Act
        String result=accountManager.withdraw(customer,1000);

        // Assert
        Assertions.assertEquals("success",result);
        Assertions.assertEquals(-2000,customer.getBalance());
    }


}
