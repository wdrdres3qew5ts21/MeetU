/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meetu.communityservice.model;

import java.util.HashMap;
import java.util.List;

/**
 *
 * @author wdrdr
 */
class PollChoices {

    private int totalVote;

    private HashMap<String, Integer> pollChoiceDetail;

    public int getTotalVote() {
        return totalVote;
    }

    public void setTotalVote(int totalVote) {
        this.totalVote = totalVote;
    }

    public HashMap<String, Integer> createNewChoice(String choiceTopic) {
        this.pollChoiceDetail.put(choiceTopic, 0);
        return this.pollChoiceDetail;
    }

    public void voteToChoice(String choiceKey) {
        int voteNumberOfChoice = this.pollChoiceDetail.get(choiceKey);
        this.pollChoiceDetail.put(choiceKey, voteNumberOfChoice + 1);
    }

    public HashMap<String, Integer> getPollChoiceDetail() {
        return pollChoiceDetail;
    }

    public void setPollChoiceDetail(HashMap<String, Integer> pollChoiceDetail) {
        this.pollChoiceDetail = pollChoiceDetail;
    }

    @Override
    public String toString() {
        return "PollChoices{" + "totalVote=" + totalVote + ", pollChoiceDetail=" + pollChoiceDetail + '}';
    }

}
