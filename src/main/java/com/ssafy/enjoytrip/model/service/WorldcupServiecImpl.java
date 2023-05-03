package com.ssafy.enjoytrip.model.service;

import com.ssafy.enjoytrip.model.dto.Worldcup;
import com.ssafy.enjoytrip.model.mapper.WorldcupMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorldcupServiecImpl implements WorldcupService{

    private WorldcupMapper mapper;

    @Autowired
    public WorldcupServiecImpl(WorldcupMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public List<Worldcup> getWorldcupList() {
        return mapper.selectAll();
    }

    @Override
    public Worldcup getWorldcup(int id) {
        return mapper.select(id);
    }

    @Override
    public int updateWorldcupWins(int id) {
        return mapper.update(id);
    }
}
