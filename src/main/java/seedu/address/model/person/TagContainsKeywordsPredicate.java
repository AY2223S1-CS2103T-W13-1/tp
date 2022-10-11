package seedu.address.model.person;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.tag.Tag;

import java.util.function.Predicate;
import java.util.List;
import java.util.Set;


/**
 * Tests that a {@code Person}'s {@code Tag} matches any of the keywords given.
 */
public class TagContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public TagContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        Set<Tag> tagSet = person.getTags();
        for (Tag t : tagSet) {
            if (keywords.stream().anyMatch(keywords ->
                    StringUtil.containsWordIgnoreCase(t.tagName, keywords))) {
                return true;
            }

        }
        return false;
    }
}
