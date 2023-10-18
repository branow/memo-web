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
public class Module {

    @Id
    private Integer moduleId;

    private String moduleName;

    private String description;

    @ManyToOne
    @JoinColumn(name = "access")
    private AccessType accessType;

    @OneToMany
    @JoinColumn(name = "module")
    private List<Collection> collections;
}
