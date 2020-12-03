package com.ysh.talentshowintro.mapper;

import com.ysh.talentshowintro.model.CustomOrder;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CustomOrderMapper {
    @Insert("insert into `order`(orderID, intent, orderStatus, updateDateTime, quantity, amountValue, amountCurrencyCode, payerID, payerFullName, payerGivenName, payerSurname, payerPhoneType, payerPhoneNumber, payerEmail, payerCountryCode, payerPostalCode, payerAdminArea1, payerAdminArea2, payerAdminArea3, payerAdminArea4, payerAddressLine1, payerAddressLine2, payerAddressLine3, payeeClientID, payeeEmail, payeeMerchantID, payeeDisplayBrandName, payeeDisplayBusinessPhone, payeeDisplayEmail) values (#{orderID}, #{intent}, #{orderStatus}, #{updateDateTime}, #{quantity}, #{amountValue}, #{amountCurrencyCode}, #{payerID},#{payerFullName}, #{payerGivenName}, #{payerSurname}, #{payerPhoneType}, #{payerPhoneNumber}, #{payerEmail},#{payerCountryCode}, #{payerPostalCode}, #{payerAdminArea1}, #{payerAdminArea2}, #{payerAdminArea3},#{payerAdminArea4}, #{payerAddressLine1}, #{payerAddressLine2}, #{payerAddressLine3}, #{payeeClientID}, #{payeeEmail},#{payeeMerchantID}, #{payeeDisplayBrandName}, #{payeeDisplayBusinessPhone}, #{payeeDisplayEmail})")
    public void save(CustomOrder customOrder);

    @Select("select id,orderID, intent, orderStatus, updateDateTime, quantity, amountValue, amountCurrencyCode, payerID, payerFullName, payerGivenName, payerSurname, payerPhoneType, payerPhoneNumber, payerEmail, payerCountryCode, payerPostalCode, payerAdminArea1, payerAdminArea2, payerAdminArea3, payerAdminArea4, payerAddressLine1, payerAddressLine2, payerAddressLine3, payeeClientID, payeeEmail, payeeMerchantID, payeeDisplayBrandName, payeeDisplayBusinessPhone, payeeDisplayEmail from `order`")
    public List<CustomOrder> getAllOrder();
}
