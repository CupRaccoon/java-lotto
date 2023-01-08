package lotto;

import java.util.HashSet;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class WinningLotto {

    private final Lotto lotto;
    private final LottoNumber bonusNumber;

    public WinningLotto(Lotto lotto, LottoNumber bonusNumber){
        this.lotto = lotto;
        validateBonusNumber(bonusNumber);
        this.bonusNumber = bonusNumber;
    }

    public int calculateRank(Lotto lotto){
        int sameNumber = this.lotto.calculateSameNumber(lotto);
        boolean matchBonusNumber = lotto.contains(bonusNumber);
        if (sameNumber == 6) {
            return 1;
        }
        if (sameNumber == 5 && matchBonusNumber) {
            return 2;
        }
        if (sameNumber <= 5 && sameNumber >= 3) {
            return (8 - sameNumber);
        }
        return 0;
    }

    private void validateBonusNumber(LottoNumber bonusNumber){
        if (lotto.contains(bonusNumber)) {
            throw new IllegalArgumentException("로또 번호와 보너스 번호는 모두 달라야 합니다.");
        }
    }
}
