import java.util.ArrayList;

class Fridge {
    private String name;
    private long id;
    private ArrayList<FridgeProduct> contents = new ArrayList<>();

    public void addToFridge(FridgeProduct product) {
        contents.add(product);
    }

    public void remove(FridgeProduct product) {
        contents.remove(product);
    }

    public ArrayList<FridgeProduct> getContents() {
        return contents;
    }

    public void addToFridge(ArrayList<FridgeProduct> list) {
        contents.addAll(list);
    }

    public int indexOfProduct(String name){
        for(FridgeProduct product : contents) {
            if (product.getName().equals(name))
                return contents.indexOf(product);
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
    public String toString() {
        return "Fridge{" +
                "name='" + name + '\'' +
                ", contents=" + contents +
                '}';
    }
}
