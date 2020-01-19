package ventanas;

import java.awt.BorderLayout;
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
				bSeleccionar.setEnabled(true);
				revalidate();
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
		
		JPanel pCentro = new JPanel(new GridLayout(1,6));
		vs.add(pCentro, BorderLayout.CENTER);
		
		JPanel pInfo = new JPanel();
		JPanel pPoke = new JPanel();
		JPanel pNombre = new JPanel();
		JPanel pImg = new JPanel();
		JPanel pMovimientos = new JPanel(new GridLayout(2, 2));
		pPoke.add(pNombre, BorderLayout.NORTH);
		pPoke.add(pImg, BorderLayout.CENTER);
		pInfo.add(pPoke, BorderLayout.WEST);
		pInfo.add(pMovimientos, BorderLayout.EAST);
		
		for (Integer[] miembroEquipo : equipo) {
			try {
				Connection conn = DriverManager.getConnection(BaseDatosPoke.url);
				Statement stmt = conn.createStatement();
				
				ResultSet rs = stmt.executeQuery("SELECT name, tipo FROM movimientos WHERE id IN ("+miembroEquipo[2]
						+", "+miembroEquipo[3]+", "+miembroEquipo[4]+", "+miembroEquipo[5]+");");
				
				
				
				conn.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		
	}
	
	public void creaPanelInf() {
		JPanel pInf = new JPanel();
		bSeleccionar = new JButton("Seleccionar equipo");
		JButton bSalir = new JButton("Salir");
		bSeleccionar.setEnabled(false);
		
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
	}
}
