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

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.sqlite.SQLiteConfig;

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
		SQLiteConfig config = new SQLiteConfig();  
	    config.enforceForeignKeys(true);
		try {
				conn = DriverManager.getConnection(url, config.toProperties());
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

	public static void pokemonInsert(String nombre, int stats[], double altura, double peso, String tipo1,
			String tipo2) {
		if(conn != null
				&& (stats != null && stats.length == 6)) {
			try {
				String sql = "insert into pokemon values(?, ?, ?, ?, ? ?, ?, ?, ?, ? ,?)";
				PreparedStatement st = conn.prepareStatement(sql);
				st.setString(0, nombre);
				st.setInt(1, stats[0]); // Nombre
				st.setInt(2, stats[1]); // ps
				st.setInt(3, stats[2]); // 
				st.setInt(4, stats[3]);
				st.setInt(5, stats[4]);
				st.setInt(6, stats[5]);
				st.setDouble(7, altura);
				st.setDouble(8, peso);
				st.setString(9, tipo1);
				st.setString(10, tipo2);
				st.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	public static void leerFichero(String fichero_nombre) {
		try {
			FileInputStream fichero = new FileInputStream(new File(fichero_nombre + ".xlsx"));
			XSSFWorkbook workbook = new XSSFWorkbook(fichero);
			XSSFSheet hoja = workbook.getSheetAt(0);
			Iterator<Row> rowIterator = hoja.iterator();
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				Iterator<Cell> cellIterator = row.cellIterator();
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					switch (cell.getCellType()) {
					case NUMERIC:
						System.out.print(cell.getNumericCellValue() + " ");
						break;
					case STRING:
						System.out.print(cell.getStringCellValue() + " ");
						break;
					}
				}
				System.out.println("");
			}
			fichero.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws ClassNotFoundException {
		Class.forName("org.sqlite.JDBC");
		crearTabla();
		// se lee solo hay se hacer que se guarde en una lista y de hay a la base de
		// datos
		leerFichero("Data/web/" + "poke_info");

	}
}
