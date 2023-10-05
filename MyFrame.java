import java.awt.Color;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
 
public class MyFrame extends JFrame {
	private MyPanel panel;
	
    public MyPanel getPanel() {
		return panel;
	}

	public MyFrame() {
        super("BALL");
        panel = new MyPanel();
        add(panel); 
        pack();
        panel.setBackground(Color.black);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
	public static void main(String[] args) throws IOException {
		new MyFrame().getPanel().dzialaj();
	}
}