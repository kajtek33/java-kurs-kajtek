package org.example.product;

public class Electronics extends Product{
    private final int warrantyMonths;

    public Electronics(String sku, String name, double netPrice, int warrantyMonths) {
        super(sku, name, netPrice);

        this.warrantyMonths = warrantyMonths;
    }

    public int getWarrantyMonths() {
        return warrantyMonths;
    }

    @Override
    public String getCategory(){
        return "ELEKTRONIKA";
    }

    @Override
    public double getShippingCost() {
        if (getNetPrice() > 2000) {
            return 0;
        }
        return 19.99;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " (GWARANCJA " + warrantyMonths + " MIES.)";
    }
}
