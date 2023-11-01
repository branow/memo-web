package com.branow.memoweb.exception.entitynotfound;

public class ModuleNotFoundException extends EntityNotFoundException{

    public ModuleNotFoundException(String searchField, Object fieldValue) {
        super("Module", searchField, fieldValue);
    }

}
