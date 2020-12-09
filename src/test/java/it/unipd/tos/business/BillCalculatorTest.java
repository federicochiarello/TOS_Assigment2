package it.unipd.tos.business;

import java.util.List;
import java.util.ArrayList;
import java.time.LocalTime;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import it.unipd.tos.business.exception.TakeAwayBillException;
import it.unipd.tos.model.MenuItem;
import it.unipd.tos.model.User;

public class BillCalculatorTest {
	
    private BillCalculator calculator;
    private List<MenuItem> list;
    private User user;
    private LocalTime time;
    private double total;
	
    @Before
    public void setup() {
        calculator = new BillCalculator();
        list = new ArrayList<MenuItem>();
        user = new User("Luca","Toni",43);
        time = LocalTime.of(10,18);
        total = 0.0;
    }

    @Test(expected = TakeAwayBillException.class) 
    public void getOrderPrice_EmptyList_ExceptionThrown() throws TakeAwayBillException {
        list = null;
        total = calculator.getOrderPrice(list,user,time);
    }
    
    @Test
    public void getOrderPrice_EmptyList_ControlOfErrorMessage() throws TakeAwayBillException {
        list = null;
        try {
            total = calculator.getOrderPrice(list,user,time);
        } catch (TakeAwayBillException exc) {
            assertEquals("TakeAwayBillException: La lista è vuota", exc.toString());
        }
    }
    
    @Test(expected = TakeAwayBillException.class) 
    public void getOrderPrice_NullItem_ExceptionThrown() throws TakeAwayBillException {
        list.add(null);
        list.add(new MenuItem(MenuItem.ItemType.Budino,"Pinguino",9.0));
        total = calculator.getOrderPrice(list,user,time);
    }

    @Test
    public void getOrderPrice_3DifferentItems_CalculatedTotalPriceWithoutSalesOrCommissions() throws TakeAwayBillException {
        list.add(new MenuItem(MenuItem.ItemType.Budino, "Biancaneve", 5.0));
        list.add(new MenuItem(MenuItem.ItemType.Gelato, "Coppa Del Nonno", 3.5));
        list.add(new MenuItem(MenuItem.ItemType.Bevanda, "Grappino", 2.5));
        total = calculator.getOrderPrice(list,user,time);
        assertEquals(11.0,total,0.0);
    }
}
