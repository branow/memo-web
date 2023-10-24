package com.branow.memoweb.dto.collection;

import com.branow.memoweb.dto.flashcard.FlashcardDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CollectionDto {

    private Integer collectionId;
    private String collectionName;
    private List<FlashcardDto> flashCards;

}
