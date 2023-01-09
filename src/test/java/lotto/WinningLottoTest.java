package lotto;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class WinningLottoTest {

    @Test
    void calculateRankTest() {
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6).stream()
                .map(LottoNumber::new).collect(Collectors.toList()));
        WinningLotto winningLotto = new WinningLotto(lotto,new LottoNumber(7));

        Lotto rankOneLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6).stream()
                .map(LottoNumber::new).collect(Collectors.toList()));
        Lotto rankTwoLotto = new Lotto(List.of(1, 2, 3, 4, 5, 7).stream()
                .map(LottoNumber::new).collect(Collectors.toList()));
        Lotto rankThreeLotto = new Lotto(List.of(1, 2, 3, 4, 5, 10).stream()
                .map(LottoNumber::new).collect(Collectors.toList()));
        Lotto rankZeroLotto = new Lotto(List.of(1, 2, 10, 11, 12, 13).stream()
                .map(LottoNumber::new).collect(Collectors.toList()));

        assertThat(winningLotto.calculateRank(rankOneLotto)).isEqualTo(1);
        assertThat(winningLotto.calculateRank(rankTwoLotto)).isEqualTo(2);
        assertThat(winningLotto.calculateRank(rankThreeLotto)).isEqualTo(3);
        assertThat(winningLotto.calculateRank(rankZeroLotto)).isEqualTo(0);


    }

    @Test
    void createByDuplicatedBonusNumber() {
        List<LottoNumber> lottoNumbers = List.of(1, 2, 3, 4, 5, 6).stream()
                .map(LottoNumber::new).collect(Collectors.toList());
        assertThatThrownBy(() ->
                new WinningLotto(new Lotto(lottoNumbers),new LottoNumber(6))).isInstanceOf(IllegalArgumentException.class);
    }
    @Test
    void createByNotRangeBonusNumber() {
        List<LottoNumber> lottoNumbers = List.of(1, 2, 3, 4, 5, 6).stream()
                .map(LottoNumber::new).collect(Collectors.toList());
        assertThatThrownBy(() -> new WinningLotto(new Lotto(lottoNumbers),new LottoNumber(46)))
                .isInstanceOf(IllegalArgumentException.class);
    }


}