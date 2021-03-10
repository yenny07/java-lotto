package lotto;

import java.util.*;

public class LottoMachine {
    int[] hits = new int[5];

    public void run(int ticketCount) throws Exception {
        List<Ticket> tickets = makeTickets(ticketCount);

        WinningNumber winningNumber = new WinningNumber();
        winningNumber.inputWinningNumber();
        winningNumber.inputBonusNumber();

        matcher(tickets, winningNumber);
    }

    private List<Ticket> makeTickets(int ticketCount) {
        List<Ticket> tickets = new ArrayList<>();

        for (int i = 0; i < ticketCount; i++) {
            tickets.add(new Ticket());
            System.out.println(tickets.get(i));
        }

        return tickets;
    }

    private void matcher(List<Ticket> tickets, WinningNumber winningNumber) {
        long hitCount = 0;
        Rank rank = null;

        for (Ticket ticket : tickets) {
            hitCount = ticket.stream()
                    .filter(winningNumber::contains)
                    .count();
            if (hitCount >= 3) {
                rank.getRank(hitCount, ticket, winningNumber.getBonusNumber());
                hits[rank.ordinal()] += 1;
            }
        }
    }


}
