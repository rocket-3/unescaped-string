package io.github.rocket_3;

import java.util.stream.IntStream;

public class CharsEnvelope implements CharSequence{

    private final CharSequence sequence;

    public CharsEnvelope(final CharSequence sequence) {
        this.sequence = sequence;
    }

    @Override
    public int length() {
        return sequence.length();
    }

    @Override
    public char charAt(final int index) {
        return sequence.charAt(index);
    }

    @Override
    public CharSequence subSequence(final int start, final int end) {
        return sequence.subSequence(start, end);
    }

    @Override
    public String toString() {
        return sequence.toString();
    }

    @Override
    public IntStream chars() {
        return sequence.chars();
    }

    @Override
    public IntStream codePoints() {
        return sequence.codePoints();
    }

}
