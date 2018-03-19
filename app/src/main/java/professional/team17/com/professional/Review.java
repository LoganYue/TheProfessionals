package professional.team17.com.professional;

import java.util.Date;

/**
 * @Author
 * @version 1.0
 * @See ReviewList
 * this class is mainly created for describe a new review object
 */
//TODO implement for project part 5
public class Review {

    private double score;
    private String comment;
    private String profileName; //person who gave review
    private String reviewTitle;
    private Date date;

    public Review(double score, String reviewer, String comment, String title) {
        this.score = score;
        this.profileName = reviewer;
        this.comment = comment;
        this.reviewTitle = title;
        this.date = new Date();
    }

    public Review(double score, String reviewer, String title) {
        this.score = score;
        this.profileName = reviewer;
        this.reviewTitle = title;
        this.date = new Date();
    }
    /* GETTERS */

    /**
     * Set getters for getting all the attributes from the review object
     * @return String or Double
     */

    public Double getScore() {
        return this.score;
    }

    public String getComment() {
        return this.comment;
    }

    public String getProfileName() {
        return this.profileName;
    }

    public String getReviewTitle() {
        return this.reviewTitle;
    }

    public Date getDate(){return this.date;}

    /**
     * Set setters for updating all the attributes of the review object
     * @param score
     */

    /*SETTERS*/
    public void setScore(Double score) {
        this.score = score;
    }


    public void setComment(String comment) {
        this.comment = comment;

    }

    private void setProfileName(String name) {
        this.profileName = name;
    }

    public void setReviewTitle(String title) {
        this.reviewTitle = title;
    }





}






