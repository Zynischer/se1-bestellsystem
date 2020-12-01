package system;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;

import datamodel.Customer;
import datamodel.Order;
import datamodel.OrderItem;

final class OutputProcessor implements system.Components.OutputProcessor {

	private InventoryManager inventoryManager;
	private OrderProcessor orderProcessor;
	private final int printLineWidth = 95;

	OutputProcessor(InventoryManager inventory, OrderProcessor processor) {
		inventoryManager = inventory;
		orderProcessor = processor;
	}

	@Override
	public void printOrders(List<Order> orders, boolean printVAT) {
		StringBuffer sbAllOrders = new StringBuffer("-------------");
		StringBuffer sbLineItem = new StringBuffer();

		/*
		 * Insert code to print orders with all order items:
		 */

		int gesamtpreis = 0;
		for (Order order : orders) {

			int preis = 0;

			Customer customer = order.getCustomer();
			String customerName = splitName( customer, customer.getFirstName() + " " + customer.getLastName() );
			String output = "#" + order.getId() + ", " + customerName + "'s Bestellung: ";
			for (OrderItem item : order.getItems()) {
				if (preis != 0) {
					output += ", ";
				}
				preis += item.getUnitsOrdered() * item.getArticle().getUnitPrice();
				output += item.getUnitsOrdered() + "x " + item.getDescription();
			}
			sbLineItem = fmtLine(output, fmtPrice(preis, "EUR", 14), printLineWidth);

			sbLineItem.append("\t");
			sbAllOrders.append("\n");
			sbAllOrders.append(sbLineItem);
			gesamtpreis += preis;
		}

		// calculate total price
		String fmtPriceTotal = pad(fmtPrice(gesamtpreis, "", " EUR"), 14, true);

		// append final line with totals
		sbAllOrders.append("\n").append(fmtLine("-------------", "------------- -------------", printLineWidth))
				.append("\n").append(fmtLine("Gesamtwert aller Bestellungen:", fmtPriceTotal, printLineWidth));
		
		if(printVAT) {
			String vat = pad(fmtPrice(orderProcessor.vat(gesamtpreis, 1), "", " EUR"), 14, true);
			sbAllOrders.append("\n").append(fmtLine("Im Gesamtbetrag enthaltene Mehrwertsteuer", vat, printLineWidth));
		}

		// print sbAllOrders StringBuffer with all output to System.out
		System.out.println(sbAllOrders.toString());
	}

	@Override
	public void printInventory() {
		// TODO Auto-generated method stub

	}

	@Override
	public String fmtPrice(long price, String currency) {
		String fmtPrice = pad(fmtPrice(price, "", " " + currency), 14, true);
		return fmtPrice;
	}

	@Override
	public String fmtPrice(long price, String currency, int width) {
		String fmtPrice = pad(fmtPrice(price, "", " " + currency), 14, true);
		return fmtPrice;
	}

	private String fmtPrice( long price, String prefix, String postfix ) {
		StringBuffer fmtPriceSB = new StringBuffer();
		if( prefix != null ) {
			fmtPriceSB.append( prefix );
		}

		fmtPriceSB = fmtPrice( fmtPriceSB, price );

		if( postfix != null ) {
			fmtPriceSB.append( postfix );
		}
		return fmtPriceSB.toString();
	}

	private StringBuffer fmtPrice( StringBuffer sb, long price ) {
		if( sb == null ) {
			sb = new StringBuffer();
		}
		double dblPrice = ( (double)price ) / 100.0;			// convert cent to Euro
		DecimalFormat df = new DecimalFormat( "#,##0.00",
			new DecimalFormatSymbols( new Locale( "de" ) ) );	// rounds double to 2 digits

		String fmtPrice = df.format( dblPrice );				// convert result to String in DecimalFormat
		sb.append( fmtPrice );
		return sb;
	}

	
	@Override
	public StringBuffer fmtLine(String leftStr, String rightStr, int width) {
		StringBuffer sb = new StringBuffer(leftStr);
		int shiftable = 0; // leading spaces before first digit
		for (int i = 1; rightStr.charAt(i) == ' ' && i < rightStr.length(); i++) {
			shiftable++;
		}
		final int tab1 = width - rightStr.length() + 1; // - ( seperator? 3 : 0 );
		int sbLen = sb.length();
		int excess = sbLen - tab1 + 1;
		int shift2 = excess - Math.max(0, excess - shiftable);
		if (shift2 > 0) {
			rightStr = rightStr.substring(shift2, rightStr.length());
			excess -= shift2;
		}
		if (excess > 0) {
			switch (excess) {
			case 1:
				sb.delete(sbLen - excess, sbLen);
				break;
			case 2:
				sb.delete(sbLen - excess - 2, sbLen);
				sb.append("..");
				break;
			default:
				sb.delete(sbLen - excess - 3, sbLen);
				sb.append("...");
				break;
			}
		}
		String strLineItem = String.format("%-" + (tab1 - 1) + "s%s", sb.toString(), rightStr);
		sb.setLength(0);
		sb.append(strLineItem);
		return sb;
	}

	@Override
	public String splitName(Customer customer, String name) {
		String tname = name.trim();

		String[] pieces;
		if (tname.contains(",")) {
			pieces = tname.split(",");
			customer.setFirstName(pieces[1].trim());
			customer.setLastName(pieces[0].trim());
		} else if (tname.contains(" ")) {
			pieces = tname.split(" ");
			for (int i = 0; i < pieces.length - 1; i++) {
				if (i == 0) {
					customer.setFirstName(pieces[0].trim());
				} else {
					customer.setFirstName(String.join(" ", customer.getFirstName(), pieces[i].trim()));
				}
			}
			customer.setLastName(pieces[pieces.length - 1].trim());
		}
		return customer.getLastName()+", "+customer.getFirstName();
	}

	@Override
	public String singleName(Customer customer) {
		return customer.getLastName() + ", " + customer.getFirstName();
	}
	
	/**
	 * Pad string to minimum width, either right-aligned or left-aligned
	 * 
	 * @param str String to pad
	 * @param width minimum width to which result is padded
	 * @param rightAligned flag to chose left- or right-alignment
	 * @return padded String
	 */
	private String pad( String str, int width, boolean rightAligned ) {
		String fmtter = ( rightAligned? "%" : "%-" ) + width + "s";
		String padded = String.format( fmtter, str );
		return padded;
	}



}
