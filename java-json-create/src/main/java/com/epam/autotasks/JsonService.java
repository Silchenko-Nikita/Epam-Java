package com.epam.autotasks;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

public class JsonService {

    @SneakyThrows
    public void createAnimalJson(String filePath, List<Animal> animals) {
        ObjectMapper objectMapper = new ObjectMapper();

        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(filePath), animals);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
