package com.ysh.talentshowintro.mapper;

import com.ysh.talentshowintro.model.Order;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface OrderMapper {
    @Insert("insert into `order`(orderid, intent, status, createdatetime, updatedatetime, payerid, payergivenname, payersurname, payeremail) values(#{orderID}, #{intent}, #{status}, #{createDateTime}, #{updateDateTime}, #{payerID}, #{payerGivenName}, #{payerSurname}, #{payerEmail});")
    public void save(Order order);

    @Select("select orderID,intent,status,createDateTime,updateDateTime,payerID,payerGivenName,payerSurname,payerEmail form `order`")
    public List<Order> getAllOrder();
}
