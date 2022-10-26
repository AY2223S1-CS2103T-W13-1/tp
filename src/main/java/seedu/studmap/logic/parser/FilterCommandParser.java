package seedu.studmap.logic.parser;

import static seedu.studmap.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.studmap.logic.commands.FilterCommand;
import seedu.studmap.logic.parser.exceptions.ParseException;
import seedu.studmap.model.student.AssignmentContainsKeywordsPredicate;
import seedu.studmap.model.student.ModuleContainsKeywordsPredicate;
import seedu.studmap.model.student.TagContainsKeywordsPredicate;


/**
 * Parses input arguments and create a new FilterCommand object
 */
public class FilterCommandParser implements Parser<FilterCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FilterCommand
     * and returns a FilterCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public FilterCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.length() == 2
                || !trimmedArgs.startsWith("t/")
                || !trimmedArgs.startsWith("m/")
                || !trimmedArgs.startsWith("a/")) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
        }
        String prefix = trimmedArgs.substring(0, 2);

        String[] nameKeywords = trimmedArgs.split("\\s+");

        if (prefix.equals("t/")) {
            return new FilterCommand(new TagContainsKeywordsPredicate(Arrays.asList(nameKeywords)),
                    null, null);
        } else if (prefix.equals("m/")){
            return new FilterCommand(null,
                    new ModuleContainsKeywordsPredicate(Arrays.asList(nameKeywords)), null);
        }
        return new FilterCommand(null, null,
                new AssignmentContainsKeywordsPredicate(Arrays.asList(nameKeywords)));

    }
}
