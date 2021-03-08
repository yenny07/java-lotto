package lotto;

import java.util.*;
import java.util.stream.Collectors;

public class LottoMachine {
    int[] hits = new int[0];

    public void run(int ticketCount) {
        List<Ticket> tickets = makeTickets(ticketCount);

        WinningNumber winningNumber = new WinningNumber();
        winningNumber.inputWinningNumber();
        winningNumber.inputBonusNumber();

        try {
            matcher(tickets, winningNumber);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public int buyTickets() {
        Input input = new Input();
        int inputMoney = input.inputInt("금액을 입력하세요.");
        int ticketCount = inputMoney / 1000;
        System.out.println(ticketCount + "개를 구매했습니다.");

        return ticketCount;
    }

    private List<Ticket> makeTickets(int ticketCount) {
        List<Ticket> tickets = new ArrayList<>();

        for (int i = 0; i < ticketCount; i++) {
            tickets.add(new Ticket());
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
                rank = getRank(hitCount, ticket, winningNumber.getBonusNumber());
                hits[rank.ordinal()] += 1;
            }
        }
    }

    private Rank getRank(long hitCount, Ticket ticket, int bonusNumber) {
        Rank rank = null;

        switch ((int) hitCount) {
            case 3:
                rank = Rank.THREE;
                break;
            case 4:
                rank = Rank.FOUR;
                break;
            case 5:
                rank = (ticket.contains(bonusNumber)) ? Rank.BONUS : Rank.FIVE;
                break;
            case 6:
                rank = Rank.SIX;
                break;
            default:
                rank = null;
        }

        return rank;
    }
}
