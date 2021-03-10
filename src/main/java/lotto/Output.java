package lotto;

import java.util.List;

public class Output {
    public static void printResult(int[] hits) {
        Rank[] ranks = Rank.values();

        System.out.println("당첨 통계");
        System.out.println("========");

        for (Rank rank : ranks) {
            printRankElement(rank.getHit(), rank.getPrize(), hits[rank.ordinal()]);
        }
    }

    public static void printRankElement(int hit, int prize, int hitCount) {
        System.out.println(hit + "개 일치 (" + prize + "원) -" + hitCount + "개");
    }

    public static void printYield(double yield) {
        System.out.printf("총 수익률은 %.2f입니다.", yield);
    }

    public static void printTicketCount(int ticketCount) {
        System.out.println(ticketCount + "개를 구매했습니다.");
    }

    public static void printTickets(List<Ticket> tickets) {
        for (Ticket ticket : tickets) {
            System.out.println(ticket);
        }
    }
}
