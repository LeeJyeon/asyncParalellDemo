package com.test.project.async.table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "RECEIVE")
@IdClass(Keys.class)
@ToString
public class Receive implements Serializable {

    @Id
    @Column(length = 8)
    private String date;
    @Id
    private Long comNumber;

    private String uuid;
    private String content;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

}
