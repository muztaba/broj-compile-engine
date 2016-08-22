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
@Table(name = "problem")
@Data
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Problem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "problem_id")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "time_limit", nullable = false)
    private Long timeLimit;

    @Column(name = "memory_limit", nullable = false)
    private Long memoryLimit;

    @Column(name = "in_file", length = 100000)
    private byte[] inputFile;

    @Column(name = "res_file", length = 100000)
    private byte[] resultFile;

    @Column(name = "time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date time;

    public Long getTimeLimit() {
        Objects.requireNonNull(timeLimit, "Time Limit should not be null");
        return timeLimit;
    }

    public Long getMemoryLimit() {
        Objects.requireNonNull(memoryLimit, "Memory Limit should not be null");
        return memoryLimit;
    }
}
