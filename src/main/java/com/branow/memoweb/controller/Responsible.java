package com.branow.memoweb.controller;

public interface Responsible<T> {

    T respond() throws Exception;

}
