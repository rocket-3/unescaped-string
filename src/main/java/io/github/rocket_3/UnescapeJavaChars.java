package io.github.rocket_3;

import java.text.MessageFormat;
import java.util.function.Function;

/**
 * Code adapted from https://gist.github.com/uklimaschewski/6741769.
 * @since 0.1
 */
public class UnescapeJavaChars implements Function<CharSequence, CharSequence> {

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
                if (isOctal(second)) {
                    final StringBuilder code = new StringBuilder();
                    for (
                        int offset = 1;
                        offset <= 3 && !isOverflow(point + offset, chars);
                        offset++
                    ) {
                        code.append(chars.charAt(point + offset));
                    }
                    sb.append((char) Integer.parseInt(code.toString(), 8));
                    point += 1;
                    point += code.length();
                } else if (isUnicode(second)) {
                    if (!isOverflow(point + 5, chars)) {
                        sb.append(
                            Character.toChars(
                                Integer.parseInt(
                                    MessageFormat.format(
                                        "{0}{1}{2}{3}",
                                        chars.charAt(point + 2),
                                        chars.charAt(point + 3),
                                        chars.charAt(point + 4),
                                        chars.charAt(point + 5)
                                    ),
                                    16
                                )
                            )
                        );
                        point += 6;
                    } else {
                        sb.append(first);
                        point += 1;
                    }
                } else if (isSpecial(second)) {
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

    private boolean isOctal(final char symbol) {
        return symbol >= '0' && symbol <= '7';
    }

    private boolean isUnicode(final char symbol) {
        return symbol == 'u';
    }

    private boolean isOverflow(final int point, final CharSequence text) {
        return point >= text.length();
    }

}
