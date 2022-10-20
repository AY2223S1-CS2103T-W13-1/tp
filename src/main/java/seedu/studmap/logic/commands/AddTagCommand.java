package seedu.studmap.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.studmap.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Logger;

import seedu.studmap.MainApp;
import seedu.studmap.commons.core.LogsCenter;
import seedu.studmap.commons.core.index.IndexListGenerator;
import seedu.studmap.logic.commands.commons.StudentEditor;
import seedu.studmap.model.student.Student;
import seedu.studmap.model.student.StudentData;
import seedu.studmap.model.tag.Tag;

/**
 * Add tag for an existing student in the student map.
 */
public class AddTagCommand extends EditStudentCommand<AddTagCommand.AddTagCommandStudentEditor> {

    public static final Logger LOGGER = LogsCenter.getLogger(MainApp.class);

    public static final String COMMAND_WORD = "addtag";

    public static final String MESSAGE_ARGUMENTS = "Index: %1$s, new tag: %2$s";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Add tags for a student in the student map.\n"
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

    public static final String MESSAGE_MULTI_ADD_TAGS_SUCCESS = "Added tags %1$s for %2$d students";

    public static final String MESSAGE_TAGS_NOT_ADDED = "At least one tag must be added";

    public AddTagCommand(IndexListGenerator indexListGenerator, AddTagCommandStudentEditor editor) {
        super(indexListGenerator, editor);
    }

    /**
     * Formats the tag set to be more user-friendly string.
     *
     * @param tags set to be formatted.
     * @return the fomatted list of tags/
     */
    public static String tagSetToString(Set<Tag> tags) {
        String res = "";
        for (Tag tag : tags) {
            res += tag.toString() + ", ";
        }
        return res.substring(0, res.length() - 2);
    }

    @Override
    public String getSingleEditSuccessMessage(Student editedStudent) {
        return String.format(MESSAGE_SINGLE_ADD_TAGS_SUCCESS, tagSetToString(studentEditor.tags),
                editedStudent.getName());
    }

    @Override
    public String getMultiEditSuccessMessage(List<Student> editedStudents) {
        return String.format(MESSAGE_MULTI_ADD_TAGS_SUCCESS, tagSetToString(studentEditor.tags), editedStudents.size());
    }

    @Override
    public String getNoEditMessage() {
        return MESSAGE_TAGS_NOT_ADDED;
    }


    /**
     * A static StudentEditor that adds tags to a given Student.
     */
    public static class AddTagCommandStudentEditor extends StudentEditor {

        private Set<Tag> tags;

        /**
         * Parameterless constructor.
         */
        public AddTagCommandStudentEditor() {
        }

        /**
         * Constructor with tags.
         *
         * @param tags Tags that this editor will add to a given Student.
         */
        public AddTagCommandStudentEditor(Set<Tag> tags) {
            requireNonNull(tags);
            setTags(tags);
        }

        public Optional<Set<Tag>> getTags() {
            return Optional.ofNullable(tags);
        }

        public void setTags(Set<Tag> tags) {
            requireNonNull(tags);
            this.tags = new HashSet<>(tags);
        }

        @Override
        public Student editStudent(Student studentToEdit) {
            assert studentToEdit != null;

            StudentData studentData = studentToEdit.getStudentData();
            Set<Tag> newTags = studentData.getTags();
            newTags.addAll(tags);
            studentData.setTags(newTags);

            return new Student(studentData);
        }

        @Override
        public boolean hasEdits() {
            return tags != null && tags.size() > 0;
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditCommand.EditCommandStudentEditor)) {
                return false;
            }

            // state check
            EditCommand.EditCommandStudentEditor e = (EditCommand.EditCommandStudentEditor) other;

            return getTags().equals(e.getTags());
        }
    }
}
