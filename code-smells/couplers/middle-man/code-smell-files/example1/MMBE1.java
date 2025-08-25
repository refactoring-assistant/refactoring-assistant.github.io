import java.util.HashMap;
import java.util.Map;

class Product {
    private String name;
    private String type;
    private int stock;

    public Product(String name, String type, int stock) {
        this.name = name;
        this.type = type;
        this.stock = stock;
    }

    public int getStock() {
        return this.stock;
    }

    public void reduceStock() {
        this.stock -= 1;
    }

    public void showDetails(int id) {
        System.out.println("Name: " + this.name);
        System.out.println("Type: " + this.type);
        System.out.println("Stock: " + this.stock);
    }
}

class ProductManager {
    Map<Integer, ProductHandler> products;

    public ProductManager() {
        this.products = new HashMap<>();
    }

    public void addProduct(int id, ProductHandler product) {
        products.put(id, product);
    }

    public void removeProduct(int id) {
        products.remove(id);
    }

    public void showTotalStock() {
        int totalStock = 0;
        for (Map.Entry<Integer, ProductHandler> product : products.entrySet()) {
            totalStock += product.getValue().getStock();
        }
        System.out.println("Total Stock of all Products: " + totalStock);
    }

}

class ProductHandler {
    private Product product;
    private String name;
    private int id;

    public ProductHandler(Product product, String name, int id) {
        this.product = product;
        this.name = name;
        this.id = id;
    }

    public int getStock() {
        return product.getStock();
    }

    public void printDetails() {
        System.out.println("Name: " + this.name + "\nId: " + this.id);
    }
    

}

public class MMBE1 {

    public static void main(String[] args) {
        Product redsoxcap = new Product("Baseball Cap", "Hat", 10);
        ProductHandler redsoxcapHandler = new ProductHandler(redsoxcap, "CapHandler", 123);
        Product stanley = new Product("Stanley Cup", "Mug", 15);
        ProductHandler stanleyHandler = new ProductHandler(stanley, "MugHandler", 234);
        ProductManager pm = new ProductManager();
        pm.addProduct(1, redsoxcapHandler);
        pm.addProduct(2, stanleyHandler);
        pm.showTotalStock();
        redsoxcap.reduceStock();
        pm.showTotalStock();
    }

}
