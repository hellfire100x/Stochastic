import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
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
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class DataCentre extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	public Font f1 = new Font("Times New roman", Font.BOLD, 15);
	public Font f = new Font("Times New roman", Font.BOLD, 18);

	JMenuBar mbr = new JMenuBar();

	JMenu file = new JMenu("File");

	JMenuItem item1 = new JMenuItem("Restrict User");
	JMenuItem item2 = new JMenuItem("Resolve use");

	JPanel p = new JPanel();

	JButton vm = new JButton("Schedule VM");

	JLabel local = new JLabel("Locally accepted jobs");
	JLabel remote = new JLabel("Remotely accepted jobs");

	ImageIcon im, im2, scd, scdgrn, blk, bw, bw1, bwgrn, cross, cross2, q2b,
			error, adm, admin, arr1, arr2, garr1, garr2, red, redq, centre;

	JLabel queue = new JLabel();
	JLabel queue2 = new JLabel();
	JLabel sched = new JLabel();

	JLabel dcent = new JLabel();

	JLabel redq1 = new JLabel();

	JLabel redd = new JLabel();
	JLabel arw1 = new JLabel();
	JLabel arw2 = new JLabel();

	JLabel admn = new JLabel();

	JLabel blkln = new JLabel();
	JLabel band = new JLabel();
	JLabel Lline = new JLabel();

	JLabel line2 = new JLabel();

	JLabel l1 = new JLabel("1"), l2 = new JLabel("2"), l3 = new JLabel("3"),
			l4 = new JLabel("4"), l5 = new JLabel("5"), l6 = new JLabel("6"),
			l7 = new JLabel("7"), l8 = new JLabel(" . . . ");

	JLabel q1 = new JLabel("1"), q2 = new JLabel("2"), q3 = new JLabel("3"),
			q4 = new JLabel("4"), q5 = new JLabel(". . .");

	JButton bwth = new JButton("Assign Bandwidth");
	JButton alloc = new JButton("Create VM");
	JButton remfile = new JButton("View Remote-Files");
	JButton locfile = new JButton("View Local-Files");
	JButton view = new JButton("View Pending Jobs");
	JButton vmview = new JButton("View VM's");
	JButton bandw = new JButton("View BandWidth");
	JButton reject = new JButton("Rejected Jobs");
	
	
	ImageIcon grnlin;

	int gid;

	DataCentre() {

		p.setLayout(null);
		p.setBackground(new Color(10,150,180));

		setJMenuBar(mbr);
		mbr.add(file);
		file.add(item1);
		file.add(item2);

		adm = new ImageIcon("image/adm.png");

		admn.setIcon(adm);

		admin = new ImageIcon("image/admin.png");

		im = new ImageIcon("image/queue.png");
		queue.setIcon(im);

		im2 = new ImageIcon("image/2queue.png");
		queue2.setIcon(im2);

		scdgrn = new ImageIcon("image/grnschedule.png");

		error = new ImageIcon("image/error.png");

		scd = new ImageIcon("image/schedule.png");
		sched.setIcon(scd);

		grnlin = new ImageIcon("image/grnline.png");

		blk = new ImageIcon("image/black.png");
		blkln.setIcon(blk);

		line2.setIcon(blk);

		bwgrn = new ImageIcon("image/bandgreen.png");

		bw1 = new ImageIcon("image/band.png");
		bw = new ImageIcon("image/band.png");
		band.setIcon(bw);

		cross2 = new ImageIcon("image/greenL.png");
		red = new ImageIcon("image/redL.png");

		cross = new ImageIcon("image/blackL.png");
		Lline.setIcon(cross);

		arr1 = new ImageIcon("image/toq1.png");
		arw1.setIcon(arr1);

		arr2 = new ImageIcon("image/toq2.png");
		arw2.setIcon(arr2);

		redq = new ImageIcon("image/redq1.png");

		garr1 = new ImageIcon("image/greenq1.png");
		garr2 = new ImageIcon("image/greenq2.png");

		centre = new ImageIcon("image/centre.png");
		dcent.setIcon(centre);
		dcent.setBounds(75, -100, 300, 300);

		l1.setFont(f);
		l2.setFont(f);
		l3.setFont(f);
		l4.setFont(f);
		l5.setFont(f);
		l6.setFont(f);
		l7.setFont(f);
		l8.setFont(f);

		q1.setFont(f);
		q2.setFont(f);
		q3.setFont(f);
		q4.setFont(f);
		q5.setFont(f);

		local.setFont(f1);
		remote.setFont(f1);

		remfile.setFont(f);
		locfile.setFont(f);
		alloc.setFont(f);
		bwth.setFont(f);
		reject.setFont(f);

		view.setFont(f);
		vmview.setFont(f);
		bandw.setFont(f);

		alloc.setBounds(40, 550, 190, 30);
		bwth.setBounds(265, 550, 190, 30);

		remfile.setBounds(40, 510, 190, 30);
		locfile.setBounds(265, 510, 190, 30);
		view.setBounds(490, 550, 190, 30);
		vmview.setBounds(490, 510, 190, 30);
		bandw.setBounds(690, 510, 200, 30);
		reject.setBounds(700, 550, 190, 30);

		l1.setBounds(400, 150, 50, 25);
		l2.setBounds(360, 150, 50, 25);
		l3.setBounds(320, 150, 50, 25);
		l4.setBounds(280, 150, 50, 25);
		l5.setBounds(240, 150, 50, 25);
		l6.setBounds(200, 150, 50, 25);
		l7.setBounds(160, 150, 50, 25);
		l8.setBounds(120, 147, 50, 25);

		admn.setBounds(20, 170, 125, 75);

		q1.setBounds(440, 398, 50, 50);
		q2.setBounds(410, 398, 50, 50);
		q3.setBounds(375, 398, 50, 50);
		q4.setBounds(340, 398, 50, 50);
		q5.setBounds(298, 392, 50, 50);

		local.setBounds(450, 120, 250, 35);
		remote.setBounds(170, 260, 250, 35);

		Lline.setBounds(150, 150, 200, 350);

		queue.setBounds(100, 140, 350, 50);
		sched.setBounds(600, 130, 220, 75);
		blkln.setBounds(450, 130, 300, 75);

		line2.setBounds(475, 380, 300, 75);
		band.setBounds(625, 380, 220, 75);
		queue2.setBounds(270, 320, 250, 200);

		arw1.setBounds(52, 147, 75, 50);
		arw2.setBounds(100, 185, 75, 50);

		Border b = BorderFactory.createRaisedBevelBorder();
		JLabel border = new JLabel();
		border.setBorder(b);
		border.setBounds(15, 50, 900, 550);

		p.add(l1);
		p.add(l2);
		p.add(l3);
		p.add(l4);
		p.add(l5);
		p.add(l6);
		p.add(l7);
		p.add(l8);

		p.add(line2);
		p.add(alloc);
		p.add(bwth);

		p.add(remfile);
		p.add(locfile);
		p.add(reject);

		p.add(view);
		p.add(vmview);

		p.add(arw1);
		p.add(arw2);

		p.add(q1);
		p.add(q2);
		p.add(q3);
		p.add(q4);
		p.add(q5);

		p.add(dcent);

		p.add(border);

		p.add(local);
		p.add(remote);

		p.add(band);
		p.add(blkln);
		p.add(sched);
		p.add(Lline);

		p.add(queue2);
		p.add(queue);

		p.add(admn);
		p.add(bandw);
		add(p);
		bandw.addActionListener(this);
		alloc.addActionListener(this);
		bwth.addActionListener(this);
		remfile.addActionListener(this);
		locfile.addActionListener(this);

		item1.addActionListener(this);
		item2.addActionListener(this);

		reject.addActionListener(this);
		view.addActionListener(this);
		vmview.addActionListener(this);

		int[] ports = new int[] { 4005, 5005, 7006 };

		for (int i = 0; i < 3; i++) {
			Thread t = new Thread(new PortListener(ports[i]));
			t.setName("Listener-" + ports[i]);
			t.start();

		}
	}

	public static void main(String[] args) {

		DataCentre dc = new DataCentre();
		dc.setSize(950,680);
		dc.setTitle("Data Centre::A Stochastic Model to Investigate Data Center Performance and QoS in IaaS Cloud Computing Systems");
		dc.setVisible(true);

	}

	@SuppressWarnings("static-access")
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == bandw) {
			
			ViewBandwidth v11 = new ViewBandwidth();
			v11.setTitle("Virtual VM Bandwidth Details");
			v11.setSize(400, 300);
			v11.setVisible(true);
			v11.setResizable(false);
			
		}
		
		
		if (e.getSource() == item1) {

			try {

				DBConnection
						.getConnection()
						.createStatement()
						.executeUpdate(
								"insert into restrict values( '"
										+ JOptionPane
												.showInputDialog(null,
														"Enter User-Name to be Restrict")
										+ "'  )");

			} catch (Exception ex) {
				System.out.println(ex);
			}
		}

		if (e.getSource() == item2) {

			try {

				DBConnection
						.getConnection()
						.createStatement()
						.executeUpdate(
								"delete from restrict where Uname = '"
										+ JOptionPane
												.showInputDialog(null,
														"Enter user name to be resolve")
										+ "'  ");
				
				
				JOptionPane.showMessageDialog(this, "User Unblocked Successfully....");

			} catch (Exception ex) {
				System.out.println(ex);
			}

		}

		if (e.getSource() == alloc) {

			String[] vm = { "Select", "LocalVm", "RemoteVm" };

			String Svm = (String) JOptionPane.showInputDialog(null,
					"Select VM", "Select", JOptionPane.QUESTION_MESSAGE, null,
					vm, vm[0]);

			MemoryAlloc m = new MemoryAlloc();
			m.t1.setText(Svm);
			m.t1.setEditable(false);

		}

		if (e.getSource() == bwth) {

			JOptionPane op = new JOptionPane();

			String[] vm = { "Select", "LocalVm", "RemoteVm" };
			String Svm = (String) JOptionPane
					.showInputDialog(null, "Select VM", "Select",
							op.QUESTION_MESSAGE, null, vm, vm[0]);

			try {

				DBConnection
						.getConnection()
						.createStatement()
						.executeUpdate(
								"update Bandwidth set Bandwidth = '"
										+ op.showInputDialog(null,
												"Enter Bandwidth")
										+ "' where VName = '" + Svm + "'   ");

				op.showMessageDialog(null, "Bandwidth Assigned");

			} catch (Exception ex) {
				System.out.println(ex);
			}
		}

		if (e.getSource() == remfile) {

			RemoteFiles v = new RemoteFiles();
			v.setTitle("Remote VM Files");
			v.setSize(515, 400);
			v.setVisible(true);
			v.setResizable(false);

		}

		if (e.getSource() == locfile) {

			LocalFiles v = new LocalFiles();
			v.setTitle("Local VM Files");
			v.setSize(515, 400);
			v.setVisible(true);
			v.setResizable(false);

		}

		if (e.getSource() == view) {

			ViewPendingJobs v = new ViewPendingJobs();
			v.setTitle("Pendig-Files");
			v.setSize(400, 450);
			v.setVisible(true);
			v.setResizable(false);

		}

		if (e.getSource() == vmview) {

			ViewVirtual v = new ViewVirtual();
			v.setTitle("Virtual VM Details");
			v.setSize(400, 300);
			v.setVisible(true);
			v.setResizable(false);

		}

		if (e.getSource() == reject) {

			RejectedJobs v = new RejectedJobs();
			v.setTitle("Rejected Files");
			v.setSize(515, 350);
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

			if (this.port == 4005) {

				try {

					ServerSocket server = new ServerSocket(4005);
					Socket socket;

					while (true) {
						socket = server.accept();
						DataInputStream dis = new DataInputStream(
								socket.getInputStream());
						String request = dis.readUTF();

						admn.setIcon(admin);

						if (request.equals("Local")) {

							String fname = dis.readUTF();
							String sk = dis.readUTF();
							String ct = dis.readUTF();

							int nb = ct.getBytes("utf8").length;

							int bwt = 0;

							String vm = "LocalVm";

							Thread.sleep(2500);
							arw1.setIcon(garr1);

							ResultSet rs = DBConnection
									.getConnection()
									.createStatement()
									.executeQuery(
											"Select * from Bandwidth where VName = '"
													+ vm + "' ");

							if (rs.next() == true) {

								bwt = Integer.parseInt(rs.getString(2));

							}

							int th = 0;
							int total = 0;

							if (bwt > nb) {

								ResultSet resultSet = DBConnection
										.getConnection()
										.createStatement()
										.executeQuery(
												"select * from Allocate where VM = '"
														+ vm + "' ");

								if (resultSet.next() == true) {

									total = Integer.parseInt(resultSet
											.getString(2));

									th = Integer.parseInt(resultSet
											.getString(3));
								}

								if (th > nb) {

									int x = 0;

									ResultSet set = DBConnection
											.getConnection()
											.createStatement()
											.executeQuery(
													"select count(*) from LocalVm  ");

									while (set.next() == true) {

										x = Integer.parseInt(set.getString(1));

										gid = x + 1;

										if (x == 0) {
											l1.setForeground(Color.GREEN);
										}
										if (x == 1) {
											l2.setForeground(Color.GREEN);
										}
										if (x == 2) {
											l3.setForeground(Color.GREEN);
										}
										if (x == 3) {
											l4.setForeground(Color.GREEN);
										}
										if (x == 4) {
											l4.setForeground(Color.GREEN);
										}
										if (x == 5) {
											l6.setForeground(Color.GREEN);
										}
										if (x == 6) {
											l7.setForeground(Color.GREEN);
										}
										if (x == 7 || x > 7) {
											l8.setForeground(Color.GREEN);
										}

										Thread.sleep(750);

										int remain = total - nb;

										if (remain < th) {

											SimpleDateFormat sdfDate = new SimpleDateFormat(
													"dd/MM/yyyy");
											SimpleDateFormat sdfTime = new SimpleDateFormat(
													"HH:mm:ss");

											Date now = new Date();

											String strDate = sdfDate
													.format(now);
											String strTime = sdfTime
													.format(now);

											String dt = strDate + "   "
													+ strTime;

											String resn = "Threshold";

											DBConnection
													.getConnection()
													.createStatement()
													.executeUpdate(
															"insert into Rejected values( '"
																	+ resn
																	+ "', '"
																	+ vm
																	+ "', '"
																	+ fname
																	+ "','"
																	+ dt
																	+ "' )");

											blkln.setIcon(error);

											JOptionPane
													.showMessageDialog(null,
															"Errored jobs dude to Less Threshold value");

											Thread.sleep(2500);

											adm = new ImageIcon("image/adm.png");
											im = new ImageIcon(
													"image/queue.png");
											im2 = new ImageIcon(
													"image/2queue.png");
											scd = new ImageIcon(
													"image/schedule.png");
											blk = new ImageIcon(
													"image/black.png");
											bw1 = new ImageIcon(
													"image/band.png");
											cross = new ImageIcon(
													"image/blackL.png");
											arr1 = new ImageIcon(
													"image/toq1.png");
											arr2 = new ImageIcon(
													"image/toq2.png");

											local.setForeground(Color.black);
											remote.setForeground(Color.black);

											line2.setIcon(blk);
											admn.setIcon(adm);
											queue.setIcon(im);
											queue2.setIcon(im2);
											sched.setIcon(scd);
											blkln.setIcon(blk);
											line2.setIcon(blk);
											band.setIcon(bw1);
											Lline.setIcon(cross);
											arw1.setIcon(arr1);
											arw2.setIcon(arr2);

										} else {

											Thread.sleep(2500);

											blkln.setIcon(grnlin);

											Thread.sleep(750);

											String rmn = Integer
													.toString(remain);

											DBConnection
													.getConnection()
													.createStatement()
													.executeUpdate(
															"update Allocate set Memory = '"
																	+ rmn
																	+ "' where VM = '"
																	+ vm + "' ");

											Thread.sleep(750);
											sched.setIcon(scdgrn);

											SimpleDateFormat sdfDate = new SimpleDateFormat(
													"dd/MM/yyyy");
											SimpleDateFormat sdfTime = new SimpleDateFormat(
													"HH:mm:ss");

											Date now = new Date();

											String strDate = sdfDate
													.format(now);
											String strTime = sdfTime
													.format(now);

											String dt = strDate + "   "
													+ strTime;

											DBConnection
													.getConnection()
													.createStatement()
													.executeUpdate(
															"insert into LocalVm(Fname, Sk, dt) values('"
																	+ fname
																	+ "','"
																	+ sk
																	+ "','"
																	+ dt
																	+ "' )");

											Socket socket2 = new Socket(
													"localhost", 6006);

											DataOutputStream dos = new DataOutputStream(
													socket2.getOutputStream());

											dos.writeUTF(vm);
											dos.writeUTF(fname);
											dos.writeUTF(sk);
											dos.writeUTF(ct);

											Thread.sleep(2500);

											adm = new ImageIcon("image/adm.png");
											im = new ImageIcon(
													"image/queue.png");
											im2 = new ImageIcon(
													"image/2queue.png");
											scd = new ImageIcon(
													"image/schedule.png");
											blk = new ImageIcon(
													"image/black.png");
											bw1 = new ImageIcon(
													"image/band.png");
											cross = new ImageIcon(
													"image/blackL.png");
											arr1 = new ImageIcon(
													"image/toq1.png");
											arr2 = new ImageIcon(
													"image/toq2.png");

											local.setForeground(Color.black);
											remote.setForeground(Color.black);

											line2.setIcon(blk);
											admn.setIcon(adm);
											queue.setIcon(im);
											queue2.setIcon(im2);
											sched.setIcon(scd);
											blkln.setIcon(blk);
											line2.setIcon(blk);
											band.setIcon(bw1);
											Lline.setIcon(cross);
											arw1.setIcon(arr1);
											arw2.setIcon(arr2);

										}
									}
								} else {

									arw1.setIcon(redq);

									Thread.sleep(2500);

									blkln.setIcon(error);

									SimpleDateFormat sdfDate = new SimpleDateFormat(
											"dd/MM/yyyy");
									SimpleDateFormat sdfTime = new SimpleDateFormat(
											"HH:mm:ss");

									Date now = new Date();

									String strDate = sdfDate.format(now);
									String strTime = sdfTime.format(now);

									String dt = strDate + "   " + strTime;

									String resn = "Threshold";

									DBConnection
											.getConnection()
											.createStatement()
											.executeUpdate(
													"insert into Rejected values( '"
															+ resn + "', '"
															+ vm + "', '"
															+ fname + "','"
															+ dt + "' )");

									JOptionPane
											.showMessageDialog(
													null,
													"Errored jobs dude to Less Threshold value \n File size is greaeter than Threshold value");
									Thread.sleep(1500);

									adm = new ImageIcon("image/adm.png");
									im = new ImageIcon("image/queue.png");
									im2 = new ImageIcon("image/2queue.png");
									scd = new ImageIcon("image/schedule.png");
									blk = new ImageIcon("image/black.png");
									bw1 = new ImageIcon("image/band.png");
									cross = new ImageIcon("image/blackL.png");
									arr1 = new ImageIcon("image/toq1.png");
									arr2 = new ImageIcon("image/toq2.png");

									local.setForeground(Color.black);
									remote.setForeground(Color.black);

									line2.setIcon(blk);
									admn.setIcon(adm);
									queue.setIcon(im);
									queue2.setIcon(im2);
									sched.setIcon(scd);
									blkln.setIcon(blk);
									line2.setIcon(blk);
									band.setIcon(bw1);
									Lline.setIcon(cross);
									arw1.setIcon(arr1);
									arw2.setIcon(arr2);

								}

							} else {

								arw1.setIcon(redq);
								Thread.sleep(2500);
								blkln.setIcon(error);

								SimpleDateFormat sdfDate = new SimpleDateFormat(
										"dd/MM/yyyy");
								SimpleDateFormat sdfTime = new SimpleDateFormat(
										"HH:mm:ss");

								Date now = new Date();

								String strDate = sdfDate.format(now);
								String strTime = sdfTime.format(now);

								String dt = strDate + "   " + strTime;

								String resn = "Bandwidth";

								DBConnection
										.getConnection()
										.createStatement()
										.executeUpdate(
												"insert into Rejected values( '"
														+ resn + "', '" + vm
														+ "', '" + fname
														+ "','" + dt + "' )");

								JOptionPane
										.showMessageDialog(null,
												"Errored jobs dude to Less B/W / File size is greater than bandwidth ");

								Thread.sleep(2500);

								adm = new ImageIcon("image/adm.png");
								im = new ImageIcon("image/queue.png");
								im2 = new ImageIcon("image/2queue.png");
								scd = new ImageIcon("image/schedule.png");
								blk = new ImageIcon("image/black.png");
								bw1 = new ImageIcon("image/band.png");
								cross = new ImageIcon("image/blackL.png");
								arr1 = new ImageIcon("image/toq1.png");
								arr2 = new ImageIcon("image/toq2.png");

								local.setForeground(Color.black);
								remote.setForeground(Color.black);

								line2.setIcon(blk);
								admn.setIcon(adm);
								queue.setIcon(im);
								queue2.setIcon(im2);
								sched.setIcon(scd);
								blkln.setIcon(blk);
								line2.setIcon(blk);
								band.setIcon(bw1);
								Lline.setIcon(cross);
								arw1.setIcon(arr1);
								arw2.setIcon(arr2);

							}
						}
					}
				} catch (Exception ex) {
					System.out.println(ex);
				}
			}

			if (this.port == 5005) {

				try {

					ServerSocket server = new ServerSocket(5005);
					Socket socket;

					while (true) {

						socket = server.accept();
						DataInputStream dis = new DataInputStream(
								socket.getInputStream());
						String request = dis.readUTF();

						int total = 0;
						int bw = 0;

						admn.setIcon(admin);

						if (request.equals("Remote")) {

							String uname = dis.readUTF();
							String fname = dis.readUTF();
							String sk = dis.readUTF();
							String ct = dis.readUTF();

							ResultSet rset = DBConnection
									.getConnection()
									.createStatement()
									.executeQuery(
											"select * from restrict where Uname = '"
													+ uname + "' ");

							if (rset.next() == true) {

								String sql = "insert into pending values('"
										+ uname + "', '" + fname + "','" + sk
										+ "' )";

								DBConnection.getConnection().createStatement()
										.executeUpdate(sql);

								new FileOutputStream("Pending/" + fname)
										.write(ct.getBytes());

								JOptionPane
										.showMessageDialog(
												null,
												"The user  "
														+ uname
														+ "  has been restricted , \n the sending file is waiting for Admin  permission  ");

								Thread.sleep(2500);

								adm = new ImageIcon("image/adm.png");
								im = new ImageIcon("image/queue.png");
								im2 = new ImageIcon("image/2queue.png");
								scd = new ImageIcon("image/schedule.png");
								blk = new ImageIcon("image/black.png");
								bw1 = new ImageIcon("image/band.png");
								cross = new ImageIcon("image/blackL.png");
								arr1 = new ImageIcon("image/toq1.png");
								arr2 = new ImageIcon("image/toq2.png");

								local.setForeground(Color.black);
								remote.setForeground(Color.black);

								line2.setIcon(blk);
								admn.setIcon(adm);
								queue.setIcon(im);
								queue2.setIcon(im2);
								sched.setIcon(scd);
								blkln.setIcon(blk);
								line2.setIcon(blk);
								band.setIcon(bw1);
								Lline.setIcon(cross);
								arw1.setIcon(arr1);
								arw2.setIcon(arr2);

							} else {

								int nb = ct.getBytes("utf8").length;

								String vm = "RemoteVm";

								Thread.sleep(2500);
								arw2.setIcon(garr2);

								ResultSet rs = DBConnection
										.getConnection()
										.createStatement()
										.executeQuery(
												"Select * from Bandwidth where VName = '"
														+ vm + "' ");

								if (rs.next() == true) {

									bw = Integer.parseInt(rs.getString(2));

								}

								int th = 0;

								if (bw > nb) {

									ResultSet resultSet = DBConnection
											.getConnection()
											.createStatement()
											.executeQuery(
													"select * from Allocate where VM = '"
															+ vm + "' ");

									if (resultSet.next() == true) {

										total = Integer.parseInt(resultSet
												.getString(2));

										th = Integer.parseInt(resultSet
												.getString(3));

									}

									Thread.sleep(2500);
									Lline.setIcon(cross2);

									Thread.sleep(2500);
									remote.setForeground(new Color(10, 250, 10));

									Thread.sleep(2500);

									if (th > nb) {

										int z = 0;

										ResultSet set = DBConnection
												.getConnection()
												.createStatement()
												.executeQuery(
														"select count(*) from RemoteVm  ");

										while (set.next() == true) {

											z = Integer.parseInt(set
													.getString(1));

											gid = z + 1;

											if (z == 0) {
												q1.setForeground(Color.GREEN);
											}
											if (z == 1) {
												q2.setForeground(Color.GREEN);
											}
											if (z == 2) {
												q3.setForeground(Color.GREEN);
											}
											if (z == 3) {
												q4.setForeground(Color.GREEN);
											}
											if (z == 4 || z > 4) {

												q5.setForeground(Color.GREEN);

											}
										}

										Thread.sleep(2500);

										int remain = total - nb;

										if (remain < th) {

											Lline.setIcon(red);

											Thread.sleep(1000);

											SimpleDateFormat sdfDate = new SimpleDateFormat(
													"dd/MM/yyyy");
											SimpleDateFormat sdfTime = new SimpleDateFormat(
													"HH:mm:ss");

											Date now = new Date();

											String strDate = sdfDate
													.format(now);
											String strTime = sdfTime
													.format(now);

											String dt = strDate + "   "
													+ strTime;

											String resn = "Threshold";

											DBConnection
													.getConnection()
													.createStatement()
													.executeUpdate(
															"insert into Rejected values( '"
																	+ resn
																	+ "', '"
																	+ vm
																	+ "', '"
																	+ fname
																	+ "','"
																	+ dt
																	+ "' )");

											remote.setForeground(Color.red);
											line2.setIcon(error);

											JOptionPane
													.showMessageDialog(null,
															"Errored jobs due to Less Threshold value");

											Thread.sleep(2500);

											adm = new ImageIcon("image/adm.png");
											im = new ImageIcon(
													"image/queue.png");
											im2 = new ImageIcon(
													"image/2queue.png");
											scd = new ImageIcon(
													"image/schedule.png");
											blk = new ImageIcon(
													"image/black.png");
											bw1 = new ImageIcon(
													"image/band.png");
											cross = new ImageIcon(
													"image/blackL.png");
											arr1 = new ImageIcon(
													"image/toq1.png");
											arr2 = new ImageIcon(
													"image/toq2.png");

											local.setForeground(Color.black);
											remote.setForeground(Color.black);

											line2.setIcon(blk);
											admn.setIcon(adm);
											queue.setIcon(im);
											queue2.setIcon(im2);
											sched.setIcon(scd);
											blkln.setIcon(blk);
											line2.setIcon(blk);
											band.setIcon(bw1);
											Lline.setIcon(cross);
											arw1.setIcon(arr1);
											arw2.setIcon(arr2);

										} else {

											String rmn = Integer
													.toString(remain);

											DBConnection
													.getConnection()
													.createStatement()
													.executeUpdate(
															"update Allocate set Memory = '"
																	+ rmn
																	+ "' where VM = '"
																	+ vm + "' ");

											Thread.sleep(2500);
											line2.setIcon(grnlin);

											Thread.sleep(2500);

											band.setIcon(bwgrn);

											SimpleDateFormat sdfDate = new SimpleDateFormat(
													"dd/MM/yyyy");
											SimpleDateFormat sdfTime = new SimpleDateFormat(
													"HH:mm:ss");

											Date now = new Date();

											String strDate = sdfDate
													.format(now);
											String strTime = sdfTime
													.format(now);

											String dt = strDate + "   "
													+ strTime;

											DBConnection
													.getConnection()
													.createStatement()
													.executeUpdate(
															"insert into RemoteVm(Fname, Sk, dt) values('"
																	+ fname
																	+ "','"
																	+ sk
																	+ "','"
																	+ dt
																	+ "' )");

											Socket socket2 = new Socket(
													"localhost", 5006);

											DataOutputStream dos = new DataOutputStream(
													socket2.getOutputStream());

											dos.writeUTF(vm);
											dos.writeUTF(fname);
											dos.writeUTF(sk);
											dos.writeUTF(ct);

											Thread.sleep(2500);

											adm = new ImageIcon("image/adm.png");
											im = new ImageIcon(
													"image/queue.png");
											im2 = new ImageIcon(
													"image/2queue.png");
											scd = new ImageIcon(
													"image/schedule.png");
											blk = new ImageIcon(
													"image/black.png");
											bw1 = new ImageIcon(
													"image/band.png");
											cross = new ImageIcon(
													"image/blackL.png");
											arr1 = new ImageIcon(
													"image/toq1.png");
											arr2 = new ImageIcon(
													"image/toq2.png");

											local.setForeground(Color.black);
											remote.setForeground(Color.black);

											line2.setIcon(blk);
											admn.setIcon(adm);
											queue.setIcon(im);
											queue2.setIcon(im2);
											sched.setIcon(scd);
											blkln.setIcon(blk);
											line2.setIcon(blk);
											band.setIcon(bw1);
											Lline.setIcon(cross);
											arw1.setIcon(arr1);
											arw2.setIcon(arr2);

										}

									} else {

										Thread.sleep(2500);
										Lline.setIcon(red);
										Thread.sleep(2500);
										SimpleDateFormat sdfDate = new SimpleDateFormat(
												"dd/MM/yyyy");
										SimpleDateFormat sdfTime = new SimpleDateFormat(
												"HH:mm:ss");

										Date now = new Date();

										String strDate = sdfDate.format(now);
										String strTime = sdfTime.format(now);

										String dt = strDate + "   " + strTime;

										String resn = "Threshold";

										DBConnection
												.getConnection()
												.createStatement()
												.executeUpdate(
														"insert into Rejected values( '"
																+ resn + "', '"
																+ vm + "', '"
																+ fname + "','"
																+ dt + "' )");

										line2.setIcon(error);
										remote.setForeground(Color.red);
										JOptionPane
												.showMessageDialog(
														null,
														"Errored jobs due to Less Threshold value \n File size is greaeter than Threshold value");

										Thread.sleep(1500);

										adm = new ImageIcon("image/adm.png");
										im = new ImageIcon("image/queue.png");
										im2 = new ImageIcon("image/2queue.png");
										scd = new ImageIcon(
												"image/schedule.png");
										blk = new ImageIcon("image/black.png");
										bw1 = new ImageIcon("image/band.png");
										cross = new ImageIcon(
												"image/blackL.png");
										arr1 = new ImageIcon("image/toq1.png");
										arr2 = new ImageIcon("image/toq2.png");

										local.setForeground(Color.black);
										remote.setForeground(Color.black);

										line2.setIcon(blk);
										admn.setIcon(adm);
										queue.setIcon(im);
										queue2.setIcon(im2);
										sched.setIcon(scd);
										blkln.setIcon(blk);
										line2.setIcon(blk);
										band.setIcon(bw1);
										Lline.setIcon(cross);
										arw1.setIcon(arr1);
										arw2.setIcon(arr2);
									}

								} else {

									Thread.sleep(2500);
									line2.setIcon(error);

									SimpleDateFormat sdfDate = new SimpleDateFormat(
											"dd/MM/yyyy");
									SimpleDateFormat sdfTime = new SimpleDateFormat(
											"HH:mm:ss");

									Date now = new Date();

									String strDate = sdfDate.format(now);
									String strTime = sdfTime.format(now);

									String dt = strDate + "   " + strTime;

									String resn = "Bandwidth";

									DBConnection
											.getConnection()
											.createStatement()
											.executeUpdate(
													"insert into Rejected values( '"
															+ resn + "', '"
															+ vm + "', '"
															+ fname + "','"
															+ dt + "' )");

									JOptionPane
											.showMessageDialog(null,
													"Errored jobs due to Less B/W / File size is greater than bandwidth ");

									Thread.sleep(2500);

									adm = new ImageIcon("image/adm.png");
									im = new ImageIcon("image/queue.png");
									im2 = new ImageIcon("image/2queue.png");
									scd = new ImageIcon("image/schedule.png");
									blk = new ImageIcon("image/black.png");
									bw1 = new ImageIcon("image/band.png");
									cross = new ImageIcon("image/blackL.png");
									arr1 = new ImageIcon("image/toq1.png");
									arr2 = new ImageIcon("image/toq2.png");

									local.setForeground(Color.black);
									remote.setForeground(Color.black);

									line2.setIcon(blk);
									admn.setIcon(adm);
									queue.setIcon(im);
									queue2.setIcon(im2);
									sched.setIcon(scd);
									blkln.setIcon(blk);
									line2.setIcon(blk);
									band.setIcon(bw1);
									Lline.setIcon(cross);
									arw1.setIcon(arr1);
									arw2.setIcon(arr2);

								}
							}
						}
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}

			if (this.port == 7006) {

				try {

					ServerSocket server = new ServerSocket(7006);
					Socket socket;
					while (true) {

						socket = server.accept();
						DataInputStream dis = new DataInputStream(
								socket.getInputStream());

						String uname = dis.readUTF();
						String fname = dis.readUTF();
						String sk = dis.readUTF();
						String vm = dis.readUTF();

						DataOutputStream dss = new DataOutputStream(
								socket.getOutputStream());

						if (vm.equals("LocalVm")) {

							ResultSet rs = DBConnection
									.getConnection()
									.createStatement()
									.executeQuery(
											"select * from LocalVm where Fname = '"
													+ fname + "'  ");

							if (rs.next() == true) {

								Socket socket2 = new Socket("localhost", 7009);
								DataOutputStream dos = new DataOutputStream(
										socket2.getOutputStream());

								dos.writeUTF("LocalVm");
								dos.writeUTF(uname);
								dos.writeUTF(fname);
								dos.writeUTF(sk);

								DataInputStream ds = new DataInputStream(
										socket2.getInputStream());
								String res = ds.readUTF();

								if (res.equals("yes")) {

									dss.writeUTF("success");
									dss.writeUTF(ds.readUTF());
								}

								if (res.equals("block")) {
									dss.writeUTF("block");
								}

								if (res.equals("no")) {
									dss.writeUTF("fail");
								}
							} else {
								dss.writeUTF("not");
							}
						}

						if (vm.equals("RemoteVm")) {

							ResultSet rss = DBConnection
									.getConnection()
									.createStatement()
									.executeQuery(
											"select * from RemoteVm where Fname = '"
													+ fname + "' ");

							while (rss.next() == true) {

								Socket socket2 = new Socket("localhost", 7010);
								DataOutputStream dos = new DataOutputStream(
										socket2.getOutputStream());

								dos.writeUTF("RemoteVm");
								dos.writeUTF(uname);
								dos.writeUTF(fname);
								dos.writeUTF(sk);

								DataInputStream ds = new DataInputStream(
										socket2.getInputStream());
								String res = ds.readUTF();

								if (res.equals("yes")) {

									dss.writeUTF("success");
									dss.writeUTF(ds.readUTF());

								}

								if (res.equals("block")) {
									dss.writeUTF("block");
								}

								if (res.equals("no")) {
									dss.writeUTF("fail");
								}
							}
							if (rss.next() != true) {
								dss.writeUTF("not");
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
