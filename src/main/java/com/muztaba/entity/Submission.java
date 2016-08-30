package com.muztaba.entity;

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
@Table(name = "submissions")
@Data
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Submission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "submission_id")
    private long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "contest_id", referencedColumnName = "contest_id")
    private Contests contests;

    @Column(name = "contest_id", updatable = false, insertable = false)
    private long contestFK;

    @Column(name = "status")
    private boolean status;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "problem_id", referencedColumnName = "problem_id")
    private Problem problem;

    @Column(name = "language")
    private String lang;

    @Column(name = "source_code", length = 100000)
    private byte[] srcFile;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date time;

    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updated;

    @Column(name = "remember_token")
    private String rememberToken;
}
