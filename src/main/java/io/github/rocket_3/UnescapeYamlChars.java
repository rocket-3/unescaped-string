package io.github.rocket_3;

import java.util.function.Function;

/**
 * Code adapted from https://gist.github.com/uklimaschewski/6741769.
 * @since 0.1
 */
public class UnescapeYamlChars implements Function<CharSequence, CharSequence> {


    @Override
    public CharSequence apply(final CharSequence chars) {
        final int length = chars.length();
        final StringBuilder sb = new StringBuilder(length);
        for (int point = 0; point < length; ) {
            char first = chars.charAt(point);
            if (!isEscape(first) || isOverflow(point + 1, chars)) {
                sb.append(first);
                point += 1;
            } else {
                final char second = chars.charAt(point + 1);
                if (isSpecial(second)) {
                    sb.append(replaceSpecial(second));
                    point += 2;
                } else {
                    sb.append(first);
                    point += 1;
                }
            }
        }
        return sb;
    }

    private char replaceSpecial(final char symbol) {
        if (symbol == 'b') {
            return '\b';
        }
        if (symbol == 'f') {
            return '\f';
        }
        if (symbol == 'n') {
            return '\n';
        }
        if (symbol == 'r') {
            return '\r';
        }
        if (symbol == 't') {
            return '\t';
        }
        return symbol;
    }

    private boolean isSpecial(final char symbol) {
        return symbol == '\\' ||
               symbol == 'b' ||
               symbol == 'f' ||
               symbol == 'n' ||
               symbol == 'r' ||
               symbol == 't' ||
               symbol == '\"';
    }

    private boolean isEscape(final char symbol) {
        return symbol == '\\';
    }

    private boolean isOverflow(final int point, final CharSequence text) {
        return point >= text.length();
    }

}
