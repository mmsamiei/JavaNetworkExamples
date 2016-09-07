import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;


public class MyClient {
	int clientNumber;
	Socket socket ; 
	public MyClient(int port,int clientNumber) {
		this.clientNumber = clientNumber;
		try {
			socket = new Socket("localhost",port);
			OutputStream outputStream = socket.getOutputStream();
			outputStream.write(clientNumber);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		MyClient myClient = new MyClient(9999,33);
		//MyClient myClient2 = new MyClient(9999,2);
		//MyClient myClient3 = new MyClient(9999,3);
	}
}
