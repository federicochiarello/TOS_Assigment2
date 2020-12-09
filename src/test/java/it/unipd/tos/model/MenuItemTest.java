package it.unipd.tos.model;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class MenuItemTest {

    private MenuItem budino;
    private String nomeBudino = "Pinguino";
    private double costoBudino = 3.0;
    
    @Before
    public void setUp() {
        budino = new MenuItem(MenuItem.ItemType.Budino, nomeBudino, costoBudino);
    }
  
    @Test
    public void GetItemType_IsEqual() {
        assertEquals(MenuItem.ItemType.Budino, budino.getItemType());
    }

    @Test
    public void GetName_IsEqual() {
        assertEquals(nomeBudino, budino.getName());
    }

    @Test
    public void GetPrice_IsEqual() {
        assertEquals(costoBudino, budino.getPrice(), 0.0);
    }
}
