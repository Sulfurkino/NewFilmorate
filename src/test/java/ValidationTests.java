import com.kino.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.*;
import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidationTests {

    private Validator validator;

    @BeforeEach
    public void setUp(){
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @Test
    void shouldAccessCreateUser(){
        User user = User.builder()
                .email("abcdef@mail.com")
                .login("abcd")
                .name("Alphabet")
                .birthday(LocalDate.of(2000,12,1))
                .build();
        Set<ConstraintViolation<User>> errors = validator.validate(user);
        assertTrue(errors.isEmpty());
    }

    @Test
    void shouldFailCreateUser(){
        User user = User.builder()
                .email("abcdef mail.com")
                .login("ab cd")
                .name("Alph abet")
                .birthday(LocalDate.of(2900,12,1))
                .build();
        Set<ConstraintViolation<User>> errors = validator.validate(user);
        assertFalse(errors.isEmpty());
    }


}
