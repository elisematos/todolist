package com.project.todolist.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Item {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "attribuer un nom")
    private String name;
    private boolean done;
}
