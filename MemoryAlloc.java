import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class MemoryAlloc extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	Container c;

	public Font f1 = new Font("Times New roman", Font.BOLD, 15);

	JLabel l1 = new JLabel("V-Machine"), l2 = new JLabel("Memory"),
			l3 = new JLabel("Threshold Value");

	JTextField t1 = new JTextField(), t2 = new JTextField(),
			t3 = new JTextField();

	JButton submit = new JButton("Submit");

	MemoryAlloc() {

		c = getContentPane();
		c.setLayout(null);
		c.setBackground(Color.LIGHT_GRAY);

		l1.setFont(f1);
		l2.setFont(f1);
		l3.setFont(f1);

		t1.setFont(f1);
		t2.setFont(f1);
		t3.setFont(f1);

		l1.setBounds(50, 50, 100, 25);
		l2.setBounds(50, 100, 100, 25);
		l3.setBounds(50, 150, 100, 25);

		t1.setBounds(165, 50, 150, 25);
		t2.setBounds(165, 100, 150, 25);
		t3.setBounds(165, 150, 150, 25);

		submit.setBounds(50, 200, 125, 30);

		c.add(l1);
		c.add(l2);
		c.add(l3);
		c.add(t1);
		c.add(t2);
		c.add(t3);
		c.add(submit);

		submit.addActionListener(this);

		setSize(350, 350);
		setResizable(false);
		setVisible(true);
		setTitle("Allocate Memory");

	}

	@SuppressWarnings("static-access")
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == submit) {

			try {

				JOptionPane op = new JOptionPane();

				DBConnection
						.getConnection()
						.createStatement()
						.executeUpdate(
								"update Allocate set Memory ='" + t2.getText()
										+ "' , Threshold = '" + t3.getText()
										+ "' where VM ='" + t1.getText()
										+ "'  ");

				op.showMessageDialog(null, "Memory Allocated successfully");

			} catch (Exception ex) {
			}

		}

	}
}
