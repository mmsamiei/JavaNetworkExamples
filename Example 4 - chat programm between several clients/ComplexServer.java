import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;


public class ComplexServer implements Runnable  {
	ChatServer chatServer;
	Socket socket;
	Thread t;
	public ComplexServer(Socket socket,ChatServer chatServer) {
		this.chatServer = chatServer;
		this.socket = socket;
		t = new Thread(this);
		t.start();
	}
	@Override
	public void run() {
		while(true){
			DataInputStream dataInputStream = null;
			try {
				dataInputStream = new DataInputStream(socket.getInputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				while(!(dataInputStream.available()>0));
				String s = dataInputStream.readUTF();
				chatServer.sendMessage(s);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}
