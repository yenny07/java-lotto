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

    public Rank getRank(long hitCount, Ticket ticket, int bonusNumber) {
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
