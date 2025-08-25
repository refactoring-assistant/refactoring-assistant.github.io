enum ConnectionState {
    CONNECTED,
    FAILURE,
    NOTCONNECTED
}

class MongoDBConnectionVariables {
    private int port;
    private String host;
    private String username;
    private String password;

    public MongoDBConnectionVariables(int port, String host, String username, String password) {
        this.port = port;
        this.host = host;
        this.username = username;
        this.password = password;
    }

    public String getConnectionString() {
        return host + ":" + port + "/" + username + "&" + password;
    }

    public void isCredentialsValid() throws IllegalArgumentException {
        if (this.port < 0 || this.port > 65535) {
            throw new IllegalArgumentException("Port is invalid");
        }

        if (!this.host.startsWith("http")) {
            throw new IllegalArgumentException("Host is invalid");
        }
    }

    public void validateUsernamePassword() {
        System.out.println("Given username: " + this.username + " and password: " + this.password);
    }
}

class MongoDBConnector {
    private ConnectionState state;

    public MongoDBConnector() {
        this.state = ConnectionState.NOTCONNECTED;
    }

    public boolean testConnection(MongoDBConnectionVariables mcv) {
        if (Math.random() > 0.1) {
            System.out.println("Connection to: " + mcv.getConnectionString() + " successful");
            return true;
        } else {
            System.out.println("Connection to: " + mcv.getConnectionString() + " failed");
            return false;
        }
    }

    public void connectToDatabase(MongoDBConnectionVariables mcv)
            throws IllegalArgumentException {

        System.out.println("Testing connection...");

        if (!testConnection(mcv)) {
            this.state = ConnectionState.FAILURE;
            throw new IllegalArgumentException("Failed trying to connect to database: " + mcv.getConnectionString());
        }

        System.out.println("Connection to database estabilished: " + mcv.getConnectionString());
        this.state = ConnectionState.CONNECTED;
    }

    public String getCurrentState() {
        return "State: " + this.state;
    }
}

public class DCGE1 {
    public static void main(String[] args) {
        MongoDBConnector mongodb = new MongoDBConnector();
        MongoDBConnectionVariables mcv = new MongoDBConnectionVariables(27017, "http://localhost", "yash", "fn023uc");
        mongodb.connectToDatabase(mcv);
    }
}
