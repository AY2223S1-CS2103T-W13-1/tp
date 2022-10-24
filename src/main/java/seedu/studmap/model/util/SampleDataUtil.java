package seedu.studmap.model.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.studmap.model.AddressBook;
import seedu.studmap.model.ReadOnlyAddressBook;
import seedu.studmap.model.person.*;
import seedu.studmap.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {

        PersonData personData;
        ArrayList<PersonData> personDatas = new ArrayList<>();

        personData = new PersonData();;
        personData.setName(new Name("Alex Yeoh"));
        personData.setPhone(new Phone("87438807"));
        personData.setEmail(new Email("alexyeoh@example.com"));
        personData.setId(new StudentID("A1234567A"));
        personData.setGitUser(new GitName("user1"));
        personData.setTeleHandle(new TeleHandle("@user1"));
        personData.setAddress(new Address("Blk 30 Geylang Street 29, #06-40"));
        personData.setTags(getTagSet("friends"));
        personData.setAttendances(getAttendedSet("T01", "T02"));
        personData.addAttendances(getNotAttendedSet("T03"));
        personDatas.add(personData);

        personData = new PersonData();;
        personData.setName(new Name("Bernice Yu"));
        personData.setPhone(new Phone("99272758"));
        personData.setEmail(new Email("berniceyu@example.com"));
        personData.setId(new StudentID("A1234567B"));
        personData.setGitUser(new GitName("user2"));
        personData.setTeleHandle(new TeleHandle("@user2"));
        personData.setAddress(new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"));
        personData.setTags(getTagSet("colleagues", "friends"));
        personDatas.add(personData);

        personData = new PersonData();;
        personData.setName(new Name("Charlotte Oliveiro"));
        personData.setPhone(new Phone("93210283"));
        personData.setEmail(new Email("charlotte@example.com"));
        personData.setId(new StudentID("A1234567C"));
        personData.setGitUser(new GitName("user3"));
        personData.setTeleHandle(new TeleHandle("@user3"));
        personData.setAddress(new Address("Blk 11 Ang Mo Kio Street 74, #11-04"));
        personData.setTags(getTagSet("neighbours"));
        personDatas.add(personData);

        personData = new PersonData();;
        personData.setName(new Name("David Li"));
        personData.setPhone(new Phone("91031282"));
        personData.setEmail(new Email("lidavid@example.com"));
        personData.setId(new StudentID("A1234567D"));
        personData.setGitUser(new GitName("user4"));
        personData.setTeleHandle(new TeleHandle("@user4"));
        personData.setAddress(new Address("Blk 436 Serangoon Gardens Street 26, #16-43"));
        personData.setTags(getTagSet("family"));
        personDatas.add(personData);

        personData = new PersonData();;
        personData.setName(new Name("Irfan Ibrahim"));
        personData.setPhone(new Phone("92492021"));
        personData.setEmail(new Email("irfan@example.com"));
        personData.setId(new StudentID("A1234567E"));
        personData.setGitUser(new GitName("user5"));
        personData.setTeleHandle(new TeleHandle("@user5"));
        personData.setAddress(new Address("Blk 47 Tampines Street 20, #17-35"));
        personData.setTags(getTagSet("classmates"));
        personDatas.add(personData);

        personData = new PersonData();;
        personData.setName(new Name("Roy Balakrishnan"));
        personData.setPhone(new Phone("92624417"));
        personData.setEmail(new Email("royb@example.com"));
        personData.setId(new StudentID("A1234567F"));
        personData.setGitUser(new GitName("user6"));
        personData.setTeleHandle(new TeleHandle("@user6"));
        personData.setAddress(new Address("Blk 45 Aljunied Street 85, #11-31"));
        personData.setTags(getTagSet("colleagues"));
        personDatas.add(personData);

        return personDatas.stream()
                .map(x -> new Person(x))
                .toArray(Person[]::new);
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

    /**
     * Returns an Attendance set of attended classes containing the list of strings given.
     */
    public static Set<Attendance> getAttendedSet(String... strings) {
        return Arrays.stream(strings)
                .map(className -> new Attendance(className, Boolean.TRUE))
                .collect(Collectors.toSet());
    }

    /**
     * Returns an Attendance set of classes not attended containing the list of strings given.
     */
    public static Set<Attendance> getNotAttendedSet(String... strings) {
        return Arrays.stream(strings)
                .map(className -> new Attendance(className, Boolean.FALSE))
                .collect(Collectors.toSet());
    }
}
