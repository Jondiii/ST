package ventanas;

import javax.swing.JFrame;

public class VentanaSelectorEquipos extends JFrame {
	
	VentanaSelectorEquipos vs;

	public VentanaSelectorEquipos() {
		vs = this;
		
		setTitle("Selector de equipos");
		setLocation(200, 100);
		setSize(700, 500);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		setVisible(true);
	}
}
