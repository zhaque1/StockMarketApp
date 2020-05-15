import java.awt.BorderLayout;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.BoxLayout;

public class Frame extends JFrame{

	 BoxLayout layout;
	 TopBar topbar;
	 UserPro userPro;
	 TableOfTickers tot;
	 JPanel container;
	 JScrollPane scrPane;

	public Frame(StockList list) {

		super("Stock Market App");
		topbar = new TopBar(list, this);
		tot = new TableOfTickers(list, this);
		userPro = new UserPro(list);
		updateUserStrat(new OneStrategy());
		container = new JPanel();
		layout = new BoxLayout(container,BoxLayout.Y_AXIS);

		container.setLayout(layout);


		container.add(topbar);
		container.add(tot);
		container.add(userPro);


		add(container);

		setSize(900,600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

	}
	public void notifyUserPro(){
		userPro.addData();
	}
	public void updateUserStrat(BuySellStockStrat strategy){
		userPro.setStrategy(strategy);
	}

}
