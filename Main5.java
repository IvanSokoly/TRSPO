import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Main {
    private static Socket clientSocket;
    private static ServerSocket server;
    private static BufferedReader in;
    private static BufferedWriter out;

    public static void main(String[] args) throws ParseException {
        Scanner sc = new Scanner(System.in);
        String input = sc.next();
        if (input.equals("client"))
            try {
                try {
                    clientSocket = new Socket("localhost", 27015);
                    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                    in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

                    System.out.println("Вы что-то хотели сказать? Введите это здесь:");
                    String message = reader.readLine();
                    JSONObject obj = new JSONObject();
                    obj.put("name", input);
                    obj.put("message", message);
                    out.write(obj.toJSONString());
                    out.flush();
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
                    server = new ServerSocket(27015);
                    System.out.println("Сервер запущен!");
                    clientSocket = server.accept();
                    try {
                        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                        out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

                        String word = in.readLine();
                        JSONObject json = StringToJSON(word);
                        word = JSONToString(json);
                        System.out.println(word);
                        out.write("test" + word);
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

    public static JSONObject StringToJSON(String text) throws ParseException {
        return (JSONObject) new JSONParser().parse(text);
    }

    public static String JSONToString(JSONObject json) {
        return json.get("name") + ": " + json.get("message") + "\n";
    }
}
