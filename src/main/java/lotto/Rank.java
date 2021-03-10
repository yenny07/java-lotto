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

    public static Rank getRank(long hitCount, Ticket ticket, int bonusNumber) {
        switch ((int) hitCount) {
            case 3:
                return Rank.THREE;
            case 4:
                return Rank.FOUR;
            case 5:
                return (ticket.contains(bonusNumber)) ? Rank.BONUS : Rank.FIVE;
            case 6:
                return Rank.SIX;
            default:
                return null;
        }
    }
}
