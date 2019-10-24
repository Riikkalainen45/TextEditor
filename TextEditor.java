import java.awt.BorderLayout;



import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Window;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.text.StyledEditorKit;
import javax.swing.text.StyledEditorKit.BoldAction;

import com.sun.javafx.css.Style;
import com.sun.org.apache.xerces.internal.util.URI;

import javafx.scene.text.Font;

import javax.swing.JMenuBar;
import javax.swing.JToolBar;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.awt.event.InputEvent;
import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class TextEditor extends JFrame {

	protected static final java.awt.Font ITALIC = null;
	private JPanel contentPane;
	private JEditorPane editorPane;
	ImageIcon icon;
	JFrame InfoWindow ;

	//tekstieditorin käynnistys
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TextEditor frame = new TextEditor();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// luodaan frame
	
	public TextEditor() {
		setTitle(" FunEditor ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		icon = new ImageIcon("src/image/faviconSmily.png");
		setIconImage(icon.getImage());
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnTiedost = new JMenu("Tiedosto");
		menuBar.add(mnTiedost);
	
		
//Avaa scennerin ja on valmiina tallentamaan tietoa
		
		JMenuItem mntmAvaa = new JMenuItem("Avaa");
		mntmAvaa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				avaaTiedosto();
			}
		});
		mntmAvaa.setIcon(new ImageIcon(TextEditor.class.getResource("/com/sun/java/swing/plaf/windows/icons/TreeOpen.gif")));
		mntmAvaa.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
		mnTiedost.add(mntmAvaa);
	
//Tallentaa tekstin EditorSave.txt tiedostoon
		
		JMenuItem mntmTallenna = new JMenuItem("Tallenna");
		mntmTallenna.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			tallennaTiedosto();
				
			}
		});
		mntmTallenna.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		mnTiedost.add(mntmTallenna);

// poistaa tekstin editor panelista 
		
		JMenuItem mntmLopeta = new JMenuItem("Tyhjenn\u00E4");
		mntmLopeta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			 editorPane.setText("");
			}
		});
		mnTiedost.add(mntmLopeta);

//Sulkee panelin
		JMenuItem mntmSulje = new JMenuItem("Sulje");
		mntmSulje.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		JMenuItem mntmUusiKansio = new JMenuItem("Uusi Kansio");
		mntmUusiKansio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				JFileChooser valintaikkuna = new JFileChooser();
				valintaikkuna.showSaveDialog(null);
				
				 uusiTiedosto();
			}
		});
		mntmUusiKansio.setIcon(new ImageIcon(TextEditor.class.getResource("/javax/swing/plaf/metal/icons/ocean/floppy.gif")));
		mnTiedost.add(mntmUusiKansio);
		mnTiedost.add(mntmSulje);
		
		JMenu mnMuokkaa = new JMenu("Muokkaa teksti\u00E4");
		menuBar.add(mnMuokkaa);

		
//Etsii tekstistä ensimmäisen ja sanan ja merkitsee sen punaisella
// parempi ensimmäinen sana kun ei yhtään...		
		
		JMenuItem mntmEtsi = new JMenuItem("Etsi");
		mntmEtsi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			etsiSana();
				
				}
		});
//Tämä onnistui paremmin, korvaa kaikki valitut sanat toisella annetulla sanalla...
		
		mnMuokkaa.add(mntmEtsi);
		
		JMenuItem mntmKorvaa = new JMenuItem("Korvaa");
		mntmKorvaa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				muutaSanat();
			}
		});
		mnMuokkaa.add(mntmKorvaa);
		
		JMenu mnTietoja = new JMenu("Tietoja");
		menuBar.add(mnTietoja);
		
//Multiple screens ja kuvan, tekstin ja faviconin lisääminen paneliin... 
		
		JMenuItem mntmOhjelmatietoja = new JMenuItem("Olinko ulalla?");
		mntmOhjelmatietoja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Ulalla frame = new Ulalla();
				frame.setVisible(true);
				setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
});
	
		mnTietoja.add(mntmOhjelmatietoja);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		
		setContentPane(contentPane);
		
		JToolBar toolBar = new JToolBar();
		contentPane.add(toolBar, BorderLayout.NORTH);
		
// Leikkaa		
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editorPane.cut();
			}
		});
		btnNewButton.setIcon(new ImageIcon(TextEditor.class.getResource("/com/sun/javafx/scene/web/skin/Cut_16x16_JFX.png")));
		btnNewButton.setBackground(Color.LIGHT_GRAY);
		toolBar.add(btnNewButton);
		

// Print
		JButton btnNewButton_2 = new JButton("");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					editorPane.print();
				}
				catch(Exception ex) {
					System.out.println("Tulostus ei onnistu!");
				}
				System.out.println(e);
			}
		});
		btnNewButton_2.setIcon(new ImageIcon(TextEditor.class.getResource("/com/sun/javafx/scene/web/skin/Paste_16x16_JFX.png")));
		btnNewButton_2.setBackground(Color.LIGHT_GRAY);
		toolBar.add(btnNewButton_2);
		
//taustavärin vaihtaminen paneliin
		
		JButton btnSin = new JButton("SIN");
		btnSin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				editorPane.setBackground(Color.CYAN);
			}
		});
		toolBar.add(btnSin);
		
		JButton btnKel = new JButton("KEL");
		btnKel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				editorPane.setBackground(Color.YELLOW);
			}
		});
		toolBar.add(btnKel);
		
		editorPane = new JEditorPane();
		editorPane.setText("Valitse ALOITA - TIEDOSTO -valikosta ja ala luovaksi");
		contentPane.add(editorPane, BorderLayout.CENTER);
	}
	
	protected void open(File Ulalla) {
		// Miten meni omasta mielestä...
		
	}

	public JEditorPane getEditorPane() {
		return editorPane;
	}
	
// Pari tilaa vievää metodia siirsin pois tieltä...
	
	public void avaaTiedosto() {
		editorPane.setText("");
		Scanner lukija = null;
		File tiedosto = new File("src//EditorSave.txt");
		
		String rivi = null;
		try {
			lukija = new Scanner(tiedosto);
			 
			while(lukija.hasNextLine()) {
				rivi+= lukija.nextLine()+"\n";
				System.out.println(rivi);
			}
		}
		catch(FileNotFoundException ex) {
			System.out.println("Tiedostoa ei löydy...");
		}
		editorPane.setText(rivi);
	}
	
	public void tallennaTiedosto() {
		
		try {
			PrintWriter writer = new PrintWriter("EditorSave.txt");
			String tekstit = editorPane.getText();
			writer.println( tekstit );
			
			writer.flush();
			writer.close();
		}
		catch (Exception x) {
			System.out.println("Tallentaminen ei onnistu?");
			x.printStackTrace();
		}
	}
	public void etsiSana() {
		Scanner lukija1 = new Scanner (System.in);
		System.out.println("Anna haettava sana:");
		String etsitään = lukija1.nextLine();
		
		Scanner lukija = new Scanner(System.in);
		String tiedosto = new String(editorPane.getText());
	
		String rivi = null;
		
			lukija = new Scanner(tiedosto);
			int indeksi = tiedosto.indexOf(etsitään); 
			
			 while (lukija.hasNextLine()) {
			
				rivi+= lukija.nextLine()+"\n";
									
			if(tiedosto.indexOf(etsitään)> -1) {
	
		editorPane.setSelectionColor(Color.RED);

		editorPane.setSelectionStart(indeksi);
		editorPane.setSelectionEnd( indeksi + etsitään.length());
			} 
		}
	}
	public void muutaSanat() {
		Scanner lukija1 = new Scanner (System.in);
		System.out.println("Anna muutettava sana:");
		String muutetaan = lukija1.nextLine();
		
		System.out.println("Millä sanalla sana korvataan?");
		String uusi = lukija1.nextLine();
		
		Scanner lukija = new Scanner(System.in);
		String tiedosto = new String(editorPane.getText());
	
		String rivi = null;
		
			lukija = new Scanner(tiedosto);
			int indeksi = tiedosto.indexOf(muutetaan); 
			
			while (lukija.hasNextLine()) {
				rivi+= lukija.nextLine()+"\n";
									
			if(tiedosto.indexOf(muutetaan)> -1) {
		
		String uusiTiedosto = tiedosto.replaceAll(muutetaan, uusi);
		editorPane.setText(uusiTiedosto);
		
			}
		}
		
	}
	public void uusiTiedosto() {
		
		JFileChooser valintaikkuna = new JFileChooser();
		valintaikkuna.showSaveDialog(null);
		
		// uusiTiedosto();
		
		String uusiTiedosto = valintaikkuna.getSelectedFile().getAbsolutePath();
		System.out.println(uusiTiedosto);
		
		try {
			PrintWriter writer = new PrintWriter(uusiTiedosto);
			String tekstit = editorPane.getText();
			writer.println( tekstit );
			
			writer.flush();
			writer.close();
		}
		catch (Exception x) {
			System.out.println("Tallentaminen ei onnistu?");
			x.printStackTrace();
		}
		
	}
		
}