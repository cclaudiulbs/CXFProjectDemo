package com.cxf.demo.pojo;

import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;

/**
 * @author cclaudiu
 */
public class Customers {

    @JsonProperty("customers")
    private List<Customer> customers;

    public Customers() {

    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }
}
