/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meetu.eventservice.model;

import java.util.Date;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author Test
 */
@Document(collection = "reviews")
public class Review {
    
    @Id
    private String reviewId;
    
    @Indexed
    private String uid;
    
    private String displayName;
    
    private String photoURL;
    
    private String elasticEventId;
    
    private double rating;
    
    private String reviewDetail;
    
    private Date reviewDate; 
    
    @Transient
    private boolean isReview = false;

    public boolean isIsReview() {
        return isReview;
    }

    public void setIsReview(boolean isReview) {
        this.isReview = isReview;
    }
    
    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getPhotoURL() {
        return photoURL;
    }

    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }
    
    public String getReviewId() {
        return reviewId;
    }

    public void setReviewId(String reviewId) {
        this.reviewId = reviewId;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getElasticEventId() {
        return elasticEventId;
    }

    public void setElasticEventId(String elasticEventId) {
        this.elasticEventId = elasticEventId;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getReviewDetail() {
        return reviewDetail;
    }

    public void setReviewDetail(String reviewDetail) {
        this.reviewDetail = reviewDetail;
    }

    public Date getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(Date reviewDate) {
        this.reviewDate = reviewDate;
    }

    @Override
    public String toString() {
        return "Review{" + "reviewId=" + reviewId + ", uid=" + uid + ", displayName=" + displayName + ", photoUrl=" + photoURL + ", elasticEventId=" + elasticEventId + ", rating=" + rating + ", reviewDetail=" + reviewDetail + ", reviewDate=" + reviewDate + '}';
    }

   
}
