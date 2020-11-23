package datamodel;

/**
 * Bestellpositionierungsmodell
 * @author Mert Catik
 *
 */
public class OrderItem {
	private String description;
	private Article article;
	private int unitsOrdered;
	
	/**
	 * public constructor
	 * @param descr Beschreibung der Position
	 * @param article Artikel der Position
	 * @param units Verkaufte Einheiten
	 */
	protected OrderItem(String descr, Article article, int units) {
		description = descr;
		this.article = article;
		unitsOrdered = units;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String descr) {
		description = descr;
	}

	public int getUnitsOrdered() {
		return unitsOrdered;
	}

	public void setUnitsOrdered(int number) {
		this.unitsOrdered = number;
;
	}

	public Article getArticle() {
		return article;
	}
}
