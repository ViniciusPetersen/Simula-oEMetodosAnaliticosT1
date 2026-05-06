import java.io.*;
import java.net.*;

public class TCPClient {
    public static void main(String[] args) throws Exception {
        String serverName = "localhost";
        int port = 9876;

        Socket clientSocket = new Socket(serverName, port);
        System.out.println("Conectado ao servidor. Digite 'sair' para encerrar.");

        PrintWriter outToServer = new PrintWriter(
            new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream())), true);
        BufferedReader inFromServer = new BufferedReader(
            new InputStreamReader(clientSocket.getInputStream()));
        BufferedReader inFromUser = new BufferedReader(
            new InputStreamReader(System.in));

        String message;

        // ✅ loop: continua enviando até o usuário digitar "sair"
        while (true) {
            System.out.print("Digite uma mensagem: ");
            message = inFromUser.readLine();

            if (message.equalsIgnoreCase("sair")) {
                System.out.println("Encerrando conexão...");
                break;
            }

            outToServer.println(message);

            String response = inFromServer.readLine();
            System.out.println("Resposta do servidor: " + response);
        }

        clientSocket.close();
        System.out.println("Conexão encerrada.");
    }
}