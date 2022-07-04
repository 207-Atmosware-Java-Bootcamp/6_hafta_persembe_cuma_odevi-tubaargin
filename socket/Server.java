package socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static String receiveMessage;
    private static String sendMessage;

    private static void twoWayServer() {
        FileLog fl = new FileLog();
        try {
            System.out.println("Server Hazır");
            ServerSocket serverSocket = new ServerSocket(4545);
            Socket socket = serverSocket.accept();

            OutputStream outputStream = socket.getOutputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

            PrintWriter printWriter = new PrintWriter(outputStream, true);

            InputStream inputStream = socket.getInputStream();
            BufferedReader receiveRead = new BufferedReader(new InputStreamReader(inputStream));

            while (true) {
                if ((receiveMessage = receiveRead.readLine()) != null) {
                    System.out.println("CLIENT: " + receiveMessage);
                    fl.logWrite("CLIENT: " + receiveMessage);
                }
                sendMessage = bufferedReader.readLine();
                printWriter.println(sendMessage);
                printWriter.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        twoWayServer();
    }
}
