package ventanas;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class VentanaCreadorEquipos extends JFrame {
	
	VentanaCreadorEquipos vc;
	
	public VentanaCreadorEquipos() {
		vc = this;
		setTitle("Creador de equipos");
		setLocation(200, 100);
		setSize(500, 500);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		//Se crean los componentes básicos de la ventana
		creaPanelSup();
		creaPanelInf();
		creaPanelEquipo();
		
		setVisible(true);
	}
	
	private void creaPanelEquipo() {
		JPanel pEquipo = new JPanel(new GridLayout(6,1));
		add(pEquipo, BorderLayout.CENTER);
		
		JPanel pPoke = new JPanel();
		JPanel pIzq = new JPanel();
		JPanel pCentro = new JPanel(new GridLayout(2,2));
		JLabel imgPoke = new JLabel();
		pPoke.add(pIzq, BorderLayout.WEST);
		pPoke.add(pCentro, BorderLayout.CENTER);
		pEquipo.add(pPoke);
	
		String[] ArrayPokemons = cargarNombrePokes();
		
		JComboBox<String> comboPoke = new JComboBox<String>(ArrayPokemons);
		pIzq.add(comboPoke, BorderLayout.NORTH);
		
		comboPoke.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ImageIcon icono_1 = new ImageIcon(getClass().getResource("/img/" + comboPoke.getSelectedItem() + "_frente.png"));
				ImageIcon icono_2 = new ImageIcon(icono_1.getImage().getScaledInstance(100, 100, java.awt.Image.SCALE_DEFAULT));
				imgPoke.removeAll();
				imgPoke.setIcon(icono_2);
				pEquipo.revalidate();
			}
		});
		
		ImageIcon icono_1 = new ImageIcon(getClass().getResource("/img/" + comboPoke.getSelectedItem() + "_frente.png"));
		ImageIcon icono_2 = new ImageIcon(icono_1.getImage().getScaledInstance(100, 100, java.awt.Image.SCALE_DEFAULT));
		imgPoke.setIcon(icono_2);
		pIzq.add(imgPoke, BorderLayout.CENTER);
		
	}
	
	private String[] cargarNombrePokes(){
		ArrayList<String> listaPokemons = new ArrayList<String>();

		File fInic = new File("src/img/");
		 if (fInic.isDirectory()) {
			 for( File f : fInic.listFiles() ) {
				 	String nombreRuta = f.getName();
				 	if(nombreRuta.contains("frente")) listaPokemons.add(nombreRuta);
		 		}
		 }
		
		String[] ArrayPokemons = new String[listaPokemons.size()];
				
		for (int i = 0; i < listaPokemons.size(); i++) {
				String nombrePoke = listaPokemons.get(i).replaceAll("_frente.png", "");
				ArrayPokemons[i] = nombrePoke;
			}
		return ArrayPokemons;
	}
	
	private void creaPanelSup() {
		JTextField nombreEquipo = new JTextField(15);
		nombreEquipo.setText("Mi equipo");
		JPanel pNombre = new JPanel();
		pNombre.add(nombreEquipo, BorderLayout.CENTER);
		add(pNombre, BorderLayout.NORTH);
		
		nombreEquipo.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) { //Si el textField está vacío sale un mensaje de error y devuelve el focus al TextField
				if(nombreEquipo.getText().trim().isEmpty()) {
					JOptionPane error = new JOptionPane();
					error.showMessageDialog(null, "El nombre del equipo no puede estar vacío.", "Error", JOptionPane.ERROR_MESSAGE);
					nombreEquipo.requestFocus();
				}
			}
			@Override
			public void focusGained(FocusEvent e) {	} //No hacemos nada con esto
		});
		
		
	}
	
	private void creaPanelInf() {
		JButton bGuardar = new JButton("Guardar y salir");
		JButton bCancelar = new JButton("Cancelar");
		JPanel pInferior = new JPanel();
		pInferior.add(bGuardar, BorderLayout.WEST);
		pInferior.add(bCancelar, BorderLayout.EAST);
		add(pInferior, BorderLayout.SOUTH);
	}
	
	private static class PanelMovimiento extends JPanel {
		
		public PanelMovimiento() {
			//COSAS A AÑADIR:
			//Combo box arriba para elegir el movimiento y que salga también su tipo, clase, pot, prec y pp
			//Pequeña descripción del efecto del movimiento abajo.
			//Este panel tendrá que añadirse 4 veces por cada pokémon elegido.
			//Hacer que el color del panel cambie en función del tipo de movimiento????
		}
	}
}
