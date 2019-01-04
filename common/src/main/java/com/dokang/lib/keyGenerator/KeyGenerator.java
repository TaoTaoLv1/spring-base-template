package com.dokang.lib.keyGenerator;

public interface KeyGenerator<T> {
    T getNext(String type);

    T getNext();
}
