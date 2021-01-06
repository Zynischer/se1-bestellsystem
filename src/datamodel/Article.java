package datamodel;

/**
 * Artikel Datenmodell
 * @author Mert Catik
 *
 */
public class Article {
	private String id;
	private String description;
	private long unitPrice;
	private int unitsInStore;
	
	/**
	 * public constructor
	 * @param id Artikelid
	 * @param descr Beschreibung des Artikels
	 * @param price Preis des Artikels
	 * @param units Verfügbare Einheiten des Artikels
	 */
	protected Article(String id, String descr, long price, int units) {
		this.id = id;
		setDescription(descr);
		setUnitPrice(price);
		setUnitsInStore(units);
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String descr) {
		if(descr == null || descr.isEmpty())
			descr = "";
		description = descr;
	}

	public long getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(long price) {
		if(price < 0 || price == Long.MAX_VALUE)
			price = 0;
		this.unitPrice = price;
	}

	public int getUnitsInStore() {
		return unitsInStore;
	}

	public void setUnitsInStore(int number) {
		if(number < 0 || number >= Integer.MAX_VALUE)
			number = 0;
		this.unitsInStore = number;
	}

	public String getId() {
		return id;
	}
	
}
