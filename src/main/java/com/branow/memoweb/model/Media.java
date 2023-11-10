package com.branow.memoweb.model;

import com.branow.memoweb.model.auxilary.FileType;
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
public class Media {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer mediaId;
    private byte[] media;
    private String hash;
    @Enumerated(EnumType.STRING)
    private FileType.Format format;

}