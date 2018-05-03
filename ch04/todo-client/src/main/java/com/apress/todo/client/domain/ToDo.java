package com.apress.todo.client.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;

@Data
public class ToDo {

    private String id;
    private String description;
    private Date created;
    private Date modified;
    private boolean completed;


    public ToDo(){
        Date date = Date.from(Instant.now());
        this.id = UUID.randomUUID().toString();
        this.created = date;
        this.modified = date;
    }

    public ToDo(String description){
        this();
        this.description = description;
    }
}