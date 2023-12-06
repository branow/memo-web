package com.branow.memoweb.service;

import java.util.List;

public interface LearningService {

    List<Integer> getFlashcardIdsToLearnWithJwtCheck(String jwt, Integer studyType, List<Integer> collections,
                                         List<Integer> levels, Boolean sort);

}
