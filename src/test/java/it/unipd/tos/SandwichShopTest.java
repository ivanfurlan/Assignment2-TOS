package it.unipd.tos;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class SandwichShopTest 
    extends TestCase
{
    
	public SandwichShopTest( String testName )
    {
        super( testName );
    }
	
    public static Test suite()
    {
        return new TestSuite( SandwichShopTest.class );
    }

    
    public void testSandwichShop()
    {
        assertTrue( true );
    }
}
