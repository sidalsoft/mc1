package com.example.MC1.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name="messages")
public class MessageModel
{

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "session_id", nullable = false)
    private Integer sessionId;

    @Column(name = "MC1_timestamp")
    private Date MC1Timestamp;

    @Column(name = "MC2_timestamp")
    private Date MC2Timestamp;

    @Column(name = "MC3_timestamp")
    private Date MC3Timestamp;

    @Column(name = "end_timestamp")
    private Date endTimestamp;

    public Long getId()
    {
        return id;
    }

    public void setId( Long id )
    {
        this.id = id;
    }

    public Integer getSessionId()
    {
        return sessionId;
    }

    public void setSessionId( Integer sessionId )
    {
        this.sessionId = sessionId;
    }

    public Date getMC1Timestamp()
    {
        return MC1Timestamp;
    }

    public void setMC1Timestamp( Date MC1Timestamp )
    {
        this.MC1Timestamp = MC1Timestamp;
    }

    public Date getMC2Timestamp()
    {
        return MC2Timestamp;
    }

    public void setMC2Timestamp( Date MC2Timestamp )
    {
        this.MC2Timestamp = MC2Timestamp;
    }

    public Date getMC3Timestamp()
    {
        return MC3Timestamp;
    }

    public void setMC3Timestamp( Date MC3Timestamp )
    {
        this.MC3Timestamp = MC3Timestamp;
    }

    public Date getEndTimestamp()
    {
        return endTimestamp;
    }

    public void setEndTimestamp( Date endTimestamp )
    {
        this.endTimestamp = endTimestamp;
    }
}
