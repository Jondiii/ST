package ventanas;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import main.Main;
import principal.Combate;
import principal.HiloJuego;
import principal.Pokemon;

import java.awt.image.BufferedImage;

public class VentanaInicio extends JFrame {
	
	private VentanaInicio vi;
	public JLabel logo;
	static ArrayList<Pokemon>  miEquipo = new ArrayList<>();
	static ArrayList<Pokemon> oponente = new ArrayList<>();
	private Combate c;
	
	public VentanaInicio( Combate c) {
		vi = this;
		vi.setResizable(false);
		vi.setTitle("Pokemon Stars");
		vi.setLocation(700, 300);
		vi.setSize(500, 500);
		vi.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
		vi.crearPanelInferior();
		this.c = c;
	}
	
	private void crearPanelInferior(){
		JPanel panel = new JPanel(new BorderLayout());
		JButton jugar = new JButton("Jugar");
		JButton logIn = new JButton("Log in");
		JButton register = new JButton("Regisrarse");
		JButton salir = new JButton("Salir");
		JPanel pBotones = new JPanel();
		JPanel pLogo = new JPanel();
		
		ImageIcon icono_1 = new ImageIcon(getClass().getResource("/img/pokemonstars.png"));
		ImageIcon icono_2 = new ImageIcon(icono_1.getImage().getScaledInstance(500, 300, java.awt.Image.SCALE_DEFAULT));
		logo = new JLabel();
		logo.setIcon(icono_2);

		pLogo.add(logo);
		pBotones.add(jugar);
		pBotones.add(logIn);
		pBotones.add(register);
		pBotones.add(salir);
		panel.add(pLogo, BorderLayout.CENTER);
		panel.add(pBotones, BorderLayout.SOUTH);
		vi.add(panel);
		setVisible(true);
		
		jugar.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaJuego vj = new VentanaJuego(c);
				vj.addWindowListener( new WindowAdapter() {
					@Override
					public void windowClosing(WindowEvent e) {
						Main.cerrada = true;
					}
				});
				vj.setVisible(true);
				vi.dispose();
				HiloJuego mh = new HiloJuego(c, vj);
				mh.start();
			}
		});
		
		logIn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JDialog d = new JDialog();
				JPanel pUsuario = new JPanel(new FlowLayout());
				JTextField areaUsername = new JTextField(14);
				pUsuario.add(new JLabel("Usuario: "));
				pUsuario.add(areaUsername);
				JPanel pContraseña = new JPanel(new FlowLayout());
				JPasswordField areaContra = new JPasswordField(14);
				pContraseña.add(new JLabel("Contraseña: "));
				pContraseña.add(areaContra);
				JPanel panelOk = new JPanel();
				JButton bConfirmar = new JButton("OK");
				panelOk.add(bConfirmar);
				d.addWindowListener(new WindowAdapter() {
					@Override
					public void windowOpened(WindowEvent e) {
						Properties properties = new Properties();
						try {
							properties.loadFromXML(new FileInputStream("propiedades.xml"));
						} catch (InvalidPropertiesFormatException e1) {
							e1.printStackTrace();
						} catch (FileNotFoundException e1) {
							e1.printStackTrace();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						String user = properties.getProperty("Usuario");
						String pass = properties.getProperty("Contraseña");
						areaUsername.setText(user);
						areaContra.setText(pass);
						
						super.windowOpened(e);
					}
				});

				//Ane quiere ponerlo bonito y que esté todo en el medio. :)
				d.add(pUsuario, BorderLayout.NORTH);
				d.add(pContraseña, BorderLayout.CENTER);
				d.add(panelOk, BorderLayout.SOUTH);
				d.setSize(300, 150);
				d.setVisible(true);
				d.setLocation(vi.getLocation());
				d.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				

				
				bConfirmar.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						if (areaUsername.getText() == null || areaUsername.getText().trim() == "") return; //añadir jlabel que diga info de porque no
						String pass = new String (areaContra.getPassword());
						if (pass== null || pass.trim() == "") return; //añadir jlabel que diga info de porque no
						String user = areaUsername.getText();
						
						Properties properties = new Properties();
						properties.setProperty("Usuario", user);
						properties.setProperty("Contraseña", pass);
						
						try {
							properties.storeToXML(new FileOutputStream("propiedades.xml"), "Propiedades de usuario");
						} catch (FileNotFoundException e1) {
							e1.printStackTrace();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
				});
			}

			private void addWindowOpened(WindowAdapter windowAdapter) {
				// TODO Auto-generated method stub
				
			}
		});
		
		salir.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				vi.dispose();
			}
		});
	}
	
}
