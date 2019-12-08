////////////////////////////////////////////////////////////////////
// IVAN FURLAN 1161622
////////////////////////////////////////////////////////////////////
package it.unipd.tos;

import java.util.List;
import java.util.ArrayList;

import it.unipd.tos.business.exception.TakeAwayBillException;
import it.unipd.tos.business.TakeAwayBill;
import it.unipd.tos.business.TakeAwayBillImpl;
import it.unipd.tos.model.MenuItem;
import it.unipd.tos.model.ItemType;

import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import static org.junit.Assert.assertEquals;

public class SandwichShopTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void MenuItem_NomeCorretto(){
        MenuItem test = new MenuItem(ItemType.PANINO, "Panino", 3.50);

        assertEquals("Panino", test.getNome());

    }

    @Test
    public void SandwichShop_TotaleCorretto(){
        List<MenuItem> itemsOrdered = new ArrayList<MenuItem>();
        TakeAwayBill test = new TakeAwayBillImpl();

        itemsOrdered.add(new MenuItem(ItemType.PANINO, "Panino", 3.50));
        itemsOrdered.add(new MenuItem(ItemType.FRITTO, "Fritto", 2.25));
        itemsOrdered.add(new MenuItem(ItemType.BEVANDA, "Bevanda", 2.10));
        itemsOrdered.add(new MenuItem(ItemType.PANINO, "Panino", 6.00));

        try{
            assertEquals(13.85, test.getOrderPrice(itemsOrdered), 0.0);
        } 
        catch (TakeAwayBillException exc){
            exc.getMessage();
        }
    }

    @Test
    public void SandwichShop_ScontoPiuDi5Panini_SoloPanini(){
        List<MenuItem> itemsOrdered = new ArrayList<MenuItem>();
        TakeAwayBill test = new TakeAwayBillImpl();

        itemsOrdered.add(new MenuItem(ItemType.PANINO, "Panino", 3.50));
        itemsOrdered.add(new MenuItem(ItemType.PANINO, "Panino", 6.00));
        itemsOrdered.add(new MenuItem(ItemType.PANINO, "Panino", 5.00));
        itemsOrdered.add(new MenuItem(ItemType.PANINO, "Panino", 4.00));
        itemsOrdered.add(new MenuItem(ItemType.PANINO, "Panino", 7.00));

        try{
            assertEquals(23.75, test.getOrderPrice(itemsOrdered), 0.0);
        } 
        catch (TakeAwayBillException exc){
            exc.getMessage();
        }

    }

    @Test
    public void SandwichShop_ScontoPiuDi5Panini_NonSoloPanini(){
        List<MenuItem> itemsOrdered = new ArrayList<MenuItem>();
        TakeAwayBill test = new TakeAwayBillImpl();

        itemsOrdered.add(new MenuItem(ItemType.PANINO, "Panino", 3.50));
        itemsOrdered.add(new MenuItem(ItemType.PANINO, "Panino", 6.00));
        itemsOrdered.add(new MenuItem(ItemType.FRITTO, "Fritto", 2.25));
        itemsOrdered.add(new MenuItem(ItemType.PANINO, "Panino", 5.00));
        itemsOrdered.add(new MenuItem(ItemType.PANINO, "Panino", 4.00));
        itemsOrdered.add(new MenuItem(ItemType.BEVANDA, "Bevanda", 2.10));
        itemsOrdered.add(new MenuItem(ItemType.PANINO, "Panino", 7.00));

        try{
            assertEquals(28.1, test.getOrderPrice(itemsOrdered), 0.0);
        } 
        catch (TakeAwayBillException exc){
            exc.getMessage();
        }
    }

    @Test
    public void SandwichShop_ScontoPiuDi50Euro(){
        List<MenuItem> itemsOrdered = new ArrayList<MenuItem>();
        TakeAwayBill test = new TakeAwayBillImpl();

        itemsOrdered.add(new MenuItem(ItemType.PANINO, "Panino", 10));
        itemsOrdered.add(new MenuItem(ItemType.PANINO, "Panino", 10.50));
        itemsOrdered.add(new MenuItem(ItemType.FRITTO, "Fritto", 9.50));
        itemsOrdered.add(new MenuItem(ItemType.BEVANDA, "Bevanda", 20));

        try{
            assertEquals(45, test.getOrderPrice(itemsOrdered), 0.0);
        } 
        catch (TakeAwayBillException exc){
            exc.getMessage();
        }
    }

    @Test
    public void SandwichShop_Limite30Elementi() throws TakeAwayBillException{
        List<MenuItem> itemsOrdered = new ArrayList<MenuItem>();
        TakeAwayBill test = new TakeAwayBillImpl();

        for(int i=0; i<10; i++) {
            itemsOrdered.add(new MenuItem(ItemType.PANINO, "Panino", 10));
            itemsOrdered.add(new MenuItem(ItemType.FRITTO, "Fritto", 9.50));
            itemsOrdered.add(new MenuItem(ItemType.BEVANDA, "Bevanda", 20));
        }
        itemsOrdered.add(new MenuItem(ItemType.PANINO, "Panino", 5));

        thrown.expect(TakeAwayBillException.class);
        thrown.expectMessage("Non si può acquistare più di 30 elementi.");   

        test.getOrderPrice(itemsOrdered);
    }

    @Test
    public void SandwichShop_CommissioneSotto10Euro(){
        List<MenuItem> itemsOrdered = new ArrayList<MenuItem>();
        TakeAwayBill test = new TakeAwayBillImpl();

        itemsOrdered.add(new MenuItem(ItemType.PANINO, "Panino", 1));
        itemsOrdered.add(new MenuItem(ItemType.FRITTO, "Fritto", 8));
        itemsOrdered.add(new MenuItem(ItemType.BEVANDA, "Bevanda", 0.99));

        try{
            assertEquals(10.49, test.getOrderPrice(itemsOrdered), 0.0);
        } 
        catch (TakeAwayBillException exc){
            exc.getMessage();
        }
    }

}
