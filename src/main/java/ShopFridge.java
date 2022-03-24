import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;

class ShopFridge {

    public static void main(String[] args) throws IOException {
        BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
        ShopFridge shopFridge=new ShopFridge();

        startShopFridge(shopFridge);

    }
        public static void startShopFridge(ShopFridge shopFridge) throws IOException{
        BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
        boolean run=true;

        while(run){
            System.out.println("\nWrite: 1-to create new Fridge " +
                    "\n 2-to create new Shopping list " +
                    "\n 3-to create new Fridge and Shopping list" +
                    "\n 0=to exit");
            int option=Integer.parseInt(reader.readLine());
            run=shopFridge.options(option);

        }

    }

    public boolean options(Integer option) throws IOException{
        if(option==0){
            return false;
        }
        if(option==1){
            Fridge fridge=new Fridge();
            fridge.addToFridge(listOfFridgeProducts());
            return true;
        }
        if(option==2){
            ShoppingList shoppingList=new ShoppingList();
            shoppingList.addToShoppingList(listOfProducts());
            return true;

        }
        if(option==3) {
            Fridge fridge=new Fridge();
            fridge.addToFridge(listOfFridgeProducts());
            ShoppingList shoppingList=new ShoppingList();
            shoppingList.addToShoppingList(listOfProducts());
            return true;

        }
        System.out.println("Please select number between 0 and 9");
        return true;

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
    
    public void addToFridge(LinkedList<FridgeProduct> list){
        for(int i=0;i<list.size();i++){
            contents.add(list.get(i));
        }
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
    public void addToShoppingList(LinkedList<Product> list){
        for(int i=0;i<list.size();i++){
            shoppingList.add(list.get(i));
        }
    }

}
