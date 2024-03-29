package com.accountsmanager.account;

import com.accountsmanager.AccountsManagerApplication;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.List;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = AccountsManagerApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AccountFacadeTestSuite {

    @Autowired
    private AdapterAccountRepository adapterAccountRepository;
    @Autowired
    private AccountMapper accountMapper;

    @Test
    @DisplayName("should create main account using validateData() when we passing user account without currency")
    void createMainAccountPassingUserAccountWithoutCurrency() {
        //given
        Account account = new Account();
        //system under test
        var toTest = new AccountFacade(accountMapper,null,null,null,adapterAccountRepository);
        //when
        Account accountAfterTest = toTest.validateData(7L, account);
        //then
        Assertions.assertEquals(7, accountAfterTest.getUserId());
        Assertions.assertEquals("Main account", accountAfterTest.getAccountName());
        Assertions.assertEquals(new BigDecimal(0), accountAfterTest.getBalance());
        Assertions.assertEquals("PL55", accountAfterTest.getNumber().substring(0, 4));
        Assertions.assertEquals("zł", accountAfterTest.getCurrencySymbol());
    }
    @Test
    @DisplayName("should create user EUR account using validateData() when we pass correct data")
    void createAccountForUserPassingCorrectData() {
        //given
        Account account = new Account(
                null,
                null,
                "student account",
                new BigDecimal(0),
                new BigDecimal(0),
                null,
                "EUR",
                "€"
        );
        //system under test
        var toTest = new AccountFacade(accountMapper,null,null,null,adapterAccountRepository);
        //when
        Account accountAfterTest = toTest.validateData(7L, account);
        //then
        Assertions.assertNotNull(accountAfterTest.getId());
        Assertions.assertEquals(7, accountAfterTest.getUserId());
        Assertions.assertEquals("student account", accountAfterTest.getAccountName());
        Assertions.assertEquals(new BigDecimal(0), accountAfterTest.getBalance());
        Assertions.assertEquals("DE49", accountAfterTest.getNumber().substring(0, 4));
        Assertions.assertEquals("EUR", accountAfterTest.getCurrency());
        Assertions.assertEquals("€", accountAfterTest.getCurrencySymbol());
    }
    @Test
    @DisplayName("should return real data from database when we saving several examples one in EUR one in PLN")
    void getAllUserAccounts() {
        //given
        AccountEntity userAccountPLN = new AccountEntity(7L, "pln account", new BigDecimal(777),new BigDecimal(0), "PL55123123", "PLN", "zł");
        AccountEntity userAccountEUR = new AccountEntity(7L, "euro account", new BigDecimal(400),new BigDecimal(0), "DE49123123", "EUR", "€");
        userAccountPLN.setId(111L);
        userAccountEUR.setId(333L);
        adapterAccountRepository.save(userAccountPLN);
        adapterAccountRepository.save(userAccountEUR);
        //and
        //system under test
        var toTest = new AccountFacade(accountMapper,null,null,null,adapterAccountRepository);
        //when
        List<Account> accountAfterRealMethod = toTest.getAllUserAccounts(7L);
        //then
        Assertions.assertEquals(2, accountAfterRealMethod.size());
        Assertions.assertTrue(accountAfterRealMethod.stream()
                .anyMatch(currency -> currency.getCurrency().equals("PLN")));
        Assertions.assertTrue(accountAfterRealMethod.stream()
                .anyMatch(currency -> currency.getCurrency().equals("EUR")));
    }
    @Test
    @DisplayName("exists by number should return true when we check if account number which is 'generated' exist in database before assign to the user")
    void existsByNumberInDb() {
        //given
        AccountEntity userAccountPLN = new AccountEntity(7L, "pln account", new BigDecimal(777),new BigDecimal(0), "PL55123123", "PLN", "zł");
        adapterAccountRepository.save(userAccountPLN);
        //when
        boolean thisShouldBeTrue = adapterAccountRepository.existsByNumber("PL55123123");
        //then
        Assertions.assertTrue(thisShouldBeTrue);
    }
}
