/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meetu.userservice.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author wdrdr
 */
public class Persona {

    private byte probationDuration;

    private byte absentInRow;

    private List<String> interestIdea;

    private short sumAllOfParticipateEvent;

    private List<InterestGenreBehavior> interestBehaviorList;

    public byte getProbationDuration() {
        return probationDuration;
    }

    public void setProbationDuration(byte probationDuration) {
        this.probationDuration = probationDuration;
    }

    public byte getAbsentInRow() {
        return absentInRow;
    }

    public void setAbsentInRow(byte absentInRow) {
        this.absentInRow = absentInRow;
    }

    public List<String> getInterestIdea() {
        if (this.interestIdea == null) {
            this.interestIdea = new ArrayList<String>();
        }
        return this.interestIdea;
    }

    public void setInterestIdea(List<String> interestIdea) {
        this.interestIdea = interestIdea;
    }

    public short getSumAllOfParticipateEvent() {
        for (InterestGenreBehavior interestBehavior : this.interestBehaviorList) {
            this.sumAllOfParticipateEvent += interestBehavior.getTotalParticipate();
        }
        return this.sumAllOfParticipateEvent;
    }

    public void setSumAllOfParticipateEvent(short sumAllOfParticipateEvent) {
        this.sumAllOfParticipateEvent = sumAllOfParticipateEvent;
    }

    public List<InterestGenreBehavior> getInterestBehaviorList() {
        if (this.interestBehaviorList == null) {
            this.interestBehaviorList = new ArrayList<InterestGenreBehavior>();
            // { "genre": "art", "totalView": 0, "totalParticipate": 2, "absent": 0 },
            this.interestBehaviorList.add(new InterestGenreBehavior("art", 0, 0, 0));
            this.interestBehaviorList.add(new InterestGenreBehavior("beauty", 0, 0, 0));
            this.interestBehaviorList.add(new InterestGenreBehavior("book", 0, 0, 0));
            this.interestBehaviorList.add(new InterestGenreBehavior("business", 0, 0, 0));
            this.interestBehaviorList.add(new InterestGenreBehavior("family", 0, 0, 0));
            this.interestBehaviorList.add(new InterestGenreBehavior("flim", 0, 0, 0));
            this.interestBehaviorList.add(new InterestGenreBehavior("game", 0, 0, 0));
            this.interestBehaviorList.add(new InterestGenreBehavior("music", 0, 0, 0));
            this.interestBehaviorList.add(new InterestGenreBehavior("photography", 0, 0, 0));
            this.interestBehaviorList.add(new InterestGenreBehavior("social", 0, 0, 0));
            this.interestBehaviorList.add(new InterestGenreBehavior("technology", 0, 0, 0));
        }
        return this.interestBehaviorList;
    }

    public void setInterestBehaviorList(List<InterestGenreBehavior> interestBehaviorList) {
        this.interestBehaviorList = interestBehaviorList;
    }

    @Override
    public String toString() {
        return "Persona{" + "probationDuration=" + probationDuration + ", absentInRow=" + absentInRow + ", interestIdea=" + interestIdea + ", interestBehaviorList=" + interestBehaviorList + '}';
    }

}
