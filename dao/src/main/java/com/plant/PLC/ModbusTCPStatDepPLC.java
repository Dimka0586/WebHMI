package com.plant.PLC;

import com.plant.StatDepPLC;

/**
 * Created by Shevchenko on 22.02.2016.
 */
public class ModbusTCPStatDepPLC implements StatDepPLCDAO {
    private ModbusTCPDAOFactory modbusTCPDAOFactory;

    public ModbusTCPStatDepPLC(ModbusTCPDAOFactory modbusTCPDAOFactory) {
        this.modbusTCPDAOFactory = new ModbusTCPDAOFactory();
    }

    @Override
    public StatDepPLC readByRef(String addr, int ref) {
        StatDepPLC statDepPLC = new StatDepPLC();
        float[] statFloats = modbusTCPDAOFactory.readMultiFloat(addr, ref, 11);
        statDepPLC.setHour(statFloats[0]);
        statDepPLC.setOldHour(statFloats[1]);
        statDepPLC.setShift(statFloats[2]);
        statDepPLC.setOldShift(statFloats[3]);
        statDepPLC.setDay(statFloats[4]);
        statDepPLC.setOldDay(statFloats[5]);
        statDepPLC.setTenDay(statFloats[6]);
        statDepPLC.setOldTenDay(statFloats[7]);
        statDepPLC.setMonth(statFloats[8]);
        statDepPLC.setOldMonth(statFloats[9]);
        statDepPLC.setSeason(statFloats[10]);
        return statDepPLC;
    }

    @Override
    public void writeByRef(String addrPLC, int ref, StatDepPLC[] writeData) {

    }
}
