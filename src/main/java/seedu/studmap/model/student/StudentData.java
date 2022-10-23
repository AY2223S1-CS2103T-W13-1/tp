package seedu.studmap.model.student;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.studmap.model.tag.Tag;

/**
 * A parameter object for Student constructor.
 */
public class StudentData {
    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private Set<Tag> tags = new HashSet<>();
    private Set<Attendance> attendances = new HashSet<>();
    private Set<Assignment> assignments = new HashSet<>();

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public Phone getPhone() {
        return phone;
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    public void setAttendances(Set<Attendance> attendances) {
        this.attendances = attendances;
    }

    public void addAttendances(Set<Attendance> attendances) {
        this.attendances.addAll(attendances);
    }

    public void setAssignments(Set<Assignment> assignments) {
        this.assignments = assignments;
    }

    public void addAssignments(Set<Assignment> assignments) {
        this.assignments.addAll(assignments);
    }

    public Set<Attendance> getAttendances() {
        return attendances;
    }

    public Set<Assignment> getAssignments() {
        return assignments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        StudentData that = (StudentData) o;
        return Objects.equals(name, that.name)
                && Objects.equals(phone, that.phone)
                && Objects.equals(email, that.email)
                && Objects.equals(address, that.address)
                && Objects.equals(tags, that.tags)
                && Objects.equals(attendances, that.attendances)
                && Objects.equals(assignments, that.assignments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, phone, email, address, tags, attendances, assignments);
    }
}
