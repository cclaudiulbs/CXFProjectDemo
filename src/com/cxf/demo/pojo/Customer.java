package com.cxf.demo.pojo;

import org.codehaus.jackson.annotate.JsonProperty;

import java.util.Map;

public class Customer {

    @JsonProperty("id")
    private String id;

    @JsonProperty("customerDescription")
    private Map<String, String> customerDescription;

    public Customer() {

    }

    public String getId() {
        return id;
    }

    public Map<String, String> getCustomerDescription() {
        return customerDescription;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCustomerDescription(Map<String, String> customerDescription) {
        this.customerDescription = customerDescription;
    }
}
