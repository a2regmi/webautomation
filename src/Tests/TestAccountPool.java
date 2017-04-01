package Tests;

import Utils.AccountManagement.Account;
import Utils.AccountManagement.AccountPool;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;

@Tag("AccountPool")
@DisplayName("Verify account pool works in single-threaded environment")
public class TestAccountPool {

    @Test
    public void verifyRemoval(){
        AccountPool accountPool = AccountPool.getAccountPool();
        Account account = accountPool.getAccount(); // Remove account from pool
        Assert.assertFalse(accountPool.containsAccount(account));
        Assert.assertNotNull(account.getUsername());
        Assert.assertNotNull(account.getPassword()); // Verify there is some value stored in them, we don't really care what it is
        accountPool.returnAccount(account); // Return to pool
        Assert.assertTrue(accountPool.containsAccount(account));
    }
}
