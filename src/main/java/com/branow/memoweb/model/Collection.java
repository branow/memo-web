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
    private Integer collectionId;

    private String collectionName;

    @OneToMany
    @JoinColumn(name = "collection")
    private List<FlashCard> flashCards;

}
