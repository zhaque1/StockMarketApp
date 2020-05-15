import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.awt.TextField;
public class TopBar extends JPanel{

	JButton search;
	JButton remove;
	JComboBox<?> adviceCB;
	String stratList[] = {"Buy High", "Sell High", "Sell Random", "Terrible Strategy"};
	JTextField searchAreaTA;
	JLabel selStratLabel,searchAreaLabel,stratLabel;
  StockList stockList;
	Frame parent;
	public TopBar(StockList list, Frame parent) {
		stockList = list;
		this.parent = parent;
		search = new JButton("ADD");
		remove = new JButton("REMOVE");
		searchAreaTA = new JTextField(6);
		stratLabel = new JLabel("Buy when Change% > 5, else sell");
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
		searchAreaLabel = new JLabel("Search Stock");
		adviceCB = new JComboBox<Object>(stratList);
		adviceCB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JComboBox<String> combo = (JComboBox<String>) e.getSource();
		        String selectedStrat = (String) combo.getSelectedItem();
		        // TODO: CHANGE THIS SO it saves selectedStrat to something that can be accessed
		        switch(selectedStrat){
							case "Buy High":
								parent.updateUserStrat(new OneStrategy());
								stratLabel.setText("Buy when Change% > 5, else sell");
								break;
							case "Sell High":
								parent.updateUserStrat(new AlternateStrategy());
								stratLabel.setText("Buy when Change% < 5, else sell");
								break;
							case "Sell Random":
								parent.updateUserStrat(new RandomStrategy());
								stratLabel.setText("Buys and sells randomly");
								break;
							case "Terrible Strategy":
								parent.updateUserStrat(new TerribleStrategy());
								stratLabel.setText("Buys if the stock symbol is 3 characters long, else if sells if the stock price < 20, else hold");
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
		add(stratLabel);

	}

}
