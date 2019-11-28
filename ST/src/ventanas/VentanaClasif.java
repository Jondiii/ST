package ventanas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;

import database.BaseDatosPoke;
import principal.Usuario;

public class VentanaClasif extends JFrame{
	String [] encabezado = {"Nombre","Puntuacion","PartGanadas","PartPerdidas",  "WinRate"};
	public VentanaClasif() {
		setSize(700, 600);
		setLocation(400, 200);
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		crearTabla();
	}
	private void crearTabla() {
		String [][] datos = null;
		try (
			Connection conn = DriverManager.getConnection(BaseDatosPoke.url);
			Statement stmt  = conn.createStatement();
	        ResultSet rs_1 = stmt.executeQuery("SELECT COUNT (*) FROM usuario")){
			datos = new String [rs_1.getInt(1)][rs_1.getInt(1)];
			int columna = 0;
			ResultSet rs    = stmt.executeQuery("SELECT nombre, puntuacion, partidasGanadas, partidasPerdidas FROM usuario WHERE puntuacion >= (SELECT avg(puntuacion) FROM usuario )");
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
        add(sp); 

	}
	
}
