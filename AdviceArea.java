import javax.swing.*;

public class AdviceArea extends JPanel {
	
	JTextArea adviceTA;
	JLabel adviceLabel;
	
	public AdviceArea() {
		adviceTA = new JTextArea(1,10);
		adviceLabel = new JLabel("ADVICE: ");
		
		add(adviceLabel);
		add(adviceTA);
		
	}
	
}
