package polo.model.entity;

import java.util.ArrayList;

public class Order{
    
    private int id;
    private int clientId;
    private ArrayList<String> products;
    private static int ID = 0;

    public Order(int clientId, ArrayList<String> products) {
        this.id = ID++;
        this.clientId = clientId;
        this.products = products;
    }
    
    public Order(int id, int clientId, ArrayList<String> products) {
        this.id = id;
        this.clientId = clientId;
        this.products = products;
    }
    
    public void addProduct(Product product, int amount){
        products.add(product.getCode() + "-" + amount);
    }

    public int getId() {
        return id;
    }

    public int getClientId() {
        return clientId;
    }

    public ArrayList<String> getProducts() {
        return products;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public void setProducts(ArrayList<String> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        String p = "";
        for (String product : products) {
            p += product + " ";
        }
        return "Id de la ordern " + id + ", Id del usuario " + clientId + " prd " + p; 
    }
    
    
}
