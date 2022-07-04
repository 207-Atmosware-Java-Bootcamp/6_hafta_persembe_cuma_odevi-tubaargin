package socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    private static String receiveMessage;
    private static String sendMessage;

    private static void twoWayServer() {
        FileLog fileLog = new FileLog();
        try {
            Socket socket = new Socket("localhost", 4545);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            OutputStream outputStream = socket.getOutputStream();
            PrintWriter printWriter = new PrintWriter(outputStream, true);
            InputStream inputStream = socket.getInputStream();
            BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(inputStream));
            System.out.println("Client: Lütfen mesaj yaiziniz ");

            while (true) {
                sendMessage = bufferedReader.readLine();
                printWriter.println(sendMessage);
                printWriter.flush();

                if ((receiveMessage = bufferedReader2.readLine()) != null) {
                    System.out.println("SERVER: " + receiveMessage);
                    fileLog.logWrite("SERVER: " + receiveMessage);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        twoWayServer();
    }
}
