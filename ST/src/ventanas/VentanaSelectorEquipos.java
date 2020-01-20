package ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import database.BaseDatosPoke;
import principal.AlcanceMovimiento;
import principal.CategoriaMov;
import principal.EfectoSecundario;
import principal.EstadosAlterados;
import principal.Movimiento;
import principal.Pokemon;
import principal.Tipo;
import principal.Usuario;

public class VentanaSelectorEquipos extends JFrame {
	
	VentanaSelectorEquipos vs;
	JButton bSeleccionar;
	Usuario u = VentanaInicio.u;
	ArrayList<String> nombresEquipos = new ArrayList<String>();
	ArrayList<Integer> idsEquipos = new ArrayList<Integer>();
	JPanel pCentro = new JPanel(new GridLayout(6,1));
	JComboBox comboEquipos;
	HashMap<String, Integer[][]> pokesEnEquipo = new HashMap<String, Integer[][]>();
	ArrayList<Pokemon> equipoFinal = new ArrayList<Pokemon>();
	
	public VentanaSelectorEquipos() {
		vs = this;
		
		setTitle("Selector de equipos");
		setLocation(200, 0);
		setSize(500, 1000);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		creaPanelInf();
		
		leerEquipos();
		JPanel pCombo = new JPanel();
		comboEquipos = new JComboBox<String>(nombresEquipos.toArray(new String[nombresEquipos.size()]));
		pCombo.add(comboEquipos);
		vs.add(pCentro, BorderLayout.CENTER);
		add(pCombo, BorderLayout.NORTH);
		
		comboEquipos.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				muestraEquipoSeleccionado((String)comboEquipos.getSelectedItem());
				bSeleccionar.setEnabled(true);
				vs.revalidate();
			}
		});
		
		setVisible(true);
	}

	private void leerEquipos() {
		try {
			Connection conn = DriverManager.getConnection(BaseDatosPoke.url);
			Statement stmt = conn.createStatement();
		    ResultSet idResult = stmt.executeQuery("SELECT * FROM equipo e, usuario u WHERE e.idUsuario=u.id AND u.nombre='"+u.getNombre()+"';");
		    
		    while(idResult.next()) {
		    	int id = idResult.getInt("id");//No sé cómo no se confunde entre el id del equipo y el del usuario. Poniendo equipo.id da error.
			    if (!idsEquipos.contains(id)) idsEquipos.add(id);
		    }
		    
		    idResult.close();
		    
		    for (Integer id : idsEquipos) {
		    	 ResultSet pokesEquipoResult = stmt.executeQuery("SELECT * FROM equipo e, pokesequipo pe WHERE e.id=pe.idEquipo AND e.id="+id+";");
		    	 Integer[][] arrayPokesEquipo = new Integer[6][6];
		    	 
		    	 String teamName = "";
		    	 int count = 0;
		    	 
		    	 while(pokesEquipoResult.next()) {
		    		 teamName = pokesEquipoResult.getString("nombre").replace("_", " ");
		    		 if (!nombresEquipos.contains(teamName)) nombresEquipos.add(teamName);
		    		 
		    		 int idEquipo = id;
			    	 int idPoke = pokesEquipoResult.getInt("idpoke");
			    	 int idmov1 = pokesEquipoResult.getInt("mov1");
			    	 int idmov2 = pokesEquipoResult.getInt("mov2");
			    	 int idmov3 = pokesEquipoResult.getInt("mov3");
			    	 int idmov4 = pokesEquipoResult.getInt("mov4");
			    	 
			    	 Integer[] listadoDatos = {idEquipo, idPoke, idmov1, idmov2, idmov3, idmov4};
			    	 arrayPokesEquipo[count] = listadoDatos;
			    	 count += 1;
			    	 
			     }
		    	 
		    	 pokesEnEquipo.put(teamName, arrayPokesEquipo);
			}
		    
		    conn.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void muestraEquipoSeleccionado(String nombre) {
		Integer[][] equipo = pokesEnEquipo.get(nombre);
		
		pCentro.removeAll();

		for (Integer[] miembroEquipo : equipo) {
			
			JPanel pInfo = new JPanel();
			JPanel pPoke = new JPanel();
			JPanel pNombre = new JPanel();
			JPanel pImg = new JPanel();
			JPanel pMovimientos = new JPanel(new GridLayout(2, 2));
			JLabel imgPoke = new JLabel();
			pPoke.add(pNombre, BorderLayout.NORTH);
			pImg.add(imgPoke);
			pPoke.add(pImg, BorderLayout.CENTER);
			pInfo.add(pPoke, BorderLayout.WEST);
			pInfo.add(pMovimientos, BorderLayout.EAST);
			pCentro.add(pInfo);
			
			try {
				Connection conn = DriverManager.getConnection(BaseDatosPoke.url);
				Statement stmt = conn.createStatement();
				
				ResultSet rs = stmt.executeQuery("SELECT name, tipo FROM movimientos WHERE id IN ("+miembroEquipo[2]
						+", "+miembroEquipo[3]+", "+miembroEquipo[4]+", "+miembroEquipo[5]+");");
				
				pMovimientos.removeAll();
				while(rs.next()) {
					JLabel movimiento = new JLabel();
					movimiento.setText(rs.getString("name").replace("_", " ")+"  ");
					if (rs.getString("tipo").equals("Agua") || rs.getString("tipo").contentEquals("Dragon") || rs.getString("tipo").equals("Fantasma") || rs.getString("tipo").equals("Siniestro") || rs.getString("tipo").equals("Roca")) {
						movimiento.setForeground(Color.white);
					}
					Tipo type = Tipo.valueOf(rs.getString("tipo").toUpperCase());
					movimiento.setOpaque(true);
					movimiento.setBorder(BorderFactory.createBevelBorder(0));
					movimiento.setBackground(type.getColor());
					pMovimientos.add(movimiento);
				}
				
				rs.close();
				
				ResultSet rs2 = stmt.executeQuery("SELECT name FROM pokemons WHERE id="+miembroEquipo[1]+";");
				
				pNombre.removeAll();
				pNombre.add(new JLabel(rs2.getString("name").replace("_", " ")));
				
				ImageIcon icono_1 = new ImageIcon(getClass().getResource("/img/" + rs2.getString("name") + "_frente.png"));
				ImageIcon icono_2 = new ImageIcon(icono_1.getImage().getScaledInstance(100, 100, java.awt.Image.SCALE_DEFAULT));
				imgPoke.setIcon(icono_2);

				conn.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
	
		}
		
	}
	
	public void creaPanelInf() {
		JPanel pInf = new JPanel(new FlowLayout());
		bSeleccionar = new JButton("Seleccionar equipo");
		JButton bSalir = new JButton("Salir");
		bSeleccionar.setEnabled(false);
		
		pInf.add(bSeleccionar);
		pInf.add(bSalir);
		
		bSeleccionar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Integer[][] equipo = pokesEnEquipo.get(comboEquipos.getSelectedItem());
				try {
					Connection conn = DriverManager.getConnection(BaseDatosPoke.url);
					Statement stmt = conn.createStatement();
					
					for (Integer[] teamMember : equipo) {
						ArrayList<Movimiento> listaMovs = new ArrayList<Movimiento>();
						ResultSet rMov = stmt.executeQuery("SELECT * FROM movimientos WHERE id IN ("+teamMember[2]
								+", "+teamMember[3]+", "+teamMember[4]+", "+teamMember[5]+");");
						
						while (rMov.next()) {
							listaMovs.add(new Movimiento(rMov.getString("name").replace("_", " "), Tipo.valueOf(rMov.getString("tipo").toUpperCase()), rMov.getInt("potencia"), rMov.getInt("precision"),
													rMov.getInt("pp"), CategoriaMov.valueOf(rMov.getString("categoria").toUpperCase()),	rMov.getInt("prioridad"),
													AlcanceMovimiento.valueOf(rMov.getString("alcance").toUpperCase()), EstadosAlterados.valueOf(rMov.getString("efecto").toUpperCase()),
													EfectoSecundario.valueOf(rMov.getString("estadoSecundario").toUpperCase()), rMov.getFloat("prEstado"), rMov.getFloat("prStats"), rMov.getFloat("prEfecto"),
													rMov.getBoolean("cambioStatsARival"), rMov.getInt("ataque"), rMov.getInt("defensa"), rMov.getInt("ataqueEspecial"),
													rMov.getInt("defensaEspecial"), rMov.getInt("velocidad")));
						}
						
						rMov.close();
						
						ResultSet rPoke = stmt.executeQuery("SELECT * FROM pokemons WHERE id="+teamMember[1]+";");
						
						equipoFinal.add(new Pokemon(rPoke.getString("name"), rPoke.getInt("altura"), rPoke.getInt("peso"), " ", rPoke.getInt("ps"),
								rPoke.getInt("ataque"), rPoke.getInt("defensa"), rPoke.getInt("ataqueEspecial"), rPoke.getInt("defensaEspecial"),
								rPoke.getInt("velocidad"), 50, listaMovs, Tipo.valueOf(rPoke.getString("tipo1").toUpperCase()),
								Tipo.valueOf(rPoke.getString("tipo2").toUpperCase())));
					}
					
					conn.close();
				} catch (SQLException e2) {
					System.out.println(e2.getMessage());
				}
			vs.dispose();//TODO antes de esto hay que hacer que el equipo se guarde en algún lado fuera de esta ventana.
			
			}
		});
		
		bSalir.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				vs.dispose();
			}
		});
		
		vs.add(pInf, BorderLayout.SOUTH);
	}
}
