package com.dove.json.serializable;

import java.io.Serializable;

public class Man implements Serializable {
    private String address;

    public String getAddress() {
        return this.address;
    }

    public void setAddress(final String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Man{" +
                "address='" + address + '\'' +
                '}';
    }
}
