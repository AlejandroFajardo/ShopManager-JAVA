package polo.model.DAO;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import polo.model.entity.Product;


public class ProductManager {
   
    private ArrayList<Product> products;

    public ProductManager(ArrayList<Product> products) {
        this.products = products;
    }

    public ProductManager() {
        this.products = new ArrayList<>();
    }
    
    public void addProduct(int purchasePrice, int sellPrice, int warehouseAmount, int minimalWarehouseAmount, boolean iva){
        products.add(new Product(purchasePrice, sellPrice, warehouseAmount, minimalWarehouseAmount, iva));
    }
    
    public void deleteProduct(int id){        
        Product p = null;
        for (Product product : products) {
            if (product.getCode()== id) {
                p = product;
            }
        }
        products.remove(p);
    }
    
    public void editProduct(int code, int purchasePrice, int sellPrice, int warehouseAmount, int minimalWarehouseAmount, boolean iva){
        products.stream().filter((product) -> (product.getCode() == code)).map((product) -> {
            product.setIva(iva);
            return product;
        }).map((product) -> {
            product.setMinimalWarehouseAmount(minimalWarehouseAmount);
            return product;
        }).map((product) -> {
            product.setPurchasePrice(purchasePrice);
            return product;
        }).map((product) -> {
            product.setSellPrice(sellPrice);
            return product;
        }).forEachOrdered((product) -> {
            product.setWarehouseAmount(warehouseAmount);
        });
        
//        for (Product product : products) {
//            if (product.getCode() == code) {
//                product.setIva(iva);
//                product.setMinimalWarehouseAmount(minimalWarehouseAmount);
//                product.setPurchasePrice(purchasePrice);
//                product.setSellPrice(sellPrice);
//                product.setWarehouseAmount(warehouseAmount);
//            }
//        }
        
    }
    
    public Product searchProduct(int code){
        for (Product product : products) {
            if (product.getCode() == code) {
                return product;
            }
        }
        return new Product(-1, 0, 0, 0, 0, false);
    }
    
    public Product moreWarehouseAmountProduct(){
        int aux = 0;
        Product auxProduct = new Product(-1, 0, 0, 0, 0, false);
        for (Product product : products) {
            if (product.getWarehouseAmount() > aux) {
                auxProduct = product;
            }
        }
        return auxProduct;
    }
    
    public Product getProduct(int code){
        Product aux = new Product(-1, 0, 0, 0, 0, false);
        for (Product product : products) {
            if (product.getCode() == code) {
                return aux = product;
            }
        }
        return aux;
    }
    
    public boolean decreaseProducts(int code, int amount){
        for (Product product : products) {
            if (product.getCode() == code) {
                if (product.getWarehouseAmount() >= amount) {
                    product.setWarehouseAmount(product.getWarehouseAmount() - amount);
                    if (product.getWarehouseAmount() < product.getMinimalWarehouseAmount()) {
                        JOptionPane.showMessageDialog(null, "Debe comprar mas producto de id -> " + product.getCode() + " por que quedan menos del minimo permitido");
                    }
                    return true;
                }
            }
        }
        return false;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }
}
