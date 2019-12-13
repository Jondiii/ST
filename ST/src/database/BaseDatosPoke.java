package database;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;


import org.sqlite.SQLiteConfig;

import main.Main;

/**
 * Clase que crea la Base de Datos. En principio solo la usaremos una única vez,
 * una vez creada pensamos editar la base de datos con programas externos.
 * 
 * FALTA: Claves primarias y claves externas. Relaciones faltan también.
 */
public class BaseDatosPoke {

	public static String url = "jdbc:sqlite:src/database/PokemonStars.db";
	private static Connection conn;

	public static void crearTabla() {
		//TODO: Los id de las tablas hacer auto increment
		String sqlPokemon = "CREATE TABLE IF NOT EXISTS pokemons (\n"
                + "    idPokemon integer NOT NULL PRIMARY KEY AUTOINCREMENT,\n"
                + "    name text ,\n"
                + "    ps integer,\n"
                + "    ataque  integer,\n"
                + "    defensa integer,\n"
                + "    ataqueEspecial integer,\n"
                + "	   defensaEspecial integer,\n"
                + "    velocidad integer,\n"
                + "    altura integer,\n"
                + "    peso integer,\n"
                + "    tipo1 text ,\n"
                + "    tipo2 text"
                + ");";
		
		String sqlMovimientos = "CREATE TABLE IF NOT EXISTS movimientos (\n"
				+ "idMovimiento integer NOT NULL PRIMARY KEY AUTOINCREMENT,\n"
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
				+ ");";
		String sqlPokemonMovimientos = "CREATE TABLE IF NOT EXISTS pokemon_movimiento ("
				+ "idPokemonMovimiento integer NOT NULL PRIMARY KEY AUTOINCREMENT,"
				+ "FOREIGN KEY idPokemon REFERENCES pokemons(idPokemon)"
				+ "FOREIGN KEY idMovimiento REFERENCES movimientos(idMovimiento)"
				+ ");";
		String sqlUsuarios = "CREATE TABLE IF NOT EXISTS usuario (\n"
				+ "idUsuario integer NOT NULL PRIMARY KEY AUTOINCREMENT,\n"
				+ " nombre text ,\n"
				+ " contraseña text ,\n"
				+ "entrenador text \n"
				+ ");";
		String sqlEquipos = "CREATE TABLE IF NOT EXISTS equipo (\n"
				+ "idEquipo integer NOT NULL PRIMARY KEY AUTOINCREMENT,\n"
				+ "nombre text ,\n"
				+ "idUsuario integer,\n"
				+ "FOREIGN KEY (idUsuario) REFERENCES usuario(id) "
				+ ");";
		
		try {
	
			 Statement stmt = conn.createStatement(); 
			 //crea nuevas tablas
		            stmt.execute(sqlPokemon);
		            stmt.execute(sqlMovimientos);
		            stmt.execute(sqlPokemonMovimientos);
		            stmt.execute(sqlUsuarios);
		            stmt.execute(sqlEquipos);
		}catch(SQLException e){
			
		}
	}

	public static void pokemonInsert(int id, String nombre, int stats[], double altura, double peso, String ataques[], String tipos[]) {
		if(conn != null
				&& (stats != null)) {
			try {
				
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery("select id from movimientos where name in ('"+ataques[0]+"','"+ataques[1]+"', '"+ataques[2]+"', '"+ataques[3]+"', '"+ataques[4]+"'"
						+ ", '"+ataques[5]+"', '"+ataques[6]+"', '"+ataques[7]+"', '"+ataques[8]+"', '"+ataques[9]+ "')");
				int id_mow[] = new int[10];
				int i = 0;
				while (rs.next()) {
					id_mow[i] = rs.getInt("id");
					i++;
					if (i == 10)
						break;
				}
				String sql = "insert into pokemons values("+id+",'"+nombre+"',"+stats[0]+", "+stats[1]+", "
						+ ""+stats[2]+", "+stats[3]+", "+stats[4]+", "+stats[5]+", "+altura+", "+peso+", "
						+ ""+id_mow[0]+", "+ id_mow[1]+", "+id_mow[2]+", " +id_mow[3]+", " +id_mow[4]+", "
						+ id_mow[5]+", "+ id_mow[6]+", " + id_mow[7]+", " + id_mow[8]+", " +id_mow[9]+", "
						+ "'"+tipos[0]+"', '"+tipos[1]+"')";
				
				st.executeUpdate(sql);
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}
	public static void movInsert(int id, String nombre, String alcance, int prioridad, int dano, int precision, int pp,
			String categoria, String mov_tipo, String estado_alterno, String estado_secunf, double[] probabilidades,
			String cambioStat, int[] stats) {
			if(conn != null
					) {
				try {
					Statement st = conn.createStatement();
					String sql = "insert into movimientos values("+id+",'"+nombre+"','"+mov_tipo+"',"
							+ ""+dano+","+precision+","+pp+","+prioridad+",'"+estado_alterno+"','"+categoria+"',"
							+ "'"+alcance+"', "+probabilidades[0]+", "+probabilidades[1]+","
							+ ""+probabilidades[2]+",'"+estado_secunf+"','"+cambioStat+"',"+stats[0]+","
							+ ""+stats[1]+", "+stats[2]+", "+stats[3]+","+stats[4]+")";
					
					
					st.executeUpdate(sql);
					
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) throws ClassNotFoundException {
		Class.forName("org.sqlite.JDBC");
		//crearTabla();
		try {
			conn = DriverManager.getConnection(url);
			Statement st = conn.createStatement();
			st.executeUpdate("delete from movimientos");
			st.executeUpdate("delete from pokemons");
			Main.basesDatosCargarMov();
			Main.basesDatosCargarPoke();
			System.out.println("Cargada/actualizada la base de datos");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		//Main.basesDatosCargarPoke();
	}



		
	
}
