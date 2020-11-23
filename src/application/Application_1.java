package application;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;



/**
 * 
 * Main class to launch the application.
 * 
 * @author svgr64
 *
 */
public class Application_1 {

	private final int printLineWidth = 84;


	/**
	 * main() to launch application.
	 * 
	 * @param args command line arguments
	 */
	public static void main( String[] args ) {

		System.out.println( "SE1-Bestellsystem" );

		Application_1 application = new Application_1();

	}



	/*
	 * Private methods.
	 */



	/**
	 * Format long-price in 1/100 (cents) to String using DecimalFormatter, add
	 * currency and pad to minimum width right-aligned.
	 * For example, 299, "EUR", 12 -> "    2,99 EUR"
	 * 
	 * @param price price as long in 1/100 (cents)
	 * @param currency currency as String, e.g. "EUR"
	 * @param width minimum width to which result is padded
	 * @return price as String with currency and padded to minimum width
	 */
	private String fmtPrice( long price, String currency, int width ) {
		String fmtPrice = pad( fmtPrice( price, "", " " + currency ), 14, true );
		return fmtPrice;
	}


	/**
	 * Format long-price in 1/100 (cents) to String using DecimalFormatter and
	 * prepend prefix and append postfix String.
	 * For example, 299, "(", ")" -> "(2,99)"
	 * 
	 * @param price price as long in 1/100 (cents)
	 * @param prefix String to prepend before price
	 * @param postfix String to append after price
	 * @return price as String
	 */
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


	/**
	 * Format long-price in 1/100 (cents) to String using DecimalFormatter.
	 * For example, 299 -> "2,99"
	 * 
	 * @param sb StringBuffer to which price is added
	 * @param price price as long in 1/100 (cents)
	 * @return StringBuffer with added price
	 */
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


	/**
	 * Format line to a left-aligned part followed by a right-aligned part padded to
	 * a minimum width.
	 * For example:
	 * 
	 * <left-aligned part>                          <>       <right-aligned part>
	 * 
	 * "#5234968294, Eric's Bestellung: 1x Kanne         20,00 EUR   (3,19 MwSt)"
	 * 
	 * |<-------------------------------<width>--------------------------------->|
	 * 
	 * @param leftStr left-aligned String
	 * @param rightStr right-aligned String
	 * @param totalWidth minimum width to which result is padded
	 * @return String with left- and right-aligned parts padded to minimum width
	 */
	private StringBuffer fmtLine( String leftStr, String rightStr, int totalWidth ) {
		StringBuffer sb = new StringBuffer( leftStr );
		int shiftable = 0;		// leading spaces before first digit
		for( int i=1; rightStr.charAt( i ) == ' ' && i < rightStr.length(); i++ ) {
			shiftable++;
		}
		final int tab1 = totalWidth - rightStr.length() + 1;	// - ( seperator? 3 : 0 );
		int sbLen = sb.length();
		int excess = sbLen - tab1 + 1;
		int shift2 = excess - Math.max( 0, excess - shiftable );
		if( shift2 > 0 ) {
			rightStr = rightStr.substring( shift2, rightStr.length() );
			excess -= shift2;
		}
		if( excess > 0 ) {
			switch( excess ) {
			case 1:	sb.delete( sbLen - excess, sbLen ); break;
			case 2: sb.delete( sbLen - excess - 2 , sbLen ); sb.append( ".." ); break;
			default: sb.delete( sbLen - excess - 3, sbLen ); sb.append( "..." ); break;
			}
		}
		String strLineItem = String.format( "%-" + ( tab1 - 1 ) + "s%s", sb.toString(), rightStr );
		sb.setLength( 0 );
		sb.append( strLineItem );
		return sb;
	}
}
