import java.io.*;
import java.net.*;

public class TCPServer {
    public static void main(String[] args) throws Exception {
        int port = 9876;
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Servidor TCP aguardando conexões na porta " + port + "...");

        Socket connectionSocket = serverSocket.accept();
        System.out.println("Cliente conectado: " + connectionSocket.getInetAddress());

        BufferedReader inFromClient = new BufferedReader(
            new InputStreamReader(connectionSocket.getInputStream()));
        PrintWriter outToClient = new PrintWriter(
            new BufferedWriter(new OutputStreamWriter(connectionSocket.getOutputStream())), true);

        String clientMessage;

        // ✅ loop: continua recebendo até o cliente desconectar (readLine retorna null)
        while ((clientMessage = inFromClient.readLine()) != null) {
            System.out.println("Mensagem recebida: " + clientMessage);
            outToClient.println(clientMessage.toUpperCase());
        }

        connectionSocket.close();
        serverSocket.close();
        System.out.println("Cliente desconectado. Conexão encerrada.");
    }
}