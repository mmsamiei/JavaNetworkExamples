import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MultiThreadServer implements Runnable {
	Socket clientSocket;
	ServerSocket serverSocket;

	public MultiThreadServer(Socket clientSocket, ServerSocket serverSocket) {
		super();
		this.clientSocket = clientSocket;
		this.serverSocket = serverSocket;
	}

	@Override
	public void run() {
		while (true) {
			try {
				int a =0;
				int b=0;
				DataInputStream dataInputStream = new DataInputStream(clientSocket.getInputStream());
				while(!(dataInputStream.available()>0));
				a = dataInputStream.readInt();
				while(!(dataInputStream.available()>0));
				b = dataInputStream.readInt();
				DataOutputStream dataOutputStream = new DataOutputStream(clientSocket.getOutputStream());
				dataOutputStream.writeInt(a+b);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
