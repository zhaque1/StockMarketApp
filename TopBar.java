import javax.swing.*;

public class TopBar extends JPanel{
	
	JButton search;
	JComboBox<?> adviceCB;
	String stratList[] = {"One", "Alt", "Random", "Terrible"};
	JTextArea searchAreaTA;
	JLabel selStratLabel,searchAreaLabel;
	
	public TopBar() {
		
		search = new JButton("ADD");
		searchAreaTA = new JTextArea(1,6);
		selStratLabel = new JLabel("Select Investment Strategy");
		searchAreaLabel = new JLabel("ADD Stock");
		adviceCB = new JComboBox<Object>(stratList);
		
		add(searchAreaLabel);
		add(searchAreaTA);
		add(search);
		add(selStratLabel);
		add(adviceCB);

	}

}
