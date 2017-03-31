package Utils.AccountManagement;

/** Simple account type that contains username and password. More fields can be added. */
public class Account {

    private String username, password;
    private Domain domain;

    public Account(String username, String password, Domain domain) {
        this.username = username;
        this.password = password;
        this.domain = domain;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Domain getDomain() {
        return domain;
    }
}
