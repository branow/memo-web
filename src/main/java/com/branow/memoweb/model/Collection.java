package com.branow.memoweb.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Collection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer collectionId;

    private String collectionName;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "collection")
    private List<Flashcard> flashcards;

    private Integer module;/***/

}
