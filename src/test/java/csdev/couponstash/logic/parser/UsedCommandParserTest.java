package csdev.couponstash.logic.parser;

import static csdev.couponstash.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static csdev.couponstash.logic.commands.CommandTestUtil.VALID_MONEY_SYMBOL;

import org.junit.jupiter.api.Test;

import csdev.couponstash.logic.commands.UsedCommand;
import csdev.couponstash.testutil.TypicalIndexes;

public class UsedCommandParserTest {
    private UsedCommandParser parser = new UsedCommandParser(VALID_MONEY_SYMBOL.toString());

    @Test
    public void parse_invalidArgs_throwsParseException() {
        String index = "a";
        CommandParserTestUtil.assertParseFailure(parser,
                index, String.format(ParserUtil.MESSAGE_INVALID_INDEX, index)
                        + "\n\n" + String.format(
                                MESSAGE_INVALID_COMMAND_FORMAT,
                                String.format(
                                        UsedCommand.MESSAGE_USAGE,
                                        VALID_MONEY_SYMBOL,
                                        VALID_MONEY_SYMBOL
                                )
                )
        );
    }

    @Test void parse_validArgs_returnsUsedCommand() {
        UsedCommand expectedFirstUsedCommand = new UsedCommand(TypicalIndexes.INDEX_FIRST_COUPON);
        CommandParserTestUtil.assertParseSuccess(parser, "1", expectedFirstUsedCommand);

        UsedCommand expectedSecondUsedCommand = new UsedCommand(TypicalIndexes.INDEX_SECOND_COUPON);
        CommandParserTestUtil.assertParseSuccess(parser, "2 s/" + VALID_MONEY_SYMBOL + "10",
                expectedSecondUsedCommand);
    }

    @Test
    public void parse_integerOverflow_throwsParseException() {
        CommandParserTestUtil.assertParseFailure(parser,
                Long.toString(Integer.MAX_VALUE + 1L),
                ParserUtil.MESSAGE_INDEX_OVERFLOW
                        + "\n\n" + String.format(
                        MESSAGE_INVALID_COMMAND_FORMAT,
                        String.format(
                                UsedCommand.MESSAGE_USAGE,
                                VALID_MONEY_SYMBOL,
                                VALID_MONEY_SYMBOL
                        )
                )
        );
    }
}
