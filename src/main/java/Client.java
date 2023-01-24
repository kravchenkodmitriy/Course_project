import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Client {
    private static final int PORT = 8989;
    private static final String HOST = "localhost";

    public static void main(String[] args) throws IOException{
        while (true) {
            try (
                    Socket clientSocket = new Socket(HOST, PORT);
                    PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                    BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            ) {
                BufferedReader scanner = new BufferedReader(new InputStreamReader(System.in));
                System.out.println("Введите название товара и количество");
                String input = scanner.readLine();
                String[] scannerReader = input.split("\t");
                if (input.equals("end")) {
                    System.out.println("Программа завершена");
                    break;
                } else if (scannerReader.length >= 1) {
                    String product = scannerReader[0];
                    String date = todaysDate();
                    int sum = Integer.parseInt(scannerReader[1]);
                } else {
                    System.out.println("Ввели не верно!");
                }
            }
        }
    }
    public static String todaysDate() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy.mm.dd");
        LocalDateTime timeNow = LocalDateTime.now();
        return dateTimeFormatter.format(timeNow);
    }
}
