
public class AlternateStrategy implements BuySellStockStrat {

	@Override
	public String buyOrSell(Stock obj) {
		
		if(obj.changePercentage > 5.00) {
			return "SELL";
		}
		
		else if(obj.changePercentage < 5.00) {
			return "BUY";
		}
		
		else {
			return "HOLD";
		}

	}

}
