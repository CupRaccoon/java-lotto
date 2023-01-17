package lotto;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.*;
import java.util.stream.Collectors;

public class LottoGame {
    public static final int LOTTO_PRICE = 1000;
    public static final int MAX_MONEY = 2_000_000_000;


    public void run(){
        try {
            List<Lotto> lottos = this.buyLottos();

            WinningLotto winningLotto = this.readWinningLottos();

            this.showEarningRate(lottos,winningLotto);
        }
        catch(IllegalArgumentException e){
            IOController.printExceptionMessage(e);
        }
    }

    List<Lotto> buyLottos(){
        int buyingMoney = IOController.readBuyingMoney();
        int numberOfLottos = buyingMoney/LottoGame.LOTTO_PRICE;

        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < numberOfLottos; i++) {
            List<LottoNumber> numbers = Randoms.pickUniqueNumbersInRange(1, 45, 6)
                    .stream().map(LottoNumber::new).collect(Collectors.toList());
            Lotto lotto = new Lotto(numbers);
            lottos.add(lotto);
        }
        IOController.printLottos(lottos);
        return lottos;
    }

    WinningLotto readWinningLottos(){
        Lotto winningNumbers = IOController.readWinningNumbers();
        LottoNumber bonusNumber = IOController.readBonusNumber();
        return new WinningLotto(winningNumbers,bonusNumber);

    }
    private void showEarningRate(List<Lotto> lottos, WinningLotto winningLotto) {
        final Map<Rank, Integer> allRankings = Arrays.stream(Rank.values())
                .collect(Collectors.toMap(key -> key, value -> 0));
        
        
        for (Lotto lotto : lottos) {
            final Rank rank = winningLotto.matchRank(lotto);
            allRankings.merge(rank,1,Integer::sum);
        }

        float totalPrize = 0f;
        totalPrize += allRankings.get(Rank.FIRST) * Rank.FIRST.getPrize();
        totalPrize += allRankings.get(Rank.SECOND) * Rank.SECOND.getPrize();
        totalPrize += allRankings.get(Rank.THIRD) * Rank.THIRD.getPrize();
        totalPrize += allRankings.get(Rank.FOURTH) * Rank.FOURTH.getPrize();
        totalPrize += allRankings.get(Rank.FIFTH) * Rank.FIFTH.getPrize();
        totalPrize += allRankings.get(Rank.MISS) * Rank.MISS.getPrize();
        int buyingMoney = lottos.size() * LOTTO_PRICE;
        float earningRate = totalPrize / buyingMoney * 100;

        IOController.printPrizeResult(allRankings,earningRate);
    }
}
