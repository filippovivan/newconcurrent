package by.filippov.newconcurrent.enteties;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

public class Auction extends Thread {
	private static final Logger LOG = Logger.getLogger(Auction.class);
	private ArrayList<Lot> lots;
	private LotDirector director;
	private SellingState state;

	public enum SellingState {
		ONE(" раз!"), TWO(" два!"), THREE(" три!"), SOLD(" продано!");
		private String message;

		public String getMessage() {
			return message;
		}

		SellingState(String message) {
			this.message = message;
		}
	}

	@Override
	public void run() {
		for (Lot lot : lots) {
			sell(lot);
		}
	}

	private boolean sell(Lot lot) {
		director = new LotDirector(lot);
		LOG.info("Lot " + lot + " placed");
		director.setRegistrationEnd(false);
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			LOG.warn("Waiting for registering interrapted");
		}
		director.setRegistrationEnd(true);
		while (true) {
			try {
				TimeUnit.MILLISECONDS.sleep(800);
			} catch (InterruptedException e) {
				LOG.warn("Waiting for a bet interrapted");
			}
			if (lot.isChanged()) {
				state = SellingState.ONE;
			} else {
				switch (state) {
				case ONE:
					state = SellingState.TWO;
					break;
				case TWO:
					state = SellingState.THREE;
					break;
				case THREE:
					state = SellingState.SOLD;
					break;
				default:
					LOG.warn("Invalid selling state");
				}
				LOG.info(lot + " " + state);
			}
			if (state == SellingState.SOLD) {
				return true;
			}
		}
	}

	public LotDirector getDirector() {
		return director;
	}

	public SellingState getSellingState() {
		return state;
	}

}
