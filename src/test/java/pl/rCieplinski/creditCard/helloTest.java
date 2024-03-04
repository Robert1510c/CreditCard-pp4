package pl.rCieplinski.creditCard;

import org.junit.jupiter.api.Test;

public class helloTest {
    @Test
    void HelloTest(){
        var name = "Robert";
        var message = String.format("Hello %s", name);

        System.out.println(message);
    }

    @Test
    void equationTestV2(){
        //A / Arrange
        int a = 2;
        int b = 3;
        //A / Act
        var result = a + b;
        //A / Assert
        assert (5 == result);
    }
}
