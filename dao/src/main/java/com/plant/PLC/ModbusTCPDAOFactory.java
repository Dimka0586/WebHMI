package com.plant.PLC;

import net.wimpi.modbus.ModbusException;
import net.wimpi.modbus.io.ModbusTCPTransaction;
import net.wimpi.modbus.msg.ReadMultipleRegistersRequest;
import net.wimpi.modbus.msg.ReadMultipleRegistersResponse;
import net.wimpi.modbus.msg.WriteMultipleRegistersRequest;
import net.wimpi.modbus.msg.WriteMultipleRegistersResponse;
import net.wimpi.modbus.net.TCPMasterConnection;
import net.wimpi.modbus.procimg.Register;
import net.wimpi.modbus.procimg.SimpleRegister;
import net.wimpi.modbus.util.ModbusUtil;
import org.apache.commons.lang.ArrayUtils;
import org.apache.log4j.Logger;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by Shevchenko on 22.02.2016.
 */
public class ModbusTCPDAOFactory extends DAOFactoryPLC<TCPMasterConnection, Register[], float[]> {

    static Logger logger = Logger.getLogger(ModbusTCPDAOFactory.class);

    @Override
    public Register[] readMultiReg(String addr, int ref, int count) {
        TCPMasterConnection connection = this.getConnection(addr);
        ModbusTCPTransaction transaction = new ModbusTCPTransaction(connection);
        ReadMultipleRegistersRequest multiReq = new ReadMultipleRegistersRequest(ref, count);
        ReadMultipleRegistersResponse multiResp = null;

        transaction.setRequest(multiReq);
        Register[] registers = null;
        try {
            transaction.execute();
            multiResp = (ReadMultipleRegistersResponse)transaction.getResponse();
            registers = multiResp.getRegisters();

            logger.info("Received " + registers.length + " multi registers");
        } catch (ModbusException e) {
            e.printStackTrace();
        }finally {
            if (connection != null){
                closeConnection(connection);
            }
        }
        return registers;
    }

    @Override
    public void writeMultiReg(String addr, int ref, short[] writeData) {
        TCPMasterConnection connection = this.getConnection(addr);
        ModbusTCPTransaction transaction = new ModbusTCPTransaction(connection);
        Register[] registersArr = new Register[writeData.length];

        for (int i=0; i<writeData.length; i++){
            registersArr[i] = new SimpleRegister(writeData[i]);
        }
        WriteMultipleRegistersRequest multiReq = new WriteMultipleRegistersRequest(ref, registersArr);
        WriteMultipleRegistersResponse multiResp = null;
        transaction.setRequest(multiReq);
        try {
            transaction.execute();
            logger.info("Multi registers is writed");
        } catch (ModbusException e) {
            e.printStackTrace();
        }finally {
            if (connection != null){
                closeConnection(connection);
            }
        }
    }

    @Override
    public float[] readMultiFloat(String addr, int ref, int count) {
        Register[] registers = null;
        float[] floatList = new float[count];
        registers = readMultiReg(addr, ref, count * 2);
        int k = 0;
        for (int i = 0; i < count*2; i = i+2){
            byte[] b1 = registers[i].toBytes();
            byte[] b2 = registers[i+1].toBytes();
            b1 = ArrayUtils.addAll(b2, b1);
            floatList[k] = ModbusUtil.registersToFloat(b1);
            k++;
        }
        return floatList;
    }

    @Override
    public void writeMultiFloat(String addrPLC, int ref, float[] writeData) {
        byte[] bytesFromFloat = new byte[4];
        short[]shortsToArrFloat = new short[writeData.length*2];
        int k = 0;
        for (int i = 0; i < writeData.length; i++){
            bytesFromFloat = ModbusUtil.floatToRegisters(writeData[i]);
            shortsToArrFloat[k] = ModbusUtil.registerToShort(bytesFromFloat, 2);
            shortsToArrFloat[k+1] = ModbusUtil.registerToShort(bytesFromFloat, 0);
            k = k + 2;
        }
        writeMultiReg(addrPLC, ref, shortsToArrFloat);
    }

    @Override
    public TCPMasterConnection getConnection(String addr) {
        TCPMasterConnection connection = null;
        int port = 0;
        InetAddress inetAddress = null;
        logger.info("Getting TCP Master Connection by = " + addr);
        try {
            int idx = addr.indexOf(':');
            if (idx > 0) {
                port = Integer.parseInt(addr.substring(idx + 1));
                addr = addr.substring(0, idx);
            }
            logger.info("adressStr = " + addr);
            inetAddress = InetAddress.getByName(addr);
        } catch (UnknownHostException ex) {
            logger.warn("UnknowHostException = ",ex );
        }
        connection = new TCPMasterConnection(inetAddress);
        connection.setPort(port);
        logger.info("Connection is received");
        return connection;
    }

    @Override
    public void closeConnection(TCPMasterConnection connection) {
        connection.close();
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
