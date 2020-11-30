package com.ysh.talentshowintro.model;

import java.sql.Timestamp;

public class Order {
    private String orderID;
    private String intent;
    // CREATED. The order was created with the specified context.
    // SAVED. The order was saved and persisted. The order status continues to be in progress until a capture is made with final_capture = true for all purchase units within the order.
    // APPROVED. The customer approved the payment through the PayPal wallet or another form of guest or unbranded payment. For example, a card, bank account, or so on.
    // VOIDED. All purchase units in the order are voided.
    // COMPLETED. The payment was authorized or the authorized payment was captured for the order.
    // PAYER_ACTION_REQUIRED. The order requires an action from the payer (e.g. 3DS authentication). Redirect the payer to the "rel":"payer-action" HATEOAS link returned as part of the response prior to authorizing or capturing the order.
    // Read only.
    private String status;
    private Timestamp createDateTime;
    private Timestamp updateDateTime;
    private String payerID;
    private String payerGivenName;
    private String payerSurname;
    private String payerEmail;

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getIntent() {
        return intent;
    }

    public void setIntent(String intent) {
        this.intent = intent;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(Timestamp createDateTime) {
        this.createDateTime = createDateTime;
    }

    public Timestamp getUpdateDateTime() {
        return updateDateTime;
    }

    public void setUpdateDateTime(Timestamp updateDateTime) {
        this.updateDateTime = updateDateTime;
    }

    public String getPayerID() {
        return payerID;
    }

    public void setPayerID(String payerID) {
        this.payerID = payerID;
    }

    public String getPayerGivenName() {
        return payerGivenName;
    }

    public void setPayerGivenName(String payerGivenName) {
        this.payerGivenName = payerGivenName;
    }

    public String getPayerSurname() {
        return payerSurname;
    }

    public void setPayerSurname(String payerSurname) {
        this.payerSurname = payerSurname;
    }

    public String getPayerEmail() {
        return payerEmail;
    }

    public void setPayerEmail(String payerEmail) {
        this.payerEmail = payerEmail;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderID='" + orderID + '\'' +
                ", intent='" + intent + '\'' +
                ", status='" + status + '\'' +
                ", createDateTime='" + createDateTime + '\'' +
                ", updateDateTime='" + updateDateTime + '\'' +
                ", payerID='" + payerID + '\'' +
                ", payerGivenName='" + payerGivenName + '\'' +
                ", payerSurname='" + payerSurname + '\'' +
                ", payerEmail='" + payerEmail + '\'' +
                '}';
    }
}
