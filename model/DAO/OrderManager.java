package polo.model.DAO;

import java.util.ArrayList;
import polo.model.entity.Account;
import polo.model.entity.Order;
import polo.model.entity.Product;

public class OrderManager {

    private ArrayList<Order> pendingOrders;
    private ArrayList<Order> approvedOrders;
    private ArrayList<Order> rejectedOrders;
    private ArrayList<Order> chargedOrders;
    private ArrayList<Order> deliveredgOrders;
    private UserManager userManager;
    private ProductManager productManager;

    public OrderManager(ArrayList<Order> pendingOrders, ArrayList<Order> approvedOrders, ArrayList<Order> rejectedOrders, ArrayList<Order> chargedOrders, ArrayList<Order> deliveredgOrders, UserManager userManager, ProductManager productManager) {
        this.pendingOrders = pendingOrders;
        this.approvedOrders = approvedOrders;
        this.rejectedOrders = rejectedOrders;
        this.chargedOrders = chargedOrders;
        this.deliveredgOrders = deliveredgOrders;
        this.productManager = productManager;
        this.userManager = userManager;
    }

    public OrderManager(ArrayList<Order> pendingOrders, UserManager userManager, ProductManager productManager) {
        this.pendingOrders = pendingOrders;
        this.userManager = userManager;
        this.productManager = productManager;
        this.approvedOrders = new ArrayList<>();
        this.rejectedOrders = new ArrayList<>();
        this.chargedOrders = new ArrayList<>();
        this.deliveredgOrders = new ArrayList<>();
    }

    private OrderManager() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void addOrder(Order order) {
        pendingOrders.add(order);
    }

    public void chargeAllOrdersConsole() {
        ArrayList<Order> toRemove = new ArrayList<>();
        for (Order pendingOrder : pendingOrders) {
            if (userManager.searchClientId(pendingOrder.getClientId()).getId() >= 0 && userManager.haveUserMoney(pendingOrder.getClientId())) {
                ArrayList<Account> accounts = new ArrayList<>();
                for (Account account : userManager.getAccountsList()) {
                    if (account.getIdUser() == pendingOrder.getClientId() && account.getMoneyAmount() > (calculatePriceNoIva(pendingOrder) + (int) calculateIva(pendingOrder))) {
                        accounts.add(account);
                    }
                }
                if (!accounts.isEmpty()) {
                    userManager.getAccountsList().get(userManager.getAccountsList().indexOf(accounts.get(0))).decreaseAmount(calculatePriceNoIva(pendingOrder) + (int) calculateIva(pendingOrder));
                    boolean ok = true;
                    for (String product : pendingOrder.getProducts()) {
                        String[] prodStrings = product.split("-");
                        Product p = productManager.getProduct(Integer.parseInt(prodStrings[0]));
                        if (p.getWarehouseAmount() < Integer.parseInt(prodStrings[1])) {
                            ok = false;
                        }
                    }
                    if (ok) {
                        for (String product : pendingOrder.getProducts()) {
                            String[] prodStrings = product.split("-");
                            boolean decrease = productManager.decreaseProducts(Integer.parseInt(prodStrings[0]), Integer.parseInt(prodStrings[1]));
                            if (!decrease) {
                                break;
                            }
                        }
                        chargedOrders.add(pendingOrder);
                        toRemove.add(pendingOrder);
                        System.out.println("A");

                    } else {
                        rejectedOrders.add(pendingOrder);
                        toRemove.add(pendingOrder);
                        System.out.println("R CAN");
                    }
                } else {
                    rejectedOrders.add(pendingOrder);
                    toRemove.add(pendingOrder);
                    System.out.println("R ACC");
                }
            } else {
                rejectedOrders.add(pendingOrder);
                toRemove.add(pendingOrder);
                System.out.println("R MON");
            }
        }
        for (Order order : toRemove) {
            pendingOrders.remove(order);
        }
    }

    public int calculatePriceNoIva(Order order) {
        int total = 0;
        for (String product : order.getProducts()) {
            String[] prodStrings = product.split("-");
            if (!prodStrings[0].isEmpty()) {
                Product productAux = productManager.getProduct(Integer.parseInt(prodStrings[0]));
                if (productAux.getCode() == -1) {
                    total += 0;
                } else {
                    total += productAux.getSellPrice();
                }
            }
        }

        return total;
    }

    public double calculateIva(Order order) {
        int Iva = 0;
        for (String product : order.getProducts()) {
            String[] prodStrings = product.split("-");
            if (!prodStrings[0].isEmpty()) {
                Product productAux = productManager.getProduct(Integer.parseInt(prodStrings[0]));
                if (productAux.isIva()) {
                    Iva += ((productAux.getSellPrice() * 19) / 100);
                }
            }

        }
        return Iva;
    }

    public ArrayList calculateSubtotal(Order order) {
        ArrayList<Integer> subtotals = new ArrayList<Integer>();
        for (String product : order.getProducts()) {
            String[] prodStrings = product.split("-");
            if (!prodStrings[0].isEmpty() && !prodStrings[1].isEmpty()) {
                Product productAux = productManager.getProduct(Integer.parseInt(prodStrings[0]));
                subtotals.add(productAux.getSellPrice() * Integer.parseInt(prodStrings[1]));
            }
        }
        return subtotals;
    }

    public ArrayList<Order> getPendingOrders() {
        return pendingOrders;
    }

    public ArrayList<Order> getApprovedOrders() {
        return approvedOrders;
    }

    public ArrayList<Order> getChargedOrders() {
        return chargedOrders;
    }

    public ArrayList<Order> getDeliveredgOrders() {
        return deliveredgOrders;
    }

    public ArrayList<Order> getRejectedOrders() {
        return rejectedOrders;
    }

}
