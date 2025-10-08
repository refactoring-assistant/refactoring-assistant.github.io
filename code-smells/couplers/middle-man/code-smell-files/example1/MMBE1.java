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

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getStock() {
        return this.stock;
    }

    public void reduceStock() {
        this.stock -= 1;
    }

}

class ProductHandler {
    private Product product;
    private int id;

    public ProductHandler(Product product, int id) {
        this.product = product;
        this.id = id;
    }

    public int getStock() {
        return this.product.getStock();
    }

    public String getProductName() {
        return this.product.getName();
    }

    public String getProductType() {
        return this.product.getType();
    }

    public void reduceStock() {
        this.product.reduceStock();
    }

    public void showDetails() {
        System.out.println("Product Name: " + this.product.getName());
        System.out.println("Product Type: " + this.product.getType());
        System.out.println("Stock: " + this.product.getStock());
    }

}

class ProductManager {
    Map<Integer, ProductHandler> productHandlers;

    public ProductManager() {
        this.productHandlers = new HashMap<>();
    }

    public void addProductHandler(int id, ProductHandler productHandler) {
        productHandlers.put(id, productHandler);
    }

    public void removeProductHandler(int id) {
        productHandlers.remove(id);
    }

    public void showTotalStock() {
        int totalStock = 0;
        for (ProductHandler productHandler : productHandlers.values()) {
            totalStock += productHandler.getStock();
        }
        System.out.println("Total Stock: " + totalStock);
    }

    public void showAllDetails() {
        for (ProductHandler productHandler : productHandlers.values()) {
            productHandler.showDetails();
        }
    }

}

public class MMBE1 {

    public static void main(String[] args) {
        Product redsoxcap = new Product("Baseball Cap", "Hat", 10);
        ProductHandler redsoxcapHandler = new ProductHandler(redsoxcap, 123);

        Product stanley = new Product("Stanley Cup", "Mug", 15);
        ProductHandler stanleyHandler = new ProductHandler(stanley, 234);

        ProductManager pm = new ProductManager();
        pm.addProduct(1, redsoxcapHandler);
        pm.addProduct(2, stanleyHandler);

        pm.showTotalStock();
        redsoxcapHandler.reduceStock();
        pm.showTotalStock();
    }

}
