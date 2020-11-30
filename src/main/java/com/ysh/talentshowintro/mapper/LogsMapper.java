package com.ysh.talentshowintro.mapper;

import com.ysh.talentshowintro.model.Logs;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LogsMapper {

    @Insert("insert into logs(requestURI, queryString, method, status, message, referer, createDateTime, thread, userName, remoteAddr, remotePort, userAgent, acceptLanguage, sessionId, contentType, consumeTime, source) values(#{requestURI}, #{queryString}, #{method}, #{status}, #{message}, #{referer}, #{createDateTime}, #{thread}, #{userName}, #{remoteAddr}, #{remotePort}, #{userAgent}, #{acceptLanguage}, #{sessionId}, #{contentType}, #{consumeTime}, #{source})")
    void save(Logs logs);
}
