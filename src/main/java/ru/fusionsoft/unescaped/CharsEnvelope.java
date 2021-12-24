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

import java.util.stream.IntStream;

/**
 * The type of CharSequence that can be constructed of another CharSequence instance.
 * @since 0.1
 */
public class CharsEnvelope implements CharSequence {

    /**
     * An instance of {@link CharSequence} encapsulated.
     */
    private final CharSequence sequence;

    /**
     * Instantiates a new Chars envelope.
     * @param sequence The CharSequence to be encapsulated.
     */
    public CharsEnvelope(final CharSequence sequence) {
        this.sequence = sequence;
    }

    @Override
    public final int length() {
        return this.sequence.length();
    }

    @Override
    public final char charAt(final int index) {
        return this.sequence.charAt(index);
    }

    @Override
    public final CharSequence subSequence(final int start, final int end) {
        return this.sequence.subSequence(start, end);
    }

    @Override
    public final String toString() {
        return this.sequence.toString();
    }

    @Override
    public final IntStream chars() {
        return this.sequence.chars();
    }

    @Override
    public final IntStream codePoints() {
        return this.sequence.codePoints();
    }

}
