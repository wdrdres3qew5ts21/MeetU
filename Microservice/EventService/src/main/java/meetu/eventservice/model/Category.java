/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meetu.eventservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author Test
 */
@Document("category")
public class Category {
    
    @Id
    private String categoryId;
    
    private String categoryName;
    
    private String categoryLabel;

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryLabel() {
        return categoryLabel;
    }

    public void setCategoryLabel(String categoryLabel) {
        this.categoryLabel = categoryLabel;
    }

    @Override
    public String toString() {
        return "Catagory{" + "catagoryId=" + categoryId + ", catagoryName=" + categoryName + ", catagoryLabelName=" + categoryLabel + '}';
    }

  
}
