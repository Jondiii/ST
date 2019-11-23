package ventanas;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class VentanaSelecion extends JFrame {
	private Font font = new Font("Arial", Font.PLAIN, 16);
	
	public VentanaSelecion() {
		setSize(600, 800);
		setLocation(600, 100);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		panelUsuario();
		panelEscoger();
		panelInferior();
	}

	private void panelInferior() {
		JPanel panel_informacion = new JPanel(new FlowLayout());
		JLabel punt = new JLabel("Puntuacion:" + "  " +VentanaInicio.u.getPuntuacion()+ " ");
		punt.setFont(font);
		JLabel partG = new JLabel("PartGanadas:" + "  " +VentanaInicio.u.getPartidasGanadas()+ " ");
		partG.setFont(font);
		JLabel partF =new JLabel("PartPerdidas:" + "  " +VentanaInicio.u.getPartidasPerdidas()+ " ");
		partF.setFont(font);
		
		panel_informacion.add(punt);
		panel_informacion.add(partG);
		panel_informacion.add(partF);
		
		add(panel_informacion, BorderLayout.SOUTH);
	}

	private void panelEscoger() {
		JPanel pEscoger = new JPanel();
		BoxLayout box = new BoxLayout(pEscoger, BoxLayout.Y_AXIS);
		pEscoger.setLayout(box);
		JComboBox<Modalidades_Juego> escoger_modalidad = new JComboBox<Modalidades_Juego>();
		escoger_modalidad.setModel(new DefaultComboBoxModel<>(Modalidades_Juego.values()));
		escoger_modalidad.setMaximumSize( escoger_modalidad.getPreferredSize() );
		pEscoger.add(escoger_modalidad);
		JButton luchar = new JButton("LUCHAR");
		pEscoger.add(luchar);
		JButton clasif = new JButton("CLASIFICACION");
		pEscoger.add(clasif);
		JButton crear_eq = new JButton("CREAR EQUIPO");
		pEscoger.add(crear_eq);
		
		add(pEscoger, BorderLayout.CENTER);
	}

	private void panelUsuario() {
		JPanel pUsuario = new JPanel();
		JButton cerrar_sesion = new JButton("Cerrar Sesion");
		ImageIcon usuario = new ImageIcon(getClass().getResource("/img/entrenadores/" +VentanaInicio.u.getImagen()+ ".png"));
		JLabel imagen_usuario = new JLabel(usuario);
		JLabel nombre_usuario = new JLabel(VentanaInicio.u.getNombre());
		nombre_usuario.setFont(font);
		
		pUsuario.add(cerrar_sesion);
		
		pUsuario.add(imagen_usuario);
		pUsuario.add(nombre_usuario);
		
		add(pUsuario, BorderLayout.NORTH);
	}
	
	public static void main(String[] args) {
		VentanaSelecion vs = new VentanaSelecion();
		vs.setVisible(true);
	}
}
