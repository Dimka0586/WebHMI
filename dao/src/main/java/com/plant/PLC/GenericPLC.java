package com.plant.PLC;

/**
 * Created by Shevchenko on 22.02.2016.
 */
public interface GenericPLC<T> {

    public T readMultiByRef(String addrPLC, int ref, int count);
    public void writeMultiByRef(String addrPLC, int ref, T[] writeData);

}
