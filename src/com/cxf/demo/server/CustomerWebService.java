package com.cxf.demo.server;


import com.cxf.demo.constants.ApplicationConstants;
import com.cxf.demo.pojo.Customers;
import com.cxf.demo.service.CustomMarshaller;
import org.apache.commons.io.IOUtils;
import org.springframework.util.Assert;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * GET:
 * http://localhost:9090/services/customers/1
 * <p/>
 * POST:
 * {
 * "customers": [
 * {
 * "id": "1",
 * "customerDescription": {
 * "first_name": "cclaudiu",
 * "job": "java_developer",
 * "age": "31"
 * }
 * },
 * {
 * "id": "2",
 * "customerDescription": {
 * "first_name": "marius",
 * "job": "java_developer",
 * "age": "3"
 * }
 * }
 * ]
 * }
 *
 * @author cclaudiu
 */
public class CustomerWebService {

    private final CustomMarshaller<Customers> marshaller = new CustomMarshaller<Customers>();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("customers/{id}")
    public Response getCustomer(@PathParam("id") String customerId) {

        Customers customers = marshaller.unmarshall(getFileContent(getStreamFromFile()), Customers.class);
        return Response.ok(customers.getCustomers().get(Integer.parseInt(customerId) - 1)).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("customers/create")
    public Response createCustomers(Customers customers) {
        OutputStream outputStream = new ByteArrayOutputStream();
        final String outContent = "";
        marshaller.marshall(outputStream, customers);

        try {
            IOUtils.write(outContent, outputStream);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }

        Assert.hasText(customers.getCustomers().get(0).getId());

        return Response.ok().build();
    }


    private String getFileContent(InputStream inputStream) {
        String fileContent;
        try {
            fileContent = IOUtils.toString(inputStream);
        } catch (IOException e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
        return fileContent;
    }

    private InputStream getStreamFromFile() {
        return getClass().getClassLoader().getResourceAsStream(ApplicationConstants.CUSTOMERS_JSON.getValue());
    }


}
