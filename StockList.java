import java.net.URL;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
public class StockList{
  private ArrayList <Stock> stockList;
  private ArrayList <StockWatcher> stockWatcherList;
  private ArrayList <Integer> userPortfolio;
  public StockList(){
    stockList = new ArrayList<Stock>();
    stockWatcherList = new ArrayList<StockWatcher>();
    userPortfolio = new ArrayList<Integer>();
  }
  public void addStock(Stock stock){
    stockWatcherList.add(new StockWatcher(stock));
    stockWatcherList.get(stockWatcherList.size()-1).parseData();
    stockList.add(stock);
  }
  public Stock getStock(int index){
    return stockList.get(index);
  }
  public void updateAll(DefaultTableModel dtm, Frame parent){
    Object[] row;
    for(StockWatcher stockWatcher : stockWatcherList){
      stockWatcher.updateData();
      int ind = stockWatcherList.indexOf(stockWatcher);
      /*Stock st = stockList.get(ind);
      if(st.getSymbol().compareTo("ALGRU")==0){
        st.setPrice(99.99);
        st.setChangePercentage(6);
      }*/
      row = stockList.get(ind).getRow();
      int i = 0;
      while(i<10){
        dtm.setValueAt(row[i], ind, i);
        i++;
      }
      if(userPortfolio.contains(ind))
        parent.notifyUserPro();
    }
  }
  public boolean newStock(String symbol){
    Stock stock = new Stock(symbol);
    StockWatcher stockWatcher = new StockWatcher(stock);
    if(stockWatcher.parseData()){
      stockWatcherList.add(stockWatcher);
      stockList.add(stock);
      return true;
    }else{
      return false;
    }
  }
  public Stock findStockBySymbol(String symbol){
    for(Stock stock : stockList){
      //System.out.println(symbol+" : "+stock.getSymbol());
      if(symbol.compareTo(stock.getSymbol())==0){
        return stock;
      }
    }
    return null;
  }
  public void addUserStock(Stock stock){
    userPortfolio.add(stockList.indexOf(stock));
  }
  public Stock findUserStock(String symbol){
    for(int index : userPortfolio){
      if(symbol.compareTo(stockList.get(index).getSymbol())==0){
        return stockList.get(index);
      }
    }
    return null;
  }
  public void removeUserStock(Stock stock){
    userPortfolio.remove((Object)stockList.indexOf(stock));
  }
  public ArrayList<Stock> getUserPortfolio(){
    ArrayList<Stock> stocks = new ArrayList<Stock>();
    for(int index : userPortfolio){
      stocks.add(getStock(index));
    }
    return stocks;
  }
}
