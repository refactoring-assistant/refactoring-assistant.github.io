
interface Connector {
    public boolean checkValidUrl();

    public String getDomain();
}

class HTTPConnector implements Connector {

    private String url;

    public HTTPConnector(String url) {
        this.url = url;
    }

    @Override
    public boolean checkValidUrl() {
        if(this.url.startsWith("http")) {
            System.out.println("Valid HTTP url");
            return true;
        }
        return false;
    }

    @Override
    public String getDomain() {
        String domain = this.url.substring(7, this.url.indexOf("/", 8));
        return domain;
    }

}

class FTPConnector implements Connector {

    private String url;

    public FTPConnector(String url) {
        this.url = url;
    }

    @Override
    public boolean checkValidUrl() {
        if (this.url.startsWith("ftp")) {
            System.out.println("Valid FTP url");
            return true;
        }
        return false;
    }

    @Override
    public String getDomain() {
        String domain = this.url.substring(this.url.indexOf("@")+1, this.url.lastIndexOf(":"));
        return domain;
    }

}


public class DVCHGE1 {
    public static void main(String[] args) {
        Connector serebii = new HTTPConnector("http://serebii.net/filepath");
        Connector ubuntu = new FTPConnector("ftp://yash:1234@ubuntumachine.com:22/filepath");
        serebii.checkValidUrl();
        System.out.println(serebii.getDomain());
        ubuntu.checkValidUrl();
        System.out.println(ubuntu.getDomain());
    }
}