
public class TerribleStrategy implements BuySellStockStrat {

	@Override
	public String buyOrSell(Stock obj) {
		
		if(obj.symbol.length() == 3)
			return "BUY";
		
		else if(obj.price < 20.00)
			return "SELL";
		
		else 
			return "HOLD";
		

	}

}
