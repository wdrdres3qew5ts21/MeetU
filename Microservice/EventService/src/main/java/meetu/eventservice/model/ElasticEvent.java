/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meetu.eventservice.model;

/**
 *
 * @author wdrdr
 */
public class ElasticEvent {
    
    private String eventDetail;
    
    private long price;

    public String getEventDetail() {
        return eventDetail;
    }

    public void setEventDetail(String eventDetail) {
        this.eventDetail = eventDetail;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "ElasticEvent{" + "eventDetail=" + eventDetail + ", price=" + price + '}';
    }
    
    
    
    
}
