import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class RemoteClient extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	JPanel p1 = new JPanel();

	public Font f1 = new Font("Times New roman", Font.BOLD, 15);

	JLabel l1 = new JLabel("File-Name"), l2 = new JLabel("Secret-key"),
			l3 = new JLabel("Virtual M/C");

	JTextField t1 = new JTextField(), t2 = new JTextField();

	JComboBox cb = new JComboBox();

	JTextArea ta = new JTextArea();
	JScrollPane pane = new JScrollPane();

	JButton request = new JButton("Submit"), reset = new JButton("Reset");

	RemoteClient() {

		p1.setLayout(null);
		p1.setBackground(Color.PINK);

		cb.addItem("SelectVM");
		cb.addItem("LocalVm");
		cb.addItem("RemoteVm");

		TitledBorder b1 = BorderFactory.createTitledBorder("Request File");
		JLabel brd1 = new JLabel();
		brd1.setBorder(b1);

		TitledBorder b2 = BorderFactory.createTitledBorder("Received File");
		JLabel brd2 = new JLabel();
		brd2.setBorder(b2);

		b1.setTitleColor(Color.blue);
		b2.setTitleColor(Color.blue);

		brd1.setBounds(20, 25, 370, 250);
		brd2.setBounds(20, 290, 370, 350);

		l1.setFont(f1);
		l2.setFont(f1);
		l3.setFont(f1);

		t1.setFont(f1);
		t2.setFont(f1);
		cb.setFont(f1);

		request.setFont(f1);
		reset.setFont(f1);

		ta.setFont(f1);

		ta.setRows(10);
		ta.setColumns(10);
		pane.setViewportView(ta);

		l1.setBounds(50, 50, 100, 25);
		l2.setBounds(50, 100, 100, 25);
		l3.setBounds(50, 150, 100, 25);

		t1.setBounds(165, 50, 150, 25);
		t2.setBounds(165, 100, 150, 25);
		cb.setBounds(165, 150, 150, 25);

		request.setBounds(50, 200, 125, 25);
		reset.setBounds(200, 200, 125, 25);

		pane.setBounds(50, 310, 320, 300);

		p1.add(l1);
		p1.add(l2);
		p1.add(l3);

		p1.add(t1);
		p1.add(t2);
		p1.add(cb);

		p1.add(brd1);
		p1.add(brd2);

		p1.add(request);
		p1.add(reset);

		p1.add(pane);

		add(p1);

		request.addActionListener(this);
		reset.addActionListener(this);

	}

	public static void main(String[] args) {

		RemoteClient rc = new RemoteClient();
		rc.setSize(450, 700);
		rc.setVisible(true);
		rc.setTitle("RemoteClient::A Stochastic Model to Investigate Data Center Performance and QoS in IaaS Cloud Computing Systems");

	}

	@SuppressWarnings("static-access")
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == request) {

			JOptionPane op = new JOptionPane();

			if (cb.getSelectedItem().equals("SelectVM")) {

				op.showMessageDialog(null, "Please select Virtual machine...");

			} else {

				try {

					String name = op.showInputDialog(null, "Enter User-Name");

					Socket socket = new Socket(op.showInputDialog(null,
							"Enter Data-Centre IPAddress"), 7006);

					DataOutputStream dos = new DataOutputStream(
							socket.getOutputStream());

					dos.writeUTF(name);
					dos.writeUTF(t1.getText());
					dos.writeUTF(t2.getText());
					dos.writeUTF((String) cb.getSelectedItem());

					DataInputStream dis = new DataInputStream(
							socket.getInputStream());
					String resp = dis.readUTF();

					if (resp.equals("success")) {

						ta.setText(dis.readUTF());

					}

					if (resp.equals("block")) {

						op.showMessageDialog(null,
								"You are currently blocked  \n To access request to Cloud Server");
					}

					if (resp.equals("not")) {
						op.showMessageDialog(null,
								"The requested file not found in the Particular VM");
					}

					if (resp.equals("fail")) {

						op.showMessageDialog(
								null,
								"You are trying to Attack the file \n You are automatically blocked in Cloud Server");

					}

				} catch (Exception ex) {
					System.out.println(ex);
				}
			}
		}

		if (e.getSource() == reset) {

			t1.setText("");
			t2.setText("");
			cb.setSelectedItem("SelectVM");

		}
	}
}
