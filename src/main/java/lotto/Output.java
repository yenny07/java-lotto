package lotto;

public class Output {

    public void printResult(int[] hits, int inputMoney) {
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
}
