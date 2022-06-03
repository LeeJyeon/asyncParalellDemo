package com.test.project.async.table;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;
import java.io.Serializable;

@Getter
@Setter
public class Keys implements Serializable {
    private String date;
    private Long comNumber;
}
