package polo.model.entity;

import java.util.ArrayList;

public class test {
    
    public static void main(String[] args) {
        ArrayList<String> a = new ArrayList<>();
        a.add("156-654");
        a.add("854-56418");
        for (String string : a) {
            String[] prodStrings = string.split("-");
            System.out.println(Integer.parseInt(prodStrings[0]));
        }
    }
}
