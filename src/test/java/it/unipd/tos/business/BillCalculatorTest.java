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
    
    @Test(expected = TakeAwayBillException.class) 
    public void getOrderPrice_MoreThen30Items_ExceptionThrown() throws TakeAwayBillException {
        for(int i=0; i<31; i++) {
            list.add(new MenuItem(MenuItem.ItemType.Gelato,"Cioccolato",1.0));
	    }
        total = calculator.getOrderPrice(list,user,time);
    } 
    
    @Test
    public void getOrderPrice_MoreThen30Items_ControlOfErrorMessage() throws TakeAwayBillException {
        for(int i=0; i<31; i++) {
            list.add(new MenuItem(MenuItem.ItemType.Gelato,"Cioccolato",1.0));
	    }
	    try {
            total = calculator.getOrderPrice(list,user,time);
        } catch (TakeAwayBillException exc) {
            assertEquals("TakeAwayBillException: La lista ha più di 30 elementi", exc.toString());
        }
    }   
    
    @Test
    public void getOrderPrice_30ItemsList_CalculatedTotalPrice() throws TakeAwayBillException {
        for(int i=0; i<30; i++) {
            list.add(new MenuItem(MenuItem.ItemType.Budino,"Cioccolato",1.0));
	}
        total = calculator.getOrderPrice(list,user,time);
        assertEquals(30.0,total,0.0);
    } 

    @Test
    public void getOrderPrice_3DifferentItems_CalculatedTotalPriceWithoutSalesOrCommissions() throws TakeAwayBillException {
        list.add(new MenuItem(MenuItem.ItemType.Budino, "Biancaneve", 5.0));
        list.add(new MenuItem(MenuItem.ItemType.Gelato, "Coppa Del Nonno", 3.5));
        list.add(new MenuItem(MenuItem.ItemType.Bevanda, "Grappino", 2.5));
        total = calculator.getOrderPrice(list,user,time);
        assertEquals(11.0,total,0.0);
    }  

    @Test
    public void getOrderPrice_5IceCream_CalculatedWithoutDiscount() throws TakeAwayBillException {
        list.add(new MenuItem(MenuItem.ItemType.Gelato,"Vaniglia",1.0));
        list.add(new MenuItem(MenuItem.ItemType.Gelato,"Limone",2.0));
        list.add(new MenuItem(MenuItem.ItemType.Gelato,"Cioccolato",2.0));
        list.add(new MenuItem(MenuItem.ItemType.Gelato,"Pistacchio",2.0));
        list.add(new MenuItem(MenuItem.ItemType.Gelato,"Fragola",4.0));
        total = calculator.getOrderPrice(list,user,time);
        assertEquals(11,total,0.0);
    }
    
    @Test
    public void getOrderPrice_6IceCream_CalculatedWith50Discount() throws TakeAwayBillException {
        list.add(new MenuItem(MenuItem.ItemType.Gelato,"Vaniglia",2.0));
        list.add(new MenuItem(MenuItem.ItemType.Gelato,"Limone",1.0));
        list.add(new MenuItem(MenuItem.ItemType.Gelato,"Cioccolato",2.0));
        list.add(new MenuItem(MenuItem.ItemType.Gelato,"Pistacchio",2.0));
        list.add(new MenuItem(MenuItem.ItemType.Gelato,"Fragola",2.0));
        list.add(new MenuItem(MenuItem.ItemType.Gelato,"Nocciola",2.0));
        total = calculator.getOrderPrice(list,user,time);
        assertEquals(10.5,total,0.0);
    }
    
    @Test 
    public void getOrderPrice_50EuroInIceCreamsAndBudini_CalculatedWithoutDiscount() throws TakeAwayBillException {
        list.add(new MenuItem(MenuItem.ItemType.Budino,"Pinguino",25.0));
        list.add(new MenuItem(MenuItem.ItemType.Gelato,"Coppa Nafta",25.0));
        list.add(new MenuItem(MenuItem.ItemType.Bevanda,"Mirto",1.0));
        total = calculator.getOrderPrice(list,user,time);
        assertEquals(51.0,total,0.0);
    } 

    @Test 
    public void getOrderPrice_MoreThan50EuroInIceCreamsAndBudini_CalculatedWithDiscount() throws TakeAwayBillException {
        list.add(new MenuItem(MenuItem.ItemType.Budino,"Pinguino",29.0));
        list.add(new MenuItem(MenuItem.ItemType.Gelato,"Coppa Nafta",30.0));
        list.add(new MenuItem(MenuItem.ItemType.Bevanda,"Mirto",1.0));
        total = calculator.getOrderPrice(list,user,time);
        assertEquals(54.0,total,0.0);
    }

    @Test
    public void getOrderPrice_LessThan10Euro_CommissionApplied() throws TakeAwayBillException {
        list.add(new MenuItem(MenuItem.ItemType.Bevanda,"Acqua del rubinetto",2.5));
        total = calculator.getOrderPrice(list,user,time);
        assertEquals(3.0,total,0.0);
    }

    @Test
    public void getOrderPrice_UserWinFreeOrder_TotalEqualTo0() throws TakeAwayBillException {
    	User minorUser = new User("Marco","Rossi",15);
        list.add(new MenuItem(MenuItem.ItemType.Bevanda,"Acqua ragia",11.0));
        LocalTime discountTime = LocalTime.of(18,18);
        calculator.freeOrderRandomizer.setOutcome(Randomizer.settedOutcome.True);
        total = calculator.getOrderPrice(list,minorUser,discountTime);
        assertEquals(0.0,total,0.0);
    }

}
