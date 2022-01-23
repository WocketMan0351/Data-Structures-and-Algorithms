package lambdas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class LamdaEventListener extends JFrame implements Time {
	private JPanel panel;
	private JButton button1;
	private JButton button2;
	private JButton button3;

	class Button2Listener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Click handled with nested class implementing ActionListener.");
		}
	}

	public LamdaEventListener() {
		panel = new JPanel();
		add(panel);
		createButtons();
		setSettings();
	}

	private void createButtons() {
		button1 = new JButton("Lambda");
		button1.addActionListener(event -> System.out.println("Click handled with lamda expression."));
		panel.add(button1);

		button2 = new JButton("No Lambda");
		button2.addActionListener(new Button2Listener());
		panel.add(button2);

		button3 = new JButton("Time");
		button3.addActionListener(event -> System.out.println("The time is " + getTime()));
		panel.add(button3);
	}

	private void setSettings() {
		setSize(400, 200);
		setTitle("Lambda Expressions");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	@Override
	public String getTime() {
		LocalTime time = LocalTime.now();
		return time.toString().substring(0, 8);
	}

	public static void main(String[] args) {
		JFrame frame = new LamdaEventListener();
	}

}
