
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
public class Ulalla extends JFrame {
	
	    JPanel contentPane;
	    JLabel imageLabel = new JLabel();
	    JLabel headerLabel = new JLabel();
	    ImageIcon icon;
	    
// uusi frame ja siihen teksti ja gif ... jos onnistuu
	    
	    public Ulalla() {
	    	
	        try {
	            setDefaultCloseOperation(EXIT_ON_CLOSE);
	            contentPane = (JPanel) getContentPane();
	            contentPane.setLayout(new BorderLayout());
	            setSize(new Dimension(400, 300));
	            setTitle("Miten teht‰v‰ meni omasta mielest‰?");
	            icon = new ImageIcon("src/image/kuva.png");
	            setIconImage(icon.getImage());
	            
	            
	            // Otsikko
	            headerLabel.setFont(new java.awt.Font("Comic Sans MS", Font.BOLD, 16));
	            headerLabel.setText("   Kirjoitit yhden merkin v‰‰rin ja tajusit 2 pv p‰‰st‰!");
	            contentPane.add(headerLabel, java.awt.BorderLayout.NORTH);
	            

	            
	            // Kuvan liitt‰mis yrityksi‰ ...

//				String url = "file://C://Users//riikk//Documents//TekstiEditor3//src//image//ulalla1.gif";
//				editorPane.setText("<html><img src="+url+"><img></html>");
				
//				java.net.URL URL = getClass().getResource("ulalla");
//				System.out.println(url);
//				editorPane.setText("<html><img src="+url+"></img></html>");

	           
	            ImageIcon ulalla = new ImageIcon(this.getClass().getResource(
	                    "image\\kuutamolla.png"));
	            imageLabel.setIcon(ulalla);
	            contentPane.add(imageLabel, java.awt.BorderLayout.CENTER);
	            // set visiple
	            
            this.setLocationRelativeTo(null);
	            this.setVisible(true);
	        }
	         catch (Exception exception) {
	            exception.printStackTrace();
	        }
	    }
	          

	    public static void main(String[] args) {
	    	EventQueue.invokeLater(new Runnable() {
	    		public void run() {
	    			try {
	    				Ulalla frame = new Ulalla();
	    				frame.setVisible(true);
	    			}
	    			catch  (Exception ex) {
	    				System.out.println("Ulalla joo ollaan!");	
	    				}
	    			}
	    		
	    	});
	        
	    }

	}


