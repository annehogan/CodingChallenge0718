package simpleatm.model.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * AccountRepository - a JPA repository to manage {@link Account}
 *
 * @Author Anne Hogan
 * @Copyright 2018
 */

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    /**
     * Finds the Account by using the account number and pin as a search criteria.
     *
     * @param accountNumber
     * @return A list of Accounts with the relevant accountNumber and pin.
     * If no Account is found, this method returns null.
     */
    List<Account> findByNumberAndPin(Long accountNumber, Long pin);

}
