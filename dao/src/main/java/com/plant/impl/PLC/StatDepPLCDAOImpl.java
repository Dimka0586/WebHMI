package com.plant.impl.PLC;

import com.plant.PLC.StatDepPLCDAO;
import com.plant.StatDepPLC;

/**
 * Created by Shevchenko on 22.02.2016.
 */
public class StatDepPLCDAOImpl implements StatDepPLCDAO {

    @Override
    public StatDepPLC readByRef(String addr, int ref) {

        return null;
    }

    @Override
    public void writeByRef(String addrPLC, int ref, StatDepPLC[] writeData) {

    }
}
