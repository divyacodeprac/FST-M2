package Activities;

import org.example.BankAccount;
import org.example.NotEnoughFundsException;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Activity2 {

    @Test
    public void notEnoughFunds(){
        BankAccount bankAccount = new BankAccount(10);
        assertThrows(NotEnoughFundsException.class,()->bankAccount.withdraw(10),"Balance must be greater than amount of withdrawal");
    }

    @Test
    public void enoughFunds(){
        BankAccount bankAccount = new BankAccount(100);
        assertDoesNotThrow(()->bankAccount.withdraw(100));
    }


}
