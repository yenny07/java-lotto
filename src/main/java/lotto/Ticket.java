package lotto;

import java.util.ArrayList;
import java.util.Collections;

public class Ticket extends ArrayList<Integer>{

    public Ticket() {
        createNumbers();
    }

    private void createNumbers() {

        for (int i = 1; i <= 45; i++) {
            this.add(i);
        }

        Collections.shuffle(this); // 됨
        for (int i = 6; i <= 45; i++) {
            this.remove(i);
        }
        Collections.sort(this); // 됨

    }
}
