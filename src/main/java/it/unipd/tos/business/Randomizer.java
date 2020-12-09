////////////////////////////////////////////////////////////////////
// FEDERICO CHIARELLO 1187598
////////////////////////////////////////////////////////////////////
package it.unipd.tos.business;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.time.LocalTime;

import it.unipd.tos.model.User;

public class Randomizer {
    
    public enum settedOutcome {
        Random, True, False;
    }
    
    private Random random;
    private int number;
    private List<String> list;
    private settedOutcome outcome;
    
    public Randomizer() {
        random = new Random();
        number = 10;
        list = new ArrayList<String>();
        outcome = settedOutcome.Random;
    }

    public boolean checkOutcome(User user, LocalTime time) {
        if (getRandomBoolean() && checkCandidate(user) && time.getHour() == 18 && number > 0) {
            list.add(user.getId());
            number--;
            return true;
        } else {
            return false;
        }
    }
    
    private boolean checkCandidate(User user) {
        if (user.getAge() < 18 && (!list.contains(user.getId()))) {
            return true;
        } else {
            return false;
        }
    }
    
    private boolean getRandomBoolean() {
        if (outcome == settedOutcome.Random) {
            return random.nextBoolean();
        } else if (outcome == settedOutcome.True) {
            return true;
        } else {
            return false;
        }
    }
    
    public void setOutcome(Randomizer.settedOutcome o) {
        outcome = o;
    }

}
