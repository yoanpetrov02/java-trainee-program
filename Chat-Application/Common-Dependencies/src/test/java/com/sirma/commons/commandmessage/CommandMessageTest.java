package com.sirma.commons.commandmessage;

import org.junit.Assert;
import org.junit.Test;

public class CommandMessageTest {

    @Test
    public void testCommandMessageEqualsEqual() {
        CommandMessage a = new CommandMessage("type", "args");
        CommandMessage b = new CommandMessage("type", "args");

        Assert.assertEquals(a, b);
    }

    @Test
    public void testCommandMessageEqualsNotEqual() {
        CommandMessage a = new CommandMessage("type1", "args");
        CommandMessage b = new CommandMessage("type2", "args");

        Assert.assertNotEquals(a, b);
    }

    @Test
    public void testCommandMessageEqualsSameObject() {
        CommandMessage a = new CommandMessage("type", "args");
        CommandMessage b = a;

        Assert.assertEquals(a, b);
    }
}
