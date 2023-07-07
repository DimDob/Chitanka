package com.example.Service;

import org.apache.avro.specific.SpecificRecord;

import java.util.Optional;

public interface NotificationService {

    Optional<String> sendGreeting(SpecificRecord event);
}
