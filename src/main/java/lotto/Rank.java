package lotto;

public enum Rank {
    THREE(3, 5000),
    FOUR(4, 50000),
    FIVE(5, 1500000),
    BONUS(5, 30000000),
    SIX(6, 2000000000);

    private final int hit;
    private final int prize;

    Rank(int hit, int prize) {
        this.hit = hit;
        this.prize = prize;
    }

    public int getHit() {
        return hit;
    }

    public int getPrize() {
        return prize;
    }

    public String getString() {
        return hit + "개 일치 (" + prize + "원) - ";
    }
}
