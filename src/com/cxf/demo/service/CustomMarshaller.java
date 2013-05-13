package com.cxf.demo.service;

import com.cxf.demo.exception.MarshallerException;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

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

    public void marshall(OutputStream outputStream, T value) {
        try {
            mapper.writeValue(outputStream, value);
        } catch (JsonMappingException e) {
            throw new MarshallerException(e.getMessage(), e);
        } catch (JsonGenerationException e) {
            throw new MarshallerException(e.getMessage(), e);
        } catch (IOException e) {
            throw new MarshallerException(e.getMessage(), e);
        }
    }

}
