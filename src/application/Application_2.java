package application;

import java.util.ArrayList;
import java.util.List;

import datamodel.Article;
import datamodel.Customer;
import datamodel.Order;
import datamodel.OrderItem;
import system.ComponentFactory;
import system.Components;

public class Application_2 {
	private final int printLineWidth = 95;
	public static void main(String[] args) {
		System.out.println("SE1‐Bestellsystem");
		ComponentFactory componentFactory = ComponentFactory.getInstance();
		Components.OutputProcessor outputProcessor = componentFactory.getOutputProcessor();
		Components.DataFactory dataFactory = componentFactory.getDataFactory();
		Components.OrderProcessor orderProcessor = componentFactory.getOrderProcessor();

		/*
		 * Erzeugung der Kunden, Artikel und Bestellungen aus Application_1.java
		 */
		Customer cEric = dataFactory.createCustomer("Eric Schulz-Mueller", "eric2346@gmail.com");
		Customer cAnne = dataFactory.createCustomer("Meyer, Anne", "+4917223524");
		Customer cNadine = dataFactory.createCustomer("Nadine Ulla Blumenfeld", "+4915292454");
		Customer cTimo = dataFactory.createCustomer("Timo Werner", "tw@gmail.com");
		Customer cSandra = dataFactory.createCustomer("Sandra Müller", "samue62@gmail.com");

/*		
		Customer cEric = new Customer("C86516", "Eric Schulz-Mueller", "eric2346@gmail.com");
		Customer cAnne = new Customer("C64327", "Meyer, Anne", "+4917223524");
		Customer cNadine = new Customer("C12396", "Nadine Ulla Blumenfeld", "+4915292454");
		Customer cTimo = new Customer("C12345", "Timo Werner", "tw@gmail.com");
		Customer cSandra = new Customer("C54321", "Sandra Müller", "samue62@gmail.com");
*/

		Article aTasse = dataFactory.createArticle("Tasse", 299, 2000);
		Article aBecher = dataFactory.createArticle("Becher", 149, 8400);
		Article aKanne = dataFactory.createArticle("Kanne", 2000, 2400);
		Article aTeller = dataFactory.createArticle("Teller", 649, 7000);
		Article aKaffeemaschine = dataFactory.createArticle("Kaffeemaschine", 2999, 500);
		Article aTeekocher = dataFactory.createArticle("Teekocher", 1999, 2000);

		// Eric's 1st order
		Order o5234 = dataFactory.createOrder(cEric);
		OrderItem oi1 = dataFactory.createOrderItem(aKanne.getDescription(), aKanne, 1); // 1x Kanne
		o5234.addItem(oi1); // add OrderItem to Order 5234968294L

		// Eric's 2nd order
		Order o8592 = dataFactory.createOrder(cEric);
		o8592.addItem( // add three OrderItems to Order 8592356245L
				dataFactory.createOrderItem(aTeller.getDescription(), aTeller, 4) // 4x Teller
		).addItem(dataFactory.createOrderItem(aBecher.getDescription(), aBecher, 8) // 8x Becher
		).addItem(dataFactory.createOrderItem("passende Tassen", aTasse, 4) // 4x passende Tassen
		);

		// Anne's order
		Order o3563 = dataFactory.createOrder(cAnne);
		o3563.addItem(dataFactory.createOrderItem(aKanne.getDescription() + " aus Porzellan", aKanne, 1));

		// Nadine's order
		Order o6135 = dataFactory.createOrder(cNadine);
		o6135.addItem( // 12x Teller
				dataFactory.createOrderItem(aTeller.getDescription() + " blau/weiss Keramik", aTeller, 12));

		// Werner's order
		Order o3234 = dataFactory.createOrder(cTimo);
		o3234.addItem(dataFactory.createOrderItem(aKaffeemaschine.getDescription(), aKaffeemaschine, 1))
				.addItem(dataFactory.createOrderItem(aTasse.getDescription(), aTasse, 6));

		// Mueller's order
		Order o6565 = dataFactory.createOrder(cSandra);
		o6565.addItem(dataFactory.createOrderItem(aTeekocher.getDescription(), aTeekocher, 1))
				.addItem(dataFactory.createOrderItem(aBecher.getDescription(), aBecher, 4))
				.addItem(dataFactory.createOrderItem(aTeller.getDescription(), aTeller, 4));

		List<Order> orders = new ArrayList<Order>(List.of(o5234, o8592, o3563, o6135, o3234, o6565));

		outputProcessor.printOrders(orders, false); // Ausgabe aller Bestellungen
	}
}
