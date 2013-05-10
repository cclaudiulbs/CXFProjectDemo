package com.cxf.demo.service;

import com.cxf.demo.exception.MarshallerException;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.File;
import java.io.IOException;

/**
 * @author cclaudiu
 */
public class CustomMarshaller<T extends Object> {

    private final ObjectMapper mapper = new ObjectMapper();

    public T unmarshall(String content, Class<T> destClazz) {
        T result;
        try {

            result = mapper.readValue(content, destClazz);
        } catch (IOException io) {
            throw new MarshallerException(io.getMessage(), io);
        }

        return result;
    }

    public void marshall(File outfile, String content) {
        try {
            mapper.writeValue(outfile, content);
        } catch (IOException e) {
            throw new MarshallerException(e.getMessage(), e);
        }
    }
}
