package com.bastienvh.eurder.unittests;

import com.bastienvh.eurder.domain.item.ItemMapper;
import com.bastienvh.eurder.repository.ItemRepository;
import com.bastienvh.eurder.service.ItemService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static io.restassured.RestAssured.when;

@ExtendWith(MockitoExtension.class)
class ItemServiceTest {
    @Mock
    ItemMapper mapper;
    @Mock
    ItemRepository repository;
    @InjectMocks
    ItemService service;

    @Test
    void addItem_givenAValidItemDTO_thenMapToDTOAndStoreInRepository() {
        //GIVEN

        //WHEN

        //THEN
    }
}