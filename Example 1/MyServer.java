import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class MyServer implements Runnable {
	ServerSocket serverSocket;
	private int port;
	public MyServer(int port) {
		this.port = port;
		try {
			serverSocket = new ServerSocket(port);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void run() {
		while(true){
			try {
				System.out.println("Server start to find client socket and accept it ...");
				Socket clientSocket = serverSocket.accept();
				System.out.println("Server find a client socket and accepted it ...");
				InputStream inputStream = clientSocket.getInputStream();
				int clinetNumber = inputStream.read();
				System.out.println("the clientNumber of this client is "+clinetNumber+"\n\n");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	public static void main(String[] args) {
		Thread t = new Thread(new MyServer(9999));
		t.start();
	}
	
}
