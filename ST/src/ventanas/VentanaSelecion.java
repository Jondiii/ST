package ventanas;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import database.BaseDatosPoke;
import main.Main;
import principal.Combate;
import principal.Pokemon;
import socket.Cliente;


public class VentanaSelecion extends JFrame {
	private Font font = new Font("Arial", Font.PLAIN, 16);
	private Combate c;
	public VentanaSelecion vs;
	public static ArrayList<Pokemon> pokemons_para_combatir = null;
	
	public VentanaSelecion(Combate c) {
		pokemons_para_combatir = Main.miEquipo;
		setSize(400, 500);
		setLocation(600, 100);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLayout(new BorderLayout());
        
		panelUsuario();
		panelEscoger();
		panelInferior();
		panelVacio();
		this.c = c;
		vs = this;
		
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
		
		JButton seleccionar_eq = new JButton("ELEGIR EQUIPO");
		crear_eq.setMaximumSize(new Dimension(120, 40));
		pEscoger.add(seleccionar_eq);
		
		add(pEscoger, BorderLayout.CENTER);
		
		luchar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Cliente c1 = new Cliente();
		        try {
					c1.startConnection("192.168.1.18", 5000); // si estoy en ethernet "192.168.1.18"// si estoy con wifi "192.168.1.105"
					Cliente.sendObject(pokemons_para_combatir);
		        } catch (ClassNotFoundException | IOException e) {
					e.printStackTrace();
				}
			}
		});
		
		clasif.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				VentanaClasif vc = new VentanaClasif();
				vc.setVisible(true);
			}
		});
		
		crear_eq.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaCreadorEquipos ventEqui = new VentanaCreadorEquipos();
			}
		});
		
		seleccionar_eq.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				VentanaSelectorEquipos v_sel_equi = new VentanaSelectorEquipos();
			}
		});
	}

	private void panelUsuario() {
		JPanel pUsuario = new JPanel();
		pUsuario.setPreferredSize(new Dimension(400, 150));
		JButton cerrar_sesion = new JButton("Cerrar Sesion");
		ImageIcon usuario = new ImageIcon(getClass().getResource("/img/entrenadores/" +VentanaInicio.u.getImagen()+ ".png"));
		JLabel imagen_usuario = new JLabel(usuario);
		imagen_usuario.addMouseListener(new MouseAdapter() {
		
			@Override
			public void mousePressed(MouseEvent e) {
				JDialog pCambioTrainer = new JDialog();
				JPanel pOption = new JPanel(new FlowLayout());
				pOption.add(VentanaInicio.entrenadores);
				pCambioTrainer.add(pOption, BorderLayout.CENTER);
				pCambioTrainer.setLocation(vs.getLocation().x + 40, vs.getLocation().y + 40);
				pCambioTrainer.setSize(300, 200);
				JPanel pSprite = new JPanel();
				JLabel labelSprite = new JLabel();
				pSprite.add(labelSprite);
				
				ImageIcon icono_1 = new ImageIcon(getClass().getResource("/img/entrenadores/unknown.png"));
				ImageIcon icono_2 = new ImageIcon(icono_1.getImage().getScaledInstance(100, 100, java.awt.Image.SCALE_DEFAULT));
				labelSprite.setIcon(icono_2);
				pSprite.add(labelSprite);
				
				VentanaInicio.entrenadores.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						ImageIcon icono_1 = new ImageIcon(getClass().getResource("/img/entrenadores/" + VentanaInicio.entrenadores.getSelectedItem() + ".png"));
						ImageIcon icono_2 = new ImageIcon(icono_1.getImage().getScaledInstance(100, 100, java.awt.Image.SCALE_DEFAULT));
						labelSprite.setIcon(icono_2);
						pSprite.removeAll();
						pSprite.add(labelSprite);
					}
				});
				
				pCambioTrainer.add(pSprite, BorderLayout.EAST);
				
				JButton bAceptar = new JButton("Aceptar");
				
				bAceptar.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						try {
							Connection conn = DriverManager.getConnection(BaseDatosPoke.url);
							Statement stmt  = conn.createStatement();
							stmt.executeUpdate("update usuario set imagen='" + VentanaInicio.entrenadores.getSelectedItem() + "' where nombre='" + VentanaInicio.u.getNombre()+"'");
							VentanaInicio.u.setImagen(VentanaInicio.entrenadores.getSelectedItem().toString());
							conn.close();
						} catch (SQLException e2) {
							System.out.println(e2.getMessage());
						}
						
						ImageIcon icono_1 = new ImageIcon(getClass().getResource("/img/entrenadores/" + VentanaInicio.u.getImagen() + ".png"));
						ImageIcon icono_2 = new ImageIcon(icono_1.getImage().getScaledInstance(100, 100, java.awt.Image.SCALE_DEFAULT));
						imagen_usuario.removeAll();
						imagen_usuario.setIcon(icono_2);
						pOption.revalidate();
						pCambioTrainer.dispose();						
					}
				});
				
				pCambioTrainer.add(bAceptar, BorderLayout.SOUTH);
				pCambioTrainer.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				pCambioTrainer.setVisible(true);
			}
		});
		
		
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
