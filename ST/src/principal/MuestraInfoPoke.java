package principal;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MuestraInfoPoke extends JDialog {

	private Pokemon poke;
	private JPanel pPrincipal = new JPanel(new BorderLayout());
	
	public MuestraInfoPoke(Pokeball p) {		//He hecho que se pase una pokeball por si queremos hacer que no se muestren los datos en caso de que el pokemon no haya salido nunca.
		poke = p.getPoke();
		this.add(pPrincipal);
		
		JPanel pSuperior = new JPanel(new BorderLayout()); //Panel de arriba del todo
		JPanel pNombre = new JPanel();						//Parte superior del panel de arriba
		JPanel pTipos = new JPanel();						//Parte inferior del panel de arriba
		
		//Se añaden el nombre y el tipo del pokémon
		pNombre.add(new JLabel(poke.getNombre(), JLabel.CENTER));
		pTipos.add(new JLabel(poke.getTipos().toString(), JLabel.CENTER));
		pSuperior.add(pNombre, BorderLayout.NORTH);
		pSuperior.add(pTipos, BorderLayout.SOUTH);
		
		//Se añaden los stats del pokémon
		JPanel pStats = new JPanel(new GridLayout(4, 2));
		
		pStats.add(new JLabel("PS: " + poke.getPs() + "/" + poke.getPs_max(), JLabel.CENTER));
		pStats.add(new JLabel("Atq.: " + poke.getAtaque(), JLabel.CENTER));
		pStats.add(new JLabel("Def.: " + poke.getDefensa(), JLabel.CENTER));
		pStats.add(new JLabel("Atq. Esp.: " + poke.getAtaque_especial(), JLabel.CENTER));
		pStats.add(new JLabel("Def. Esp." + poke.getDefensa_especial(), JLabel.CENTER));
		pStats.add(new JLabel("Ataque: " + poke.getVelocidad(), JLabel.CENTER));
		pStats.add(new JLabel(""));
		
		pPrincipal.add(pSuperior, BorderLayout.NORTH);
		pPrincipal.add(pStats, BorderLayout.CENTER);
		
		if(poke.getEstado() != null) pStats.add(new JLabel(poke.getEstado().toString(), JLabel.CENTER)); //Muestra el estado si no es null.
		
		pSuperior.add(pNombre, BorderLayout.NORTH);
		pSuperior.add(pTipos, BorderLayout.SOUTH);

	}
	
}
