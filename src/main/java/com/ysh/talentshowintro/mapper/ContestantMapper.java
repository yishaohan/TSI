package com.ysh.talentshowintro.mapper;

import com.ysh.talentshowintro.model.Contestant;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface ContestantMapper {
    @Update("update impact.contestant set ballotCount=ballotCount+1 where name=#{name}")
    void voteByName(String name);

    @Select("select * from contestant order by ballotCount desc")
    List<Contestant> getContestants();

    @Select("select sum(ballotCount) from contestant")
    int getNumOfBallots();
}

