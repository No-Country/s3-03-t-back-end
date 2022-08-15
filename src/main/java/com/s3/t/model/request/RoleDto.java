package com.s3.t.model.request;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
public class RoleDto implements Serializable {
    private final String name;
    private final String description;
    private final Timestamp timestamp;
}
