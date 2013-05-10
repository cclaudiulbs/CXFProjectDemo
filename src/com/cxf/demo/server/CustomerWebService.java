package com.cxf.demo.server;


import com.cxf.demo.constants.ApplicationConstants;
import com.cxf.demo.pojo.Customers;
import com.cxf.demo.service.CustomMarshaller;
import org.apache.commons.io.IOUtils;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.io.InputStream;

/**
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
