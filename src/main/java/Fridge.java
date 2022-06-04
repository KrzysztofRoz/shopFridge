import java.util.ArrayList;

class Fridge {
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
        for (int i = 0; i < list.size(); i++) {
            contents.add(list.get(i));
        }
    }

}
