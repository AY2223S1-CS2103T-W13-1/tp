package seedu.studmap.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.studmap.logic.parser.CliSyntax.PREFIX_CLASS;
import static seedu.studmap.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.studmap.commons.core.Messages;
import seedu.studmap.commons.core.index.Index;
import seedu.studmap.logic.commands.exceptions.CommandException;
import seedu.studmap.model.Model;
import seedu.studmap.model.student.Attendance;
import seedu.studmap.model.student.Student;
import seedu.studmap.model.student.StudentData;

/**
 * Unmarks the specified attendance record from the student identified using its displayed index.
 */
public class UnmarkCommand extends Command {

    public static final String COMMAND_WORD = "unmark";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Unmarks the attendance for student identified by the index number used in the displayed"
            + " student list.\n Removes attendance record for the class or tutorial specified in the parameter.\n"
            + "Parameters: INDEX (must be positive integer) "
            + PREFIX_CLASS + " [CLASS]\n"
            + "Example: " + COMMAND_WORD + " 1 " + PREFIX_CLASS + " T01";

    public static final String MESSAGE_UNMARK_SUCCESS = "Removed Class %1$s from Student: %2$s";
    public static final String MESSAGE_UNMARK_NOTFOUND = "Class %1$s not found in Student: %2$s";

    private final Index index;
    private final Attendance attendance;

    /**
     * @param index Index of the student in the filtered student list to remove the attendance
     * @param attendance Attendance of the student to be removed
     */
    public UnmarkCommand(Index index, Attendance attendance) {
        this.index = index;
        this.attendance = attendance;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Student> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Student studentToEdit = lastShownList.get(index.getZeroBased());
        StudentData studentData = new StudentData();
        studentData.setName(studentToEdit.getName());
        studentData.setPhone(studentToEdit.getPhone());
        studentData.setEmail(studentToEdit.getEmail());
        studentData.setId(studentToEdit.getId());
        studentData.setGitUser(studentToEdit.getGitName());
        studentData.setTeleHandle(studentToEdit.getTeleHandle());
        studentData.setAddress(studentToEdit.getAddress());
        studentData.setTags(studentToEdit.getTags());

        Set<Attendance> newAttendance = new HashSet<>(studentToEdit.getAttendances());
        boolean isRemoved = newAttendance.remove(attendance);
        studentData.setAttendances(newAttendance);
        Student editedStudent = new Student(studentData);

        model.setPerson(studentToEdit, editedStudent);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(
                String.format(isRemoved ? MESSAGE_UNMARK_SUCCESS : MESSAGE_UNMARK_NOTFOUND,
                        attendance.className, editedStudent));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UnmarkCommand)) {
            return false;
        }

        // state check
        UnmarkCommand e = (UnmarkCommand) other;
        return index.equals(e.index)
                && attendance.equals(e.attendance);
    }
}
