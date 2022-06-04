import java.util.ArrayList;

class ShoppingList {
    private ArrayList<Product> shoppingList = new ArrayList<>();

    public void addToShoppingList(Product product) {
        shoppingList.add(product);
    }

    public void remove(Product product) {
        shoppingList.remove(product);
    }

    public ArrayList<Product> getShoppingList() {
        return shoppingList;
    }

    public void addToShoppingList(ArrayList<Product> list) {
        for (int i = 0; i < list.size(); i++) {
            shoppingList.add(list.get(i));
        }
    }

}
