import java.util.ArrayList;
import java.util.Objects;

class ShoppingList {
    private String name;
    private long id;
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
        shoppingList.addAll(list);
    }

    public int indexOfProduct(String productName){
        for(Product product : shoppingList) {
            if (product.getName().equals(productName))
                return shoppingList.indexOf(product);
        }
        return -1;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShoppingList that = (ShoppingList) o;
        return Objects.equals(name, that.name) && shoppingList.equals(that.shoppingList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(shoppingList);
    }

    @Override
    public String toString() {
        return name + '\'' +
                ", shoppingList=" + shoppingList ;
    }
}
