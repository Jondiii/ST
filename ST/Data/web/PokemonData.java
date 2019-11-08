package web;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Writer;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.hssf.usermodel.HSSFWorkbook; 
import org.apache.poi.ss.usermodel.Sheet; 
import org.apache.poi.ss.usermodel.Workbook; 



public class PokemonData {
	private static String url ="https://pokemon.fandom.com/es/wiki/";
	private static String [] pokemons = {"Abomasnow", "Aegislash", "Aggron", "Aurorus", "Bewear",
			"Blaziken", "Bronzong", "Chandelure", "Charizard", "Cloyster", "Dragonite", 
			"Eelektross", "Espeon", "Floatzel", "Flygon", "Froslass", "Gallade", "Galvantula", 
			"Garchomp", "Gardevoir", "Gengar", "Glaceon", "Gliscor", "Leafeon", "Jolteon", 
			"Lucario", "Lumineon", "Luxray", "Lycanroc", "Magnezone", 
			"Metagross", "Milotic", "Mimikyu", "Mismagius", "Porygon",  "Raichu_de_Alola", 
			"Reuniclus", "Salazzle", "Sceptile", "Scolipede", "Serperior", "Spiritomb", "Sylveon", "Togekiss", 
			"Typhlosion", "Tyranitar", "Umbreon", "Vaporeon", "Venusaur", "Wigglytuff", "Yanmega"
			
	};
	private static String [] moviminetos = {"Hoja_afilada", "Viento_hielo", "Canto_helado", 
			"Arraigo", "Mazazo", "Ventisca", "Hiperrayo", "Protección", "Avalancha", "Terremoto",
			"Sombra_vil", "Cabeza_de_hierro", "Espada_santa",	"Escudo_real", "Bola_sombra",	"Golpe_aéreo",
			"Gigaimpacto",	"Foco_resplandor",	"Danza_espada",	"Tajo_umbrío",	"Defensa_ferréa",
			"Doble_filo",	"Cuerpo_pesado",	"Puño_Trueno",	"Tormenta_de_arena", "Enfado",
			"Liofilización", "Onda_trueno",	"Descanso",	"Tóxico", "Trueno",	"Psíquico",	"Poder_pasado",
			"Roca_afilada", "Ojitos_tiernos", "Azote", "Vendetta",	"Machada", 	"Garra_dragón",
			"Garra_umbría", "Puño_fuego", "Evite_ígneo", "Patada_salto_alta", "Pájaro_osado",
			"Gancho_alto",	"Puya_nociva",	"Avivar",	"Danza_lluvia",	"Hipnosis",	"Rayo_confuso",
			"Espacio_raro", "Paz_mental",	"Llamarada", "Pulso_umbrío",	"Dia_soleado",	"Fuego_fatuo",
			"Vuelo", "Ataque_ala",	"Cuchillada",	"Golpe_cabeza",	"Púas_tóxicas",	"Hidrobomba",
			"Rayo_hielo",	"Surf",	"Carámbano",	"Rompecoraza", "Danza_dragón",	"Cometa_draco",
			"Lanzallamas", "Desarme",	"Rayo",	"Voltiocambio",	"Gigadrenado", "Premonición",	"Mofa",
			"Brillo_magico","Reflejo",	"Pantalla_luz", "Sol_matinal", "Roca_afilada",
			"Bola_sombra", "Psicocarga", "Puño_hielo", "Mismo_destino", "Sustituto", "Zumbido",
			"Ida_y_vuelta", "Energibola", "Granizo", "Hierba_lazo", "Deseo", "Onda_certera", "Rayo",
			"Drenadoras", "Triturar", "Danza_pétalo", "Demolicion", "Rayo_solar", "Acua_jet",
			"Hidrobomba", "Energibola", "Lanzallamas", "Bomba_lodo", "Maquinacion", "Golpe_aereo",
			"Recuperacion", "A_bocajarro", "Psico_corte", "Red_viscosa", "Colmillo_ígneo", "Hipnosis",
			"Come_sueños", "Escaldar", "Colmillo_rayo", "Trampa_venenosa", "Llueve_hojas", "Tajo_aéreo",
			"Fuerza_lunar", "Pulso_umbrio", "Respiro", "Acrobata",	"Tijera _x",	"Rayo_solar",
			"Doble_patada", "Golpe_cabeza",	"Velocidad_extrema",	"Ataque_óseo", "Demolición",
			"Viento_plata", "Rugido", "Conteneo", "Avalancha", "Corpulencia", "Tumba rocas",	
			"Supersonico",	"Giro_bola","Voltiocambio",	"Exploxión", "Cabezazo_zen",
			"Puño_meteoro", "Cabeza_de_hierro",	"Agilidad",	"Recuperación",	"Cola_dragon",	"Descanso",
			"Sonámbulo", "Sombra_vil", "Triataque",	"Electrocañón",	"Agilidad",	"Puño_mareo",
			"Lanzallamas", "Onda_tóxica", "Megacuerno",	"Golpe_bajo", "Beso_drenaje", "Esfera_aural",
			"Humareda",	"Estallido", "Juego_sucio", "Armadura_ácida", "Somnífero", "Ala de acero"
			};
	
	private static String [][] info_pokes = new String[55][9];
	private static URL webGetUrl(int indice_poke) {	
		try {
			return new URL(url + pokemons[indice_poke]);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return null;
	}
	private static void crearArchivo() throws IOException {
		Workbook pokemons = new HSSFWorkbook();
        Sheet hoja = pokemons.createSheet("poke_info");
		int rowCount = 0;
	    for (String[] pokemon : info_pokes) {
	    	Row row = hoja.createRow(++rowCount);
	        int columnCount = 0;
	        for (String datos : pokemon) {
	                Cell cell = row.createCell(++columnCount);
	                try{cell.setCellValue(Integer.valueOf(datos));
	                
	                }catch(Exception e) {
	                	cell.setCellValue(datos);
	                }
	        }
	    }
	    try (FileOutputStream outputStream = new FileOutputStream("poke_info.xlsx")) {
            pokemons.write(outputStream);
        }
	}
	private static void guardar_Data(StringBuilder sb, int indice) {
		info_pokes[indice][0] = pokemons[indice];
		String PS = String.valueOf(sb.toString().charAt(sb.toString().indexOf("Máximos (naturaleza neutra)")+ 38))
				 + String.valueOf(sb.toString().charAt(sb.toString().indexOf("Máximos (naturaleza neutra)")+ 39)) +
				 String.valueOf(sb.toString().charAt(sb.toString().indexOf("Máximos (naturaleza neutra)")+ 40));
		info_pokes[indice][1] = PS;
		String ataque = String.valueOf(sb.toString().charAt(sb.toString().indexOf("Máximos (naturaleza neutra)")+ 52))
				 + String.valueOf(sb.toString().charAt(sb.toString().indexOf("Máximos (naturaleza neutra)")+ 53)) +
				String.valueOf(sb.toString().charAt(sb.toString().indexOf("Máximos (naturaleza neutra)")+ 54));
		info_pokes[indice][2] = ataque;
		String defensa = String.valueOf(sb.toString().charAt(sb.toString().indexOf("Máximos (naturaleza neutra)")+ 66))
				 + String.valueOf(sb.toString().charAt(sb.toString().indexOf("Máximos (naturaleza neutra)")+ 67)) +
				String.valueOf(sb.toString().charAt(sb.toString().indexOf("Máximos (naturaleza neutra)")+ 68));
		info_pokes[indice][3] = defensa;
		String at_especial = String.valueOf(sb.toString().charAt(sb.toString().indexOf("Máximos (naturaleza neutra)")+ 80))
				 + String.valueOf(sb.toString().charAt(sb.toString().indexOf("Máximos (naturaleza neutra)")+ 81)) +
				String.valueOf(sb.toString().charAt(sb.toString().indexOf("Máximos (naturaleza neutra)")+ 82));
		info_pokes[indice][4] = at_especial;
		String def_espacial = String.valueOf(sb.toString().charAt(sb.toString().indexOf("Máximos (naturaleza neutra)")+ 94))
				 + String.valueOf(sb.toString().charAt(sb.toString().indexOf("Máximos (naturaleza neutra)")+ 95)) +
				String.valueOf(sb.toString().charAt(sb.toString().indexOf("Máximos (naturaleza neutra)")+ 96));
		info_pokes[indice][5] = def_espacial;
		String velocidad = String.valueOf(sb.toString().charAt(sb.toString().indexOf("Máximos (naturaleza neutra)") + 108))
				 + String.valueOf(sb.toString().charAt(sb.toString().indexOf("Máximos (naturaleza neutra)")+ 109)) +
				String.valueOf(sb.toString().charAt(sb.toString().indexOf("Máximos (naturaleza neutra)")+ 110));
		info_pokes[indice][6] = velocidad;
		String altura = String.valueOf(sb.toString().charAt(sb.toString().indexOf("Altura")+ 54))
				 + String.valueOf(sb.toString().charAt(sb.toString().indexOf("Altura")+ 55)) +
				String.valueOf(sb.toString().charAt(sb.toString().indexOf("Altura") + 56) +
				String.valueOf(sb.toString().charAt(sb.toString().indexOf("Altura") + 57)) +
				String.valueOf(sb.toString().charAt(sb.toString().indexOf("Altura") + 58)));
		info_pokes[indice][7] = altura;
		String peso = String.valueOf(sb.toString().charAt(sb.toString().indexOf("Peso")+ 52))
				 + String.valueOf(sb.toString().charAt(sb.toString().indexOf("Peso")+ 53)) +
				String.valueOf(sb.toString().charAt(sb.toString().indexOf("Peso") + 54) +
				String.valueOf(sb.toString().charAt(sb.toString().indexOf("Peso") + 55)) +
				String.valueOf(sb.toString().charAt(sb.toString().indexOf("Peso") + 56)));
		info_pokes[indice][8] = peso;
	}
	public static void main(String[] args) throws IOException {
		int indice = 0;
		for (String s : PokemonData.pokemons) {
			URLConnection con = webGetUrl(indice).openConnection();
			InputStream in =con.getInputStream();
			BufferedReader ine = new BufferedReader(new InputStreamReader(
                con.getInputStream(), "UTF-8"));
			String inputLine;
			StringBuilder a = new StringBuilder();
			while ((inputLine = ine.readLine()) != null)
				a.append(inputLine + "\n" );
			in.close();
			guardar_Data(a, indice);
			indice ++;
			}
			crearArchivo();

	}

}
