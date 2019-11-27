package ventanas;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.Main;
import principal.Combate;


public class VentanaSelecion extends JFrame {
	private Font font = new Font("Arial", Font.PLAIN, 16);
	private Combate c;
	
	
	public VentanaSelecion(Combate c) {
		setSize(400, 500);
		setLocation(600, 100);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLayout(new BorderLayout());
		
		panelUsuario();
		panelEscoger();
		panelInferior();
		panelVacio();
		this.c = c;
	}
	private void panelVacio() {
		JPanel panel_izq = new JPanel();
		panel_izq.setPreferredSize(new Dimension(115, 50));
		add(panel_izq, BorderLayout.WEST);
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
//		JComboBox<Modalidades_Juego> escoger_modalidad = new JComboBox<Modalidades_Juego>();
//		escoger_modalidad.setModel(new DefaultComboBoxModel<>(Modalidades_Juego.values()));
//		escoger_modalidad.setMaximumSize( escoger_modalidad.getPreferredSize() );
//		pEscoger.add(escoger_modalidad);
		JButton luchar = new JButton("LUCHAR");
		luchar.setMaximumSize(new Dimension(120, 40));
		pEscoger.add(luchar);
		JButton clasif = new JButton("LADDER");
		clasif.setMaximumSize(new Dimension(120, 40));
		pEscoger.add(clasif);
		JButton crear_eq = new JButton("CREAR EQUIPO");
		crear_eq.setMaximumSize(new Dimension(120, 40));
		pEscoger.add(crear_eq);
		
		
		add(pEscoger, BorderLayout.CENTER);
		
		luchar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
	}

	private void panelUsuario() {
		JPanel pUsuario = new JPanel();
		pUsuario.setPreferredSize(new Dimension(400, 150));
		JButton cerrar_sesion = new JButton("Cerrar Sesion");
		ImageIcon usuario = new ImageIcon(getClass().getResource("/img/entrenadores/" +VentanaInicio.u.getImagen()+ ".png"));
		JLabel imagen_usuario = new JLabel(usuario);
		JLabel nombre_usuario = new JLabel(VentanaInicio.u.getNombre());
		nombre_usuario.setFont(font);
		
		pUsuario.add(cerrar_sesion);
		pUsuario.add(imagen_usuario);
		pUsuario.add(nombre_usuario);
		
		add(pUsuario, BorderLayout.NORTH);
		
		cerrar_sesion.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaInicio v = new VentanaInicio(c);
				v.setVisible(true);
				dispose();
			}
		});
		
		
	}
	
//	public static void main(String[] args) {
//		VentanaSelecion vs = new VentanaSelecion();
//		vs.setVisible(true);
//	}
}
