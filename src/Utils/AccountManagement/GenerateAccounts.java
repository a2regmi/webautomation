package Utils.AccountManagement;

import Utils.ResourcePool;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.ConcurrentLinkedQueue;

import static Utils.AccountManagement.AccountPool.ACCOUNT_XML;

/** Generates dummy Accounts.xml file. File is output to path specified by ACCOUNT_XML from AccountPool.java */
public class GenerateAccounts {

    public static void main(String[] args) {
        ResourcePool<Account> pool = new ResourcePool<>(0); // init pool with no accounts

        // Add 50 accounts
        for(int i=0; i<50; i++)
            pool.returnResource(new Account("user" + i, "password" + i));

        // Serialize pool
        try {
            XStream xStream = new XStream(new StaxDriver());
            xStream.alias("account", Account.class);
            xStream.alias("accounts", ConcurrentLinkedQueue.class);
            xStream.alias("account-list", ResourcePool.class);
            Files.write(Paths.get(ACCOUNT_XML), xStream.toXML(pool).getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
