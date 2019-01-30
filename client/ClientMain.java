package client;

import sun.rmi.runtime.Log;

import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientMain {
    private static Logger log = Logger.getLogger("ClientMain");
    private static String serverName = "192.168.0.10";
    private static int serverPort = 6789;
    private static Socket socket = null;
    public static void main(String...args) throws IOException {
        ClientMain clientMain = new ClientMain();
        clientMain.openConnection();
        clientMain.closeConnection();



    }
    public void closeConnection()
    {
        if (socket!=null&&!socket.isClosed()) {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            finally {
                socket = null;
            }
        }
        socket = null;
    }
    public void openConnection()
    {
        closeConnection();
        try {
            socket = new Socket(serverName, serverPort);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void sendData(byte[] data) throws Exception {
        if (socket==null||socket.isClosed())
        {
            throw new Exception("Невозможно отправить данны. Сокет не создан или закрыт");
        }
        try {
            socket.getOutputStream().write(data);
            socket.getOutputStream().flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    protected void finalize() throws Throwable {
        super.finalize();
        closeConnection();
    }
}
