import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class CloudServer implements ActionListener {

	JFrame jf = new JFrame();
	Container c;

	JPanel p1 = new JPanel();
	JPanel p2 = new JPanel();

	public Font f1 = new Font("Times New roman", Font.BOLD, 15);

	JLabel v1 = new JLabel(" REMOTE USER VM");
	JLabel v2 = new JLabel("LOCAL USER VM");

	JButton files = new JButton("View Files");
	JButton attack = new JButton("View Attackers");
	JButton unblock = new JButton("Unblock user");

	ImageIcon im1, im2, dc, line1, line2, grn1, grn2;

	ImageIcon grndc;

	JLabel tov1 = new JLabel();
	JLabel tov2 = new JLabel();

	ImageIcon gvm1, gvm2;

	JLabel vm1 = new JLabel(), vm2 = new JLabel(), dc2 = new JLabel();

	CloudServer() {

		c = jf.getContentPane();
		c.setLayout(null);

		p1.setLayout(null);
		p2.setLayout(null);

		grndc = new ImageIcon("image/grndc.png");

		grn1 = new ImageIcon("image/tov1.png");
		grn2 = new ImageIcon("image/tov2.png");

		gvm1 = new ImageIcon("image/greenvm1.png");
		gvm2 = new ImageIcon("image/greenvm2.png");

		files.setBounds(25, 125, 150, 30);
		attack.setBounds(25, 180, 150, 30);
		unblock.setBounds(25, 235, 150, 30);

		files.setForeground(Color.blue);
		attack.setForeground(Color.blue);
		unblock.setForeground(Color.blue);

		files.setFont(f1);
		attack.setFont(f1);
		unblock.setFont(f1);

		line1 = new ImageIcon("image/blktov1.png");
		tov1.setIcon(line1);

		line2 = new ImageIcon("image/blktov2.png");
		tov2.setIcon(line2);

		im1 = new ImageIcon("image/vm1.png");
		vm1.setIcon(im1);

		im2 = new ImageIcon("image/vm2.png");
		vm2.setIcon(im2);

		dc = new ImageIcon("image/dc.png");
		dc2.setIcon(dc);

		vm1.setBounds(225, 50, 210, 200);
		vm2.setBounds(225, 250, 210, 200);

		dc2.setBounds(10, 220, 75, 75);
		tov1.setBounds(65, 150, 200, 75);
		tov2.setBounds(65, 285, 200, 75);

		Border b = BorderFactory.createLineBorder(Color.GRAY, 3);
		JLabel bord = new JLabel();
		bord.setBorder(b);
		bord.setBounds(20, 20, 810, 610);

		v1.setBounds(95, 100, 200, 25);
		v2.setBounds(95, 350, 200, 25);

		p2.setBounds(75, 50, 500, 500);
		p1.setBounds(600, 50, 200, 500);

		p1.setBackground(Color.LIGHT_GRAY);
		p2.setBackground(new Color(193, 102, 100));

		c.add(bord);

		p1.add(attack);
		p1.add(files);
		p1.add(unblock);

		p2.add(v1);
		p2.add(v2);
		p2.add(dc2);
		p2.add(tov1);
		p2.add(tov2);

		p2.add(vm1);
		p2.add(vm2);

		c.add(p1);
		c.add(p2);

		c.setBackground(Color.cyan);

		jf.setBounds(50, 25, 850, 680);
		jf.setVisible(true);
		jf.setTitle("Cloud Server::A Stochastic Model to Investigate Data Center Performance and QoS in IaaS Cloud Computing Systems");

		files.addActionListener(this);
		unblock.addActionListener(this);
		attack.addActionListener(this);

		int[] ports = new int[] { 5006, 6006, 7009, 7010 };

		for (int i = 0; i < 4; i++) {

			Thread t = new Thread(new PortListener(ports[i]));
			t.setName("Listener-" + ports[i]);
			t.start();

		}
	}

	public static void main(String[] args) {

		new CloudServer();

	}

	@SuppressWarnings("static-access")
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == files) {

			ViewFiles v = new ViewFiles();
			v.setTitle("File - Details");
			v.setSize(515, 400);
			v.setVisible(true);
			v.setResizable(false);

		}

		if (e.getSource() == unblock) {
			JOptionPane op = new JOptionPane();

			try {

				DBConnection
						.getConnection()
						.createStatement()
						.executeUpdate(
								"delete from Attacker where Uname = '"
										+ op.showInputDialog(null,
												"Enter Username") + "'  ");

				op.showMessageDialog(null, "User Un-Blocked successfully");

			} catch (Exception ex) {
				System.out.println(ex);
			}

		}

		if (e.getSource() == attack) {

			ViewAttackers v = new ViewAttackers();
			v.setTitle("Attacker - Details");
			v.setSize(515, 400);
			v.setVisible(true);
			v.setResizable(false);

		}
	}

	class PortListener implements Runnable {

		int port;

		public PortListener(int port) {
			this.port = port;
		}

		public void run() {

			if (this.port == 5006) {

				try {

					ServerSocket server = new ServerSocket(5006);
					Socket socket;

					while (true) {

						socket = server.accept();

						DataInputStream dis = new DataInputStream(
								socket.getInputStream());
						String vvmm = dis.readUTF();
						String fname = dis.readUTF();
						String sk = dis.readUTF();
						String ct = dis.readUTF();

						if (vvmm.equals("RemoteVm")) {

							dc2.setIcon(grndc);

							Thread.sleep(2500);

							tov1.setIcon(grn1);

							SimpleDateFormat sdfDate = new SimpleDateFormat(
									"dd/MM/yyyy");
							SimpleDateFormat sdfTime = new SimpleDateFormat(
									"HH:mm:ss");

							Date now = new Date();

							String strDate = sdfDate.format(now);
							String strTime = sdfTime.format(now);

							String dt = strDate + "   " + strTime;

							DBConnection
									.getConnection()
									.createStatement()
									.executeUpdate(
											"insert into Server values(  '"
													+ vvmm + "' ,'" + fname
													+ "','" + sk + "','" + dt
													+ "' )");

							new FileOutputStream("CloudServer/" + fname)
									.write(ct.getBytes());

							Thread.sleep(2500);
							vm1.setIcon(gvm1);

							Thread.sleep(2500);

							line1 = new ImageIcon("image/blktov1.png");
							tov1.setIcon(line1);

							line2 = new ImageIcon("image/blktov2.png");
							tov2.setIcon(line2);

							im1 = new ImageIcon("image/vm1.png");
							vm1.setIcon(im1);

							im2 = new ImageIcon("image/vm2.png");
							vm2.setIcon(im2);

							dc = new ImageIcon("image/dc.png");
							dc2.setIcon(dc);

						}
					}

				} catch (Exception ex) {
					System.out.println(ex);
				}

			}

			if (this.port == 6006) {

				try {

					ServerSocket server = new ServerSocket(6006);
					Socket socket;

					while (true) {
						socket = server.accept();

						DataInputStream dis = new DataInputStream(
								socket.getInputStream());
						String virt = dis.readUTF();

						if (virt.equals("LocalVm")) {

							dc2.setIcon(grndc);

							String fname = dis.readUTF();
							String sk = dis.readUTF();
							String cont = dis.readUTF();

							SimpleDateFormat sdfDate = new SimpleDateFormat(
									"dd/MM/yyyy");
							SimpleDateFormat sdfTime = new SimpleDateFormat(
									"HH:mm:ss");

							Date now = new Date();

							String strDate = sdfDate.format(now);
							String strTime = sdfTime.format(now);

							String dt = strDate + "   " + strTime;

							Thread.sleep(2500);
							tov2.setIcon(grn2);
							Thread.sleep(2500);

							DBConnection
									.getConnection()
									.createStatement()
									.executeUpdate(
											"insert into Server values(  '"
													+ virt + "' ,'" + fname
													+ "','" + sk + "','" + dt
													+ "' )");

							new FileOutputStream("CloudServer/" + fname)
									.write(cont.getBytes());

							Thread.sleep(2500);
							vm2.setIcon(gvm2);

							Thread.sleep(2500);

							line1 = new ImageIcon("image/blktov1.png");
							tov1.setIcon(line1);

							line2 = new ImageIcon("image/blktov2.png");
							tov2.setIcon(line2);

							im1 = new ImageIcon("image/vm1.png");
							vm1.setIcon(im1);

							im2 = new ImageIcon("image/vm2.png");
							vm2.setIcon(im2);

							dc = new ImageIcon("image/dc.png");
							dc2.setIcon(dc);

						}
					}
				} catch (Exception ex) {
					System.out.println(ex);
				}

			}

			if (this.port == 7009) {

				try {

					ServerSocket server = new ServerSocket(7009);
					Socket socket;

					while (true) {
						socket = server.accept();

						DataInputStream dis = new DataInputStream(
								socket.getInputStream());
						String vm = dis.readUTF();
						String name = dis.readUTF();
						String fname = dis.readUTF();
						String sk = dis.readUTF();

						DataOutputStream dos = new DataOutputStream(
								socket.getOutputStream());

						ResultSet set = DBConnection
								.getConnection()
								.createStatement()
								.executeQuery(
										"select * from Attacker where Uname = '"
												+ name + "'  ");

						if (set.next() == true) {

							dos.writeUTF("block");

						} else {

							ResultSet rs = DBConnection
									.getConnection()
									.createStatement()
									.executeQuery(
											"select * from Server where Vmachine = '"
													+ vm + "'  and Fname ='"
													+ fname
													+ "' and Secret = '" + sk
													+ "'   ");

							if (rs.next() == true) {

								FileInputStream fis = new FileInputStream(
										"CloudServer/" + fname);
								byte[] b = new byte[fis.available()];
								fis.read(b);
								String data = new String(b);

								dos.writeUTF("yes");
								dos.writeUTF(data);

							} else {

								SimpleDateFormat sdfDate = new SimpleDateFormat(
										"dd/MM/yyyy");
								SimpleDateFormat sdfTime = new SimpleDateFormat(
										"HH:mm:ss");

								Date now = new Date();

								String strDate = sdfDate.format(now);
								String strTime = sdfTime.format(now);

								String dt = strDate + "   " + strTime;

								DBConnection
										.getConnection()
										.createStatement()
										.executeUpdate(
												"insert into attacker values('"
														+ name + "' ,'" + vm
														+ "', '" + fname
														+ "','" + sk + "','"
														+ dt + "') ");

								dos.writeUTF("no");
							}
						}
					}
				} catch (Exception ex) {
					System.out.println(ex);
				}

			}

			if (this.port == 7010) {

				try {

					ServerSocket server = new ServerSocket(7010);
					Socket socket;

					while (true) {

						socket = server.accept();

						DataInputStream dis = new DataInputStream(
								socket.getInputStream());

						String vm = dis.readUTF();
						String name = dis.readUTF();
						String fname = dis.readUTF();
						String sk = dis.readUTF();

						DataOutputStream dos = new DataOutputStream(
								socket.getOutputStream());

						ResultSet set = DBConnection
								.getConnection()
								.createStatement()
								.executeQuery(
										"select * from Attacker where Uname ='"
												+ name + "'  ");

						if (set.next() == true) {
							dos.writeUTF("block");
						} else {
							ResultSet rs = DBConnection
									.getConnection()
									.createStatement()
									.executeQuery(
											"select * from Server where  Fname ='"
													+ fname
													+ "' and Secret = '" + sk
													+ "'   ");

							while (rs.next() == true) {

								FileInputStream fis = new FileInputStream(
										"CloudServer/" + fname);
								byte[] b = new byte[fis.available()];
								fis.read(b);
								String data = new String(b);

								dos.writeUTF("yes");
								dos.writeUTF(data);

							}

							if (rs.next() != true) {

								SimpleDateFormat sdfDate = new SimpleDateFormat(
										"dd/MM/yyyy");
								SimpleDateFormat sdfTime = new SimpleDateFormat(
										"HH:mm:ss");

								Date now = new Date();

								String strDate = sdfDate.format(now);
								String strTime = sdfTime.format(now);

								String dt = strDate + "   " + strTime;

								DBConnection
										.getConnection()
										.createStatement()
										.executeUpdate(
												"insert into attacker values('"
														+ name + "' ,'" + vm
														+ "', '" + fname
														+ "','" + sk + "','"
														+ dt + "') ");

								dos.writeUTF("no");
							}
						}
					}
				} catch (Exception ex) {
					System.out.println(ex);
				}
			}
		}
	}
}
