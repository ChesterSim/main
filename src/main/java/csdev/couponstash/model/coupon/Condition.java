package csdev.couponstash.model.coupon;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Person's remark in the address book.
 * Guarantees: immutable; is always valid
 */
public class Condition {
    public static final String MESSAGE_CONSTRAINTS =
            "Do input a term and condition, if neccessary. Please limit to a max of 50 words.";
    public static final int STRING_LENGTH_LIMIT = 100;
    public static final String DEFAULT_NO_CONDITION = "No condition stated.";


    public final String value;

    public Condition(String condition) {
        requireNonNull(condition);
        value = condition;
    }

    /**
     * Returns true if a given string is a valid condition.
     */
    public static boolean isValidCondition(String test) {
        int wordCount = test.trim().split("\\s+").length;
        if (wordCount > 100) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Condition // instanceof handles nulls
                && value.equals(((Condition) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
