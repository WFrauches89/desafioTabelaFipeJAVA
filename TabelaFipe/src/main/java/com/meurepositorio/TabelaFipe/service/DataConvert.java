package com.meurepositorio.TabelaFipe.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.util.List;

public class DataConvert implements IDataConvert{

    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public <T> T getDatas(String json, Class<T> classNew) {
        try {
            return mapper.readValue(json, classNew);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T> List<T> getList(String json, Class<T> classNew) {
        CollectionType list = mapper.getTypeFactory()
                .constructCollectionType(List.class, classNew);
        try {
            return mapper.readValue(json, list);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
