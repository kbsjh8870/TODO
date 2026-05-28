package org.example.todo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TodoDTO {
    private int id;
    private int user_id;
    private String content;
    private LocalDate upload_date;
    private int category_id;
    private boolean done;
}
