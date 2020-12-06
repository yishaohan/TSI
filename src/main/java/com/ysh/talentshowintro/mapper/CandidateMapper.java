package com.ysh.talentshowintro.mapper;

import com.ysh.talentshowintro.model.Candidate;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface CandidateMapper {

    @Select("select * from candidate")
    public List<Candidate> getAllCandidates();

    @Update("update candidate set votes=votes+1 where name=#{name}")
    public void vote(Candidate candidate);

    @Select("select * from candidate where name=#{name}")
    public Candidate getCandidateByName(String name);
}
