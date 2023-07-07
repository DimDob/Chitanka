package com.example.Deserializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.avro.specific.SpecificRecord;
import org.springframework.stereotype.Service;
import schemas.messaging.avro.events.books.added.BookAdded;
import schemas.messaging.avro.events.books.deleted.BookDeleted;
import schemas.messaging.avro.events.books.loaned.BookLoaned;

@Service
@Slf4j
public class Deserializer {

    private final ObjectMapper objectMapper = new ObjectMapper();

    private <T extends SpecificRecord> T deserialize(SpecificRecord event, Class<T> eventType) {
        try {
            JsonNode json = objectMapper.readTree(event.toString());
            return objectMapper.treeToValue(json, eventType);
        } catch (JsonProcessingException e) {
            log.info("Couldn't deserialize object.");
        }
        return null;
    }

    public BookAdded deserializeBookAdded(SpecificRecord event) {
        return deserialize(event, BookAdded.class);
    }

    public BookLoaned deserializeBookModified(SpecificRecord event) {
        return deserialize(event, BookLoaned.class);
    }

    public BookDeleted deserializeBookDeleted(SpecificRecord event) {
        return deserialize(event, BookDeleted.class);
    }
}

