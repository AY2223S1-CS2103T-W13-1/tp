package seedu.studmap.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.studmap.logic.parser.CliSyntax.PREFIX_ASSIGNMENT;
import static seedu.studmap.logic.parser.CliSyntax.PREFIX_CLASS;
import static seedu.studmap.model.Model.PREDICATE_SHOW_ALL_STUDENTS;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.studmap.commons.core.Messages;
import seedu.studmap.commons.core.index.Index;
import seedu.studmap.logic.commands.exceptions.CommandException;
import seedu.studmap.model.Model;
import seedu.studmap.model.student.Assignment;
import seedu.studmap.model.student.Attendance;
import seedu.studmap.model.student.Student;
import seedu.studmap.model.student.StudentData;

/**
 * Unmarks the specified attendance record from the student identified using its displayed index.
 */
public class UnmarkCommand extends Command {

    public static final String COMMAND_WORD = "unmark";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Unmarks the attendance for student identified by the index number used in the displayed student list."
            + "\nSupport both attendance and assignment."
            + "\n<Attendance>"
            + "\n Removes attendance record for the class or tutorial specified in the parameter.\n"
            + "Parameters: INDEX (must be positive integer) "
            + PREFIX_CLASS + " [CLASS]\n"
            + "Example: " + COMMAND_WORD + " 1 " + PREFIX_CLASS + " T01"
            + "\n<Assignment>"
            + "\n Removes the specified assignment.\n"
            + "Parameters: INDEX (must be positive integer) "
            + PREFIX_ASSIGNMENT + " [CLASS]"
            + "\n Example: " + COMMAND_WORD + " 1 " + PREFIX_ASSIGNMENT + " A01";;

    public static final String MESSAGE_UNMARK_ATTENDANCE_SUCCESS = "Removed Class %1$s from Student: %2$s";
    public static final String MESSAGE_UNMARK_ATTENDANCE_NOTFOUND = "Class %1$s not found in Student: %2$s";
    public static final String MESSAGE_UNMARK_ASSIGNMENT_SUCCESS = "Removed assignment %1$s from Student:\n%2$s";
    public static final String MESSAGE_UNMARK_ASSIGNMENT_NOTFOUND = "Assignment %1$s not found in Student:\n%2$s";

    public static final String MESSAGE_NO_EDIT = "Attendance or Assignment must be provided.";

    private final Index index;
    private final Attendance attendance;
    private final Assignment assignment;

    /**
     * @param index Index of the student in the filtered student list to remove the attendance
     * @param attendance Attendance of the student to be removed
     */
    public UnmarkCommand(Index index, Attendance attendance) {
        this.index = index;
        this.attendance = attendance;
        this.assignment = null;
    }

    /**
     * @param index Index of the student in the filtered student list to remove the assignment
     * @param attendance Assignment of the student to be removed
     */
    public UnmarkCommand(Index index, Assignment assignment) {
        this.index = index;
        this.attendance = null;
        this.assignment = assignment;
    }


    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Student> lastShownList = model.getFilteredStudentList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
        }

        Student studentToEdit = lastShownList.get(index.getZeroBased());
        StudentData studentData = studentToEdit.getStudentData();

        if (attendance != null) {
            assert assignment == null : "Assignment cannot be removed when unmarking attedance";
            Set<Attendance> newAttendance = new HashSet<>(studentToEdit.getAttendances());
            boolean isRemoved = newAttendance.remove(attendance);
            studentData.setAttendances(newAttendance);
            Student editedStudent = new Student(studentData);

            model.setStudent(studentToEdit, editedStudent);
            model.updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);

            return new CommandResult(
                    String.format(isRemoved ? MESSAGE_UNMARK_ATTENDANCE_SUCCESS : MESSAGE_UNMARK_ATTENDANCE_NOTFOUND,
                            attendance.className, editedStudent));
        } else {
            assert assignment != null && attendance == null : "Attendance cannot be removed when unmarking assignment";
            Set<Assignment> newAssignments = new HashSet<>(studentToEdit.getAssignments());
            boolean isRemoved = newAssignments.remove(assignment);
            studentData.setAssignments(newAssignments);
            Student editedStudent = new Student(studentData);

            model.setStudent(studentToEdit, editedStudent);
            model.updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);

            return new CommandResult(
                    String.format(isRemoved ? MESSAGE_UNMARK_ASSIGNMENT_SUCCESS : MESSAGE_UNMARK_ASSIGNMENT_NOTFOUND,
                            assignment.assignmentName, editedStudent));
        }

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
