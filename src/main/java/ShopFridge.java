
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

class ShopFridge {
    private ArrayList<Fridge> myFridges=new ArrayList<>();
    private ArrayList<ShoppingList> myShoppingLists=new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
        ShopFridge shopFridge=new ShopFridge();

        startShopFridge(shopFridge);

    }

    public static void startShopFridge(ShopFridge shopFridge) throws IOException{
        BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
        boolean run=true;

        while(run){
            System.out.println("\nWrite:" +
                    "\n 1-to create new Fridge " +
                    "\n 2-to create new Shopping list " +
                    "\n 3-to create new Fridge and Shopping list" +
                    "\n 0-to exit");
            int option=Integer.parseInt(reader.readLine());
            run=shopFridge.options(option);

        }

    }

    public boolean options(Integer option) throws IOException{
        Fridge fridge=new Fridge();
        ShoppingList shoppingList=new ShoppingList();

        switch(option){
            case (0):
                return false;
            case (1):
                fridge.addToFridge(listOfFridgeProducts());
                myFridges.add(fridge);
                System.out.println("Fridge number " + (myFridges.size()+1) + " was add to your Fridges");
                return true;
            case(2):
                shoppingList.addToShoppingList(listOfProducts());
                myShoppingLists.add(shoppingList);
                System.out.println("Shoppinglist number " + (myShoppingLists.size()+1) + " was add to your Shopping Lists");
                return true;
            case(3):
                fridge.addToFridge(listOfFridgeProducts());
                myFridges.add(fridge);
                System.out.println("Fridge number " + (myFridges.size()+1) + " was add to your fridges");
                shoppingList.addToShoppingList(listOfProducts());
                myShoppingLists.add(shoppingList);
                System.out.println("Shoppinglist number " + (myShoppingLists.size()+1) + " was add to your Shopping Lists");
                return true;
            default:
                System.out.println("Please select number between 0 and 9");
                return true;
        }

    }

    public ArrayList<FridgeProduct> listOfFridgeProducts() throws IOException{
        BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
        ArrayList<FridgeProduct> list=new ArrayList<>();

            String isLast="";

        while(!isLast.equals("last")) {
            System.out.println("Write name of product in your fridge: ");

            String name = reader.readLine();
            System.out.println("How much of " + name + " you got in your fridge?");
            Integer quantity = Integer.parseInt(reader.readLine());
            FridgeProduct product = new FridgeProduct(name, quantity);
            list.add(product);
            System.out.println(name + " was add to your fridge, if it's your last product write 'last', if you want add more write 'more'. ");
            isLast = reader.readLine();
        }


        return list;
    }


    public ArrayList<Product> listOfProducts() throws IOException{
        BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
        ArrayList<Product> list=new ArrayList<>();

        String isLast="";
        while(!isLast.equals("last")){
            System.out.println("Write name of product you want to buy: ");
            String name = reader.readLine();
            System.out.println("How much of " + name + " you want to buy?");
            Integer quantity = Integer.parseInt(reader.readLine());
            Product product = new Product(name, quantity);
            list.add(product);
            System.out.println(name + " was add to your shopping list, if it's your last product write 'last', if you want add more write 'more'. ");
            isLast = reader.readLine();

        }
        return list;
    }

    public ShoppingList removeExcess(Fridge fridge, ShoppingList shoppingList){

        ArrayList<FridgeProduct> fridgeContent=fridge.getContents();
        ArrayList<Product> shoppingListContent=shoppingList.getShoppingList();

    //remove products from shopping list if we got too much of the product in our fridge, or change the products quantity to not exceeds max value
        for(int i=0;i<shoppingListContent.size();i++){
            String productName=shoppingListContent.get(i).getName();
            for(int j=0;j<fridgeContent.size();j++){
                String fridgeProductName=fridgeContent.get(j).getName();
                if(fridgeProductName.equals(productName)){
                    if(fridgeContent.get(j).getMax()<
                            fridgeContent.get(j).getQuantity()+shoppingListContent.get(i).getQuantity()){
                        if(fridgeContent.get(j).getMax()<=fridgeContent.get(j).getQuantity()){
                            shoppingListContent.remove(i);
                        }else
                        {
                            shoppingListContent.get(i).setQuantity((fridgeContent.get(j).getMax()-fridgeContent.get(j).getQuantity()));
                        }
                    }
                }
            }
        }
        ShoppingList checkedShoppingList=new ShoppingList();
        checkedShoppingList.addToShoppingList(shoppingListContent);
        return checkedShoppingList;
    }

    public ShoppingList fillGaps(Fridge fridge, ShoppingList shoppingList){
        ArrayList<FridgeProduct> fridgeContent=fridge.getContents();
        ArrayList<Product> shoppingListContent=shoppingList.getShoppingList();

        for(int i=0;i<fridgeContent.size();i++){
            String productName=fridgeContent.get(i).getName();
            if(fridgeContent.get(i).getMin()>fridgeContent.get(i).getQuantity()){
                int gaps=fridgeContent.get(i).getMin()-fridgeContent.get(i).getQuantity();
                for(int j=0;j<shoppingListContent.size();j++){
                    if(shoppingListContent.get(j).getName().equals(productName)){
                        if(shoppingListContent.get(j).getQuantity()<gaps)
                            shoppingListContent.get(j).setQuantity(gaps);
                    }
                    else{
                        Product missingProduct=new Product(productName,gaps);
                        shoppingListContent.add(missingProduct);
                    }
                }
            }
        }
        ShoppingList checkedShoppingList=new ShoppingList();
        checkedShoppingList.addToShoppingList(shoppingListContent);
        return checkedShoppingList;
    }


}