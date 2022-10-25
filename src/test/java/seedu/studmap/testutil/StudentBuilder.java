package seedu.studmap.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.studmap.model.student.Address;
import seedu.studmap.model.student.Assignment;
import seedu.studmap.model.student.Attendance;
import seedu.studmap.model.student.Email;
import seedu.studmap.model.student.GitName;
import seedu.studmap.model.student.Name;
import seedu.studmap.model.student.Phone;
import seedu.studmap.model.student.Student;
import seedu.studmap.model.student.StudentData;
import seedu.studmap.model.student.StudentID;
import seedu.studmap.model.student.TeleHandle;
import seedu.studmap.model.tag.Tag;
import seedu.studmap.model.util.SampleDataUtil;

/**
 * A utility class to help with building Student objects.
 */
public class StudentBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_ID = "A1234567B";
    public static final String DEFAULT_GIT = "GitUser";
    public static final String DEFAULT_TELE = "@CS2103T";

    private Name name;
    private Phone phone;
    private Email email;
    private StudentID id;
    private GitName gitName;
    private TeleHandle handle;
    private Address address;
    private Set<Tag> tags;
    private Set<Attendance> attendances;
    private Set<Assignment> assignments;

    /**
     * Creates a {@code StudentBuilder} with the default details.
     */
    public StudentBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        id = new StudentID(DEFAULT_ID);
        gitName = new GitName(DEFAULT_GIT);
        handle = new TeleHandle(DEFAULT_TELE);
        address = new Address(DEFAULT_ADDRESS);
        tags = new HashSet<>();
        attendances = new HashSet<>();
        assignments = new HashSet<>();
    }

    /**
     * Initializes the StudentBuilder with the data of {@code studentToCopy}.
     */
    public StudentBuilder(Student studentToCopy) {
        name = studentToCopy.getName();
        phone = studentToCopy.getPhone();
        email = studentToCopy.getEmail();
        id = studentToCopy.getId();
        gitName = studentToCopy.getGitName();
        handle = studentToCopy.getTeleHandle();
        address = studentToCopy.getAddress();
        tags = new HashSet<>(studentToCopy.getTags());
        attendances = new HashSet<>(studentToCopy.getAttendances());
        assignments = new HashSet<>(studentToCopy.getAssignments());
    }

    /**
     * Sets the {@code Name} of the {@code Student} that we are building.
     */
    public StudentBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Student} that we are building.
     */
    public StudentBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Parses the {@code classNames} which the student has attended into a
     * {@code Set<Attendance>} and adds it to the {@code Student} that we are building.
     */
    public StudentBuilder addAttended(String ... classNames) {
        this.attendances.addAll(SampleDataUtil.getAttendedSet(classNames));
        return this;
    }

    /**
     * Parses the {@code classNames} which the student has attended into a
     * {@code Set<Attendance>} and adds it to the {@code Student} that we are building.
     */
    public StudentBuilder addNotAttended(String ... classNames) {
        this.attendances.addAll(SampleDataUtil.getNotAttendedSet(classNames));
        return this;
    }

    /**
     * Parses the {@code assignmentNames} which the user has not marked into a
     * {@code Set<Assignment>} and adds it to the {@code Student} that we are building.
     */
    public StudentBuilder addAssignedNew(String ... assignmentNames) {
        this.assignments.addAll(SampleDataUtil.getNewAssignments(assignmentNames));
        return this;
    }

    /**
     * Parses the {@code assignmentNames} which the user has received into a
     * {@code Set<Assignment>} and adds it to the {@code Student} that we are building.
     */
    public StudentBuilder addAssignedReceived(String ... assignmentNames) {
        this.assignments.addAll(SampleDataUtil.getReceivedAssignments(assignmentNames));
        return this;
    }

    /**
     * Parses the {@code assignmentNames} which the user has marked into a
     * {@code Set<Assignment>} and adds it to the {@code Student} that we are building.
     */
    public StudentBuilder addAssignedMarked(String ... assignmentNames) {
        this.assignments.addAll(SampleDataUtil.getMarkedAssignments(assignmentNames));
        return this;
    }

    public StudentBuilder setAttended(Set<Attendance> attendances) {
        this.attendances = attendances;
        return this;
    }

    public StudentBuilder setAssigned(Set<Assignment> assignments) {
        this.assignments = assignments;
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Student} that we are building.
     */
    public StudentBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Student} that we are building.
     */
    public StudentBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Student} that we are building.
     */
    public StudentBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code id} of the {@code StudentID} that we are building.
     */
    public StudentBuilder withId(String id) {
        this.id = new StudentID(id);
        return this;
    }

    /**
     * Sets the {@code gitName} of the {@code GitName} that we are building.
     */
    public StudentBuilder withGitName(String name) {
        this.gitName = new GitName(name);
        return this;
    }

    /**
     * Sets the {@code handle} of the {@code TeleHandle} that we are building.
     */
    public StudentBuilder withTeleHandle(String teleHandle) {
        this.handle = new TeleHandle(teleHandle);
        return this;
    }

    /**
     * Builds the student using the given parameters.
     * @return New Student.
     */
    public Student build() {
        StudentData studentData = new StudentData();
        studentData.setName(this.name);
        studentData.setPhone(this.phone);
        studentData.setEmail(this.email);
        studentData.setId(this.id);
        studentData.setGitUser(this.gitName);
        studentData.setTeleHandle(this.handle);
        studentData.setAddress(this.address);
        studentData.setTags(this.tags);
        studentData.setAttendances(this.attendances);
        studentData.setAssignments(this.assignments);
        return new Student(studentData);
    }

}
