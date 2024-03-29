package com.ysh.talentshowintro.mapper;

import com.ysh.talentshowintro.model.CustomOrder;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface CustomOrderMapper {
    @Insert("insert into `order`(orderID, orderType, intent, orderStatus, updateDateTime, quantity, amountValue, amountCurrencyCode, description, payerID, payerFullName, payerGivenName, payerSurname, payerPhoneType, payerPhoneNumber, payerEmail, payerCountryCode, payerPostalCode, payerAdminArea1, payerAdminArea2, payerAdminArea3, payerAdminArea4, payerAddressLine1, payerAddressLine2, payerAddressLine3, payeeClientID, payeeEmail, payeeMerchantID, payeeDisplayBrandName, payeeDisplayBusinessPhone, payeeDisplayEmail) values (#{orderID}, #{orderType}, #{intent}, #{orderStatus}, #{updateDateTime}, #{quantity}, #{amountValue}, #{amountCurrencyCode}, #{description}, #{payerID},#{payerFullName}, #{payerGivenName}, #{payerSurname}, #{payerPhoneType}, #{payerPhoneNumber}, #{payerEmail},#{payerCountryCode}, #{payerPostalCode}, #{payerAdminArea1}, #{payerAdminArea2}, #{payerAdminArea3},#{payerAdminArea4}, #{payerAddressLine1}, #{payerAddressLine2}, #{payerAddressLine3}, #{payeeClientID}, #{payeeEmail},#{payeeMerchantID}, #{payeeDisplayBrandName}, #{payeeDisplayBusinessPhone}, #{payeeDisplayEmail})")
    public void save(CustomOrder customOrder);

    @Select("select id,orderID, orderType, intent, orderStatus, updateDateTime, quantity, amountValue, amountCurrencyCode, description, payerID, payerFullName, payerGivenName, payerSurname, payerPhoneType, payerPhoneNumber, payerEmail, payerCountryCode, payerPostalCode, payerAdminArea1, payerAdminArea2, payerAdminArea3, payerAdminArea4, payerAddressLine1, payerAddressLine2, payerAddressLine3, payeeClientID, payeeEmail, payeeMerchantID, payeeDisplayBrandName, payeeDisplayBusinessPhone, payeeDisplayEmail from `order`")
    public List<CustomOrder> getAllOrder();

    @Select("select sum(CAST(amountValue as DECIMAL(7,2))) from `order` group by orderType order by orderType;")
    List<Double> getAmounts();
}
