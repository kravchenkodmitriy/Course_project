import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(8080);) { // стартуем сервер один(!) раз
            Gson gson = new Gson();

            FileProcessing fileProcessing = new FileProcessing();
            fileProcessing.readJson(null);
            while (true) { // в цикле(!) принимаем подключения
                try (
                        Socket socket = serverSocket.accept();
                        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        PrintWriter out = new PrintWriter(socket.getOutputStream());
                ) {
                    String input = in.readLine();
                    Purchasess client = gson.fromJson(input, Purchasess.class);
                    fileProcessing.readJson(client);
                    String serverConnection = fileProcessing.stringToJson();
                    System.out.println(serverConnection);

                }
            }
        } catch (IOException e) {
            System.out.println("Не могу запустить сервер");
            e.printStackTrace();
        }
    }
}
