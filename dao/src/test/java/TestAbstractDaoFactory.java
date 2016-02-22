import com.plant.PLC.DAOFactoryPLC;
import com.plant.PLC.ModbusTCPDAOFactory;
import net.wimpi.modbus.procimg.Register;

/**
 * Created by Shevchenko on 22.02.2016.
 */
public class TestAbstractDaoFactory {
    public static void main(String[] args) {
        String addr = "192.168.1.5:502";
        ModbusTCPDAOFactory modbusTCPDAOFactory = (ModbusTCPDAOFactory)DAOFactoryPLC.getDAOFactoryPLC(2);
        modbusTCPDAOFactory.writeMultiReg(addr, 6, new short[]{111, 112, 113, 114, 115});
        Register[] registers = modbusTCPDAOFactory.readMultiReg(addr, 0, 5);
        for(int i = 0; i < registers.length; i++){
            System.out.println(registers[i].getValue());
        }
        modbusTCPDAOFactory.writeMultiFloat(addr, 50, new float[]{(float)145.23, (float)27.25, (float)45.65,
                (float)54.25, (float)85.74});
        float[] floats = modbusTCPDAOFactory.readMultiFloat(addr, 50, 5);
        for (int i = 0; i < floats.length; i++){
            System.out.println(floats[i]);
        }
    }
}
