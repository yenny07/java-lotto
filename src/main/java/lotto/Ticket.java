package lotto;

import java.util.ArrayList;
import java.util.Collections;

public class Ticket extends ArrayList<Integer> {

    public Ticket() {
        createNumbers();
    }

    public Ticket(ArrayList<Integer> testInput) {
        this.addAll(testInput);
    }

    private void createNumbers() {

        for (int i = 1; i <= 45; i++) {
            this.add(i);
        }

        Collections.shuffle(this);
        this.subList(6, 45).clear();
        Collections.sort(this);
    }
}
