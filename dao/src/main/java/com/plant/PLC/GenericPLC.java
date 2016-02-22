package com.plant.PLC;

/**
 * Created by Shevchenko on 22.02.2016.
 */
public interface GenericPLC<T> {

    public T readByRef(String addrPLC, int ref);
    public void writeByRef(String addrPLC, int ref, T[] writeData);

}
