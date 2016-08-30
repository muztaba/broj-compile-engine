package com.muztaba.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;
import java.util.Objects;

/**
 * Created by seal on 8/19/16.
 */
@Entity
@Table(name = "problems")
@Data
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Problem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "problem_id")
    private long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "contest_id", referencedColumnName = "contest_id")
    private Contests contests;

    @Column(name = "problem_name")
    private String problemName;

    @Column(name = "problem_author")
    private String problemAuthor;

    @Column(name = "statement")
    private String statement;

    @Column(name = "sample_input")
    private String sampleInput;

    @Column(name = "sample_output")
    private String sampleOutput;

    @Column(name = "time_limit", nullable = false)
    private Long timeLimit;

    @Column(name = "memory_limit", nullable = false)
    private Long memoryLimit;

    @Column(name = "judge_input", length = 100000)
    private byte[] inputFile;

    @Column(name = "judge_output", length = 100000)
    private byte[] resultFile;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date time;

    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updated;

    @Column(name = "remember_token")
    private String rememberToken;
}
