package lotto;

import java.util.Objects;

public class LottoNumber {
    private final Integer number;


    public LottoNumber(int number) {
        validateNumberRange(number);
        this.number = number;
    }

    protected void validateNumberRange(int number) {
        if (number < 1 || number > 45) {
            throw new IllegalArgumentException("로또 번호는 1부터 45 사이의 숫자여야 합니다.");
        }
    }

    @Override
    public String toString() {
        return Integer.toString(this.number);
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(number);
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof LottoNumber) {
            LottoNumber compared = (LottoNumber) o;
            return this.number.equals(compared.number);
        }
        else{
            return false;
        }
    }
}
