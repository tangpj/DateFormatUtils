package com.tangpj.dateformatutils;

import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

import java.io.IOException;

/**
 * @className: DateTimeTypeAdapter
 * @author create by Tang
 * @date 2017/5/5 上午10:12
 * @description: 
 */

public class DateTimeTypeAdapter extends TypeAdapter<DateTime>{

    private final DateTimeFormatter dateTimeFormatter;

    public DateTimeTypeAdapter(){
        this(ISODateTimeFormat.dateTimeNoMillis());
    }

    public DateTimeTypeAdapter(DateTimeFormatter dateTimeFormatter){
        this.dateTimeFormatter = dateTimeFormatter;
    }

    @Override
    public DateTime read(JsonReader in) throws IOException {
        JsonToken peek = in.peek();
        if (peek == JsonToken.NULL) {
            in.nextNull();
            return null;
        }

        return deserializeToDate(in.nextString());

    }

    @Override
    public synchronized void write(JsonWriter out, DateTime value) throws IOException {
        if (value == null){
            out.nullValue();
            return;
        }
        String dateTimeFormatStr = value.toString(dateTimeFormatter);
        out.value(dateTimeFormatStr);

    }

    private synchronized DateTime deserializeToDate(String json) {
        try {
            return DateTime.parse(json,dateTimeFormatter);
        } catch (IllegalArgumentException e) {
            throw new JsonSyntaxException(json, e);
        }

    }


}
