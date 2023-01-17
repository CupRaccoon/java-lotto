package lotto;

import camp.nextstep.edu.missionutils.Console;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class IOController {

    public static int readBuyingMoney() {
        System.out.println("구입금액을 입력해 주세요.");
        String input = Console.readLine();
        int buyingMoney;
        try { buyingMoney = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("로또 구입 금액은 숫자입니다.");
        }
        if(buyingMoney < 0 || buyingMoney > LottoGame.MAX_MONEY){
            throw new IllegalArgumentException("로또 구입 금액은 0원이상 2,000,000,000원 이하입니다.");
        }
        if (buyingMoney % LottoGame.LOTTO_PRICE != 0) {
            throw new IllegalArgumentException("로또 구입 금액은 1000원 단위입니다.");
        }
        return buyingMoney;
    }

    public static Lotto readWinningNumbers(){
        System.out.println("당첨 번호를 입력해 주세요.");
        String input = Console.readLine();
        String pattern = "^([1-9]?[0-9],){5}[1-9]?[0-9]$";
        if (!Pattern.matches(pattern, input)) {
            throw new IllegalArgumentException("쉼표로 구분된 6개의 숫자를 입력해야 합니다.");
        }
        String[] splitedInputs = input.split(",");
        List<LottoNumber> lottoNumbers = new ArrayList<>();
        for (String splitedInput : splitedInputs) {
            int inputNumber = Integer.parseInt(splitedInput);
            lottoNumbers.add(new LottoNumber(inputNumber));
        }
        return new Lotto(lottoNumbers);
    }

    public static LottoNumber readBonusNumber(){
        System.out.println("보너스 번호를 입력해 주세요.");
        String input = Console.readLine();
        String pattern = "^[1-9]?[0-9]$";
        if (!Pattern.matches(pattern, input)) {
            throw new IllegalArgumentException("최대 두자리 숫자를 입력해야 합니다.");
        }
        int inputNumber = Integer.parseInt(input);
        return new LottoNumber(inputNumber);
    }

    public static void printLottos(List<Lotto> lottos) {
        System.out.println(lottos.size() + "개를 구매했습니다.");
        for (Lotto lotto : lottos) {
            System.out.println(lotto.show());
        }
    }

    public static void printPrizeResult(Map<Rank,Integer> allRankings, Float earningRate) {
        System.out.println("당첨 통계");
        System.out.println("---");
        System.out.println("3개 일치 (5,000원) - " + allRankings.get(Rank.FIFTH) + "개");
        System.out.println("4개 일치 (50,000원) - " + allRankings.get(Rank.FOURTH) + "개");
        System.out.println("5개 일치 (1,500,000원) - " + allRankings.get(Rank.THIRD) + "개");
        System.out.println("5개 일치, 보너스 볼 일치 (30,000,000원) - " + allRankings.get(Rank.SECOND) + "개");
        System.out.println("6개 일치 (2,000,000,000원) - " + allRankings.get(Rank.FIRST) + "개");

        System.out.println("총 수익률은 " + String.format("%.1f", earningRate) + "%입니다.");
    }

    public static void printExceptionMessage(IllegalArgumentException e) {
        System.out.println("[ERROR] " + e.getMessage());
    }


}
