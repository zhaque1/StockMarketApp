import java.util.Date;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
public class StockWatcher{
  private String symbol;
  private Stock stock;
  private float last;
  private Date date;
  String full = "https://financialmodelingprep.com/api/v3/historical-price-full/";
  String day = "?timeseries=1";
  String profile = "https://financialmodelingprep.com/api/v3/company/profile/";
  URL prof;
  URL hist;
  public StockWatcher(Stock stock){
    this.symbol = stock.getSymbol();
    this.stock = stock;
    try{
      String temp = profile+symbol;
      String temp2 = full+symbol+day;
      prof = new URL(temp);
      hist = new URL(temp2);
    }catch(MalformedURLException e){
      System.out.println("oops");
    }
  }
  public void updateData(){
    parseData();
  }
  public boolean parseData(){
    boolean ret = true;
    try{
      BufferedReader reader = new BufferedReader(new InputStreamReader(prof.openStream(),"UTF-8"));
      for(String line; (line = reader.readLine()) != null;) {
        String left;
        String right;
        String[] pline = line.split("[:]");
        if(pline[0].length()>3){
          left = pline[0].trim();
          //System.out.println(left);
          left = left.substring(1,left.length()-1);
          //System.out.println(left);
          switch(left){
            case "price":
              stock.setPrice(new Double(pline[1].substring(1,pline[1].length()-1)).doubleValue());
            case "companyName":
              //System.out.println(pline[1]);
              if(pline[1].length()==4||pline[1].substring(1,pline[1].length()-1).compareTo("null")==0)
                ret = false;
              stock.setName(pline[1].substring(1,pline[1].length()-1));
              break;
            default:
              break;
          }
        }
      }
      reader.close();
    }catch(IOException e){
      System.out.println(e);
    }
    try{
      BufferedReader reader1 = new BufferedReader(new InputStreamReader(hist.openStream(),"UTF-8"));
      for(String line1; (line1 = reader1.readLine()) != null;) {
        String left1;
        String right1;
        String[] pline1 = line1.split("[:]");
        if(pline1[0].length()>3){
          left1 = pline1[0].trim();
          left1 = left1.substring(1,left1.length()-1);
          //System.out.println(left1);
          switch(left1){
            case "open":
              stock.setOpen(new Double(pline1[1].substring(1,pline1[1].length()-1)).doubleValue());
              break;
            case "high":
              stock.setDayHigh(new Double(pline1[1].substring(1,pline1[1].length()-1)).doubleValue());
              break;
            case "low":
              stock.setDayLow(new Double(pline1[1].substring(1,pline1[1].length()-1)).doubleValue());
              break;
            case "close":
              stock.setPrevClose(new Double(pline1[1].substring(1,pline1[1].length()-1)).doubleValue());
              break;
            case "volume":
              stock.setVolume(new Double(pline1[1].substring(1,pline1[1].length()-1)).doubleValue());
              break;
            case "change":
              stock.setChange(new Double(pline1[1].substring(1,pline1[1].length()-1)).doubleValue());
              break;
            case "changePercent":
              stock.setChangePercentage(new Double(pline1[1].substring(1,pline1[1].length()-1)).doubleValue());
              break;
            default:
              break;
          }
        }
      }
      reader1.close();
    }catch(IOException e){
      System.out.println(e);
    }
    return ret;
  }
}
