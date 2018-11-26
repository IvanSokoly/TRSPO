import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Main {
    private static Socket clientSocket;
    private static ServerSocket server;
    private static BufferedReader reader;
    private static BufferedReader in;
    private static BufferedWriter out;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.next();
        if (input.equals("client"))
            try {
                try {
                    clientSocket = new Socket("localhost", 4004);
                    reader = new BufferedReader(new InputStreamReader(System.in));
                    in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

                    System.out.println("Вы что-то хотели сказать? Введите это здесь:");
                    String word = reader.readLine();
                    out.write(word + "\n");
                    out.flush();
                    String serverWord = in.readLine();
                    System.out.println(serverWord);
                } finally {
                    System.out.println("Клиент был закрыт...");
                    clientSocket.close();
                    in.close();
                    out.close();
                }
            } catch (IOException e) {
                System.err.println(e);
            }
        else
            try {
                try {
                    server = new ServerSocket(4004);
                    System.out.println("Сервер запущен!");
                    clientSocket = server.accept();
                    try {
                        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                        out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

                        String word = in.readLine();
                        System.out.println(word);
                        out.flush();

                    } finally {
                        clientSocket.close();
                        in.close();
                        out.close();
                    }
                } finally {
                    System.out.println("Сервер закрыт!");
                    server.close();
                }
            } catch (IOException e) {
                System.err.println(e);
            }
    }
}
