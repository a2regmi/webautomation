package Utils.AccountManagement;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/** Account pool allows management of multiple accounts to be used concurrently. */
public class AccountPool {

    private static AccountPool accountPool;
    private static HashMap<Domain, ArrayList> accountList;
    private Random random;
    public static final String ACCOUNT_XML = "C:\\slumberbolt\\Accounts.xml";

    private AccountPool(){
        accountList = new HashMap<>();
        random = new Random();
        try {
            XStream xStream = new XStream(new StaxDriver());
            xStream.alias("account-map", Map.class);
            xStream.alias("accounts", List.class);
            xStream.alias("account", Account.class);
            xStream.alias("domain", Domain.class);
            xStream.alias("domains", Map.Entry.class);
            String xml = new String(Files.readAllBytes(Paths.get(ACCOUNT_XML)));
            accountList = (HashMap<Domain, ArrayList>)xStream.fromXML(xml);
        } catch (IOException e) {
            System.err.print(e.getMessage());
            System.exit(-1);
        }
    }

    public static AccountPool getAccountPool() {
        if (accountPool == null) accountPool = new AccountPool();
        return accountPool;
    }

    public Account getAccount(Domain domain){
        ArrayList accounts = accountList.get(domain);
        return (Account)accounts.remove(random.nextInt(accounts.size()-1));
    }

    public void returnAccount(Domain domain, Account account){
        accountList.get(domain).add(account);
    }

    public boolean containsAccount(Domain domain, Account account){
        return accountList.get(domain).contains(account);
    }

}
