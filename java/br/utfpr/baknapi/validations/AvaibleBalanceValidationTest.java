import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import br.utfpr.bankapi.validations.AvailableBalanceValidation;
import br.utfpr.bankapi.model.Account;
import br.utfpr.bankapi.repository.AccountRepository;

import java.util.Optional;

public class AvailableBalanceValidationTest {

    @InjectMocks
    private AvailableBalanceValidation availableBalanceValidation;

    @Mock
    private AccountRepository accountRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSufficientBalance() {
        Account account = new Account();
        account.setId(1L);
        account.setBalance(200.0);

        when(accountRepository.findById(1L)).thenReturn(Optional.of(account));
        assertTrue(availableBalanceValidation.check(1L, 150.0));
    }

    @Test
    public void testInsufficientBalance() {
        Account account = new Account();
        account.setId(1L);
        account.setBalance(100.0);

        when(accountRepository.findById(1L)).thenReturn(Optional.of(account));
        assertFalse(availableBalanceValidation.check(1L, 150.0));
    }
}

