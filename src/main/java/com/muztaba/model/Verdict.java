package com.muztaba.model;

import com.muztaba.service.compiler.util.CompileStatus;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by seal on 8/20/16.
 */
@Entity
@Table(name = "verdict")
@Data
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Verdict {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "verdict_id")
    private long id;

    @OneToOne(targetEntity = Submission.class)
    @JoinColumn(name = "submission", referencedColumnName = "submission_id")
    private Submission submission;

    @Column(name = "verdict")
    @Enumerated(EnumType.STRING)
    private CompileStatus compileStatus;
}
