package com.plant.PLC;

/**
 * Created by Shevchenko on 22.02.2016.
 */
public abstract class DAOFactoryPLC<TCon, TReadMultiReg, TReadMultiFloat> {

    public static final int MODBUS_RTU = 1;
    public static final int MODBUS_TCP = 2;
    public static final int PROFIBUS = 3;


    public abstract TReadMultiReg readMultiReg(String addr, int ref, int count);
    public abstract void writeMultiReg(String addr, int ref, short[] writeData);
    public abstract TReadMultiFloat readMultiFloat(String addr, int ref, int count);
    public abstract void writeMultiFloat(String addrPLC, int ref, float[] writeData);
    public abstract TCon getConnection(String addr);
    public abstract void closeConnection(TCon connection);
    public abstract StatDepPLCDAO getStatDAO();
    public abstract AnInPLCDAO getAnInDAO();

    public static DAOFactoryPLC getDAOFactoryPLC(int whichFactory){
        switch (whichFactory){
            case MODBUS_RTU:
                return new ModbusRTUDAOFactory();
            case MODBUS_TCP:
                return new ModbusTCPDAOFactory();
            case PROFIBUS:
                return new ProfibusDAOFactory();
            default:
                return null;
        }
    }

}
