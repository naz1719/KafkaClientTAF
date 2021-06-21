package com.project.inftrastructure.db.dto;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class MessageDTO {

    @Column(name = "id")
    private Long id;
    @Column(name = "message")
    private String message;

    public MessageDTO() {
    }

    public MessageDTO(Long id, String message) {
        this.id = id;
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
