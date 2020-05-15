import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Component;
import javax.swing.table.DefaultTableCellRenderer;
public class UserPro extends JPanel{

	BorderLayout layout;
	JLabel label;
	JTable table;
	DefaultTableModel dtm;
	StockList stocks;
	BuySellStockStrat strategy;
	String[] header = { "SYMBOL", "NAME", "PRICE",
	            "OPEN", "DAY HIGH", "DAY LOW",
							"VOL", "PREV CLOSE", "CHANGE",
							"CHANGE %", "Advice" };

	public UserPro(StockList stocks) {
		this.stocks = stocks;
		layout = new BorderLayout(0,0);
		label = new JLabel("User Portfolio");
		label.setPreferredSize(new Dimension(100,25));
		label.setBackground(new Color(120, 187, 255));
		table = new JTable() {
			@Override
			public Class<?> getColumnClass(int column){
				if(column<2 || column>9)
					return String.class;
				return Double.class;
			}
		};
		table.setDefaultRenderer(String.class, new DefaultTableCellRenderer(){
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column){
				Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
				int h = ((String) value).compareTo("BUY");
				int k = ((String) value).compareTo("HOLD");
				int j = ((String) value).compareTo("SELL");
				Color colr;
				if(h==0){
					colr = new Color(79, 184, 117);
					c.setForeground(new Color(37, 52, 59));
				}
				else if(k==0){
					colr = new Color(235, 237, 133);
					c.setForeground(new Color(37, 52, 59));
				}
				else if(j==0){
					colr = new Color(232, 111, 111);
					c.setForeground(new Color(37, 52, 59));
				}
				else{
					colr = new Color(37,52,59);
					c.setForeground(new Color(186, 200, 204));
				}
				c.setBackground(colr);
				return c;
			}
		});
		table.setDefaultRenderer(Double.class, new DefaultTableCellRenderer(){
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column){
				Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
				c.setBackground(new Color(37,52,59));
				c.setForeground(new Color(186, 200, 204));
				return c;
			}
		});
		dtm = new DefaultTableModel(0, 0);
		setLayout(layout);
		setPreferredSize(new Dimension(800,100));
		dtm.setColumnIdentifiers(header);
		table.setModel(dtm);
		table.setBackground(new Color(37, 52, 59));
		table.setPreferredScrollableViewportSize(table.getPreferredSize());
		table.setFillsViewportHeight(true);
		table.setDefaultEditor(Object.class, null);
		add(label, BorderLayout.NORTH);
		add(new JScrollPane(table), BorderLayout.CENTER);

	}

	public void setStrategy(BuySellStockStrat strategy){
		this.strategy = strategy;
	}

	void addData() {
		ArrayList<Stock> stocklist = stocks.getUserPortfolio();
		dtm.setRowCount(0);
		for(Stock stock : stocklist){
			dtm.addRow(stock.getRow());
			dtm.setValueAt(strategy.buyOrSell(stock),stocklist.indexOf(stock),10);
		}
	}
	void removeData() {

	}


}
