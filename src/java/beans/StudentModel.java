package beans;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import dao.CellDao;
import dao.DistrictDao;
import dao.ProvinceDao;
import dao.SectorDao;
import dao.VillageDao;
import domain.Cell;

import domain.District;

import domain.Province;
import domain.Sector;
import domain.Village;

import java.util.List;
import java.util.stream.Collectors;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.transaction.Transactional;

/**
 *
 * @author Theophile
 */
@ManagedBean(name = "st")
@SessionScoped
@Transactional
public class StudentModel {
    private int studentId;
    private int provinceId=1;
    private int districtId=1;
    private int sectorId=1;
    private int cellId=1;
    private int villageId=1;
    private String provinceName;
    private ProvinceDao provinceDao = new ProvinceDao();
    private DistrictDao districtDao = new DistrictDao();
    private SectorDao sectorDao = new SectorDao();
    private CellDao cellDao = new CellDao();
    private VillageDao villageDao = new VillageDao();

    public int getCellId() {
        return cellId;
    }

    public void setCellId(int cellId) {
        this.cellId = cellId;
    }

    public int getVillageId() {
        return villageId;
    }

    public void setVillageId(int villageId) {
        this.villageId = villageId;
    }

    public CellDao getCellDao() {
        return cellDao;
    }

    public void setCellDao(CellDao cellDao) {
        this.cellDao = cellDao;
    }

    public VillageDao getVillageDao() {
        return villageDao;
    }

    public void setVillageDao(VillageDao villageDao) {
        this.villageDao = villageDao;
    }
   
    
    
  
    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

 

    public int getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(int provinceId) {
        this.provinceId = provinceId;
    }

    public int getDistrictId() {
        return districtId;
    }

    public void setDistrictId(int districtId) {
        this.districtId = districtId;
//        this.student.setDst(districtDao.find(District.class, this.districtId));
    }


    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public List<Village> getVillages(){
        return villageDao.findAll(Village.class);
    }


    public ProvinceDao getProvinceDao() {
        return provinceDao;
    }

    public void setProvinceDao(ProvinceDao provinceDao) {
        this.provinceDao = provinceDao;
    }

    public DistrictDao getDistrictDao() {
        return districtDao;
    }

    public void setDistrictDao(DistrictDao districtDao) {
        this.districtDao = districtDao;
    }
   public List<Province> getProvinces(){
     return provinceDao.allProvinces();
   }
   
   public List<District> getDistrictsInProvince(){
     Province prov = new ProvinceDao().findOne(Province.class, provinceId);
      List<District> districts = prov.getDistricts().stream().distinct().collect(Collectors.toList());
     return districts;
   }
   
   public List<Sector> getSectorsInDistrict(){
       District  dist = new DistrictDao().findOne(District.class, districtId);
       List<Sector> secs;
       secs = dist.getSectors().stream().distinct().collect(Collectors.toList());
       return secs;
   }
   
   public List<Cell> getCellsInSector(){
       Sector  sects = new SectorDao().findOne(Sector.class, sectorId);   
       return sects.getCells().stream().distinct().collect(Collectors.toList());
   }
   
   public List<Village> getVillagesInCell(){
       Cell  cll = new CellDao().findOne(Cell.class, cellId);
       return cll.getVillages().stream().distinct().collect(Collectors.toList());
   }

    public int getSectorId() {
        return sectorId;
    }

    public void setSectorId(int sectorId) {
        this.sectorId = sectorId;
    }

    public SectorDao getSectorDao() {
        return sectorDao;
    }

    public void setSectorDao(SectorDao sectorDao) {
        this.sectorDao = sectorDao;
    }

 
      
 }
   
   
    

