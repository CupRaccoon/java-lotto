package lotto;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LottoMaker {

    List<Lotto> buyLottos(int buyingMoney){
        int numberOfLottos = buyingMoney/LottoGame.LOTTO_PRICE;

        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < numberOfLottos; i++) {
            List<LottoNumber> numbers = Randoms.pickUniqueNumbersInRange(1, 45, 6)
                    .stream().map(LottoNumber::new).collect(Collectors.toList());
            Lotto lotto = new Lotto(numbers);
            lottos.add(lotto);
        }
        return lottos;
    }

    public WinningLotto readWinningLottos(Lotto winningNumbers, LottoNumber bonusNumber){
        return new WinningLotto(winningNumbers,bonusNumber);
    }
}
