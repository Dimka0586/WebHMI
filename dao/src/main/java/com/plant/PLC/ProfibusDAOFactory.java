package com.plant.PLC;

/**
 * Created by Shevchenko on 22.02.2016.
 */
public class ProfibusDAOFactory extends DAOFactoryPLC {
    @Override
    public Object readMultiReg(String addr, int ref, int count) {
        return null;
    }

    @Override
    public void writeMultiReg(String addr, int ref, short[] writeData) {

    }

    @Override
    public Object readMultiFloat(String addr, int ref, int count) {
        return null;
    }

    @Override
    public void writeMultiFloat(String addrPLC, int ref, float[] writeData) {

    }

    @Override
    public Object getConnection(String addr) {
        return null;
    }

    @Override
    public void closeConnection(Object connection) {

    }

    @Override
    public StatDepPLCDAO getStatDAO() {
        return null;
    }

    @Override
    public AnInPLCDAO getAnInDAO() {
        return null;
    }
}
