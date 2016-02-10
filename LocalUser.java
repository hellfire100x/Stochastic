import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class LocalUser extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	Container c;

	public Font f1 = new Font("Times New roman", Font.BOLD, 15);

	JTextField t1 = new JTextField();
	JTextField t2 = new JTextField();
	JTextArea ta = new JTextArea();
	JScrollPane pane = new JScrollPane();
	JButton submit = new JButton("Upload");
	JButton browse = new JButton("Browse");

	LocalUser() {

		c = getContentPane();
		c.setLayout(null);
		c.setBackground(Color.YELLOW);

		t1.setBounds(75, 100, 175, 25);
		t2.setBounds(75, 150, 175, 25);
		
		ImageIcon banner = new ImageIcon(this.getClass().getResource("LocalUser.png"));
		JLabel title=new JLabel();
		title.setIcon(banner);
		title.setBounds(10, -10, 850, 100);
		

		t1.setFont(f1);
		t2.setFont(f1);
		ta.setFont(f1);

		t1.setEditable(false);
		t2.setEditable(false);

		ta.setRows(10);
		ta.setColumns(10);
		pane.setViewportView(ta);

		pane.setBounds(75, 200, 350, 350);
		browse.setBounds(500, 155, 125, 30);
		submit.setBounds(500, 210, 125, 30);

		c.add(pane);
		c.add(browse);
		c.add(submit);
		c.add(t1);
		c.add(t2);
		c.add(title);

		setSize(650, 625);
		setVisible(true);
		setTitle("Local User::A Stochastic Model to Investigate Data Center Performance and QoS in IaaS Cloud Computing Systems");

		browse.addActionListener(this);
		submit.addActionListener(this);

	}

	public static void main(String[] args) {
		new LocalUser();
	}

	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == browse) {

			JFileChooser chooser = new JFileChooser();

			try {

				File f = new File(new File("filename.txt").getCanonicalPath());

				chooser.setSelectedFile(f);

			} catch (IOException e1) {
				e1.printStackTrace();
			}

			int retval = chooser.showOpenDialog(browse);

			if (retval == JFileChooser.APPROVE_OPTION) {

				File field = chooser.getSelectedFile();

				t1.setText(field.getName());

			}

			File curFile = chooser.getSelectedFile();

			try {

				FileInputStream fstream = new FileInputStream(curFile);

				DataInputStream ins = new DataInputStream(fstream);

				BufferedReader br = new BufferedReader(new InputStreamReader(
						ins));

				StringBuffer buffer = new StringBuffer();

				String strLine;
				while ((strLine = br.readLine()) != null) {

					buffer.append(strLine + "\n");

				}

				t2.setText(Integer.toString(new KeyGenerator().getKeys()));

				ta.setText(buffer.toString());

			} catch (Exception e1) {
				System.err.println("Error: " + e1.getMessage());
			}
		}

		if (e.getSource() == submit) {

			try {

				Socket socket = new Socket("localhost", 4005);

				DataOutputStream dos = new DataOutputStream(
						socket.getOutputStream());

				dos.writeUTF("Local");
				dos.writeUTF(t1.getText());
				dos.writeUTF(t2.getText());
				dos.writeUTF(ta.getText());

				JOptionPane.showMessageDialog(null,
						"File uploaded successfully");

			} catch (Exception ex) {
				System.out.println(ex);
			}

		}
	}
}
