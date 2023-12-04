package com.branow.memoweb.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class FormattedText {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer textId;

    private String text;


    private String format;

    @ManyToOne
    @JoinColumn(name = "image")
    private Media image;

    @ManyToOne
    @JoinColumn(name = "audio")
    private Media audio;

}
