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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;

/**
 *
 * @author Gasana
 */
@Entity
public class Support implements Serializable{

   

    

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String supporterName;
    private String supportCategory;
    private String supportType;
    private Integer supportValue;
    private String supportName;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date supportDate;
    @ManyToOne
    private Village village;
    private String status;
    private String supporterPhone;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dueDate;
    private Integer quantity;
    @OneToMany(mappedBy = "support")
    private List<Resident> residents;
    private Integer FamilyNo;
    private Integer valuePerFamily;
    private Integer qtyPerFamily;
    private Integer fixedSupportValue;
    
  

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSupporterName() {
        return supporterName;
    }

    public void setSupporterName(String supporterName) {
        this.supporterName = supporterName;
    }

    public String getSupportCategory() {
        return supportCategory;
    }

    public void setSupportCategory(String supportCategory) {
        this.supportCategory = supportCategory;
    }

    public String getSupportType() {
        return supportType;
    }

    public void setSupportType(String supportType) {
        this.supportType = supportType;
    }

   

    public Integer getSupportValue() {
        return supportValue;
    }

    public void setSupportValue(Integer supportValue) {
        this.supportValue = supportValue;
    }

    public String getSupportName() {
        return supportName;
    }

    public void setSupportName(String supportName) {
        this.supportName = supportName;
    }

    public Village getVillage() {
        return village;
    }

    public void setVillage(Village village) {
        this.village = village;
    }

    public Date getSupportDate() {
        return supportDate;
    }

    public void setSupportDate(Date supportDate) {
        this.supportDate = supportDate;
    }

    public Integer getFamilyNo() {
        return FamilyNo;
    }

    public void setFamilyNo(Integer FamilyNo) {
        this.FamilyNo = FamilyNo;
    }

  

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSupporterPhone() {
        return supporterPhone;
    }

    public void setSupporterPhone(String supporterPhone) {
        this.supporterPhone = supporterPhone;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public List<Resident> getResidents() {
        return residents;
    }

    public void setResidents(List<Resident> residents) {
        this.residents = residents;
    }

    public Integer getValuePerFamily() {
        return valuePerFamily;
    }

    public void setValuePerFamily(Integer valuePerFamily) {
        this.valuePerFamily = valuePerFamily;
    }

    public Integer getQtyPerFamily() {
        return qtyPerFamily;
    }

    public void setQtyPerFamily(Integer qtyPerFamily) {
        this.qtyPerFamily = qtyPerFamily;
    }

    public Integer getFixedSupportValue() {
        return fixedSupportValue;
    }

    public void setFixedSupportValue(Integer fixedSupportValue) {
        this.fixedSupportValue = fixedSupportValue;
    }
    
    
    
}
