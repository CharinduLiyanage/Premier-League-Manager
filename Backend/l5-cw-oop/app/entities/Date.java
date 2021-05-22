/*
 * Full name    : Charindu Supun Nauththuduwa Lianage
 * IIT number   : 2018411
 * UOW number   : w1761962
 * <p>
 * I confirm that I understand what plagiarism /
 * collusion / contract cheating is and have read and
 * understood the section on Assessment Offences in the
 * Essential Information for Students. The work that I
 * have submitted is entirely my own. Any work from
 * other authors is duly referenced and acknowledged.
 */

package entities;

// Imports.
import java.io.Serializable;
import java.util.Objects;

/**
 * Date class to make Date objects.
 */
public class Date implements Serializable {
    private int year;
    private int month;
    private int day;

    /**
     * Default Constructor for Date.
     */
    public Date() {
    }

    /**
     * An argument constructor for Date.
     *
     * @param year  Year on the date.
     * @param month Month of the date.
     * @param day   Day of the date.
     */
    public Date(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    /**
     * The method used for comparing two Date objects.
     *
     * @param that Date object to compare with.
     * @return Returns an Integer ('0' if equal in value, '-1' if lesser, '1' if greater).
     */
    public int compareTo(Date that) {
        int compare = Integer.compare(this.year, that.getYear());

        if (compare == 0) {
            compare = Integer.compare(this.month, that.getMonth());
            if (compare == 0) {
                compare = Integer.compare(this.day, that.getDay());
            }
        }

        return compare;
    }

    /**
     * The Method used to check if two Date objects are equal.
     *
     * @param o Date object to check whether equal.
     * @return Returns a boolean value ('true' if equal, 'false' if unequal).
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Date date = (Date) o;
        return year == date.year &&
                month == date.month &&
                day == date.day;
    }

    /**
     * The hashCode method for Date object.
     *
     * @return Returns an unique integer value.
     */
    @Override
    public int hashCode() {
        return Objects.hash(year, month, day);
    }

    /**
     * The Method to make a Date object a String.
     *
     * @return Returns a String containing all the details of the Date object.
     */
    @Override
    public String toString() {
        return "Date{" +
                "year=" + year +
                ", month=" + month +
                ", day=" + day +
                '}';
    }

    /**
     * Getter method for the year of the Date object.
     *
     * @return Returns the year of the Date object.
     */
    public int getYear() {
        return year;
    }

    /**
     * Setter method for the year of the Date object.
     *
     * @param year The year of the Date object.
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * Getter method for the month of the Date object.
     *
     * @return Returns the month of the Date object.
     */
    public int getMonth() {
        return month;
    }

    /**
     * Setter method for the month of the Date object.
     *
     * @param month The month of the Date object.
     */
    public void setMonth(int month) {
        this.month = month;
    }

    /**
     * Getter method for the day of the Date object.
     *
     * @return Returns the day of the Date object.
     */
    public int getDay() {
        return day;
    }

    /**
     * Setter method for the day of the Date object.
     *
     * @param day The day of the Date object.
     */
    public void setDay(int day) {
        this.day = day;
    }
}
