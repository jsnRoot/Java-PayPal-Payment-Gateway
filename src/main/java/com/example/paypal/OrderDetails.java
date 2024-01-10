package com.example.paypal;

public class OrderDetails {
    private String producName;
    private float subTotal;
    private float shipping;
    private float tax;
    private float total;

    public OrderDetails(String producName, String subTotal, String shipping, String tax, String total) {
        this.producName = producName;
        this.subTotal = Float.parseFloat(subTotal);
        this.shipping = Float.parseFloat(shipping);
        this.tax = Float.parseFloat(tax);
        this.total = Float.parseFloat(total);
    }

    public String getProducName() {
        return producName;
    }

    public String getSubTotal() {
        return String.format("%.2f",subTotal);
    }

    public String getShipping() {
        return String.format("%.2f",shipping);
    }

    public String getTax() {
        return String.format("%.2f",tax);
    }

    public String getTotal() {
        return String.format("%.2f",total);
    }
}
