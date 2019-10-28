package principal;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.image.BufferedImage;

public class VentanaInicio extends JFrame {
	
	private JFrame ventana;
	public JLabel logo;
	
	public VentanaInicio() {
		
		setResizable(false);
		
		setTitle("Pokemon Stars");
		setLocation(700, 300);
		setSize(500, 500);
		setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
		
		crearPanelInferior();
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
		getContentPane().add(panel);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		VentanaInicio ventana = new VentanaInicio();
	}
}
