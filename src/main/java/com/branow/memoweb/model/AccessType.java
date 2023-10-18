package com.branow.memoweb.model;

import com.branow.memoweb.model.auxilary.Access;
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
public class AccessType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer accessTypeId;

    @Enumerated(EnumType.STRING)
    private Access access;

}
