package lotto;

import java.util.*;

public class LottoMachine {
    int[] hits = new int[5];

    public double run(int ticketCount) throws Exception {
        List<Ticket> tickets = makeTickets(ticketCount);

        WinningNumber winningNumber = new WinningNumber();
        winningNumber.inputWinningNumber();
        winningNumber.inputBonusNumber();

        matcher(tickets, winningNumber);

        return calculateYield(ticketCount);
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

        for (Ticket ticket : tickets) {
            hitCount = ticket.stream()
                    .filter(winningNumber.getSixNumbers()::contains)
                    .count();
            calculateHits(winningNumber, hitCount, ticket);
        }
    }

    private void calculateHits(WinningNumber winningNumber, long hitCount, Ticket ticket) {
        if (hitCount >= 3) {
            Rank rank = Rank.getRank(hitCount, ticket, winningNumber.getBonusNumber());
            hits[rank.ordinal()] += 1;
        }
    }

    private double calculateYield(int ticketCount) {
        Rank[] ranks = Rank.values();
        int totalPrize = 0;

        for (Rank rank : ranks) {
            totalPrize += rank.getPrize() * hits[rank.ordinal()];
        }

        return (double) totalPrize / (ticketCount * 1000);
    }
}
