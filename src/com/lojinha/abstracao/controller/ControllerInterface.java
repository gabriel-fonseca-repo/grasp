package com.lojinha.abstracao.controller;

public interface ControllerInterface<E> {

    public default void save(E entity) {

    }

    public default void update(E entity) {

    }

}
