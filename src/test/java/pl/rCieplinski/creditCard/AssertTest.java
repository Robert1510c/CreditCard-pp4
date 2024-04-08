package pl.rCieplinski.creditCard;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;


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

        assertThat(names).isUnmodifiable().hasSize(3).containsAll(Arrays.asList("Robert","Krzysztof"));

    }
}
