package org.example.endtermproject.patterns;

public class DbConfigSingleton {
    private static volatile DbConfigSingleton instance;

    private final String url;
    private final String user;
    private final String password;

    private DbConfigSingleton(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    public static DbConfigSingleton initOnce(String url, String user, String password) {
        if (instance == null) {
            synchronized (DbConfigSingleton.class) {
                if (instance == null) instance = new DbConfigSingleton(url, user, password);
            }
        }
        return instance;
    }

    public static DbConfigSingleton getInstance() {
        if (instance == null) throw new IllegalStateException("DbConfigSingleton not initialized");
        return instance;
    }

    public String url() { return url; }
    public String user() { return user; }
    public String password() { return password; }
}
