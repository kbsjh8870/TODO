package org.example.todo.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table("todo")
public class Todo {
    @Id
    private int id;
    private int user_id;
    private String content;
    private LocalDate upload_date;
    private int category_id;
    @Column("isdone")
    private boolean done;
}
