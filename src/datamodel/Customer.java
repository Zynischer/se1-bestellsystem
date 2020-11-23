package datamodel;

/**
 * Kundenmodell
 * @author Mert Catik
 *
 */
public class Customer {
	private final String id;
	private String firstName;
	private String lastName;
	private String contact;
	
	/**
	 * public constructor
	 * @param id Id des Benutzers
	 * @param name Name des Benutzers
	 * @param contact Kontaktadresse des Benutzers
	 */
	protected Customer(String id, String name, String contact) {
		this.id = id;
		lastName = name;
		firstName = "";
		this.contact = contact;
	}
		
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getId() {
		return id;
	}
}
