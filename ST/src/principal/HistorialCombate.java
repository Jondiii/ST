package principal;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Scrollbar;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class HistorialCombate extends JFrame{
	private Font f = new Font("Arial", Font.PLAIN, 20);
	private JScrollPane central;
	private JPanel jscroll;

	
	public HistorialCombate() {
		setSize(new Dimension(400, 500));
		setLocation(1100,  100);
		setTitle("HISTORIAL");
		
		jscroll = new JPanel();
		jscroll.setLayout(new BoxLayout(jscroll, BoxLayout.Y_AXIS));
		jscroll.setSize(new Dimension(400, 500));
		createJscroll();
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
	}

	public void añadirPanel(String info) {
		JPanel nuevo_panel = new JPanel();
		nuevo_panel.setBackground(Color.GRAY);
		JLabel info_batalla = new JLabel(info);
		info_batalla.setFont(f);
		nuevo_panel.add(info_batalla);
		jscroll.add(nuevo_panel);
		revalidate();
	}
	private void createJscroll() {
		central = new JScrollPane(jscroll);
		central.setPreferredSize(new Dimension(480, 480));
		central.setSize(new Dimension(480, 480));
		central.setMinimumSize(new Dimension(480, 40));
		central.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		add(central, BorderLayout.CENTER);
		
	}

	public void añadirTurno(String turno) {
		JPanel nuevo_panel = new JPanel();
		nuevo_panel.setBackground(Color.CYAN);
		JLabel info_turno = new JLabel(turno);
		info_turno.setFont(f);
		nuevo_panel.add(info_turno);
		jscroll.add(nuevo_panel);
		revalidate();
	}

}
