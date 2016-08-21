package com.muztaba.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by seal on 8/19/16.
 */
@Entity
@Table(name = "submission")
@Data
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Submission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "submission_id")
    private long id;

    @Column(name = "username")
    private String userName;

    @Column(name = "status")
    private boolean status;

/*    @Column(name = "problem_id")
    private long problemId;*/

    @ManyToOne(optional = false)
    @JoinColumn(referencedColumnName = "problem_id")
    private Problem problem;

    @Column(name = "language")
    private String lang;

    @Column(name = "src_file", length = 100000)
    private byte[] srcFile;

    @Column(name = "time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date time;
}
