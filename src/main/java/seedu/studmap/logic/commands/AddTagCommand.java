package seedu.studmap.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.studmap.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.studmap.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import seedu.studmap.MainApp;
import seedu.studmap.commons.core.LogsCenter;
import seedu.studmap.commons.core.Messages;
import seedu.studmap.commons.core.index.Index;
import seedu.studmap.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.studmap.logic.commands.exceptions.CommandException;
import seedu.studmap.model.Model;
import seedu.studmap.model.student.Student;
import seedu.studmap.model.tag.Tag;

/**
 * Add tag for an existing student in the address book.
 */
public class AddTagCommand extends Command {

    public static final Logger LOGGER = LogsCenter.getLogger(MainApp.class);

    public static final String COMMAND_WORD = "addtag";

    public static final String MESSAGE_ARGUMENTS = "Index: %1$s, new tag: %2$s";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Add tags for a student in the address book.\n"
        + "Parameters:\n"
        + "  INDEX (must be a positive integer or use \"all\" to add tags for everyone in the list) \n"
        + "  [" + PREFIX_TAG + "TAG]...\n"
        + "Example:\n" + COMMAND_WORD + " 1 "
        + PREFIX_TAG + "lab "
        + PREFIX_TAG + "goodProgress"
        + "\nor\n" + COMMAND_WORD + " all "
        + PREFIX_TAG + "tutorial "
        + PREFIX_TAG + "needRemedial";

    public static final String MESSAGE_SINGLE_ADD_TAGS_SUCCESS = "Added tags %1$s Student: %2$s";

    public static final String MESSAGE_MULTI_ADD_TAGS_SUCCESS = "Added tags %1$s for %2$d persons";

    public static final String MESSAGE_TAGS_NOT_ADDED = "At least one tag must be added";

    public static final String MESSAGE_DUPLICATE_PERSON = "This student already exists in the address book.";

    /**
     * Index of the student to add the tags for.
     * If index is null, the tags will be added for every student in the list.
     */
    private Index index;

    /** The tags to be added.*/
    private Set<Tag> tags;

    private String toBeAddedTagsStr;

    /** The tags to be added.*/
    private boolean isAddToAll;

    /**
     * Constructs AddTagCommand that will add specified tag to all persons in the
     * displayed list.
     *
     * @param tags of the student to be added
     */
    public AddTagCommand(Set<Tag> tags) {
        requireNonNull(tags);

        this.isAddToAll = true;
        this.tags = tags;
        this.toBeAddedTagsStr = tagSetToSting(tags);
    }

    /**
     * @param index of the student in the filtered student list to add the tag
     * @param tags of the student to be added
     */
    public AddTagCommand(Index index, Set<Tag> tags) {
        requireNonNull(tags);

        this.isAddToAll = false;
        this.index = index;
        this.tags = tags;
        this.toBeAddedTagsStr = tagSetToSting(tags);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Student> lastShownList = model.getFilteredPersonList();
        if (this.isAddToAll) {
            int numOfPersonUpdated = 0;
            for (int idx = 0; idx < lastShownList.size(); idx++) {
                Student studentToEdit = lastShownList.get(idx);
                executeSingle(model, Index.fromZeroBased(idx), studentToEdit);
                numOfPersonUpdated += 1;
            }
            model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
            return new CommandResult(String.format(
                    MESSAGE_MULTI_ADD_TAGS_SUCCESS,
                    toBeAddedTagsStr,
                    numOfPersonUpdated));
        }

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        assert !this.isAddToAll && this.index != null
                : "[AddTagCommand] index should not be null if it is not all";
        Student studentToEdit = lastShownList.get(index.getZeroBased());
        CommandResult res = executeSingle(model, this.index, studentToEdit);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return res;
    }

    /**
     * Adds tags for a single student in the displayed list.
     * @param model {@code Model} which the command should operate on.
     * @param index of the student for whom the tags will be added.
     * @param studentToEdit for whom the tags will be added.
     * @return feedback message of the operation result for display.
     * @throws CommandException If an error occurs during command execution.
     */
    public CommandResult executeSingle(
                Model model, Index index, Student studentToEdit) throws CommandException {
        Set<Tag> existingTags = studentToEdit.getTags();
        for (Tag tag : existingTags) {
            if (!this.tags.contains(tag)) {
                this.tags.add(tag);
            }
        }
        EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptor();
        editPersonDescriptor.setTags(this.tags);
        CommandResult editPersonResult = (new EditCommand(index, editPersonDescriptor)).executeNoRefresh(model);
        LOGGER.info(editPersonResult.getFeedbackToUser());

        assert index != null : "[AddTagCommand] Index in executeSingle should not be null";
        return new CommandResult(String.format(
                MESSAGE_SINGLE_ADD_TAGS_SUCCESS,
                toBeAddedTagsStr,
                studentToEdit.getName()));
    }

    /**
     * Formats the tag set to be more user friendly string.
     * @param tags set to be formatted.
     * @return the fomatted list of tags/
     */
    public static String tagSetToSting(Set<Tag> tags) {
        String res = "";
        for (Tag tag : tags) {
            res += tag.toString() + ", ";
        }
        return res.substring(0, res.length() - 2);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddTagCommand)) {
            return false;
        }

        // state check
        AddTagCommand e = (AddTagCommand) other;
        return index.equals(e.index)
                && this.tags.equals(e.tags);
    }
}
