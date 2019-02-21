/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meetu.eventservice.model;

import org.springframework.data.annotation.Id;

/**
 *
 * @author wdrdr
 */
public class Organize {

    @Id
    private String organizeId;

    private String organizeName;

    private String organizeDetail;

    private String organizeTags;

    private String organizeImageProfile;

    private String organizeImageCover;

    public String getOrganizeId() {
        return organizeId;
    }

    public void setOrganizeId(String organizeId) {
        this.organizeId = organizeId;
    }

    public String getOrganizeName() {
        return organizeName;
    }

    public void setOrganizeName(String organizeName) {
        this.organizeName = organizeName;
    }

    public String getOrganizeDetail() {
        return organizeDetail;
    }

    public void setOrganizeDetail(String organizeDetail) {
        this.organizeDetail = organizeDetail;
    }

    public String getOrganizeTags() {
        return organizeTags;
    }

    public void setOrganizeTags(String organizeTags) {
        this.organizeTags = organizeTags;
    }

    public String getOrganizeImageProfile() {
        return organizeImageProfile;
    }

    public void setOrganizeImageProfile(String organizeImageProfile) {
        this.organizeImageProfile = organizeImageProfile;
    }

    public String getOrganizeImageCover() {
        return organizeImageCover;
    }

    public void setOrganizeImageCover(String organizeImageCover) {
        this.organizeImageCover = organizeImageCover;
    }

    @Override
    public String toString() {
        return "Organize{" + "organizeId=" + organizeId + ", organizeName=" + organizeName + ", organizeDetail=" + organizeDetail + ", organizeTags=" + organizeTags + ", organizeImageProfile=" + organizeImageProfile + ", organizeImageCover=" + organizeImageCover + '}';
    }
    
    

}
