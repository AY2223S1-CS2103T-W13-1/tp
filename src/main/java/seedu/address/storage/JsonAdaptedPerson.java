package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.*;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Person}.
 */
class JsonAdaptedPerson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    private final String name;
    private final String phone;
    private final String email;
    private final String StudentId;
    private final String gitName;
    private final String handle;
    private final String address;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();
    private final List<JsonAdaptedAttendance> attended = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedPerson(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
                             @JsonProperty("email") String email, @JsonProperty("StudentId") String StudentId,
                             @JsonProperty("gitName") String gitName, @JsonProperty("handle") String handle,
                             @JsonProperty("address") String address,
                             @JsonProperty("tagged") List<JsonAdaptedTag> tagged,
                             @JsonProperty("attended") List<JsonAdaptedAttendance> attended) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.StudentId = StudentId;
        this.gitName = gitName;
        this.handle = handle;
        this.address = address;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
        if (attended != null) {
            this.attended.addAll(attended);
        }
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedPerson(Person source) {
        name = source.getName().fullName;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        StudentId = source.getId().value;
        gitName = source.getGitName().value;
        handle = source.getTeleHandle().value;
        address = source.getAddress().value;
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
        attended.addAll(source.getAttendances().stream()
                .map(attendance -> new JsonAdaptedAttendance(attendance.getString()))
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Person toModelType() throws IllegalValueException {
        final List<Tag> personTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            personTags.add(tag.toModelType());
        }

        final List<Attendance> personAttendances = new ArrayList<>();
        for (JsonAdaptedAttendance attendance : attended) {
            personAttendances.add(attendance.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (phone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
        }
        if (!Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        final Phone modelPhone = new Phone(phone);

        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        final Email modelEmail = new Email(email);

        if (StudentId == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    StudentID.class.getSimpleName()));
        }
        if (!StudentID.isValidStudentID(StudentId)) {
            throw new IllegalValueException(StudentID.MESSAGE_CONSTRAINTS);
        }
        final StudentID modelId = new StudentID(StudentId);

        if (gitName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    GitName.class.getSimpleName()));
        }
        if (!GitName.isValidGitName(gitName)) {
            throw new IllegalValueException(GitName.MESSAGE_CONSTRAINTS);
        }
        final GitName modelGit = new GitName(gitName);

        if (handle == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    TeleHandle.class.getSimpleName()));
        }
        if (!TeleHandle.isValidTeleHandle(handle)) {
            throw new IllegalValueException(TeleHandle.MESSAGE_CONSTRAINTS);
        }
        final TeleHandle modelHandle = new TeleHandle(handle);

        if (address == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName()));
        }
        if (!Address.isValidAddress(address)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        final Address modelAddress = new Address(address);

        final Set<Tag> modelTags = new HashSet<>(personTags);
        final Set<Attendance> modelAttendances = new HashSet<>(personAttendances);

        PersonData personData = new PersonData();
        personData.setName(modelName);
        personData.setPhone(modelPhone);
        personData.setEmail(modelEmail);
        personData.setId(modelId);
        personData.setGitUser(modelGit);
        personData.setTeleHandle(modelHandle);
        personData.setAddress(modelAddress);
        personData.setTags(modelTags);
        personData.setAttendances(modelAttendances);
        return new Person(personData);
    }

}
