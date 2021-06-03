package com.dove.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class NullSerialize extends JsonSerializer<Object> {

    @Override
    public void serialize(Object value, JsonGenerator jgen,
                          SerializerProvider provider) throws IOException
    {
        jgen.writeString("");

    }
}
