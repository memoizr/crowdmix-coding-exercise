package gd.crowdmix.parser;

import gd.crowdmix.command.*;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class TextCommandParserTest {
    private final TextCommandParser parser = new TextCommandParser();

    @Test
    public void parseRequestTimelineCommand() {
        final Command expectedCommand = new RequestTimeline("Charlie");
        assertThat(parser.parseCommand("Charlie"), is(expectedCommand));
    }

    @Test
    public void parseRequestWallCommand() {
        final Command expectedCommand = new RequestWall("Charlie");
        assertThat(parser.parseCommand("Charlie wall"), is(expectedCommand));
    }

    @Test
    public void parsePublishMessageCommand() {
        final Command expectedCommand = new PublishMessage("Charlie", "I love the weather today");
        assertThat(parser.parseCommand("Charlie -> I love the weather today"), is(expectedCommand));
    }

    @Test
    public void parseFollowUserCommand() {
        final Command expectedCommand = new FollowUser("Charlie", "Alice");
        assertThat(parser.parseCommand("Charlie follows Alice"), is(expectedCommand));
    }

    @Test
    public void unparseableCommand() {
        try {
            parser.parseCommand("bunch of monkeys");
            fail("Should have thrown IllegalArgumentException");
        } catch (IllegalArgumentException expected) {
            assertThat(expected.getMessage(), is("Unknown command: [bunch of monkeys]"));
        }
    }
}