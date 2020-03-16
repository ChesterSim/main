package csdev.couponstash.logic.parser;

import static csdev.couponstash.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import csdev.couponstash.commons.core.index.Index;
import csdev.couponstash.logic.commands.UsedCommand;
import csdev.couponstash.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new UsedCommand object
 */
public class UsedCommandParser implements Parser<UsedCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a UsedCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public UsedCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new UsedCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, UsedCommand.MESSAGE_USAGE), pe);
        }
    }
}