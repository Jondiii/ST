package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Clase que crea la Base de Datos. En principio solo la usaremos una única vez, una vez creada pensamos editar la
 * base de datos con programas externos.
 * 
 * FALTA: Claves primarias y claves externas. Relaciones faltan también. 
 */
public class BaseDatosPoke {
	
	public static void main(String[] args) throws ClassNotFoundException {

		// Carga el sqlite-JDBC driver usando el cargador de clases
		Class.forName("org.sqlite.JDBC");

		Connection connection = null;
		try {
			// Crear una conexión de BD
			connection = DriverManager.getConnection("jdbc:sqlite:database/PokemonStars.db");
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30);  // poner timeout 30 msg

			statement.executeUpdate("drop table if exists person");
			statement.executeUpdate("create table pokemon (id integer, name string, ps integer, ataque integer,"
					+ "defensa integer, ataqueEspecial integer, defensaEspecial integer, velocidad integer,"
					+ "altura integer, peso integer, mov1 integer, mov2 integer, mov3 integer, mov4 integer, "
					+ "mov5 integer, mov6 integer, mov7 integer, mov8 integer, mov9 integer, mov10 integer, tipo1 string,"
					+ "tipo2 string)");
			
			statement.executeUpdate("create table movimiento (id integer, name string, tipo string,"
					+ "potencia integer, precision integer, pp integer, prioridad integer, efecto string,"
					+ "categoria string, alcance string, probEfecto integer)");
			
			statement.executeUpdate("create table usuario (id integer, nombre string, contraseña string,"
					+ "entrenador string)");
			
			statement.executeUpdate("create table equipo (id integer, nombre string, idUsuario integer)");
			int res = statement.executeUpdate("insert into person values(1, 'leo')");
			res = statement.executeUpdate("insert into person values(2, 'yui')");
		} catch(SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			try {
				if(connection != null)
					connection.close();
			} catch(SQLException e) {
				// Cierre de conexión fallido
				System.err.println(e);
			}
		}
	}
}
