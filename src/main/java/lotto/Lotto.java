package lotto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class Lotto {


    private final List<LottoNumber> numbers;

    public Lotto(List<LottoNumber> numbers) {
        validateNumbers(numbers);
        this.numbers = numbers;
    }

    private void validateNumbers(List<LottoNumber> numbers){
        if (numbers.size() != 6) {
            throw new IllegalArgumentException("로또 번호는 6개이여야 합니다.");
        }
        validateDuplication(numbers);
    }

    private void validateDuplication(List<LottoNumber> numbers) {
        if (new HashSet<>(numbers).size() != 6) {
            throw new IllegalArgumentException("로또 번호는 모두 달라야 합니다.");
        }
    }

    public int calculateSameNumber(Lotto lotto){
        long sameNumber = this.numbers.stream().filter(lotto::contains).count();
        return (int) sameNumber;
    }

    public boolean contains(LottoNumber lottoNumber){
        return this.numbers.contains(lottoNumber);
    }
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        for (int index = 0; index < this.numbers.size() - 1; index++) {
            stringBuilder.append(numbers.get(index));
            stringBuilder.append(", ");
        }
        stringBuilder.append(numbers.get(numbers.size() - 1));
        stringBuilder.append("]");
        return stringBuilder.toString();
    }


}
