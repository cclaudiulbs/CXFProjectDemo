package com.cxf.demo.constants;

/**
 * @author cclaudiu
 */
public enum ApplicationConstants {
    CUSTOMERS_JSON("customer.json");

    private final String value;

    private ApplicationConstants(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
