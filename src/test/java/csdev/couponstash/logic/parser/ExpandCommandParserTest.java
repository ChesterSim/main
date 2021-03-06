package csdev.couponstash.logic.parser;

import static csdev.couponstash.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import org.junit.jupiter.api.Test;

import csdev.couponstash.logic.commands.ExpandCommand;

import csdev.couponstash.testutil.TypicalIndexes;

class ExpandCommandParserTest {
    private ExpandCommandParser parser = new ExpandCommandParser();

    @Test
    public void parse_validArgs() {
        CommandParserTestUtil.assertParseSuccess(parser, "1",
                new ExpandCommand(TypicalIndexes.INDEX_FIRST_COUPON));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        String index = "a";
        CommandParserTestUtil.assertParseFailure(
                parser,
                index,
                String.format(
                        String.format(ParserUtil.MESSAGE_INVALID_INDEX, index) + "\n\n"
                                + MESSAGE_INVALID_COMMAND_FORMAT,
                        ExpandCommand.MESSAGE_USAGE
                )
        );
    }

    @Test
    public void parse_integerOverflow_throwsParseException() {
        CommandParserTestUtil.assertParseFailure(parser,
                Long.toString(Integer.MAX_VALUE + 1L),
                String.format(
                        ParserUtil.MESSAGE_INDEX_OVERFLOW + "\n\n"
                                + MESSAGE_INVALID_COMMAND_FORMAT,
                        ExpandCommand.MESSAGE_USAGE
                )
        );
    }
}
