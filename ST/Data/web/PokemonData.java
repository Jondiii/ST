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
			"Typhlosion", "Tyranitar", "Umbreon", "Vaporeon", "Venasaur", "Wigglytuff", "Yanmega"
			
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
			"Lanzallamas", "Desarme",	"Rayo",	"Voltiocambio",	"Gigadrenado",
			BRILLO MAGICO	REFLEJO	HIPERRAYO	PANTALLA LUZ	SOL MATINAL
			HIDROBOMBA	AQUA JET	TRITURAR	TÓXICO	SURF	RAYO HIELO	PUÑO HIELO	HIPERRAYO	DANZA LLUVIA	MOFA
			GARRA DRAGÓN 	TERREMOTO	TRITURAR	TÓXICO	LLANZALLAMAS	COMETA DRACO	VUELO	HIPERRAYO	DESCANSO	ROCA AFILADA
			RAYO HIELO	VENTISCA	MISMO DESTINO	RAYO CONFUSO	BOLA SOMBRA	ONDA TRUENO	FUEGO FATUO	TÓXICO	PROTECCIÓN	MOFA
			A BOCAJARRO	CUCHILLADA	TAJO UMBRIO	PSICO CORTE	PAZ MENTAL	PROTECCIÓN	PSIQUICO	BRILLO MAGICO	DEMOLICIÓN	AVALANCHA
			TRUENO	ONDA TRUENO	RAYO	ZUMBIDO	RED VISCOSA	PROTECCIÓN	TÓXICO	ENERGIBOLA	VOLTIOCAMBIO	PUYA NOCIVA
			CARRA DRAGÓN	TERREMOTO	TRITURAR	COLMILLO IGNEO	AVALANCHA	COMETA DRACO	TÓXICO	DANZA ESPADA	ROCA AFILADA	GIGAIMPACTO
			FUERZA LUNAR	PAZ MENTAL	PSÍQUICO	HIPNOSIS	DESEO	PROTECCIÓN	TÓXICO	BOLA SOMBRA	COME SUEÑOS	PREMONICIÓN
			BOLA SOMBRA	RAYO CONFUSO	PULSO UMBRIO	MISMO DESTINO	HIPNOSIS	PROTECCIÓN	PUYA NOCIVA	BRILLO MAGICO	BOMBA LODO	TÓXICO
			RAYO HIELO	GRANIZO	OJITOS TIERNOS	VENTISCA	MORDISCO	SUSTITUTO	PROTECCIÓN	BOLA SOMBRA	TÓXICO	GIGAIMPACTO
			GOLPE AEREO 	RESPIRO	PULSO UMBRIO	DANZA ESPADA	BOMBA LODO	PROTECCIÓN	TERREMOTO	ROCA AFILADA	ÁCROBATA	TÓXICO
			GIGADRENADO	DANZA ESPADA	SINTESIS	TÓXICO	BOLA SOMBRA	PROTECCIÓN	TIJERA X	RAYO SOLAR	ENERGIBOLA	HIPERRAYO
			TRUENO	ONDA TRUENO	PROTECCIÓN	TÓXICO	BOLA SOMBRA	DOBLE PATADA	COLMILLO RAYO	GOLPE CABEZA	VOLTIOCAMBIO	GIGAIMPACTO
			A BOCAJARRO	DANZA ESPADA	VELOCIDAD EXTREMA	ATAQUE OSEO	TÓXICO	PROTECCIÓN	BOLA SOMBRA	ROCA AFILADA	TERREMOTO	DEMOLICIÓN
			SURF	DANZA LLUVIA	VIENTO PLATA	RAYO HIELO	ESCALDAR	IDA Y VUELTA	HIPERRAYO	PROTECCIÓN	TÓXICO	GIGAIMPACTO
			TRUENO	COLMILLO RAYO	TRITURAR	ONDA TRUENO	TÓXICO	PROTECCIÓN	RUGIDO	CONTENEO	VOLTIOCAMBIO	GIGAIMPACTO
			ROCA AFILADA	TRITURAR 	AVALANCHA	CORPULENCIA	DEMOLICIÓN	TÓXICO	PROTECCIÓN	TUMBA ROCAS	DANZA ESPADA	TREPARROCAS
			TRUENO	FOCO RESPLANDOR	ONDA TRUENO	SUPERSONICO	GIRO BOLA	TÓXICO	PROTECCIÓN	GIGAIMPACTO	VOLTIOCAMBIO	EXPLOXIÓN
			HIERBA LAZO	MACHADA	CABEZAZO ZEN	PUÑO METEORO	TERREMOTO	AVALANCHA	CABEZA DE HIERRO	GIRO BOLA	AGILIDAD	TÓXICO
			SURF	RAYO HIELO	DANZA LLUVIA	RECUPERACIÓN	COLA DRAGON	VENTISCA	TÓXICO	DESCANSO	ESCALDAR	SONÁMBULO
			FUERZA LUNAR	GARRA UMBRIA	CUCHILLADA	SOMBRA VIL	BOLA SOMBRA	TIJERA X	PSIQUICO	PROTECCIÓN	BRILLO MÁGICO	TÓXICO
			PAZ MENTAL	PSÍQUICO	BRILLO MÁGICO	PULSO UMBRIO	ONDA TRUENO	BOLA SOMBRA	RAYO	ENERGIBOLA	TÓXICO	MAQUINACIÓN
			TRIATAQUE	ELECTROCAÑÓN	RECUPERACIÓN	DOBLE RAYO	ONDA TRUENO	PSICOCARGA	AGILIDAD	GOLPE AEREO	TÓXICO	GIGAIMPACTO
			PSIQUICO	RAYO	PAZ MENTAL	PROTECCIÓN	DEMOLICIÓN	PSICOCARGA	TRUENO	TÓXICO	VOLTIOCAMBIO	ONDA CERTERA
			PUÑO MAREO	PAZ MENTAL	BOLA SOMBRA	ENERGIBOLA	ESPACIO RARO	PSICOCARGA	PROTECCIÓN	PSIQUICO	GIRO BOLA	ONDA CERTERA
			LLAMARADA	BOMBA LODO	TÓXICO	TAMPA VENENOSA	PUYA NOCIVA	LANZALLAMAS	PROTECCIÓN	MOFA	ONDA TÓXICA	MAQUINACIÓN
			GIGADRENADO	TIJERA X	SINTESIS	LLUEVE HOJAS	TAJO UMBRIO	GARRA DRAGÓN	TERREMOTO	TUMBA ROCAS	TÓXICO	PROTECCIÓN
			MEGACUERNO	BOMBA LODO	PUYA NOCIVA	TIJERA X	TRAMPA VENENOSA	TÓXICO	GIRO BOLA	TERREMOTO	PROTECCIÓN	GIGAIMPACTO
			GIGADRENADO	PAZ MENTAL	ENERGIBOLA	DRENADORAS	COLA DRAGON	TÓXICO	GIRO VIL	PROTECCIÓN	RAYO SOLAR	GIGAIMPACTO
			BOLA SOMBRA	RAYO CONFUSO	SOMBRA VIL	GOLPE BAJO	PULSO UMBRIO	PAZ MENTAL	TÓXICO	PSÍQUICO	TUMBA ROCAS	PROTECCIÓN
			FUERZA LUNAR	BESO DRENAJE	PAZ MENTAL	BOLA SOMBRA	BRILLO MÁGICO	PSICOCARGA	TÓXICO	BOLA SOMBRA	PROTECCIÓN	DESEO
			TAJO AEREO	ESFERA AURAL	PSÍQUICO	BOLA SOMBRA	BRILLO MÁGICO	PSICOCARGA	VUELO	ONDA TRUENO	VELOCIDAD EXTREMA	GIGAIMPACTO
			LLAMARADA	TERREMOTO	HUMAREDA	ONDA CERTERA	TUMBA ROCAS	DEMOLICIÓN	PROTECCIÓN 	FUEGO FATUO	DESCANSO	ESTALLIDO
			TRITURAR	TERREMOTO	PULSO UMBRIO	ROCA AFILADA	GARRA DRAGÓN	PROTECCIÓN	DEMOLICIÓN	TUMBA ROCAS	TORMENTA DE ARENA (?)	GIGAIMPACTO
			PULSO UMBRIO	RAYO CONFUSO	DESCANSO	TÓXICO	PROTECCIÓN	BOLA SOMBRA	HIPERRAYO	PSIQUICO	JUEGO SUCIO	DESEO
			HIDROBOMBA	RAYO HIELO	VENTISCA	ESCALDAR	DANZA LLUVIA	ARMADURA ÁCIDA	DESEO	PROTECCIÓN	TÓXICO	DESCANSO
			GIGADRENADO	SOMNÍFERO	SINTESIS	ENERGIBOLA	TERREMOTO	TÓXICO	BOMBA LODO	DANZA PÉTALO	DRENADORAS	GIGAIMPACTO
			CARANTOÑA	DOBLE FILO	ONDA TRUENO	BOLA SOMBRA	RAYO HIELO	BRILLO MAGICO	PSÍQUICO	DESCANSO	PROTECCIÓN	TÓXICO
			TAJO AEREO	IDA Y VUELTA	CUCHILLADA	SUPERSONICO	TAJO UMBRIO	PSÍQUICO	ALA DE ACERO	TÓXICO	PROTECCIÓN	GIGAIMPACTO
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
