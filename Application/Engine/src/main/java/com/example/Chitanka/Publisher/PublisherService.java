package com.example.Chitanka.Publisher;

import org.apache.avro.specific.SpecificRecord;

public interface PublisherService {

    void publish(SpecificRecord event);
}
