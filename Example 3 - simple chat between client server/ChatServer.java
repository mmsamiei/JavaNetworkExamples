import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;


public class ChatServer extends JFrame implements Runnable,ActionListener {

	private JPanel contentPane;
	private JTextField newMessage;
	private JTextArea messageHistory;
	private JButton btnSend;
	private ServerSocket serverSocket;
	private Socket clientSocket;
	
	public static void main(String[] args) {
		new ChatServer(9999);
	}

	
	public ChatServer(int port) {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		messageHistory = new JTextArea();
		messageHistory.setBounds(12, 13, 408, 192);
		contentPane.add(messageHistory);
		messageHistory.setEditable(false);
		
		newMessage = new JTextField();
		newMessage.setBounds(12, 218, 305, 22);
		contentPane.add(newMessage);
		newMessage.setColumns(10);
		
		btnSend = new JButton("Send");
		btnSend.setBounds(323, 217, 97, 25);
		contentPane.add(btnSend);
		btnSend.addActionListener(this);
		
		try {
			serverSocket = new ServerSocket(port);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		new Thread(this).start();
		
		setVisible(true);
	}


	@Override
	public void run() {
		messageHistory.setText("waiting for connecting a client ..."+"\n");
		try {
			clientSocket = serverSocket.accept();
			messageHistory.setText(messageHistory.getText()+"a client now is connected"+"\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
		while(true){
			try {
				DataInputStream dataInputStream = new DataInputStream(clientSocket.getInputStream());
				while(!(dataInputStream.available()>0));
				String s = dataInputStream.readUTF();
				messageHistory.setText(messageHistory.getText()+"client :"+s);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		try {
			DataOutputStream dataOutputStream = new DataOutputStream(clientSocket.getOutputStream());
			dataOutputStream.writeUTF(newMessage.getText()+"\n");
			messageHistory.setText(messageHistory.getText()+newMessage.getText()+"\n");
			newMessage.setText("");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
