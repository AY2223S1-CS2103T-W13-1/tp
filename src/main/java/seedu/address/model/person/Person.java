package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.tag.Tag;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final StudentID id;
    private final TeleHandle teleHandle;
    private final GitName gitName;
    private final Address address;
    private final Set<Tag> tags = new HashSet<>();
    private final Set<Attendance> attendances = new HashSet<>();

    /**
     * Constructor using a PersonData parameter object.
     * Requires name, phone, email, address, tags to be non-null.
     *
     * @param personData PersonData parameter object.
     */
    public Person(PersonData personData) {
        requireAllNonNull(personData.getId(), personData.getGitUser(),
                personData.getTeleHandle(), personData.getName(), personData.getPhone(),
                personData.getEmail(), personData.getAddress(),
                personData.getTags(), personData.getAttendances());

        this.id = personData.getId();
        this.teleHandle = personData.getTeleHandle();
        this.gitName = personData.getGitUser();
        this.name = personData.getName();
        this.phone = personData.getPhone();
        this.email = personData.getEmail();
        this.address = personData.getAddress();
        this.tags.addAll(personData.getTags());
        this.attendances.addAll(personData.getAttendances());
    }

    public StudentID getId() {
        return id;
    }

    public GitName getGitName() {
        return gitName;
    }

    public TeleHandle getTeleHandle() {
        return teleHandle;
    }

    public Name getName() {
        return name;
    }

    public String getNameString() {
        return name.toString();
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns an immutable Attendances set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Attendance> getAttendances() {
        return Collections.unmodifiableSet(attendances);
    }

    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null && otherPerson.getName().equals(getName());
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) other;
        return otherPerson.getName().equals(getName())
                && otherPerson.getId().equals(getId())
                && otherPerson.getGitName().equals(getGitName())
                && otherPerson.getTeleHandle().equals(getTeleHandle())
                && otherPerson.getPhone().equals(getPhone())
                && otherPerson.getEmail().equals(getEmail())
                && otherPerson.getAddress().equals(getAddress())
                && otherPerson.getTags().equals(getTags())
                && otherPerson.getAttendances().equals(getAttendances());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(id, gitName, teleHandle, name, phone, email, address, tags, attendances);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName()).append("; Phone: ").append(getPhone()).append("; Email: ")
                .append(getEmail()).append("; StudentID: ")
                .append(getId()).append("; GitHub Username: ")
                .append(getGitName()).append("; TeleHandle: ").append(getTeleHandle())
                .append("; Address: ").append(getAddress());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }

        Set<Attendance> attendances = getAttendances();
        if (!attendances.isEmpty()) {
            builder.append("; Attendance: ");
            attendances.forEach(builder::append);
        }
        return builder.toString();
    }

}
