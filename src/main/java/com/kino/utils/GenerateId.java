package com.kino.utils;

public enum GenerateId {
    INSTANCE;
    private long id;

    GenerateId() {
        id = 1L;
    }
    public long generateId(){
        return id++;
    }
}
