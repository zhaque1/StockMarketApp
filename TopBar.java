import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.awt.TextField;
public class TopBar extends JPanel{

	JButton search;
	JButton remove;
	JComboBox<?> adviceCB;
	String stratList[] = {"One", "Alt", "Random", "Terrible"};
	JTextField searchAreaTA;
	JLabel selStratLabel,searchAreaLabel;
  StockList stockList;
	Frame parent;
	public TopBar(StockList list, Frame parent) {
		stockList = list;
		this.parent = parent;
		search = new JButton("ADD");
		remove = new JButton("REMOVE");
		searchAreaTA = new JTextField(6);
		//searchAreaTA = new JTextArea(1,6);
		search.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e){
				Stock temp = stockList.findStockBySymbol(searchAreaTA.getText());
			//	System.out.println(searchAreaTA.getText()+":"+temp.getSymbol());
				if(temp!=null){
					list.addUserStock(temp);
					parent.notifyUserPro();
					searchAreaTA.setText("");
				}
			}
		});
		remove.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				Stock temp = stockList.findUserStock(searchAreaTA.getText());
				if(temp!=null){
					list.removeUserStock(temp);
					parent.notifyUserPro();
					searchAreaTA.setText("");
				}
			}
		});
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
		        switch(selectedStrat){
							case "One":
								parent.updateUserStrat(new OneStrategy());
								break;
							case "Alt":
								parent.updateUserStrat(new AlternateStrategy());
								break;
							case "Random":
								parent.updateUserStrat(new RandomStrategy());
								break;
							case "Terrible":
								parent.updateUserStrat(new TerribleStrategy());
								break;
							default:
								break;
						}
						parent.notifyUserPro();
			}
		});

		add(searchAreaLabel);
		add(searchAreaTA);
		add(search);
		add(remove);
		add(selStratLabel);
		add(adviceCB);

	}

}
