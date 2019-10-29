package principal;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.Main;

import java.awt.image.BufferedImage;

public class VentanaInicio extends JFrame {
	
	private VentanaInicio vi;
	public JLabel logo;
	static ArrayList<Pokemon>  miEquipo = new ArrayList<>();
	static ArrayList<Pokemon> oponente = new ArrayList<>();
	private Combate c;
	
	public VentanaInicio( Combate c) {
		vi = this;
		vi.setResizable(false);
		vi.setTitle("Pokemon Stars");
		vi.setLocation(700, 300);
		vi.setSize(500, 500);
		vi.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
		vi.crearPanelInferior();
		this.c = c;
	}
	
	private void crearPanelInferior(){
		JPanel panel = new JPanel(new BorderLayout());
		JButton jugar = new JButton("Jugar");
		JButton salir = new JButton("Salir");
		JPanel pBotones = new JPanel();
		JPanel pLogo = new JPanel();
		
		ImageIcon icono_1 = new ImageIcon(getClass().getResource("/img/pokemonstars.png"));
		ImageIcon icono_2 = new ImageIcon(icono_1.getImage().getScaledInstance(500, 300, java.awt.Image.SCALE_DEFAULT));
		logo = new JLabel();
		logo.setIcon(icono_2);

		pLogo.add(logo);
		pBotones.add(jugar);
		pBotones.add(salir);
		panel.add(pLogo, BorderLayout.CENTER);
		panel.add(pBotones, BorderLayout.SOUTH);
		vi.add(panel);
		setVisible(true);
		
		jugar.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaJuego vj = new VentanaJuego(c);
				vj.addWindowListener( new WindowAdapter() {
					@Override
					public void windowClosing(WindowEvent e) {
						Main.cerrada = true;
					}
				});
				vj.setVisible(true);
				vi.dispose();
				HiloJuego mh = new HiloJuego(c, vj);
				mh.start();
			}
		});
		
		salir.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				vi.dispose();
			}
		});
	}
	
}
