
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Locale;

class ShopFridge {
    private ArrayList<Fridge> myFridges=new ArrayList<>();
    private ArrayList<ShoppingList> myShoppingLists=new ArrayList<>();
    private final ShoppingListEditor editor;
    private final ListCreator creator;
    private EditView editView;

    public ShopFridge(ShoppingListEditor editor, ListCreator creator,EditView editView){
        this.editor=editor;
        this.creator=creator;
        this.editView=editView;
    }



    public static void main(String[] args) throws IOException {
        BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
        EditView editView=new EditView();
        ShopFridge shopFridge=new ShopFridge(new ShoppingListEditor(),new ListCreator(),new EditView());
        editView.setShopFridge(shopFridge);


        startShopFridge(shopFridge);

    }

    public static void startShopFridge(ShopFridge shopFridge) throws IOException{
        BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
        boolean run=true;

        while(run){
            System.out.println("""

                    Write:
                     1-to create new Fridge\s
                     2-to create new Shopping list\s
                     3-to create new Fridge and Shopping list
                     4-to check your shoppingList
                     5-to display your fridge
                     6-to display searched shopping list
                     9-to edit your fridge or shopping lists
                     0-to exit""");
            int option=Integer.parseInt(reader.readLine());
            run=shopFridge.options(option);

        }

    }

    public boolean options(Integer option) throws IOException{
        BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
        switch(option){
            case (0):
                return false;
            case (1):
                makeNewFridge();
                return true;
            case(2):
               makeNewList();
                return true;
            case(3):
                makeNewFridge();
                makeNewList();
                return true;
            case(4):
                return true;
            case(5):
                String searchedFridge= reader.readLine();
                int ind=findFridgeByName(searchedFridge);
                if(ind !=-1){
                    System.out.println(myFridges.get(ind));
                    return true;
                }
                System.out.println("Fridge not found");
                return  true;
            case(6):
                String searchedName= reader.readLine();
                int index=findListByName(searchedName);
                if(index!=-1){
                    System.out.println(myShoppingLists.get(index));
                    return true;
                }
                System.out.println("List not found");
                return  true;
            case (9):
                editView.setShopFridge(this);
                System.out.println("What do you want to edit? fridge/shoppingList?");
                String choice= reader.readLine();
                if(choice.equals("fridge")){
                    editView.setFridgeFlag(true);
                    editView.setFridges(myFridges);
                    editView.showAllFridges();
                    System.out.println("Write name of fridge to edit it: ");
                    String fridgeToEdit= reader.readLine();
                    editView.setActiveFridge(myFridges.get(findFridgeByName(fridgeToEdit)));
                    editView.showActiveFridge();
                    editView.editOption();
                }
                if(choice.toLowerCase(Locale.ROOT).equals("shoppinglist")){
                    editView.setFridgeFlag(false);
                    editView.setShoppingLists(myShoppingLists);
                    editView.showAllLists();
                    System.out.println("Write name of Shopping List to edit it: ");
                    String ListToEdit= reader.readLine();
                    editView.setActiveShoppingList(myShoppingLists.get(findListByName(ListToEdit)));
                    editView.showActiveShoppingList();
                    editView.editOption();
                }
                return true;

            default:
                System.out.println("Please select number between 0 and 9");
                return true;
        }

    }


    public void makeNewList() throws IOException{
        ShoppingList shoppingList=new ShoppingList();
        shoppingList.addToShoppingList(creator.createShoppingList());
        myShoppingLists.add(shoppingList);
        shoppingList.setId(myShoppingLists.size());
        shoppingList.setName(String.format("new Shopping List(%d)",myShoppingLists.size()));
        System.out.println("Shoppinglist {" + (shoppingList.getName()) + "} was add to your Shopping Lists");
    }

    public void makeNewFridge() throws IOException{
        Fridge fridge=new Fridge();
        fridge.addToFridge(creator.createFridge());
        myFridges.add(fridge);
        fridge.setId(myFridges.size());
        fridge.setName(String.format("new Fridge(%d)",myFridges.size()));
        System.out.println("Fridge {" + (fridge.getName()) + "} was add to your Fridges");
    }

    public int findListByName(String name){
        for(ShoppingList list:myShoppingLists){
            if(list.getName().equals(name))
                return myShoppingLists.indexOf(list);
        }
        return -1;
    }
    public int findFridgeByName(String name){
        for(Fridge fridge:myFridges){
            if(fridge.getName().equals(name))
                return myFridges.indexOf(fridge);
        }
        return -1;
    }

    public void setMyFridges(ArrayList<Fridge> myFridges) {
        this.myFridges = myFridges;
    }

    public void setMyShoppingLists(ArrayList<ShoppingList> myShoppingLists) {
        this.myShoppingLists = myShoppingLists;
    }
}