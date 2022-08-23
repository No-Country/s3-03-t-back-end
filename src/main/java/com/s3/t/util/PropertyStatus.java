package com.s3.t.util;

public enum PropertyStatus {
    FREE("FREE"),RENTED("RENTED"), PROSSES("PROSSES");

    private final String name;

    PropertyStatus(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
