
public class OneStrategy implements BuySellStockStrat {

	@Override
	public String buyOrSell(Stock obj) {
		
		if(obj.changePercentage > 5.00) {
			return "BUY";
		}
		
		else if(obj.changePercentage < 5.00) {
			return "SELL";
		}
		
		else {
			return "HOLD";
		}
		

	}

}
