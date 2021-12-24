/*
 * Copyright (C) 2018-2021 FusionSoft
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * You may not use this file except in compliance with the License.
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied.
 *
 * See the License for the specific language governing permissions
 * and limitations under the License.
 */
package ru.fusionsoft.unescaped;

import java.text.MessageFormat;
import java.util.function.Function;

/**
 * Code adapted from https://gist.github.com/uklimaschewski/6741769.
 * Unescapes chars from Java in-code text representation.
 * @since 0.1
 * @checkstyle ExecutableStatementCountCheck (200 lines)
 * @checkstyle MagicNumberCheck (200 lines)
 * @checkstyle NestedIfDepthCheck (200 lines)
 * @checkstyle BooleanExpressionComplexityCheck (200 lines)
 */
public class UnescapeJavaChars implements Function<CharSequence, CharSequence> {

    @Override
    public final CharSequence apply(final CharSequence chars) {
        final int length = chars.length();
        final StringBuilder string = new StringBuilder(length);
        for (int point = 0; point < length; ) {
            final char first = chars.charAt(point);
            if (!isEscape(first) || isOverflow(point + 1, chars)) {
                string.append(first);
                point += 1;
            } else {
                final char second = chars.charAt(point + 1);
                if (isOctal(second)) {
                    final StringBuilder code = new StringBuilder();
                    for (
                        int offset = 1;
                        offset <= 3 && !isOverflow(point + offset, chars);
                        offset = offset + 1
                    ) {
                        code.append(chars.charAt(point + offset));
                    }
                    string.append((char) Integer.parseInt(code.toString(), 8));
                    point += 1;
                    point += code.length();
                } else if (isUnicode(second)) {
                    if (!isOverflow(point + 5, chars)) {
                        string.append(
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
                        string.append(first);
                        point += 1;
                    }
                } else if (isSpecial(second)) {
                    string.append(replaceSpecial(second));
                    point += 2;
                } else {
                    string.append(first);
                    point += 1;
                }
            }
        }
        return string;
    }

    /**
     * Return special character instead of a letter.
     * @param symbol the symbol
     * @return The special character version.
     */
    private static char replaceSpecial(final char symbol) {
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

    /**
     * Check character is part of special one
     * @param symbol the symbol
     * @return Is one of or not.
     */
    private static boolean isSpecial(final char symbol) {
        return symbol == '\\'
           || symbol == 'b'
           || symbol == 'f'
           || symbol == 'n'
           || symbol == 'r'
           || symbol == 't'
           || symbol == '\"';
    }

    /**
     * Check character is part of escape one.
     * @param symbol the symbol.
     * @return Is one of or not.
     */
    private static boolean isEscape(final char symbol) {
        return symbol == '\\';
    }

    /**
     * Check index is bigger that text's length.
     * @param point the index.
     * @return Boolean.
     */
    private static boolean isOverflow(final int point, final CharSequence text) {
        return point >= text.length();
    }

    /**
     * Check character is part of octal one.
     * @param symbol the symbol.
     * @return Is one of or not.
     */
    private static boolean isOctal(final char symbol) {
        return symbol >= '0' && symbol <= '7';
    }

    /**
     * Check character is part of unicode one.
     * @param symbol the symbol.
     * @return Is one of or not.
     */
    private static boolean isUnicode(final char symbol) {
        return symbol == 'u';
    }

}
