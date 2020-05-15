
public class RandomStrategy implements BuySellStockStrat {

	@Override
	public String buyOrSell(Stock obj) {

		if((obj.symbol.length() == 4) && !(obj.time > 1300 && obj.time < 1622)) {
			return  "BUY";
		}

		else if((obj.time > 1300 && obj.time < 1622) && !(obj.symbol.length() == 4 )) {
			return "SELL";
		}

		else
			return "HOLD";


	}

}
