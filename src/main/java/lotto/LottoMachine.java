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

    public int buyTickets() throws RuntimeException {

        Input input = new Input();
        int inputMoney = input.inputInt("금액을 입력하세요.");

        if (inputMoney < 1000) {
            throw new RuntimeException("한장도 사지 못하는 금액입니다.");
        }

        int ticketCount = inputMoney / 1000;
        System.out.println(ticketCount + "개를 구매했습니다.");

        return ticketCount;
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
