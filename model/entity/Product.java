package polo.model.entity;

public class Product {

    private int code;
    private int purchasePrice;
    private int sellPrice;
    private int warehouseAmount;
    private int minimalWarehouseAmount;
    private boolean iva;
    private static int ID = 1;
    
    public Product(int purchasePrice, int sellPrice, int warehouseAmount, int minimalWarehouseAmount, boolean iva) {
        this.code = ID++;
        this.purchasePrice = purchasePrice;
        this.sellPrice = sellPrice;
        this.warehouseAmount = warehouseAmount;
        this.minimalWarehouseAmount = minimalWarehouseAmount;
        this.iva = iva;
    }
    
    public Product(int code, int purchasePrice, int sellPrice, int warehouseAmount, int minimalWarehouseAmount, boolean iva) {
        this.code = code;
        this.purchasePrice = purchasePrice;
        this.sellPrice = sellPrice;
        this.warehouseAmount = warehouseAmount;
        this.minimalWarehouseAmount = minimalWarehouseAmount;
        this.iva = iva;
    }

    public int getCode() {
        return code;
    }

    public int getPurchasePrice() {
        return purchasePrice;
    }

    public int getSellPrice() {
        return sellPrice;
    }

    public int getWarehouseAmount() {
        return warehouseAmount;
    }

    public int getMinimalWarehouseAmount() {
        return minimalWarehouseAmount;
    }

    public boolean isIva() {
        return iva;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setPurchasePrice(int purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public void setSellPrice(int sellPrice) {
        this.sellPrice = sellPrice;
    }

    public void setWarehouseAmount(int warehouseAmount) {
        this.warehouseAmount = warehouseAmount;
    }

    public void setMinimalWarehouseAmount(int minimalWarehouseAmount) {
//        if (minimalWarehouseAmount <= 0) {
//            this.minimalWarehouseAmount = 0;
//        }else{
//            this.minimalWarehouseAmount = minimalWarehouseAmount;
//        }
        
        this.minimalWarehouseAmount = (minimalWarehouseAmount <= 0) ? 0 :  minimalWarehouseAmount;
    }

    public void setIva(boolean iva) {
        this.iva = iva;
    }

    @Override
    public String toString() {
        return "Id " + code + ", Precio " + sellPrice + ", Cantidad en inventario " + warehouseAmount + ", Minimo en inventario " + minimalWarehouseAmount + ", ¿Iva? " + iva;
    }
    
    public String[] toArray(){
        String[] p = {String.valueOf(code), String.valueOf(sellPrice), String.valueOf(warehouseAmount), String.valueOf(minimalWarehouseAmount), String.valueOf(iva)};
        return p;
    }
}
