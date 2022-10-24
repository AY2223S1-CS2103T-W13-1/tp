package seedu.studmap.logic.commands;

import static seedu.studmap.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.studmap.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.studmap.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.studmap.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.studmap.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.studmap.commons.core.Messages;
import seedu.studmap.commons.core.index.Index;
import seedu.studmap.model.Model;
import seedu.studmap.model.ModelManager;
import seedu.studmap.model.UserPrefs;
import seedu.studmap.model.student.Attendance;
import seedu.studmap.model.student.Student;
import seedu.studmap.testutil.PersonBuilder;


/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code MarkCommand}.
 */
class MarkCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Student studentToMark = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Attendance attendance = new Attendance("T04", true);
        MarkCommand markCommand = new MarkCommand(INDEX_FIRST_PERSON, attendance);

        Student markedStudent = new PersonBuilder(studentToMark).addAttended("T04").build();

        String expectedMessage = String.format(MarkCommand.MESSAGE_MARK_SUCCESS,
                attendance.getAttendance(), markedStudent);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), markedStudent);
        assertCommandSuccess(markCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Student studentInFilteredList = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Attendance attendance = new Attendance("T04", true);
        MarkCommand markCommand = new MarkCommand(INDEX_FIRST_PERSON, attendance);

        Student markedStudent = new PersonBuilder(studentInFilteredList).addAttended("T04").build();

        String expectedMessage = String.format(MarkCommand.MESSAGE_MARK_SUCCESS,
                attendance.getAttendance(), markedStudent);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), markedStudent);
        assertCommandSuccess(markCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        Attendance attendance = new Attendance("T04", true);
        MarkCommand markCommand = new MarkCommand(outOfBoundIndex, attendance);

        assertCommandFailure(markCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }
}
