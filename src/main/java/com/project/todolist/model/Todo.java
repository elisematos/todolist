package com.project.todolist.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "attribuer un nom")
    private String title;
    @OneToMany(cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<Item> items;
}
