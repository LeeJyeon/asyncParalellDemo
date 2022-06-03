package com.test.project.async.table;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class KeysCheck implements Serializable {
    private String date;
    private String uuid;
}
