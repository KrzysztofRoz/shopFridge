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
                     3-to Add Products
                     4-to Edit Product
                     5-to Exit from edit mode""");
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
                if(isFridge){
                    addFridgeProduct();
                }
                else{
                    addProductToShoppingList();
                }
                break;
            case(4):
                selectProduct();
                break;
            case (5):
                break;
            default:
                System.out.println("Write a number 1-4.");
                editOption();
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
    public void addFridgeProduct(){
        try {
            ArrayList<FridgeProduct> newProducts=shopFridge.getCreator().createFridge();
            fridges.get(fridges.indexOf(activeFridge)).addToFridge(newProducts);
            shopFridge.setMyFridges(fridges);
        } catch (IOException e) {
            System.out.println("Enter the name, and correct quantity (non-negative number)");
            addFridgeProduct();
        }
    }
    public void addProductToShoppingList(){
        try {
            ArrayList<Product> newProducts=shopFridge.getCreator().createShoppingList();
            shoppingLists.get(shoppingLists.indexOf(activeShoppingList)).addToShoppingList(newProducts);
        } catch (IOException e ) {
            System.out.println("Enter the name, and correct quantity (non-negative number)");
            addFridgeProduct();
        }
    }

 /// method to select product to edit
    public void selectProduct() {
        if(isFridge()){
            this.showActiveFridge();
            System.out.println("Write name of product you want change:");
            try {
                String selectedProductName= reader.readLine();
                FridgeProduct selectedProduct=activeFridge.getContents().get(activeFridge.indexOfProduct(selectedProductName));
                choseEditOptionFridgeProduct(selectedProduct);
            } catch(IndexOutOfBoundsException e){
                System.out.println("Cannot find product.");
                selectProduct();
            } catch (IOException e) {
                System.out.println("Inappropriate name");
                selectProduct();
            }
        }else{
            this.showActiveShoppingList();
            System.out.println("Write name of product you want change:");
            try {
                String selectedProductName= reader.readLine();
                Product selectedProduct=activeShoppingList.getShoppingList().get(activeShoppingList.indexOfProduct(selectedProductName));
                choseEditOptionProduct(selectedProduct);
            } catch(IndexOutOfBoundsException e){
                System.out.println("Cannot find product.");
                selectProduct();
            } catch (IOException e) {
                System.out.println("Inappropriate name");
                selectProduct();
            }
        }
//Methods to edit products in shopping list change names and quantity

    } public void choseEditOptionProduct(Product selectedProduct){
        System.out.println(selectedProduct);
        System.out.println("""

                    What you want edit
                    Write:
                     1-to Change product name\s
                     2-to Change product quantity\s
                     3-to Remove product from Shopping List\s 
                     4-to Exit from edit mode""");
        try {
            Integer option=Integer.parseInt(reader.readLine());
            switch(option){
                case(1):
                    changeProductName(selectedProduct);
                    break;
                case (2):
                    changeProductQuantity(selectedProduct);
                    break;
                case (3):
                    listProductRemove(selectedProduct);
                    break;
                case (4):
                    break;
                default:
                    System.out.println("Write option number 1-4");
                    choseEditOptionProduct(selectedProduct);
            }
        } catch(NullPointerException | IOException e){
            System.out.println("Please chose a option 1-4 ");
            choseEditOptionProduct(selectedProduct);
        }

    }
    public void changeProductName(Product selectedProduct){
        System.out.println("Write new product name:");
        try {
            String newName= reader.readLine();
            selectedProduct.setProduct(newName, selectedProduct.getQuantity());
        } catch (IOException e) {
            System.out.println("Insert new name.");
            changeProductName(selectedProduct);
        }
    }
    public void changeProductQuantity(Product selectedProduct){
        System.out.println("Write new quantity of " + selectedProduct.getName() +" (must be numerical value):");
        try {
            Integer newQuantity= Integer.parseInt(reader.readLine());
            selectedProduct.setQuantity(newQuantity);
        } catch (IOException e) {
            System.out.println("Insert quantity.");
            changeProductQuantity(selectedProduct);
        }
    }
    public void listProductRemove(Product selectedProduct){
        shoppingLists.get(shoppingLists.indexOf(activeShoppingList)).remove(selectedProduct);
        shopFridge.setMyShoppingLists(shoppingLists);
    }

//Methods to edit fridge products change names,quantity and Min/Max settings
    public void choseEditOptionFridgeProduct(FridgeProduct selectedProduct){
        System.out.println(selectedProduct);
        System.out.println("""

                    What you want edit
                    Write:
                     1-to Change product name\s
                     2-to Change product quantity\s
                     3-to Change min and max
                     4-to Remove product from fridge 
                     5-to Exit from edit mode""");
        try {
            Integer option=Integer.parseInt(reader.readLine());
            switch(option){
                case(1):
                    changeFridgeProductName(selectedProduct);
                    break;
                case (2):
                    changeFridgeProductQuantity(selectedProduct);
                    break;
                case (3):
                    changeMinMax(selectedProduct);
                    break;
                case (4):
                    fridgeProductRemove(selectedProduct);
                    break;
                case (5):
                    break;
                default:
                    System.out.println("Write option number 1-5");
                    choseEditOptionFridgeProduct(selectedProduct);
            }
        } catch(NullPointerException | IOException e){
            System.out.println("Please chose a option 1-5 ");
            choseEditOptionFridgeProduct(selectedProduct);
        }

    }

    ///check if i can use this method after change FridgeProduct to Product by both Fridge and shoppingList
    public void changeFridgeProductName(FridgeProduct selectedProduct){
        System.out.println("Write new product name:");
        try {
            String newName= reader.readLine();
            selectedProduct.setProduct(newName, selectedProduct.getQuantity());
        } catch (IOException e) {
            System.out.println("Insert new name.");
            changeFridgeProductName(selectedProduct);
        }
    }
    public void changeFridgeProductQuantity(FridgeProduct selectedProduct){
        System.out.println("Write new quantity of " + selectedProduct.getName() +" (must be numerical value):");
        try {
            Integer newQuantity= Integer.parseInt(reader.readLine());
            selectedProduct.setQuantity(newQuantity);
        } catch (IOException e) {
            System.out.println("Insert quantity.");
            changeFridgeProductQuantity(selectedProduct);
        }
    }
    public void changeMinMax(FridgeProduct selectedProduct){
        System.out.println("Write new minimal quantity of " + selectedProduct.getName() +" (must be non-negative numerical value):");
        try {
            Integer newMin= Integer.parseInt(reader.readLine());
            selectedProduct.setMin(newMin);
            System.out.println("Write new maximal quantity of " + selectedProduct.getName() +" (must be non-negative numerical value, greater than min value):");
            selectedProduct.setMax(Integer.parseInt(reader.readLine()));
        } catch (IOException e) {
            System.out.println("Insert correct value. Minimal value must be non-negative number. Maximal value must be greater than minimal");
            changeMinMax(selectedProduct);
        }

    }
    public void fridgeProductRemove(FridgeProduct selectedProduct){
        fridges.get(fridges.indexOf(activeFridge)).remove(selectedProduct);
        shopFridge.setMyFridges(fridges);
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
