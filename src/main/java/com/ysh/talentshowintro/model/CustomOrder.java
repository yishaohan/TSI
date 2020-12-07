package com.ysh.talentshowintro.model;

import java.sql.Timestamp;

public class CustomOrder {
    private int id;
    private String orderID;
    private String orderType;
    private String intent;
    /**
     * CREATED. The order was created with the specified context.
     * // SAVED. The order was saved and persisted. The order status continues to be in progress until a capture is made with final_capture = true for all purchase units within the order.
     * // APPROVED. The customer approved the payment through the PayPal wallet or another form of guest or unbranded payment. For example, a card, bank account, or so on.
     * // VOIDED. All purchase units in the order are voided.
     * // COMPLETED. The payment was authorized or the authorized payment was captured for the order.
     * // PAYER_ACTION_REQUIRED. The order requires an action from the payer (e.g. 3DS authentication). Redirect the payer to the "rel":"payer-action" HATEOAS link returned as part of the response prior to authorizing or capturing the order.
     * // Read only.
     */
    private String orderStatus;
    private Timestamp updateDateTime;
    private int quantity;
    private String amountValue;
    private String amountCurrencyCode;
    private String description;
    private String payerID;
    private String payerFullName;
    private String payerGivenName;
    private String payerSurname;
    private String payerPhoneType;
    private String payerPhoneNumber;
    private String payerEmail;
    private String payerCountryCode;
    private String payerPostalCode;
    private String payerAdminArea1;
    private String payerAdminArea2;
    private String payerAdminArea3;
    private String payerAdminArea4;
    private String payerAddressLine1;
    private String payerAddressLine2;
    private String payerAddressLine3;
    private String payeeClientID;
    private String payeeEmail;
    private String payeeMerchantID;
    private String payeeDisplayBrandName;
    private String payeeDisplayBusinessPhone;
    private String payeeDisplayEmail;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

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

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Timestamp getUpdateDateTime() {
        return updateDateTime;
    }

    public void setUpdateDateTime(Timestamp updateDateTime) {
        this.updateDateTime = updateDateTime;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getAmountValue() {
        return amountValue;
    }

    public void setAmountValue(String amountValue) {
        this.amountValue = amountValue;
    }

    public String getAmountCurrencyCode() {
        return amountCurrencyCode;
    }

    public void setAmountCurrencyCode(String amountCurrencyCode) {
        this.amountCurrencyCode = amountCurrencyCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPayerID() {
        return payerID;
    }

    public void setPayerID(String payerID) {
        this.payerID = payerID;
    }

    public String getPayerFullName() {
        return payerFullName;
    }

    public void setPayerFullName(String payerFullName) {
        this.payerFullName = payerFullName;
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

    public String getPayerPhoneType() {
        return payerPhoneType;
    }

    public void setPayerPhoneType(String payerPhoneType) {
        this.payerPhoneType = payerPhoneType;
    }

    public String getPayerPhoneNumber() {
        return payerPhoneNumber;
    }

    public void setPayerPhoneNumber(String payerPhoneNumber) {
        this.payerPhoneNumber = payerPhoneNumber;
    }

    public String getPayerEmail() {
        return payerEmail;
    }

    public void setPayerEmail(String payerEmail) {
        this.payerEmail = payerEmail;
    }

    public String getPayerCountryCode() {
        return payerCountryCode;
    }

    public void setPayerCountryCode(String payerCountryCode) {
        this.payerCountryCode = payerCountryCode;
    }

    public String getPayerPostalCode() {
        return payerPostalCode;
    }

    public void setPayerPostalCode(String payerPostalCode) {
        this.payerPostalCode = payerPostalCode;
    }

    public String getPayerAdminArea1() {
        return payerAdminArea1;
    }

    public void setPayerAdminArea1(String payerAdminArea1) {
        this.payerAdminArea1 = payerAdminArea1;
    }

    public String getPayerAdminArea2() {
        return payerAdminArea2;
    }

    public void setPayerAdminArea2(String payerAdminArea2) {
        this.payerAdminArea2 = payerAdminArea2;
    }

    public String getPayerAdminArea3() {
        return payerAdminArea3;
    }

    public void setPayerAdminArea3(String payerAdminArea3) {
        this.payerAdminArea3 = payerAdminArea3;
    }

    public String getPayerAdminArea4() {
        return payerAdminArea4;
    }

    public void setPayerAdminArea4(String payerAdminArea4) {
        this.payerAdminArea4 = payerAdminArea4;
    }

    public String getPayerAddressLine1() {
        return payerAddressLine1;
    }

    public void setPayerAddressLine1(String payerAddressLine1) {
        this.payerAddressLine1 = payerAddressLine1;
    }

    public String getPayerAddressLine2() {
        return payerAddressLine2;
    }

    public void setPayerAddressLine2(String payerAddressLine2) {
        this.payerAddressLine2 = payerAddressLine2;
    }

    public String getPayerAddressLine3() {
        return payerAddressLine3;
    }

    public void setPayerAddressLine3(String payerAddressLine3) {
        this.payerAddressLine3 = payerAddressLine3;
    }

    public String getPayeeClientID() {
        return payeeClientID;
    }

    public void setPayeeClientID(String payeeClientID) {
        this.payeeClientID = payeeClientID;
    }

    public String getPayeeEmail() {
        return payeeEmail;
    }

    public void setPayeeEmail(String payeeEmail) {
        this.payeeEmail = payeeEmail;
    }

    public String getPayeeMerchantID() {
        return payeeMerchantID;
    }

    public void setPayeeMerchantID(String payeeMerchantID) {
        this.payeeMerchantID = payeeMerchantID;
    }

    public String getPayeeDisplayBrandName() {
        return payeeDisplayBrandName;
    }

    public void setPayeeDisplayBrandName(String payeeDisplayBrandName) {
        this.payeeDisplayBrandName = payeeDisplayBrandName;
    }

    public String getPayeeDisplayBusinessPhone() {
        return payeeDisplayBusinessPhone;
    }

    public void setPayeeDisplayBusinessPhone(String payeeDisplayBusinessPhone) {
        this.payeeDisplayBusinessPhone = payeeDisplayBusinessPhone;
    }

    public String getPayeeDisplayEmail() {
        return payeeDisplayEmail;
    }

    public void setPayeeDisplayEmail(String payeeDisplayEmail) {
        this.payeeDisplayEmail = payeeDisplayEmail;
    }

    @Override
    public String toString() {
        return "CustomOrder{" +
                "id=" + id +
                ", orderID='" + orderID + '\'' +
                ", orderType='" + orderType + '\'' +
                ", intent='" + intent + '\'' +
                ", orderStatus='" + orderStatus + '\'' +
                ", updateDateTime=" + updateDateTime +
                ", quantity=" + quantity +
                ", amountValue='" + amountValue + '\'' +
                ", amountCurrencyCode='" + amountCurrencyCode + '\'' +
                ", description='" + description + '\'' +
                ", payerID='" + payerID + '\'' +
                ", payerFullName='" + payerFullName + '\'' +
                ", payerGivenName='" + payerGivenName + '\'' +
                ", payerSurname='" + payerSurname + '\'' +
                ", payerPhoneType='" + payerPhoneType + '\'' +
                ", payerPhoneNumber='" + payerPhoneNumber + '\'' +
                ", payerEmail='" + payerEmail + '\'' +
                ", payerCountryCode='" + payerCountryCode + '\'' +
                ", payerPostalCode='" + payerPostalCode + '\'' +
                ", payerAdminArea1='" + payerAdminArea1 + '\'' +
                ", payerAdminArea2='" + payerAdminArea2 + '\'' +
                ", payerAdminArea3='" + payerAdminArea3 + '\'' +
                ", payerAdminArea4='" + payerAdminArea4 + '\'' +
                ", payerAddressLine1='" + payerAddressLine1 + '\'' +
                ", payerAddressLine2='" + payerAddressLine2 + '\'' +
                ", payerAddressLine3='" + payerAddressLine3 + '\'' +
                ", payeeClientID='" + payeeClientID + '\'' +
                ", payeeEmail='" + payeeEmail + '\'' +
                ", payeeMerchantID='" + payeeMerchantID + '\'' +
                ", payeeDisplayBrandName='" + payeeDisplayBrandName + '\'' +
                ", payeeDisplayBusinessPhone='" + payeeDisplayBusinessPhone + '\'' +
                ", payeeDisplayEmail='" + payeeDisplayEmail + '\'' +
                '}';
    }
}
