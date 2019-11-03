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
			statement.executeUpdate("create table pokemon (id integer NOT NULL, name string, ps integer, ataque integer,"
					+ "defensa integer, ataqueEspecial integer, defensaEspecial integer, velocidad integer,"
					+ "altura integer, peso integer, mov1 integer, mov2 integer, mov3 integer, mov4 integer, "
					+ "mov5 integer, mov6 integer, mov7 integer, mov8 integer, mov9 integer, mov10 integer, tipo1 string,"
					+ "tipo2 string, PRIMARY KEY(id), FOREIGN KEY (mov1) REFERENCES movimiento(id),"
					+ "FOREIGN KEY (mov2) REFERENCES movimiento(id), FOREIGN KEY (mov3) REFERENCES movimiento(id),"
					+ "FOREIGN KEY (mov4) REFERENCES movimiento(id), FOREIGN KEY (mov5) REFERENCES movimiento(id),"
					+ "FOREIGN KEY (mov6) REFERENCES movimiento(id), FOREIGN KEY (mov7) REFERENCES movimiento(id),"
					+ "FOREIGN KEY (mov8) REFERENCES movimiento(id), FOREIGN KEY (mov9) REFERENCES movimiento(id),"
					+ "FOREIGN KEY (mov10) REFERENCES movimiento(id)");
			
			statement.executeUpdate("create table movimiento (id integer NOT NULL, name string, tipo string,"
					+ "potencia integer, precision integer, pp integer, prioridad integer, efecto string,"
					+ "categoria string, alcance string, probEfecto integer, PRIMARY KEY(id))");
			
			statement.executeUpdate("create table usuario (id integer NOT NULL, nombre string, contraseña string,"
					+ "entrenador string, PRIMARY KEY(id))");
			
			statement.executeUpdate("create table equipo (id integer NOT NULL, nombre string, idUsuario integer)"
					+ ", PRIMARY KEY(id), FOREIGN KEY (idUsuario) REFERENCES usuario(id)");
//			int res = statement.executeUpdate("insert into person values(1, 'leo')");
//			res = statement.executeUpdate("insert into person values(2, 'yui')");
			System.out.println("BD creada (espero).");
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
