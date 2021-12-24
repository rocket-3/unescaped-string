package io.github.rocket_3;

public class UnescapedJavaChars extends CharsEnvelope {

    public UnescapedJavaChars(final CharSequence chars) {
        super(new UnescapeJavaChars().apply(chars));
    }

}
