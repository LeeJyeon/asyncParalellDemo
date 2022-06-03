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
@Table(name = "TABLE_B")
@IdClass(KeysCheck.class)
@ToString
public class CheckB  implements Serializable {

    @Id
    @Column(length = 8)
    private String date;
    @Id
    private String uuid;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

}
