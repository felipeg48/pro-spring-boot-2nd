package com.apress.todo.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ToDoCollection {

    @JsonProperty("_embedded")
    public Embedded embedded;

    @Data
    public class Embedded {

        public List<ToDo> toDoList;

    }
}
