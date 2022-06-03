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
@Table(name = "Send")
@IdClass(Keys.class)
@ToString
public class Send implements Serializable {

    @Id
    @Column(length = 8)
    private String date;
    @Id
    private Long comNumber;

    private String content;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

}
