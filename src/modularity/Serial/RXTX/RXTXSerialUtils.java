package modularity.Serial.RXTX;

import gnu.io.CommPortIdentifier;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * Created by Tommaso Garuglieri on 16/06/2016.
 */
public class RXTXSerialUtils {

    public static String[] WINDOWS_PORTS = {"COM0", "COM1", "COM2", "COM3", "COM4", "COM5", "COM6", "COM7"};

    public static String[] RASPBERRY_PORTS = {"/dev/ttyACM0"};

    public static String[] LINUX_PORTS = {"/dev/ttyUSB0"};

    public static String[] MACOSX_PORTS = {"/dev/tty.usbserial-A9007UX1"};

    public static void raspberryWorkaround() {
        System.setProperty("gnu.io.rxtx.SerialPorts", "/dev/ttyACM0");
    }

    public static List<CommPortIdentifier> getDevices() {
        Enumeration<CommPortIdentifier> portEnum = CommPortIdentifier.getPortIdentifiers();
        List<CommPortIdentifier> portList = new ArrayList<>();
        while (portEnum.hasMoreElements()) {
            CommPortIdentifier portIdentifier = portEnum.nextElement();
            portList.add(portIdentifier);
        }
        return portList;
    }

    public static CommPortIdentifier getDevice() {
        List<CommPortIdentifier> devices = getDevices();
        if (devices.size() == 0)
            throw new RuntimeException("No device found");
        else if (devices.size() > 1)
            throw new RuntimeException("More than one device found");
        else return devices.get(0);
    }

    public static CommPortIdentifier getDeviceByPort(String... ports) {
        Enumeration portEnum = CommPortIdentifier.getPortIdentifiers();
        CommPortIdentifier currPortId = (CommPortIdentifier) portEnum.nextElement();
        for (String portName : ports) {
            if (currPortId.getName().equals(portName)) {
                return currPortId;
            }
        }
        return null;
    }
}
