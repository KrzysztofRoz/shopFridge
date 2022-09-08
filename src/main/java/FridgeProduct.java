class FridgeProduct extends Product {
    private int min = 0;
    private int max = 99;

    public FridgeProduct(String name, int quantity) {
        super(name, quantity);
    }

    public FridgeProduct(String name){super(name);}

    public void setMin(int min) {
        this.min = min;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

    @Override
    public String toString() {
        return super.toString()+
                ", min=" + min +
                ", max=" + max;
    }
}
