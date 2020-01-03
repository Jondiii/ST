package web;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Writer;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.hssf.usermodel.HSSFWorkbook; 
import org.apache.poi.ss.usermodel.Sheet; 
import org.apache.poi.ss.usermodel.Workbook;
import org.ietf.jgss.Oid;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;

import principal.Movimiento;



public class PokemonData {
	private static String url ="https://pokemon.fandom.com/es/wiki/";
	private static String [] data = new String [200];
	private static String [] pokemons = {"Abomasnow", "Aegislash", "Aggron", "Aurorus", "Bewear",
			"Blaziken", "Bronzong", "Chandelure", "Charizard", "Cloyster", "Dragonite", 
			"Eelektross", "Espeon", "Floatzel", "Flygon", "Froslass", "Gallade", "Galvantula", 
			"Garchomp", "Gardevoir", "Gengar", "Glaceon", "Gliscor", "Leafeon", "Jolteon", 
			"Lucario", "Lumineon", "Luxray", "Lycanroc", "Magnezone", 
			"Metagross", "Milotic", "Mimikyu", "Mismagius", "Porygon",  "Raichu_de_Alola", 
			"Reuniclus", "Salazzle", "Sceptile", "Scolipede", "Serperior", "Spiritomb", "Sylveon", "Togekiss", 
			"Typhlosion", "Tyranitar", "Umbreon", "Vaporeon", "Venusaur", "Wigglytuff", "Yanmega"
			
	};
	private static String [] movimientos = {"Hoja_afilada", "Viento_hielo", "Canto_helado", 
			"Arraigo", "Mazazo", "Ventisca", "Hiperrayo", "Protección", "Avalancha", "Terremoto",
			"Sombra_vil", "Cabeza_de_hierro", "Espada_santa",	"Escudo_real", "Bola_sombra",	"Golpe_aéreo",
			"Giga_impacto",	"Foco_resplandor",	"Danza_espada",	"Tajo_umbrío",	"Defensa_férrea",
			"Doble_filo",	"Cuerpo_pesado",	"Puño_Trueno",	"Tormenta_de_arena", "Enfado",
			"Liofilización", "Onda_trueno",	"Descanso",	"Tóxico", "Trueno",	"Psíquico",
			"Roca_afilada", "Ojitos_tiernos", "Azote", "Vendetta",	"Machada", 	"Garra_dragón",
			"Garra_umbría", "Puño_fuego", "Envite_ígneo", "Patada_salto_alta", "Pájaro_osado",
			"Gancho_alto",	"Puya_nociva",	"Avivar",	"Danza_lluvia",	"Hipnosis",	"Rayo_confuso",
			"Espacio_raro", "Paz_mental",	"Llamarada", "Pulso_umbrío",	"Dia_soleado",	"Fuego_fatuo",
			"Vuelo", "Ataque_ala",	"Cuchillada",	"Golpe_cabeza",	"Púas_tóxicas",	"Hidrobomba",
			"Rayo_hielo",	"Surf",	"Carámbano",	"Rompecoraza", "Danza_dragón",	"Cometa_draco",
			"Lanzallamas", "Desarme",	"Rayo",	"Voltiocambio",	"Gigadrenado", "Premonición",	"Mofa",
			"Brillo_mágico","Reflejo",	"Pantalla_luz", "Sol_matinal", "Roca_afilada",
			"Bola_sombra", "Psicocarga", "Puño_hielo", "Mismo_destino", "Sustituto", "Zumbido",
			"Ida_y_vuelta", "Energibola", "Granizo", "Hierba_lazo", "Deseo", "Onda_certera", "Rayo",
			"Drenadoras", "Triturar", "Danza_pétalo", "Demolicion", "Rayo_solar", "Acua_jet",
			"Hidrobomba", "Energibola", "Lanzallamas", "Bomba_lodo", "Maquinacion", "Golpe_aereo",
			"Recuperacion", "A_bocajarro", "Psico_corte", "Red_viscosa", "Colmillo_ígneo", "Hipnosis",
			"Come_sueños", "Escaldar", "Colmillo_rayo", "Trampa_venenosa", "Llueve_hojas", "Tajo_aéreo",
			"Fuerza_lunar", "Pulso_umbrio", "Respiro", "Acróbata",	"Tijera_X",	"Rayo_solar",
			"Doble_patada", "Golpe_cabeza",	"Velocidad_extrema",	"Ataque_óseo", "Demolición",
			"Viento_plata", "Rugido", "Contoneo", "Avalancha", "Corpulencia", "Tumba_rocas",	
			"Supersonico",	"Giro_bola","Voltiocambio",	"Explosión", "Cabezazo_zen",
			"Puño_meteoro", "Cabeza_de_hierro",	"Agilidad",	"Recuperación",	"Cola_dragon",	"Descanso",
			"Sonámbulo", "Sombra_vil", "Triataque",	"Electrocañón",	"Agilidad",	"Puño_mareo",
			"Lanzallamas", "Onda_tóxica", "Megacuerno",	"Golpe_bajo", "Beso_drenaje", "Esfera_aural",
			"Humareda",	"Estallido", "Juego_sucio", "Armadura_ácida", "Somnífero", "Ala_de_acero"
			};
	

	private static String [] mov = {"potencia","precisión", "prioridad", "pp", "blanco", "secundario" };
	private static String [][] info_pokes = new String[55][9];
	private static String [][] info_movimientos = new String[200][10];
	private static URL webGetUrlPokemons(int indice_poke) {	
		try {
			return new URL(url + pokemons[indice_poke]);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return null;
	}
	private static URL webGetUrlMovi(int indice_movi) {	
		try {
			return new URL(url + movimientos[indice_movi]);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return null;
	}
	private static void crearArchivo(String nombre, String [][] lista) throws IOException {
		Workbook pokemons = new HSSFWorkbook();
		Sheet hoja = pokemons.createSheet(nombre);
		int rowCount = 0;
	    for (String[] l : lista) {
	    	Row row = hoja.createRow(++rowCount);
	        int columnCount = 0;
	        for (String datos : l) {
	                Cell cell = row.createCell(++columnCount);
	                try{cell.setCellValue(Integer.valueOf(datos));
	                
	                }catch(Exception e) {
	                	cell.setCellValue(datos);
	                }
	        }
	    }
	    try (FileOutputStream outputStream = new FileOutputStream(nombre +".xlsx")) {
            pokemons.write(outputStream);
        }
	}
	public static void guardar_datos(String info_poke, int indice_1, int indice_2, String[][] lista) {
		lista[indice_1][indice_2] = String.valueOf(info_poke);
	}
	public static void getInfo_poke() throws IOException {
		int indice = 0; 
		for (String s : pokemons) {	
			guardar_datos(pokemons[indice], indice, 0, info_pokes);
			URL con = webGetUrlPokemons(indice);
			Document doc = Jsoup.parse(con, 50000);
			Elements rows = doc.select("table.estadisticas");
			Element cols = rows.select("tr").get(4);
			Elements stats = cols.select("td"); 
			int indice_2 = 1;
			for (Element e : stats) {
				guardar_datos(e.text(), indice, indice_2, info_pokes);
				if(indice_2 == 5) {
					indice_2 = 1;
				}else {
				indice_2++;
				}
			}
			indice++;
		}
		crearArchivo("poke_info", info_pokes);
	}
	private static void getInfo_movi() throws IOException {
		int indice = 0; 
		for (String s_1 : movimientos) {
			guardar_datos(movimientos[indice], indice, 0, info_movimientos);
			URL con = webGetUrlMovi(indice);
			Document doc = Jsoup.parse(con, 50000);
			Elements rows = doc.select("div[data-source]");
			int indice_2 = 1;
			for (Element e : rows) {
				for (String s : mov) {
					if (e.attr("data-source").toString().equals(s)){
						Element e_1 = e.select("div").get(1);
						guardar_datos(e_1.ownText(), indice, indice_2, info_movimientos);
					}
					if (e.attr("data-source").equals("categoría") || e.attr("data-source").equals("tipo")){
						Elements tipo = e.select("div");
						Element e_6 = tipo.select("a[title]").get(1);
						guardar_datos(e_6.attr("title"), indice, indice_2, info_movimientos);
					}
				}
				if(indice_2 == 8) {
					indice_2 = 1;
				}else {
				indice_2++;
				}
			}
			indice++;
		}
		crearArchivo("mov_info", info_movimientos);
	}
	private static void getInfo_movs_caracteristicas() throws IOException{
		int indice = 0; 
		boolean concursos = true;
		for (String s_1 : movimientos) {
			URL con = webGetUrlMovi(indice);
			Document doc = Jsoup.parse(con, 50000);
					Elements e_2 = doc.select("dl");
					Elements e_3 = e_2.select("dt");
					if (e_3.eachText().get(0).equals("Gran concurso") || e_3.eachText().get(0).equals("Concurso") || e_3.eachText().get(0).equals("Súper concurso")) {
						Element e_1 = doc.select("p").get(1);
						System.out.println(s_1 + "," + e_1.text());
						guardarLista(s_1 + "," + e_1.text(), indice);
					} else {
							Elements e_4 = e_2.select("dd");
							guardarLista(s_1 + "," + e_4.text(), indice);
							System.out.println(s_1 + "," + e_4.text());
				
					}
			indice ++;
		}
		
	}
	private static void guardarLista(String string, int i) {
		data[i] = string; 
	}
	private static void escribir_fichero() {
		 try {
			 	
	        	FileWriter escritor = new FileWriter(new File("Data/web/caracteristica_mov.txt"));
	        	BufferedWriter buferescr = new BufferedWriter(escritor);
	        	
	        	for (String s : data) {
	        		buferescr.write(s);
	        		buferescr.newLine();
	        	}
	        	buferescr.close();
	        	escritor.close();

	        } catch (Exception e) {
	            e.printStackTrace();
	        } 
	        }
	
	private static void leer_fichero() {
		try {
			BufferedReader br = new BufferedReader(new FileReader("Data/web/caracteristica_mov.txt"));
			
			String st; 
			  try {
				while ((st = br.readLine()) != null) {
					String[] partes = st.split( "\\." );
					String stringFinal = partes[0];
					stringFinal = stringFinal + ".";	
						
				    System.out.println(stringFinal);
				    }
			} catch (IOException e) {
				e.printStackTrace();
			} 
			  try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	
	
	public static void main(String[] args) throws IOException {
		//getInfo_poke();
		//getInfo_movi();
		//getInfo_movs_caracteristicas();
		//escribir_fichero();
		//leer_fichero();

	}

}
