package polo.model.DAO;

import java.util.ArrayList;
import polo.model.entity.Account;
import polo.model.entity.Client;

public class UserManager {
    
    private ArrayList<Client> clients;
    private ArrayList<Account> accountsList;

    public UserManager(ArrayList<Client> userList) {
        this.clients = userList;
        this.accountsList = new ArrayList<>();
    }

    public UserManager(ArrayList<Client> clients, ArrayList<Account> accountsList) {
        this.clients = clients;
        this.accountsList = accountsList;
    }

    public UserManager() {
        this.clients = new ArrayList<>();
        this.accountsList = new ArrayList<>();
    }
    
    public void addClient(String name, String address, String phoneNumber, String email){
        clients.add(new Client(name, address, phoneNumber, email));
    }
    
    public void addAccount(Account account){
        accountsList.add(account);
    }
    
    public Client getClient(int id){
        for (Client client : clients) {
            if (client.getId() == id) {
                return client;
            }
        }
        return new Client(-1, "", " ", " ", " ");
    }
    
    public void deleteClient(int id){
        clients.stream().filter((client) -> (client.getId() == id)).forEachOrdered((client) -> {
            clients.remove(client);
        });
        
//        for (Client client : clients) {
//            if (client.getId() == id) {
//                clients.remove(client);
//            }
//        }
    }
    
    public void editClient(int id, String name, String address, String phoneNumber, String email){
        clients.stream().filter((client) -> (client.getId() == id)).map((client) -> {
            client.setName(name);
            return client;
        }).map((client) -> {
            client.setAddress(address);
            return client;
        }).map((client) -> {
            client.setPhoneNumber(phoneNumber);
            return client;
        }).forEachOrdered((client) -> {
            client.setEmail(email);
        });
        
//        for (Client client : clients) {
//            if (client.getId() == id) {
//                client.setName(name);
//                client.setAddress(address);
//                client.setPhoneNumber(phoneNumber);
//                client.setEmail(email);
//            }
//        }
    }
    
    public Client searchClientId(int id){
        for (Client client : clients) {
            if (client.getId() == id) {
                return client;
            }
        }
        return new Client(-1, " ", " ", " ", " ");
    }
    
    public Client searchClientName(String name){
        for (Client client : clients) {
            if (client.getName().equalsIgnoreCase(name)) {
                return client;
            }
        }
        return new Client(-1, " ", " ", " ", " ");
    }
    
    public Client searchClientEmail(String email){
        for (Client client : clients) {
            if (client.getEmail().equalsIgnoreCase(email)) {
                return client;
            }
        }
        return new Client(-1, " ", " ", " ", " ");
    }

    public Client searchClientPhoneNumber(String phone){
        for (Client client : clients) {
            if (client.getPhoneNumber().equalsIgnoreCase(phone)) {
                return client;
            }
        }
        return new Client(-1, " ", " ", " ", " ");
    }
    
    public Client searchClientAddress(String address){
        for (Client client : clients) {
            if (client.getAddress().equalsIgnoreCase(address)) {
                return client;
            }
        }
        return new Client(-1, " ", " ", " ", " ");
    }
    
    
    public ArrayList<Account> getUserAccounts(int id){
        ArrayList<Account> accounts = new ArrayList<>();
        for (Account account : accountsList) {
            if (account.getIdUser() == id) {
                accounts.add(account);
            }
        }
        return accounts;
    }
    
    public boolean haveUserMoney(int id){
        for (Account account : accountsList) {
            if (account.getIdUser() == id && account.getMoneyAmount() > 0) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<Client> getClients() {
        return clients;
    }

    public ArrayList<Account> getAccountsList() {
        return accountsList;
    }
    
    
}
