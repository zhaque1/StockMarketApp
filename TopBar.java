import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TopBar extends JPanel implements ActionListener{

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
		adviceCB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JComboBox<String> combo = (JComboBox<String>) e.getSource();
		        String selectedStrat = (String) combo.getSelectedItem();
		        // TODO: CHANGE THIS SO it saves selectedStrat to something that can be accessed
		        System.out.println(selectedStrat);
			}
		});

		add(searchAreaLabel);
		add(searchAreaTA);
		add(search);
		add(selStratLabel);
		add(adviceCB);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JComboBox<String> combo = (JComboBox<String>) e.getSource();
				String selectedStrat = (String) combo.getSelectedItem();
				// TODO: CHANGE THIS SO it saves selectedStrat to something that can be accessed
				System.out.println(selectedStrat);
	}

}
