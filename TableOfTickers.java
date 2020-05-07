import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class TableOfTickers extends JPanel{
	
	BorderLayout layout;
	JLabel label;
	JTable table;
	DefaultTableModel dtm;
	String[] header = { "SYMBOL", "NAME", "PRICE",
	            "OPEN", "DAY HIGH", "DAY LOW", "VOL", "PREV CLOSE", "CHANGE", "CHANGE %" };
	
	public TableOfTickers() {
		
		layout = new BorderLayout(0,0);
		label = new JLabel("Stock Symbol List");
		table = new JTable();
		dtm = new DefaultTableModel(0, 0);
		dtm.setColumnIdentifiers(header);
		
		table.setModel(dtm);
		for (int count = 1; count <= 5; count++) {
		        dtm.addRow(new Object[] { "data", "data", "data",
		                "data", "data", "data","data","data","data","data" });
		 }
		table.setPreferredScrollableViewportSize(table.getPreferredSize());
		table.setFillsViewportHeight(true);
		
		add(label, BorderLayout.NORTH);
		add(new JScrollPane(table), BorderLayout.CENTER);

	}
	
	void addData() {
		// TODO
	}

}
