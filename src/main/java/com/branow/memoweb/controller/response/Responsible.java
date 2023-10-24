package com.branow.memoweb.controller.response;

public interface Responsible<T> {

    T respond() throws Exception;

}
