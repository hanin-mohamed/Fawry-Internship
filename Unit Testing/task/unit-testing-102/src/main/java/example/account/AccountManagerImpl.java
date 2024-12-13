package example.account;

public class AccountManagerImpl implements AccountManager {
    private static final int MAX_CREDIT = 1000;
    @Override
    public String deposit(Customer customer, int amount) {
        if (amount<0){
            return "Negative amount Not Allowed";
        }
        if (amount==0)
            return "Zero amount Not Allowed";
        customer.setBalance(customer.getBalance() + amount);
        return "success";
    }

    @Override
    public String withdraw(Customer customer, int amount) {
        if (amount<0){
            return "Negative amount Not Allowed";
        }
        if (amount==0)
            return "Zero amount Not Allowed";

        int expectedBalance = customer.getBalance() - amount;
        if (expectedBalance < 0 && !customer.isVip()) {
            if (!customer.isCreditAllowed()) {
                return "insufficient account balance";
            }
            else if (expectedBalance < -MAX_CREDIT) {
                return "maximum credit exceeded";
            }
        }

        customer.setBalance(expectedBalance);
        return "success";
    }
}
