import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;

class ShopFridge {

    public static void main(String[] args) throws IOException {
        BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
        ShopFridge wybor=new ShopFridge();

        System.out.println("If you want to set your fridge status write 0, if you want create shopping list write 1,if you want do both write 2: ");
        int opcja=Integer.parseInt(reader.readLine());
        wybor.options(opcja);



    }

    public void options(Integer option) throws IOException{
        if(option==0){
            Fridge fridge=new Fridge();
            setFridgeStatus(listOfFridgeProducts(),fridge);
        }
        if(option==1){
            ShoppingList shoppingList=new ShoppingList();
            setShoppingList(listOfProducts(),shoppingList);
        }
        if(option==2) {
            Fridge fridge=new Fridge();
            setFridgeStatus(listOfFridgeProducts(),fridge);
            ShoppingList shoppingList=new ShoppingList();
            setShoppingList(listOfProducts(),shoppingList);
        }

    }

    public LinkedList<FridgeProduct> listOfFridgeProducts() throws IOException{
        BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
        LinkedList<FridgeProduct> list=new LinkedList<>();

            String isLast="";
        while(!isLast.equals("last")){
            FridgeProduct product=new FridgeProduct();
            System.out.println("Write name of product in your fridge: ");
            String name= reader.readLine();
            System.out.println("How much of " + name + " you got in your fridge?");
            Integer quantity=Integer.parseInt(reader.readLine());
            product.setProduct(name,quantity);
            list.add(product);
            System.out.println(name +" was add to your fridge, if it's your last product write 'last', if you want add more write 'more'. ");
            isLast= reader.readLine();

        }
        return list;
    }

    public void setFridgeStatus(LinkedList<FridgeProduct> list, Fridge fridge){
        for(int i=0;i<list.size();i++){
            fridge.addToFridge(list.get(i));
        }
    }


    public LinkedList<Product> listOfProducts() throws IOException{
        BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
        LinkedList<Product> list=new LinkedList<>();

        String isLast="";
        while(!isLast.equals("last")){
            Product product=new Product();
            System.out.println("Write name of product you want to buy: ");
            String name= reader.readLine();
            System.out.println("How much of " + name + " you want to buy?");
            Integer quantity=Integer.parseInt(reader.readLine());
            product.setProduct(name,quantity);
            list.add(product);
            System.out.println(name +" was add to your shopping list, if it's your last product write 'last', if you want add more write 'more'. ");
            isLast= reader.readLine();

        }
        return list;
    }

    public void setShoppingList(LinkedList<Product> list, ShoppingList shoppingList){
        for(int i=0;i< list.size();i++){
            shoppingList.addToShoppingList(list.get(i));
        }
    }

}
class FridgeProduct extends Product{
    private int min=0;
    private int max=99;

    public void changeMin(int min){
        this.min=min;
    }
    public void changeMax(int max){
        this.max=max;
    }

    public int getMin(){ return min; }
    public int getMax() { return max; }
}

class Product{
    private String name;
    private int quantity;


    public void setProduct(String name,int quantity){

        this.name=name;
        if(quantity>0) {
            this.quantity = quantity;
        }else
            {
                this.quantity=0;
        }
    }
    public void setProduct(String name){
        this.name=name;
        this.quantity=0;
    }

    public int getQuantity() {
        return quantity;
    }
    public String getName() {
        return name;
    }
}

class Fridge{
    private LinkedList<FridgeProduct> contents=new LinkedList<>();

    public void addToFridge(FridgeProduct product){
        contents.add(product);
    }

    public void remove(FridgeProduct product){
        contents.remove(product);
    }

    public LinkedList<FridgeProduct> getContents() {
        return contents;
    }

}

class ShoppingList{
    private LinkedList<Product> shoppingList=new LinkedList<>();

    public void addToShoppingList(Product product){
        shoppingList.add(product);
    }

    public void remove(Product product){
        shoppingList.remove(product);
    }
    public LinkedList<Product> getShoppingList() {
        return shoppingList;
    }

}