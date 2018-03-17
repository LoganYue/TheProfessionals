package professional.team17.com.professional;

import android.util.Log;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;


/**
 *
 * This is the entity class for a task object
 * TODO potentially remove some setters/getters that are not being used
 * REVIEW AFTER Project part 5 full implementation
 *
 * @author Allison
 * @see BidList
 */
public class Task implements Serializable{
    private String profileName;
    private String name;
    private String description;
    private String location;
    private Date date;
    private String uniqueID;
    private ArrayList<String> photos;
    private BidList bids;
    public String status;

    /**
     *
     * @param profileName - String username of requested
     * @param name - String name of the task
     * @param description  - String description of the task
     * @param location - String location of the task
     * @param date - Date object - the date for the task to be completed by
     */
    public Task(String profileName, String name, String description, String location, Date date) {
        this.profileName = profileName;
        this.name = name;
        this.description = description;
        this.location = location;
        this.date = date;
        this.uniqueID = null;
        this.status = "Requested";
        this.bids = new BidList();
    }


    /**
     *
     * @param profileName - String username of requested
     * @param name - String name of the task
     * @param description  - String description of the task
     * @param location - String location of the task
     * @param date - String date for the task to be completed by
     */
    public Task(String profileName, String name, String description, String location, String date) {
        this.profileName = profileName;
        this.name = name;
        this.description = description;
        this.location = location;
        this.date = parseDate(date);
        this.uniqueID = null;
        this.status = "Requested";
        this.bids = new BidList();
    }

    /**
     *
     * @param profileName - String username of requested
     * @param name - String name of the task
     * @param description  - String description of the task
     * @param location - String location of the task
     */
    public Task(String profileName, String name, String description, String location) {
        this.profileName = profileName;
        this.name = name;
        this.description = description;
        this.location = location;
        this.date = null;
        this.uniqueID = null;
        this.status = "Requested";
        this.bids = new BidList();
    }


    /**
     *
     * @return profilename - a string repr the user name of the task requester
     */
    public String getProfileName() {
        return profileName;
    }

    /**
     *
     * @param profileName - a string repr the user name of the task requester
     */
    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    /**
     *
     * @return name - a string repre the name of the task
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name - a string repr the name of the task
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return description as string
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description - a string repr the task description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *
     * @return location as string
     */
    public String getLocation() {
        return location;
    }

    /**
     *
     * @param location - string repr the address location
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     *
     * @return date as Date object
     */
    public Date getDate() {
        return date;
    }

    /**
     *
     * @return String date representation
     */
    public String getDateAsString() {
        if (this.date == null) {
            return "N/A";
        }
        String myFormat = "dd/MM/yyyy";
        SimpleDateFormat dateFormat = new SimpleDateFormat(myFormat);
        return dateFormat.format(this.date);
    }


    public void setDate(Date date) {
        this.date = date;
    }

    public String getUniqueID() {
        return uniqueID;
    }

    /**
     *
     * @param ID - the unique id for the task
     */
    public void setId(String ID) {
        this.uniqueID = ID;
    }

    /**
     *
     * //TODO implement in project part 5
     */
    public ArrayList<String> getPhotos() {
        return photos;
    }


    /**
     *
     * //TODO implement in project part 5
     */
    public void setPhotos(ArrayList<String> photos) {
        this.photos = photos;
    }

    /**
     *
     * //TODO implement in project part 5
     */
    public void addPhoto(String photo){
        this.photos.add(photo);
    }

    /**
     *
     * //TODO implement in project part 5
     */
    public void removePhoto(String photo){
        this.photos.remove(photo);
    }

    /**
     *
     * @return a bidlist of bids held in the task
     */
    public BidList getBids() {
        return bids;
    }


    /**
     *
     * @param bid - the bids to be added against the bid
     */
    public void addBid(Bid bid){
        //if requested, update to Bidded
        if (isRequested()){
            setBidded();
        }
       this.bids.addBids(bid);
    }

    /**
     *
     * @param bid - the bid object to be removed from the tasklist
     */
    public void removeBid(Bid bid){
        this.bids.delete(bid);
        if (bids.isEmpty()){
            setRequested();
        }
    }

    /**
     * This will clear all bids against the task
     */
    public void clearBids(){
        this.bids.clear();
        setRequested();
    }

    /**
     *
     * @return - this will return the status of the task
     */
    public String getStatus() {
        return status;
    }

    /**
     *
     * @param status - this will set the status of the task
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Will change the status to Requested
     */
    public void setRequested(){
        this.status = "Requested";
    }


    /**
     *
     * @param bid - the bid selected for the task
     */
    public void chooseBid(Bid bid){
        this.clearBids();
        this.addBid(bid);
    }


    /**
     * Will change the status to Done
     */
    public void setDone(){
        this.status = "Done";
    }


    /**
     * Will change the status to Requested
     */
    private void setBidded(){
        this.status = "Bidded";
    }


    /**
     *
     * @return boolean value on whether status is Assigned
     */
    public boolean isAssigned() {
        return this.status.equals("Assigned");
    }

    /**
     *
     * @return boolean value on whether status is Requested
     */
    public boolean isRequested() {
        return this.status.equals("Requested");
    }

    /**
     *
     * @return boolean value on whether status is Bidded
     */
    public boolean isBidded() {
        return this.status.equals("Bidded");
    }

    /**
     *
     * @return boolean value on whether status is Done
     */
    public boolean isDone() {
        return this.status.equals("Done");
    }

    /**
     *
     * @return string representing task important part
     */
    public String toString(){
        return "Name: '" + this.name +
                "', Status: '" + this.status +
                "', profile: '" + this.profileName +
                "', id: '" + this.uniqueID;
    }

    /**
     *
     * @param sdate - the string representation of the date
     * @return - the Date format of the string representation
     */
    private java.util.Date parseDate(String sdate) {
        String myFormat = "dd/MM/yyyy";
        java.util.Date input = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat(myFormat, Locale.US);
        if (sdate != null) {
            try {
                input = dateFormat.parse(sdate);
            } catch (ParseException e) {
                //do nothing
            }
        }
        return input;
    }

}
