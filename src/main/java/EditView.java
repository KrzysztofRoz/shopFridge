import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class EditView {
    private ShopFridge shopFridge;
    private ArrayList<Fridge> fridges;
    private ArrayList<ShoppingList> shoppingLists;
    private Fridge activeFridge;
    private ShoppingList activeShoppingList;
    private boolean isFridge;

    BufferedReader reader=new BufferedReader(new InputStreamReader((System.in)));



    public void showAllFridges(){
        StringBuilder builder=new StringBuilder("Fridges:\n");
        for(Fridge fridge:fridges){
            builder.append("\t").append(fridge).append("\n");
        }
        builder.append("=============================================================");
        System.out.println(builder);
    }
    public void showAllLists(){
        StringBuilder builder=new StringBuilder("Shopping lists:\n");
        for(ShoppingList list:shoppingLists){
            builder.append("\t").append(list).append("\n");
        }
        builder.append("=============================================================");
        System.out.println(builder);
    }
    public void showActiveFridge(){
        System.out.println("Chosen fridge:");
        System.out.println(activeFridge);
        System.out.println("=============================================================");
    }
    public void showActiveShoppingList(){
        System.out.println("Chosen shopping list:");
        System.out.println(activeShoppingList);
        System.out.println("=============================================================");
    }
    public void editOption(){
        System.out.println("""

                    Write:
                     1-to Change name\s
                     2-to Delete\s
                     3-to Edit Products
                     4-to Exit from edit mode""");
        try {
            Integer chosenOption=Integer.parseInt(reader.readLine());
            optionResolver(chosenOption);
        } catch (IOException e) {
            System.out.println("Please follow the instructions");
            e.printStackTrace();
        }
    }

    public void optionResolver(Integer choice) throws IOException {
        switch (choice){
            case(1):
                editName();
                break;
            case(2):
                delete();
                break;
            case(3):
                break;
            case(4):
                break;
            default:
                System.out.println("Write a number 1-4.");
                editOption();
        }

    }
    public void delete() throws IOException {
        System.out.println("Are you sure you want delete this? Y/N" );
        if(isFridge()){
            this.showActiveFridge();
        }else{
            this.showActiveShoppingList();
        }
        String isYes= reader.readLine().toLowerCase(Locale.ROOT);

        if(isYes.equals("y")||isYes.equals("yes")){
            if(isFridge()) {
                fridges.remove(activeFridge);
                shopFridge.setMyFridges(fridges);
            }else{
                shoppingLists.remove(activeShoppingList);
                shopFridge.setMyShoppingLists(shoppingLists);
            }
        }

    }
    public void editName() throws IOException {
        if(isFridge){
            Fridge preEditFridge=activeFridge;
            this.showActiveFridge();
            System.out.println("Write new name:");
            String newName=reader.readLine();
            activeFridge.setName(newName);
            fridges.remove(preEditFridge);
            fridges.add(activeFridge);
            shopFridge.setMyFridges(fridges);

        }else{
            ShoppingList preEditList=activeShoppingList;
            this.showActiveShoppingList();
            System.out.println("Write new name:");
            String newName=reader.readLine();
            activeShoppingList.setName(newName);
            shoppingLists.remove(preEditList);
            shoppingLists.add(activeShoppingList);
            shopFridge.setMyShoppingLists(shoppingLists);

        }


    }

    public void nameChanger(String newName){

    }






    public void setShopFridge(ShopFridge shopFridge) {
        this.shopFridge = shopFridge;
    }
    public boolean isFridge() {
        return isFridge;
    }

    public void setFridgeFlag(boolean fridge) {
        isFridge = fridge;
    }
    public Fridge getActiveFridge() {
        return activeFridge;
    }

    public void setActiveFridge(Fridge activeFridge) {
        this.activeFridge = activeFridge;
    }

    public ShoppingList getActiveShoppingList() {
        return activeShoppingList;
    }

    public void setActiveShoppingList(ShoppingList activeShoppingList) {
        this.activeShoppingList = activeShoppingList;
    }

    public List<Fridge> getFridges() {
        return fridges;
    }

    public void setFridges(ArrayList<Fridge> fridges) {
        this.fridges = fridges;
    }

    public List<ShoppingList> getShoppingLists() {
        return shoppingLists;
    }

    public void setShoppingLists(ArrayList<ShoppingList> shoppingLists) {
        this.shoppingLists = shoppingLists;
    }

}
