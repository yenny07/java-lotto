package lotto;

import java.util.*;
import java.util.stream.Collectors;

public class LottoMachine {

    public void run() {

        try {
            int ticketCount = buyTickets();
            List<Ticket> tickets = makeTickets(ticketCount);

            WinningNumber winningNumber = new WinningNumber();
            winningNumber.inputWinningNumber();
            winningNumber.inputBonusNumber();

            int[] hits = matcher(tickets, winningNumber);
            printResult(hits, ticketCount * 1000);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    private int buyTickets() {
        Scanner scanner = new Scanner(System.in);
        String inputMoney = scanner.nextLine();
        int ticketCount = Integer.parseInt(inputMoney) / 1000;
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

    private int[] matcher(List<Ticket> tickets, WinningNumber winningNumber) {
        int[] hits = new int[5];
        long hitCount = 0;
        Rank rank = null;

        for (List<Integer> ticket : tickets) {
            hitCount = ticket.stream()
                    .filter(winningNumber::contains)
                    .count();
            if (hitCount >= 3) {
                rank = getRank(hitCount, ticket, winningNumber.getBonusNumber());
                hits[rank.ordinal()] += 1;
            }
        }

        return hits;
    }

    private Rank getRank(long hitCount, List<Integer> ticket, int bonusNumber) {
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

    private void printResult(int[] hits, int inputMoney) {
        Rank[] ranks = Rank.values();
        int totalPrize = 0;

        System.out.println("당첨 통계");
        System.out.println("========");
        for (Rank rank : ranks) {
            System.out.println(rank.getHit() + "개 일치 (" + rank.getPrize() + "원) - " + hits[rank.ordinal()] + "개");
            totalPrize += rank.getPrize() * hits[rank.ordinal()];
        }

        System.out.println("총 수익률은 " + (totalPrize / inputMoney) + "%입니다.");
    }

}
