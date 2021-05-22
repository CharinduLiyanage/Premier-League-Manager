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

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.Serializable;
import java.util.Objects;

/**
 * Abstract Class SportClub.
 */
@JsonDeserialize(as = FootballClub.class)
//@JsonTypeInfo(use=JsonTypeInfo.Id.CLASS, include=JsonTypeInfo.As.PROPERTY, property="@class")
public abstract class SportsClub implements Serializable {
    private String name;
    private String location;

    /**
     * Default Constructor for SportClub.
     */
    public SportsClub() {

    }

    /**
     * An argument constructor for SportClub.
     *
     * @param name     The name of the SportClub.
     * @param location The location of the SportClub.
     */
    public SportsClub(String name, String location) {
        this.name = name;
        this.location = location;
    }

    /**
     * The method used for comparing two SportsClub objects.
     *
     * @param that SportsClub object to compare with.
     * @return Returns an Integer ('0' if equal in value, '-1' if lesser, '1' if greater).
     */
    public int compareTo(SportsClub that) {
        int compare = this.name.compareTo(that.name);
        if (compare == 0) {
            compare = this.location.compareTo(that.location);
        }
        return compare;
    }

    /**
     * The Method used to check if two SportsClub objects are equal.
     *
     * @param o SportClub object to check whether equal.
     * @return Returns a boolean value ('true' if equal, 'false' if unequal).
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SportsClub that = (SportsClub) o;
        return name.equals(that.name) &&
                location.equals(that.location);
    }

    /**
     * The hashCode method for SportClub object.
     *
     * @return Returns an unique integer value.
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, location);
    }

    /**
     * The Method to make a SportClub object a String.
     *
     * @return Returns a String containing all the details of the SportClub object.
     */
    @Override
    public String toString() {
        return "SportsClub{" +
                "name='" + name + '\'' +
                ", location='" + location + '\'' +
                '}';
    }

    /**
     * Getter method for the name of the SportClub object.
     *
     * @return Returns the name of the SportClub object.
     */
    public String getName() {
        return name;
    }

    /**
     * Setter method for the name of the SportClub object.
     *
     * @param name The name of the SportClub object.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter method for the location of the SportClub object.
     *
     * @return Returns the location of the SportClub object.
     */
    public String getLocation() {
        return location;
    }

    /**
     * Setter method for location of the SportClub object.
     *
     * @param location The location of the SportClub object.
     */
    public void setLocation(String location) {
        this.location = location;
    }
}
