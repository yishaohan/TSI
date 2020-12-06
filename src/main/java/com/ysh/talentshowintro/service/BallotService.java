package com.ysh.talentshowintro.service;

import com.ysh.talentshowintro.mapper.BallotMapper;
import com.ysh.talentshowintro.model.Ballot;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BallotService {
    final BallotMapper ballotMapper;

    public BallotService(BallotMapper ballotMapper) {
        this.ballotMapper = ballotMapper;
    }

//    public List<String> getAllEmails() {
//        return votedMapper.getAllEmails();
//    }

    public List<Ballot> getBallotsByEmail(String email) {
        return ballotMapper.getBallotsByEmail(email);
    }

    public void saveBallot(Ballot ballot) {
        ballotMapper.save(ballot);
    }

    public int getAllBallotsNumbers() {
        return ballotMapper.getNumbers();
    }
}

