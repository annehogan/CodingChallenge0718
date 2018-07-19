package simpleatm.model;

import com.fasterxml.jackson.annotation.JsonRootName;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * WithdrawalDTO - POJO class for passing withdrawal data to the front end
 *
 * @Author Anne Hogan
 * @Copyright 2018
 */

@JsonRootName("withdrawal")
public class WithdrawalDTO implements Serializable {

    private List<BanknotePile> banknotePiles;
    private Long remainingBalance;

    public WithdrawalDTO(List<BanknotePile> banknotePiles, Long remainingBalance) {
        this.banknotePiles = banknotePiles;
        this.remainingBalance = remainingBalance;
    }

    public List<BanknotePile> getBanknotePiles() {
        return banknotePiles;
    }

    public void setBanknotePiles(List<BanknotePile> banknotePiles) {
        this.banknotePiles = banknotePiles;
    }

    public Long getRemainingBalance() {
        return remainingBalance;
    }

    public void setRemainingBalance(Long remainingBalance) {
        this.remainingBalance = remainingBalance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WithdrawalDTO that = (WithdrawalDTO) o;
        return Objects.equals(banknotePiles, that.banknotePiles) &&
                Objects.equals(remainingBalance, that.remainingBalance);
    }

    @Override
    public int hashCode() {

        return Objects.hash(banknotePiles, remainingBalance);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("WithdrawalDTO{");
        sb.append("banknotePiles=").append(banknotePiles);
        sb.append(", remainingBalance=").append(remainingBalance);
        sb.append('}');
        return sb.toString();
    }
}
