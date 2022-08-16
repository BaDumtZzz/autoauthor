package ru.rsreu.autoauthor.domain;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

/**
 * User
 */
public class User {
    /** id */
    private  BigInteger id;
    /** nickname */
    private  String nickname;
    /** password */
    private  String password;
    /** email */
    private  String email;
    /** role */
    private  List<String> role;
    /** group  */
    private  List<Integer> group;
    /** group  */
    private  Integer groupId;
    /** group  */
    private  List<String> groupName;

    private  String singleGroupName;
    /** activity status */
    private  String status;

    /**
     * Returns the id
     * @return id
     */
    public BigInteger getId() {
        return id;
    }

    /**
     * Returns the nickname
     * @return nickname
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * Returns the password
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Returns the email
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Returns the role
     * @return role
     */
    public List<String> getRole() {
        return role;
    }

    /**
     * Returns the date of creation
     * @return the date of creation
     */
    public List<Integer> getGroup() {
        return group;
    }

    /**
     * Returns the date of creation
     * @return the date of creation
     */
    public Integer getGroupId() {
        return groupId;
    }

    /**
     * Returns the date of creation
     * @return the date of creation
     */
    public List<String> getGroupName() {
        return groupName;
    }

    /**
     * Returns the date of creation
     * @return the date of creation
     */
    public String getSingleGroupName() {
        return singleGroupName;
    }

    /**
     * Returns the status
     * @return status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the id
     * @param id id
     */
    public void setId(BigInteger id) {
        this.id = id;
    }

    /**
     * Sets the nickname
     * @param nickname nickname
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * Sets the password
     * @param password password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Sets the email
     * @param email email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Sets the role
     * @param role role
     */
    public void setRole(List<String> role) {
        this.role = role;
    }

    /**
     * Sets the date of creation
     * @param group the date of creation
     */
    public void setGroup(List<Integer> group) {
        this.group = group;
    }

    /**
     * Sets the date of creation
     * @param groupName the date of creation
     */
    public void setGroupName(List<String> groupName) {
        this.groupName = groupName;
    }

    /**
     * Sets the date of creation
     * @param groupName the date of creation
     */
    public void setSingleGroupName(String groupName) {
        this.singleGroupName = groupName;
    }

    /**
     * Sets the date of creation
     * @param groupId the date of creation
     */
    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }


    /**
     * Sets the activity status
     * @param status the activity status
     */
    public void setStatus(String status) {
        this.status = status;
    }
}
