package hello.itemservice.message;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;

import java.util.Locale;

@SpringBootTest
public class MessageSourceTest {

    @Autowired
    MessageSource messageSource;

    @Test
    void helloMessage() {
        String result = messageSource.getMessage("hello", null, Locale.ENGLISH);
        Assertions.assertThat(result).isEqualTo("hello");
    }

    @Test
    void notFoundMessageCode() {
        Assertions.assertThatThrownBy(()-> messageSource.getMessage("no_code", null, null))
                .isInstanceOf(NoSuchMessageException.class);
    }

    @Test
    void notFoundMessageCodeDefaultMessage() {
        String result = messageSource.getMessage("no_code", null, "기본메시지", null);
        Assertions.assertThat(result).isEqualTo("기본메시지");
    }

    @Test
    void argumentMessage() {
        String message = messageSource.getMessage("hello.name", new Object[]{"Spring"}, null);
        Assertions.assertThat(message).isEqualTo("안녕 Spring");
    }

    @Test
    void defaultLang() {
        Assertions.assertThat(messageSource.getMessage("hello", null, null)).isEqualTo("안녕");
    }
    @Test
    void enLang() {
        Assertions.assertThat(messageSource.getMessage("hello", null, Locale.ENGLISH)).isEqualTo("heloo");
    }
}
