package com.branow.memoweb.service;

import com.branow.memoweb.model.AccessType;

public interface AccessTypeService {

    AccessType getByAccessName(String access);

}
