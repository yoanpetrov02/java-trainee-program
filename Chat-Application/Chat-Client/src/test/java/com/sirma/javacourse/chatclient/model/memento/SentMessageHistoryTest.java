package com.sirma.javacourse.chatclient.model.memento;

import org.junit.Assert;
import org.junit.Test;

public class SentMessageHistoryTest {

    @Test
    public void testMessageHistoryLast() {
        SentMessageHistory history = new SentMessageHistory();
        history.add("1");
        history.add("2");
        history.last();

        Assert.assertEquals("1", history.last());
    }

    @Test
    public void testMessageHistoryNext() {
        SentMessageHistory history = new SentMessageHistory();
        history.add("1");
        history.add("2");
        history.last();
        history.last();

        Assert.assertEquals("2", history.next());
    }
}
