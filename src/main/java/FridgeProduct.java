class FridgeProduct extends Product {
    private int min = 0;
    private int max = 99;

    public FridgeProduct(String name, int quantity) {
        super(name, quantity);
    }

    public void changeMin(int min) {
        this.min = min;
    }

    public void changeMax(int max) {
        this.max = max;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }
}
