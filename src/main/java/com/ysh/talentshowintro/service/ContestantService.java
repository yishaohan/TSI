package com.ysh.talentshowintro.service;

import com.ysh.talentshowintro.mapper.ContestantMapper;
import com.ysh.talentshowintro.model.Contestant;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContestantService {
    final ContestantMapper contestantMapper;

    public ContestantService(ContestantMapper contestantMapper) {
        this.contestantMapper = contestantMapper;
    }

    public void voteByName(String name) {
        contestantMapper.voteByName(name);
    }

    public List<Contestant> getContestants() {
        return contestantMapper.getContestants();
    }

    public int getNumOfBallots() {
        return contestantMapper.getNumOfBallots();
    }
}

