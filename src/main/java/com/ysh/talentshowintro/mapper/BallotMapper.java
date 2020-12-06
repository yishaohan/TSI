package com.ysh.talentshowintro.mapper;

import com.ysh.talentshowintro.model.Ballot;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.sql.Timestamp;
import java.util.List;

@Mapper
public interface BallotMapper {
//    @Select("Select email from emails")
//    List<String> getAllEmails();

    @Select("select * from ballots where voter=#{voter}")
    List<Ballot> getBallotsByEmail(String voter);

    @Insert("insert into ballots(voter, candidate, createDateTime) values (#{voter}, #{candidate}, #{createDateTime})")
    void save(Ballot ballot);

    @Select("select count(*) from ballots")
    int getNumbers();
}

