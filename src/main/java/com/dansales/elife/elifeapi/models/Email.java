package com.dansales.elife.elifeapi.models;

import com.dansales.elife.elifeapi.models.enums.StatusEmail;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity()
@Table(name = "email")
public class Email extends BaseEntity {
    @Column()
    private String owner;
    @Column(name = "email_from")
    private String from;
    @Column(name = "email_to")
    private String to;
    @Column()
    private String subject;
    @Column(columnDefinition = "TEXT")
    private String content;
    @Column()
    private StatusEmail status;

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public StatusEmail getStatus() {
        return status;
    }

    public void setStatus(StatusEmail status) {
        this.status = status;
    }
}
