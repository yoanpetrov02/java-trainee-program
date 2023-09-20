package com.sirma.commons.commandmessage;

import org.junit.Assert;
import org.junit.Test;

public class CommandMessageParserTest {

    @Test
    public void testParserOneArgument() {
        CommandMessage expected = new CommandMessage("type", "arg1");
        CommandMessage actual = CommandMessageParser.parseMessage("type [arg1]");

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testParserMultipleArguments() {
        CommandMessage expected = new CommandMessage("type", "arg1", "arg 2", "arg.3");
        CommandMessage actual = CommandMessageParser.parseMessage("type [arg1] [arg 2] [arg.3] ");

        Assert.assertEquals(expected, actual);
    }
}
