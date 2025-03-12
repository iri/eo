/*
 * SPDX-FileCopyrightText: Copyright (c) 2016-2025 Objectionary.com
 * SPDX-License-Identifier: MIT
 */
package org.eolang.maven;

import com.yegor256.Mktmp;
import com.yegor256.MktmpResolver;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.cactoos.list.ListOf;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

/**
 * Test case for {@link Walk}.
 *
 * @since 0.11
 */
@ExtendWith(MktmpResolver.class)
final class WalkTest {

    @Test
    void findsFilesMatchingGlobPattern(@Mktmp final Path temp) throws Exception {
        final String nonmatch = "foo/hello/0.1/EObar/x.bin";
        final String match = "EOxxx/bar";
        final String pattern = "EO**/*";
        final int count = 1;
        new HmBase(temp).save("", Paths.get(nonmatch));
        new HmBase(temp).save("", Paths.get(match));
        MatcherAssert.assertThat(
            String.format(
                "Expected %d file(s) matching pattern '%s'",
                count,
                pattern
            ),
            new Walk(temp).includes(new ListOf<>(pattern)),
            Matchers.iterableWithSize(count)
        );
    }
}
