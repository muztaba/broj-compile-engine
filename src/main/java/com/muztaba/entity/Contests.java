package com.muztaba.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by seal on 8/28/2016.
 */

@Entity
@Table(name = "contests")
@Data
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Contests {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contest_id")
    private Long id;

    @Column(name = "contest_name")
    private String contestName;

    @Column(name = "contest_author")
    private String contest_author;

    @Column(name = "start_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startTime; //java.sql.Timestamp

    @Column(name = "end_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endTime;

    @Column(name = "duration")
    private Long duration;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updated;

    @Column(name = "remember_token")
    private String rememberToken;

}
