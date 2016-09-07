import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class MyClient {
	public static void main(String[] args) {
		try {
			Socket socket1 = new Socket("localhost", 9999);
			Socket socket2 = new Socket("localhost", 9999);
			DataOutputStream dataOutputStream1 = new DataOutputStream(
					socket1.getOutputStream());
			DataOutputStream dataOutputStream2 = new DataOutputStream(
					socket2.getOutputStream());
			dataOutputStream1.writeInt(1);
			dataOutputStream2.writeInt(3);
			dataOutputStream2.writeInt(4);
			dataOutputStream1.writeInt(2);
			DataInputStream dataInputStream1 = new DataInputStream(
					socket1.getInputStream());
			DataInputStream dataInputStream2 = new DataInputStream(
					socket2.getInputStream());
			while (!(dataInputStream1.available() > 0))
				;
			System.out.println("response is " + dataInputStream1.readInt());

			while (!(dataInputStream2.available() > 0))
				;
			System.out.println("response for 2nd is "
					+ dataInputStream2.readInt());

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}