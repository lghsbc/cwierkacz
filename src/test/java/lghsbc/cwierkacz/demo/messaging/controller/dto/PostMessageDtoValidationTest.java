package lghsbc.cwierkacz.demo.messaging.controller.dto;

import org.apache.commons.lang3.RandomStringUtils;
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
public class PostMessageDtoValidationTest {

    private static final String VALID_TEXT = "valid-text";

    @Autowired
    private Validator validator;

    @Test
    public void shouldAllowValidMessage() {
        PostMessageDto dto = new PostMessageDto(VALID_TEXT);

        Set<ConstraintViolation<PostMessageDto>> result = validator.validate(dto);

        assertThat(result).isEmpty();
    }

    @Test
    public void shouldRejectTextLongerThan140Chars() {
        String invalidText = RandomStringUtils.random(141);
        PostMessageDto dto = new PostMessageDto(invalidText);

        Set<ConstraintViolation<PostMessageDto>> result = validator.validate(dto);

        assertThat(result).hasSize(1);
    }

    @Test
    public void shouldRejectEmptyText() {
        String invalidText = "";
        PostMessageDto dto = new PostMessageDto(invalidText);

        Set<ConstraintViolation<PostMessageDto>> result = validator.validate(dto);

        assertThat(result).hasSize(1);
    }

    @Test
    public void shouldRejectNullText() {
        String invalidText = null;
        PostMessageDto dto = new PostMessageDto(invalidText);

        Set<ConstraintViolation<PostMessageDto>> result = validator.validate(dto);

        assertThat(result).hasSize(1);
    }

    @Test
    public void shouldAllowMin1CharText() {
        String textWithMinimumLength = "1";
        PostMessageDto dto = new PostMessageDto(textWithMinimumLength);

        Set<ConstraintViolation<PostMessageDto>> result = validator.validate(dto);

        assertThat(result).isEmpty();
    }

    @Test
    public void shouldAllowMax140CharText() {
        String textWithMaximumLength = RandomStringUtils.random(140);
        PostMessageDto dto = new PostMessageDto(textWithMaximumLength);

        Set<ConstraintViolation<PostMessageDto>> result = validator.validate(dto);

        assertThat(result).isEmpty();
    }

}