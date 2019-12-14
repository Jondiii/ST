package principal;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelMovimiento extends JPanel{
	
	public PanelMovimiento(Movimiento mov) {
	//COSAS A AÑADIR:
	//Pequeña descripción del efecto del movimiento abajo.
	//Hacer que el color del panel cambie en función del tipo de movimiento????
		
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
		
		JLabel descripcion = new JLabel("Movimiento chungo que hace nosequé y blablablá");
		
		add(descripcion, BorderLayout.SOUTH);
		
		if(mov.getTipo() == Tipo.SINIESTRO  || mov.getTipo() == Tipo.ROCA || mov.getTipo() == Tipo.FANTASMA || mov.getTipo() == Tipo.AGUA) {
			setForeground(Color.white); //Cambia la letra a blanco
			descripcion.setForeground(Color.white);
		}
		
		setVisible(true);
		
	}
}
