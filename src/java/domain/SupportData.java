/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;

/**
 *
 * @author Gasana
 */
@Entity
public class SupportData implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String supportName;
    private String supporterName;
    private Integer quantity;
    private Integer supportValue;
    private Integer resident_id;
    private Integer support_id;
    private Date claimedDate;
    private String residentName;
    private String villageName;
    @Column(unique = true)
    private String residentkey;
    private String supportCategory;
    private Integer village_id;
    private String gender;
    private String educationLevel;
    private String healthInsuranceCard;
    private String ownHouse;
    private String ownBusiness;
    private String jobStatus;
    private String disabled;
    private String diseases;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSupportName() {
        return supportName;
    }

    public void setSupportName(String supportName) {
        this.supportName = supportName;
    }

    public String getSupporterName() {
        return supporterName;
    }

    public void setSupporterName(String supporterName) {
        this.supporterName = supporterName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

   

    public Integer getResident_id() {
        return resident_id;
    }

    public void setResident_id(Integer resident_id) {
        this.resident_id = resident_id;
    }

    public Integer getSupport_id() {
        return support_id;
    }

    public void setSupport_id(Integer support_id) {
        this.support_id = support_id;
    }

    public String getResidentName() {
        return residentName;
    }

    public void setResidentName(String residentName) {
        this.residentName = residentName;
    }

    public String getVillageName() {
        return villageName;
    }

    public void setVillageName(String villageName) {
        this.villageName = villageName;
    }

    public Integer getSupportValue() {
        return supportValue;
    }

    public void setSupportValue(Integer supportValue) {
        this.supportValue = supportValue;
    }

    public String getResidentkey() {
        return residentkey;
    }

    public void setResidentkey(String residentkey) {
        this.residentkey = residentkey;
    }

    public Date getClaimedDate() {
        return claimedDate;
    }

    public void setClaimedDate(Date claimedDate) {
        this.claimedDate = claimedDate;
    }

    public String getSupportCategory() {
        return supportCategory;
    }

    public void setSupportCategory(String supportCategory) {
        this.supportCategory = supportCategory;
    }

    public Integer getVillage_id() {
        return village_id;
    }

    public void setVillage_id(Integer village_id) {
        this.village_id = village_id;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEducationLevel() {
        return educationLevel;
    }

    public void setEducationLevel(String educationLevel) {
        this.educationLevel = educationLevel;
    }

    public String getHealthInsuranceCard() {
        return healthInsuranceCard;
    }

    public void setHealthInsuranceCard(String healthInsuranceCard) {
        this.healthInsuranceCard = healthInsuranceCard;
    }

    public String getOwnHouse() {
        return ownHouse;
    }

    public void setOwnHouse(String ownHouse) {
        this.ownHouse = ownHouse;
    }

    public String getOwnBusiness() {
        return ownBusiness;
    }

    public void setOwnBusiness(String ownBusiness) {
        this.ownBusiness = ownBusiness;
    }

    public String getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(String jobStatus) {
        this.jobStatus = jobStatus;
    }

    public String getDisabled() {
        return disabled;
    }

    public void setDisabled(String disabled) {
        this.disabled = disabled;
    }

    public String getDiseases() {
        return diseases;
    }

    public void setDiseases(String diseases) {
        this.diseases = diseases;
    }

  
}
