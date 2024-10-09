package org.test.awsdb.SQL;

public class DataSourceProperties {
    private final String host;
    private final String port;
    private final String db;
    private final String username;
    private final String password;

    public DataSourceProperties () {
        this.host = System.getenv("RDS_HOSTNAME");
        this.port = System.getenv("RDS_PORT");
        this.db = System.getenv("RDS_DBNAME");
        this.username = System.getenv("RDS_USERNAME");
        this.password = System.getenv("RDS_PASSWORD");
    }

    public DataSourceProperties (String host, String port, String db, String username, String password) {
        this.host = host;
        this.port = port;
        this.db = db;
        this.username = username;
        this.password = password;
    }

    public String getHost() {
        return host;
    }

    public String getJDBCURL () {
        return "jdbc:postgresql://" + this.getHost() + ":" + this.getPort() + "/" + this.getDb();
    }

    public String getPort() {
        return port;
    }

    public String getDb() {
        return db;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
