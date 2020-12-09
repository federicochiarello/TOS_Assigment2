////////////////////////////////////////////////////////////////////
// FEDERICO CHIARELLO 1187598
////////////////////////////////////////////////////////////////////
package it.unipd.tos.business;

import java.util.List;
import java.time.LocalTime;

import it.unipd.tos.business.exception.TakeAwayBillException;
import it.unipd.tos.model.MenuItem;
import it.unipd.tos.model.User;
import it.unipd.tos.business.Randomizer;

public class BillCalculator implements TakeAwayBill {

    public Randomizer freeOrderRandomizer = new Randomizer();
    private double total;
    private double cheaperIceCreamPrice;
    private double totalDrinksPrice;
    private int numberOfIceCream;
    
    public double getOrderPrice(List<MenuItem> itemsOrdered, User user, LocalTime time) throws TakeAwayBillException {

        total = 0.0;
        cheaperIceCreamPrice = 0.0;
        totalDrinksPrice = 0.0;
        numberOfIceCream = 0;

        if (itemsOrdered == null) {
            throw new TakeAwayBillException("La lista è vuota");
        }
        
        if (itemsOrdered.contains(null)) {
            throw new TakeAwayBillException("La lista contiene un elemento vuoto");
        }
        
        if (itemsOrdered.size() > 30) {
            throw new TakeAwayBillException("La lista ha più di 30 elementi");
        }
        
        for (MenuItem i: itemsOrdered) {
            total += i.getPrice();
            
            if (i.getItemType() == MenuItem.ItemType.Gelato) {    
                numberOfIceCream++;
                if ((cheaperIceCreamPrice == 0.0) || (cheaperIceCreamPrice > i.getPrice())) {
                     cheaperIceCreamPrice = i.getPrice();
                }
            }
        
            if (i.getItemType() == MenuItem.ItemType.Bevanda) {
                totalDrinksPrice += i.getPrice();
            }
        }
        
        if (numberOfIceCream > 5) {
            total -= cheaperIceCreamPrice * 0.5;
        }
        
        if (total - totalDrinksPrice > 50) {
            total *= 0.9;
        }
        
        if (total < 10) {
            total += 0.5;
        }

        if (freeOrderRandomizer.checkOutcome(user,time)) {
            total = 0.0;
        }
        
        return total;

    }
}
