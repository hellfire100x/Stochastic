import javax.swing.*;

import java.net.Socket;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.*;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ViewPendingJobs extends JFrame implements ActionListener

{

	private static final long serialVersionUID = 1L;

	public Font f1 = new Font("Times New roman", Font.BOLD, 15);

	JScrollPane pane;
	JPanel c;
	JLabel imglabel;

	JButton send = new JButton("Send Pending Jobs");

	@SuppressWarnings({ "unchecked", "rawtypes" })
	ViewPendingJobs() {

		c = new JPanel();
		c.setLayout(null);

		c.setBackground(Color.LIGHT_GRAY);

		try {

			Vector heading = new Vector();

			heading.addElement("Owner-Name");
			heading.addElement("File-Name");
			heading.addElement("Secret-Key");

			Vector data = new Vector();

			ResultSet rs = DBConnection.getConnection().createStatement()
					.executeQuery("Select * from Pending");

			ResultSetMetaData rsm = rs.getMetaData();
			int col = rsm.getColumnCount();

			while (rs.next()) {

				Vector row = new Vector();

				for (int i = 1; i <= col; i++) {

					row.addElement(rs.getObject(i));

				}

				data.addElement(row);
			}

			JTable table = new JTable(data, heading);

			pane = new JScrollPane(table);

			pane.setBounds(25, 10, 350, 200);

			send.setBounds(100, 250, 200, 30);
			send.setFont(f1);
			c.add(pane);
			c.add(send);

			add(c);

			send.addActionListener(this);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@SuppressWarnings("static-access")
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == send) {

			JOptionPane op = new JOptionPane();

			byte[] b;

			try {

				String uname = op.showInputDialog(null, "Enter User-Name");
				String fname = op.showInputDialog(null, "Enter File name");

				String sk = "";

				String sql = "select * from Pending where Uname= '" + uname
						+ "' and Fname ='" + fname + "'  ";

				ResultSet rs = DBConnection.getConnection().createStatement()
						.executeQuery(sql);

				if (rs.next() == true) {
					sk = rs.getString(3);
				}

				FileInputStream fis = new FileInputStream("Pending/" + fname);
				b = new byte[fis.available()];
				fis.read(b);

				String ct = new String(b);

				SimpleDateFormat sdfDate = new SimpleDateFormat("dd/MM/yyyy");
				SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm:ss");

				String vm = "RemoteVm";

				Date now = new Date();

				String strDate = sdfDate.format(now);
				String strTime = sdfTime.format(now);

				String dt = strDate + "   " + strTime;

				DBConnection
						.getConnection()
						.createStatement()
						.executeUpdate(
								"insert into RemoteVm(Fname, Sk, dt) values('"
										+ fname + "','" + sk + "','" + dt
										+ "' )");

				Socket socket2 = new Socket("localhost", 5006);

				DataOutputStream dos = new DataOutputStream(
						socket2.getOutputStream());

				dos.writeUTF(vm);
				dos.writeUTF(fname);
				dos.writeUTF(sk);
				dos.writeUTF(ct);

				dos.close();
				socket2.close();
				fis.close();

				String query = " delete from Pending where Uname = '" + uname
						+ "'  and Fname = '" + fname + "'  ";

				DBConnection.getConnection().createStatement()
						.executeUpdate(query);

				File f = new File("Pending/" + fname);

				boolean success = f.delete();

				System.out.println(success);

				op.showMessageDialog(null,
						"File Uploaded to Cloud server Successfully");

				dispose();

				ViewPendingJobs v = new ViewPendingJobs();
				v.setTitle("Pendig-Files");
				v.setSize(400, 450);
				v.setVisible(true);
				v.setResizable(false);

			} catch (Exception ex) {
				System.out.println(ex);
			}

		}
	}
}