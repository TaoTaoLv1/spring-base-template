package com.dokang.lib.jsonLib.module;

import com.dokang.lib.jsonLib.serialize.DateDeserializer;
import com.dokang.lib.jsonLib.serialize.DateSerializer;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.util.Date;

public class DateModule extends SimpleModule {

    private static final long serialVersionUID = 1L;

    public DateModule() {
        super();
        addSerializer(Date.class, new DateSerializer());
        addDeserializer(Date.class, new DateDeserializer());
    }
}