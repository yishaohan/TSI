package com.ysh.talentshowintro.service;

import com.ysh.talentshowintro.mapper.CandidateMapper;
import com.ysh.talentshowintro.model.Candidate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CandidateService {

    final CandidateMapper candidateMapper;

    public CandidateService(CandidateMapper candidateMapper) {
        this.candidateMapper = candidateMapper;
    }

    public List<Candidate> getAllCandidates() {
        return candidateMapper.getAllCandidates();
    }

    public void vote(Candidate candidate) {
        candidateMapper.vote(candidate);
    }

    public Candidate getCandidateByName(String name) {
        return candidateMapper.getCandidateByName(name);
    }
}
