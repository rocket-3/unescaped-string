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

/**
 * The type of {@link CharsEnvelope} that uses result of {@link UnescapeYamlChars} as delegate.
 * @since 0.1
 */
public class UnescapedYamlChars extends CharsEnvelope {

    /**
     * Instantiates a new Unescaped yaml chars.
     * @param origin The CharSequence to be encapsulated.
     */
    public UnescapedYamlChars(final CharSequence origin) {
        super(new UnescapeYamlChars().apply(origin));
    }

}