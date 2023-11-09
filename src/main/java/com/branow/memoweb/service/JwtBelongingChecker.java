package com.branow.memoweb.service;

public interface JwtBelongingChecker {

    void belongToOrThrow(String jwt, Integer userId);

    void moduleBelongToOrThrow(String jwt, Integer moduleId);

    void collectionBelongToOrThrow(String jwt, Integer collectionId);

    void flashcardBelongToOrThrow(String jwt, Integer flashcardId);

    boolean belongTo(String jwt, Integer userId);

    boolean moduleBelongTo(String jwt, Integer moduleId);

    boolean collectionBelongTo(String jwt, Integer collectionId);

    boolean flashcardBelongTo(String jwt, Integer flashcardId);

    Integer getUserId(String jwt);

}
