package io.github.rocket_3;

public class UnescapedYamlChars extends CharsEnvelope {

    public UnescapedYamlChars(final CharSequence origin) {
        super(new UnescapeYamlChars().apply(origin));
    }

}
