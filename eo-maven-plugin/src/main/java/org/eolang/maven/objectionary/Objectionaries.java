/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2016-2023 Objectionary.com
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included
 * in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NON-INFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package org.eolang.maven.objectionary;

import org.eolang.maven.hash.CommitHash;

/**
 * Hash-Objectionary hash map.
 * @since 0.29.6
 */
public interface Objectionaries {
    /**
     * Set new objectionary by given hash only if it's absent.
     * @param hash Hash as {@link CommitHash}.
     * @param objectionary Objectionary to store.
     * @return Objectionaries with new objectionary by given hash.
     */
    Objectionaries with(CommitHash hash, Objectionary objectionary);

    /**
     * Get objectionary by given hash.
     * @param hash Hash as {@link CommitHash}
     * @return Objectionary by given hash.
     */
    Objectionary get(CommitHash hash);

    /**
     * Fake objectionaries.
     * Contains only one default objectionary that will be returned by any hash.
     *
     * @since 0.29.6
     */
    class Fake implements Objectionaries {
        /**
         * Default objectionary.
         */
        private final Objectionary objry;

        /**
         * Ctor.
         */
        public Fake() {
            this(new Objectionary.Fake());
        }

        /**
         * Ctor.
         * @param objectionary Default objectionary
         */
        public Fake(final Objectionary objectionary) {
            this.objry = objectionary;
        }

        @Override
        public Objectionaries with(final CommitHash hash, final Objectionary objectionary) {
            return this;
        }

        @Override
        public Objectionary get(final CommitHash hash) {
            return this.objry;
        }
    }
}
