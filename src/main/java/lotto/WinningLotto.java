package lotto;


public class WinningLotto {

    private final Lotto lotto;
    private final LottoNumber bonusNumber;

    public WinningLotto(Lotto lotto, LottoNumber bonusNumber){
        this.lotto = lotto;
        validateBonusNumber(bonusNumber);
        this.bonusNumber = bonusNumber;
    }

    public Rank matchRank(Lotto lotto){
        int sameNumber = this.lotto.hitCount(lotto);
        boolean matchBonusNumber = lotto.contains(bonusNumber);
        if (sameNumber == 6) {
            return Rank.FIRST;
        }
        if (sameNumber == 5 && matchBonusNumber) {
            return Rank.SECOND;
        }
        if (sameNumber == 5){
            return Rank.THIRD;
        }
        if(sameNumber == 4){
            return Rank.FOURTH;
        }
        if(sameNumber == 3){
            return Rank.FIFTH;
        }
        return Rank.MISS;
    }

    private void validateBonusNumber(LottoNumber bonusNumber){
        if (lotto.contains(bonusNumber)) {
            throw new IllegalArgumentException("로또 번호와 보너스 번호는 모두 달라야 합니다.");
        }
    }
}
