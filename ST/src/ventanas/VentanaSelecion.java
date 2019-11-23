package ventanas;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class VentanaSelecion extends JFrame {

	public VentanaSelecion() {
		setSize(600, 800);
		setLocation(600, 100);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		panelUsuario();
		panelEscoger();
	}
	
	private void panelEscoger() {
		JPanel pEscoger = new JPanel(new GridLayout(7, 0));
		JComboBox<Modalidades_Juego> escoger_modalidad = new JComboBox<Modalidades_Juego>();
		escoger_modalidad.setModel(new DefaultComboBoxModel<>(Modalidades_Juego.values()));
		pEscoger.add(escoger_modalidad);
		JButton luchar = new JButton("LUCHAR");
		pEscoger.add(luchar);
		JButton clasif = new JButton("CLASIFICACION");
		pEscoger.add(clasif);
		JButton enc_amigos= new JButton("AMIGOS");
		pEscoger.add(enc_amigos);
		JButton equipo = new JButton("HACER EQUIPO");
		pEscoger.add(luchar);
		add(pEscoger, BorderLayout.CENTER);
	}

	private void panelUsuario() {
		JPanel pUsuario = new JPanel(new BorderLayout());
		JButton cerrar_sesion = new JButton("Cerrar Sesion");
		JPanel pUsuario_labels = new JPanel(new GridLayout(2,0));
		ImageIcon usuario = new ImageIcon(VentanaInicio.u.getImagen());
		JLabel imagen_usuario = new JLabel(usuario);
		JLabel nombre_usuario = new JLabel(VentanaInicio.u.getNombre());
		
		pUsuario_labels.add(imagen_usuario);
		pUsuario_labels.add(nombre_usuario);
		
		pUsuario.add(cerrar_sesion, BorderLayout.WEST);
		pUsuario.add(pUsuario_labels, BorderLayout.EAST);
		
		add(pUsuario, BorderLayout.SOUTH);
	}
	

	public static void main(String[] args) {
		VentanaSelecion vs = new VentanaSelecion();
		vs.setVisible(true);
	}
}
