package ventanas;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import database.BaseDatosPoke;
import principal.Usuario;

public class VentanaClasif extends JFrame{
	String [] encabezado = {"Nombre","Puntuacion","PartGanadas","PartPerdidas",  "WinRate"};
	JPanel panel_sup;
	JPanel panel_inf;
	JLabel error;
	JScrollPane sp_1;
	public VentanaClasif() {
		setSize(500, 310);
		setLocation(400, 200);
		setLayout(new GridLayout(2, 0));
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		crearPanel_sup();
		crearPanel_inf();
		crearTabla();
	}
	private void crearPanel_inf() {
		panel_inf = new JPanel(new BorderLayout());
		JPanel central = new JPanel(new FlowLayout());
		JPanel jtable_inf = new JPanel();
		JLabel label = new JLabel("Intoduce el nombre el jugador que desea buscar:");
		JTextField texto = new JTextField(15);
		JPanel panel_boton = new JPanel();
		JButton aceptar = new JButton("OK");
		JButton volver = new JButton("VOLVER");
		aceptar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				jtable_inf.removeAll();
				try (
					Connection conn = DriverManager.getConnection(BaseDatosPoke.url);
					Statement stmt  = conn.createStatement();
				    ResultSet rs_1 = stmt.executeQuery("Select nombre, puntuacion, partidasGanadas, partidasPerdidas FROM usuario where nombre ='" + texto.getText()+"'")){
					if (rs_1.next()) {
						String[][] dato = new String[1][5];
						dato[0][0]= rs_1.getString("nombre");
						dato[0][1]= String.valueOf(rs_1.getInt("puntuacion"));
						dato[0][2]= String.valueOf(rs_1.getInt("partidasGanadas"));
			        	dato[0][3] = String.valueOf(rs_1.getInt("partidasPerdidas"));	
			        	if (rs_1.getInt("partidasGanadas") == 0) {
			        		dato[0][4] = "0%";
			        	}else {
			        		double win_rate  = rs_1.getInt("partidasGanadas")/(rs_1.getInt("partidasPerdidas") + rs_1.getInt("partidasGanadas"));
			        		dato[0][4] = String.valueOf(win_rate + "%");
			        	}
			        	JTable t_1 = new JTable(dato, encabezado);
						sp_1 = new JScrollPane(t_1); 
						jtable_inf.add(sp_1);
						jtable_inf.revalidate();
					}else {
						error = new JLabel("No se ha podido encontrar a este usuario");
						jtable_inf.add(error);
						jtable_inf.revalidate();
					}
					
					
				}catch(SQLException e1) {
				      System.out.println(e1.getMessage());
				}
				
			}
		});
		
		volver.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		central.add(label);
		central.add(texto);
		central.add(jtable_inf);
		
		panel_boton.add(aceptar);
		panel_boton.add(volver);
		
		panel_inf.add(central, BorderLayout.CENTER);
		panel_inf.add(panel_boton, BorderLayout.SOUTH);
		add(panel_inf);
	}
	private void crearPanel_sup() {
		panel_sup = new JPanel();
		add(panel_sup);
		
	}
	private void crearTabla() {
		String [][] datos = null;
		try (
			Connection conn = DriverManager.getConnection(BaseDatosPoke.url);
			Statement stmt  = conn.createStatement();
	        ResultSet rs_1 = stmt.executeQuery("SELECT COUNT (*) FROM usuario")){
			datos = new String [rs_1.getInt(1)][rs_1.getInt(1)];
			int columna = 0;
			ResultSet rs    = stmt.executeQuery("SELECT nombre, puntuacion, partidasGanadas, partidasPerdidas FROM usuario WHERE puntuacion >= (SELECT avg(puntuacion) FROM usuario ) ORDER BY puntuacion");
	        while(rs.next()) {
	        	datos[columna][0] = rs.getString("nombre");
	        	datos[columna][1] = String.valueOf(rs.getInt("puntuacion"));
	        	datos[columna][2] = String.valueOf(rs.getInt("partidasGanadas"));
	        	datos[columna][3] = String.valueOf(rs.getInt("partidasPerdidas"));
	        	if (rs.getInt("partidasGanadas") == 0) {
	        		datos[columna][4] = "0%";
	        	}else {
	        	double win_rate  = rs.getInt("partidasGanadas")/(rs.getInt("partidasPerdidas") + rs.getInt("partidasGanadas"));
	        	datos[columna][4] = String.valueOf(win_rate + "%");
	        	}
	            columna ++;	
	        }
	            
	      } catch (SQLException e1) {
		      System.out.println(e1.getMessage());
	      }
		
		JTable t = new JTable(datos, encabezado);
		JScrollPane sp = new JScrollPane(t); 
        panel_sup.add(sp); 

	}
	
}
