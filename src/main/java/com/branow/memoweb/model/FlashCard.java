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
public class FlashCard {

    @Id
    private Integer flashCardId;

    @OneToOne
    @JoinColumn(name = "front_side")
    private FormattedText frontSide;

    @OneToOne
    @JoinColumn(name = "back_side")
    private FormattedText backSide;

    @OneToMany
    @JoinColumn(name = "flash_card")
    private List<Score> scores;

}
