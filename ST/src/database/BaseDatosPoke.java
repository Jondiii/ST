package database;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.sqlite.SQLiteConfig;

/**
 * Clase que crea la Base de Datos. En principio solo la usaremos una única vez, una vez creada pensamos editar la
 * base de datos con programas externos.
 * 
 * FALTA: Claves primarias y claves externas. Relaciones faltan también. 
 */
public class BaseDatosPoke {
	
	public static String url = "jdbc:sqlite:src/database/PokemonStars.db";
	
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
                + "    mov1 integer , \n"
                + "    mov2 integer , \n"
                + "    mov3 integer ,\n "
                + "    mov4 integer ,\n"
                + "    mov5 integer ,\n"
                + "    mov6 integer , \n"
                + "    mov7 integer ,\n"
                + "    mov8 integer , \n"
                + "    mov9 integer , \n"
                + "    mov10 integer , \n"
                + "    tipo1 text ,\n"
                + "    tipo2 text, \n"
                + "FOREIGN KEY (mov1) REFERENCES movimiento(id),"
    			+ "FOREIGN KEY (mov2) REFERENCES movimiento(id),"
    			+ "FOREIGN KEY (mov3) REFERENCES movimiento(id),"
    			+ "FOREIGN KEY (mov4) REFERENCES movimiento(id),"
    			+ "FOREIGN KEY (mov5) REFERENCES movimiento(id),"
    			+ "FOREIGN KEY (mov6) REFERENCES movimiento(id), "
    			+ "FOREIGN KEY (mov7) REFERENCES movimiento(id),"
    			+ "FOREIGN KEY (mov8) REFERENCES movimiento(id), "
    			+ "FOREIGN KEY (mov9) REFERENCES movimiento(id),"
    			+ "FOREIGN KEY (mov10) REFERENCES movimiento(id) "
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
				+ "idUsuario integer,\n"
//				+ "PRIMARY KEY(id),\n"
				+ "FOREIGN KEY (idUsuario) REFERENCES usuario(id) "
				+ ");";
		SQLiteConfig config = new SQLiteConfig();  
	    config.enforceForeignKeys(true);
		try (Connection conn = DriverManager.getConnection(url, config.toProperties())) {
			 Statement stmt = conn.createStatement(); 
			 //crea nuevas tablas
		            stmt.execute(sql_1);
		            stmt.execute(sql);
		            stmt.execute(sql_2);
		            stmt.execute(sql_3);
		}catch(SQLException e){
			
		}
	}
	
	
	
	public static void leerFichero(String fichero_nombre) {
		try {
			FileInputStream fichero = new FileInputStream(new File(fichero_nombre +".xlsx"));
			XSSFWorkbook workbook = new XSSFWorkbook(fichero);
			XSSFSheet hoja = workbook.getSheetAt(0);
			Iterator<Row> rowIterator = hoja.iterator();
            while (rowIterator.hasNext()){
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                while (cellIterator.hasNext()){
                    Cell cell = cellIterator.next();
                    switch (cell.getCellType()){
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
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }		
	}
	public static void main(String[] args) throws ClassNotFoundException {
		Class.forName("org.sqlite.JDBC");
		crearTabla();
		//se lee solo hay se hacer que se guarde en una lista y de hay a la base de datos
		leerFichero("Data/web/" + "poke_info");

	}
}
