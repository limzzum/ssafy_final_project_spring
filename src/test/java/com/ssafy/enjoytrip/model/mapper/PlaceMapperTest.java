package com.ssafy.enjoytrip.model.mapper;

import com.ssafy.enjoytrip.model.dto.Place;
import com.ssafy.enjoytrip.model.dto.SearchPlace;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Transactional
class PlaceMapperTest {

    @Autowired
    PlaceMapper mapper;

    @Test
    void selectAll() {
        SearchPlace search = SearchPlace.builder().type(12).sido(6).keyword("해운대").build();
        List<Place> places = mapper.selectAll(search);
        int max = Math.min(10, places.size());
        for (int i = 0; i < max; i++) {
            assertEquals(places.get(i).getSido(), 6);
            assertEquals(places.get(i).getType(), 12);
        }
    }

    @Test
    void select() {
        Place select = mapper.select(125266);
        assertNotNull(select);
        assertEquals(select.getType(), 12);
        assertEquals(select.getSido(), 32);
    }
}