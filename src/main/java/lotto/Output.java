package lotto;

public class Output {

    public void printResult(int[] hits, int inputMoney) {
        Rank[] ranks = Rank.values();
        int totalPrize = 0;

        System.out.println("당첨 통계");
        System.out.println("========");
        for (Rank rank : ranks) {
            System.out.println(rank.getString() + hits[rank.ordinal()] + "개");
            totalPrize += rank.getPrize() * hits[rank.ordinal()];
        }

        System.out.println("총 수익률은 " + (totalPrize / inputMoney) + "%입니다.");
    }

}
