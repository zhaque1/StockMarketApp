import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ArrayList <Stock> stockList = new ArrayList<Stock>();
		Frame frame = new Frame();
		Stock temp = new Stock();
		
		URL url = null;
		try {
			url = new URL("https://financialmodelingprep.com/api/v3/search?exchange=NASDAQ");
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"))) {
		    for (String line; (line = reader.readLine()) != null;) {

		    if(line.equals("[ {")) {
		    	continue;
		    }
		    
		    else if(line.equals("} ]")) {
		    	continue;
		    }
		    
		    else{
			    System.out.println(line);
			    
		    }
		    
		  }
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
