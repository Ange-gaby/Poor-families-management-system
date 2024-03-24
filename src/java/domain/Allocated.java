/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author Gasana
 */
@Entity
public class Allocated implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String status;
    private String residentNames;
    private String residentCategory;
    private String residentEducationLevel;
    private String jobStatus;
    private String residentHealthInsuranceCard;
    private String residentDisibailityStatus;
    private String residentDiseasesStatus;
    private String gender;
    private String nid;
    private String phoneNumber;
    @ManyToOne
    private Resident resident;
    @ManyToOne
    private Village village;
    private String supportStatus;
    private String ownBusiness;
    private String ownHouse;
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Resident getResident() {
        return resident;
    }

    public void setResident(Resident resident) {
        this.resident = resident;
    }

    public Village getVillage() {
        return village;
    }

    public void setVillage(Village village) {
        this.village = village;
    }

    public String getResidentNames() {
        return residentNames;
    }

    public void setResidentNames(String residentNames) {
        this.residentNames = residentNames;
    }

    public String getResidentCategory() {
        return residentCategory;
    }

    public void setResidentCategory(String residentCategory) {
        this.residentCategory = residentCategory;
    }

    public String getResidentEducationLevel() {
        return residentEducationLevel;
    }

    public void setResidentEducationLevel(String residentEducationLevel) {
        this.residentEducationLevel = residentEducationLevel;
    }

    public String getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(String jobStatus) {
        this.jobStatus = jobStatus;
    }

    public String getResidentHealthInsuranceCard() {
        return residentHealthInsuranceCard;
    }

    public void setResidentHealthInsuranceCard(String residentHealthInsuranceCard) {
        this.residentHealthInsuranceCard = residentHealthInsuranceCard;
    }

    public String getResidentDisibailityStatus() {
        return residentDisibailityStatus;
    }

    public void setResidentDisibailityStatus(String residentDisibailityStatus) {
        this.residentDisibailityStatus = residentDisibailityStatus;
    }

    public String getResidentDiseasesStatus() {
        return residentDiseasesStatus;
    }

    public void setResidentDiseasesStatus(String residentDiseasesStatus) {
        this.residentDiseasesStatus = residentDiseasesStatus;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getNid() {
        return nid;
    }

    public void setNid(String nid) {
        this.nid = nid;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getSupportStatus() {
        return supportStatus;
    }

    public void setSupportStatus(String supportStatus) {
        this.supportStatus = supportStatus;
    }

    public String getOwnBusiness() {
        return ownBusiness;
    }

    public void setOwnBusiness(String ownBusiness) {
        this.ownBusiness = ownBusiness;
    }

    public String getOwnHouse() {
        return ownHouse;
    }

    public void setOwnHouse(String ownHouse) {
        this.ownHouse = ownHouse;
    }
    
    
   
}
