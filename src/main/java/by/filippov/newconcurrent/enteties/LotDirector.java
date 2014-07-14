package by.filippov.newconcurrent.enteties;

import java.util.ArrayList;

public class LotDirector extends Thread {
	private boolean registrationEnd;
	private ArrayList<Person> persons;
	private Lot lot;

	public LotDirector(Lot lot) {
		registrationEnd = true;
		persons = new ArrayList<>();
		this.lot = lot;
	}

	public boolean registerPerson(Person person) {
		if (!registrationEnd) {
			return persons.add(person);
		} else {
			return false;
		}
	}

	public boolean setRegistrationEnd(boolean b) {
		return registrationEnd;
	}

	public Lot getLot() {
		return lot;
	}
}
