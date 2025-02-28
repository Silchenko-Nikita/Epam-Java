package edu.epam.fop.json.warehouse;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import edu.epam.fop.json.warehouse.item.Item;

import java.io.InputStream;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class WarehouseReaderImpl implements WarehouseReader {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.registerModule(new JavaTimeModule());
    }

    @Override
    public Collection<Item> readItems(InputStream data) {
        try {
            List<Item> items = objectMapper.readValue(data, new TypeReference<List<Item>>() {});

            return items.stream()
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Failed to read items from InputStream", e);
        }
    }
}
