package lotto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LottoGame {
    public static final int LOTTO_PRICE = 1000;
    public static final int MAX_MONEY = 2_000_000_000;


    public void run(){
        try {
            LottoMaker lottoMaker = new LottoMaker();

            int buyingMoney = IOController.readBuyingMoney();
            List<Lotto> lottos = lottoMaker.buyLottos(buyingMoney);
            IOController.printLottos(lottos);

            Lotto winningNumbers = IOController.readWinningNumbers();
            LottoNumber bonusNumber = IOController.readBonusNumber();
            WinningLotto winningLotto = lottoMaker.readWinningLottos(winningNumbers, bonusNumber);

            this.showEarningRate(lottos,winningLotto,buyingMoney);
        }
        catch(IllegalArgumentException e){
            IOController.printExceptionMessage(e);
        }
    }

    private void showEarningRate(List<Lotto> lottos, WinningLotto winningLotto, int buyingMoney) {
        Integer[] allRankings = {0, 0, 0, 0, 0, 0};
        for (Lotto lotto : lottos) {
            int ranking = winningLotto.matchRank(lotto);
            allRankings[ranking]++;
        }

        float totalPrize = 0f;
        totalPrize += allRankings[Rank.FIRST.getValue()] * Rank.FIRST.getPrize();
        totalPrize += allRankings[Rank.SECOND.getValue()] * Rank.SECOND.getPrize();
        totalPrize += allRankings[Rank.THIRD.getValue()] * Rank.THIRD.getPrize();
        totalPrize += allRankings[Rank.FOURTH.getValue()] * Rank.FOURTH.getPrize();
        totalPrize += allRankings[Rank.FIFTH.getValue()] * Rank.FIFTH.getPrize();
        totalPrize += allRankings[Rank.MISS.getValue()] * Rank.MISS.getPrize();
        float earningRate = totalPrize / buyingMoney * 100;

        IOController.printPrizeResult(new ArrayList<>(Arrays.asList(allRankings)),earningRate);
    }
}
