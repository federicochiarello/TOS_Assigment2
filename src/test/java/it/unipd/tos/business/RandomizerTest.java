package it.unipd.tos.business;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import it.unipd.tos.business.Randomizer.settedOutcome;
import it.unipd.tos.model.User;
import java.time.LocalTime;

public class RandomizerTest {

    private User user;
    private LocalTime time;
    private Randomizer rand;
	
    @Before
    public void setup() {
        user = new User("Luca","Toni",14);
        time = LocalTime.of(18,15);
        rand = new Randomizer();
    }

    @Test
    public void checkOutcome_UserWinFreeOrder() {
        rand.setOutcome(settedOutcome.True);
        assertTrue(rand.checkOutcome(user,time));
    }
    
    @Test
    public void checkOutcome_UserDoesNotWinFreeOrder() {
        rand.setOutcome(settedOutcome.False);
        assertFalse(rand.checkOutcome(user,time));
    }

    @Test
    public void checkOutcome_OverAgeLimitUser_DoesNotWinFreeOrder() {
    	User overAgeUser = new User("Francesco","Totti",92);
        rand.setOutcome(settedOutcome.True);
        assertFalse(rand.checkOutcome(overAgeUser,time));
    }
    
    @Test
    public void checkOutcome_OutOfTimeRange_DoesNotWinFreeOrder() {
    	LocalTime outOfTime = LocalTime.of(19,15);
        rand.setOutcome(settedOutcome.True);
        assertFalse(rand.checkOutcome(user,outOfTime));
    }
    
    @Test
    public void checkOutcome_LowerBoundTimeRange_WinFreeOrder() {
    	LocalTime lowerRangeTime = LocalTime.of(18,00);
        rand.setOutcome(settedOutcome.True);
        assertTrue(rand.checkOutcome(user,lowerRangeTime));
    }
    
    @Test
    public void checkOutcome_UpperBoundTimeRange_DoesNotWinFreeOrder() {
    	LocalTime upperRangeTime = LocalTime.of(19,00);
        rand.setOutcome(settedOutcome.True);
        assertFalse(rand.checkOutcome(user,upperRangeTime));
    }
    
    @Test
    public void checkOutcome_FreeOrderSlotsFinished_DoesNotWinFreeOrder() {
    	rand.setOutcome(settedOutcome.True);
        for(int i=0; i<=10; i++) {
        	User tmpUser = new User("Salvo","Montalbano",12);
        	rand.checkOutcome(tmpUser,time);
        }
        rand.setOutcome(settedOutcome.True);
        assertFalse(rand.checkOutcome(user,time));
        rand.setOutcome(settedOutcome.Random);
        assertFalse(rand.checkOutcome(user,time));
    }
    
    @Test
    public void checkOutcome_SameUser_RejectedSecondAttempt() {
        rand.setOutcome(settedOutcome.True);
        rand.checkOutcome(user,time);
        assertFalse(rand.checkOutcome(user,time));
    }
    
}
