package Utils.AccountManagement;

/** Used to manage accounts through domains */
public enum Domain {
    GOOGLE("www.google.com"),
    FACEBOOK("www.facebook.com");

    final String URL;

    Domain(String URL){
        this.URL = URL;
    }
}
