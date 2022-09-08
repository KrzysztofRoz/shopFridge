import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

class Product{
    private String name;
    private int quantity;

    public Product(String name, int quantity){
        this.name=name;
        this.quantity=Math.max(quantity, 0);
    }
    public Product(String name){
        this.name=name;
        this.quantity=0;
    }


    public void setProduct(String name,int quantity){

        this.name=name;
        this.quantity = Math.max(quantity, 0);
    }

    public void setQuantity(int quantity){this.quantity=quantity;}

    public int getQuantity() {
        return quantity;
    }
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return  name + '\'' +
                ", quantity=" + quantity;
    }
}
