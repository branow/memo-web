package com.branow.memoweb.service;

public interface JwtBelongingChecker {

    void belongToOrThrow(String jwt, Integer userId);

    void moduleBelongToOrThrow(String jwt, Integer moduleId);

    boolean belongTo(String jwt, Integer userId);

    boolean moduleBelongTo(String jwt, Integer moduleId);

    Integer getUserId(String jwt);

}
