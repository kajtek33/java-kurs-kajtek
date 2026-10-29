package org.example.product;

public class DigitalProduct extends Product{
    private final String downloadUrl;

    public DigitalProduct(String sku, String name, double netPrice, String downloadUrl) {
        super(sku, name, netPrice);

        this.downloadUrl = downloadUrl;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }
    @Override
    public String getCategory(){
        return "CYFROWA";
    }

    @Override
    public double getShippingCost() {
        return 0;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " (PLIK DO POBRANIA)";
    }
}
