import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServer {
	ServerSocket serverSocket;
	private int port;
	Thread serverThread;

	public static void main(String[] args) {
		MyServer myServer = new MyServer(9999);
	}
	
	public MyServer(int port) {
		this.port = port;
		try {
			serverSocket = new ServerSocket(port);
		} catch (IOException e) {
			e.printStackTrace();
		}
		serverThread = new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					try {
						Socket clientSocket = serverSocket.accept();
						System.out.println("server find a socket now ...");
						new Thread(new MultiThreadServer(clientSocket,serverSocket)).start();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		});
		serverThread.start();
	}
}