package com.example.paypal;

import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;

import java.util.ArrayList;
import java.util.List;

public class PaymentServices {
    private static final String CLIENT_ID = "ATxUgiB7ARGkfLd0WsntV1hWv372a-TbPshI78Wu0kJh-DsLDsoqUxDBR9av1IVSXLBhh-HEt01fHRTY";
    private static final String CLIENT_SECRET = "EHaaUsR6JBJROCVl9lOJAMtadnPdWzrjzIB1ybuBz3v5bCEn0da0sotDYg11CW-BDd84X8N62_HmdxjV";
    private static final String MODE = "sandbox";

    public  String authorizePayment(OrderDetails orderDetails) throws PayPalRESTException {
        Payer payer = getPayerInformation();
        RedirectUrls redirectUrls = getRedirectURLs();
        List<Transaction> listTransaction = getTransactionInformation(orderDetails);
        Payment requestPayment = new Payment();
        requestPayment.setTransactions(listTransaction).setRedirectUrls(redirectUrls).setPayer(payer).setIntent("authorize");
        APIContext apiContext = new APIContext(CLIENT_ID, CLIENT_SECRET, MODE);

        Payment approvedPayment =  requestPayment.create(apiContext);

        System.out.println(approvedPayment);
        return getApprovalLink(approvedPayment);

    }

    private String getApprovalLink(Payment approvedPayment){
        List<Links> links = approvedPayment.getLinks();
        String approvalLink = null;
        for(Links link : links){
            if(link.getRel().equalsIgnoreCase("approval_url")){
                approvalLink = link.getHref();
            }
        }

        return  approvalLink;
    }

    private List<Transaction> getTransactionInformation(OrderDetails orderDetails ){
        Details details = new Details();
        details.setShipping(orderDetails.getShipping());
        details.setSubtotal(orderDetails.getSubTotal());
        details.setTax(orderDetails.getTax());

        Amount amount = new Amount();
        amount.setCurrency("USD");
        amount.setTotal(orderDetails.getTotal());
        amount.setDetails(details);

        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setDescription(orderDetails.getProducName());

        ItemList itemList = new ItemList();
        List<Item> items = new ArrayList<Item>();

        Item item = new Item();
        item.setCurrency("USD").setDescription(orderDetails.getProducName()).setPrice(orderDetails.getSubTotal()).setTax(orderDetails.getTax()).setQuantity("1");

        items.add(item);
        itemList.setItems(items);
        transaction.setItemList(itemList);

        List<Transaction> listTransaction = new ArrayList<Transaction>();
        listTransaction.add(transaction);

        return listTransaction;
    }

    private RedirectUrls  getRedirectURLs() {
        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl("http://localhost:8080/cansel.html");
        redirectUrls.setReturnUrl("http://localhost:8080/review_payment");

        return redirectUrls;
    }

    private Payer getPayerInformation() {
        Payer payer = new Payer();
        payer.setPaymentMethod("paypal");

        PayerInfo payerInfo = new PayerInfo();
        payerInfo.setFirstName("yasiru").setLastName("perera").setEmail("yasiruperera@gmail.com");
        payer.setPayerInfo(payerInfo);

        return payer;
    }

    public Payment getPaymentDetails(String paymentId) throws PayPalRESTException {
        APIContext apiContext = new APIContext(CLIENT_ID, CLIENT_SECRET, MODE);
        return Payment.get(apiContext, paymentId);
    }

    public Payment executePayment(String paymentId, String payerId) throws PayPalRESTException {
        PaymentExecution paymentExecution = new PaymentExecution();
        paymentExecution.setPayerId(payerId);

        Payment payment = new Payment().setId(paymentId);
        APIContext apiContext = new APIContext(CLIENT_ID, CLIENT_SECRET, MODE);

        return payment.execute(apiContext, paymentExecution);
    }


}
