package ventanas;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.DefaultComboBoxModel;
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

import org.hamcrest.BaseDescription;

import database.BaseDatosPoke;
import principal.AlcanceMovimiento;
import principal.CategoriaMov;
import principal.EfectoSecundario;
import principal.EstadosAlterados;
import principal.Movimiento;
import principal.PanelMovimiento;
import principal.Pokemon;
import principal.Tipo;

public class VentanaCreadorEquipos extends JFrame {
	
	VentanaCreadorEquipos vc;
	JComboBox<Movimiento> comboMov1 = new JComboBox<Movimiento>();
	JComboBox<Movimiento> comboMov2 = new JComboBox<Movimiento>();
	JComboBox<Movimiento> comboMov3 = new JComboBox<Movimiento>();
	JComboBox<Movimiento> comboMov4 = new JComboBox<Movimiento>();
	PanelMovimiento p1;
	PanelMovimiento p2;
	PanelMovimiento p3;
	PanelMovimiento p4;
	JLabel psPoke = new JLabel();
	JLabel ataquePoke = new JLabel();
	JLabel defensaPoke = new JLabel();
	JLabel ataqueEspecialPoke = new JLabel();
	JLabel defensaEspecialPoke = new JLabel();
	JLabel velocidadPoke = new JLabel();

	HashMap<String, Pokemon> pokesEnEquipo = new HashMap<String, Pokemon>();

	public VentanaCreadorEquipos() {
		vc = this;
		setTitle("Creador de equipos");
		setLocation(200, 100);
		setSize(700, 500);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		//Se crean los componentes básicos de la ventana
		creaPanelSup();
		creaPanelInf();
		creaPanelPoke();
		
		setVisible(true);
	}
	
	private void creaPanelPoke() {
		JPanel pDescripcionMovs = new JPanel(new GridLayout(2,2));
		JPanel pPrincipal = new JPanel();
		JPanel pPoke = new JPanel();
		pPoke.add(pPrincipal, BorderLayout.NORTH);
		pPoke.add(pDescripcionMovs, BorderLayout.SOUTH);
		add(pPoke, BorderLayout.CENTER);

		JPanel pIzq = new JPanel();
		JPanel pCentro = new JPanel();
		JPanel pDer = new JPanel(new GridLayout(6,2));
		JPanel pMovs = new JPanel(new GridLayout(4,1));
		JLabel imgPoke = new JLabel();
		
		pPrincipal.add(pIzq, BorderLayout.WEST);
		pPrincipal.add(pCentro, BorderLayout.CENTER);
		pPrincipal.add(pDer, BorderLayout.EAST);

		String[] arrayPokemons = cargarNombrePokes();
		
		JComboBox<String> comboPoke = new JComboBox<String>(arrayPokemons);
		comboPoke.setEditable(true);
		comboPoke.setSelectedItem("");
		pIzq.add(comboPoke, BorderLayout.NORTH);
		comboPoke.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				ImageIcon icono_1 = new ImageIcon(getClass().getResource("/img/" + comboPoke.getSelectedItem() + "_frente.png"));
				ImageIcon icono_2 = new ImageIcon(icono_1.getImage().getScaledInstance(100, 100, java.awt.Image.SCALE_DEFAULT));
				imgPoke.removeAll();
				imgPoke.setIcon(icono_2);
				
				Movimiento[] arrayMovs = null;
				if (comboPoke.getSelectedItem().equals("Abomasnow")) {
					try {
						arrayMovs = cargaMovs(BaseDatosPoke.getNombreAbomasnow());
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				} else {
					arrayMovs = cargaMovs((String) comboPoke.getSelectedItem());
				}
				
				actualizaCombos(arrayMovs);
		
				try {
					Connection conn;
					conn = DriverManager.getConnection(BaseDatosPoke.url);
					Statement stmt = conn.createStatement();
				    ResultSet rs = stmt.executeQuery("Select ps, ataque, defensa, ataqueEspecial, "
				    		+ "defensaEspecial, velocidad from pokemons where name ='"+comboPoke.getSelectedItem()+"'" );
				    psPoke.setText(rs.getInt("ps")+"");
				    ataquePoke.setText(rs.getInt("ataque")+"");
				    defensaPoke.setText(rs.getInt("defensa")+"");
				    ataqueEspecialPoke.setText(rs.getInt("ataqueEspecial")+"");
				    defensaEspecialPoke.setText(rs.getInt("defensaEspecial")+"");
				    velocidadPoke.setText(rs.getInt("velocidad")+"");
				    conn.close();
				}catch(SQLException e2){
					System.out.println(e2.getMessage());
				}
				
				pPrincipal.revalidate();
				
			}
		});
		
		pCentro.add(new JLabel("Movimientos"), BorderLayout.NORTH);
		pMovs.add(comboMov1);
		pMovs.add(comboMov2);
		pMovs.add(comboMov3);
		pMovs.add(comboMov4);
		pCentro.add(pMovs, BorderLayout.CENTER);
		
		comboMov1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				p1.setMov((Movimiento)comboMov1.getSelectedItem());
			}
		});
		
		comboMov2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				p2.setMov((Movimiento)comboMov2.getSelectedItem());
			}
		});
		
		comboMov3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				p3.setMov((Movimiento)comboMov3.getSelectedItem());
			}
		});
		
		comboMov4.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				p4.setMov((Movimiento)comboMov4.getSelectedItem());
			}
		});
		

		pIzq.add(imgPoke, BorderLayout.CENTER);
		
		pDer.add(new JLabel("PS"));
		pDer.add(psPoke);
		pDer.add(new JLabel("Ataque"));
		pDer.add(ataquePoke);
		pDer.add(new JLabel("Defensa"));
		pDer.add(defensaPoke);
		pDer.add(new JLabel("Ata. especial"));
		pDer.add(ataqueEspecialPoke);
		pDer.add(new JLabel("Def. especial  "));
		pDer.add(defensaEspecialPoke);
		pDer.add(new JLabel("Velocidad"));
		pDer.add(velocidadPoke);
		
		creaPanelMovs(pDescripcionMovs);
		
	}
	
	public void actualizaCombos(Movimiento[] movs) {
		comboMov1.removeAllItems();
		comboMov2.removeAllItems();
		comboMov3.removeAllItems();
		comboMov4.removeAllItems();
		
		comboMov1.setModel(new DefaultComboBoxModel<Movimiento>(movs));
		comboMov2.setModel(new DefaultComboBoxModel<Movimiento>(movs));
		comboMov3.setModel(new DefaultComboBoxModel<Movimiento>(movs));
		comboMov4.setModel(new DefaultComboBoxModel<Movimiento>(movs));
	}
	
	public void creaPanelMovs(JPanel p) {
		Movimiento movVacio = new Movimiento("Vacío", Tipo.FANTASMA, 0, 0, 0, CategoriaMov.ESPECIAL, 0, AlcanceMovimiento.CAMPO, EstadosAlterados.NULL, 0, EfectoSecundario.DEBILITACION);
		p.add(p1 = new PanelMovimiento(movVacio));
		p.add(p2 = new PanelMovimiento(movVacio));
		p.add(p3 = new PanelMovimiento(movVacio));
		p.add(p4 = new PanelMovimiento(movVacio));

	}
	
	public String[] cargarNombrePokes(){
		ArrayList<String> listaPokemons = new ArrayList<String>();
		try {
			listaPokemons = BaseDatosPoke.getPokemons();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String[] arrayPokemons = new String[listaPokemons.size()];
			
		for (int i = 0; i < listaPokemons.size(); i++) {
				String nombrePoke = listaPokemons.get(i).replaceAll("_frente.png", "");
				arrayPokemons[i] = nombrePoke;
			}
		return arrayPokemons;
	}
	public static Movimiento[] cargaMovs(String nombrePoke) {
		ArrayList<Movimiento> listaMovs = new ArrayList<Movimiento>();
		ArrayList<Integer> listaIdMovs = new ArrayList<Integer>();
		try {
			listaMovs = BaseDatosPoke.queryDB(nombrePoke);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Movimiento[] arrayMovs = new Movimiento[listaMovs.size()];
		for (int i = 0; i < listaMovs.size(); i++) {
			Movimiento nombreMov = listaMovs.get(i);
			arrayMovs[i] = nombreMov;
			}
		return arrayMovs;
//		try {
//			Connection conn = DriverManager.getConnection(BaseDatosPoke.url);
//			Statement stmt = conn.createStatement();
////			ResultSet rs = stmt.executeQuery("select mov1, mov2, mov3, mov4, mov5, mov6, mov7, mov8, mov9, mov10 from pokemons where name='" + nombrePoke + "'");
//			
//			ResultSet rs = stmt.executeQuery("select mov1, mov2, mov3, mov4, mov5, mov6, mov7, mov8, mov9, mov10 from pokemons where name='" + nombrePoke + "'");	
//			
//			//Coge los IDs de los movimientos de los pokémon
//			for (int i = 1; i <=10; i++) {
//				listaIdMovs.add(rs.getInt("mov" + i));
//			}
//	
//		
//		} catch (SQLException e) {
//			System.out.println(e.getMessage() + ". Error al cargar los movimientos (posiblemente los de Abomasnow).");
//		}
//		
//		try {
//			Connection conn2 = DriverManager.getConnection(BaseDatosPoke.url);
//			Statement stmt2 = conn2.createStatement();
//			//Añade los movimientos a la lista.
//			for (int i = 0; i < 10; i++) {
//				ResultSet rs2 = stmt2.executeQuery("select * from movimientos where id=" + listaIdMovs.get(i));
//				listaMovs.add(new Movimiento(rs2.getString("name").replace("_", " "), Tipo.valueOf(rs2.getString("tipo").toUpperCase()), rs2.getInt("potencia"), rs2.getInt("precision"),
//						rs2.getInt("pp"), CategoriaMov.valueOf(rs2.getString("categoria").toUpperCase()),  rs2.getInt("prioridad"), AlcanceMovimiento.valueOf(rs2.getString("alcance").toUpperCase()),
//						EstadosAlterados.valueOf(rs2.getString("efecto").toUpperCase()), EfectoSecundario.valueOf(rs2.getString("estadoSecundario").toUpperCase()),
//						rs2.getInt("prEfecto"), rs2.getInt("prStats"),rs2.getInt("prEstado"), rs2.getBoolean("cambioStatsARival"), rs2.getInt("ataque"),
//						rs2.getInt("defensa"), rs2.getInt("ataqueEspecial"),rs2.getInt("defensaEspecial"),rs2.getInt("velocidad")));
//			
//			}
//
//		} catch (SQLException e) {
//			System.out.println(e.getMessage() + ". Error al crear los movimientos.");
//		}
//		
//		Movimiento[] arrayMovs = new Movimiento[listaMovs.size()];
//		
//		for (int i = 0; i < listaMovs.size(); i++) {
//			Movimiento nombreMov = listaMovs.get(i);
//			arrayMovs[i] = nombreMov;
//			}
//		return arrayMovs;
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

}
