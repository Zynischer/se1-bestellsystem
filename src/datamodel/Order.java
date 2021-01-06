package datamodel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Bestellmodell
 * @author Mert Catik
 *
 */
public class Order {
	private final long id;
	private final Date date;
	private final Customer customer;
	private final List<OrderItem> items;
	
	/**
	 * public constructor
	 * @param id Id der Bestellung
	 * @param date Datum der Bestellung
	 * @param customer Kunde der Bestellung
	 */
	protected Order(long id, Date date, Customer customer) {
		this.id = id;
		if(date == null) 
			date = new Date();
		this.date = date;
		this.customer = customer;
		this.items = new ArrayList<OrderItem>();
	}

	public long getId() {
		return id;
	}

	public Date getDate() {
		return date;
	}

	public Customer getCustomer() {
		return customer;
	}

	public Iterable<OrderItem> getItems() {
		return items;
	}
	
	public int count() {
		return items.size();
	}
	
	public Order addItem(OrderItem item) {
		if(item == null || items.contains(item)) 
			return null;
		items.add(item);
		return this;
	}
	
	public Order removeItem(OrderItem item) {
		items.remove(item);
		return this;
	}

	
	public Order clearItem() {
		items.clear();
		return this;
	}
}
