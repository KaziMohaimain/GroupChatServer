import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private ServerSocket serverSocket;
    private Socket clientSocket;
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;

//Constructor:
    public Server(int port)
    {
        try {

            serverSocket = new ServerSocket(port);
            System.out.println("Server listening on port " + port);
            clientSocket = serverSocket.accept();
            System.out.println("Client connected!");

            outputStream = new ObjectOutputStream(clientSocket.getOutputStream());
            inputStream = new ObjectInputStream(clientSocket.getInputStream());
            while(clientSocket.isConnected())
            {
                Message messageObject = (Message) inputStream.readObject();
                System.out.println("[" + messageObject.senderName + "] " + messageObject.messageContent);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }



}
