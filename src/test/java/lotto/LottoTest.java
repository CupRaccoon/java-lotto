package lotto;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoTest {
    @DisplayName("로또 번호의 개수가 6개가 넘어가면 예외가 발생한다.")
    @Test
    void createLottoByOverSize() {
        List<LottoNumber> lottoNumbers = List.of(1, 2, 3, 4, 5, 6, 7).stream()
                .map(LottoNumber::new).collect(Collectors.toList());
        assertThatThrownBy(() -> new Lotto(lottoNumbers))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호에 중복된 숫자가 있으면 예외가 발생한다.")
    @Test
    void createLottoByDuplicatedNumber() {
        List<LottoNumber> lottoNumbers = List.of(1, 2, 3, 4, 5, 5).stream()
                .map(LottoNumber::new).collect(Collectors.toList());
        assertThatThrownBy(() -> new Lotto(lottoNumbers))
                .isInstanceOf(IllegalArgumentException.class);
    }


    @Test
    void calculateSameNumberTest() {
        Lotto lotto1 = new Lotto(List.of(1, 2, 3, 4, 5, 6).stream()
                .map(LottoNumber::new).collect(Collectors.toList()));
        Lotto lotto2 = new Lotto(List.of(4, 5, 6, 7, 8, 9).stream()
                .map(LottoNumber::new).collect(Collectors.toList()));
        Assertions.assertThat(lotto1.calculateSameNumber(lotto2)).isEqualTo(3);

    }

    @Test
    void containsTest() {
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6).stream()
                .map(LottoNumber::new).collect(Collectors.toList()));
        Assertions.assertThat(lotto.contains(new LottoNumber(3))).isTrue();
        Assertions.assertThat(lotto.contains(new LottoNumber(7))).isFalse();
    }
}
