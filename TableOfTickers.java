import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.Dimension;
import java.util.Date;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.Runnable;
import java.lang.InterruptedException;
import java.lang.Thread;
import java.awt.Color;
import java.awt.Component;
import javax.swing.table.DefaultTableCellRenderer;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
public class TableOfTickers extends JPanel implements Runnable{

	private Thread t;
	BorderLayout layout;
	JLabel label;
	JTable table;
	Stock stockHolder;
	DefaultTableModel dtm;
	ExecutorService executor = Executors.newFixedThreadPool(20);
	String[] header = { "SYMBOL", "NAME", "PRICE",
	            "OPEN", "DAY HIGH", "DAY LOW", "VOL", "PREV CLOSE", "CHANGE", "CHANGE %" };
	StockList stocks;
	int i;
	Frame parent;
	public TableOfTickers(StockList list, Frame parent) {
		this.parent = parent;
		i = 0;
		stocks = list;
		layout = new BorderLayout(0,0);
		label = new JLabel("Stock Symbol List");
		table = new JTable() {
			@Override
			public Class<?> getColumnClass(int column){
				if(column<2)
					return String.class;
				return Double.class;
			}
		};
		table.setDefaultRenderer(String.class, new DefaultTableCellRenderer(){
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column){
				Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
				c.setBackground(new Color(37,52,59));
				c.setForeground(new Color(186, 200, 204));
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
		setPreferredSize(new Dimension(800,200));
		dtm.setColumnIdentifiers(header);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setModel(dtm);
		/*for (int count = 1; count <= 15; count++) {
		        dtm.addRow(new Object[] { "data", "data", "data",
		                "data", "data", "data","data","data","data","data" });
		}*/
		table.setPreferredScrollableViewportSize(table.getPreferredSize());
		table.setFillsViewportHeight(true);
		table.setDefaultEditor(Object.class, null);
		add(label, BorderLayout.NORTH);
		add(new JScrollPane(table), BorderLayout.CENTER);
		start();
	}

	public void start(){
		if(t == null){
			t = new Thread(this);
			t.start();
		}
	}

	@Override
	public void run(){
		parseData();
		while(true){
			try{
				Thread.sleep(10000);
				update();
				System.out.println("update");
			} catch(InterruptedException e){
				System.out.println(e);
			}
		}
	}
	public void parseData(){
		try{//limit=1000&
			URL url = new URL("https://financialmodelingprep.com/api/v3/search?query=&limit=1000&exchange=NASDAQ");;
      BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(),"UTF-8"));
      for(String line; (line = reader.readLine()) != null;) {
        String left;
        String right;
        String[] pline = line.split("[:]");
        left = pline[0].trim();
        //System.out.println(left.charAt(0)+":"+left.compareTo("\"symbol\""));
				if(left.compareTo("\"symbol\"")==0){
						if(stocks.newStock(pline[1].substring(2,pline[1].length()-2)))
						{
							stockHolder = stocks.getStock(i);
							executor.submit(this::addRow);
							i++;
						}
				}
      }
      reader.close();
    }catch(IOException e){
      System.out.println(e);
    }
	}
	public void addRow(){
		Stock stock = stockHolder;
		dtm.addRow(stock.getRow());
	}
	public void update(){
		stocks.updateAll(dtm, parent);
	}
	void addData() {

	}

}
