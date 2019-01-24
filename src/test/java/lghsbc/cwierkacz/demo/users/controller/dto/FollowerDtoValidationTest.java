package lghsbc.cwierkacz.demo.users.controller.dto;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FollowerDtoValidationTest {
    private static final String VALID_TEXT = "valid-text";
    private static final String VALID_USER = "valid-user";

    @Autowired
    private Validator validator;

    @Test
    public void shouldAllowValidMessage() {
        FollowerDto dto = new FollowerDto(VALID_USER);

        Set<ConstraintViolation<FollowerDto>> result = validator.validate(dto);

        assertThat(result).isEmpty();
    }

    @Test
    public void shouldRejectEmptyUserId() {
        String emptyUserId = "";
        FollowerDto dto = new FollowerDto(emptyUserId);

        Set<ConstraintViolation<FollowerDto>> result = validator.validate(dto);

        assertThat(result).hasSize(1);
    }

    @Test
    public void shouldRejectNullUserId() {
        String nullUserId = null;
        FollowerDto dto = new FollowerDto(nullUserId);

        Set<ConstraintViolation<FollowerDto>> result = validator.validate(dto);

        assertThat(result).hasSize(1);
    }
}