package io.github.rocket_3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class UnescapedJavaCharsTest {

    @Test
    public void rendersExpectedText() {
        Assertions.assertEquals(
            "1 \n \\ \" \063 \u0224",
            new UnescapedJavaChars(
                "1 \\n \\ \\\" \\063 \\u0224"
            ).toString()
        );
    }

}
