import java.util.ArrayList;

public class ShoppingListEditor {


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






}
