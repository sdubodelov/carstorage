package com.sdubadzelau.carstorage.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sdubadzelau.carstorage.model.datamuse.SimilarWord;

import java.util.Collections;
import java.util.List;

public class JSONParser {

    public static List<SimilarWord> parseSimilarWords(String source) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(source, new TypeReference<List<SimilarWord>>() {
            });
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
}
