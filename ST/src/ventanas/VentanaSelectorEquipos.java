package ventanas;

import java.awt.BorderLayout;
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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import database.BaseDatosPoke;
import principal.Pokemon;
import principal.Usuario;

public class VentanaSelectorEquipos extends JFrame {
	
	VentanaSelectorEquipos vs;
	JButton bSeleccionar;
	Usuario u = VentanaInicio.u;
	ArrayList<String> nombresEquipos = new ArrayList<String>();
	ArrayList<Integer> idsEquipos = new ArrayList<Integer>();
	JPanel pCentro = new JPanel(new GridLayout(1,6));
	
	HashMap<String, Integer[][]> pokesEnEquipo = new HashMap<String, Integer[][]>();
	
	public VentanaSelectorEquipos() {
		vs = this;
		
		setTitle("Selector de equipos");
		setLocation(200, 100);
		setSize(700, 500);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		creaPanelInf();

		leerEquipos();
		JPanel pCombo = new JPanel();
		JComboBox comboEquipos = new JComboBox<String>(nombresEquipos.toArray(new String[nombresEquipos.size()]));
		pCombo.add(comboEquipos);
		add(pCombo, BorderLayout.NORTH);
		
		comboEquipos.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				muestraEquipoSeleccionado((String)comboEquipos.getSelectedItem());
				vs.add(pCentro, BorderLayout.CENTER);
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
		pCentro = new JPanel(new GridLayout(6,1));

		for (Integer[] miembroEquipo : equipo) {
			
			JPanel pInfo = new JPanel();
			JPanel pPoke = new JPanel();
			JPanel pNombre = new JPanel();
			JPanel pImg = new JPanel();
			JPanel pMovimientos = new JPanel(new GridLayout(2, 2));
			JLabel imgPoke = new JLabel();
			pPoke.add(pNombre, BorderLayout.NORTH);
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
				while(rs.next()) pMovimientos.add(new JLabel(rs.getString("name").replace("_", " ")));
				
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
				//TODO
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
