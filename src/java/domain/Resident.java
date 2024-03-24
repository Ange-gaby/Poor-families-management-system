/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 *
 * @author Gasana
 */
@Entity
public class Resident implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String firstname;
    private String lastname;
    private String gender;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dob;
    private String martialStatus;
    private String phoneNumber;
    private String nationalID;
    private String jobStatus;
    private String category;
    private Integer childrenNo=0;
    private String incomeRange;
    private String rentingRange;
    private String educationLevel;
    private Integer mealsPerDayRange=1;
    private String healthInsurance;
    private String residentStatus;
    private String disabilityStatus;
    private String diseasesStatus;
    @ManyToOne
    private Village village;
    @OneToOne(mappedBy = "resident")
    private User user;
    @OneToMany(mappedBy = "resident")
    private List<Allocated> allocates;
    private String code;
    private Integer healthRates;
    private Integer educationRates;
    private Integer economicRates;
    private Integer generalRates;
    private String marriedTo="None";
    
    @OneToMany(mappedBy = "resident")
    private List<Message> messages;
    @ManyToOne
    private Support support;
    private String ageRange;
    private String childrenOccupation;
    private String ownHouse;
    private Integer ownHouseNo=0;
    private Integer ownHouseValue=0;
    private String ownBusiness;
    private Integer ownBusinessNo=0;
    private Integer ownBusinessIncomes=0;
    private String ownLand;
    private Integer ownLandNo=0;
    private Integer ownLandValue=0;
    private String ownAnimal;
    private Integer ownAnimalNo=0;
    private Integer ownAnimalValue=0;
    private Integer monthlyRenting=0;
    private Integer monthlyIncones=0;
    private Integer monthlyExpenses=0;
    
    // EDUCATION
    
    private String schoolLocation;
    private String schoolName="None";
    private Integer schoolExpenses=0;
    private Integer startedYear=0;
    private Integer finishedYear=0;
    
    
    // HEALTH
    
    private String ableToWork;
    private String disabilityName="None";
    private String diseaseName="None";
    private String houseMemberInsured;
    private Integer houseMemberInsuredNo=0;
    
    // OTHER INFO
    
    private Integer houseMemberNo=0;
    private String jobType;
    private Integer employeedHouseMembersNo=0;
    private Integer joblessHouseMembersNo=0;
    private String ableToAffordHygienicMaterials;
    private String ableToAffordWater;
    private String ableToAffordPower;
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

   

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

   
    public String getMartialStatus() {
        return martialStatus;
    }

    public void setMartialStatus(String martialStatus) {
        this.martialStatus = martialStatus;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getNationalID() {
        return nationalID;
    }

    public void setNationalID(String nationalID) {
        this.nationalID = nationalID;
    }

    public String getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(String jobStatus) {
        this.jobStatus = jobStatus;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getChildrenNo() {
        return childrenNo;
    }

    public void setChildrenNo(Integer childrenNo) {
        this.childrenNo = childrenNo;
    }

   

  

    public String getIncomeRange() {
        return incomeRange;
    }

    public void setIncomeRange(String incomeRange) {
        this.incomeRange = incomeRange;
    }

    public String getRentingRange() {
        return rentingRange;
    }

    public void setRentingRange(String rentingRange) {
        this.rentingRange = rentingRange;
    }

    public String getEducationLevel() {
        return educationLevel;
    }

    public void setEducationLevel(String educationLevel) {
        this.educationLevel = educationLevel;
    }

    public Integer getMealsPerDayRange() {
        return mealsPerDayRange;
    }

    public void setMealsPerDayRange(Integer mealsPerDayRange) {
        this.mealsPerDayRange = mealsPerDayRange;
    }

   

    public String getHealthInsurance() {
        return healthInsurance;
    }

    public void setHealthInsurance(String healthInsurance) {
        this.healthInsurance = healthInsurance;
    }

    public Village getVillage() {
        return village;
    }

    public void setVillage(Village village) {
        this.village = village;
    }

    public String getResidentStatus() {
        return residentStatus;
    }

    public void setResidentStatus(String residentStatus) {
        this.residentStatus = residentStatus;
    }

  
    public String getDisabilityStatus() {
        return disabilityStatus;
    }

    public void setDisabilityStatus(String disabilityStatus) {
        this.disabilityStatus = disabilityStatus;
    }

    public String getDiseasesStatus() {
        return diseasesStatus;
    }

    public void setDiseasesStatus(String diseasesStatus) {
        this.diseasesStatus = diseasesStatus;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public List<Allocated> getAllocates() {
        return allocates;
    }

    public void setAllocates(List<Allocated> allocates) {
        this.allocates = allocates;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public Integer getHealthRates() {
        return healthRates;
    }

    public void setHealthRates(Integer healthRates) {
        this.healthRates = healthRates;
    }

    public Integer getEducationRates() {
        return educationRates;
    }

    public void setEducationRates(Integer educationRates) {
        this.educationRates = educationRates;
    }

    public Integer getEconomicRates() {
        return economicRates;
    }

    public void setEconomicRates(Integer economicRates) {
        this.economicRates = economicRates;
    }

    public Integer getGeneralRates() {
        return generalRates;
    }

    public void setGeneralRates(Integer generalRates) {
        this.generalRates = generalRates;
    }

    public Support getSupport() {
        return support;
    }

    public void setSupport(Support support) {
        this.support = support;
    }

    public String getMarriedTo() {
        return marriedTo;
    }

    public void setMarriedTo(String marriedTo) {
        this.marriedTo = marriedTo;
    }

    public String getAgeRange() {
        return ageRange;
    }

    public void setAgeRange(String ageRange) {
        this.ageRange = ageRange;
    }

    public String getChildrenOccupation() {
        return childrenOccupation;
    }

    public void setChildrenOccupation(String childrenOccupation) {
        this.childrenOccupation = childrenOccupation;
    }

    public String getOwnHouse() {
        return ownHouse;
    }

    public void setOwnHouse(String ownHouse) {
        this.ownHouse = ownHouse;
    }

    public Integer getOwnHouseNo() {
        return ownHouseNo;
    }

    public void setOwnHouseNo(Integer ownHouseNo) {
        this.ownHouseNo = ownHouseNo;
    }

    public Integer getOwnHouseValue() {
        return ownHouseValue;
    }

    public void setOwnHouseValue(Integer ownHouseValue) {
        this.ownHouseValue = ownHouseValue;
    }

    public String getOwnBusiness() {
        return ownBusiness;
    }

    public void setOwnBusiness(String ownBusiness) {
        this.ownBusiness = ownBusiness;
    }

    public Integer getOwnBusinessNo() {
        return ownBusinessNo;
    }

    public void setOwnBusinessNo(Integer ownBusinessNo) {
        this.ownBusinessNo = ownBusinessNo;
    }

    public Integer getOwnBusinessIncomes() {
        return ownBusinessIncomes;
    }

    public void setOwnBusinessIncomes(Integer ownBusinessIncomes) {
        this.ownBusinessIncomes = ownBusinessIncomes;
    }

    public String getOwnLand() {
        return ownLand;
    }

    public void setOwnLand(String ownLand) {
        this.ownLand = ownLand;
    }

    public Integer getOwnLandNo() {
        return ownLandNo;
    }

    public void setOwnLandNo(Integer ownLandNo) {
        this.ownLandNo = ownLandNo;
    }

    public Integer getOwnLandValue() {
        return ownLandValue;
    }

    public void setOwnLandValue(Integer ownLandValue) {
        this.ownLandValue = ownLandValue;
    }

    public String getOwnAnimal() {
        return ownAnimal;
    }

    public void setOwnAnimal(String ownAnimal) {
        this.ownAnimal = ownAnimal;
    }

    public Integer getOwnAnimalNo() {
        return ownAnimalNo;
    }

    public void setOwnAnimalNo(Integer ownAnimalNo) {
        this.ownAnimalNo = ownAnimalNo;
    }

    public Integer getOwnAnimalValue() {
        return ownAnimalValue;
    }

    public void setOwnAnimalValue(Integer ownAnimalValue) {
        this.ownAnimalValue = ownAnimalValue;
    }

    public Integer getMonthlyRenting() {
        return monthlyRenting;
    }

    public void setMonthlyRenting(Integer monthlyRenting) {
        this.monthlyRenting = monthlyRenting;
    }

    public Integer getMonthlyIncones() {
        return monthlyIncones;
    }

    public void setMonthlyIncones(Integer monthlyIncones) {
        this.monthlyIncones = monthlyIncones;
    }

    public Integer getMonthlyExpenses() {
        return monthlyExpenses;
    }

    public void setMonthlyExpenses(Integer monthlyExpenses) {
        this.monthlyExpenses = monthlyExpenses;
    }

    public String getSchoolLocation() {
        return schoolLocation;
    }

    public void setSchoolLocation(String schoolLocation) {
        this.schoolLocation = schoolLocation;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public Integer getSchoolExpenses() {
        return schoolExpenses;
    }

    public void setSchoolExpenses(Integer schoolExpenses) {
        this.schoolExpenses = schoolExpenses;
    }

    public Integer getStartedYear() {
        return startedYear;
    }

    public void setStartedYear(Integer startedYear) {
        this.startedYear = startedYear;
    }

    public Integer getFinishedYear() {
        return finishedYear;
    }

    public void setFinishedYear(Integer finishedYear) {
        this.finishedYear = finishedYear;
    }

    public String getAbleToWork() {
        return ableToWork;
    }

    public void setAbleToWork(String ableToWork) {
        this.ableToWork = ableToWork;
    }

    public String getDisabilityName() {
        return disabilityName;
    }

    public void setDisabilityName(String disabilityName) {
        this.disabilityName = disabilityName;
    }

    public String getDiseaseName() {
        return diseaseName;
    }

    public void setDiseaseName(String diseaseName) {
        this.diseaseName = diseaseName;
    }

    public String getHouseMemberInsured() {
        return houseMemberInsured;
    }

    public void setHouseMemberInsured(String houseMemberInsured) {
        this.houseMemberInsured = houseMemberInsured;
    }

    public Integer getHouseMemberInsuredNo() {
        return houseMemberInsuredNo;
    }

    public void setHouseMemberInsuredNo(Integer houseMemberInsuredNo) {
        this.houseMemberInsuredNo = houseMemberInsuredNo;
    }

    public Integer getHouseMemberNo() {
        return houseMemberNo;
    }

    public void setHouseMemberNo(Integer houseMemberNo) {
        this.houseMemberNo = houseMemberNo;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public Integer getEmployeedHouseMembersNo() {
        return employeedHouseMembersNo;
    }

    public void setEmployeedHouseMembersNo(Integer employeedHouseMembersNo) {
        this.employeedHouseMembersNo = employeedHouseMembersNo;
    }

    public Integer getJoblessHouseMembersNo() {
        return joblessHouseMembersNo;
    }

    public void setJoblessHouseMembersNo(Integer joblessHouseMembersNo) {
        this.joblessHouseMembersNo = joblessHouseMembersNo;
    }

    public String getAbleToAffordHygienicMaterials() {
        return ableToAffordHygienicMaterials;
    }

    public void setAbleToAffordHygienicMaterials(String ableToAffordHygienicMaterials) {
        this.ableToAffordHygienicMaterials = ableToAffordHygienicMaterials;
    }

    public String getAbleToAffordWater() {
        return ableToAffordWater;
    }

    public void setAbleToAffordWater(String ableToAffordWater) {
        this.ableToAffordWater = ableToAffordWater;
    }

    public String getAbleToAffordPower() {
        return ableToAffordPower;
    }

    public void setAbleToAffordPower(String ableToAffordPower) {
        this.ableToAffordPower = ableToAffordPower;
    }

   

  
    

    
    
}
