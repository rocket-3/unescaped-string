package io.github.rocket_3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class UnescapedCsvCharsTest {

    @Test
    public void rendersExpectedChars() {
        Assertions.assertEquals(
            "1 \n \\ \" \\063 \\u0224",
            new UnescapedYamlChars(
                "1 \\n \\\\ \\\" \\063 \\u0224"
            ).toString()
        );
    }

}
