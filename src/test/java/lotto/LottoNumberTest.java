package lotto;

import org.assertj.core.api.Assert;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LottoNumberTest {

    @Test
    void validateNumberRangeTest() {
        Assertions.assertThatThrownBy(()->new LottoNumber(0))
                .isInstanceOf(IllegalArgumentException.class);

        Assertions.assertThatThrownBy(()->new LottoNumber(46))
                .isInstanceOf(IllegalArgumentException.class);

    }
    @Test
    void testToString() {
        Assertions.assertThat(new LottoNumber(20).toString()).isEqualTo("20");
    }
}