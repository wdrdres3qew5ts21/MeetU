/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meetu.userservice.model;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author wdrdr
 */
@Document(collection = "organizes")
public class Organize {

    @Id
    private String organizeId;

    @Indexed(unique = true)
    private String organizeName;

    private String organizeDetail;

    private List<String> organizeTags;

    private String organizeImageProfile;

    private String organizeImageCover;
    
    private Admin organizeOwner;

    private List<Admin> adminList;
    
    private String email;
    
    private String website;
    
    private String line;
    
    private String twitter;
    
    private String instragram;
    
    private String phone;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getInstragram() {
        return instragram;
    }

    public void setInstragram(String instragram) {
        this.instragram = instragram;
    }
    
    

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    
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

    public List<Admin> getAdminList() {
        if(adminList == null){
            return new ArrayList<Admin>();
        }
        return adminList;
    }

    public void setAdminList(List<Admin> adminList) {
        this.adminList = adminList;
    }

    public String getOrganizeDetail() {
        return organizeDetail;
    }

    public void setOrganizeDetail(String organizeDetail) {
        this.organizeDetail = organizeDetail;
    }

    public List<String> getOrganizeTags() {
        return organizeTags;
    }

    public void setOrganizeTags(List<String> organizeTags) {
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

    public Admin getOrganizeOwner() {
        return organizeOwner;
    }

    public void setOrganizeOwner(Admin organizeOwner) {
        this.organizeOwner = organizeOwner;
    }

    @Override
    public String toString() {
        return "Organize{" + "organizeId=" + organizeId + ", organizeName=" + organizeName + ", organizeDetail=" + organizeDetail + ", organizeTags=" + organizeTags + ", organizeImageProfile=" + organizeImageProfile + ", organizeImageCover=" + organizeImageCover + ", organizeOwner=" + organizeOwner + ", adminList=" + adminList + '}';
    }
    
}
