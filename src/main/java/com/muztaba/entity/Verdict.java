package com.muztaba.entity;

import com.muztaba.service.compiler.util.CompileStatus;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by seal on 8/20/16.
 */
@Entity
@Table(name = "verdicts")
@Data
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Verdict {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "verdict_id")
    private long id;

    @OneToOne(targetEntity = Submission.class)
    @JoinColumn(name = "submission_id", referencedColumnName = "submission_id")
    private Submission submission;

    @Column(name = "output_file", length = 100000)
    private byte[] outputFile;

    @Column(name = "output_file_special", length = 100000)
    private byte[] outputFileSpecial;

    @Column(name = "result")
    @Enumerated(EnumType.STRING)
    private CompileStatus compileStatus;

    @Column(name = "execution_time")
    private Long executionTime;

    @Column(name = "execution_memory")
    private Long executionMemory;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updated;

    @Column(name = "remember_token")
    private String rememberToken;

}
