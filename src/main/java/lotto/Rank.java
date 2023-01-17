package lotto;

public enum Rank {
    FIRST(1,2_000_000_000),
    SECOND(2,30_000_000),
    THIRD(3,1_500_000),
    FOURTH(4,50_000),
    FIFTH(5,5_000),
    MISS(0,0);

    private final int prize;
    Rank(int value, int prize){
        this.prize = prize;
    }

    public int getPrize() {
        return prize;
    }
}
