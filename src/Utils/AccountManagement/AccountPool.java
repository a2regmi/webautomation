package Utils.AccountManagement;


import Utils.ResourcePool;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.NoSuchElementException;
import java.util.concurrent.ConcurrentLinkedQueue;

/** GoogleAccount pool allows management of multiple accounts to be used concurrently. */
public class AccountPool {

    private static final AccountPool accountPool = new AccountPool();
    private static ResourcePool<Account> pool;
    public static final String ACCOUNT_XML = "C:\\slumberbolt\\Accounts.xml";
    private final long WAIT_TIME = 3; // time to wait for an account, in seconds


    private AccountPool(){
        pool = new ResourcePool<>(0); // Maximum number of accounts will be automatically set when deserialized
        try {
            XStream xStream = new XStream(new StaxDriver());
            xStream.alias("account", Account.class);
            xStream.alias("accounts", ConcurrentLinkedQueue.class);
            xStream.alias("account-list", ResourcePool.class);
            String xml = new String(Files.readAllBytes(Paths.get(ACCOUNT_XML)));
            pool = (ResourcePool<Account>)xStream.fromXML(xml);
        } catch (IOException e) {
            System.err.print(e.getMessage());
            System.exit(-1);
        }
    }

    public static AccountPool getAccountPool() {
        return accountPool;
    }

    public void returnAccount(Account account){
        pool.returnResource(account);
    }

    //todo: handle timeout while waiting for account; probably just create a new account
    public Account getAccount(){
        Account account = null;
        try {
            account = pool.getResource(WAIT_TIME);
        } catch (NoSuchElementException e) {
            System.out.println("Could not get account in time");
            System.exit(-1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return account;
    }

    public boolean containsAccount(Account account){
        return pool.contains(account);
    }

}
