import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javafx.scene.chart.PieChart.Data;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;

public class ChatClient extends JFrame implements Runnable, ActionListener {

	private JPanel contentPane;
	private JTextField newMessage;
	private JTextArea messageHistory;
	private JButton btnSend;
	private Socket mySocket;
	private String clientName;

	private JScrollPane jScrollPane;

	public static void main(String[] args) {
		String name = JOptionPane.showInputDialog("what is your name?");
		System.out.println(name);

		new ChatClient(9999, name);
	}

	public ChatClient(int port, String clientName) {

		this.clientName = clientName;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		messageHistory = new JTextArea();
		// messageHistory.setBounds(12, 13, 408, 192);
		messageHistory.setEditable(false);
		// contentPane.add(messageHistory);

		jScrollPane = new JScrollPane(messageHistory);
		contentPane.add(jScrollPane);
		jScrollPane.setVisible(true);
		jScrollPane.setBounds(12, 13, 408, 192);

		newMessage = new JTextField();
		newMessage.setBounds(12, 218, 305, 22);
		contentPane.add(newMessage);
		newMessage.setColumns(10);

		btnSend = new JButton("Send");
		btnSend.setBounds(323, 217, 97, 25);
		contentPane.add(btnSend);
		btnSend.addActionListener(this);

		try {
			mySocket = new Socket("localhost", port);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		new Thread(this).start();

		setVisible(true);
	}

	@Override
	public void run() {
		while (true) {
			try {
				DataInputStream dataInputStream = new DataInputStream(
						mySocket.getInputStream());
				while (!(dataInputStream.available() > 0))
					;
				String s = dataInputStream.readUTF();
				messageHistory.setText(messageHistory.getText() + s);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (!newMessage.getText().isEmpty()) {
			try {
				DataOutputStream dataOutputStream = new DataOutputStream(
						mySocket.getOutputStream());
				dataOutputStream.writeUTF(clientName + " : "
						+ newMessage.getText() + "\n");
				newMessage.setText("");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
