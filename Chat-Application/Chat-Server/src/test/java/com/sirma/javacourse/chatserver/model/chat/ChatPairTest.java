package com.sirma.javacourse.chatserver.model.chat;

import org.junit.Assert;
import org.junit.Test;

public class ChatPairTest {

    @Test
    public void testChatPairEqualsSameObject() {
        ChatPair a = new ChatPair("a", "b");
        ChatPair b = a;

        Assert.assertEquals(a, b);
    }

    @Test
    public void testChatPairEqualsDifferentOrder() {
        ChatPair a = new ChatPair("a", "b");
        ChatPair b = new ChatPair("b", "a");

        Assert.assertEquals(a, b);
        Assert.assertEquals(b, a);
    }

    @Test
    public void testChatPairHashCodeDifferentOrder() {
        ChatPair a = new ChatPair("a", "b");
        ChatPair b = new ChatPair("b", "a");

        Assert.assertEquals(a.hashCode(), b.hashCode());
    }
}
