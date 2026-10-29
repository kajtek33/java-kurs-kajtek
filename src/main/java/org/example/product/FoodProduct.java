package org.example.product;

public class FoodProduct extends Product {
   private final String expiryDate;

    public FoodProduct(String sku, String name, double netPrice, String expiryDate) {
       super(sku, name, netPrice);

       this.expiryDate = expiryDate;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    @Override
    public String getCategory(){
        return "JEDZENIE";
    }

    @Override
    public double getShippingCost() {
        return 29.99;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " (TERMIN: " + getExpiryDate() + ")";
    }
}
