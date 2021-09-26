package polo.model.DAO;

import java.awt.BorderLayout;
import java.awt.CardLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;

import java.awt.event.ItemListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import polo.model.entity.Account;
import polo.model.entity.Client;
import polo.model.entity.Order;
import polo.model.entity.Product;

public class MainWindow implements ItemListener, ActionListener {

    JPanel cards;
    final static String PANEL_CLIENTES = "Card con clientes";
    final static String PANEL_PRODUCTOS = "Card con productos";
    final static String PANEL_ORDENES_P = "Card con ordenes pendientes";
    final static String PANEL_ORDENES_C = "Card con ordenes Cobradas";
    final static String PANEL_ORDENES_R = "Card con ordenes Rechazadas";
    private UserManager userManager;
    private OrderManager orderManager;
    private ProductManager productManager;
    private JList listPr;
    private JList listPO;
    private JList listAO;
    private JList listDO;
    private JPanel card2;
    private JPanel card3;
    private JPanel card4;
    private JPanel card5;

    public MainWindow() throws SQLException {
        listPr = new JList();
        listPO = new JList();
        listDO = new JList();
        listAO = new JList();
        card2 = new JPanel();
        card3 = new JPanel();
        card4 = new JPanel();
        card5 = new JPanel();
        Conexion conexion = new Conexion();
        conexion.conectarBaseDatos("jdbc:mysql://localhost:3306/", "root", "");
        ResultSet resultadoClientes = conexion.querysDML("select * from polo.clientes;");
        ArrayList<Client> clients = new ArrayList<>();
        while (resultadoClientes.next()) {
            String nombre = resultadoClientes.getString("nombre");
            String direccion = resultadoClientes.getString("direccion");
            String telefono = resultadoClientes.getString("telefono");
            String email = resultadoClientes.getString("email");
            clients.add(new Client(nombre, direccion, telefono, email));
        }

        ResultSet resultadoProductos = conexion.querysDML("select * from polo.productos;");
        ArrayList<Product> products = new ArrayList<>();
        while (resultadoProductos.next()) {
            int compra = resultadoProductos.getInt("precio_compra");
            int venta = resultadoProductos.getInt("precio_venta");
            int inventario = resultadoProductos.getInt("cantidad_inventario");
            int inventario_minimo = resultadoProductos.getInt("inventario_minimo");
            boolean iva = resultadoProductos.getBoolean("iva");
            products.add(new Product(compra, venta, inventario, inventario_minimo, iva));
        }

        ResultSet resultadoCuentas = conexion.querysDML("select * from polo.cuentas;");
        ArrayList<Account> accounts = new ArrayList<>();
        while (resultadoCuentas.next()) {
            int tarjeta = resultadoCuentas.getInt("tarjeta_credito");
            int usuario = resultadoCuentas.getInt("id_usuario");
            int dinero = resultadoCuentas.getInt("dinero");
            accounts.add(new Account(tarjeta, dinero, usuario));
        }
        conexion.desconectarBaseDatos();
//        Client aa = new Client("Alejandro", "av 123 #90-60", "3214777612", "asra@hotmail.com");
//        Client af = new Client("Alexander", "av 115 #89-32", "3215658156", "dsfg@hotmail.com");
//        Client ad = new Client("Alejandra", "cl 546 #56-15", "3219158945", "rwt@hotmail.com");
//        ArrayList<Client> clients = new ArrayList<>();
//        clients.add(aa);
//        clients.add(af);
//        clients.add(ad);
//
//        Account a = new Account(8798423, 54942, 0);
//        Account b = new Account(5487993, 89871, 0);
//        Account c = new Account(5421257, 48749, 0);
//        Account d = new Account(9687412, 335421, 1);
//        Account e = new Account(2312250, 1000, 1);
//        Account f = new Account(9743212, 285415, 2);
//        Account g = new Account(8132147, 41584541, 0);
//        ArrayList<Account> accounts = new ArrayList<>();
//        accounts.add(a);
//        accounts.add(b);
//        accounts.add(c);
//        accounts.add(d);
//        accounts.add(e);
//        accounts.add(g);
//        accounts.add(f);
//
//        Product papa = new Product(100, 3000, 200, 20, false);
//        Product gaseosa = new Product(50, 1500, 300, 30, false);
//        Product jugo = new Product(20, 1600, 80, 5, true);
//        Product arroz = new Product(200, 5000, 456, 50, false);
//        Product sal = new Product(40, 2000, 600, 60, false);
//        Product azucar = new Product(60, 2000, 600, 60, true);
//        Product lenteja = new Product(89, 2000, 500, 50, false);
//        Product frijol = new Product(80, 2000, 500, 50, false);
//        Product panela = new Product(500, 1000, 1000, 40, true);
//        Product platano = new Product(900, 2000, 10, 2, false);
//        ArrayList<Product> products = new ArrayList<>();
//        products.add(papa);
//        products.add(gaseosa);
//        products.add(jugo);
//        products.add(arroz);
//        products.add(azucar);
//        products.add(lenteja);
//        products.add(frijol);
//        products.add(panela);
//        products.add(platano);
//        products.add(sal);

        ArrayList<String> product = new ArrayList<>();
        product.add("1-5");
        Order bb = new Order(0, product);
        ArrayList<Order> orders = new ArrayList<>();
        orders.add(bb);
        productManager = new ProductManager(products);
        userManager = new UserManager(clients, accounts);
        orderManager = new OrderManager(orders, userManager, productManager);
    }

    public void agregarComponentes(JFrame contenedor) {

        JPanel comboBoxPanel = new JPanel();
        String[] comboItems = {PANEL_CLIENTES, PANEL_PRODUCTOS, PANEL_ORDENES_P, PANEL_ORDENES_C, PANEL_ORDENES_R};

        JComboBox cb = new JComboBox(comboItems);
        cb.setEditable(false);
        cb.addItemListener(this);
        comboBoxPanel.add(cb);
        JPanel card1 = new JPanel();
        DefaultListModel model = new DefaultListModel();
        userManager.getClients().forEach((c) -> {
            model.addElement(c.toString());
        });
        JList listCl = new JList(model);
        card1.add(listCl);

        DefaultListModel modelP = new DefaultListModel();
        productManager.getProducts().forEach((p) -> {
            modelP.addElement(p.toString());
        });
        listPr = new JList(modelP);
        JButton update = new JButton("Actualizar Productos");
        update.addActionListener(this);
        update.setActionCommand("agregar");
        JButton search = new JButton("Buscar");
        search.addActionListener(this);
        search.setActionCommand("buscar");
        JButton delete = new JButton("Eliminar");
        delete.addActionListener(this);
        delete.setActionCommand("eliminar");
        card2.add(update);
        card2.add(search);
        card2.add(delete);
        card2.add(listPr);

        DefaultListModel modelPO = new DefaultListModel();
        orderManager.getPendingOrders().forEach((o) -> {
            modelPO.addElement(o.toString());
        });
        listPO = new JList(modelPO);
        JButton charge = new JButton("Cobrar");
        charge.addActionListener(this);
        charge.setActionCommand("cobrar");
        JButton order = new JButton("Nueva");
        order.addActionListener(this);
        order.setActionCommand("nueva");
        card3.add(charge);
        card3.add(order);
        card3.add(listPO);

        DefaultListModel modelAO = new DefaultListModel();
        orderManager.getChargedOrders().forEach((o) -> {
            modelAO.addElement(o.toString());
        });
        listAO = new JList(modelAO);
        card4.add(listAO);

        DefaultListModel modelDO = new DefaultListModel();
        orderManager.getRejectedOrders().forEach((o) -> {
            modelDO.addElement(o.toString());
        });
        listDO = new JList(modelDO);
        card5.add(listDO);

        //instancia el panel que contiene las "cards" y las agrega.
        cards = new JPanel(new CardLayout());
        cards.add(card1, PANEL_CLIENTES);
        cards.add(card2, PANEL_PRODUCTOS);
        cards.add(card3, PANEL_ORDENES_P);
        cards.add(card4, PANEL_ORDENES_C);
        cards.add(card5, PANEL_ORDENES_R);

        contenedor.add(comboBoxPanel, BorderLayout.PAGE_START);
        contenedor.add(cards, BorderLayout.CENTER);

    }

    @Override
    public void itemStateChanged(ItemEvent ie) {
        CardLayout cl = (CardLayout) (cards.getLayout());
        cl.show(cards, (String) ie.getItem());

    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getActionCommand().equalsIgnoreCase("cobrar")) {
            orderManager.chargeAllOrdersConsole();
        } else if (ae.getActionCommand().equalsIgnoreCase("nueva")) {
            String idClient = JOptionPane.showInputDialog("ingrese el id del usuario que compra");
            boolean a = true;
            ArrayList<String> products = new ArrayList<>();
            while (a) {
                String pedido = JOptionPane.showInputDialog("ingrese el pedido id-cantidad (para terminar escriba solo 'ok')");
                if (pedido.equals("ok")) {
                    a = false;
                } else {
                    products.add(pedido);
                }
            }
            Order order = new Order(Integer.parseInt(idClient), products);
            orderManager.addOrder(order);
            Conexion conexion = new Conexion();
            conexion.conectarBaseDatos("jdbc:mysql://localhost:3306/", "root", "");
            String p = "";
            for (String product : products) {
                p += product + " ";
            }
            conexion.querysDDL("INSERT INTO polo.ordenes(id, id_cliente, productos) VALUES (NULL, " + order.getClientId() + ", '" + p + "')");
            conexion.desconectarBaseDatos();

        } else if (ae.getActionCommand().equalsIgnoreCase("agregar")) {
            String id = JOptionPane.showInputDialog("ingrese el codigo del producto");
            String cantidad = JOptionPane.showInputDialog("ingrese la cantidad de que quiere ingresar al inventario");
            int aux = productManager.getProduct(Integer.parseInt(id)).getWarehouseAmount() + Integer.parseInt(cantidad);
            productManager.getProduct(Integer.parseInt(id)).setWarehouseAmount(aux);
            Conexion conexion = new Conexion();
            conexion.conectarBaseDatos("jdbc:mysql://localhost:3306/", "root", "");
            conexion.querysDDL("UPDATE polo.productos SET cantidad_inventario=" + aux + " WHERE codigo=" + Integer.parseInt(id));
            conexion.desconectarBaseDatos();
        } else if (ae.getActionCommand().equalsIgnoreCase("buscar")) {
            String id = JOptionPane.showInputDialog("ingrese el codigo del producto que desea buscar");
            JOptionPane.showMessageDialog(null, productManager.getProduct(Integer.parseInt(id)).toString());
        } else if (ae.getActionCommand().equalsIgnoreCase("eliminar")) {
            String id = JOptionPane.showInputDialog("ingrese el codigo del producto que desea eliminar");
            productManager.deleteProduct(Integer.parseInt(id));
            Conexion conexion = new Conexion();
            conexion.conectarBaseDatos("jdbc:mysql://localhost:3306/", "root", "");
            conexion.querysDDL("DELETE FROM polo.productos WHERE codigo=" + Integer.parseInt(id));
            conexion.desconectarBaseDatos();
            JOptionPane.showMessageDialog(null, "Producto eliminardo exitosamente");
        }

        DefaultListModel modelP = new DefaultListModel();
        productManager.getProducts().forEach((p) -> {
            modelP.addElement(p.toString());
        });
        listPr = new JList(modelP);
        DefaultListModel modelPO = new DefaultListModel();
        orderManager.getPendingOrders().forEach((o) -> {
            modelPO.addElement(o.toString());
        });
        listPO = new JList(modelPO);
        DefaultListModel modelAO = new DefaultListModel();
        orderManager.getChargedOrders().forEach((o) -> {
            modelAO.addElement(o.toString());
        });
        listAO = new JList(modelAO);
        DefaultListModel modelDO = new DefaultListModel();
        orderManager.getRejectedOrders().forEach((o) -> {
            modelDO.addElement(o.toString());
        });
        listDO = new JList(modelDO);
        card2.remove(3);
        card3.remove(2);
        card4.removeAll();
        card5.removeAll();
        card2.add(listPr);
        card3.add(listPO);
        card4.add(listAO);
        card5.add(listDO);
        cards.revalidate();
        cards.repaint();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Order Manager");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        MainWindow demo;
        try {
            demo = new MainWindow();
            demo.agregarComponentes(frame);
        } catch (SQLException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
        frame.pack();
        frame.setVisible(true);
    }
}
