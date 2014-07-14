package by.filippov.newconcurrent.enteties;

import java.util.concurrent.locks.ReentrantLock;

public class Lot {
	private int id;
	private String description;
	private int initPrice;
	private int currentPrice;
	private Person lastRised;
	private boolean changed;
	ReentrantLock lock = new ReentrantLock();

	public boolean isChanged() {
		return changed;
	}

	public void setChanged(boolean changed) {
		this.changed = changed;
	}

	public Lot() {
	}

	public Lot(int id, String description, int initPrice) {
		super();
		this.id = id;
		this.description = description;
		this.initPrice = initPrice;
		currentPrice = initPrice;
		changed = false;
	}

	public void raisePrice(int addition, Person person) {
		try {
			lock.lock();
			currentPrice += addition;
			lastRised = person;
		} finally {
			lock.unlock();
		}
	}

	public int getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public int getInitPrice() {
		return initPrice;
	}

	public int getCurrentPrice() {
		return currentPrice;
	}

	public Person getLastRised() {
		return lastRised;
	}
}
