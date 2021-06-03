package com.dove.lambda;

public interface Carriage {
    public default String rock() {
        return "... from side to side";
    }
}
