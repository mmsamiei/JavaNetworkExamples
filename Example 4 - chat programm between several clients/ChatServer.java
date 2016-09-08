import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;


public class ChatServer  implements Runnable,ActionListener {

	private ServerSocket serverSocket;
	private Vector<Socket> clients;
	
	public static void main(String[] args) {
		new ChatServer(9999);
	}

	
	public ChatServer(int port) {
		
		
		try {
			serverSocket = new ServerSocket(port);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		new Thread(this).start();
		
		clients=new Vector<Socket>();
	}


	@Override
	public void run() {
		
		while(true){
			Socket clientTemp;
			try {
				System.out.println("1");
				clientTemp = serverSocket.accept();
				clients.addElement(clientTemp);
				new ComplexServer(clientTemp,this);
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
//		try {
//			DataOutputStream dataOutputStream = new DataOutputStream(.getOutputStream());
//			dataOutputStream.writeUTF(newMessage.getText()+"\n");
//			messageHistory.setText(messageHistory.getText()+newMessage.getText()+"\n");
//			newMessage.setText("");
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
	}
	
	public  void sendMessage(String s){
		for(int i=0;i<clients.size();i++){
			try {
				DataOutputStream dataOutputStream = new DataOutputStream(clients.elementAt(i).getOutputStream());
				dataOutputStream.writeUTF(s);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
