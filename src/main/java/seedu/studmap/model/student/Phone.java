package seedu.studmap.model.student;

import static java.util.Objects.requireNonNull;
import static seedu.studmap.commons.util.AppUtil.checkArgument;

/**
 * Represents a Student's phone number in the student map.
 * Guarantees: immutable; is valid as declared in {@link #isValidPhone(String)}
 */
public class Phone {

    public static final String NO_PHONE_STRING = "No Phone number";

    public static final String MESSAGE_CONSTRAINTS =
            "Phone numbers should only contain numbers, " +
                    " start with 8 or 9 and it should have a length of 8";
    public static final String VALIDATION_REGEX = "^[8-9]\\d{7}";
    public final String value;

    /**
     * Constructs an empty {@code Phone}
     */
    public Phone() {
        value = "";
    }

    /**
     * Constructs a {@code Phone}.
     *
     * @param phone A valid phone number.
     */
    public Phone(String phone) {
        requireNonNull(phone);
        checkArgument(isValidPhone(phone), MESSAGE_CONSTRAINTS);
        value = phone;
    }

    /**
     * Returns true if a given string is a valid phone number.
     */
    public static boolean isValidPhone(String test) {
        return test.isEmpty() || test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value.isBlank()
                ? NO_PHONE_STRING
                : value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Phone // instanceof handles nulls
                && value.equals(((Phone) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
