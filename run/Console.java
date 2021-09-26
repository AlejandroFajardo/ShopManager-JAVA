package run;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import polo.model.DAO.OrderManager;
import polo.model.DAO.ProductManager;
import polo.model.DAO.UserManager;
import polo.model.entity.Account;
import polo.model.entity.Client;
import polo.model.entity.Order;
import polo.model.entity.Product;

public class Console {

    ProductManager productManager;
    UserManager userManager;
    OrderManager orderManager;

    public static void main(String[] args) {
        Console console = new Console();
        Client aa = new Client("Alejandro", "av 123 #90-60", "3214777612", "asra@hotmail.com");
        Client af = new Client("Alexander", "av 115 #89-32", "3215658156", "dsfg@hotmail.com");
        Client ad = new Client("Alejandra", "cl 546 #56-15", "3219158945", "rwt@hotmail.com");
        ArrayList<Client> clients = new ArrayList<>();
        clients.add(aa);
        clients.add(af);
        clients.add(ad);

        Account a = new Account(8798423, 54942, 0);
        Account b = new Account(5487993, 89871, 0);
        Account c = new Account(5421257, 48749, 0);
        Account d = new Account(9687412, 335421, 1);
        Account e = new Account(2312250, 1000, 1);
        Account f = new Account(9743212, 285415, 2);
        Account g = new Account(8132147, 41584541, 0);
        ArrayList<Account> accounts = new ArrayList<>();
        accounts.add(a);
        accounts.add(b);
        accounts.add(c);
        accounts.add(d);
        accounts.add(e);
        accounts.add(g);
        accounts.add(f);

        Product papa = new Product(100, 3000, 200, 20, false);
        Product gaseosa = new Product(50, 1500, 300, 30, false);
        Product jugo = new Product(20, 1600, 80, 5, true);
        Product arroz = new Product(200, 5000, 456, 50, false);
        Product sal = new Product(40, 2000, 600, 60, false);
        Product azucar = new Product(60, 2000, 600, 60, true);
        Product lenteja = new Product(89, 2000, 500, 50, false);
        Product frijol = new Product(80, 2000, 500, 50, false);
        Product panela = new Product(500, 1000, 1000, 40, true);
        Product platano = new Product(900, 2000, 10, 2, false);
        ArrayList<Product> products = new ArrayList<>();
        products.add(papa);
        products.add(gaseosa);
        products.add(jugo);
        products.add(arroz);
        products.add(azucar);
        products.add(lenteja);
        products.add(frijol);
        products.add(panela);
        products.add(platano);
        products.add(sal);
        
        ArrayList<String> product = new ArrayList<>();
        product.add("1-5");
        Order bb = new Order(0, product);
        ArrayList<Order> orders = new ArrayList<>();
        orders.add(bb);
        console.productManager = new ProductManager(products);
        console.userManager = new UserManager(clients, accounts);
        console.orderManager = new OrderManager(orders, console.userManager, console.productManager);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bienvenido | Selecciona una opcion");
        System.out.println("1. Agregar orden");
        System.out.println("2. Agregar producto");
        System.out.println("3. Agregar cliente");
        System.out.println("4. Listar ordenes pendientes");
        System.out.println("5. Listar ordenes rechazadas");
        System.out.println("6. Listar ordenes aprovadas");
        System.out.println("7. Listar ordenes cobradas");
        System.out.println("8. Listar ordenes entregadas");
        System.out.println("9. Listar productos");
        System.out.println("10. Listar cuentas");
        System.out.println("11. Listar clientes");
        System.out.println("12. cobrar ordenes");
        System.out.println("13. Salir");
        boolean out = false;
        while (!out) {
            int opcion = scanner.nextInt();
            switch (opcion) {
                case 1:
                    console.addOrder(console, scanner);
                    console.printMainMenu();
                    break;
                case 2:
                    console.addProduct(console, scanner);
                    console.printMainMenu();
                    break;
                case 3:
                    console.addClient(console, scanner);
                    console.printMainMenu();
                    break;
                case 4:
                    console.listPending(console);
                    console.printMainMenu();
                    break;
                case 5:
                    console.listRejected(console);
                    console.printMainMenu();
                    break;
                case 6:
                    console.listApproved(console);
                    console.printMainMenu();
                    break;
                case 7:
                    console.listCharged(console);
                    console.printMainMenu();
                    break;
                case 8:
                    console.listDelivered(console);
                    console.printMainMenu();
                    break;
                case 9:
                    console.listProducts(console);
                    console.printMainMenu();
                    break;
                case 10:
                    console.listAccounts(console);
                    console.printMainMenu();
                    break;
                case 11:
                    console.listClients(console);
                    console.printMainMenu();
                    break;
                case 12:
                    console.chargeAllOrdersConsole(console);
                    console.printMainMenu();
                    break;
                case 13:
                    out = true;
                    break;
                default:
                    System.out.println("Solo números entre 1 y 9");
            }
        }
    }

    public void addOrder(Console console, Scanner sc) {
        System.out.println("Ingrese el id del usuario");
        int userId = sc.nextInt();
        listProducts(console);
        System.out.println("Ingrese los id de los productos y la cantidad que desea comprar, linea por linea");
        System.out.println("En formato id-cantidad, cuando termine ingrese 'ok'");
        ArrayList<String> products = new ArrayList<>();
        while (true) {
            String line = sc.next();
            if (line.equalsIgnoreCase("ok")) {
                break;
            }else{
                products.add(line);
            }
            System.out.println("Ingrese el siguiente");
        }
        for (String product : products) {
            System.out.print("{" + product + "}");
        }
        console.orderManager.addOrder(new Order(userId, products));
    }
    
    public void chargeAllOrdersConsole(Console console){
        console.orderManager.chargeAllOrdersConsole();
    }

    public void addProduct(Console console, Scanner sc) {
        System.out.println("Ingrese el precio de compra");
        int purchasePrice = sc.nextInt();
        System.out.println("Ingrese el precio de venta");
        int sellPrice = sc.nextInt();
        System.out.println("Ingrese el inventario");
        int warehouseAmount = sc.nextInt();
        System.out.println("Ingrese el inventario minimo");
        int minimalWarehouseAmount = sc.nextInt();
        System.out.println("tiene iva?(true - false)");
        String iva = sc.nextLine();
        boolean iva1 = iva.equals("true") ? true : false;
        console.productManager.addProduct(purchasePrice, sellPrice, warehouseAmount, minimalWarehouseAmount, iva1);
    }

    public void addClient(Console console, Scanner sc) {
        System.out.println("Ingrese el nombre");
        String name = sc.nextLine();
        System.out.println("Ingrese la direccion");
        String address = sc.nextLine();
        System.out.println("Ingrese el telefono");
        String phoneNumber = sc.nextLine();
        System.out.println("Ingrese el email");
        String email = sc.nextLine();
        console.userManager.addClient(name, address, phoneNumber, email);
    }

    public void listClients(Console console) {
        for (Client client : console.userManager.getClients()) {
            System.out.println(client.toString());
        }
    }

    public void listAccounts(Console console) {
        for (Account account : console.userManager.getAccountsList()) {
            System.out.println(account.toString());
        }
    }

    public void listPending(Console console) {
        for (Order order : console.orderManager.getPendingOrders()) {
            System.out.println(order.toString());
        }
    }

    public void listRejected(Console console) {
        for (Order order : console.orderManager.getRejectedOrders()) {
            System.out.println(order.toString());
        }
    }

    public void listCharged(Console console) {
        for (Order order : console.orderManager.getChargedOrders()) {
            System.out.println(order.toString());
        }
    }

    public void listDelivered(Console console) {
        for (Order order : console.orderManager.getDeliveredgOrders()) {
            System.out.println(order.toString());
        }
    }

    public void listApproved(Console console) {
        for (Order order : console.orderManager.getApprovedOrders()) {
            System.out.println(order.toString());
        }
    }

    public void listProducts(Console console) {
        for (Product product : console.productManager.getProducts()) {
            System.out.println(product.toString());
        }
    }

    private void printMainMenu() {
        System.out.println(" ");
        System.out.println(" ");
        System.out.println("Selecciona una opcion");
        System.out.println("1. Agregar orden");
        System.out.println("2. Agregar producto");
        System.out.println("3. Agregar cliente");
        System.out.println("4. Listar ordenes pendientes");
        System.out.println("5. Listar ordenes rechazadas");
        System.out.println("6. Listar ordenes aprovadas");
        System.out.println("7. Listar ordenes cobradas");
        System.out.println("8. Listar ordenes entregadas");
        System.out.println("9. Listar productos");
        System.out.println("10. Listar cuentas");
        System.out.println("11. Listar clientes");
        System.out.println("12. cobrar ordenes");
        System.out.println("13. Salir");
    }
}
