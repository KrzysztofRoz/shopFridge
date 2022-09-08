import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ListCreator {


    public ArrayList<FridgeProduct> createFridge() throws IOException {
        BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
        ArrayList<FridgeProduct> list=new ArrayList<>();

        String isLast="";

        while(!isLast.equals("last")) {
            System.out.println("Write name of product in your fridge: ");

            String name = reader.readLine();
            System.out.println("How much of " + name + " you got in your fridge?");
            Integer quantity = Integer.parseInt(reader.readLine());
            list.add(new FridgeProduct(name, quantity));
            System.out.println(name + " was add to your fridge, if it's your last product write 'last', if you want add more write 'more'. ");
            isLast = reader.readLine();
        }


        return list;
    }


    public ArrayList<Product> createShoppingList() throws IOException{
        BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
        ArrayList<Product> list=new ArrayList<>();

        String isLast="";
        while(!isLast.equals("last")){
            System.out.println("Write name of product you want to buy: ");
            String name = reader.readLine();
            System.out.println("How much of " + name + " you want to buy?");
            Integer quantity = Integer.parseInt(reader.readLine());
            list.add(new Product(name, quantity));
            System.out.println(name + " was add to your shopping list, if it's your last product write 'last', if you want add more write 'more'. ");
            isLast = reader.readLine();

        }
        return list;
    }
}
