package com.branow.memoweb.dto.collection;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CollectionShortDto {

    private Integer collectionId;
    private String collectionName;
    private Integer size;

}
