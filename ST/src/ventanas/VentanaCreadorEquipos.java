package ventanas;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
import principal.Pokeball;
import principal.Pokemon;
import principal.Tipo;

public class VentanaCreadorEquipos extends JFrame {
	
	VentanaCreadorEquipos vc;
	JComboBox<String> comboPoke;
	JComboBox<Movimiento> comboMov1 = new JComboBox<Movimiento>();
	JComboBox<Movimiento> comboMov2 = new JComboBox<Movimiento>();
	JComboBox<Movimiento> comboMov3 = new JComboBox<Movimiento>();
	JComboBox<Movimiento> comboMov4 = new JComboBox<Movimiento>();
	PanelMovimiento p1;
	PanelMovimiento p2;
	PanelMovimiento p3;
	PanelMovimiento p4;
	Pokeball pb1;
	Pokeball pb2;
	Pokeball pb3;
	Pokeball pb4;
	Pokeball pb5;
	Pokeball pb6;
	Pokeball[] listaPokeball = new Pokeball[6];
	//Pokeball[] listaPokeball = new Pokeball[6];

	int pokeballSeleccionada = 1;

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
		creaPanelEquipo();
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
		
		comboPoke = new JComboBox<String>(arrayPokemons);
		comboPoke.setEditable(true);
		comboPoke.setSelectedItem("");
		pIzq.add(comboPoke, BorderLayout.NORTH);
		comboPoke.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
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
				} catch (NullPointerException exception) {	}
				
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
		//Se puede meter aquí lo de que se cambien las combo boxes cuando se selecciona un pokemon ya creado.
		comboMov1.setModel(new DefaultComboBoxModel<Movimiento>(movs));
		comboMov2.setModel(new DefaultComboBoxModel<Movimiento>(movs));
		comboMov3.setModel(new DefaultComboBoxModel<Movimiento>(movs));
		comboMov4.setModel(new DefaultComboBoxModel<Movimiento>(movs));
	}
	
	public void creaPanelMovs(JPanel p) {
		Movimiento movVacio = new Movimiento("", Tipo.FANTASMA, 0, 0, 0, CategoriaMov.ESPECIAL, 0, AlcanceMovimiento.CAMPO, EstadosAlterados.NULL, 0, EfectoSecundario.DEBILITACION);
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
		ArrayList<Integer> listaIdMovs = new ArrayList<Integer>();//No sé qué es esto.
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
		JButton bGuardarPoke = new JButton("Guardar Pokémon");
		JButton bGuardarEquipo = new JButton("Guardar equipo");
		JButton bSalir = new JButton("Salir");
		JPanel pInferior = new JPanel();
		pInferior.add(bGuardarEquipo, BorderLayout.WEST);
		pInferior.add(bGuardarPoke, BorderLayout.CENTER);
		pInferior.add(bSalir, BorderLayout.EAST);
		add(pInferior, BorderLayout.SOUTH);
		
		bGuardarPoke.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String[] datosPoke = {(String)comboPoke.getSelectedItem(), comboMov1.getSelectedItem().toString(), 
						comboMov2.getSelectedItem().toString(), comboMov3.getSelectedItem().toString(), comboMov4.getSelectedItem().toString()};
				listaPokeball[pokeballSeleccionada-1].setDatosPoke(datosPoke); //Pone que la ball no existe
			}
		});
	}
	
	private void creaPanelEquipo() {
		JPanel pPokeballs = new JPanel(new GridLayout(6,1));
		ImageIcon icono = new ImageIcon(getClass().getResource("/img/pokeball.png"));
		ImageIcon iconoPokeball = new ImageIcon(icono.getImage().getScaledInstance(30, 30, java.awt.Image.SCALE_DEFAULT));
		
		String[] datosVacios = {"", "", "", "", "", ""};

		listaPokeball[0] = (pb1 = new Pokeball(iconoPokeball, datosVacios));
		listaPokeball[1] = (pb2 = new Pokeball(iconoPokeball, datosVacios));
		listaPokeball[2] = (pb3 = new Pokeball(iconoPokeball, datosVacios));
		listaPokeball[3] = (pb4 = new Pokeball(iconoPokeball, datosVacios));
		listaPokeball[4] = (pb5 = new Pokeball(iconoPokeball, datosVacios));
		listaPokeball[5] = (pb6 = new Pokeball(iconoPokeball, datosVacios));
		
		addPokeballListeners();
		
		pPokeballs.add(pb1);
		pPokeballs.add(pb2);
		pPokeballs.add(pb3);
		pPokeballs.add(pb4);
		pPokeballs.add(pb5);
		pPokeballs.add(pb6);
		
		add(pPokeballs, BorderLayout.EAST);

	}
	
	private void addPokeballListeners() { //FALTA: Guardar los datos en cada pokeball. Añadir listener al botón de salir. Quitar la imagen del poke al cambiar de pokemon
		pb1.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if (pokeballSeleccionada == 1) return;
				pokeballSeleccionada = 1;
				
				if(pb1.getDatosPoke()[0].isEmpty()) {
					comboPoke.setSelectedIndex(-1);
					comboMov1.setSelectedIndex(-1);
					comboMov2.setSelectedIndex(-1);
					comboMov3.setSelectedIndex(-1);
					comboMov4.setSelectedIndex(-1);

				} else {
					comboPoke.setSelectedItem(pb1.getDatosPoke()[0]);
					comboMov1.setSelectedItem(pb1.getDatosPoke()[1]);
					comboMov2.setSelectedItem(pb1.getDatosPoke()[2]);
					comboMov3.setSelectedItem(pb1.getDatosPoke()[3]);
					comboMov4.setSelectedItem(pb1.getDatosPoke()[4]);

				}
			}
		});
		
		pb2.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if (pokeballSeleccionada == 2) return;
				pokeballSeleccionada = 2;
				
				if(pb1.getDatosPoke()[0].isEmpty()) {
					comboPoke.setSelectedIndex(-1);
					comboMov1.setSelectedIndex(-1);
					comboMov2.setSelectedIndex(-1);
					comboMov3.setSelectedIndex(-1);
					comboMov4.setSelectedIndex(-1);

				} else {
					comboPoke.setSelectedItem(pb2.getDatosPoke()[0]);
					comboMov1.setSelectedItem(pb2.getDatosPoke()[1]);
					comboMov2.setSelectedItem(pb2.getDatosPoke()[2]);
					comboMov3.setSelectedItem(pb2.getDatosPoke()[3]);
					comboMov4.setSelectedItem(pb2.getDatosPoke()[4]);

				}
			}
		});
		
		pb3.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if (pokeballSeleccionada == 3) return;
				pokeballSeleccionada = 3;
				
				if(pb3.getDatosPoke()[0].isEmpty()) {
					comboPoke.setSelectedIndex(-1);
					comboMov1.setSelectedIndex(-1);
					comboMov2.setSelectedIndex(-1);
					comboMov3.setSelectedIndex(-1);
					comboMov4.setSelectedIndex(-1);

				} else {
					comboPoke.setSelectedItem(pb3.getDatosPoke()[0]);
					comboMov1.setSelectedItem(pb3.getDatosPoke()[1]);
					comboMov2.setSelectedItem(pb3.getDatosPoke()[2]);
					comboMov3.setSelectedItem(pb3.getDatosPoke()[3]);
					comboMov4.setSelectedItem(pb3.getDatosPoke()[4]);

				}
			}
		});	
		
		pb4.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if (pokeballSeleccionada == 4) return;
				pokeballSeleccionada = 4;
				
				if(pb4.getDatosPoke()[0].isEmpty()) {
					comboPoke.setSelectedIndex(-1);
					comboMov1.setSelectedIndex(-1);
					comboMov2.setSelectedIndex(-1);
					comboMov3.setSelectedIndex(-1);
					comboMov4.setSelectedIndex(-1);

				} else {
					comboPoke.setSelectedItem(pb4.getDatosPoke()[0]);
					comboMov1.setSelectedItem(pb4.getDatosPoke()[1]);
					comboMov2.setSelectedItem(pb4.getDatosPoke()[2]);
					comboMov3.setSelectedItem(pb4.getDatosPoke()[3]);
					comboMov4.setSelectedItem(pb4.getDatosPoke()[4]);

				}
			}
		});	
		
		pb5.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if (pokeballSeleccionada == 5) return;
				pokeballSeleccionada = 5;
				
				if(pb5.getDatosPoke()[0].isEmpty()) {
					comboPoke.setSelectedIndex(-1);
					comboMov1.setSelectedIndex(-1);
					comboMov2.setSelectedIndex(-1);
					comboMov3.setSelectedIndex(-1);
					comboMov4.setSelectedIndex(-1);

				} else {
					comboPoke.setSelectedItem(pb5.getDatosPoke()[0]);
					comboMov1.setSelectedItem(pb5.getDatosPoke()[1]);
					comboMov2.setSelectedItem(pb5.getDatosPoke()[2]);
					comboMov3.setSelectedItem(pb5.getDatosPoke()[3]);
					comboMov4.setSelectedItem(pb5.getDatosPoke()[4]);

				}
			}
		});	
		
		pb6.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if (pokeballSeleccionada == 6) return;
				pokeballSeleccionada = 6;
				
				if(pb1.getDatosPoke()[0].isEmpty()) {
					comboPoke.setSelectedIndex(-1);
					comboMov1.setSelectedIndex(-1);
					comboMov2.setSelectedIndex(-1);
					comboMov3.setSelectedIndex(-1);
					comboMov4.setSelectedIndex(-1);

				} else {
					comboPoke.setSelectedItem(pb6.getDatosPoke()[0]);
					comboMov1.setSelectedItem(pb6.getDatosPoke()[1]);
					comboMov2.setSelectedItem(pb6.getDatosPoke()[2]);
					comboMov3.setSelectedItem(pb6.getDatosPoke()[3]);
					comboMov4.setSelectedItem(pb6.getDatosPoke()[4]);

				}
			}
		});	
	}

}
