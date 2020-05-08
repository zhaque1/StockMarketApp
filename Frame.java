import java.awt.BorderLayout;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class Frame extends JFrame{

	 BorderLayout layout;
	 TopBar topbar;
	 UserPro userPro;
	 TableOfTickers tot;
	 JPanel container;
	 AdviceArea aa;
	 JScrollPane scrPane;


	public Frame() {

		super("Stock Market App");
	  layout = new BorderLayout(0,10);
		topbar = new TopBar();
		tot = new TableOfTickers();
		userPro = new UserPro();
		container = new JPanel();
		aa = new AdviceArea();

		setLayout(layout);


		container.add(topbar, BorderLayout.NORTH);
		container.add(tot, BorderLayout.CENTER);
		container.add(userPro, BorderLayout.CENTER);
		container.add(aa, BorderLayout.CENTER);

		scrPane = new JScrollPane(container);

		add(container);

		setSize(900,600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

	}

}
