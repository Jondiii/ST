package principal;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.TextArea;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelMovimiento extends JPanel{
	
	Movimiento mov;
	
	public PanelMovimiento(Movimiento movimiento) {
		this.mov = movimiento;
	//COSAS A AÑADIR:
	//Que el tamaño de la ventana se ajuste a la descripción o al revés.
		
		anadirDatos();
		
		setVisible(true);
	}
	
	public void anadirDatos() {
		if (mov.getNombre().isEmpty()) return;
		removeAll();
		setBackground(mov.getTipo().getColor());
		setLayout(new BorderLayout());
		
		JPanel pDatos = new JPanel();
		pDatos.setLayout(new GridLayout(2, 3));
		pDatos.add(new JLabel(mov.getTipo().toString()));
		pDatos.add(new JLabel(mov.getNombre()));
		pDatos.add(new JLabel(mov.getCat().toString()));
		pDatos.add(new JLabel("Potencia: "  + mov.getPotencia()));
		pDatos.add(new JLabel("Precisión: " + mov.getPrecision()));
		pDatos.add(new JLabel("PP: " + mov.getPp()));
		add(pDatos, BorderLayout.NORTH);
		
		JLabel descripcion = new JLabel(leeDescripcion(mov.getNombre()));

		add(descripcion, BorderLayout.SOUTH);
		
		if(mov.getTipo() == Tipo.SINIESTRO  || mov.getTipo() == Tipo.ROCA || mov.getTipo() == Tipo.FANTASMA || mov.getTipo() == Tipo.AGUA) {
			setForeground(Color.white); //Cambia la letra a blanco
			descripcion.setForeground(Color.white);
		}
		
		revalidate();
		
	}
	public String leeDescripcion(String nombre) {
		String linea;
		
		try {
			 File file = new File("Data\\web\\caracteristica_mov.txt"); 
			 BufferedReader br = new BufferedReader(new FileReader(file));
			 
			 while((linea = br.readLine()) != null ) {
				 String[] array = linea.split(",", 2);
				 if (array[0].replace("_", " ").contentEquals(nombre)) return array[1];
			 }
			 
			 return "      ";
			 
		} catch (Exception e) {
			return "blablablá";
		}
		
	}

	public Movimiento getMov() {
		return mov;
	}

	public void setMov(Movimiento mov) {
		this.mov = mov;
		anadirDatos();
	}
}
