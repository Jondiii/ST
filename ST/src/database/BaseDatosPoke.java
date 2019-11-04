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
	
	static String url = "jdbc:sqlite:src/database/PokemonStars";
	
	public static void crearTabla() {
		String sql = "CREATE TABLE IF NOT EXISTS pokemons (\n"
                + "    id integer NOT NULL PRIMARY KEY,\n"
                + "    name text ,\n"
                + "    ps integer,\n"
                + "    ataque  integer,\n"
                + "    defensa integer,\n"
                + "    ataqueEspecial integer,\n"
                + "	   defensaEspecial integer,\n"
                + "    velocidad integer,\n"
                + "    altura integer,\n"
                + "    peso integer, \n"
                + "    mov1 integer, \n"
                + "    mov2 integer, \n"
                + "    mov3 integer,\n "
                + "    mov4 integer,\n"
                + "    mov5 integer,\n"
                + "    mov6 integer, \n"
                + "    mov7 integer,\n"
                + "    mov8 integer, \n"
                + "    mov9 integer, \n"
                + "    mov10 integer, \n"
                + "    tipo1 text ,\n"
                + "    tipo2 text \n"
//                + "PRIMARY KEY(id),\n" 
//                + "FOREIGN KEY (mov1),\n" 
//                + "REFERENCES movimiento(id),\n"
//    			+ "FOREIGN KEY (mov2) REFERENCES movimiento(id),\n"
//    			+ "FOREIGN KEY (mov3) REFERENCES movimiento(id),\n"
//    			+ "FOREIGN KEY (mov4) REFERENCES movimiento(id),\n"
//    			+ "FOREIGN KEY (mov5) REFERENCES movimiento(id),\n"
//    			+ "FOREIGN KEY (mov6) REFERENCES movimiento(id), \n"
//    			+ "FOREIGN KEY (mov7) REFERENCES movimiento(id),\n"
//    			+ "FOREIGN KEY (mov8) REFERENCES movimiento(id), \n"
//    			+ "FOREIGN KEY (mov9) REFERENCES movimiento(id),\n"
//    			+ "FOREIGN KEY (mov10) REFERENCES movimiento(id) \n"
                + ");";
		String sql_1 = "CREATE TABLE IF NOT EXISTS movimientos (\n"
				+ "id integer NOT NULL PRIMARY KEY,\n"
				+ "name text ,\n"
				+ "tipo text ,\n"
				+ "potencia integer,\n"
				+ " precision integer, \n"
				+ "pp INTEGER,\n"
				+ " prioridad integer,\n"
				+ " efecto text ,\n"
				+ "categoria text ,\n"
				+ " alcance text ,\n"
				+ " probEfecto integer\n"
//				+ " PRIMARY KEY(id)) \n"
				+ ");";
		String sql_2 = "CREATE TABLE IF NOT EXISTS usuario (\n"
				+ "id integer NOT NULL PRIMARY KEY,\n"
				+ " nombre text ,\n"
				+ " contraseña text ,\n"
				+ "entrenador text \n"
//				+ " PRIMARY KEY(id)) \n"
				+ ");";
		String sql_3 = "CREATE TABLE IF NOT EXISTS equipo (\n"
				+ "id integer NOT NULL PRIMARY KEY,\n"
				+ "nombre text ,\n"
				+ "idUsuario integer\n"
//				+ "PRIMARY KEY(id),\n"
//				+ "FOREIGN KEY (idUsuario) REFERENCES usuario(id) \n"
				+ ");";
		try (Connection conn = DriverManager.getConnection(url)) {
			 Statement stmt = conn.createStatement(); 
			 //crea nuevas tablas
		            stmt.execute(sql);
		            stmt.execute(sql_1);
		            stmt.execute(sql_2);
		            stmt.execute(sql_3);
		}catch(SQLException e){
			
		}
	}
	
	public static void main(String[] args) throws ClassNotFoundException {
		crearTabla();
////		 Carga el sqlite-JDBC driver usando el cargador de clases
//		Class.forName("org.sqlite.JDBC");
//
//		Connection connection = null;
//		try {
//			// Crear una conexión de BD
//			connection = DriverManager.getConnection("jdbc:sqlite:src/database/PokemonStars");
//			Statement statement = connection.createStatement();
//			statement.setQueryTimeout(30);  // poner timeout 30 msg
//
//			statement.executeUpdate("create table pokemon (id integer NOT NULL, name string, ps integer, ataque integer,"
//					+ "defensa integer, ataqueEspecial integer, defensaEspecial integer, velocidad integer,"
//					+ "altura integer, peso integer, mov1 integer, mov2 integer, mov3 integer, mov4 integer, "
//					+ "mov5 integer, mov6 integer, mov7 integer, mov8 integer, mov9 integer, mov10 integer, tipo1 string,"
//					+ "tipo2 string, PRIMARY KEY(id), FOREIGN KEY (mov1) REFERENCES movimiento(id),"
//					+ "FOREIGN KEY (mov2) REFERENCES movimiento(id), FOREIGN KEY (mov3) REFERENCES movimiento(id),"
//					+ "FOREIGN KEY (mov4) REFERENCES movimiento(id), FOREIGN KEY (mov5) REFERENCES movimiento(id),"
//					+ "FOREIGN KEY (mov6) REFERENCES movimiento(id), FOREIGN KEY (mov7) REFERENCES movimiento(id),"
//					+ "FOREIGN KEY (mov8) REFERENCES movimiento(id), FOREIGN KEY (mov9) REFERENCES movimiento(id),"
//					+ "FOREIGN KEY (mov10) REFERENCES movimiento(id)");
//			
//			statement.executeUpdate("create table movimiento (id integer NOT NULL, name string, tipo string,"
//					+ "potencia integer, precision integer, pp integer, prioridad integer, efecto string,"
//					+ "categoria string, alcance string, probEfecto integer, PRIMARY KEY(id))");
//			
//			statement.executeUpdate("create table usuario (id integer NOT NULL, nombre string, contraseña string,"
//					+ "entrenador string, PRIMARY KEY(id))");
//			
//			statement.executeUpdate("create table equipo (id integer NOT NULL, nombre string, idUsuario integer)"
//					+ ", PRIMARY KEY(id), FOREIGN KEY (idUsuario) REFERENCES usuario(id)");
////			int res = statement.executeUpdate("insert into person values(1, 'leo')");
////			res = statement.executeUpdate("insert into person values(2, 'yui')");
//		} catch(SQLException e) {
//			System.err.println(e.getMessage());
//			System.out.println("error en algo de sql");
//		} finally {
//			try {
//				if(connection != null)
//					connection.close();
//			} catch(SQLException e) {
//				// Cierre de conexión fallido
//				System.err.println(e);
//				System.out.println("error en cierre");
//			}
//		}
	}
}
