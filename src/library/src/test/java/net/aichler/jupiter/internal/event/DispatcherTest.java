/*
 * jupiter-interface
 *
 * Copyright (c) 2017, Michael Aichler.
 * All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.aichler.jupiter.internal.event;

import junit.TestRunner;
import org.junit.Rule;
import org.junit.Test;
import sbt.testing.Event;
import sbt.testing.Status;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;

/**
 * @author Michael Aichler
 */
public class DispatcherTest {

    @Rule
    public final TestRunner testRunner = new TestRunner();

    @Test
    public void shouldCalculateDuration() {

        testRunner.execute(SampleDurationTest.class.getName());

        List<Event> result = testRunner.eventHandler().byStatus(Status.Success);

        assertThat(result, hasSize(1));
        assertThat(result.get(0).duration(), greaterThan(0L));
    }

    /**
     * Embedded JUnit Jupiter sample test.
     */
    static class SampleDurationTest {

        @org.junit.jupiter.api.Test
        void test() throws Exception {
            Thread.sleep(50);
        }
    }
}