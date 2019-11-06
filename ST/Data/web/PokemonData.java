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
	private static String [][] info_pokes = new String[55][7];
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
