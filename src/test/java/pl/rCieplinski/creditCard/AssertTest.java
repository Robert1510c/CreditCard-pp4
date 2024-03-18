package pl.rCieplinski.creditCard;

import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

public class AssertTest {
    @Test
    void helloAssertJ(){
        var hello = "Hello World";

        assertThat(hello).containsOnlyDigits();
    }

    @Test
    void testSomeListExpression() {
        var names = Collections.singleton("Robert");

        assertThat(names);

    }
}
