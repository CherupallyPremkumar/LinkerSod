package com.sod.doc.chatapp.model.domain;

import com.sod.doc.chatapp.state.ContentType;
import jakarta.persistence.*;
import org.chenile.jpautils.entity.AbstractJpaStateEntity;

import java.util.Date;

@Entity
@Table(name = "messages")
public class Message extends AbstractJpaStateEntity {
    private String senderId;
    private String receiverId;
    private String content;
    private ContentType content_type;
    private Date sentAt;
    private Date receivedAt;
    private Date seen_at;


    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ContentType getContent_type() {
        return content_type;
    }

    public void setContent_type(ContentType content_type) {
        this.content_type = content_type;
    }

    public Date getSentAt() {
        return sentAt;
    }

    public void setSentAt(Date sentAt) {
        this.sentAt = sentAt;
    }

    public Date getReceivedAt() {
        return receivedAt;
    }

    public void setReceivedAt(Date receivedAt) {
        this.receivedAt = receivedAt;
    }

    public Date getSeen_at() {
        return seen_at;
    }

    public void setSeen_at(Date seen_at) {
        this.seen_at = seen_at;
    }
}
