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

	int idPoke = 0;
	JLabel psPoke = new JLabel();
	JLabel ataquePoke = new JLabel();
	JLabel defensaPoke = new JLabel();
	JLabel ataqueEspecialPoke = new JLabel();
	JLabel defensaEspecialPoke = new JLabel();
	JLabel velocidadPoke = new JLabel();
	int alturaPoke = 0;
	int pesoPoke = 0;
	JTextField nombreEquipo = new JTextField(15);
	Tipo t1 = Tipo.NULL;
	Tipo t2 = Tipo.NULL;
	
	Connection conn;

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
						
						conn = DriverManager.getConnection(BaseDatosPoke.url);
						Statement stmt = conn.createStatement();
					    ResultSet rs = stmt.executeQuery("Select id, ps, ataque, defensa, ataqueEspecial, "
					    		+ "defensaEspecial, velocidad, altura, peso, tipo1, tipo2 from pokemons where name ='"+comboPoke.getSelectedItem()+"';" );
					    psPoke.setText(rs.getInt("ps")+""); //TODO Aquí da un error con Abomasnow.
					    ataquePoke.setText(rs.getInt("ataque")+"");
					    defensaPoke.setText(rs.getInt("defensa")+"");
					    ataqueEspecialPoke.setText(rs.getInt("ataqueEspecial")+"");
					    defensaEspecialPoke.setText(rs.getInt("defensaEspecial")+"");
					    velocidadPoke.setText(rs.getInt("velocidad")+"");
					    alturaPoke = rs.getInt("altura");
					    pesoPoke = rs.getInt("peso");
					    t1 =Tipo.valueOf(rs.getString("tipo1").toUpperCase()); //TODO Falta que salgan los tipos de los pokes, no sé dónde ponerlos.
					    t2 =Tipo.valueOf(rs.getString("tipo2").toUpperCase());
					    idPoke = rs.getInt("id");
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

		comboMov1.setModel(new DefaultComboBoxModel<Movimiento>(movs));
		comboMov2.setModel(new DefaultComboBoxModel<Movimiento>(movs));
		comboMov3.setModel(new DefaultComboBoxModel<Movimiento>(movs));
		comboMov4.setModel(new DefaultComboBoxModel<Movimiento>(movs));
	}
	
	public Movimiento[] recuperaMovimientosDeComboBox() {
		Movimiento[] listado = new Movimiento[10];
		for (int i = 0; i < 10; i++) {
			listado[i] = comboMov1.getItemAt(i);
		}
		return listado;
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
				
				ArrayList<Movimiento> movimientos_poke = new ArrayList<Movimiento>();
				
				movimientos_poke.add((Movimiento)comboMov1.getSelectedItem());
				movimientos_poke.add((Movimiento)comboMov2.getSelectedItem());
				movimientos_poke.add((Movimiento)comboMov3.getSelectedItem());
				movimientos_poke.add((Movimiento)comboMov4.getSelectedItem());
						
				Pokemon pokeAdd = new Pokemon((String)comboPoke.getSelectedItem(), pesoPoke, alturaPoke, "lol", Integer.parseInt(psPoke.getText()), 
						Integer.parseInt(ataquePoke.getText()), Integer.parseInt(ataqueEspecialPoke.getText()), Integer.parseInt(defensaPoke.getText()), 
						Integer.parseInt(defensaEspecialPoke.getText()), Integer.parseInt(velocidadPoke.getText()), 50, movimientos_poke, t1, t2);
				
				pokeAdd.setId(idPoke);
				
				if (pokeballSeleccionada == 1) pb1.setPoke(pokeAdd);
				if (pokeballSeleccionada == 2) pb2.setPoke(pokeAdd);
				if (pokeballSeleccionada == 3) pb3.setPoke(pokeAdd);
				if (pokeballSeleccionada == 4) pb4.setPoke(pokeAdd);
				if (pokeballSeleccionada == 5) pb5.setPoke(pokeAdd);
				if (pokeballSeleccionada == 6) pb6.setPoke(pokeAdd);

			}
		});
		
		bGuardarEquipo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//TODO lo dejo así para que no se creen cosas raras en la bd hasta que pueda acabarlo
				if(!comprobarEquipoCorrecto()) return;
				try { //NO BORRAR NO BORRAR NO BORRAR NO BORRAR NO BORRAR NO BORRAR NO BORRAR
					conn = DriverManager.getConnection(BaseDatosPoke.url);
					Statement st = conn.createStatement();
					String username = VentanaInicio.u.getNombre();

					ResultSet rs1 = st.executeQuery("SELECT id FROM usuario WHERE nombre='"+username+"';");
					int idUser = rs1.getInt("id");
					rs1.close();

					ResultSet rs2 = st.executeQuery("SELECT nombre FROM equipo WHERE idUsuario=" + idUser + " AND nombre='" +nombreEquipo.getText().replace(" ", "_")+ "';");
					
					String teamName = "";
					while (rs2.next()) {
						teamName = rs2.getString("nombre");
						break;
					}
					
					rs2.close();

					if(teamName.equals(nombreEquipo.getText().replace(" ", "_"))) { //Si el nombre ya existe da un error;
						JOptionPane error = new JOptionPane();
						error.showMessageDialog(null, "Ya existe un equipo con ese nombre para el usuario " + username + ".", "Error", JOptionPane.ERROR_MESSAGE);
						return;
					}
						
					st.execute("INSERT INTO equipo (nombre, idUsuario) VALUES ('" + nombreEquipo.getText().replace(" ", "_") + "', " + idUser + ");");

					ResultSet rs3 = st.executeQuery("SELECT id FROM equipo WHERE nombre='"+nombreEquipo.getText().replace(" ", "_")+"' AND idUsuario="+idUser+";");
					int idEquipo = 0;
					while (rs3.next()) {
						idEquipo = rs3.getInt("id");
						break;
					}
					rs3.close();
					
					for (Pokeball p : listaPokeball) {	//Están metidos en whiles porque si no daba ResultSetException. Creo que es porque la 
														//consulta puede devolver más de un resultado (ya que en el where no hay nada que haga referencia
														//al id o a algún campo único) y no es posible asignar eso a una varianle int o string.
						ResultSet rsm1 = st.executeQuery("SELECT id FROM movimientos WHERE name='"+p.getPoke().getMovimientos_poke().get(0).toString().replace(" ", "_")+"';");
						int idMov1 = 0;
						while(rsm1.next()) {
							idMov1 = rsm1.getInt("id");
							break;
						}
						rsm1.close();
						
						ResultSet rsm2 = st.executeQuery("SELECT id FROM movimientos WHERE name='"+p.getPoke().getMovimientos_poke().get(1).toString().replace(" ", "_")+"';");
						int idMov2 = 0;
						while (rsm2.next()) {
							idMov2 = rsm2.getInt("id");
							break;
						}
						rsm2.close();
						
						ResultSet rsm3 = st.executeQuery("SELECT id FROM movimientos WHERE name='"+p.getPoke().getMovimientos_poke().get(2).toString().replace(" ", "_")+"';");
						int idMov3 = 0;
						while(rsm3.next()) {
							idMov3 = rsm3.getInt("id");
							break;
						}
						rsm3.close();
						
						ResultSet rsm4 = st.executeQuery("SELECT id FROM movimientos WHERE name='"+p.getPoke().getMovimientos_poke().get(3).toString().replace(" ", "_")+"';");
						int idMov4 = 0;
						while(rsm4.next()) {
							idMov4 = rsm4.getInt("id");
							break;
						}
						rsm4.close();
						
						ResultSet rsp = st.executeQuery("SELECT id FROM pokemons WHERE name='"+p.getNombre()+"';");
						int idPoke = 0;
						while(rsp.next()) {
							idPoke = rsp.getInt("id");
							break;
						}
						rsp.close();
						
						st.execute("INSERT INTO pokesequipo VALUES (" + idEquipo +  ", " + idPoke + ", "
						+ idMov1 + ", " + idMov2 + ", " + idMov3 + ", " + idMov4 + ");");
						System.out.println("fin");
					}
					
					conn.close();
					
					JOptionPane todoBien = new JOptionPane();
					todoBien.showMessageDialog(null, "Equipo guardado correctamente.", "¡Bien!", JOptionPane.INFORMATION_MESSAGE);

					
				} catch (SQLException e2) {
					System.out.println(e2.getMessage());
					
				}
				
			}
		});
	}
	
	private void creaPanelEquipo() {
		JPanel pPokeballs = new JPanel(new GridLayout(6,1));
		ImageIcon icono = new ImageIcon(getClass().getResource("/img/pokeball.png"));
		ImageIcon iconoPokeball = new ImageIcon(icono.getImage().getScaledInstance(30, 30, java.awt.Image.SCALE_DEFAULT));
		
		listaPokeball[0] = (pb1 = new Pokeball(iconoPokeball));
		listaPokeball[1] = (pb2 = new Pokeball(iconoPokeball));
		listaPokeball[2] = (pb3 = new Pokeball(iconoPokeball));
		listaPokeball[3] = (pb4 = new Pokeball(iconoPokeball));
		listaPokeball[4] = (pb5 = new Pokeball(iconoPokeball));
		listaPokeball[5] = (pb6 = new Pokeball(iconoPokeball));
		
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

				if(pb1.getPoke()==null) {
					comboPoke.setSelectedIndex(-1);
					comboMov1.setSelectedIndex(-1);
					comboMov2.setSelectedIndex(-1);
					comboMov3.setSelectedIndex(-1);
					comboMov4.setSelectedIndex(-1);
				} else {
					actualizaCombos(recuperaMovimientosDeComboBox());
					comboPoke.setSelectedItem(pb1.getPoke().getNombre());
					comboMov1.setSelectedItem(pb1.getPoke().getMovimientos_poke().get(0));
					comboMov2.setSelectedItem(pb1.getPoke().getMovimientos_poke().get(1));
					comboMov3.setSelectedItem(pb1.getPoke().getMovimientos_poke().get(2));
					comboMov4.setSelectedItem(pb1.getPoke().getMovimientos_poke().get(3));
					t1 = pb1.getPoke().getTipos().get(0);
					t2 = pb1.getPoke().getTipos().get(1);
				}
			}
		});
		
		pb2.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if (pokeballSeleccionada == 2) return;
				pokeballSeleccionada = 2;
				
				if(pb2.getPoke()==null) {
					comboPoke.setSelectedIndex(-1);
					comboMov1.setSelectedIndex(-1);
					comboMov2.setSelectedIndex(-1);
					comboMov3.setSelectedIndex(-1);
					comboMov4.setSelectedIndex(-1);
				} else {
					actualizaCombos(recuperaMovimientosDeComboBox());
					comboPoke.setSelectedItem(pb2.getPoke().getNombre());
					comboMov1.setSelectedItem(pb2.getPoke().getMovimientos_poke().get(0));
					comboMov2.setSelectedItem(pb2.getPoke().getMovimientos_poke().get(1));
					comboMov3.setSelectedItem(pb2.getPoke().getMovimientos_poke().get(2));
					comboMov4.setSelectedItem(pb2.getPoke().getMovimientos_poke().get(3));
					t1 = pb2.getPoke().getTipos().get(0);
					t2 = pb2.getPoke().getTipos().get(1);
				}
			}
		});
		
		pb3.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if (pokeballSeleccionada == 3) return;
				pokeballSeleccionada = 3;
				
				if(pb3.getPoke()==null) {
					comboPoke.setSelectedIndex(-1);
					comboMov1.setSelectedIndex(-1);
					comboMov2.setSelectedIndex(-1);
					comboMov3.setSelectedIndex(-1);
					comboMov4.setSelectedIndex(-1);

				} else {
					actualizaCombos(recuperaMovimientosDeComboBox());
					comboPoke.setSelectedItem(pb3.getPoke().getNombre());
					comboMov1.setSelectedItem(pb3.getPoke().getMovimientos_poke().get(0));
					comboMov2.setSelectedItem(pb3.getPoke().getMovimientos_poke().get(1));
					comboMov3.setSelectedItem(pb3.getPoke().getMovimientos_poke().get(2));
					comboMov4.setSelectedItem(pb3.getPoke().getMovimientos_poke().get(3));
					t1 = pb3.getPoke().getTipos().get(0);
					t2 = pb3.getPoke().getTipos().get(1);
				}
			}
		});	
		
		pb4.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if (pokeballSeleccionada == 4) return;
				pokeballSeleccionada = 4;
				
				if(pb4.getPoke()==null) {
					comboPoke.setSelectedIndex(-1);
					comboMov1.setSelectedIndex(-1);
					comboMov2.setSelectedIndex(-1);
					comboMov3.setSelectedIndex(-1);
					comboMov4.setSelectedIndex(-1);

				} else {
					actualizaCombos(recuperaMovimientosDeComboBox());
					comboPoke.setSelectedItem(pb4.getPoke().getNombre());
					comboMov1.setSelectedItem(pb4.getPoke().getMovimientos_poke().get(0));
					comboMov2.setSelectedItem(pb4.getPoke().getMovimientos_poke().get(1));
					comboMov3.setSelectedItem(pb4.getPoke().getMovimientos_poke().get(2));
					comboMov4.setSelectedItem(pb4.getPoke().getMovimientos_poke().get(3));
					t1 = pb4.getPoke().getTipos().get(0);
					t2 = pb4.getPoke().getTipos().get(1);
				}
			}
		});	
		
		pb5.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if (pokeballSeleccionada == 5) return;
				pokeballSeleccionada = 5;
				
				if(pb5.getPoke()==null) {
					comboPoke.setSelectedIndex(-1);
					comboMov1.setSelectedIndex(-1);
					comboMov2.setSelectedIndex(-1);
					comboMov3.setSelectedIndex(-1);
					comboMov4.setSelectedIndex(-1);

				} else {
					actualizaCombos(recuperaMovimientosDeComboBox());
					comboPoke.setSelectedItem(pb5.getPoke().getNombre());
					comboMov1.setSelectedItem(pb5.getPoke().getMovimientos_poke().get(0));
					comboMov2.setSelectedItem(pb5.getPoke().getMovimientos_poke().get(1));
					comboMov3.setSelectedItem(pb5.getPoke().getMovimientos_poke().get(2));
					comboMov4.setSelectedItem(pb5.getPoke().getMovimientos_poke().get(3));
					t1 = pb5.getPoke().getTipos().get(0);
					t2 = pb5.getPoke().getTipos().get(1);
				}
			}
		});	
		
		pb6.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if (pokeballSeleccionada == 6) return;
				pokeballSeleccionada = 6;
				
				if(pb6.getPoke()==null) {
					comboPoke.setSelectedIndex(-1);
					comboMov1.setSelectedIndex(-1);
					comboMov2.setSelectedIndex(-1);
					comboMov3.setSelectedIndex(-1);
					comboMov4.setSelectedIndex(-1);

				} else {
					actualizaCombos(recuperaMovimientosDeComboBox());
					comboPoke.setSelectedItem(pb6.getPoke().getNombre());
					comboMov1.setSelectedItem(pb6.getPoke().getMovimientos_poke().get(0));
					comboMov2.setSelectedItem(pb6.getPoke().getMovimientos_poke().get(1));
					comboMov3.setSelectedItem(pb6.getPoke().getMovimientos_poke().get(2));
					comboMov4.setSelectedItem(pb6.getPoke().getMovimientos_poke().get(3));
					t1 = pb5.getPoke().getTipos().get(0);
					t2 = pb5.getPoke().getTipos().get(1);
				}
			}
		});	
	}
	
	/**
	 * Comprueba que el equipo cumpla dos normas: que no haya dos pokémons repetidos y que un mismo pokémon no tenga un movimiento
	 * dos veces o más. Es un poco desastre pero funciona.
	 * @return true si cumple ambas, false en caso contrario, y notifica al usuario de cuál de las dos no está cumpliendo.
	 */
	private boolean comprobarEquipoCorrecto() {
		int errores = 0;
		for (Pokeball pokeball : listaPokeball) {
			errores = 0;
			if(pokeball.isVacia()) {
				JOptionPane pokeVacio = new JOptionPane();
				pokeVacio.showMessageDialog(null, "Hacen falta 6 pokémons para poder crear el equipo.", "Error", JOptionPane.ERROR_MESSAGE);
				return false;
			}
			
			for (Pokeball pokeball2 : listaPokeball) {
				if(pokeball2.isVacia()) {
					JOptionPane pokeVacio = new JOptionPane();
					pokeVacio.showMessageDialog(null, "Hacen falta 6 pokémons para poder crear el equipo.", "Error", JOptionPane.ERROR_MESSAGE);
					return false;
				}
				
				if (pokeball.getPoke().getNombre()==pokeball2.getPoke().getNombre()) errores += 1;
				if (errores==2) {
					JOptionPane pokeRepe = new JOptionPane();
					pokeRepe.showMessageDialog(null, "Error, el equipo no puede tener Pokémons repetidos.", "Error", JOptionPane.ERROR_MESSAGE);
					return false;
				};
			}
			errores = 0;
			ArrayList<Movimiento> listaMovs = pokeball.getPoke().getMovimientos_poke();
			
			for (Movimiento movimiento : listaMovs) {
				for (int contador = 0; contador < 4; contador++) {
					if(movimiento.equals(listaMovs.get(contador))) errores += 1;
					contador += 1;
				}
			}
			
			if (errores>=5) {
				JOptionPane movRepe = new JOptionPane();
				movRepe.showMessageDialog(null, "Error, el Pokémon " + pokeball.getPoke().getNombre() + " tiene algún movimiento repetido.", "Error", JOptionPane.ERROR_MESSAGE);
				return false;
			};
			
		}
			return true;
	}


}
