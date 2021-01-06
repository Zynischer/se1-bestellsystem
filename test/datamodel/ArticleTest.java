package datamodel;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import org.junit.FixMethodOrder;


/**
 * 
 * JUnit4 test code for Article class.
 * 
 * Use of assertions, see:
 *   https://junit.org/junit4/javadoc/latest/org/junit/Assert.html
 * 
 * @author sgra64
 */
@FixMethodOrder(org.junit.runners.MethodSorters.NAME_ASCENDING)
public class ArticleTest {

	/*
	 * Test fixtures - objects needed to perform the tests
	 */
	private final String aToaster_id = "SKU-868682";
	private final String aToaster_description = "Toaster";
	private final long aToaster_unitPrice = 2499;
	private final int aToaster_unitsInStore = 1200;

	private final Article aToaster = new Article( aToaster_id, aToaster_description,
			aToaster_unitPrice, aToaster_unitsInStore );

	/*
	 * Test constructor, regular case.
	 */
	@Test
	public void test001_RegularConstructor() {
		Article a = new Article( aToaster_id, aToaster_description, aToaster_unitPrice, aToaster_unitsInStore );
		assertEquals( a.getId(), aToaster_id );	// assert that correct id is returned
		assertSame( a.getId(), aToaster_id );		// "==" - equivalent
		/*
		 * insert tests to verify that:
		 *  - description is returned as provided in the constructor					OK
		 *  - unit price is returned as provided in the constructor						OK
		 * 	- units-in-store are returned as provided in the constructor				OK
		 */
		assertEquals(aToaster_description, aToaster.getDescription());
		assertEquals(aToaster_unitPrice, aToaster.getUnitPrice());
		assertEquals(aToaster_unitsInStore, aToaster.getUnitsInStore());

	}

	/*
	 * Test constructor, special case with empty String and 0 - arguments.
	 */
	@Test
	public void test002_EmptyArgumentConstructor() {
		/*
		 * insert tests for a constructor invocation: new Article( "", "", 0, 0 );
		 * to verify that:
		 *  - id "" is returned															OK
		 *  - description "" is returned												OK
		 *  - unit price 0 is returned													OK
		 *  - units-in-store 0 is returned												OK
		 */
		Article a = new Article( "", "", 0, 0 );
		assertEquals("", a.getId());
		assertEquals("", a.getDescription());
		assertEquals(0, a.getUnitPrice());
		assertEquals(0, a.getUnitsInStore());

	}

	/*
	 * Test constructor, Test special case with null and < 0 - arguments.
	 */
	@Test
	public void test003_NullArgumentConstructor() {
		/*
		 * insert tests for a constructor invocation: new Article( null, null, -1, -1 );
		 * to verify that:
		 *  - id null is returned														OK
		 *  - description "" is returned (null for description is not allowed)			OK
		 *  - unit price 0 is returned (negative unit prices are not allowed)			OK
		 *  - units-in-store 0 is returned (negative inventory is not allowed)			OK
		 */
		Article a = new Article( null, null, -1, -1 );
		assertNull(a.getId());
		assertEquals("", a.getDescription());
		assertEquals(0, a.getUnitPrice());
		assertEquals(0, a.getUnitsInStore());

	}

	@Test
	public void test010_SetDescription() {
		/*
		 * test method: setDescription( String descr );
		 * to verify that:
		 *  - String description is returned by getDescription()	(regular case)		OK
		 *  - "" is returned for setDescription( "" )				(corner case)		OK
		 *  - "" is returned for setDescription( null )				(irregular case)	OK
		 *  
		 *  Use the fixture object 'aToaster' that is created above.
		 */
		final String description = "Super Toaster Model XRC-2484698";
		aToaster.setDescription( description );			// test regular case
		assertEquals(description, aToaster.getDescription());

		aToaster.setDescription( "" );					// test corner case
		assertEquals("", aToaster.getDescription());

		aToaster.setDescription( null );				// test irregular case
		assertEquals("", aToaster.getDescription());
}

	@Test
	public void test011_SetUnitPrice() {
		/*
		 * test method: setUnitPrice( long price );
		 * to verify that:
		 *  - price = 100L is returned by getUnitPrice()			(regular case)		OK
		 *  - 0 is returned for setUnitPrice( 0 )					(corner case)		OK
		 *  - 0 is returned for setUnitPrice( Long.MAX_VALUE )		(corner case)		OK
		 *  - 0 is returned for setUnitPrice( -1 )					(irregular case)	OK
		 *  - 0 is returned for setUnitPrice( Long.MIN_VALUE )		(irregular case)	OK
		 *  
		 *  Use the fixture object 'aToaster' that is created above.
		 */
		final long price = 100L;
		aToaster.setUnitPrice( price );					// regular case
		assertEquals(price, aToaster.getUnitPrice());

		aToaster.setUnitPrice( 0 );						// corner case
		assertEquals(0, aToaster.getUnitPrice());
		
		aToaster.setUnitPrice( Long.MAX_VALUE );		// corner case
		assertEquals(0, aToaster.getUnitPrice());
		
		aToaster.setUnitPrice( -1 );					// irregular case
		assertEquals(0, aToaster.getUnitPrice());

		aToaster.setUnitPrice( Long.MIN_VALUE );		// irregular case
		assertEquals(0, aToaster.getUnitPrice());
		

	}

	@Test
	public void test012_SetUnitsInStore() {
		/*
		 * test method: setUnitsInStore( int number );
		 * to verify that:
		 *  - units = 100L is returned by getUnitsInStore()				(regular case)		OK
		 *  - 0 is returned for setUnitsInStore( 0 )					(corner case)		OK
		 *  - 0 is returned for setUnitsInStore( Integer.MAX_VALUE )	(corner case)		OK
		 *  - 0 is returned for setUnitsInStore( -1 )					(irregular case)	OK
		 *  - 0 is returned for setUnitsInStore( Integer.MIN_VALUE )	(irregular case)	OK
		 *  
		 *  Use the fixture object 'aToaster' that is created above.
		 */
		final int units = 100;
		aToaster.setUnitsInStore( units );					// regular case
		assertEquals(units, aToaster.getUnitsInStore());

		aToaster.setUnitsInStore( 0 );						// corner case
		assertEquals(0, aToaster.getUnitsInStore());

		aToaster.setUnitsInStore( Integer.MAX_VALUE );		// corner case
		assertEquals(0, aToaster.getUnitsInStore());

		aToaster.setUnitsInStore( -1 );						// irregular case
		assertEquals(0, aToaster.getUnitsInStore());

		aToaster.setUnitsInStore( Integer.MIN_VALUE );		// irregular case
		assertEquals(0, aToaster.getUnitsInStore());

	}

}
