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
    Map<Integer, Product> products;

    public ProductManager() {
        this.products = new HashMap<>();
    }

    public void addProduct(int id, Product product) {
        products.put(id, product);
    }

    public void removeProduct(int id) {
        products.remove(id);
    }

    public void showTotalStock() {
        int totalStock = 0;
        for (Map.Entry<Integer, Product> product : products.entrySet()) {
            totalStock += product.getValue().getStock();
        }
        System.out.println("Total Stock of all Products: " + totalStock);
    }

}

public class MMGE1 {

    public static void main(String[] args) {
        Product redsoxcap = new Product("Baseball Cap", "Hat", 10);
        Product stanley = new Product("Stanley Cup", "Mug", 15);
        ProductManager pm = new ProductManager();
        pm.addProduct(1, redsoxcap);
        pm.addProduct(2, stanley);
        pm.showTotalStock();
        redsoxcap.reduceStock();
        pm.showTotalStock();
    }

}
