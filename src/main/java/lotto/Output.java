package lotto;

public class Output {

    public void printResult(int[] hits) {
        Rank[] ranks = Rank.values();
        int totalPrize = 0;

        System.out.println("당첨 통계");
        System.out.println("========");

        for (Rank rank : ranks) {
            printRankElement(rank.getHit(), rank.getPrize(), hits[rank.ordinal()]);
        }
    }

    public void printRankElement(int hit, int prize, int hitCount) {
        System.out.println(hit + "개 일치 (" + prize + "원) -" + hitCount + "개");
    }

    public void printYield(double yield) {
        System.out.printf("총 수익률은 %.2f입니다.", yield);
    }
}
