package lotto;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


class IOControllerTest {

    @Test
    void readBuyingMoneyTest() {
        InputStream in = new ByteArrayInputStream("abcde".getBytes());
        System.setIn(in);
        Assertions.assertThatThrownBy(IOController::readBuyingMoney)
                .isInstanceOf(IllegalArgumentException.class);

        in = new ByteArrayInputStream("12345".getBytes());
        System.setIn(in);
        Assertions.assertThatThrownBy(IOController::readBuyingMoney)
                .isInstanceOf(IllegalArgumentException.class);

        in = new ByteArrayInputStream("14000".getBytes());
        System.setIn(in);
        Assertions.assertThat(IOController.readBuyingMoney()).isEqualTo(14);
    }

    @Test
    void readWinningLottoTest() {
        InputStream in = new ByteArrayInputStream("1,2,3,4,5,6".getBytes());
        System.setIn(in);
        List<LottoNumber> lottonumbers = new ArrayList<LottoNumber>();
        for(int i =1; i <= 6; i++){
            lottonumbers.add(new LottoNumber(i));
        }
        Lotto lotto = new Lotto(lottonumbers);
        Assertions.assertThat(IOController.readWinningNumbers().calculateSameNumber(lotto)).isEqualTo(6);

        in = new ByteArrayInputStream("1,2,3,4,5".getBytes());
        System.setIn(in);
        Assertions.assertThatThrownBy(IOController::readBonusNumber)
                .isInstanceOf(IllegalArgumentException.class);

        in = new ByteArrayInputStream("1,2,3,4,5,46".getBytes());
        System.setIn(in);
        Assertions.assertThatThrownBy(IOController::readBonusNumber)
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void readBonusNumberTest() {
        InputStream in = new ByteArrayInputStream("7".getBytes());
        System.setIn(in);
        Assertions.assertThat(IOController.readBonusNumber()).isEqualTo(new LottoNumber(7));

        in = new ByteArrayInputStream("123".getBytes());
        System.setIn(in);
        Assertions.assertThatThrownBy(IOController::readBonusNumber).isInstanceOf(IllegalArgumentException.class);

        in = new ByteArrayInputStream("123".getBytes());
        System.setIn(in);
        Assertions.assertThatThrownBy(IOController::readBonusNumber).isInstanceOf(IllegalArgumentException.class);
    }


}
