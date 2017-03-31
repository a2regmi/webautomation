package Utils.AccountManagement;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static Utils.AccountManagement.AccountPool.ACCOUNT_XML;

/** Generates dummy Accounts.xml file. File is output to path specified by ACCOUNT_XML from AccountPool.java */
public class GenerateAccounts {

    public static void main(String[] args) {
        HashMap<Domain, ArrayList> accountList;
        accountList = new HashMap<>();

        ArrayList<Account> google = new ArrayList<>();
        ArrayList<Account> facebook = new ArrayList<>();

        for (int i=0; i<50; i++)
            google.add(new Account("user" + i, "password" + i));
        for (int i=0; i<50; i++)
            facebook.add(new Account("user" + i, "password" + i));

        accountList.put(Domain.GOOGLE, google);
        accountList.put(Domain.FACEBOOK, facebook);


        XStream xStream = new XStream(new StaxDriver());
        xStream.alias("account-map", Map.class);
        xStream.alias("accounts", List.class);
        xStream.alias("account", Account.class);
        xStream.alias("domain", Domain.class);
        xStream.alias("domains", Map.Entry.class);

        try {
            Files.write(Paths.get(ACCOUNT_XML), xStream.toXML(accountList).getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
