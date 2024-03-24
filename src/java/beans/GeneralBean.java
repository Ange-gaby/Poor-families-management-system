/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.AllocatedDao;
import dao.CellDao;
import dao.DistrictDao;
import dao.MessageDao;
import dao.NotificationDao;
import dao.NumeratorDao;
import dao.ProvinceDao;
import dao.ResidentDao;
import dao.SectorDao;
import dao.SupportDao;
import dao.SupportDataDao;
import dao.UserDao;
import dao.VillageDao;
import domain.Allocated;
import domain.Cell;
import domain.District;
import domain.Message;
import domain.Notification;
import domain.Numerator;
import domain.Province;
import domain.Resident;
import domain.Sector;
import domain.Support;
import domain.SupportData;
import domain.User;
import domain.Village;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import sms.SendSMS;

/**
 *
 * @author Gasana
 */
@ManagedBean(name = "gb")
@SessionScoped
public class GeneralBean {
    private Province province;
    private District district;
   
    private Sector sector;
    private Cell cell;
    private Village village;
    private User user;
    private Resident resident;
    private UserDao userDao;
    private ResidentDao residentDao;
    private String username;
    private String confirmPassword;
    private String password;
    private List<Province> provinces = new ArrayList<>();    
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
    private List<Resident> residents = new ArrayList<>();
    private List<Resident> rsdnts = new ArrayList<>();
    private List<Numerator> numerators = new ArrayList<>();
    private String villageName;
    private Numerator numerator;
    private NumeratorDao numeratorDao;
    private SendSMS sendsms;
    private final String from_number = "+19197596496";
    private Support support;
    private SupportDao supportDao;
    private List<Support> supports = new ArrayList<>();
    private String nlast;
    private String nfirst;
    private String ngender;
    private String nphone;
    private String nnid;
    private String nstat;
    private String districtName;
    private String sectorName;
    private String cellName;
    private String provinceNam;
    private String usernam;
    private String passwrd;
    private Allocated allocated;
    private AllocatedDao allocatedDao;
    private AllocatedDao alloDao;
    private List<Allocated> allocates = new ArrayList<>();
    private String fn;
    private String ln;
    private String cat;
    private String dis;
    private String dise;
    private String hi;
    private String mp;
    private String ed;
    private String ph;
    private String jb;
    private String icr;
    private String ms;
    private String cn;
    private Date db;
    private String nd;
    private String gndr;
    private String code;
    private String numeratorName;
    private String numeratorUsername;
    private String numeratorPhone;
    
    private Message message;
    private MessageDao messageDao;
    private List<Message> messages = new ArrayList<>();
    private String senderCode;
    private Notification notification;
    private NotificationDao notificationDao;
    private List<Notification> notifications = new ArrayList<>();
    private List<SupportData> supportDatas = new ArrayList<>();
    private SupportData supportData;
    private SupportDataDao supportDataDao;
    private String suppName;
    private String suppType;
    private Date suppDueDate;
    private String suppValue;
    private String supperName;
    private Integer suppQuantity;
    private String suppFamCateg;
    private String supperPhone;
    private Integer suppVal;
    
    
     public GeneralBean() {
        supportData = new SupportData();
        supportDataDao = new SupportDataDao();
        province = new Province();
        sector = new Sector();
        cell = new Cell();
        village = new Village();
        user = new User();
        userDao = new UserDao();
        residentDao = new ResidentDao();
        resident = new Resident();
        numerator = new Numerator();
        numeratorDao = new NumeratorDao();
        sendsms = new SendSMS();
        support = new Support();
        supportDao = new SupportDao();
        allocated = new Allocated();
        allocatedDao = new AllocatedDao();
        message = new Message();
        messageDao = new MessageDao();
        notification = new Notification();
        notificationDao = new NotificationDao();
        alloDao = new AllocatedDao();
    }

  

     public String loggedInNames(){
         try {
            User usr = userDao.getUsername(user.getUsername());
            resident = usr.getResident();
            fn = resident.getFirstname()+" "+resident.getLastname();
             System.out.println("FN "+fn);
            return fn;
         } catch (Exception e) {
             System.out.println("User details errorr "+e);
             return fn;
         }
     }
  

      
      public String login() throws IOException {
       
          try {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        User usr = userDao.getUsername(user.getUsername());
        if(usr!=null){
            if(usr.getPassword().matches(createAnonymous(user.getPassword())) && usr.getType().equalsIgnoreCase("Authority")){
                username = usr.getUsername();
            
               villageName = usr.getVillage().getName();
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("session","");
                ec.redirect(ec.getRequestContextPath() + "/faces/authority/authority-dashboard.xhtml");
                return "/faces/authority/authority-dashboard.xhtml";
                
            }else if(usr.getPassword().matches(createAnonymous(user.getPassword())) && usr.getType().equalsIgnoreCase("Numerator")&& usr.getNumerator().getStatus().equalsIgnoreCase("Active")){
               username = usr.getUsername();          
               villageName = usr.getVillage().getName();
               nfirst = usr.getNumerator().getFirstname();
               nlast = usr.getNumerator().getLastname();
               ngender = usr.getNumerator().getGender();
               nphone = usr.getNumerator().getPhoneNumber();
               nnid = usr.getNumerator().getNationalID();
               nstat = usr.getNumerator().getStatus();
               numerator = usr.getNumerator();
               
               FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("session","");
               ec.redirect(ec.getRequestContextPath() + "/faces/numerator/numerator-dashboard.xhtml");
               return "/faces/numerator/numerator-dashboard.xhtml";
            }else if(usr.getPassword().matches(createAnonymous(user.getPassword())) && usr.getType().equalsIgnoreCase("Resident")){
                username = usr.getUsername();
                resident = usr.getResident();
               
               villageName = usr.getVillage().getName();
               village = usr.getVillage();
               cellName = usr.getVillage().getCell().getName();
               sectorName = usr.getVillage().getCell().getSector().getName();
               numerator = numeratorDao.getNumeratorByVillageId(village.getId());
               numeratorName = numerator.getFirstname()+" "+numerator.getLastname();
               numeratorPhone = numerator.getPhoneNumber();
               numeratorUsername = numerator.getStatus();
               resident = usr.getResident();
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("session","");
                ec.redirect(ec.getRequestContextPath() + "/faces/citizen/resident-dashboard.xhtml");
                return "/faces/citizen/resident-dashboard.xhtml";
            }
            else{
                System.out.println("Password does not match");
                return "login.xhtml";
            }
        }else{
            System.out.println("User does not exist");
            return "login.xhtml";
        }
        
          } 
          catch (Exception e) {
              System.out.println("EEERRROR LOGIN "+e);
              return "login.xhtml";
          }
          
    }



  public String todayDate(){
    LocalDateTime date = LocalDateTime.now();
    
    String dateT =""+date.getDayOfMonth()+"-"+date.getMonth()+"-"+date.getYear();
    // print out today's date
    return dateT;
    }
    
    
    public String villageRegister(){
        
        try {
            if(!this.user.getUsername().isEmpty() && this.user.getPassword()!=null && !this.user.getPassword().isEmpty() && this.user.getPassword()!=null 
                && confirmPassword!=null){      
            if(this.user.getPassword().matches(confirmPassword)){
                this.user.setActive(true);
                this.user.setPassword(createAnonymous(this.user.getPassword()));
                Village vllg = villageDao.findOne(Village.class, villageId);
                this.user.setVillage(vllg);
                this.user.setType("Authority");
                userDao.recordObject(user);
                successMessage("Village Authority Created Successfuly");
                return "register-village.xhtml";
            }else{
                warningMessage("Passwords do not match");
                System.out.println("Passwords dont matches");
                return "register-village.xhtml";
            }
        }else{
               warningMessage("Some fields are empty");
            System.out.println("Some fields are missing");
            return "register-village.xhtml";
        }
        } catch (Exception e) {
            errorMessage("Failed to create Village Account");
            return "EEEEERRORRR REGISTERING VILLAGE "+e;
        }
        
        
    }
    
    
    public String disableNumerator(Integer id){
        try {
             User usr = userDao.getUsername(user.getUsername());
             numerator = numeratorDao.getNumeratorById(id);
             numerator.setStatus("Disabled");
             numeratorDao.updateObject(numerator);
             successMessage(numerator.getFirstname()+" "+numerator.getLastname()+" Is Disabled");
             return "numerators.xhtml";
        } catch (Exception e) {
            errorMessage("Failed To Disable Numerator");
            System.out.println("Numerator Disable "+e);
            return "numerators.xhtml";
        }
    }
    public String activateNumerator(Integer id){
        try {
             User usr = userDao.getUsername(user.getUsername());
             numerator = numeratorDao.getNumeratorById(id);
             numerator.setStatus("Active");
             numeratorDao.updateObject(numerator);
             successMessage(numerator.getFirstname()+" "+numerator.getLastname()+" Is Activated");
             return "disabled-numerator.xhtml";
        } catch (Exception e) {
            System.out.println("Numerator Disable "+e);
            errorMessage("Failed To Activate Numerator");
            return "disabled-numerator.xhtml";
        }
    }
    
    
    public String claimSupportEconomic(Integer supportId){
         SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");  
         Date todayDate = new Date();
         
        try {
            User usr = userDao.getUsername(user.getUsername());
            village = usr.getVillage();
            Integer vId = village.getId();
            resident = usr.getResident();
            Integer resid = resident.getId();
            
            support = supportDao.findOne(Support.class, supportId);
            Integer sId = support.getId();
            
           
            String key = resident.getId()+"-"+"EC-C-"+support.getId();
            
            supportData = supportDataDao.getSupportDataByKey(key);
            SupportData suppData = new SupportData();
            if (supportData == null){
            suppData.setClaimedDate(todayDate);
            suppData.setResidentName(resident.getFirstname()+" "+resident.getLastname());
            suppData.setResident_id(resident.getId());
            suppData.setResidentkey(key);
            suppData.setVillageName(village.getName());
            suppData.setVillage_id(village.getId());
            suppData.setQuantity(support.getQtyPerFamily());
            suppData.setSupport_id(support.getId());
            suppData.setSupportName(support.getSupportName());
            suppData.setSupporterName(support.getSupporterName());
            suppData.setSupportValue(support.getValuePerFamily());
            suppData.setSupportCategory("Economic");
            suppData.setGender(resident.getGender());
            suppData.setEducationLevel(resident.getEducationLevel());
            suppData.setJobStatus(resident.getJobStatus());
            suppData.setOwnBusiness(resident.getOwnBusiness());
            suppData.setOwnHouse(resident.getOwnHouse());
            suppData.setHealthInsuranceCard(resident.getHealthInsurance());
            suppData.setDisabled(resident.getDisabilityStatus());
            suppData.setDiseases(resident.getDiseasesStatus());
            supportDataDao.recordObject(suppData);
            successMessage("Support is claimed");
                economicRemoveSupportQty(key, resid, vId, sId);
            }else{
            errorMessage("You have already received this support");
            }
           
            
            
            return "economic-support";
        } catch (Exception e) {
            return "economic-support";
        }
 
    }
    
    public String claimSupportHealth(Integer supportId){
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");  
         Date todayDate = new Date();
         
        try {
            User usr = userDao.getUsername(user.getUsername());
            village = usr.getVillage();
            Integer vid = village.getId();
            resident = usr.getResident();
            Integer id = resident.getId();
            support = supportDao.findOne(Support.class, supportId);
            Integer sId = support.getId();
            String key = resident.getId()+"-"+"H-C-"+support.getId();
            supportData = supportDataDao.getSupportDataByKey(key);
            SupportData suppData = new SupportData();
            if (supportData==null){
            suppData.setClaimedDate(todayDate);
            suppData.setResidentName(resident.getFirstname()+" "+resident.getLastname());
            suppData.setResident_id(resident.getId());
            suppData.setResidentkey(key);
            suppData.setVillageName(village.getName());
            suppData.setVillage_id(village.getId());
            suppData.setQuantity(support.getQtyPerFamily());
            suppData.setSupport_id(support.getId());
            suppData.setSupportName(support.getSupportName());
            suppData.setSupporterName(support.getSupporterName());
            suppData.setSupportValue(support.getValuePerFamily());
            suppData.setSupportCategory("Health");
            suppData.setGender(resident.getGender());
            suppData.setEducationLevel(resident.getEducationLevel());
            suppData.setJobStatus(resident.getJobStatus());
            suppData.setOwnBusiness(resident.getOwnBusiness());
            suppData.setOwnHouse(resident.getOwnHouse());
            suppData.setHealthInsuranceCard(resident.getHealthInsurance());
            suppData.setDisabled(resident.getDisabilityStatus());
            suppData.setDiseases(resident.getDiseasesStatus());
            supportDataDao.recordObject(suppData);
            successMessage("Support is claimed");
            
            healthRemoveSupportQty(key,id,vid,sId);
            return "health-support";
           
            }else{
            errorMessage("You have already received this support");
            return "health-support";
            }
           
            
            
            
        } catch (Exception e) {
            return "health-support";
        }
 
    }
    
    public String claimSupportEducation(Integer supportId){
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");  
         Date todayDate = new Date();
         
        try {
            User usr = userDao.getUsername(user.getUsername());
            village = usr.getVillage();
            Integer vId = village.getId();
            
            resident = usr.getResident();
            Integer resid = resident.getId();
            support = supportDao.findOne(Support.class, supportId);
            Integer sId = support.getId();
            
            String key = resident.getId()+"-"+"E-C-"+support.getId();
            supportData = supportDataDao.getSupportDataByKey(key);
            SupportData suppData = new SupportData();
            if (supportData==null){
            suppData.setClaimedDate(todayDate);
            suppData.setResidentName(resident.getFirstname()+" "+resident.getLastname());
            suppData.setResident_id(resident.getId());
            suppData.setResidentkey(key);
            suppData.setVillageName(village.getName());
            suppData.setVillage_id(village.getId());
            suppData.setQuantity(support.getQtyPerFamily());
            suppData.setSupport_id(support.getId());
            suppData.setSupportName(support.getSupportName());
            suppData.setSupporterName(support.getSupporterName());
            suppData.setSupportValue(support.getValuePerFamily());
            suppData.setSupportCategory("Education");
            suppData.setGender(resident.getGender());
            suppData.setEducationLevel(resident.getEducationLevel());
            suppData.setJobStatus(resident.getJobStatus());
            suppData.setOwnBusiness(resident.getOwnBusiness());
            suppData.setOwnHouse(resident.getOwnHouse());
            suppData.setHealthInsuranceCard(resident.getHealthInsurance());
            suppData.setDisabled(resident.getDisabilityStatus());
            suppData.setDiseases(resident.getDiseasesStatus());
            supportDataDao.recordObject(suppData);
            successMessage("Support is claimed");
             educationRemoveSupportQty(key,resid,vId,sId);
            }else{
                System.out.println("WWWWWWWANR");
            errorMessage("You have already received this support");
            }
           
            
            
            return "education-support";
        } catch (Exception e) {
            return "education-support";
        }
 
    }
    
     public String numeratorRegister(){
        
        try {
                User usr = userDao.getUsername(user.getUsername()); 
                village = usr.getVillage();
                numerator.setVillage(village);
                numerator.setStatus("Active");
                numeratorDao.recordObject(numerator);
                successMessage("Numerator Created Successfuly");
                User us = new User();
                us.setActive(Boolean.TRUE);
                us.setUsername(numerator.getFirstname());
                us.setPassword(createAnonymous("123"));
                us.setType("Numerator");
                us.setVillage(numerator.getVillage());
                us.setNumerator(numerator);
                userDao.recordObject(us);
                
                String to_number = numerator.getPhoneNumber();
                String body = "Account created successfully! \n Username : "+numerator.getFirstname()+"\n Password : 123";
                sendsms.sendSMS(to_number, from_number, body);
                return "create-numerator.xhtml";
            }

         catch (Exception e) {
//             errorMessage("Failed to create Numerator");
            return "create-numerator.xhtml";
        }
        
        
    }
     
    public String addSupport(){
         SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
         Date todayDate = new Date();
         try {
         User usr = userDao.getUsername(user.getUsername());
         village = usr.getVillage();
         support.setSupportDate(todayDate);
         support.setStatus("Available");
         support.setVillage(village);
         supportDao.recordObject(support);
         
         Integer sv = support.getSupportValue();
         Integer fn = support.getFamilyNo();
         Integer spf = sv/fn;
         Integer fv = support.getSupportValue();
         support.setValuePerFamily(spf);
         support.setFixedSupportValue(fv);
         supportDao.updateObject(support);
         
         successMessage("Support Added Successfuly");
         return "add-support.xhtml";  
        } catch (Exception e) {
             System.out.println("Add SUpport "+e);
             errorMessage("Failed TO add support");
            return "add-support.xhtml";
        }

    }
    
   
  
    public String registerResident(){
        // HEALT INSURANCE CAT
        Integer h1=0;
        Integer hic=0;
        
        Integer h2=0;
        Integer atw=0;
        
        Integer h3=0;
        Integer h4=0;
        Integer dd=0;
        
        Integer h5=0;
        Integer hmi=0;
        
        Integer h6=0;
        Integer h7=0;
        Integer hym=0;
        
        // EDUCATION CAT
        Integer ed1=0;
        Integer edle=0;
        
        Integer ed2=0;
        Integer cho=0;
        
        Integer ed3=0;
        Integer ed4=0;
        Integer jb=0;
        
        // ECONOMIC CAT
        Integer hs1=0;
        Integer owh=0;
        
        Integer hs2=0;
        Integer owhAn=0;
        
        Integer hs3=0;
        Integer owhBus=0;
        
        Integer hs4=0;
        Integer owhLa=0;
        
        Integer hs5=0;
        Integer hs6=0;
        Integer hs7=0;
        Integer ecc=0;
        
        
        
        
        
        try {

            User usr = userDao.getUsername(user.getUsername()); 
            village = usr.getVillage();
            if(resident.getFirstname()!=null && resident.getLastname()!=null && resident.getDob()!=null && resident.getPhoneNumber()!=null){
            resident.setResidentStatus("Allocated");            
            resident.setVillage(village);       
            residentDao.recordObject(resident);
            
            
            code = resident.getFirstname().charAt(0)+""+resident.getLastname().charAt(0)+"-"+resident.getId();
            resident.setCode(code);
            residentDao.updateObject(resident);
            
            

            
            User us = new User();
            String pass = "123";
            us.setActive(Boolean.TRUE);
            us.setUsername(resident.getFirstname());
            us.setPassword(createAnonymous(pass));
            us.setResident(resident);
            us.setType("Resident");
            us.setVillage(resident.getVillage());
            userDao.recordObject(us);
            
           
            System.out.println("Recoreded successfuly");
            
            //HEALTH LEVELING
            
            if(resident.getHealthInsurance().equalsIgnoreCase("None")){
                h1=4;
            }
            if(resident.getHealthInsurance().equalsIgnoreCase("Mituelle")){
                h1=3;
            }
            if(resident.getHealthInsurance().equalsIgnoreCase("Rama")){
                h1=2;
            }
            if(resident.getHealthInsurance().equalsIgnoreCase("Other")){
                h1=1;
            }
            hic = h1;
            
            if(resident.getAbleToWork().equalsIgnoreCase("No")){
                h2=4;
            }
            if(resident.getAbleToWork().equalsIgnoreCase("Yes")){
                h2=0;
            }
            
            atw=h2;
            
            if(resident.getDiseasesStatus().equalsIgnoreCase("No")){
                h3=0;
            }
            if(resident.getDiseasesStatus().equalsIgnoreCase("Yes")){
                h3=2;
            }
            
            if(resident.getDisabilityStatus().equalsIgnoreCase("No")){
                h4=0;
            }
            if(resident.getDisabilityStatus().equalsIgnoreCase("Yes")){
                h4=2;
            }
            
            dd = h3+h4;
            
             if(resident.getHouseMemberInsured().equalsIgnoreCase("No")){
                h5=4;
            }
            if(resident.getHouseMemberInsured().equalsIgnoreCase("Some")){
                h5=3;
            }
            if(resident.getHouseMemberInsured().equalsIgnoreCase("Yes")){
                h5=0;
            }
            if(resident.getHouseMemberInsured().equalsIgnoreCase("None")){
                h5=0;
            }
            
            hmi = h5;
             
            if(resident.getAbleToAffordHygienicMaterials().equalsIgnoreCase("No")){
                h6=2;
            }
            if(resident.getAbleToAffordHygienicMaterials().equalsIgnoreCase("Yes")){
                h6=0;
            }
            if(resident.getMealsPerDayRange()<=1){
                h7=2;
            }
            if(resident.getMealsPerDayRange()>1){
                h7=0;
            }
            hym = h7+h6;
            
           
            Integer hr = hmi+dd+atw+hic+hym;
            resident.setHealthRates(hr);
            residentDao.updateObject(resident);
            
            // EDUCATION LEVELING
            
            if(resident.getChildrenNo()<=1){
              if(resident.getEducationLevel().equalsIgnoreCase("None")){
                ed1=16;
            }
            if(resident.getEducationLevel().equalsIgnoreCase("Primary")){
                ed1=14;
            }
            if(resident.getEducationLevel().equalsIgnoreCase("Secondary")){
                ed1=12;
            }
            if(resident.getEducationLevel().equalsIgnoreCase("University")){
                ed1=6;
            }
            edle = ed1;
            resident.setEducationRates(edle);
            residentDao.updateObject(resident);
                
            }else{
             if(resident.getEducationLevel().equalsIgnoreCase("None")){
                ed1=8;
            }
            if(resident.getEducationLevel().equalsIgnoreCase("Primary")){
                ed1=6;
            }
            if(resident.getEducationLevel().equalsIgnoreCase("Secondary")){
                ed1=4;
            }
            if(resident.getEducationLevel().equalsIgnoreCase("University")){
                ed1=2;
            }
            edle = ed1;
           
            if(resident.getChildrenOccupation().equalsIgnoreCase("None")){
                ed2=8;
            }
            if(resident.getChildrenOccupation().equalsIgnoreCase("Students")){
                ed2=6;
            }
            if(resident.getChildrenOccupation().equalsIgnoreCase("Workers-Students")){
                ed2=4;
            }
            if(resident.getChildrenOccupation().equalsIgnoreCase("Workers")){
                ed2=2;
            }
            cho =ed2;
           
            
            }
            
            if(resident.getJobStatus().equalsIgnoreCase("No")){
               ed3=2; 
            }
            if(resident.getJobStatus().equalsIgnoreCase("Yes")){
               ed3=0; 
            }
            
            if(resident.getJobType().equalsIgnoreCase("FULL TIME")){
               ed4=0;
            }
            if(resident.getJobType().equalsIgnoreCase("PART TIME")){
               ed4=1;
            }
            if(resident.getJobType().equalsIgnoreCase("None")){
               ed4=2;
            }
            
            jb = ed3+ed4;
            
            Integer edtot =edle+cho+jb;
            resident.setEducationRates(edtot);
            residentDao.updateObject(resident);
            
            // ECONOMIC LEVELING
            
            
            if(resident.getOwnHouse().equalsIgnoreCase("No")){
                hs1=4;
            }
            if(resident.getOwnHouse().equalsIgnoreCase("Yes")){
                hs1=0;
            }
            owh=hs1;
            if(resident.getOwnAnimal().equalsIgnoreCase("No")){
                hs2=4;
            }
            if(resident.getOwnAnimal().equalsIgnoreCase("Yes")){
                hs2=0;
            }
            owhAn=hs2;
            if(resident.getOwnBusiness().equalsIgnoreCase("No")){
                hs3=4;
            }
           
            if(resident.getOwnBusiness().equalsIgnoreCase("Yes")){
                hs3=0;
            }
            owhBus=hs3;
            if(resident.getOwnLand().equalsIgnoreCase("No")){
                hs4=4;
            }
            if(resident.getOwnLand().equalsIgnoreCase("Yes")){
                hs4=0;
            }
            
             owhLa=hs4;
            
            if(resident.getCategory().equalsIgnoreCase("Category I")){
                hs5=3;
            }
            if(resident.getCategory().equalsIgnoreCase("Category II")){
                hs5=2;
            }
            if(resident.getCategory().equalsIgnoreCase("Category III")){
                hs5=1;
            }
            if(resident.getCategory().equalsIgnoreCase("Category IV")){
                hs5=0;
            }
            
            if(resident.getAbleToAffordPower().equalsIgnoreCase("No")){
                hs6=1;
            }
            if(resident.getAbleToAffordPower().equalsIgnoreCase("Yes")){
                hs6=0;
            }
            if(resident.getAbleToAffordWater().equalsIgnoreCase("No")){
                hs7=1;
            }
            if(resident.getAbleToAffordWater().equalsIgnoreCase("No")){
                hs7=0;
            }
            
            ecc= hs5+hs6+hs7;
            Integer totec=owh+owhAn+owhBus+owhLa+ecc;
            resident.setEconomicRates(totec);
            residentDao.updateObject(resident);
            
            
            // ALLOCATING RESIDENT
            
            Integer hrates = resident.getHealthRates();
            Integer edrates = resident.getEducationRates();
            Integer ecrates = resident.getEconomicRates();
            
            Integer totrates = hrates+edrates+ecrates;
            
            Allocated allo = new Allocated();
            
            if(totrates<=30){
                allo.setResident(resident);
                allo.setVillage(village);
                allo.setNid(resident.getNationalID());
                allo.setPhoneNumber(resident.getPhoneNumber());
                allo.setResidentNames(resident.getFirstname()+" "+resident.getLastname());
                allo.setResidentCategory(resident.getCategory());
                allo.setResidentEducationLevel(resident.getEducationLevel());
                allo.setResidentDiseasesStatus(resident.getDiseasesStatus());
                allo.setResidentDisibailityStatus(resident.getDisabilityStatus());
                allo.setResidentHealthInsuranceCard(resident.getHealthInsurance());
                allo.setGender(resident.getGender());
                allo.setJobStatus(resident.getJobStatus());
                allo.setSupportStatus("active");
                allo.setStatus("Rich");
                allo.setOwnBusiness(resident.getOwnBusiness());
                allo.setOwnHouse(resident.getOwnHouse());
                
                
                
                allocatedDao.recordObject(allo);
                successMessage("Resident Recorded And Allocated In Rich Category Successfuly");
                
            String to_number = resident.getPhoneNumber();
            String body = "ACCOUNT CREATED SUCCESFULLY , Username : "+resident.getFirstname()+ " And Password : 123 and you are allocated in Rich Category";
            sendsms.sendSMS(to_number, from_number, body);
                
                
            }else{
                if(hrates>=10 && edrates >= 10 && ecrates>=10){
                    
                if(hrates>edrates && edrates >= ecrates){
                        
                allo.setResident(resident);
                allo.setVillage(village);
                allo.setNid(resident.getNationalID());
                allo.setPhoneNumber(resident.getPhoneNumber());
                allo.setResidentNames(resident.getFirstname()+" "+resident.getLastname());
                allo.setResidentCategory(resident.getCategory());
                allo.setResidentEducationLevel(resident.getEducationLevel());
                allo.setResidentDiseasesStatus(resident.getDiseasesStatus());
                allo.setResidentDisibailityStatus(resident.getDisabilityStatus());
                allo.setResidentHealthInsuranceCard(resident.getHealthInsurance());
                allo.setGender(resident.getGender());
                allo.setJobStatus(resident.getJobStatus());
                allo.setSupportStatus("active");
                allo.setStatus("Health");
                allo.setOwnBusiness(resident.getOwnBusiness());
                allo.setOwnHouse(resident.getOwnHouse());
                allocatedDao.recordObject(allo);
                successMessage("Resident Recorded And Allocated In Health Category Successfuly");
                String to_number = resident.getPhoneNumber();
                String body = "ACCOUNT CREATED SUCCESFULLY , Username : "+resident.getFirstname()+ 
                " And Password : 123 and you are allocated in Health Category";
                sendsms.sendSMS(to_number, from_number, body);
                        
                 }else if(hrates>ecrates && ecrates>=edrates){
                 
                allo.setResident(resident);
                allo.setVillage(village);
                allo.setNid(resident.getNationalID());
                allo.setPhoneNumber(resident.getPhoneNumber());
                allo.setResidentNames(resident.getFirstname()+" "+resident.getLastname());
                allo.setResidentCategory(resident.getCategory());
                allo.setResidentEducationLevel(resident.getEducationLevel());
                allo.setResidentDiseasesStatus(resident.getDiseasesStatus());
                allo.setResidentDisibailityStatus(resident.getDisabilityStatus());
                allo.setResidentHealthInsuranceCard(resident.getHealthInsurance());
                allo.setGender(resident.getGender());
                allo.setJobStatus(resident.getJobStatus());
                allo.setSupportStatus("active");
                allo.setStatus("Health");
                allo.setOwnBusiness(resident.getOwnBusiness());
                allo.setOwnHouse(resident.getOwnHouse());
                allocatedDao.recordObject(allo);
                successMessage("Resident Recorded And Allocated In Health Category Successfuly");
                String to_number = resident.getPhoneNumber();
                String body = "ACCOUNT CREATED SUCCESFULLY , Username : "+resident.getFirstname()+ 
                " And Password : 123 and you are allocated in Health Category";
                sendsms.sendSMS(to_number, from_number, body);
                
                }else if(edrates>hrates && hrates>=ecrates){
                     allo.setResident(resident);
                allo.setVillage(village);
                allo.setNid(resident.getNationalID());
                allo.setPhoneNumber(resident.getPhoneNumber());
                allo.setResidentNames(resident.getFirstname()+" "+resident.getLastname());
                allo.setResidentCategory(resident.getCategory());
                allo.setResidentEducationLevel(resident.getEducationLevel());
                allo.setResidentDiseasesStatus(resident.getDiseasesStatus());
                allo.setResidentDisibailityStatus(resident.getDisabilityStatus());
                allo.setResidentHealthInsuranceCard(resident.getHealthInsurance());
                allo.setGender(resident.getGender());
                allo.setJobStatus(resident.getJobStatus());
                allo.setSupportStatus("active");
                allo.setStatus("Education");
                allo.setOwnBusiness(resident.getOwnBusiness());
                allo.setOwnHouse(resident.getOwnHouse());
                allocatedDao.recordObject(allo);
                successMessage("Resident Recorded And Allocated In Education Category Successfuly");
                String to_number = resident.getPhoneNumber();
                String body = "ACCOUNT CREATED SUCCESFULLY , Username : "+resident.getFirstname()+ 
                " And Password : 123 and you are allocated in Education Category";
                sendsms.sendSMS(to_number, from_number, body);
                }else if(edrates>ecrates && ecrates>=hrates){
                allo.setResident(resident);
                allo.setVillage(village);
                allo.setNid(resident.getNationalID());
                allo.setPhoneNumber(resident.getPhoneNumber());
                allo.setResidentNames(resident.getFirstname()+" "+resident.getLastname());
                allo.setResidentCategory(resident.getCategory());
                allo.setResidentEducationLevel(resident.getEducationLevel());
                allo.setResidentDiseasesStatus(resident.getDiseasesStatus());
                allo.setResidentDisibailityStatus(resident.getDisabilityStatus());
                allo.setResidentHealthInsuranceCard(resident.getHealthInsurance());
                allo.setGender(resident.getGender());
                allo.setJobStatus(resident.getJobStatus());
                allo.setSupportStatus("active");
                allo.setStatus("Education");
                allo.setOwnBusiness(resident.getOwnBusiness());
                allo.setOwnHouse(resident.getOwnHouse());
                allocatedDao.recordObject(allo);
                successMessage("Resident Recorded And Allocated In Education Category Successfuly");
                String to_number = resident.getPhoneNumber();
                String body = "ACCOUNT CREATED SUCCESFULLY , Username : "+resident.getFirstname()+ 
                " And Password : 123 and you are allocated in Education Category";
                sendsms.sendSMS(to_number, from_number, body);
                }else if(ecrates>edrates && edrates>hrates){
                     allo.setResident(resident);
                allo.setVillage(village);
                allo.setNid(resident.getNationalID());
                allo.setPhoneNumber(resident.getPhoneNumber());
                allo.setResidentNames(resident.getFirstname()+" "+resident.getLastname());
                allo.setResidentCategory(resident.getCategory());
                allo.setResidentEducationLevel(resident.getEducationLevel());
                allo.setResidentDiseasesStatus(resident.getDiseasesStatus());
                allo.setResidentDisibailityStatus(resident.getDisabilityStatus());
                allo.setResidentHealthInsuranceCard(resident.getHealthInsurance());
                allo.setGender(resident.getGender());
                allo.setJobStatus(resident.getJobStatus());
                allo.setSupportStatus("active");
                allo.setStatus("Economic");
                allo.setOwnBusiness(resident.getOwnBusiness());
                allo.setOwnHouse(resident.getOwnHouse());
                allocatedDao.recordObject(allo);
                successMessage("Resident Recorded And Allocated In Economic Category Successfuly");
                String to_number = resident.getPhoneNumber();
                String body = "ACCOUNT CREATED SUCCESFULLY , Username : "+resident.getFirstname()+ 
                " And Password : 123 and you are allocated in Economic Category";
                sendsms.sendSMS(to_number, from_number, body);
                }else if(ecrates>hrates && hrates>=edrates){
                allo.setResident(resident);
                allo.setVillage(village);
                allo.setNid(resident.getNationalID());
                allo.setPhoneNumber(resident.getPhoneNumber());
                allo.setResidentNames(resident.getFirstname()+" "+resident.getLastname());
                allo.setResidentCategory(resident.getCategory());
                allo.setResidentEducationLevel(resident.getEducationLevel());
                allo.setResidentDiseasesStatus(resident.getDiseasesStatus());
                allo.setResidentDisibailityStatus(resident.getDisabilityStatus());
                allo.setResidentHealthInsuranceCard(resident.getHealthInsurance());
                allo.setGender(resident.getGender());
                allo.setJobStatus(resident.getJobStatus());
                allo.setSupportStatus("active");
                allo.setStatus("Economic");
                allo.setOwnBusiness(resident.getOwnBusiness());
                allo.setOwnHouse(resident.getOwnHouse());
                allocatedDao.recordObject(allo);
                successMessage("Resident Recorded And Allocated In Economic Category Successfuly");
                String to_number = resident.getPhoneNumber();
                String body = "ACCOUNT CREATED SUCCESFULLY , Username : "+resident.getFirstname()+ 
                " And Password : 123 and you are allocated in Economic Category";
                sendsms.sendSMS(to_number, from_number, body);
                }
                else if(hrates == edrates && edrates == ecrates){
                
                allo.setResident(resident);
                allo.setVillage(village);
                allo.setNid(resident.getNationalID());
                allo.setPhoneNumber(resident.getPhoneNumber());
                allo.setResidentNames(resident.getFirstname()+" "+resident.getLastname());
                allo.setResidentCategory(resident.getCategory());
                allo.setResidentEducationLevel(resident.getEducationLevel());
                allo.setResidentDiseasesStatus(resident.getDiseasesStatus());
                allo.setResidentDisibailityStatus(resident.getDisabilityStatus());
                allo.setResidentHealthInsuranceCard(resident.getHealthInsurance());
                allo.setGender(resident.getGender());
                allo.setJobStatus(resident.getJobStatus());
                allo.setSupportStatus("active");
                allo.setStatus("Economic");
                allo.setOwnBusiness(resident.getOwnBusiness());
                allo.setOwnHouse(resident.getOwnHouse());
                allocatedDao.recordObject(allo);
                
                allo.setResident(resident);
                allo.setVillage(village);
                allo.setNid(resident.getNationalID());
                allo.setPhoneNumber(resident.getPhoneNumber());
                allo.setResidentNames(resident.getFirstname()+" "+resident.getLastname());
                allo.setResidentCategory(resident.getCategory());
                allo.setResidentEducationLevel(resident.getEducationLevel());
                allo.setResidentDiseasesStatus(resident.getDiseasesStatus());
                allo.setResidentDisibailityStatus(resident.getDisabilityStatus());
                allo.setResidentHealthInsuranceCard(resident.getHealthInsurance());
                allo.setGender(resident.getGender());
                allo.setJobStatus(resident.getJobStatus());
                allo.setSupportStatus("active");
                allo.setStatus("Education");
                allo.setOwnBusiness(resident.getOwnBusiness());
                allo.setOwnHouse(resident.getOwnHouse());
                allocatedDao.recordObject(allo);
                
                allo.setResident(resident);
                allo.setVillage(village);
                allo.setNid(resident.getNationalID());
                allo.setPhoneNumber(resident.getPhoneNumber());
                allo.setResidentNames(resident.getFirstname()+" "+resident.getLastname());
                allo.setResidentCategory(resident.getCategory());
                allo.setResidentEducationLevel(resident.getEducationLevel());
                allo.setResidentDiseasesStatus(resident.getDiseasesStatus());
                allo.setResidentDisibailityStatus(resident.getDisabilityStatus());
                allo.setResidentHealthInsuranceCard(resident.getHealthInsurance());
                allo.setGender(resident.getGender());
                allo.setJobStatus(resident.getJobStatus());
                allo.setSupportStatus("active");
                allo.setStatus("Health");
                allo.setOwnBusiness(resident.getOwnBusiness());
                allo.setOwnHouse(resident.getOwnHouse());
                allocatedDao.recordObject(allo);
                successMessage("Resident Recorded And Allocated In All Categories Successfuly");
                String to_number = resident.getPhoneNumber();
                String body = "ACCOUNT CREATED SUCCESFULLY , Username : "+resident.getFirstname()+ 
                " And Password : 123 and you are allocated in All Categories";
                sendsms.sendSMS(to_number, from_number, body);
                
                }else if(hrates == edrates && edrates > ecrates){
                 allo.setResident(resident);
                allo.setVillage(village);
                allo.setNid(resident.getNationalID());
                allo.setPhoneNumber(resident.getPhoneNumber());
                allo.setResidentNames(resident.getFirstname()+" "+resident.getLastname());
                allo.setResidentCategory(resident.getCategory());
                allo.setResidentEducationLevel(resident.getEducationLevel());
                allo.setResidentDiseasesStatus(resident.getDiseasesStatus());
                allo.setResidentDisibailityStatus(resident.getDisabilityStatus());
                allo.setResidentHealthInsuranceCard(resident.getHealthInsurance());
                allo.setGender(resident.getGender());
                allo.setJobStatus(resident.getJobStatus());
                allo.setSupportStatus("active");
                allo.setStatus("Education");
                allo.setOwnBusiness(resident.getOwnBusiness());
                allo.setOwnHouse(resident.getOwnHouse());
                allocatedDao.recordObject(allo);
                
                
                allo.setResident(resident);
                allo.setVillage(village);
                allo.setNid(resident.getNationalID());
                allo.setPhoneNumber(resident.getPhoneNumber());
                allo.setResidentNames(resident.getFirstname()+" "+resident.getLastname());
                allo.setResidentCategory(resident.getCategory());
                allo.setResidentEducationLevel(resident.getEducationLevel());
                allo.setResidentDiseasesStatus(resident.getDiseasesStatus());
                allo.setResidentDisibailityStatus(resident.getDisabilityStatus());
                allo.setResidentHealthInsuranceCard(resident.getHealthInsurance());
                allo.setGender(resident.getGender());
                allo.setJobStatus(resident.getJobStatus());
                allo.setSupportStatus("active");
                allo.setStatus("Health");
                allo.setOwnBusiness(resident.getOwnBusiness());
                allo.setOwnHouse(resident.getOwnHouse());
                allocatedDao.recordObject(allo);
                successMessage("Resident Recorded And Allocated In Health and Education Categories Successfuly");
                String to_number = resident.getPhoneNumber();
                String body = "ACCOUNT CREATED SUCCESFULLY , Username : "+resident.getFirstname()+ 
                " And Password : 123 and you are allocated in Health and Education Categories";
                sendsms.sendSMS(to_number, from_number, body);
                
                }else if (hrates == ecrates && ecrates > edrates){
                   allo.setResident(resident);
                allo.setVillage(village);
                allo.setNid(resident.getNationalID());
                allo.setPhoneNumber(resident.getPhoneNumber());
                allo.setResidentNames(resident.getFirstname()+" "+resident.getLastname());
                allo.setResidentCategory(resident.getCategory());
                allo.setResidentEducationLevel(resident.getEducationLevel());
                allo.setResidentDiseasesStatus(resident.getDiseasesStatus());
                allo.setResidentDisibailityStatus(resident.getDisabilityStatus());
                allo.setResidentHealthInsuranceCard(resident.getHealthInsurance());
                allo.setGender(resident.getGender());
                allo.setJobStatus(resident.getJobStatus());
                allo.setSupportStatus("active");
                allo.setStatus("Economic");
                allo.setOwnBusiness(resident.getOwnBusiness());
                allo.setOwnHouse(resident.getOwnHouse());
                allocatedDao.recordObject(allo);
                
                allo.setResident(resident);
                allo.setVillage(village);
                allo.setNid(resident.getNationalID());
                allo.setPhoneNumber(resident.getPhoneNumber());
                allo.setResidentNames(resident.getFirstname()+" "+resident.getLastname());
                allo.setResidentCategory(resident.getCategory());
                allo.setResidentEducationLevel(resident.getEducationLevel());
                allo.setResidentDiseasesStatus(resident.getDiseasesStatus());
                allo.setResidentDisibailityStatus(resident.getDisabilityStatus());
                allo.setResidentHealthInsuranceCard(resident.getHealthInsurance());
                allo.setGender(resident.getGender());
                allo.setJobStatus(resident.getJobStatus());
                allo.setSupportStatus("active");
                allo.setStatus("Health");
                allo.setOwnBusiness(resident.getOwnBusiness());
                allo.setOwnHouse(resident.getOwnHouse());
                allocatedDao.recordObject(allo); 
                successMessage("Resident Recorded And Allocated In Health and Economic Categories Successfuly");
                String to_number = resident.getPhoneNumber();
                String body = "ACCOUNT CREATED SUCCESFULLY , Username : "+resident.getFirstname()+ 
                " And Password : 123 and you are allocated in Health and Economic Categories";
                sendsms.sendSMS(to_number, from_number, body);
                }else if(edrates == ecrates && ecrates > hrates){
                    allo.setResident(resident);
                allo.setVillage(village);
                allo.setNid(resident.getNationalID());
                allo.setPhoneNumber(resident.getPhoneNumber());
                allo.setResidentNames(resident.getFirstname()+" "+resident.getLastname());
                allo.setResidentCategory(resident.getCategory());
                allo.setResidentEducationLevel(resident.getEducationLevel());
                allo.setResidentDiseasesStatus(resident.getDiseasesStatus());
                allo.setResidentDisibailityStatus(resident.getDisabilityStatus());
                allo.setResidentHealthInsuranceCard(resident.getHealthInsurance());
                allo.setGender(resident.getGender());
                allo.setJobStatus(resident.getJobStatus());
                allo.setSupportStatus("active");
                allo.setStatus("Economic");
                allo.setOwnBusiness(resident.getOwnBusiness());
                allo.setOwnHouse(resident.getOwnHouse());
                allocatedDao.recordObject(allo);
                
                allo.setResident(resident);
                allo.setVillage(village);
                allo.setNid(resident.getNationalID());
                allo.setPhoneNumber(resident.getPhoneNumber());
                allo.setResidentNames(resident.getFirstname()+" "+resident.getLastname());
                allo.setResidentCategory(resident.getCategory());
                allo.setResidentEducationLevel(resident.getEducationLevel());
                allo.setResidentDiseasesStatus(resident.getDiseasesStatus());
                allo.setResidentDisibailityStatus(resident.getDisabilityStatus());
                allo.setResidentHealthInsuranceCard(resident.getHealthInsurance());
                allo.setGender(resident.getGender());
                allo.setJobStatus(resident.getJobStatus());
                allo.setSupportStatus("active");
                allo.setStatus("Education");
                allo.setOwnBusiness(resident.getOwnBusiness());
                allo.setOwnHouse(resident.getOwnHouse());
                allocatedDao.recordObject(allo);
                
                String to_number = resident.getPhoneNumber();
                successMessage("Resident Recorded And Allocated In Education and Economic Categories Successfuly");
                String body = "ACCOUNT CREATED SUCCESFULLY , Username : "+resident.getFirstname()+ 
                " And Password : 123 and you are allocated in Education and Economic Categories";
                sendsms.sendSMS(to_number, from_number, body);
                }
                
                else{
                    System.out.println("ALL ABOVE 10 BUT NOT ALLOCATED");
                }
                 
               
                // HEALTH LESS 10
                }else if(hrates<=10 && edrates>10 && ecrates>10){
                    
                    if(edrates>=ecrates){
                allo.setResident(resident);
                allo.setVillage(village);
                allo.setNid(resident.getNationalID());
                allo.setPhoneNumber(resident.getPhoneNumber());
                allo.setResidentNames(resident.getFirstname()+" "+resident.getLastname());
                allo.setResidentCategory(resident.getCategory());
                allo.setResidentEducationLevel(resident.getEducationLevel());
                allo.setResidentDiseasesStatus(resident.getDiseasesStatus());
                allo.setResidentDisibailityStatus(resident.getDisabilityStatus());
                allo.setResidentHealthInsuranceCard(resident.getHealthInsurance());
                allo.setGender(resident.getGender());
                allo.setJobStatus(resident.getJobStatus());
                allo.setSupportStatus("active");
                allo.setStatus("Education");
                allo.setOwnBusiness(resident.getOwnBusiness());
                allo.setOwnHouse(resident.getOwnHouse());
                allocatedDao.recordObject(allo);
                successMessage("Resident Recorded And Allocated In Education Category Successfuly");
                String to_number = resident.getPhoneNumber();
                String body = "ACCOUNT CREATED SUCCESFULLY , Username : "+resident.getFirstname()+ 
                " And Password : 123 and you are allocated in Education Category";
                sendsms.sendSMS(to_number, from_number, body);
                    }else{
                allo.setResident(resident);
                allo.setVillage(village);
                allo.setNid(resident.getNationalID());
                allo.setPhoneNumber(resident.getPhoneNumber());
                allo.setResidentNames(resident.getFirstname()+" "+resident.getLastname());
                allo.setResidentCategory(resident.getCategory());
                allo.setResidentEducationLevel(resident.getEducationLevel());
                allo.setResidentDiseasesStatus(resident.getDiseasesStatus());
                allo.setResidentDisibailityStatus(resident.getDisabilityStatus());
                allo.setResidentHealthInsuranceCard(resident.getHealthInsurance());
                allo.setGender(resident.getGender());
                allo.setJobStatus(resident.getJobStatus());
                allo.setSupportStatus("active");
                allo.setStatus("Economic");
                allo.setOwnBusiness(resident.getOwnBusiness());
                allo.setOwnHouse(resident.getOwnHouse());
                allocatedDao.recordObject(allo);
                successMessage("Resident Recorded And Allocated In Economic Category Successfuly");
                String to_number = resident.getPhoneNumber();
                String body = "ACCOUNT CREATED SUCCESFULLY , Username : "+resident.getFirstname()+ 
                " And Password : 123 and you are allocated in Economic Category";
                sendsms.sendSMS(to_number, from_number, body);
                    }
                    
                }
                // EDUCATION LESS = 10
                else if(edrates<=10 && hrates>10 && ecrates>10){
                    
                 if(hrates>=ecrates){
                allo.setResident(resident);
                allo.setVillage(village);
                allo.setNid(resident.getNationalID());
                allo.setPhoneNumber(resident.getPhoneNumber());
                allo.setResidentNames(resident.getFirstname()+" "+resident.getLastname());
                allo.setResidentCategory(resident.getCategory());
                allo.setResidentEducationLevel(resident.getEducationLevel());
                allo.setResidentDiseasesStatus(resident.getDiseasesStatus());
                allo.setResidentDisibailityStatus(resident.getDisabilityStatus());
                allo.setResidentHealthInsuranceCard(resident.getHealthInsurance());
                allo.setGender(resident.getGender());
                allo.setJobStatus(resident.getJobStatus());
                allo.setSupportStatus("active");
                allo.setStatus("Health");
                allo.setOwnBusiness(resident.getOwnBusiness());
                allo.setOwnHouse(resident.getOwnHouse());
                allocatedDao.recordObject(allo);
                successMessage("Resident Recorded And Allocated In Health Category Successfuly");
                String to_number = resident.getPhoneNumber();
                String body = "ACCOUNT CREATED SUCCESFULLY , Username : "+resident.getFirstname()+ 
                " And Password : 123 and you are allocated in Health Category";
                sendsms.sendSMS(to_number, from_number, body);
                    }else{
                allo.setResident(resident);
                allo.setVillage(village);
                allo.setNid(resident.getNationalID());
                allo.setPhoneNumber(resident.getPhoneNumber());
                allo.setResidentNames(resident.getFirstname()+" "+resident.getLastname());
                allo.setResidentCategory(resident.getCategory());
                allo.setResidentEducationLevel(resident.getEducationLevel());
                allo.setResidentDiseasesStatus(resident.getDiseasesStatus());
                allo.setResidentDisibailityStatus(resident.getDisabilityStatus());
                allo.setResidentHealthInsuranceCard(resident.getHealthInsurance());
                allo.setGender(resident.getGender());
                allo.setJobStatus(resident.getJobStatus());
                allo.setSupportStatus("active");
                allo.setStatus("Economic");
                allo.setOwnBusiness(resident.getOwnBusiness());
                allo.setOwnHouse(resident.getOwnHouse());
                allocatedDao.recordObject(allo);
                successMessage("Resident Recorded And Allocated In Economic Category Successfuly");
                String to_number = resident.getPhoneNumber();
                String body = "ACCOUNT CREATED SUCCESFULLY , Username : "+resident.getFirstname()+ 
                " And Password : 123 and you are allocated in Economic Category";
                sendsms.sendSMS(to_number, from_number, body);
                    }
                    
                }
                // ECONOMIC LESS = 10 
                else if(ecrates<=10 && edrates>10 && hrates>10){
                    
                    if(edrates>=hrates){
                allo.setResident(resident);
                allo.setVillage(village);
                allo.setNid(resident.getNationalID());
                allo.setPhoneNumber(resident.getPhoneNumber());
                allo.setResidentNames(resident.getFirstname()+" "+resident.getLastname());
                allo.setResidentCategory(resident.getCategory());
                allo.setResidentEducationLevel(resident.getEducationLevel());
                allo.setResidentDiseasesStatus(resident.getDiseasesStatus());
                allo.setResidentDisibailityStatus(resident.getDisabilityStatus());
                allo.setResidentHealthInsuranceCard(resident.getHealthInsurance());
                allo.setGender(resident.getGender());
                allo.setJobStatus(resident.getJobStatus());
                allo.setSupportStatus("active");
                allo.setStatus("Education");
                allo.setOwnBusiness(resident.getOwnBusiness());
                allo.setOwnHouse(resident.getOwnHouse());
                allocatedDao.recordObject(allo);
                successMessage("Resident Recorded And Allocated In Education Category Successfuly");
                String to_number = resident.getPhoneNumber();
                String body = "ACCOUNT CREATED SUCCESFULLY , Username : "+resident.getFirstname()+ 
                " And Password : 123 and you are allocated in Education Category";
                sendsms.sendSMS(to_number, from_number, body);
                    }else{
                allo.setResident(resident);
                allo.setVillage(village);
                allo.setNid(resident.getNationalID());
                allo.setPhoneNumber(resident.getPhoneNumber());
                allo.setResidentNames(resident.getFirstname()+" "+resident.getLastname());
                allo.setResidentCategory(resident.getCategory());
                allo.setResidentEducationLevel(resident.getEducationLevel());
                allo.setResidentDiseasesStatus(resident.getDiseasesStatus());
                allo.setResidentDisibailityStatus(resident.getDisabilityStatus());
                allo.setResidentHealthInsuranceCard(resident.getHealthInsurance());
                allo.setGender(resident.getGender());
                allo.setJobStatus(resident.getJobStatus());
                allo.setSupportStatus("active");
                allo.setStatus("Economic");
                allo.setOwnBusiness(resident.getOwnBusiness());
                allo.setOwnHouse(resident.getOwnHouse());
                allocatedDao.recordObject(allo);
                successMessage("Resident Recorded And Allocated In Economic Category Successfuly");
                String to_number = resident.getPhoneNumber();
                String body = "ACCOUNT CREATED SUCCESFULLY , Username : "+resident.getFirstname()+ 
                " And Password : 123 and you are allocated in Economic Category";
                sendsms.sendSMS(to_number, from_number, body);
                    }
                    
                }else if(hrates>10 && edrates<=10 && ecrates<=10){
                allo.setResident(resident);
                allo.setVillage(village);
                allo.setNid(resident.getNationalID());
                allo.setPhoneNumber(resident.getPhoneNumber());
                allo.setResidentNames(resident.getFirstname()+" "+resident.getLastname());
                allo.setResidentCategory(resident.getCategory());
                allo.setResidentEducationLevel(resident.getEducationLevel());
                allo.setResidentDiseasesStatus(resident.getDiseasesStatus());
                allo.setResidentDisibailityStatus(resident.getDisabilityStatus());
                allo.setResidentHealthInsuranceCard(resident.getHealthInsurance());
                allo.setGender(resident.getGender());
                allo.setJobStatus(resident.getJobStatus());
                allo.setSupportStatus("active");
                allo.setStatus("Health");
                allo.setOwnBusiness(resident.getOwnBusiness());
                allo.setOwnHouse(resident.getOwnHouse());
                allocatedDao.recordObject(allo);
                successMessage("Resident Recorded And Allocated In Health Category Successfuly");
                String to_number = resident.getPhoneNumber();
                String body = "ACCOUNT CREATED SUCCESFULLY , Username : "+resident.getFirstname()+ 
                " And Password : 123 and you are allocated in Health Category";
                sendsms.sendSMS(to_number, from_number, body);
                }else if(edrates>10 && hrates<=10 && ecrates<=10){
                allo.setResident(resident);
                allo.setVillage(village);
                allo.setNid(resident.getNationalID());
                allo.setPhoneNumber(resident.getPhoneNumber());
                allo.setResidentNames(resident.getFirstname()+" "+resident.getLastname());
                allo.setResidentCategory(resident.getCategory());
                allo.setResidentEducationLevel(resident.getEducationLevel());
                allo.setResidentDiseasesStatus(resident.getDiseasesStatus());
                allo.setResidentDisibailityStatus(resident.getDisabilityStatus());
                allo.setResidentHealthInsuranceCard(resident.getHealthInsurance());
                allo.setGender(resident.getGender());
                allo.setJobStatus(resident.getJobStatus());
                allo.setSupportStatus("active");
                allo.setStatus("Education");
                allo.setOwnBusiness(resident.getOwnBusiness());
                allo.setOwnHouse(resident.getOwnHouse());
                allocatedDao.recordObject(allo);
                successMessage("Resident Recorded And Allocated In Education Category Successfuly");
                String to_number = resident.getPhoneNumber();
                String body = "ACCOUNT CREATED SUCCESFULLY , Username : "+resident.getFirstname()+ 
                " And Password : 123 and you are allocated in Education Category";
                sendsms.sendSMS(to_number, from_number, body);
                }else if(ecrates>10 && edrates<=10 && hrates<=10){
                 allo.setResident(resident);
                allo.setVillage(village);
                allo.setNid(resident.getNationalID());
                allo.setPhoneNumber(resident.getPhoneNumber());
                allo.setResidentNames(resident.getFirstname()+" "+resident.getLastname());
                allo.setResidentCategory(resident.getCategory());
                allo.setResidentEducationLevel(resident.getEducationLevel());
                allo.setResidentDiseasesStatus(resident.getDiseasesStatus());
                allo.setResidentDisibailityStatus(resident.getDisabilityStatus());
                allo.setResidentHealthInsuranceCard(resident.getHealthInsurance());
                allo.setGender(resident.getGender());
                allo.setJobStatus(resident.getJobStatus());
                allo.setSupportStatus("active");
                allo.setStatus("Economic");
                allo.setOwnBusiness(resident.getOwnBusiness());
                allo.setOwnHouse(resident.getOwnHouse());
                allocatedDao.recordObject(allo);
                String to_number = resident.getPhoneNumber();
                successMessage("Resident Recorded And Allocated In Economic Category Successfuly");
                String body = "ACCOUNT CREATED SUCCESFULLY , Username : "+resident.getFirstname()+ 
                " And Password : 123 and you are allocated in Economic Category";
                sendsms.sendSMS(to_number, from_number, body);
                }
                else if(hrates == edrates && edrates == ecrates){
                
                allo.setResident(resident);
                allo.setVillage(village);
                allo.setNid(resident.getNationalID());
                allo.setPhoneNumber(resident.getPhoneNumber());
                allo.setResidentNames(resident.getFirstname()+" "+resident.getLastname());
                allo.setResidentCategory(resident.getCategory());
                allo.setResidentEducationLevel(resident.getEducationLevel());
                allo.setResidentDiseasesStatus(resident.getDiseasesStatus());
                allo.setResidentDisibailityStatus(resident.getDisabilityStatus());
                allo.setResidentHealthInsuranceCard(resident.getHealthInsurance());
                allo.setGender(resident.getGender());
                allo.setJobStatus(resident.getJobStatus());
                allo.setSupportStatus("active");
                allo.setStatus("Economic");
                allo.setOwnBusiness(resident.getOwnBusiness());
                allo.setOwnHouse(resident.getOwnHouse());
                allocatedDao.recordObject(allo);
                
                allo.setResident(resident);
                allo.setVillage(village);
                allo.setNid(resident.getNationalID());
                allo.setPhoneNumber(resident.getPhoneNumber());
                allo.setResidentNames(resident.getFirstname()+" "+resident.getLastname());
                allo.setResidentCategory(resident.getCategory());
                allo.setResidentEducationLevel(resident.getEducationLevel());
                allo.setResidentDiseasesStatus(resident.getDiseasesStatus());
                allo.setResidentDisibailityStatus(resident.getDisabilityStatus());
                allo.setResidentHealthInsuranceCard(resident.getHealthInsurance());
                allo.setGender(resident.getGender());
                allo.setJobStatus(resident.getJobStatus());
                allo.setSupportStatus("active");
                allo.setStatus("Education");
                allo.setOwnBusiness(resident.getOwnBusiness());
                allo.setOwnHouse(resident.getOwnHouse());
                allocatedDao.recordObject(allo);
                
                allo.setResident(resident);
                allo.setVillage(village);
                allo.setNid(resident.getNationalID());
                allo.setPhoneNumber(resident.getPhoneNumber());
                allo.setResidentNames(resident.getFirstname()+" "+resident.getLastname());
                allo.setResidentCategory(resident.getCategory());
                allo.setResidentEducationLevel(resident.getEducationLevel());
                allo.setResidentDiseasesStatus(resident.getDiseasesStatus());
                allo.setResidentDisibailityStatus(resident.getDisabilityStatus());
                allo.setResidentHealthInsuranceCard(resident.getHealthInsurance());
                allo.setGender(resident.getGender());
                allo.setJobStatus(resident.getJobStatus());
                allo.setSupportStatus("active");
                allo.setStatus("Health");
                allo.setOwnBusiness(resident.getOwnBusiness());
                allo.setOwnHouse(resident.getOwnHouse());
                allocatedDao.recordObject(allo);
                successMessage("Resident Recorded And Allocated In All Categories Successfuly");
                
                String to_number = resident.getPhoneNumber();
                String body = "ACCOUNT CREATED SUCCESFULLY , Username : "+resident.getFirstname()+ 
                " And Password : 123 and you are allocated in All Categories";
                sendsms.sendSMS(to_number, from_number, body);
                
                }else{
                    System.out.println("ALLOCATING NOT CLASSIFIED");
                }
            
            }
           
            
           
            
            
            
           
            
            
            
            return "record-resident.xhtml";
                
            }else{
                warningMessage("Some fields are empty");
                return "record-resident.xhtml";
            }
           
           
        } catch (Exception e) {
//            errorMessage("Failed To Record a resident");
            System.out.println("Failed to record resident "+e);
            return "record-resident.xhtml";
        }
        
        
    }
    
    public String residentUpdate(){
        
        try {
           User usr = userDao.getUsername(user.getUsername());
           resident = usr.getResident();
           allocated = allocatedDao.getAllocatedByResidentIdByHealth(resident.getId());
           if(allocated!=null){
           allocatedDao.deleteObject(allocated); 
           resident.setPhoneNumber(ph);
           resident.setIncomeRange(icr);
           resident.setJobStatus(jb);
           resident.setEducationLevel(ed);
           resident.setHealthInsurance(hi);
           resident.setResidentStatus("pending");
           residentDao.updateObject(resident);
           allocate(resident.getId());

           }
           allocated = allocatedDao.getAllocatedByResidentIdByEducation(resident.getId());
           if(allocated!=null){
           allocatedDao.deleteObject(allocated); 
           resident.setPhoneNumber(ph);
           resident.setIncomeRange(icr);
           resident.setJobStatus(jb);
           resident.setEducationLevel(ed);
           resident.setHealthInsurance(hi);
           resident.setResidentStatus("pending");
           residentDao.updateObject(resident);
           allocate(resident.getId());
           System.out.println("2");
           }
           allocated = allocatedDao.getAllocatedByResidentIdByEconomic(resident.getId());
           if(allocated!=null){
           allocatedDao.deleteObject(allocated); 
           resident.setPhoneNumber(ph);
           resident.setIncomeRange(icr);
           resident.setJobStatus(jb);
           resident.setEducationLevel(ed);
           resident.setHealthInsurance(hi);
           resident.setResidentStatus("pending");
           residentDao.updateObject(resident);
           allocate(resident.getId());
           System.out.println("33");
           }
           allocated = allocatedDao.getAllocatedByResidentIdByRich(resident.getId());
           if(allocated!=null){
           allocatedDao.deleteObject(allocated); 
           resident.setPhoneNumber(ph);
           resident.setIncomeRange(icr);
           resident.setJobStatus(jb);
           resident.setEducationLevel(ed);
           resident.setHealthInsurance(hi);
           resident.setResidentStatus("pending");
           residentDao.updateObject(resident); 
           allocate(resident.getId());
           System.out.println("44");
           }
           if(allocated==null){
               System.out.println("Heeerere Man");
           resident.setPhoneNumber(ph);
           resident.setIncomeRange(icr);
           resident.setJobStatus(jb);
           resident.setEducationLevel(ed);
           resident.setHealthInsurance(hi);
           resident.setResidentStatus("pending");
           residentDao.updateObject(resident);
           allocate(resident.getId());
    
           }
           
         

           return "edit-profile.xhtml";
        } catch (Exception e) {
            System.out.println("Upadte resident error "+e);
            return "edit-profile.xhtml";
        }
    }
    
    
   
    public String allocateCitizen(Integer residentId){
        try {
         User usr = userDao.getUsername(user.getUsername()); 
         village = usr.getVillage();
         resident = residentDao.findOne(Resident.class, residentId);
         
         Integer totHr = resident.getHealthRates();
         Integer totEd = resident.getEducationRates();
         Integer totEc = resident.getEconomicRates();
        
        if(totHr == totEd && totEd == totEc){
            
            resident.setResidentStatus("Allocated");
             residentDao.updateObject(resident);
             Allocated allo = new Allocated();
             allo.setStatus("Health");
             allo.setResident(resident);
             allo.setVillage(village);
             allo.setResidentNames(resident.getFirstname()+" "+resident.getLastname());
             allo.setPhoneNumber(resident.getPhoneNumber());
             allo.setGender(resident.getGender());
             allo.setNid(resident.getNationalID());
             allo.setJobStatus(resident.getJobStatus());
             allo.setResidentDiseasesStatus(resident.getDiseasesStatus());
             allo.setResidentDisibailityStatus(resident.getDisabilityStatus());
             allo.setResidentCategory(resident.getCategory());
             allo.setResidentHealthInsuranceCard(resident.getHealthInsurance());
             allo.setResidentEducationLevel(resident.getEducationLevel());
             allo.setSupportStatus("active");

             allocatedDao.recordObject(allo);
             
             resident.setResidentStatus("Allocated");
             residentDao.updateObject(resident);
             allo.setStatus("Education");
             allo.setResident(resident);
             allo.setVillage(village);
             allo.setResidentNames(resident.getFirstname()+" "+resident.getLastname());
             allo.setPhoneNumber(resident.getPhoneNumber());
             allo.setGender(resident.getGender());
             allo.setNid(resident.getNationalID());
             allo.setJobStatus(resident.getJobStatus());
             allo.setResidentDiseasesStatus(resident.getDiseasesStatus());
             allo.setResidentDisibailityStatus(resident.getDisabilityStatus());
             allo.setResidentCategory(resident.getCategory());
             allo.setResidentHealthInsuranceCard(resident.getHealthInsurance());
             allo.setResidentEducationLevel(resident.getEducationLevel());
             allo.setSupportStatus("active");

             allocatedDao.recordObject(allo);
             
             resident.setResidentStatus("Allocated");
             residentDao.updateObject(resident);
             
             allo.setStatus("Economic");
             allo.setResident(resident);
             allo.setVillage(village);
             allo.setResidentNames(resident.getFirstname()+" "+resident.getLastname());
             allo.setPhoneNumber(resident.getPhoneNumber());
             allo.setGender(resident.getGender());
             allo.setNid(resident.getNationalID());
             allo.setJobStatus(resident.getJobStatus());
             allo.setResidentDiseasesStatus(resident.getDiseasesStatus());
             allo.setResidentDisibailityStatus(resident.getDisabilityStatus());
             allo.setResidentCategory(resident.getCategory());
             allo.setResidentHealthInsuranceCard(resident.getHealthInsurance());
             allo.setResidentEducationLevel(resident.getEducationLevel());
             allo.setSupportStatus("active");

             allocatedDao.recordObject(allo);
             successMessage(resident.getFirstname()+" "+resident.getLastname()+" Is Allocated in all categories");
        }
         
        else if( totHr >= totEd && totHr >= totEc || totHr>=7){
             resident.setResidentStatus("Allocated");
             residentDao.updateObject(resident);
             Allocated allo = new Allocated();
             allo.setStatus("Health");
             allo.setResident(resident);
             allo.setVillage(village);
             allo.setResidentNames(resident.getFirstname()+" "+resident.getLastname());
             allo.setPhoneNumber(resident.getPhoneNumber());
             allo.setGender(resident.getGender());
             allo.setNid(resident.getNationalID());
             allo.setJobStatus(resident.getJobStatus());
             allo.setResidentDiseasesStatus(resident.getDiseasesStatus());
             allo.setResidentDisibailityStatus(resident.getDisabilityStatus());
             allo.setResidentCategory(resident.getCategory());
             allo.setResidentHealthInsuranceCard(resident.getHealthInsurance());
             allo.setResidentEducationLevel(resident.getEducationLevel());
             allo.setSupportStatus("active");

             allocatedDao.recordObject(allo);
             successMessage(resident.getFirstname()+" "+resident.getLastname()+" Is Allocated in Health Category");
         }
        else if (totEd >= totHr && totEd >= totEc || totEd>=7){
          resident.setResidentStatus("Allocated");
             residentDao.updateObject(resident);
             Allocated allo = new Allocated();
             allo.setStatus("Education");
             allo.setResident(resident);
             allo.setVillage(village);
             allo.setResidentNames(resident.getFirstname()+" "+resident.getLastname());
             allo.setPhoneNumber(resident.getPhoneNumber());
             allo.setGender(resident.getGender());
             allo.setNid(resident.getNationalID());
             allo.setJobStatus(resident.getJobStatus());
             allo.setResidentDiseasesStatus(resident.getDiseasesStatus());
             allo.setResidentDisibailityStatus(resident.getDisabilityStatus());
             allo.setResidentCategory(resident.getCategory());
             allo.setResidentHealthInsuranceCard(resident.getHealthInsurance());
             allo.setResidentEducationLevel(resident.getEducationLevel());
             allo.setSupportStatus("active");
             allocatedDao.recordObject(allo);
             successMessage(resident.getFirstname()+" "+resident.getLastname()+" Is Allocated in Education Category");
        }
        else{
             resident.setResidentStatus("Allocated");
             residentDao.updateObject(resident);
             Allocated allo = new Allocated();
             allo.setStatus("Economic");
             allo.setResident(resident);
             allo.setVillage(village);
             allo.setResidentNames(resident.getFirstname()+" "+resident.getLastname());
             allo.setPhoneNumber(resident.getPhoneNumber());
             allo.setGender(resident.getGender());
             allo.setNid(resident.getNationalID());
             allo.setJobStatus(resident.getJobStatus());
             allo.setResidentDiseasesStatus(resident.getDiseasesStatus());
             allo.setResidentDisibailityStatus(resident.getDisabilityStatus());
             allo.setResidentCategory(resident.getCategory());
             allo.setResidentHealthInsuranceCard(resident.getHealthInsurance());
             allo.setResidentEducationLevel(resident.getEducationLevel());
             allo.setSupportStatus("active");
             allocatedDao.recordObject(allo);
             successMessage(resident.getFirstname()+" "+resident.getLastname()+" Is Allocated in Economic Category");
        }
         
            return "residents.xhtml";
        } catch (Exception e) {
            return "residents.xhtml";
        }
    }
    
    
    public String findResident(Integer id){
        resident = residentDao.findOne(Resident.class, id);
        System.out.println("resident "+resident.getFirstname());
        return "update-resident.xhtml?faces-redirect=true";
    }
    
    public String updateResident(){
        
        // HEALT INSURANCE CAT
        Integer h1=0;
        Integer hic=0;
        
        Integer h2=0;
        Integer atw=0;
        
        Integer h3=0;
        Integer h4=0;
        Integer dd=0;
        
        Integer h5=0;
        Integer hmi=0;
        
        Integer h6=0;
        Integer h7=0;
        Integer hym=0;
        
        // EDUCATION CAT
        Integer ed1=0;
        Integer edle=0;
        
        Integer ed2=0;
        Integer cho=0;
        
        Integer ed3=0;
        Integer ed4=0;
        Integer jb=0;
        
        // ECONOMIC CAT
        Integer hs1=0;
        Integer owh=0;
        
        Integer hs2=0;
        Integer owhAn=0;
        
        Integer hs3=0;
        Integer owhBus=0;
        
        Integer hs4=0;
        Integer owhLa=0;
        
        Integer hs5=0;
        Integer hs6=0;
        Integer hs7=0;
        Integer ecc=0;
        
        
        try {
             User usr = userDao.getUsername(user.getUsername());
             System.out.println("Resident Name "+resident.getFirstname()); 
             residentDao.updateObject(resident);
             
              Allocated alloH = allocatedDao.getAllocatedByResidentIdByHealth(resident.getId());
              Allocated alloEc = allocatedDao.getAllocatedByResidentIdByEconomic(resident.getId());
              Allocated alloEd = allocatedDao.getAllocatedByResidentIdByEducation(resident.getId());
              Allocated alloRic = allocatedDao.getAllocatedByResidentIdByRich(resident.getId());



//-------------------------------------------------------------------------------------------------------                
              if(alloH!=null){
                  allocatedDao.deleteObject(alloH);
                  Integer id = resident.getId();
                  
                  
                   //HEALTH LEVELING
            
            if(resident.getHealthInsurance().equalsIgnoreCase("None")){
                h1=4;
            }
            if(resident.getHealthInsurance().equalsIgnoreCase("Mituelle")){
                h1=3;
            }
            if(resident.getHealthInsurance().equalsIgnoreCase("Rama")){
                h1=2;
            }
            if(resident.getHealthInsurance().equalsIgnoreCase("Other")){
                h1=1;
            }
            hic = h1;
            
            if(resident.getAbleToWork().equalsIgnoreCase("No")){
                h2=4;
            }
            if(resident.getAbleToWork().equalsIgnoreCase("Yes")){
                h2=0;
            }
            
            atw=h2;
            
            if(resident.getDiseasesStatus().equalsIgnoreCase("No")){
                h3=0;
            }
            if(resident.getDiseasesStatus().equalsIgnoreCase("Yes")){
                h3=2;
            }
            
            if(resident.getDisabilityStatus().equalsIgnoreCase("No")){
                h4=0;
            }
            if(resident.getDisabilityStatus().equalsIgnoreCase("Yes")){
                h4=2;
            }
            
            dd = h3+h4;
            
             if(resident.getHouseMemberInsured().equalsIgnoreCase("No")){
                h5=4;
            }
            if(resident.getHouseMemberInsured().equalsIgnoreCase("Some")){
                h5=3;
            }
            if(resident.getHouseMemberInsured().equalsIgnoreCase("Yes")){
                h5=0;
            }
            if(resident.getHouseMemberInsured().equalsIgnoreCase("None")){
                h5=0;
            }
            
            hmi = h5;
             
            if(resident.getAbleToAffordHygienicMaterials().equalsIgnoreCase("No")){
                h6=2;
            }
            if(resident.getAbleToAffordHygienicMaterials().equalsIgnoreCase("Yes")){
                h6=0;
            }
            if(resident.getMealsPerDayRange()<=1){
                h7=2;
            }
            if(resident.getMealsPerDayRange()>1){
                h7=0;
            }
            hym = h7+h6;
            
           
            Integer hr = hmi+dd+atw+hic+hym;
            resident.setHealthRates(hr);
            residentDao.updateObject(resident);
            
            // EDUCATION LEVELING
            
            if(resident.getChildrenNo()<=1){
              if(resident.getEducationLevel().equalsIgnoreCase("None")){
                ed1=16;
            }
            if(resident.getEducationLevel().equalsIgnoreCase("Primary")){
                ed1=14;
            }
            if(resident.getEducationLevel().equalsIgnoreCase("Secondary")){
                ed1=12;
            }
            if(resident.getEducationLevel().equalsIgnoreCase("University")){
                ed1=6;
            }
            edle = ed1;
            resident.setEducationRates(edle);
            residentDao.updateObject(resident);
                
            }else{
             if(resident.getEducationLevel().equalsIgnoreCase("None")){
                ed1=8;
            }
            if(resident.getEducationLevel().equalsIgnoreCase("Primary")){
                ed1=6;
            }
            if(resident.getEducationLevel().equalsIgnoreCase("Secondary")){
                ed1=4;
            }
            if(resident.getEducationLevel().equalsIgnoreCase("University")){
                ed1=2;
            }
            edle = ed1;
           
            if(resident.getChildrenOccupation().equalsIgnoreCase("None")){
                ed2=8;
            }
            if(resident.getChildrenOccupation().equalsIgnoreCase("Students")){
                ed2=6;
            }
            if(resident.getChildrenOccupation().equalsIgnoreCase("Workers-Students")){
                ed2=4;
            }
            if(resident.getChildrenOccupation().equalsIgnoreCase("Workers")){
                ed2=2;
            }
            cho =ed2;
           
            
            }
            
            if(resident.getJobStatus().equalsIgnoreCase("No")){
               ed3=2; 
            }
            if(resident.getJobStatus().equalsIgnoreCase("Yes")){
               ed3=0; 
            }
            
            if(resident.getJobType().equalsIgnoreCase("FULL TIME")){
               ed4=0;
            }
            if(resident.getJobType().equalsIgnoreCase("PART TIME")){
               ed4=1;
                System.out.println("ed4 "+ed4);
            }
            if(resident.getJobType().equalsIgnoreCase("None")){
               ed4=2;
            }
            
            jb = ed3+ed4;
            
            Integer edtot =edle+cho+jb;
            resident.setEducationRates(edtot);
            residentDao.updateObject(resident);
            
            // ECONOMIC LEVELING
            
            
            if(resident.getOwnHouse().equalsIgnoreCase("No")){
                hs1=4;
            }
            if(resident.getOwnHouse().equalsIgnoreCase("Yes")){
                hs1=0;
            }
            owh=hs1;
            if(resident.getOwnAnimal().equalsIgnoreCase("No")){
                hs2=4;
            }
            if(resident.getOwnAnimal().equalsIgnoreCase("Yes")){
                hs2=0;
            }
            owhAn=hs2;
            if(resident.getOwnBusiness().equalsIgnoreCase("No")){
                hs3=4;
            }
           
            if(resident.getOwnBusiness().equalsIgnoreCase("Yes")){
                hs3=0;
            }
            owhBus=hs3;
            if(resident.getOwnLand().equalsIgnoreCase("No")){
                hs4=4;
            }
            if(resident.getOwnLand().equalsIgnoreCase("Yes")){
                hs4=0;
            }
            
             owhLa=hs4;
            
            if(resident.getCategory().equalsIgnoreCase("Category I")){
                hs5=3;
            }
            if(resident.getCategory().equalsIgnoreCase("Category II")){
                hs5=2;
            }
            if(resident.getCategory().equalsIgnoreCase("Category III")){
                hs5=1;
            }
            if(resident.getCategory().equalsIgnoreCase("Category IV")){
                hs5=0;
            }
            
            if(resident.getAbleToAffordPower().equalsIgnoreCase("No")){
                hs6=1;
            }
            if(resident.getAbleToAffordPower().equalsIgnoreCase("Yes")){
                hs6=0;
            }
            if(resident.getAbleToAffordWater().equalsIgnoreCase("No")){
                hs7=1;
            }
            if(resident.getAbleToAffordWater().equalsIgnoreCase("No")){
                hs7=0;
            }
            
            ecc= hs5+hs6+hs7;
            Integer totec=owh+owhAn+owhBus+owhLa+ecc;
            resident.setEconomicRates(totec);
            residentDao.updateObject(resident);
            
            
            allocate(id);
           successMessage(resident.getFirstname()+" "+resident.getLastname()+" Is updated");
            
            
            
              }
              
              
              
  //---------------------------------------------------------------------------------------------------------------------
              if(alloEc!=null){
                  allocatedDao.deleteObject(alloEc);
                  Integer id = resident.getId();
                  
                  
                   //HEALTH LEVELING
            
            if(resident.getHealthInsurance().equalsIgnoreCase("None")){
                h1=4;
            }
            if(resident.getHealthInsurance().equalsIgnoreCase("Mituelle")){
                h1=3;
            }
            if(resident.getHealthInsurance().equalsIgnoreCase("Rama")){
                h1=2;
            }
            if(resident.getHealthInsurance().equalsIgnoreCase("Other")){
                h1=1;
            }
            hic = h1;
            
            if(resident.getAbleToWork().equalsIgnoreCase("No")){
                h2=4;
            }
            if(resident.getAbleToWork().equalsIgnoreCase("Yes")){
                h2=0;
            }
            
            atw=h2;
            
            if(resident.getDiseasesStatus().equalsIgnoreCase("No")){
                h3=0;
            }
            if(resident.getDiseasesStatus().equalsIgnoreCase("Yes")){
                h3=2;
            }
            
            if(resident.getDisabilityStatus().equalsIgnoreCase("No")){
                h4=0;
            }
            if(resident.getDisabilityStatus().equalsIgnoreCase("Yes")){
                h4=2;
            }
            
            dd = h3+h4;
            
             if(resident.getHouseMemberInsured().equalsIgnoreCase("No")){
                h5=4;
            }
            if(resident.getHouseMemberInsured().equalsIgnoreCase("Some")){
                h5=3;
            }
            if(resident.getHouseMemberInsured().equalsIgnoreCase("Yes")){
                h5=0;
            }
            if(resident.getHouseMemberInsured().equalsIgnoreCase("None")){
                h5=0;
            }
            
            hmi = h5;
             
            if(resident.getAbleToAffordHygienicMaterials().equalsIgnoreCase("No")){
                h6=2;
            }
            if(resident.getAbleToAffordHygienicMaterials().equalsIgnoreCase("Yes")){
                h6=0;
            }
            if(resident.getMealsPerDayRange()<=1){
                h7=2;
            }
            if(resident.getMealsPerDayRange()>1){
                h7=0;
            }
            hym = h7+h6;
            
           
            Integer hr = hmi+dd+atw+hic+hym;
            resident.setHealthRates(hr);
            residentDao.updateObject(resident);
            
            // EDUCATION LEVELING
            
            if(resident.getChildrenNo()<=1){
              if(resident.getEducationLevel().equalsIgnoreCase("None")){
                ed1=16;
            }
            if(resident.getEducationLevel().equalsIgnoreCase("Primary")){
                ed1=14;
            }
            if(resident.getEducationLevel().equalsIgnoreCase("Secondary")){
                ed1=12;
            }
            if(resident.getEducationLevel().equalsIgnoreCase("University")){
                ed1=6;
            }
            edle = ed1;
            resident.setEducationRates(edle);
            residentDao.updateObject(resident);
                
            }else{
             if(resident.getEducationLevel().equalsIgnoreCase("None")){
                ed1=8;
            }
            if(resident.getEducationLevel().equalsIgnoreCase("Primary")){
                ed1=6;
            }
            if(resident.getEducationLevel().equalsIgnoreCase("Secondary")){
                ed1=4;
            }
            if(resident.getEducationLevel().equalsIgnoreCase("University")){
                ed1=2;
            }
            edle = ed1;
           
            if(resident.getChildrenOccupation().equalsIgnoreCase("None")){
                ed2=8;
            }
            if(resident.getChildrenOccupation().equalsIgnoreCase("Students")){
                ed2=6;
            }
            if(resident.getChildrenOccupation().equalsIgnoreCase("Workers-Students")){
                ed2=4;
            }
            if(resident.getChildrenOccupation().equalsIgnoreCase("Workers")){
                ed2=2;
            }
            cho =ed2;
           
            
            }
            
            if(resident.getJobStatus().equalsIgnoreCase("No")){
               ed3=2; 
            }
            if(resident.getJobStatus().equalsIgnoreCase("Yes")){
               ed3=0; 
            }
            
            if(resident.getJobType().equalsIgnoreCase("FULL TIME")){
               ed4=0;
            }
            if(resident.getJobType().equalsIgnoreCase("PART TIME")){
               ed4=1;
            }
            if(resident.getJobType().equalsIgnoreCase("None")){
               ed4=2;
            }
            
            jb = ed3+ed4;
            
            Integer edtot =edle+cho+jb;
            resident.setEducationRates(edtot);
            residentDao.updateObject(resident);
            
            // ECONOMIC LEVELING
            
            
            if(resident.getOwnHouse().equalsIgnoreCase("No")){
                hs1=4;
            }
            if(resident.getOwnHouse().equalsIgnoreCase("Yes")){
                hs1=0;
            }
            owh=hs1;
            if(resident.getOwnAnimal().equalsIgnoreCase("No")){
                hs2=4;
            }
            if(resident.getOwnAnimal().equalsIgnoreCase("Yes")){
                hs2=0;
            }
            owhAn=hs2;
            if(resident.getOwnBusiness().equalsIgnoreCase("No")){
                hs3=4;
            }
           
            if(resident.getOwnBusiness().equalsIgnoreCase("Yes")){
                hs3=0;
            }
            owhBus=hs3;
            if(resident.getOwnLand().equalsIgnoreCase("No")){
                hs4=4;
            }
            if(resident.getOwnLand().equalsIgnoreCase("Yes")){
                hs4=0;
            }
            
             owhLa=hs4;
            
            if(resident.getCategory().equalsIgnoreCase("Category I")){
                hs5=3;
            }
            if(resident.getCategory().equalsIgnoreCase("Category II")){
                hs5=2;
            }
            if(resident.getCategory().equalsIgnoreCase("Category III")){
                hs5=1;
            }
            if(resident.getCategory().equalsIgnoreCase("Category IV")){
                hs5=0;
            }
            
            if(resident.getAbleToAffordPower().equalsIgnoreCase("No")){
                hs6=1;
            }
            if(resident.getAbleToAffordPower().equalsIgnoreCase("Yes")){
                hs6=0;
            }
            if(resident.getAbleToAffordWater().equalsIgnoreCase("No")){
                hs7=1;
            }
            if(resident.getAbleToAffordWater().equalsIgnoreCase("No")){
                hs7=0;
            }
            
            ecc= hs5+hs6+hs7;
            Integer totec=owh+owhAn+owhBus+owhLa+ecc;
            resident.setEconomicRates(totec);
            residentDao.updateObject(resident);
            
                  allocate(id);
            successMessage(resident.getFirstname()+" "+resident.getLastname()+" Is updated");
            
            
              }
   

              
              
              
//-------------------------------------------------------------------------------------------------------   
              if(alloEd!=null){
               Integer id = resident.getId();    
                  allocatedDao.deleteObject(alloEd);
                 
                  
                  
                   //HEALTH LEVELING
            
            if(resident.getHealthInsurance().equalsIgnoreCase("None")){
                h1=4;
            }
            if(resident.getHealthInsurance().equalsIgnoreCase("Mituelle")){
                h1=3;
            }
            if(resident.getHealthInsurance().equalsIgnoreCase("Rama")){
                h1=2;
            }
            if(resident.getHealthInsurance().equalsIgnoreCase("Other")){
                h1=1;
            }
            hic = h1;
            
            if(resident.getAbleToWork().equalsIgnoreCase("No")){
                h2=4;
            }
            if(resident.getAbleToWork().equalsIgnoreCase("Yes")){
                h2=0;
            }
            
            atw=h2;
            
            if(resident.getDiseasesStatus().equalsIgnoreCase("No")){
                h3=0;
            }
            if(resident.getDiseasesStatus().equalsIgnoreCase("Yes")){
                h3=2;
            }
            
            if(resident.getDisabilityStatus().equalsIgnoreCase("No")){
                h4=0;
            }
            if(resident.getDisabilityStatus().equalsIgnoreCase("Yes")){
                h4=2;
            }
            
            dd = h3+h4;
            
             if(resident.getHouseMemberInsured().equalsIgnoreCase("No")){
                h5=4;
            }
            if(resident.getHouseMemberInsured().equalsIgnoreCase("Some")){
                h5=3;
            }
            if(resident.getHouseMemberInsured().equalsIgnoreCase("Yes")){
                h5=0;
            }
            if(resident.getHouseMemberInsured().equalsIgnoreCase("None")){
                h5=0;
            }
            
            hmi = h5;
             
            if(resident.getAbleToAffordHygienicMaterials().equalsIgnoreCase("No")){
                h6=2;
            }
            if(resident.getAbleToAffordHygienicMaterials().equalsIgnoreCase("Yes")){
                h6=0;
            }
            if(resident.getMealsPerDayRange()<=1){
                h7=2;
            }
            if(resident.getMealsPerDayRange()>1){
                h7=0;
            }
            hym = h7+h6;
            
           
            Integer hr = hmi+dd+atw+hic+hym;
            resident.setHealthRates(hr);
            residentDao.updateObject(resident);
            
            // EDUCATION LEVELING
            
            if(resident.getChildrenNo()<=1){
              if(resident.getEducationLevel().equalsIgnoreCase("None")){
                ed1=16;
            }
            if(resident.getEducationLevel().equalsIgnoreCase("Primary")){
                ed1=14;
            }
            if(resident.getEducationLevel().equalsIgnoreCase("Secondary")){
                ed1=12;
            }
            if(resident.getEducationLevel().equalsIgnoreCase("University")){
                ed1=6;
            }
            edle = ed1;
            resident.setEducationRates(edle);
            residentDao.updateObject(resident);
                
            }else{
             if(resident.getEducationLevel().equalsIgnoreCase("None")){
                ed1=8;
            }
            if(resident.getEducationLevel().equalsIgnoreCase("Primary")){
                ed1=6;
            }
            if(resident.getEducationLevel().equalsIgnoreCase("Secondary")){
                ed1=4;
            }
            if(resident.getEducationLevel().equalsIgnoreCase("University")){
                ed1=2;
            }
            edle = ed1;
           
            if(resident.getChildrenOccupation().equalsIgnoreCase("None")){
                ed2=8;
            }
            if(resident.getChildrenOccupation().equalsIgnoreCase("Students")){
                ed2=6;
            }
            if(resident.getChildrenOccupation().equalsIgnoreCase("Workers-Students")){
                ed2=4;
            }
            if(resident.getChildrenOccupation().equalsIgnoreCase("Workers")){
                ed2=2;
            }
            cho =ed2;
           
            
            }
            
            if(resident.getJobStatus().equalsIgnoreCase("No")){
               ed3=2; 
            }
            if(resident.getJobStatus().equalsIgnoreCase("Yes")){
               ed3=0; 
            }
            
            if(resident.getJobType().equalsIgnoreCase("FULL TIME")){
               ed4=0;
            }
            if(resident.getJobType().equalsIgnoreCase("PART TIME")){
               ed4=1;
            }
            if(resident.getJobType().equalsIgnoreCase("None")){
               ed4=2;
            }
            
            jb = ed3+ed4;
            
            Integer edtot =edle+cho+jb;
            resident.setEducationRates(edtot);
            residentDao.updateObject(resident);
            
            // ECONOMIC LEVELING
            
            
            if(resident.getOwnHouse().equalsIgnoreCase("No")){
                hs1=4;
            }
            if(resident.getOwnHouse().equalsIgnoreCase("Yes")){
                hs1=0;
            }
            owh=hs1;
            if(resident.getOwnAnimal().equalsIgnoreCase("No")){
                hs2=4;
            }
            if(resident.getOwnAnimal().equalsIgnoreCase("Yes")){
                hs2=0;
            }
            owhAn=hs2;
            if(resident.getOwnBusiness().equalsIgnoreCase("No")){
                hs3=4;
            }
           
            if(resident.getOwnBusiness().equalsIgnoreCase("Yes")){
                hs3=0;
            }
            owhBus=hs3;
            if(resident.getOwnLand().equalsIgnoreCase("No")){
                hs4=4;
            }
            if(resident.getOwnLand().equalsIgnoreCase("Yes")){
                hs4=0;
            }
            
             owhLa=hs4;
            
            if(resident.getCategory().equalsIgnoreCase("Category I")){
                hs5=3;
            }
            if(resident.getCategory().equalsIgnoreCase("Category II")){
                hs5=2;
            }
            if(resident.getCategory().equalsIgnoreCase("Category III")){
                hs5=1;
            }
            if(resident.getCategory().equalsIgnoreCase("Category IV")){
                hs5=0;
            }
            
            if(resident.getAbleToAffordPower().equalsIgnoreCase("No")){
                hs6=1;
            }
            if(resident.getAbleToAffordPower().equalsIgnoreCase("Yes")){
                hs6=0;
            }
            if(resident.getAbleToAffordWater().equalsIgnoreCase("No")){
                hs7=1;
            }
            if(resident.getAbleToAffordWater().equalsIgnoreCase("No")){
                hs7=0;
            }
            
            ecc= hs5+hs6+hs7;
            Integer totec=owh+owhAn+owhBus+owhLa+ecc;
            resident.setEconomicRates(totec);
            residentDao.updateObject(resident);
            
            allocate(id);
                  
              }
              if(alloRic!=null){
                   Integer id = resident.getId();
                  System.out.println("Yes: RICH "+alloRic.getResidentNames());
                  allocatedDao.deleteObject(alloRic);
                  
                  
                   //HEALTH LEVELING
            
            if(resident.getHealthInsurance().equalsIgnoreCase("None")){
                h1=4;
            }
            if(resident.getHealthInsurance().equalsIgnoreCase("Mituelle")){
                h1=3;
            }
            if(resident.getHealthInsurance().equalsIgnoreCase("Rama")){
                h1=2;
            }
            if(resident.getHealthInsurance().equalsIgnoreCase("Other")){
                h1=1;
            }
            hic = h1;
            
            if(resident.getAbleToWork().equalsIgnoreCase("No")){
                h2=4;
            }
            if(resident.getAbleToWork().equalsIgnoreCase("Yes")){
                h2=0;
            }
            
            atw=h2;
            
            if(resident.getDiseasesStatus().equalsIgnoreCase("No")){
                h3=0;
            }
            if(resident.getDiseasesStatus().equalsIgnoreCase("Yes")){
                h3=2;
            }
            
            if(resident.getDisabilityStatus().equalsIgnoreCase("No")){
                h4=0;
            }
            if(resident.getDisabilityStatus().equalsIgnoreCase("Yes")){
                h4=2;
            }
            
            dd = h3+h4;
            
             if(resident.getHouseMemberInsured().equalsIgnoreCase("No")){
                h5=4;
            }
            if(resident.getHouseMemberInsured().equalsIgnoreCase("Some")){
                h5=3;
            }
            if(resident.getHouseMemberInsured().equalsIgnoreCase("Yes")){
                h5=0;
            }
            if(resident.getHouseMemberInsured().equalsIgnoreCase("None")){
                h5=0;
            }
            
            hmi = h5;
             
            if(resident.getAbleToAffordHygienicMaterials().equalsIgnoreCase("No")){
                h6=2;
            }
            if(resident.getAbleToAffordHygienicMaterials().equalsIgnoreCase("Yes")){
                h6=0;
            }
            if(resident.getMealsPerDayRange()<=1){
                h7=2;
            }
            if(resident.getMealsPerDayRange()>1){
                h7=0;
            }
            hym = h7+h6;
            
           
            Integer hr = hmi+dd+atw+hic+hym;
            resident.setHealthRates(hr);
            residentDao.updateObject(resident);
            
            // EDUCATION LEVELING
            
            if(resident.getChildrenNo()<=1){
              if(resident.getEducationLevel().equalsIgnoreCase("None")){
                ed1=16;
            }
            if(resident.getEducationLevel().equalsIgnoreCase("Primary")){
                ed1=14;
            }
            if(resident.getEducationLevel().equalsIgnoreCase("Secondary")){
                ed1=12;
            }
            if(resident.getEducationLevel().equalsIgnoreCase("University")){
                ed1=6;
            }
            edle = ed1;
            resident.setEducationRates(edle);
            residentDao.updateObject(resident);
                
            }else{
             if(resident.getEducationLevel().equalsIgnoreCase("None")){
                ed1=8;
            }
            if(resident.getEducationLevel().equalsIgnoreCase("Primary")){
                ed1=6;
            }
            if(resident.getEducationLevel().equalsIgnoreCase("Secondary")){
                ed1=4;
            }
            if(resident.getEducationLevel().equalsIgnoreCase("University")){
                ed1=2;
            }
            edle = ed1;
           
            if(resident.getChildrenOccupation().equalsIgnoreCase("None")){
                ed2=8;
            }
            if(resident.getChildrenOccupation().equalsIgnoreCase("Students")){
                ed2=6;
            }
            if(resident.getChildrenOccupation().equalsIgnoreCase("Workers-Students")){
                ed2=4;
            }
            if(resident.getChildrenOccupation().equalsIgnoreCase("Workers")){
                ed2=2;
            }
            cho =ed2;
           
            
            }
            
            if(resident.getJobStatus().equalsIgnoreCase("No")){
               ed3=2; 
            }
            if(resident.getJobStatus().equalsIgnoreCase("Yes")){
               ed3=0; 
            }
            
            if(resident.getJobType().equalsIgnoreCase("FULL TIME")){
               ed4=0;
            }
            if(resident.getJobType().equalsIgnoreCase("PART TIME")){
               ed4=1;
               
            }
            if(resident.getJobType().equalsIgnoreCase("None")){
               ed4=2;
            }
            
            jb = ed3+ed4;
            
            Integer edtot =edle+cho+jb;
            resident.setEducationRates(edtot);
            residentDao.updateObject(resident);
            
            // ECONOMIC LEVELING
            
            
            if(resident.getOwnHouse().equalsIgnoreCase("No")){
                hs1=4;
            }
            if(resident.getOwnHouse().equalsIgnoreCase("Yes")){
                hs1=0;
            }
            owh=hs1;
            if(resident.getOwnAnimal().equalsIgnoreCase("No")){
                hs2=4;
            }
            if(resident.getOwnAnimal().equalsIgnoreCase("Yes")){
                hs2=0;
            }
            owhAn=hs2;
            if(resident.getOwnBusiness().equalsIgnoreCase("No")){
                hs3=4;
            }
           
            if(resident.getOwnBusiness().equalsIgnoreCase("Yes")){
                hs3=0;
            }
            owhBus=hs3;
            if(resident.getOwnLand().equalsIgnoreCase("No")){
                hs4=4;
            }
            if(resident.getOwnLand().equalsIgnoreCase("Yes")){
                hs4=0;
            }
            
             owhLa=hs4;
            
            if(resident.getCategory().equalsIgnoreCase("Category I")){
                hs5=3;
            }
            if(resident.getCategory().equalsIgnoreCase("Category II")){
                hs5=2;
            }
            if(resident.getCategory().equalsIgnoreCase("Category III")){
                hs5=1;
            }
            if(resident.getCategory().equalsIgnoreCase("Category IV")){
                hs5=0;
            }
            
            if(resident.getAbleToAffordPower().equalsIgnoreCase("No")){
                hs6=1;
            }
            if(resident.getAbleToAffordPower().equalsIgnoreCase("Yes")){
                hs6=0;
            }
            if(resident.getAbleToAffordWater().equalsIgnoreCase("No")){
                hs7=1;
            }
            if(resident.getAbleToAffordWater().equalsIgnoreCase("No")){
                hs7=0;
            }
            
            ecc= hs5+hs6+hs7;
            Integer totec=owh+owhAn+owhBus+owhLa+ecc;
            resident.setEconomicRates(totec);
            residentDao.updateObject(resident);
             allocate(id);
             successMessage(resident.getFirstname()+" "+resident.getLastname()+" Is updated");
                  
             }
              
             if(alloH==null && alloEd == null && alloEc ==null){
                Integer id = resident.getId();
                 
                
                
                   
                  
            
            if(resident.getHealthInsurance().equalsIgnoreCase("None")){
                h1=4;
            }
            if(resident.getHealthInsurance().equalsIgnoreCase("Mituelle")){
                h1=3;
            }
            if(resident.getHealthInsurance().equalsIgnoreCase("Rama")){
                h1=2;
            }
            if(resident.getHealthInsurance().equalsIgnoreCase("Other")){
                h1=1;
            }
            hic = h1;
            
            if(resident.getAbleToWork().equalsIgnoreCase("No")){
                h2=4;
            }
            if(resident.getAbleToWork().equalsIgnoreCase("Yes")){
                h2=0;
            }
            
            atw=h2;
            
            if(resident.getDiseasesStatus().equalsIgnoreCase("No")){
                h3=0;
            }
            if(resident.getDiseasesStatus().equalsIgnoreCase("Yes")){
                h3=2;
            }
            
            if(resident.getDisabilityStatus().equalsIgnoreCase("No")){
                h4=0;
            }
            if(resident.getDisabilityStatus().equalsIgnoreCase("Yes")){
                h4=2;
            }
            
            dd = h3+h4;
            
             if(resident.getHouseMemberInsured().equalsIgnoreCase("No")){
                h5=4;
            }
            if(resident.getHouseMemberInsured().equalsIgnoreCase("Some")){
                h5=3;
            }
            if(resident.getHouseMemberInsured().equalsIgnoreCase("Yes")){
                h5=0;
            }
            if(resident.getHouseMemberInsured().equalsIgnoreCase("None")){
                h5=0;
            }
            
            hmi = h5;
             
            if(resident.getAbleToAffordHygienicMaterials().equalsIgnoreCase("No")){
                h6=2;
            }
            if(resident.getAbleToAffordHygienicMaterials().equalsIgnoreCase("Yes")){
                h6=0;
            }
            if(resident.getMealsPerDayRange()<=1){
                h7=2;
            }
            if(resident.getMealsPerDayRange()>1){
                h7=0;
            }
            hym = h7+h6;
            
           
            Integer hr = hmi+dd+atw+hic+hym;
            resident.setHealthRates(hr);
            residentDao.updateObject(resident);
            
            // EDUCATION LEVELING
            
            if(resident.getChildrenNo()<=1){
              if(resident.getEducationLevel().equalsIgnoreCase("None")){
                ed1=16;
            }
            if(resident.getEducationLevel().equalsIgnoreCase("Primary")){
                ed1=14;
            }
            if(resident.getEducationLevel().equalsIgnoreCase("Secondary")){
                ed1=12;
            }
            if(resident.getEducationLevel().equalsIgnoreCase("University")){
                ed1=6;
            }
            edle = ed1;
            resident.setEducationRates(edle);
            residentDao.updateObject(resident);
                
            }else{
             if(resident.getEducationLevel().equalsIgnoreCase("None")){
                ed1=8;
            }
            if(resident.getEducationLevel().equalsIgnoreCase("Primary")){
                ed1=6;
            }
            if(resident.getEducationLevel().equalsIgnoreCase("Secondary")){
                ed1=4;
            }
            if(resident.getEducationLevel().equalsIgnoreCase("University")){
                ed1=2;
            }
            edle = ed1;
           
            if(resident.getChildrenOccupation().equalsIgnoreCase("None")){
                ed2=8;
            }
            if(resident.getChildrenOccupation().equalsIgnoreCase("Students")){
                ed2=6;
            }
            if(resident.getChildrenOccupation().equalsIgnoreCase("Workers-Students")){
                ed2=4;
            }
            if(resident.getChildrenOccupation().equalsIgnoreCase("Workers")){
                ed2=2;
            }
            cho =ed2;
           
            
            }
            
            if(resident.getJobStatus().equalsIgnoreCase("No")){
               ed3=2; 
            }
            if(resident.getJobStatus().equalsIgnoreCase("Yes")){
               ed3=0; 
            }
            
            if(resident.getJobType().equalsIgnoreCase("FULL TIME")){
               ed4=0;
            }
            if(resident.getJobType().equalsIgnoreCase("PART TIME")){
               ed4=1;
               
            }
            if(resident.getJobType().equalsIgnoreCase("None")){
               ed4=2;
            }
            
            jb = ed3+ed4;
            
            Integer edtot =edle+cho+jb;
            resident.setEducationRates(edtot);
            residentDao.updateObject(resident);
            
            // ECONOMIC LEVELING
            
            
            if(resident.getOwnHouse().equalsIgnoreCase("No")){
                hs1=4;
            }
            if(resident.getOwnHouse().equalsIgnoreCase("Yes")){
                hs1=0;
            }
            owh=hs1;
            if(resident.getOwnAnimal().equalsIgnoreCase("No")){
                hs2=4;
            }
            if(resident.getOwnAnimal().equalsIgnoreCase("Yes")){
                hs2=0;
            }
            owhAn=hs2;
            if(resident.getOwnBusiness().equalsIgnoreCase("No")){
                hs3=4;
            }
           
            if(resident.getOwnBusiness().equalsIgnoreCase("Yes")){
                hs3=0;
            }
            owhBus=hs3;
            if(resident.getOwnLand().equalsIgnoreCase("No")){
                hs4=4;
            }
            if(resident.getOwnLand().equalsIgnoreCase("Yes")){
                hs4=0;
            }
            
             owhLa=hs4;
            
            if(resident.getCategory().equalsIgnoreCase("Category I")){
                hs5=3;
            }
            if(resident.getCategory().equalsIgnoreCase("Category II")){
                hs5=2;
            }
            if(resident.getCategory().equalsIgnoreCase("Category III")){
                hs5=1;
            }
            if(resident.getCategory().equalsIgnoreCase("Category IV")){
                hs5=0;
            }
            
            if(resident.getAbleToAffordPower().equalsIgnoreCase("No")){
                hs6=1;
            }
            if(resident.getAbleToAffordPower().equalsIgnoreCase("Yes")){
                hs6=0;
            }
            if(resident.getAbleToAffordWater().equalsIgnoreCase("No")){
                hs7=1;
            }
            if(resident.getAbleToAffordWater().equalsIgnoreCase("No")){
                hs7=0;
            }
            
            ecc= hs5+hs6+hs7;
            Integer totec=owh+owhAn+owhBus+owhLa+ecc;
            resident.setEconomicRates(totec);
            residentDao.updateObject(resident);
            
            allocate(id);
            successMessage(resident.getFirstname()+" "+resident.getLastname()+" Is updated");
       
              }
              
            return "update-resident.xhtml";
        } catch (Exception e) {
            return "update-resident.xhtml";
        }
    }
    
    public String allocate(Integer id){
        try {
           User usr = userDao.getUsername(user.getUsername());
           resident = residentDao.findOne(Resident.class, id);
            System.out.println("HERE 1 "+resident.getFirstname());
           village = usr.getVillage();
            // ALLOCATING RESIDENT
            
            Integer hrates = resident.getHealthRates();
            Integer edrates = resident.getEducationRates();
            Integer ecrates = resident.getEconomicRates();
            
            Integer totrates = hrates+edrates+ecrates;
            System.out.println("Totttt "+totrates);
            
            Allocated allo = new Allocated();
            
            if(totrates<=30){
                allo.setResident(resident);
                allo.setVillage(village);
                allo.setNid(resident.getNationalID());
                allo.setPhoneNumber(resident.getPhoneNumber());
                allo.setResidentNames(resident.getFirstname()+" "+resident.getLastname());
                allo.setResidentCategory(resident.getCategory());
                allo.setResidentEducationLevel(resident.getEducationLevel());
                allo.setResidentDiseasesStatus(resident.getDiseasesStatus());
                allo.setResidentDisibailityStatus(resident.getDisabilityStatus());
                allo.setResidentHealthInsuranceCard(resident.getHealthInsurance());
                allo.setGender(resident.getGender());
                allo.setJobStatus(resident.getJobStatus());
                allo.setSupportStatus("active");
                allo.setStatus("Rich");
                allo.setOwnBusiness(resident.getOwnBusiness());
                allo.setOwnHouse(resident.getOwnHouse());
                allocatedDao.recordObject(allo);
                
                
            }else{
                if(hrates>=10 && edrates >= 10 && ecrates>=10){
                    
                if(hrates>edrates && edrates >= ecrates){
                        
                allo.setResident(resident);
                allo.setVillage(village);
                allo.setNid(resident.getNationalID());
                allo.setPhoneNumber(resident.getPhoneNumber());
                allo.setResidentNames(resident.getFirstname()+" "+resident.getLastname());
                allo.setResidentCategory(resident.getCategory());
                allo.setResidentEducationLevel(resident.getEducationLevel());
                allo.setResidentDiseasesStatus(resident.getDiseasesStatus());
                allo.setResidentDisibailityStatus(resident.getDisabilityStatus());
                allo.setResidentHealthInsuranceCard(resident.getHealthInsurance());
                allo.setGender(resident.getGender());
                allo.setJobStatus(resident.getJobStatus());
                allo.setSupportStatus("active");
                allo.setStatus("Health");
                allo.setOwnBusiness(resident.getOwnBusiness());
                allo.setOwnHouse(resident.getOwnHouse());
                allocatedDao.recordObject(allo);
                        
                 }else if(hrates>ecrates && ecrates>=edrates){
                 
                allo.setResident(resident);
                allo.setVillage(village);
                allo.setNid(resident.getNationalID());
                allo.setPhoneNumber(resident.getPhoneNumber());
                allo.setResidentNames(resident.getFirstname()+" "+resident.getLastname());
                allo.setResidentCategory(resident.getCategory());
                allo.setResidentEducationLevel(resident.getEducationLevel());
                allo.setResidentDiseasesStatus(resident.getDiseasesStatus());
                allo.setResidentDisibailityStatus(resident.getDisabilityStatus());
                allo.setResidentHealthInsuranceCard(resident.getHealthInsurance());
                allo.setGender(resident.getGender());
                allo.setJobStatus(resident.getJobStatus());
                allo.setSupportStatus("active");
                allo.setStatus("Health");
                allo.setOwnBusiness(resident.getOwnBusiness());
                allo.setOwnHouse(resident.getOwnHouse());
                allocatedDao.recordObject(allo);  
                
                }else if(edrates>hrates && hrates>=ecrates){
                     allo.setResident(resident);
                allo.setVillage(village);
                allo.setNid(resident.getNationalID());
                allo.setPhoneNumber(resident.getPhoneNumber());
                allo.setResidentNames(resident.getFirstname()+" "+resident.getLastname());
                allo.setResidentCategory(resident.getCategory());
                allo.setResidentEducationLevel(resident.getEducationLevel());
                allo.setResidentDiseasesStatus(resident.getDiseasesStatus());
                allo.setResidentDisibailityStatus(resident.getDisabilityStatus());
                allo.setResidentHealthInsuranceCard(resident.getHealthInsurance());
                allo.setGender(resident.getGender());
                allo.setJobStatus(resident.getJobStatus());
                allo.setSupportStatus("active");
                allo.setStatus("Education");
                allo.setOwnBusiness(resident.getOwnBusiness());
                allo.setOwnHouse(resident.getOwnHouse());
                allocatedDao.recordObject(allo);
                }else if(edrates>ecrates && ecrates>=hrates){
                allo.setResident(resident);
                allo.setVillage(village);
                allo.setNid(resident.getNationalID());
                allo.setPhoneNumber(resident.getPhoneNumber());
                allo.setResidentNames(resident.getFirstname()+" "+resident.getLastname());
                allo.setResidentCategory(resident.getCategory());
                allo.setResidentEducationLevel(resident.getEducationLevel());
                allo.setResidentDiseasesStatus(resident.getDiseasesStatus());
                allo.setResidentDisibailityStatus(resident.getDisabilityStatus());
                allo.setResidentHealthInsuranceCard(resident.getHealthInsurance());
                allo.setGender(resident.getGender());
                allo.setJobStatus(resident.getJobStatus());
                allo.setSupportStatus("active");
                allo.setStatus("Education");
                allo.setOwnBusiness(resident.getOwnBusiness());
                allo.setOwnHouse(resident.getOwnHouse());
                allocatedDao.recordObject(allo);
                }else if(ecrates>edrates && edrates>hrates){
                     allo.setResident(resident);
                allo.setVillage(village);
                allo.setNid(resident.getNationalID());
                allo.setPhoneNumber(resident.getPhoneNumber());
                allo.setResidentNames(resident.getFirstname()+" "+resident.getLastname());
                allo.setResidentCategory(resident.getCategory());
                allo.setResidentEducationLevel(resident.getEducationLevel());
                allo.setResidentDiseasesStatus(resident.getDiseasesStatus());
                allo.setResidentDisibailityStatus(resident.getDisabilityStatus());
                allo.setResidentHealthInsuranceCard(resident.getHealthInsurance());
                allo.setGender(resident.getGender());
                allo.setJobStatus(resident.getJobStatus());
                allo.setSupportStatus("active");
                allo.setStatus("Economic");
                allo.setOwnBusiness(resident.getOwnBusiness());
                allo.setOwnHouse(resident.getOwnHouse());
                allocatedDao.recordObject(allo);
                }else if(ecrates>hrates && hrates>=edrates){
                allo.setResident(resident);
                allo.setVillage(village);
                allo.setNid(resident.getNationalID());
                allo.setPhoneNumber(resident.getPhoneNumber());
                allo.setResidentNames(resident.getFirstname()+" "+resident.getLastname());
                allo.setResidentCategory(resident.getCategory());
                allo.setResidentEducationLevel(resident.getEducationLevel());
                allo.setResidentDiseasesStatus(resident.getDiseasesStatus());
                allo.setResidentDisibailityStatus(resident.getDisabilityStatus());
                allo.setResidentHealthInsuranceCard(resident.getHealthInsurance());
                allo.setGender(resident.getGender());
                allo.setJobStatus(resident.getJobStatus());
                allo.setSupportStatus("active");
                allo.setStatus("Economic");
                allo.setOwnBusiness(resident.getOwnBusiness());
                allo.setOwnHouse(resident.getOwnHouse());
                allocatedDao.recordObject(allo);
                }
                else if(hrates == edrates && edrates == ecrates){
                
                allo.setResident(resident);
                allo.setVillage(village);
                allo.setNid(resident.getNationalID());
                allo.setPhoneNumber(resident.getPhoneNumber());
                allo.setResidentNames(resident.getFirstname()+" "+resident.getLastname());
                allo.setResidentCategory(resident.getCategory());
                allo.setResidentEducationLevel(resident.getEducationLevel());
                allo.setResidentDiseasesStatus(resident.getDiseasesStatus());
                allo.setResidentDisibailityStatus(resident.getDisabilityStatus());
                allo.setResidentHealthInsuranceCard(resident.getHealthInsurance());
                allo.setGender(resident.getGender());
                allo.setJobStatus(resident.getJobStatus());
                allo.setSupportStatus("active");
                allo.setStatus("Economic");
                allo.setOwnBusiness(resident.getOwnBusiness());
                allo.setOwnHouse(resident.getOwnHouse());
                allocatedDao.recordObject(allo);
                
                allo.setResident(resident);
                allo.setVillage(village);
                allo.setNid(resident.getNationalID());
                allo.setPhoneNumber(resident.getPhoneNumber());
                allo.setResidentNames(resident.getFirstname()+" "+resident.getLastname());
                allo.setResidentCategory(resident.getCategory());
                allo.setResidentEducationLevel(resident.getEducationLevel());
                allo.setResidentDiseasesStatus(resident.getDiseasesStatus());
                allo.setResidentDisibailityStatus(resident.getDisabilityStatus());
                allo.setResidentHealthInsuranceCard(resident.getHealthInsurance());
                allo.setGender(resident.getGender());
                allo.setJobStatus(resident.getJobStatus());
                allo.setSupportStatus("active");
                allo.setStatus("Education");
                allo.setOwnBusiness(resident.getOwnBusiness());
                allo.setOwnHouse(resident.getOwnHouse());
                allocatedDao.recordObject(allo);
                
                allo.setResident(resident);
                allo.setVillage(village);
                allo.setNid(resident.getNationalID());
                allo.setPhoneNumber(resident.getPhoneNumber());
                allo.setResidentNames(resident.getFirstname()+" "+resident.getLastname());
                allo.setResidentCategory(resident.getCategory());
                allo.setResidentEducationLevel(resident.getEducationLevel());
                allo.setResidentDiseasesStatus(resident.getDiseasesStatus());
                allo.setResidentDisibailityStatus(resident.getDisabilityStatus());
                allo.setResidentHealthInsuranceCard(resident.getHealthInsurance());
                allo.setGender(resident.getGender());
                allo.setJobStatus(resident.getJobStatus());
                allo.setSupportStatus("active");
                allo.setStatus("Health");
                allo.setOwnBusiness(resident.getOwnBusiness());
                allo.setOwnHouse(resident.getOwnHouse());
                allocatedDao.recordObject(allo);
                
                }
                else if(hrates == edrates && edrates > ecrates){
                 allo.setResident(resident);
                allo.setVillage(village);
                allo.setNid(resident.getNationalID());
                allo.setPhoneNumber(resident.getPhoneNumber());
                allo.setResidentNames(resident.getFirstname()+" "+resident.getLastname());
                allo.setResidentCategory(resident.getCategory());
                allo.setResidentEducationLevel(resident.getEducationLevel());
                allo.setResidentDiseasesStatus(resident.getDiseasesStatus());
                allo.setResidentDisibailityStatus(resident.getDisabilityStatus());
                allo.setResidentHealthInsuranceCard(resident.getHealthInsurance());
                allo.setGender(resident.getGender());
                allo.setJobStatus(resident.getJobStatus());
                allo.setSupportStatus("active");
                allo.setStatus("Education");
                allo.setOwnBusiness(resident.getOwnBusiness());
                allo.setOwnHouse(resident.getOwnHouse());
                allocatedDao.recordObject(allo);
                
                allo.setResident(resident);
                allo.setVillage(village);
                allo.setNid(resident.getNationalID());
                allo.setPhoneNumber(resident.getPhoneNumber());
                allo.setResidentNames(resident.getFirstname()+" "+resident.getLastname());
                allo.setResidentCategory(resident.getCategory());
                allo.setResidentEducationLevel(resident.getEducationLevel());
                allo.setResidentDiseasesStatus(resident.getDiseasesStatus());
                allo.setResidentDisibailityStatus(resident.getDisabilityStatus());
                allo.setResidentHealthInsuranceCard(resident.getHealthInsurance());
                allo.setGender(resident.getGender());
                allo.setJobStatus(resident.getJobStatus());
                allo.setSupportStatus("active");
                allo.setStatus("Health");
                allo.setOwnBusiness(resident.getOwnBusiness());
                allo.setOwnHouse(resident.getOwnHouse());
                allocatedDao.recordObject(allo);
                
                }else if (hrates == ecrates && ecrates > edrates){
                   allo.setResident(resident);
                allo.setVillage(village);
                allo.setNid(resident.getNationalID());
                allo.setPhoneNumber(resident.getPhoneNumber());
                allo.setResidentNames(resident.getFirstname()+" "+resident.getLastname());
                allo.setResidentCategory(resident.getCategory());
                allo.setResidentEducationLevel(resident.getEducationLevel());
                allo.setResidentDiseasesStatus(resident.getDiseasesStatus());
                allo.setResidentDisibailityStatus(resident.getDisabilityStatus());
                allo.setResidentHealthInsuranceCard(resident.getHealthInsurance());
                allo.setGender(resident.getGender());
                allo.setJobStatus(resident.getJobStatus());
                allo.setSupportStatus("active");
                allo.setStatus("Economic");
                allo.setOwnBusiness(resident.getOwnBusiness());
                allo.setOwnHouse(resident.getOwnHouse());
                allocatedDao.recordObject(allo);
                
                allo.setResident(resident);
                allo.setVillage(village);
                allo.setNid(resident.getNationalID());
                allo.setPhoneNumber(resident.getPhoneNumber());
                allo.setResidentNames(resident.getFirstname()+" "+resident.getLastname());
                allo.setResidentCategory(resident.getCategory());
                allo.setResidentEducationLevel(resident.getEducationLevel());
                allo.setResidentDiseasesStatus(resident.getDiseasesStatus());
                allo.setResidentDisibailityStatus(resident.getDisabilityStatus());
                allo.setResidentHealthInsuranceCard(resident.getHealthInsurance());
                allo.setGender(resident.getGender());
                allo.setJobStatus(resident.getJobStatus());
                allo.setSupportStatus("active");
                allo.setStatus("Health");
                allo.setOwnBusiness(resident.getOwnBusiness());
                allo.setOwnHouse(resident.getOwnHouse());
                allocatedDao.recordObject(allo); 
                }else if(edrates == ecrates && ecrates > hrates){
                    allo.setResident(resident);
                allo.setVillage(village);
                allo.setNid(resident.getNationalID());
                allo.setPhoneNumber(resident.getPhoneNumber());
                allo.setResidentNames(resident.getFirstname()+" "+resident.getLastname());
                allo.setResidentCategory(resident.getCategory());
                allo.setResidentEducationLevel(resident.getEducationLevel());
                allo.setResidentDiseasesStatus(resident.getDiseasesStatus());
                allo.setResidentDisibailityStatus(resident.getDisabilityStatus());
                allo.setResidentHealthInsuranceCard(resident.getHealthInsurance());
                allo.setGender(resident.getGender());
                allo.setJobStatus(resident.getJobStatus());
                allo.setSupportStatus("active");
                allo.setStatus("Economic");
                allo.setOwnBusiness(resident.getOwnBusiness());
                allo.setOwnHouse(resident.getOwnHouse());
                allocatedDao.recordObject(allo);
                
                allo.setResident(resident);
                allo.setVillage(village);
                allo.setNid(resident.getNationalID());
                allo.setPhoneNumber(resident.getPhoneNumber());
                allo.setResidentNames(resident.getFirstname()+" "+resident.getLastname());
                allo.setResidentCategory(resident.getCategory());
                allo.setResidentEducationLevel(resident.getEducationLevel());
                allo.setResidentDiseasesStatus(resident.getDiseasesStatus());
                allo.setResidentDisibailityStatus(resident.getDisabilityStatus());
                allo.setResidentHealthInsuranceCard(resident.getHealthInsurance());
                allo.setGender(resident.getGender());
                allo.setJobStatus(resident.getJobStatus());
                allo.setSupportStatus("active");
                allo.setStatus("Education");
                allo.setOwnBusiness(resident.getOwnBusiness());
                allo.setOwnHouse(resident.getOwnHouse());
                allocatedDao.recordObject(allo);
                }
                else{
                    System.out.println("ALL ABOVE 10 BUT NOT ALLOCATED");
                }
                 
               
                // HEALTH LESS 10
                }else if(hrates<=10 && edrates>10 && ecrates>10){
                    
                    if(edrates>=ecrates){
                allo.setResident(resident);
                allo.setVillage(village);
                allo.setNid(resident.getNationalID());
                allo.setPhoneNumber(resident.getPhoneNumber());
                allo.setResidentNames(resident.getFirstname()+" "+resident.getLastname());
                allo.setResidentCategory(resident.getCategory());
                allo.setResidentEducationLevel(resident.getEducationLevel());
                allo.setResidentDiseasesStatus(resident.getDiseasesStatus());
                allo.setResidentDisibailityStatus(resident.getDisabilityStatus());
                allo.setResidentHealthInsuranceCard(resident.getHealthInsurance());
                allo.setGender(resident.getGender());
                allo.setJobStatus(resident.getJobStatus());
                allo.setSupportStatus("active");
                allo.setStatus("Education");
                allo.setOwnBusiness(resident.getOwnBusiness());
                allo.setOwnHouse(resident.getOwnHouse());
                allocatedDao.recordObject(allo);
                    }else{
                allo.setResident(resident);
                allo.setVillage(village);
                allo.setNid(resident.getNationalID());
                allo.setPhoneNumber(resident.getPhoneNumber());
                allo.setResidentNames(resident.getFirstname()+" "+resident.getLastname());
                allo.setResidentCategory(resident.getCategory());
                allo.setResidentEducationLevel(resident.getEducationLevel());
                allo.setResidentDiseasesStatus(resident.getDiseasesStatus());
                allo.setResidentDisibailityStatus(resident.getDisabilityStatus());
                allo.setResidentHealthInsuranceCard(resident.getHealthInsurance());
                allo.setGender(resident.getGender());
                allo.setJobStatus(resident.getJobStatus());
                allo.setSupportStatus("active");
                allo.setStatus("Economic");
                allo.setOwnBusiness(resident.getOwnBusiness());
                allo.setOwnHouse(resident.getOwnHouse());
                allocatedDao.recordObject(allo);
                    }
                    
                }
                // EDUCATION LESS = 10
                else if(edrates<=10 && hrates>10 && ecrates>10){
                    
                 if(hrates>=ecrates){
                allo.setResident(resident);
                allo.setVillage(village);
                allo.setNid(resident.getNationalID());
                allo.setPhoneNumber(resident.getPhoneNumber());
                allo.setResidentNames(resident.getFirstname()+" "+resident.getLastname());
                allo.setResidentCategory(resident.getCategory());
                allo.setResidentEducationLevel(resident.getEducationLevel());
                allo.setResidentDiseasesStatus(resident.getDiseasesStatus());
                allo.setResidentDisibailityStatus(resident.getDisabilityStatus());
                allo.setResidentHealthInsuranceCard(resident.getHealthInsurance());
                allo.setGender(resident.getGender());
                allo.setJobStatus(resident.getJobStatus());
                allo.setSupportStatus("active");
                allo.setStatus("Health");
                allo.setOwnBusiness(resident.getOwnBusiness());
                allo.setOwnHouse(resident.getOwnHouse());
                allocatedDao.recordObject(allo);  
                    }else{
                allo.setResident(resident);
                allo.setVillage(village);
                allo.setNid(resident.getNationalID());
                allo.setPhoneNumber(resident.getPhoneNumber());
                allo.setResidentNames(resident.getFirstname()+" "+resident.getLastname());
                allo.setResidentCategory(resident.getCategory());
                allo.setResidentEducationLevel(resident.getEducationLevel());
                allo.setResidentDiseasesStatus(resident.getDiseasesStatus());
                allo.setResidentDisibailityStatus(resident.getDisabilityStatus());
                allo.setResidentHealthInsuranceCard(resident.getHealthInsurance());
                allo.setGender(resident.getGender());
                allo.setJobStatus(resident.getJobStatus());
                allo.setSupportStatus("active");
                allo.setStatus("Economic");
                allo.setOwnBusiness(resident.getOwnBusiness());
                allo.setOwnHouse(resident.getOwnHouse());
                allocatedDao.recordObject(allo);
                    }
                    
                }
                // ECONOMIC LESS = 10 
                else if(ecrates<=10 && edrates>10 && hrates>10){
                    
                    if(edrates>=hrates){
                allo.setResident(resident);
                allo.setVillage(village);
                allo.setNid(resident.getNationalID());
                allo.setPhoneNumber(resident.getPhoneNumber());
                allo.setResidentNames(resident.getFirstname()+" "+resident.getLastname());
                allo.setResidentCategory(resident.getCategory());
                allo.setResidentEducationLevel(resident.getEducationLevel());
                allo.setResidentDiseasesStatus(resident.getDiseasesStatus());
                allo.setResidentDisibailityStatus(resident.getDisabilityStatus());
                allo.setResidentHealthInsuranceCard(resident.getHealthInsurance());
                allo.setGender(resident.getGender());
                allo.setJobStatus(resident.getJobStatus());
                allo.setSupportStatus("active");
                allo.setStatus("Education");
                allo.setOwnBusiness(resident.getOwnBusiness());
                allo.setOwnHouse(resident.getOwnHouse());
                allocatedDao.recordObject(allo);  
                    }else{
                allo.setResident(resident);
                allo.setVillage(village);
                allo.setNid(resident.getNationalID());
                allo.setPhoneNumber(resident.getPhoneNumber());
                allo.setResidentNames(resident.getFirstname()+" "+resident.getLastname());
                allo.setResidentCategory(resident.getCategory());
                allo.setResidentEducationLevel(resident.getEducationLevel());
                allo.setResidentDiseasesStatus(resident.getDiseasesStatus());
                allo.setResidentDisibailityStatus(resident.getDisabilityStatus());
                allo.setResidentHealthInsuranceCard(resident.getHealthInsurance());
                allo.setGender(resident.getGender());
                allo.setJobStatus(resident.getJobStatus());
                allo.setSupportStatus("active");
                allo.setStatus("Economic");
                allo.setOwnBusiness(resident.getOwnBusiness());
                allo.setOwnHouse(resident.getOwnHouse());
                allocatedDao.recordObject(allo);
                    }
                    
                }else if(hrates>10 && edrates<=10 && ecrates<=10){
                allo.setResident(resident);
                allo.setVillage(village);
                allo.setNid(resident.getNationalID());
                allo.setPhoneNumber(resident.getPhoneNumber());
                allo.setResidentNames(resident.getFirstname()+" "+resident.getLastname());
                allo.setResidentCategory(resident.getCategory());
                allo.setResidentEducationLevel(resident.getEducationLevel());
                allo.setResidentDiseasesStatus(resident.getDiseasesStatus());
                allo.setResidentDisibailityStatus(resident.getDisabilityStatus());
                allo.setResidentHealthInsuranceCard(resident.getHealthInsurance());
                allo.setGender(resident.getGender());
                allo.setJobStatus(resident.getJobStatus());
                allo.setSupportStatus("active");
                allo.setStatus("Health");
                allo.setOwnBusiness(resident.getOwnBusiness());
                allo.setOwnHouse(resident.getOwnHouse());
                allocatedDao.recordObject(allo);
                }else if(edrates>10 && hrates<=10 && ecrates<=10){
                allo.setResident(resident);
                allo.setVillage(village);
                allo.setNid(resident.getNationalID());
                allo.setPhoneNumber(resident.getPhoneNumber());
                allo.setResidentNames(resident.getFirstname()+" "+resident.getLastname());
                allo.setResidentCategory(resident.getCategory());
                allo.setResidentEducationLevel(resident.getEducationLevel());
                allo.setResidentDiseasesStatus(resident.getDiseasesStatus());
                allo.setResidentDisibailityStatus(resident.getDisabilityStatus());
                allo.setResidentHealthInsuranceCard(resident.getHealthInsurance());
                allo.setGender(resident.getGender());
                allo.setJobStatus(resident.getJobStatus());
                allo.setSupportStatus("active");
                allo.setStatus("Education");
                allo.setOwnBusiness(resident.getOwnBusiness());
                allo.setOwnHouse(resident.getOwnHouse());
                allocatedDao.recordObject(allo);
                }else if(ecrates>10 && edrates<=10 && hrates<=10){
                 allo.setResident(resident);
                allo.setVillage(village);
                allo.setNid(resident.getNationalID());
                allo.setPhoneNumber(resident.getPhoneNumber());
                allo.setResidentNames(resident.getFirstname()+" "+resident.getLastname());
                allo.setResidentCategory(resident.getCategory());
                allo.setResidentEducationLevel(resident.getEducationLevel());
                allo.setResidentDiseasesStatus(resident.getDiseasesStatus());
                allo.setResidentDisibailityStatus(resident.getDisabilityStatus());
                allo.setResidentHealthInsuranceCard(resident.getHealthInsurance());
                allo.setGender(resident.getGender());
                allo.setJobStatus(resident.getJobStatus());
                allo.setSupportStatus("active");
                allo.setStatus("Economic");
                allo.setOwnBusiness(resident.getOwnBusiness());
                allo.setOwnHouse(resident.getOwnHouse());
                allocatedDao.recordObject(allo);
                }
                else if(hrates == edrates && edrates == ecrates){
                
                allo.setResident(resident);
                allo.setVillage(village);
                allo.setNid(resident.getNationalID());
                allo.setPhoneNumber(resident.getPhoneNumber());
                allo.setResidentNames(resident.getFirstname()+" "+resident.getLastname());
                allo.setResidentCategory(resident.getCategory());
                allo.setResidentEducationLevel(resident.getEducationLevel());
                allo.setResidentDiseasesStatus(resident.getDiseasesStatus());
                allo.setResidentDisibailityStatus(resident.getDisabilityStatus());
                allo.setResidentHealthInsuranceCard(resident.getHealthInsurance());
                allo.setGender(resident.getGender());
                allo.setJobStatus(resident.getJobStatus());
                allo.setSupportStatus("active");
                allo.setStatus("Economic");
                allo.setOwnBusiness(resident.getOwnBusiness());
                allo.setOwnHouse(resident.getOwnHouse());
                allocatedDao.recordObject(allo);
                
                allo.setResident(resident);
                allo.setVillage(village);
                allo.setNid(resident.getNationalID());
                allo.setPhoneNumber(resident.getPhoneNumber());
                allo.setResidentNames(resident.getFirstname()+" "+resident.getLastname());
                allo.setResidentCategory(resident.getCategory());
                allo.setResidentEducationLevel(resident.getEducationLevel());
                allo.setResidentDiseasesStatus(resident.getDiseasesStatus());
                allo.setResidentDisibailityStatus(resident.getDisabilityStatus());
                allo.setResidentHealthInsuranceCard(resident.getHealthInsurance());
                allo.setGender(resident.getGender());
                allo.setJobStatus(resident.getJobStatus());
                allo.setSupportStatus("active");
                allo.setStatus("Education");
                allo.setOwnBusiness(resident.getOwnBusiness());
                allo.setOwnHouse(resident.getOwnHouse());
                allocatedDao.recordObject(allo);
                
                allo.setResident(resident);
                allo.setVillage(village);
                allo.setNid(resident.getNationalID());
                allo.setPhoneNumber(resident.getPhoneNumber());
                allo.setResidentNames(resident.getFirstname()+" "+resident.getLastname());
                allo.setResidentCategory(resident.getCategory());
                allo.setResidentEducationLevel(resident.getEducationLevel());
                allo.setResidentDiseasesStatus(resident.getDiseasesStatus());
                allo.setResidentDisibailityStatus(resident.getDisabilityStatus());
                allo.setResidentHealthInsuranceCard(resident.getHealthInsurance());
                allo.setGender(resident.getGender());
                allo.setJobStatus(resident.getJobStatus());
                allo.setSupportStatus("active");
                allo.setStatus("Health");
                allo.setOwnBusiness(resident.getOwnBusiness());
                allo.setOwnHouse(resident.getOwnHouse());
                allocatedDao.recordObject(allo);
                
                }else{
                    System.out.println("ALLOCATING NOT CLASSIFIED");
                }
            
            }
           
           return "update-resident.xhtml";
        } catch (Exception e) {
            System.out.println("Erorr : "+e);
            return "update-resident.xhtml";
        }
    }
    
    public String sendNumeratorMessage(){
    Date today = new Date();
     SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
    Date date = new Date();  
//    String dateT =""+date.getDayOfMonth()+"-"+date.getMonth()+"-"+date.getYear();
        try {
            User usr = userDao.getUsername(user.getUsername());
            village = usr.getVillage();
            resident = usr.getResident();
            message.setMessagerType("Resident");
            message.setMessageStatus("unread");
            message.setSentDate(date);
            message.setVillage(village);
            message.setResident(resident);
            message.setSenderName(resident.getFirstname()+" "+resident.getLastname());
            message.setSenderPhone(resident.getPhoneNumber());
            message.setSenderCode(resident.getCode());
            message.setVillageName(village.getName());
            messageDao.recordObject(message);
            return "inbox.xhtml";
        } catch (Exception e) {
            System.out.println("Sent message errorrr "+e);
            return "inbox.xhtml";
        }
    }
    
    public String numeratorSendNotification(){
         Date today = new Date();
     SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
    Date date = new Date(); 
        try {
             User usr = userDao.getUsername(user.getUsername());
             resident = residentDao.getResidentByCode(senderCode);
             village = usr.getVillage();
             numerator = usr.getNumerator();
             notification.setPostedDate(date);
             notification.setStatus("sent");
             notification.setVillage(village);
             notification.setNumerator(numerator);
             notificationDao.recordObject(notification);
             successMessage("Nofication is Sent to all Residents");
            return "notify.xhtml";
        } catch (Exception e) {
            System.out.println("Error compose "+e);
            errorMessage("Failed to send notification");
            return "notify.xhtml";
        }
    }
    
    public String numeratorSendMessage(){
         Date today = new Date();
     SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
    Date date = new Date(); 
        try {
             User usr = userDao.getUsername(user.getUsername());
             resident = residentDao.getResidentByCode(senderCode);
             village = usr.getVillage();
             numerator = usr.getNumerator();
             message.setMessageStatus("response");
             message.setMessagerType("Numerator");
             message.setSenderName(numerator.getFirstname()+" "+numerator.getLastname());
             message.setSenderPhone(numerator.getPhoneNumber());
             message.setSentDate(date);
             message.setVillage(village);
             message.setVillageName(village.getName());
             message.setSenderCode(senderCode);
             message.setResident(resident);
             messageDao.recordObject(message);
             successMessage("Message was sent Successfuly");
             String body = message.getMessage();
             sendsms.sendSMS(resident.getPhoneNumber(), from_number, body);
            
            return "compose.xhtml";
        } catch (Exception e) {
            System.out.println("Error compose "+e);
            errorMessage("Failed To send message");
            return "compose.xhtml";
        }
    }
    public String commaConvert(Integer n){
         DecimalFormat decimalFormat = new DecimalFormat("#.##");
         decimalFormat.setGroupingUsed(true);
         decimalFormat.setGroupingSize(3);    
         String k = decimalFormat.format(n);
         return k;
    }
     public String totalClaimedBudget(){
            DecimalFormat decimalFormat = new DecimalFormat("#.##");
            decimalFormat.setGroupingUsed(true);
            decimalFormat.setGroupingSize(3);
            
             User usr = userDao.getUsername(user.getUsername());
             village = usr.getVillage();
             
             Integer id = village.getId();
             String k ="";
             String kk="0";
             try {
                BigDecimal fq = supportDataDao.totalHealthClaimedBudget(id);
                BigDecimal fq1 = supportDataDao.totalEducationClaimedBudget(id);
                BigDecimal fq2 = supportDataDao.totalEconomicClaimedBudget(id);
                Integer d = fq.intValue();
                Integer d1 = fq1.intValue();
                Integer d2 = fq2.intValue();
                Integer tot = d + d1 + d2;
                k = decimalFormat.format(tot);
                return k;
            } catch (Exception e) {
                 return kk;
            }
        }
    
    
    public String markAsSeenMessage(Integer id){
        try {
           User usr = userDao.getUsername(user.getUsername());
           message = messageDao.findOne(Message.class, id);
           message.setMessageStatus("read");
           messageDao.updateObject(message);
           return "mailbox.xhtml";
        } catch (Exception e) {
            System.out.println("Error mark message as seen error "+e);
            return "mailbox.xhtml";
        }
    }
    
    
     public String updateResident(String code1){
          Integer disab=0;
        Integer diseas=0;
        Integer healthIns=0;
        Integer totHr=0;
        
        // Education
        Integer educ =0;
        Integer totEdr=0;
        
        // Economic
        Integer ecoInc =0;
        Integer ecoJob =0;
        Integer totEco=0;
        
        //GeneralR
        Integer mpp=0;
        Integer catt=0;
        Integer chn=0;
        Integer totGR=0;

         try {
             
      
       User usr = userDao.getUsername(user.getUsername());
       village = usr.getVillage();
       resident = residentDao.getResidentByCode(code);
       Allocated alloH = allocatedDao.getAllocatedByResidentIdByHealth(resident.getId());
       Allocated alloEc = allocatedDao.getAllocatedByResidentIdByEconomic(resident.getId());
       Allocated alloEd = allocatedDao.getAllocatedByResidentIdByEducation(resident.getId());
       Allocated alloRic = allocatedDao.getAllocatedByResidentIdByRich(resident.getId());
       if(alloH!=null){
           System.out.println("2");
       allocatedDao.deleteObject(alloH);
           System.out.println("22");
       resident.setVillage(village);
       resident.setFirstname(fn);
       resident.setLastname(ln);
       resident.setDob(db);
       resident.setCategory(cat);
       resident.setMartialStatus(ms);
       resident.setPhoneNumber(ph);
       resident.setJobStatus(jb);
       resident.setNationalID(nd);
//       resident.setMealsPerDayRange(mp);
       resident.setHealthInsurance(hi);
       resident.setEducationLevel(ed);
       resident.setIncomeRange(icr);
       resident.setGender(gndr);
      // resident.setChildrenNo(cn);
       resident.setDisabilityStatus(dis);
       resident.setDiseasesStatus(dise);
       resident.setResidentStatus("pending");
       residentDao.updateObject(resident);
         }
        
       
       if(alloEd!=null){
           System.out.println("NULL ALLOCATED");
       allocatedDao.deleteObject(alloEd);
       resident.setVillage(village);
       resident.setFirstname(fn);
       resident.setLastname(ln);
       resident.setDob(db);
       resident.setCategory(cat);
       resident.setMartialStatus(ms);
       resident.setPhoneNumber(ph);
       resident.setJobStatus(jb);
       resident.setNationalID(nd);
//       resident.setMealsPerDayRange(mp);
       resident.setHealthInsurance(hi);
       resident.setEducationLevel(ed);
       resident.setIncomeRange(icr);
       resident.setGender(gndr);
      // resident.setChildrenNo(cn);
       resident.setDisabilityStatus(dis);
       resident.setDiseasesStatus(dise);
       resident.setResidentStatus("pending");
       residentDao.updateObject(resident);
           }
        if(alloEc!=null ){
           System.out.println("1");
       allocatedDao.deleteObject(alloEc);
           System.out.println("11");
       resident.setVillage(village);
       resident.setFirstname(fn);
       resident.setLastname(ln);
       resident.setDob(db);
       resident.setCategory(cat);
       resident.setMartialStatus(ms);
       resident.setPhoneNumber(ph);
       resident.setJobStatus(jb);
       resident.setNationalID(nd);
//       resident.setMealsPerDayRange(mp);
       resident.setHealthInsurance(hi);
       resident.setEducationLevel(ed);
       resident.setIncomeRange(icr);
       resident.setGender(gndr);
      // resident.setChildrenNo(cn);
       resident.setDisabilityStatus(dis);
       resident.setDiseasesStatus(dise);
       resident.setResidentStatus("pending");
       residentDao.updateObject(resident);
           }
        if(alloRic!=null){
        System.out.println("HAAAAAAAA NULL");
       resident.setVillage(village);
       resident.setFirstname(fn);
       resident.setLastname(ln);
       resident.setDob(db);
       resident.setCategory(cat);
       resident.setMartialStatus(ms);
       resident.setPhoneNumber(ph);
       resident.setJobStatus(jb);
       resident.setNationalID(nd);
//       resident.setMealsPerDayRange(mp);
       resident.setHealthInsurance(hi);
       resident.setEducationLevel(ed);
       resident.setIncomeRange(icr);
       resident.setGender(gndr);
      // resident.setChildrenNo(cn);
       resident.setDisabilityStatus(dis);
       resident.setDiseasesStatus(dise);
       resident.setResidentStatus("pending");
       residentDao.updateObject(resident);
           }
        
       if(alloH==null && alloEd == null && alloEc ==null) {
        resident.setVillage(village);
       resident.setFirstname(fn);
       resident.setLastname(ln);
       resident.setDob(db);
       resident.setCategory(cat);
       resident.setMartialStatus(ms);
       resident.setPhoneNumber(ph);
       resident.setJobStatus(jb);
       resident.setNationalID(nd);
//       resident.setMealsPerDayRange(mp);
       resident.setHealthInsurance(hi);
       resident.setEducationLevel(ed);
       resident.setIncomeRange(icr);
       resident.setGender(gndr);
      // resident.setChildrenNo(cn);
       resident.setDisabilityStatus(dis);
       resident.setDiseasesStatus(dise);
       resident.setResidentStatus("pending");
       residentDao.updateObject(resident);
       }  
      
       
        if(resident.getDisabilityStatus().equalsIgnoreCase("No")){
                disab=0;
            }
            if(resident.getDisabilityStatus().equalsIgnoreCase("Yes")){
                disab=2;
            }
             if(resident.getDiseasesStatus().equalsIgnoreCase("No")){
                diseas=0;
            }
            if(resident.getDiseasesStatus().equalsIgnoreCase("Yes")){
                diseas=2;
            }
            if(resident.getHealthInsurance().equalsIgnoreCase("Other")){
                healthIns=1;
            }
            if(resident.getHealthInsurance().equalsIgnoreCase("Rama")){
                healthIns=2;
            }
            if(resident.getHealthInsurance().equalsIgnoreCase("Mituelle")){
                healthIns=3;
            }
            if(resident.getHealthInsurance().equalsIgnoreCase("None")){
                healthIns=4;
            }
            
            totHr = disab + diseas + healthIns;
            resident.setHealthRates(totHr);
            residentDao.updateObject(resident);
            
            if(resident.getEducationLevel().equalsIgnoreCase("None")){
                educ=4;
            }
            if(resident.getEducationLevel().equalsIgnoreCase("Primary")){
                educ=3;
            }
            if(resident.getEducationLevel().equalsIgnoreCase("Secondary")){
                educ=2;
            }
            if(resident.getEducationLevel().equalsIgnoreCase("University")){
                educ=1;
            }
            
            totEdr = educ + 4;
            resident.setEducationRates(totEdr);
            residentDao.updateObject(resident);
            
            if(resident.getJobStatus().equalsIgnoreCase("No")){
                ecoJob=2;
            }
            if(resident.getJobStatus().equalsIgnoreCase("Yes")){
                ecoJob=0;
            }
            if(resident.getIncomeRange().equalsIgnoreCase("0 - 50,000")){
                ecoInc=4;
            }
            if(resident.getIncomeRange().equalsIgnoreCase("50,000 - 150,000")){
                ecoInc=3;
            }
            if(resident.getIncomeRange().equalsIgnoreCase("150,000 - 350,000")){
                ecoInc=2;
            }
            if(resident.getIncomeRange().equalsIgnoreCase("350,000 - Above")){
                ecoInc=1;
            }
            
            
            totEco = ecoInc + ecoJob + 2;
            resident.setEconomicRates(totEco);
            residentDao.updateObject(resident);
            
//            if(resident.getChildrenNo().equalsIgnoreCase("None")){
//                chn = 1;
//            }
//            if(resident.getChildrenNo().equalsIgnoreCase("1")){
//                chn = 2;
//            }
//            if(resident.getChildrenNo().equalsIgnoreCase("2")){
//                chn = 3;
//            }
//            if(resident.getChildrenNo().equalsIgnoreCase("Above")){
//                chn = 4;
//            }
//            if(resident.getMealsPerDayRange().equalsIgnoreCase("1")){
//                mpp=4;
//                
//            }
//             if(resident.getMealsPerDayRange().equalsIgnoreCase("2")){
//                mpp=3;
//                
//            }
//              if(resident.getMealsPerDayRange().equalsIgnoreCase("3")){
//                mpp=2;
//                
//            }
//               if(resident.getMealsPerDayRange().equalsIgnoreCase("Above")){
//                mpp=1;
//                
//            }
            
            if(resident.getCategory().equalsIgnoreCase("Category I")){
                catt=4;
                
            }
            if(resident.getCategory().equalsIgnoreCase("Category II")){
                catt=3;
                
            }
            if(resident.getCategory().equalsIgnoreCase("Category III")){
                catt=2;
                
            }
             if(resident.getCategory().equalsIgnoreCase("Category IV")){
                catt=1;
                
            }
            
            totGR = catt + mpp + chn;           
            resident.setGeneralRates(totGR);
            residentDao.updateObject(resident);
       
       successMessage(resident.getFirstname()+" "+resident.getLastname()+" Updated successfuly");
         System.out.println("YYYESSS");
         return "update-resident.xhtml";
         } catch (Exception e) {
             errorMessage("Failed to update resident");
             System.out.println("Error update resid "+e);
             return "update-resident.xhtml";
         }
  
    }
    
    public String updateNumerator(){
        try {
        User usr = userDao.getUsername(user.getUsername());
        usr.setUsername(usernam);
        usr.setPassword(createAnonymous(passwrd));
        userDao.updateObject(usr);
       return "numerator-profile.xhtml"; 
        } catch (Exception e) {
            System.out.println("Error upd prof "+e);
            return "numerator-profile.xhtml";
        }
       
        
    }
    
    public String cancelSupport(Integer id){
        try {
        User usr = userDao.getUsername(user.getUsername());
        allocated = allocatedDao.getAllocatedById(id);
        allocated.setSupportStatus("Canceled");
        allocatedDao.updateObject(allocated);
        successMessage(allocated.getResidentNames()+" Is Cancelled from "+allocated.getStatus());
        return "canceled-residents.xhtml";  
        } catch (Exception e) {
            System.out.println("Error alloc "+e);
            errorMessage("Failed TO Cancel support");
            return "canceled-residents.xhtml"; 
        }
        
       
    }
    
    public String activateSupport(Integer id){
        try {
        User usr = userDao.getUsername(user.getUsername());
        allocated = allocatedDao.getAllocatedById(id);
        allocated.setSupportStatus("active");
        allocatedDao.updateObject(allocated);
        return "canceled-residents.xhtml";  
        } catch (Exception e) {
            System.out.println("Error alloc "+e);
            return "canceled-residents.xhtml"; 
        }
        
       
    }
    
    public List<Resident> getAllPendingResidentsByVillage(){
        try {
        User usr = userDao.getUsername(user.getUsername());
        Integer id = usr.getVillage().getId();
        residents = residentDao.findPendingResidentsByVillage(id);
        return residents;
        } catch (Exception e) {
            System.out.println("Pending residents "+e);
            return residents;
            
        }
        
    }
    
    public List<Message> getAllResidentUnreadMessagesByVillage(){
        try {
        User usr = userDao.getUsername(user.getUsername());
        resident = usr.getResident();
        messages = messageDao.findResidentUnreadMessagesByVillage(resident.getId());
        return messages;
        } catch (Exception e) {
            System.out.println("Unread messages error "+e);
            return messages;
            
        }
        
    }
    public String residentSeenMessageById(Integer id){
        try {
         User usr = userDao.getUsername(user.getUsername());
         message = messageDao.findOne(Message.class, id);
         message.setMessageStatus("seen-resident");
         messageDao.updateObject(message);
         return "inbox.xhtml";
        } catch (Exception e) {
            System.out.println("Error seen resident "+e);
            return "inbox.xhtml";
        }
  
    }
    
    public Integer getAllResidentUnreadMessagesNumber(){
        try {
        User usr = userDao.getUsername(user.getUsername());
        resident = usr.getResident();
        messages = messageDao.findResidentUnreadMessagesByVillage(resident.getId());
        return messages.size();
        } catch (Exception e) {
            System.out.println("Unread messages error "+e);
            return messages.size();
            
        }
        
    }
    
    public List<Message> getAllUnreadMessagesByVillage(){
        try {
        User usr = userDao.getUsername(user.getUsername());
        village = usr.getVillage();
        messages = messageDao.findUnreadMessagesByVillage(village.getId());
        return messages;
        } catch (Exception e) {
            System.out.println("Unread messages error "+e);
            return messages;
            
        }
        
    }
    
    
    
     public List<Message> getAllReadMessagesByVillage(){
        try {
        User usr = userDao.getUsername(user.getUsername());
        village = usr.getVillage();
        messages = messageDao.findReadMessagesByVillage(village.getId());
        return messages;
        } catch (Exception e) {
            System.out.println("read messages error "+e);
            return messages;
            
        }
        
    }
     
     public Integer allSenNotifications(){
         try {
              User usr = userDao.getUsername(user.getUsername());
              village = usr.getVillage();
             notifications = notificationDao.findAllNotificationByVillage(village.getId());
             return notifications.size();
         } catch (Exception e) {
             System.out.println("Error size notif "+e);
             return notifications.size();
         }
     }
     
     public String seenNotification(Integer id){
         try {
           notification = notificationDao.seenNotification(id);
           notification.setStatus("seen");
           notificationDao.updateObject(notification);
           return "notification.xhtml";
         } catch (Exception e) {
             System.out.println("Error mark as seen notifc "+e);
             return "notification.xhtml";
         }
     }
     
    public List<Notification> getUnreadNotifications(){
        try {
          User usr = userDao.getUsername(user.getUsername());
          village = usr.getVillage();
          notifications = notificationDao.findUnreadNotificationByVillage(village.getId());
          return notifications;
        } catch (Exception e) {
            System.out.println("Unread Notif "+e);
            return notifications;
        }
    }
    
    public List<SupportData> getAllClaimedSupportByResident(){
        try {
          User usr = userDao.getUsername(user.getUsername());
          Integer id = resident.getId();
          supportDatas = supportDataDao.findAllClaimedSuppors(id);
          return supportDatas;
        } catch (Exception e) {
            System.out.println("Unread Notif "+e);
            return supportDatas;
        }
    } 
    
    public Integer getAllUnreadMessagesNumber(){
        try {
        User usr = userDao.getUsername(user.getUsername());
        village = usr.getVillage();
        messages = messageDao.findUnreadMessagesByVillage(village.getId());
        return messages.size();
        } catch (Exception e) {
            System.out.println("Unread messages error "+e);
            return messages.size();
            
        }
        
    }
    
    public Integer getAllReadMessagesNumber(){
        try {
        User usr = userDao.getUsername(user.getUsername());
        village = usr.getVillage();
        messages = messageDao.findReadMessagesByVillage(village.getId());
        return messages.size();
        } catch (Exception e) {
            System.out.println("Unread messages error "+e);
            return messages.size();
            
        }
        
    }
    
    public List<Allocated> getAllSupportByResident(){
        try {
          User usr = userDao.getUsername(user.getUsername());
          resident = usr.getResident();
          allocates = allocatedDao.findSupportByUser(resident.getId());
          return allocates;
        } catch (Exception e) {
            System.out.println("Error support "+e);
            return allocates;
        }
    }
    
    
    
    public List<Resident> getAllCanceledResidentsByVillage(){
        try {
        User usr = userDao.getUsername(user.getUsername());
        Integer id = usr.getVillage().getId();
        residents = residentDao.findCanceledResidentsByVillage(id);
        return residents;
        } catch (Exception e) {
            System.out.println("Pending residents "+e);
            return residents;
            
        }
        
    }
    
    public List<Resident> getAllRichResidentsByVillage(){
        try {
        User usr = userDao.getUsername(user.getUsername());
        Integer id = usr.getVillage().getId();
        residents = residentDao.findCanceledResidentsByVillage(id);
        return residents;
        } catch (Exception e) {
            System.out.println("Pending residents "+e);
            return residents;
            
        }
        
    }
    
     public List<Resident> getAllResidentsByVillage(){
        try {
        User usr = userDao.getUsername(user.getUsername());
        Integer id = usr.getVillage().getId();
        residents = residentDao.findResidentsByVillage(id);
        return residents;
        } catch (Exception e) {
            System.out.println("Pending residents "+e);
            return residents;
            
        }
        
    }
     
     public Integer getSingleResidentsByVillage(){
        try {
        User usr = userDao.getUsername(user.getUsername());
        Integer id = usr.getVillage().getId();
        residents = residentDao.findSingleResidentsByVillage(id);
        return residents.size();
        } catch (Exception e) {
            System.out.println("Pending residents "+e);
            return residents.size();
            
        }
        
      }
     
     public Integer getMarriedResidentsByVillage(){
        try {
        User usr = userDao.getUsername(user.getUsername());
        Integer id = usr.getVillage().getId();
        residents = residentDao.findMarriedResidentsByVillage(id);
        return residents.size();
        } catch (Exception e) {
            System.out.println("Pending residents "+e);
            return residents.size();
            
        }
        
    }
     
     public Integer getJoblessResidentsByVillage(){
        try {
        User usr = userDao.getUsername(user.getUsername());
        Integer id = usr.getVillage().getId();
        residents = residentDao.findJoblessResidentsByVillage(id);
        return residents.size();
        } catch (Exception e) {
            System.out.println("Pending residents "+e);
            return residents.size();
            
        }
        
    }
     
     public Integer getHealthInsuranceResidentsByVillage(){
        try {
        User usr = userDao.getUsername(user.getUsername());
        Integer id = usr.getVillage().getId();
        residents = residentDao.findHealthInsurancesResidentsByVillage(id);
        return residents.size();
        } catch (Exception e) {
            System.out.println("Pending residents "+e);
            return residents.size();
            
        }
        
    }
     
     public Integer getCat1ResidentsByVillage(){
        try {
        User usr = userDao.getUsername(user.getUsername());
        Integer id = usr.getVillage().getId();
        residents = residentDao.findCat1ResidentsByVillage(id);
        return residents.size();
        } catch (Exception e) {
            System.out.println("Pending residents "+e);
            return residents.size();
            
        }
        
    }
     
     public Integer getCat1Per100ResidentsByVillage(){
        try {
        User usr = userDao.getUsername(user.getUsername());
        Integer id = usr.getVillage().getId();
        residents = residentDao.findCat1ResidentsByVillage(id);
        Integer cat1 = residents.size();
        List<Resident> resis = residentDao.findResidentsByVillage(id);
        Integer tots = resis.size();
        Integer perC = cat1 * 100 / tots;
        return perC;
        } catch (Exception e) {
            System.out.println("Pending residents "+e);
            return residents.size();
            
        }
        
    }
     
     public Integer getCat2Per100ResidentsByVillage(){
        try {
        User usr = userDao.getUsername(user.getUsername());
        Integer id = usr.getVillage().getId();
        residents = residentDao.findCat2ResidentsByVillage(id);
        Integer cat2 = residents.size();
        List<Resident> resis = residentDao.findResidentsByVillage(id);
        Integer tots = resis.size();
        Integer perC = cat2 * 100 / tots;
        return perC;
        } catch (Exception e) {
            System.out.println("Pending residents "+e);
            return residents.size();
            
        }
        
    }
      public Integer getCat3ResidentsByVillage(){
        try {
        User usr = userDao.getUsername(user.getUsername());
        Integer id = usr.getVillage().getId();
        residents = residentDao.findCat3ResidentsByVillage(id);
        return residents.size();
        } catch (Exception e) {
            System.out.println("Pending residents "+e);
            return residents.size();
            
        }
        
    }
      
          public String healthRemoveSupportQty(String ky,Integer resid, Integer vId,Integer sId){
        try {
            SupportData suppData = supportDataDao.getSupportDataByKey(ky);
            resident = residentDao.findOne(Resident.class, resid);
            String phn = resident.getPhoneNumber();
            String nms = resident.getFirstname()+" "+resident.getLastname();
            village = villageDao.findOne(Village.class, vId);
            support = supportDao.findOne(Support.class,sId);
            Integer qc = suppData.getQuantity();
            String sc = suppData.getSupportName();
            
            Integer q = support.getQuantity();
            Integer qp = support.getQtyPerFamily();
            Integer vp = support.getValuePerFamily();
            Integer sp = support.getSupportValue();
            if( q>qp){
                System.out.println("HERE QTY"+qp);
            Integer nq = q - qp;
            support.setQuantity(nq);
            Integer rsp = sp - vp;
            support.setFixedSupportValue(rsp);
            supportDao.updateObject(support);
            System.out.println("YEAHAYE");
            String coded = resident.getId()+"-"+support.getId()+"-2021";
//            String body = "Hello "+nms
//                    +" From "+village.getName()+" You claimed "+qc+" on "+sc+" support "+"\n Please bring this code: "+coded+" to your numerator and he/she will help you get your suport.";
            sendsms.sendSMS(phn, from_number, "sda");
                System.out.println("SEND SMSSSS");
            }else if(q==qp){
                Integer nq = q - qp;
                support.setQuantity(nq);
                Integer rsp = sp - vp;
                support.setFixedSupportValue(rsp);
                support.setStatus("Finished");
                supportDao.updateObject(support);
                String code = resident.getId()+"-"+support.getId()+"-2021";
               
            sendsms.sendSMS(phn, from_number, "Hello "+resident.getFirstname()+" "+resident.getLastname()
                    +" From "+village.getName()+" You claimed "+qc+" on "+sc+" support "+"\n Please bring this code: "+code+" to your numerator and he/she will help you get your suport.");
            
            }else{
                System.out.println("Quantity in support are less than needed");
                warningMessage("Quantity in support are less than needed");
            }
            
            
            return "health-support";
        } catch (Exception e) {
            System.out.println("Error health Remov Qty "+e);
            return "health-support";
        }
    }
    public String educationRemoveSupportQty(String ky,Integer resid, Integer vId,Integer sId){
        try {
            SupportData suppData = supportDataDao.getSupportDataByKey(ky);
            resident = residentDao.findOne(Resident.class, resid);
            String phn = resident.getPhoneNumber();
            String nms = resident.getFirstname()+" "+resident.getLastname();
            village = villageDao.findOne(Village.class, vId);
            support = supportDao.findOne(Support.class,sId);
            Integer qc = suppData.getQuantity();
            String sc = suppData.getSupportName();
            
            Integer q = support.getQuantity();
            Integer qp = support.getQtyPerFamily();
            Integer vp = support.getValuePerFamily();
            Integer sp = support.getSupportValue();
            if( q>qp){
                System.out.println("HERE QTY"+qp);
            Integer nq = q - qp;
            support.setQuantity(nq);
            Integer rsp = sp - vp;
            support.setFixedSupportValue(rsp);
            supportDao.updateObject(support);
            System.out.println("YEAHAYE");
            String coded = resident.getId()+"-"+support.getId()+"-2021";
//            String body = "Hello "+nms
//                    +" From "+village.getName()+" You claimed "+qc+" on "+sc+" support "+"\n Please bring this code: "+coded+" to your numerator and he/she will help you get your suport.";
            sendsms.sendSMS(phn, from_number, "sda");
                System.out.println("SEND SMSSSS");
            }else if(q==qp){
                Integer nq = q - qp;
                support.setQuantity(nq);
                Integer rsp = sp - vp;
                support.setFixedSupportValue(rsp);
                support.setStatus("Finished");
                supportDao.updateObject(support);
                String code = resident.getId()+"-"+support.getId()+"-2021";
            sendsms.sendSMS(phn, from_number, "Hello "+resident.getFirstname()+" "+resident.getLastname()
                    +" From "+village.getName()+" You claimed "+qc+" on "+sc+" support "+"\n Please bring this code: "+code+" to your numerator and he/she will help you get your suport.");
            
            }else{
                System.out.println("Quantity in support are less than needed");
                warningMessage("Quantity in support are less than needed");
            }
            
            
            return "education-support";
        } catch (Exception e) {
            System.out.println("Error health Remov Qty "+e);
            return "education-support";
        }
    }
    public String economicRemoveSupportQty(String ky,Integer resid, Integer vId,Integer sId){
        try {
            SupportData suppData = supportDataDao.getSupportDataByKey(ky);
            resident = residentDao.findOne(Resident.class, resid);
            String phn = resident.getPhoneNumber();
            String nms = resident.getFirstname()+" "+resident.getLastname();
            village = villageDao.findOne(Village.class, vId);
            support = supportDao.findOne(Support.class,sId);
            Integer qc = suppData.getQuantity();
            String sc = suppData.getSupportName();
            
            Integer q = support.getQuantity();
            Integer qp = support.getQtyPerFamily();
            Integer vp = support.getValuePerFamily();
            Integer sp = support.getSupportValue();
            if( q>qp){
                System.out.println("HERE QTY"+qp);
            Integer nq = q - qp;
            support.setQuantity(nq);
            Integer rsp = sp - vp;
            support.setFixedSupportValue(rsp);
            supportDao.updateObject(support);
            System.out.println("YEAHAYE");
            String coded = resident.getId()+"-"+support.getId()+"-2021";
//            String body = "Hello "+nms
//                    +" From "+village.getName()+" You claimed "+qc+" on "+sc+" support "+"\n Please bring this code: "+coded+" to your numerator and he/she will help you get your suport.";
            sendsms.sendSMS(phn, from_number, "sda");
                System.out.println("SEND SMSSSS");
            }else if(q==qp){
                Integer nq = q - qp;
                support.setQuantity(nq);
                Integer rsp = sp - vp;
                support.setFixedSupportValue(rsp);
                support.setStatus("Finished");
                supportDao.updateObject(support);
                String code = resident.getId()+"-"+support.getId()+"-2021";
               
            sendsms.sendSMS(phn, from_number, "Hello "+resident.getFirstname()+" "+resident.getLastname()
                    +" From "+village.getName()+" You claimed "+qc+" on "+sc+" support "+"\n Please bring this code: "+code+" to your numerator and he/she will help you get your suport.");
            
            }else{
                System.out.println("Quantity in support are less than needed");
                warningMessage("Quantity in support are less than needed");
            }
            
            
            return "economic-support";
        } catch (Exception e) {
            System.out.println("Error health Remov Qty "+e);
            return "economic-support";
        }
    }
      
     public Integer getCat4ResidentsByVillage(){
        try {
        User usr = userDao.getUsername(user.getUsername());
        Integer id = usr.getVillage().getId();
        residents = residentDao.findCat4ResidentsByVillage(id);
        return residents.size();
        } catch (Exception e) {
            System.out.println("Pending residents "+e);
            return residents.size();
            
        }
        
    }  
     
     public Integer getCat3Per100ResidentsByVillage(){
        try {
        User usr = userDao.getUsername(user.getUsername());
        Integer id = usr.getVillage().getId();
        residents = residentDao.findCat3ResidentsByVillage(id);
        Integer cat3 = residents.size();
        List<Resident> resis = residentDao.findResidentsByVillage(id);
        Integer tots = resis.size();
        Integer perC = cat3 * 100 / tots;
        return perC;
        } catch (Exception e) {
            System.out.println("Pending residents "+e);
            return residents.size();
            
        }
        
    }
     
     public Integer getCat4Per100ResidentsByVillage(){
        try {
        User usr = userDao.getUsername(user.getUsername());
        Integer id = usr.getVillage().getId();
        residents = residentDao.findCat4ResidentsByVillage(id);
        Integer cat4 = residents.size();
        List<Resident> resis = residentDao.findResidentsByVillage(id);
        Integer tots = resis.size();
        Integer perC = cat4 * 100 / tots;
        return perC;
        } catch (Exception e) {
            System.out.println("Pending residents "+e);
            return residents.size();
            
        }
        
    }
     
 public List<Support> getAvailableEducationSupport(){
        try {
         User usr = userDao.getUsername(user.getUsername());
         resident = usr.getResident(); 
         village = usr.getVillage();
         Integer id = village.getId();
         allocated = allocatedDao.getAllocatedByResidentIdByEducation(resident.getId());
         
         if(allocated!= null && allocated.getSupportStatus().equalsIgnoreCase("active")){
         return  supports = supportDao.findEducationSupportAvailableByVillage(id);  
         }
         else{
             System.out.println("NOOO");
             return null;
         }
           
        } catch (Exception e) {
            System.out.println("AAAAA SUPPORTS "+e);
            return supports;
        }
    }
  public Integer getCat2ResidentsByVillage(){
        try {
        User usr = userDao.getUsername(user.getUsername());
        Integer id = usr.getVillage().getId();
        residents = residentDao.findCat2ResidentsByVillage(id);
        return residents.size();
        } catch (Exception e) {
            System.out.println("Pending residents "+e);
            return residents.size();
            
        }
        
    }
  
    
    public List<Support> getAvailableEducationSupportNumerator(){
        try {
         User usr = userDao.getUsername(user.getUsername());
         resident = usr.getResident();
         
         village = usr.getVillage();
         Integer id = village.getId();
         
   
         return  supports = supportDao.findEducationSupportAvailableByVillage(id);  
        
           
        } catch (Exception e) {
            System.out.println("AAAAA SUPPORTS "+e);
            return supports;
        }
    }
    
    public List<Support> getAvailableEducationSupportAuthority(){
        try {
         User usr = userDao.getUsername(user.getUsername());
         resident = usr.getResident();
         
         village = usr.getVillage();
         Integer id = village.getId();
         
   
         return  supports = supportDao.findEducationSupportAvailableByVillageAuthority(id);  
        
           
        } catch (Exception e) {
            System.out.println("AAAAA SUPPORTS "+e);
            return supports;
        }
    }
    
     public List<Support> getAvailableHealthSupport(){
        try {
         User usr = userDao.getUsername(user.getUsername());
         village = usr.getVillage();
         Integer id = village.getId();
         
         allocated = allocatedDao.getAllocatedByResidentIdByHealth(resident.getId());
          
         if(allocated!=null && allocated.getSupportStatus().equalsIgnoreCase("active")){
           return supports = supportDao.findHealthSupportAvailableByVillage(id);   
         } else {
             return null;
         }
         
        } catch (Exception e) {
            System.out.println("AAAAA SUPPORTS "+e);
            return supports;
        }
    }
     
      public List<Support> getAvailableHealthSupportNumerator(){
        try {
         User usr = userDao.getUsername(user.getUsername());
         village = usr.getVillage();
         Integer id = village.getId();
       
           return supports = supportDao.findHealthSupportAvailableByVillage(id);   
        } catch (Exception e) {
            return supports;
        }
    }
     
      public List<Support> getAvailableHealthSupportAuthority(){
        try {
         User usr = userDao.getUsername(user.getUsername());
         village = usr.getVillage();
         Integer id = village.getId();
         
      
           return supports = supportDao.findHealthSupportAvailableByVillageAuthority(id);   
         
         
        } catch (Exception e) {
            System.out.println("AAAAA SUPPORTS "+e);
            return supports;
        }
    }
     
     
     public List<Allocated> getHealthAllocated(){
         try {
          User usr = userDao.getUsername(user.getUsername());
          villageId = usr.getVillage().getId();
          allocates = allocatedDao.findHealthAllocated(villageId);
          return allocates;
         } catch (Exception e) {
             System.out.println("Health allocs "+e);
             return allocates;
         }
     }
     
     public List<SupportData> getHealthSupportDataClaimed(){
         try {
          User usr = userDao.getUsername(user.getUsername());
          villageId = usr.getVillage().getId();
          supportDatas = supportDataDao.findHealthClaimed(villageId);
          return supportDatas;
         } catch (Exception e) {
             System.out.println("Health allocs "+e);
             return supportDatas;
         }
     }
     
     public List<SupportData> getEducationSupportDataClaimed(){
         try {
          User usr = userDao.getUsername(user.getUsername());
          villageId = usr.getVillage().getId();
          supportDatas = supportDataDao.findEducationClaimed(villageId);
          return supportDatas;
         } catch (Exception e) {
             System.out.println("Health allocs "+e);
             return supportDatas;
         }
     }
     
      public List<SupportData> getEconomicSupportDataClaimed(){
         try {
          User usr = userDao.getUsername(user.getUsername());
          villageId = usr.getVillage().getId();
          supportDatas = supportDataDao.findEconomicClaimed(villageId);
          return supportDatas;
         } catch (Exception e) {
             System.out.println("Health allocs "+e);
             return supportDatas;
         }
     }
      
       public Integer getEconomicSupportDataClaimedJobYes(){
         try {
          User usr = userDao.getUsername(user.getUsername());
          villageId = usr.getVillage().getId();
          supportDatas = supportDataDao.findEconomicClaimedECJobYes(villageId);
          return supportDatas.size();
         } catch (Exception e) {
             System.out.println("Health allocs "+e);
             return supportDatas.size();
         }
     }
       public Integer getEconomicSupportDataClaimedJobNo(){
         try {
          User usr = userDao.getUsername(user.getUsername());
          villageId = usr.getVillage().getId();
          supportDatas = supportDataDao.findEconomicClaimedECJobNo(villageId);
          return supportDatas.size();
         } catch (Exception e) {
             System.out.println("Health allocs "+e);
             return supportDatas.size();
         }
     }
       
       public Integer getEconomicSupportDataClaimedHY(){
         try {
          User usr = userDao.getUsername(user.getUsername());
          villageId = usr.getVillage().getId();
          supportDatas = supportDataDao.findEconomicClaimedECHY(villageId);
          return supportDatas.size();
         } catch (Exception e) {
             System.out.println("Health allocs "+e);
             return supportDatas.size();
         }
     }
       
       public Integer getEconomicSupportDataClaimedHN(){
         try {
          User usr = userDao.getUsername(user.getUsername());
          villageId = usr.getVillage().getId();
          supportDatas = supportDataDao.findEconomicClaimedECHN(villageId);
          return supportDatas.size();
         } catch (Exception e) {
             System.out.println("Health allocs "+e);
             return supportDatas.size();
         }
     }
       
       public Integer getEconomicSupportDataClaimedBY(){
         try {
          User usr = userDao.getUsername(user.getUsername());
          villageId = usr.getVillage().getId();
          supportDatas = supportDataDao.findEconomicClaimedECBY(villageId);
          return supportDatas.size();
         } catch (Exception e) {
             System.out.println("Health allocs "+e);
             return supportDatas.size();
         }
     }
       
       public Integer getEconomicSupportDataClaimedBN(){
         try {
          User usr = userDao.getUsername(user.getUsername());
          villageId = usr.getVillage().getId();
          supportDatas = supportDataDao.findEconomicClaimedECBN(villageId);
          return supportDatas.size();
         } catch (Exception e) {
             System.out.println("Health allocs "+e);
             return supportDatas.size();
         }
     }
       
       public Integer totalEconomicJobStatus(){
         try {
          User usr = userDao.getUsername(user.getUsername());
          villageId = usr.getVillage().getId();
          supportDatas = supportDataDao.findEconomicClaimedECJobYes(villageId);
          Integer y = supportDatas.size();
          supportDatas = supportDataDao.findEconomicClaimedECJobNo(villageId);
          Integer n = supportDatas.size();
          return y + n;
         } catch (Exception e) {
             System.out.println("Health allocs "+e);
             return supportDatas.size();
         }
     }
       
       
       
       public Integer totalEconomicOwnHouse(){
         try {
          User usr = userDao.getUsername(user.getUsername());
          villageId = usr.getVillage().getId();
          supportDatas = supportDataDao.findEconomicClaimedECHY(villageId);
          Integer y = supportDatas.size();
          supportDatas = supportDataDao.findEconomicClaimedECHN(villageId);
          Integer n = supportDatas.size();
          return y + n;
         } catch (Exception e) {
             System.out.println("Health allocs "+e);
             return supportDatas.size();
         }
     }
       
       public Integer totalEconomicOwnBusiness(){
         try {
          User usr = userDao.getUsername(user.getUsername());
          villageId = usr.getVillage().getId();
          supportDatas = supportDataDao.findEconomicClaimedECBY(villageId);
          Integer y = supportDatas.size();
          supportDatas = supportDataDao.findEconomicClaimedECBN(villageId);
          Integer n = supportDatas.size();
          return y + n;
         } catch (Exception e) {
             System.out.println("Health allocs "+e);
             return supportDatas.size();
         }
     }
       
      
     public Integer getEducationSupportDataClaimedEDNoneMale(){
         try {
          User usr = userDao.getUsername(user.getUsername());
          villageId = usr.getVillage().getId();
          supportDatas = supportDataDao.findEducationClaimedEDNoneMale(villageId);
          return supportDatas.size();
         } catch (Exception e) {
             System.out.println("Health allocs "+e);
             return supportDatas.size();
         }
     }
     public Integer getEducationSupportDataClaimedEDNoneFemale(){
         try {
          User usr = userDao.getUsername(user.getUsername());
          villageId = usr.getVillage().getId();
          supportDatas = supportDataDao.findEducationClaimedEDNoneFemale(villageId);
          return supportDatas.size();
         } catch (Exception e) {
             System.out.println("Health allocs "+e);
             return supportDatas.size();
         }
     }
     
      public Integer getEducationSupportDataClaimedEDPrimaryMale(){
         try {
          User usr = userDao.getUsername(user.getUsername());
          villageId = usr.getVillage().getId();
          supportDatas = supportDataDao.findEducationClaimedEDPrimaryMale(villageId);
          return supportDatas.size();
         } catch (Exception e) {
             System.out.println("Health allocs "+e);
             return supportDatas.size();
         }
     }
      
      
     public Integer getEducationSupportDataClaimedEDPrimaryFemale(){
         try {
          User usr = userDao.getUsername(user.getUsername());
          villageId = usr.getVillage().getId();
          supportDatas = supportDataDao.findEducationClaimedEDPrimaryFemale(villageId);
          return supportDatas.size();
         } catch (Exception e) {
             System.out.println("Health allocs "+e);
             return supportDatas.size();
         }
     }
     
      public Integer getEducationSupportDataClaimedEDSecondaryMale(){
         try {
          User usr = userDao.getUsername(user.getUsername());
          villageId = usr.getVillage().getId();
          supportDatas = supportDataDao.findEducationClaimedEDSecondaryMale(villageId);
          return supportDatas.size();
         } catch (Exception e) {
             System.out.println("Health allocs "+e);
             return supportDatas.size();
         }
     }
      
      
     public Integer getEducationSupportDataClaimedEDSecondaryFemale(){
         try {
          User usr = userDao.getUsername(user.getUsername());
          villageId = usr.getVillage().getId();
          supportDatas = supportDataDao.findEducationClaimedEDSecondaryFemale(villageId);
          return supportDatas.size();
         } catch (Exception e) {
             System.out.println("Health allocs "+e);
             return supportDatas.size();
         }
     }
     
     
     public Integer totalNoEducation(){
         try {
          User usr = userDao.getUsername(user.getUsername());
          villageId = usr.getVillage().getId();
           supportDatas = supportDataDao.findEducationClaimedEDNoneMale(villageId);
           Integer m = supportDatas.size();
           supportDatas = supportDataDao.findEducationClaimedEDNoneFemale(villageId);
           Integer f = supportDatas.size();
          
           return m+f;
         } catch (Exception e) {
             System.out.println("Health allocs "+e);
             return supportDatas.size();
         }
     }
     
      public Integer totalEducationPrimary(){
         try {
          User usr = userDao.getUsername(user.getUsername());
          villageId = usr.getVillage().getId();
           supportDatas = supportDataDao.findEducationClaimedEDPrimaryMale(villageId);
           Integer m = supportDatas.size();
           supportDatas = supportDataDao.findEducationClaimedEDPrimaryFemale(villageId);
           Integer f = supportDatas.size();
          
           return m+f;
         } catch (Exception e) {
             System.out.println("Health allocs "+e);
             return supportDatas.size();
         }
     }
      
      public Integer totalEducationSecondary(){
         try {
          User usr = userDao.getUsername(user.getUsername());
          villageId = usr.getVillage().getId();
           supportDatas = supportDataDao.findEducationClaimedEDSecondaryMale(villageId);
           Integer m = supportDatas.size();
           supportDatas = supportDataDao.findEducationClaimedEDSecondaryFemale(villageId);
           Integer f = supportDatas.size();
          
           return m+f;
         } catch (Exception e) {
             System.out.println("Health allocs "+e);
             return supportDatas.size();
         }
     }
     
     public Integer getEducationSupportDataClaimedSize(){
         try {
          User usr = userDao.getUsername(user.getUsername());
          villageId = usr.getVillage().getId();
          supportDatas = supportDataDao.findEducationClaimed(villageId);
          return supportDatas.size();
         } catch (Exception e) {
             System.out.println("Health allocs "+e);
             return supportDatas.size();
         }
     }
     
     public Integer getHealthSupportDataClaimedSize(){
         try {
          User usr = userDao.getUsername(user.getUsername());
          villageId = usr.getVillage().getId();
          supportDatas = supportDataDao.findHealthClaimed(villageId);
          return supportDatas.size();
         } catch (Exception e) {
             System.out.println("Health allocs "+e);
             return supportDatas.size();
         }
     }
     
     public Integer getHealthSupportDataClaimedHINone(){
         try {
          User usr = userDao.getUsername(user.getUsername());
          villageId = usr.getVillage().getId();
          supportDatas = supportDataDao.findHealthClaimedHINone(villageId);
          return supportDatas.size();
         } catch (Exception e) {
             System.out.println("Health allocs "+e);
             return supportDatas.size();
         }
     }
     
     public Integer getHealthSupportDataClaimedHIMituelle(){
         try {
          User usr = userDao.getUsername(user.getUsername());
          villageId = usr.getVillage().getId();
          supportDatas = supportDataDao.findHealthClaimedHIMituelle(villageId);
          return supportDatas.size();
         } catch (Exception e) {
             System.out.println("Health allocs "+e);
             return supportDatas.size();
         }
     }
     
     public Integer getHealthSupportDataClaimedHIRama(){
         try {
          User usr = userDao.getUsername(user.getUsername());
          villageId = usr.getVillage().getId();
          supportDatas = supportDataDao.findHealthClaimedHIRama(villageId);
          return supportDatas.size();
         } catch (Exception e) {
             System.out.println("Health allocs "+e);
             return supportDatas.size();
         }
     }
     
     public Integer getHealthSupportDataClaimedDisaMale(){
         try {
          User usr = userDao.getUsername(user.getUsername());
          villageId = usr.getVillage().getId();
          supportDatas = supportDataDao.findHealthClaimedDisabMale(villageId);
          return supportDatas.size();
         } catch (Exception e) {
             System.out.println("Health allocs "+e);
             return supportDatas.size();
         }
     }
     
     public Integer getHealthSupportDataClaimedDiseMale(){
         try {
          User usr = userDao.getUsername(user.getUsername());
          villageId = usr.getVillage().getId();
          supportDatas = supportDataDao.findHealthClaimedDisasMale(villageId);
          return supportDatas.size();
         } catch (Exception e) {
             System.out.println("Health allocs "+e);
             return supportDatas.size();
         }
     }
     
     public Integer getHealthSupportDataClaimedDiseFemale(){
         try {
          User usr = userDao.getUsername(user.getUsername());
          villageId = usr.getVillage().getId();
          supportDatas = supportDataDao.findHealthClaimedDisasFemale(villageId);
          return supportDatas.size();
         } catch (Exception e) {
             System.out.println("Health allocs "+e);
             return supportDatas.size();
         }
     }
     
     public Integer getHealthSupportDataClaimedDisaFemale(){
         try {
          User usr = userDao.getUsername(user.getUsername());
          villageId = usr.getVillage().getId();
          supportDatas = supportDataDao.findHealthClaimedDisabFemale(villageId);
          return supportDatas.size();
         } catch (Exception e) {
             System.out.println("Health allocs "+e);
             return supportDatas.size();
         }
     }
     
     public List<Allocated> getCanceledAllocated(){
         try {
          User usr = userDao.getUsername(user.getUsername());
          villageId = usr.getVillage().getId();
          allocates = allocatedDao.findCanceledAllocated(villageId);
          return allocates;
         } catch (Exception e) {
             System.out.println("Health allocs "+e);
             return allocates;
         }
     }
     
     
     public List<Allocated> getRichAllocated(){
         try {
          User usr = userDao.getUsername(user.getUsername());
          villageId = usr.getVillage().getId();
          allocates = allocatedDao.findRichAllocated(villageId);
          return allocates;
         } catch (Exception e) {
             System.out.println("Health allocs "+e);
             return allocates;
         }
     }
     
     public List<Allocated> getEducationAllocated(){
         try {
          User usr = userDao.getUsername(user.getUsername());
          villageId = usr.getVillage().getId();
          allocates = allocatedDao.findEducationAllocated(villageId);
          return allocates;
         } catch (Exception e) {
             System.out.println("Educa allocs "+e);
             return allocates;
         }
     }
     
     
     public List<Allocated> getEconomicAllocated(){
         try {
          User usr = userDao.getUsername(user.getUsername());
          villageId = usr.getVillage().getId();
          allocates = allocatedDao.findEconomicAllocated(villageId);
          return allocates;
         } catch (Exception e) {
             System.out.println("Economic allocs "+e);
             return allocates;
         }
     }
     
  public List<Support> getAvailableEconomicSupport(){
        try {
         User usr = userDao.getUsername(user.getUsername());
         village = usr.getVillage();
         Integer id = village.getId();
         resident = usr.getResident();
         
         allocated = allocatedDao.getAllocatedByResidentIdByEconomic(resident.getId());
         List<Support> supps = new ArrayList<>();
         if(allocated!=null && allocated.getSupportStatus().equalsIgnoreCase("active")){
            return  supports = supportDao.findEconomicSupportAvailableByVillage(id); 
         }else{
             return  supps;
         }
         
        } catch (Exception e) {
            System.out.println("AAAAA SUPPORTS "+e);
            return supports;
        }
    }
      
    public String updateSupportEconomic(Integer id){
        try {
             User usr = userDao.getUsername(user.getUsername());
             village = usr.getVillage();
             support = supportDao.findOne(Support.class, id);
             if(suppVal != null && suppQuantity !=null){
                 
             
             Integer q = support.getQuantity();
             Integer v = support.getSupportValue();
             Integer nq = suppQuantity + q;
             Integer vn = suppVal + v;
             support.setQuantity(nq);
             support.setSupportValue(vn);
             supportDao.updateObject(support);
             if(support.getQuantity()>0){
                 support.setStatus("Available");
                 supportDao.updateObject(support);
                 successMessage("Support Updated Successfuly");
             }else{
                 support.setStatus("Finished");
                 supportDao.updateObject(support);
             }
             }else{
                 warningMessage("Please fill all fields");
             }
            return "economic-support.xhtml";
        } catch (Exception e) {
            return "economic-support.xhtml";
        }
    } 
    
    public String updateSupportEducation(Integer id){
        try {
             User usr = userDao.getUsername(user.getUsername());
             village = usr.getVillage();
             support = supportDao.findOne(Support.class, id);
             if(suppVal!=null && suppQuantity!=null){
                 
             
             Integer q = support.getQuantity();
             Integer v = support.getSupportValue();
             Integer nq = suppQuantity + q;
             Integer nv = suppVal + v;
             support.setSupportValue(nv);
             support.setQuantity(nq);
             supportDao.updateObject(support);
             if(support.getQuantity()>0){
                 support.setStatus("Available");
                 supportDao.updateObject(support);
                 successMessage("Support Updated Successfully");
             }else{
                 support.setStatus("Finished");
                 supportDao.updateObject(support);
             }
             }else{
                 warningMessage("Please fill all fields");
             }
            return "education-support.xhtml";
        } catch (Exception e) {
            return "education-support.xhtml";
        }
    }
    
     public String updateSupportHealth(Integer id){
        try {
             User usr = userDao.getUsername(user.getUsername());
             village = usr.getVillage();
             support = supportDao.findOne(Support.class, id);
             if(suppVal!=null && suppQuantity!=null){
                 
             
             Integer q = support.getQuantity();
             Integer v = support.getSupportValue();
             Integer nq = suppQuantity + q;
             Integer nv = suppVal + v;
             support.setSupportValue(nv);
             support.setQuantity(nq);
             supportDao.updateObject(support);
             if(support.getQuantity()>0){
                 support.setStatus("Available");
                 supportDao.updateObject(support);
                 successMessage("Support Updated Successfully");
             }else{
                 support.setStatus("Finished");
                 supportDao.updateObject(support);
             }
             }else{
                 warningMessage("Please fill both fields");
             }
            return "health-support.xhtml";
        } catch (Exception e) {
            return "health-support.xhtml";
        }
    }
      
    public List<Support> getAvailableEconomicSupportNumerator(){
        try {
         User usr = userDao.getUsername(user.getUsername());
         village = usr.getVillage();
         Integer id = village.getId();
 
            return  supports = supportDao.findEconomicSupportAvailableByVillage(id); 
        
        } catch (Exception e) {
            System.out.println("AAAAA SUPPORTS "+e);
            return supports;
        }
    }  
      
    public List<Support> getAvailableEconomicSupportAuthority(){
        try {
         User usr = userDao.getUsername(user.getUsername());
         village = usr.getVillage();
         Integer id = village.getId();
       
            return  supports = supportDao.findEconomicSupportAvailableByVillageAuthority(id); 
        
         
        } catch (Exception e) {
            return supports;
        }
    }  
    
    public List<Numerator> getAllNumeratorByVillage(){
        try {
        User usr = userDao.getUsername(user.getUsername());
        Integer id = usr.getVillage().getId();
        numerators = numeratorDao.findNumeratorsByVillage(id);
        return numerators;
        } catch (Exception e) {
            System.out.println("Numerators error "+e);
            return numerators;
            
        }
        
    }
    
    public List<Numerator> getAllDisabledNumeratorByVillage(){
        try {
        User usr = userDao.getUsername(user.getUsername());
        Integer id = usr.getVillage().getId();
        numerators = numeratorDao.findDisabledNumeratorsByVillage(id);
        return numerators;
        } catch (Exception e) {
            System.out.println("Numerators error "+e);
            return numerators;
            
        }
        
    }
    
    public Integer getAllResidentsNumber(){
        try {
        User usr = userDao.getUsername(user.getUsername());
        Integer id = usr.getVillage().getId();
        residents = residentDao.findResidentsByVillage(id);
        return residents.size();
   
        } catch (Exception e) {
            System.out.println("ALL RESIDENTS ERROR "+e);
        return residents.size();
        }
    }
    
    public Integer getAllResidentsNumberHealth(){
        try {
        User usr = userDao.getUsername(user.getUsername());
        Integer id = usr.getVillage().getId();
        allocates = allocatedDao.findHealthAllocated(id);
        return allocates.size();
   
        } catch (Exception e) {
            System.out.println("All health allocs "+e);
        return allocates.size();
        }
    }
    
    public Integer getAllResidentsNumberEducation(){
        try {
        User usr = userDao.getUsername(user.getUsername());
        Integer id = usr.getVillage().getId();
        allocates = allocatedDao.findEducationAllocated(id);
        return allocates.size();
   
        } catch (Exception e) {
            System.out.println("All Educa allocs "+e);
        return allocates.size();
        }
    }
    
    public Integer getAllResidentsNumberEconomic(){
        try {
        User usr = userDao.getUsername(user.getUsername());
        Integer id = usr.getVillage().getId();
        allocates = allocatedDao.findEconomicAllocated(id);
        return allocates.size();
   
        } catch (Exception e) {
            System.out.println("All Econ allocs "+e);
        return allocates.size();
        }
    }
    
    public Integer getAllFemaleResidentsNumber(){
        try {
        User usr = userDao.getUsername(user.getUsername());
        Integer id = usr.getVillage().getId();
        residents = residentDao.findFemaleResidentsByVillage(id);
        return residents.size();
   
        } catch (Exception e) {
            System.out.println("All Female "+e);
        return residents.size();
        }
    }
    
     public Integer getAllMalesResidentsNumber(){
        try {
        User usr = userDao.getUsername(user.getUsername());
        Integer id = usr.getVillage().getId();
        residents = residentDao.findMaleResidentsByVillage(id);
        return residents.size();
   
        } catch (Exception e) {
            System.out.println("All Male "+e);
        return residents.size();
        }
    }
     
     public Integer getAllSupportActive(){
        try {
        User usr = userDao.getUsername(user.getUsername());
        Integer id = usr.getVillage().getId();
        allocates = allocatedDao.findAllSupportActive(id);
        return allocates.size();
   
        } catch (Exception e) {
            System.out.println("All activ supp "+e);
        return allocates.size();
        }
    }
     
     public Integer getAllSupportCanceled(){
        try {
        User usr = userDao.getUsername(user.getUsername());
        Integer id = usr.getVillage().getId();
        allocates = allocatedDao.findAllSupportCanceled(id);
        return allocates.size();
   
        } catch (Exception e) {
            System.out.println("All canceled supp "+e);
        return allocates.size();
        }
    }
    
     public Integer getAllhealthSupportValueByVillage(){
        Integer hsv =0;
         try {
            User usr = userDao.getUsername(user.getUsername());
            resident = usr.getResident();
            Integer rid = resident.getId();
            village = usr.getVillage();
            Integer id = village.getId();
            List<Allocated> allos = new ArrayList<>();
            allos = allocatedDao.findResidentInHealth(id, rid);
            if(allos.size() > 0 ){
            BigDecimal fq = supportDao.totalHealthSupportValueByVillage(id);
            allocates = allocatedDao.findAllAllocatedByHealth(id);
            Integer hrs = allocates.size();
            Integer d = fq.intValue();
            hsv = d / hrs;
            return hsv;  
            }else{
               return 0; 
            }
            
            
            
         } catch (Exception e) {
             System.out.println("Support analys health "+e);
             return hsv;
         }
     }
     
     public Integer getAllhealthQSupportValueByVillage(){
        Integer q =0;
         try {
            User usr = userDao.getUsername(user.getUsername());
            resident = usr.getResident();
            Integer rid = resident.getId();
             BigDecimal fq = supportDataDao.totalHealthQSupportValueByVillage(rid);
             q = fq.intValue();
            return q;

         } catch (Exception e) {
             System.out.println("Support analys health "+e);
             return q;
         }
     }
     
     public Integer getAlleducationQSupportValueByVillage(){
        Integer q =0;
         try {
            User usr = userDao.getUsername(user.getUsername());
            resident = usr.getResident();
            Integer rid = resident.getId();
             BigDecimal fq = supportDataDao.totalEducationQSupportValueByVillage(rid);
             q = fq.intValue();
            return q;

         } catch (Exception e) {
             System.out.println("Support analys health "+e);
             return q;
         }
     }
     
      public Integer getAlleconomicQSupportValueByVillage(){
        Integer q =0;
         try {
            User usr = userDao.getUsername(user.getUsername());
            resident = usr.getResident();
            Integer rid = resident.getId();
             BigDecimal fq = supportDataDao.totalEconomicQSupportValueByVillage(rid);
             q = fq.intValue();
            return q;

         } catch (Exception e) {
             System.out.println("Support analys health "+e);
             return q;
         }
     }
     
      public Integer getAlleducationSupportValueByVillage(){
        Integer hsv =0;
         try {
            User usr = userDao.getUsername(user.getUsername());
            resident = usr.getResident();
            Integer rid = resident.getId();
            village = usr.getVillage();
            Integer id = village.getId();
            List<Allocated> allos = new ArrayList<>();
            allos = allocatedDao.findResidentInEducation(id, rid);
            if(allos.size() > 0 ){
            BigDecimal fq = supportDao.totalEducationSupportValueByVillage(id);
            allocates = allocatedDao.findAllAllocatedByEducation(id);
            Integer hrs = allocates.size();
            Integer d = fq.intValue();
            hsv = d / hrs;
            return hsv;  
            }else{
               return 0; 
            }
            
            
            
         } catch (Exception e) {
             System.out.println("Support analys education "+e);
             return hsv;
         }
     }
     
      public Integer getAlleconomicSupportValueByVillage(){
        Integer hsv =0;
         try {
            User usr = userDao.getUsername(user.getUsername());
            resident = usr.getResident();
            Integer rid = resident.getId();
            village = usr.getVillage();
            Integer id = village.getId();
            List<Allocated> allos = new ArrayList<>();
            allos = allocatedDao.findResidentInEconomic(id, rid);
            if(allos.size() > 0 ){
            BigDecimal fq = supportDao.totalEconomicSupportValueByVillage(id);
            allocates = allocatedDao.findAllAllocatedByEconomic(id);
            Integer hrs = allocates.size();
            Integer d = fq.intValue();
            hsv = d / hrs;
            return hsv;  
            }else{
               return 0; 
            }
            
            
            
         } catch (Exception e) {
             System.out.println("Support analys economic "+e);
             return hsv;
         }
     }
 
    public String totaSupportByVillage(){
            DecimalFormat decimalFormat = new DecimalFormat("#.##");
            decimalFormat.setGroupingUsed(true);
            decimalFormat.setGroupingSize(3);
            
             User usr = userDao.getUsername(user.getUsername());
             village = usr.getVillage();
             
             Integer id = village.getId();
             String k ="";
             String kk="0";
             try {
                BigDecimal fq = supportDao.totalSupportValueByVillage(id);
                Integer d = fq.intValue();
                k = decimalFormat.format(d);
                return k;
            } catch (Exception e) {
                 return kk;
            }
        }
    
    public String totalHealthClaimedBudget(){
            DecimalFormat decimalFormat = new DecimalFormat("#.##");
            decimalFormat.setGroupingUsed(true);
            decimalFormat.setGroupingSize(3);
            
             User usr = userDao.getUsername(user.getUsername());
             village = usr.getVillage();
             
             Integer id = village.getId();
             String k ="";
             String kk="0";
             try {
                BigDecimal fq = supportDataDao.totalHealthClaimedBudget(id);
                Integer d = fq.intValue();
                k = decimalFormat.format(d);
                return k;
            } catch (Exception e) {
                 return kk;
            }
        }
    
    public String totalEducationClaimedBudget(){
            DecimalFormat decimalFormat = new DecimalFormat("#.##");
            decimalFormat.setGroupingUsed(true);
            decimalFormat.setGroupingSize(3);
            
             User usr = userDao.getUsername(user.getUsername());
             village = usr.getVillage();
             
             Integer id = village.getId();
             String k ="";
             String kk="0";
             try {
                BigDecimal fq = supportDataDao.totalEducationClaimedBudget(id);
                Integer d = fq.intValue();
                k = decimalFormat.format(d);
                return k;
            } catch (Exception e) {
                 return kk;
            }
        }
    
    public String totalEconomicClaimedBudget(){
            DecimalFormat decimalFormat = new DecimalFormat("#.##");
            decimalFormat.setGroupingUsed(true);
            decimalFormat.setGroupingSize(3);
            
             User usr = userDao.getUsername(user.getUsername());
             village = usr.getVillage();
             
             Integer id = village.getId();
             String k ="";
             String kk="0";
             try {
                BigDecimal fq = supportDataDao.totalEconomicClaimedBudget(id);
                Integer d = fq.intValue();
                k = decimalFormat.format(d);
                return k;
            } catch (Exception e) {
                 return kk;
            }
        }
    
    public String totalAllRemaingBudget(){
            DecimalFormat decimalFormat = new DecimalFormat("#.##");
            decimalFormat.setGroupingUsed(true);
            decimalFormat.setGroupingSize(3);
            
             User usr = userDao.getUsername(user.getUsername());
             village = usr.getVillage();
             
             Integer id = village.getId();
             String k ="";
             String ked ="";
             String kec ="";
             String ktot ="";
             String kk="0";
             try {
                 //health
                BigDecimal fq = supportDataDao.totalHealthClaimedBudget(id);
                BigDecimal fq1 = supportDao.totalHealthSupportValueByVillage(id);
                Integer d = fq.intValue();
                Integer d1 = fq1.intValue();
                Integer rm = d1 - d;
                k = decimalFormat.format(rm);
                
                // education
                
                 BigDecimal fqed = supportDataDao.totalEducationClaimedBudget(id);
                BigDecimal fq1ed = supportDao.totalEducationSupportValueByVillage(id);
                Integer ded = fqed.intValue();
                Integer d1ed = fq1ed.intValue();
                Integer rmed = d1ed - ded;
                ked = decimalFormat.format(rmed);
                
                // economic
                
                BigDecimal fqec = supportDataDao.totalEconomicClaimedBudget(id);
                BigDecimal fq1ec = supportDao.totalEconomicSupportValueByVillage(id);
                Integer dec = fqec.intValue();
                Integer d1ec = fq1ec.intValue();
                Integer rmec = d1ec - dec;
                kec = decimalFormat.format(rmec);
                
                Integer tot = rmec + rmed + rm;
                
                ktot = decimalFormat.format(tot);
                
                return ktot;
            } catch (Exception e) {
                 return kk;
            }
        }
    
     public String totalHealthRemaingBudget(){
            DecimalFormat decimalFormat = new DecimalFormat("#.##");
            decimalFormat.setGroupingUsed(true);
            decimalFormat.setGroupingSize(3);
            
             User usr = userDao.getUsername(user.getUsername());
             village = usr.getVillage();
             
             Integer id = village.getId();
             String k ="";
             String kk="0";
             try {
                BigDecimal fq = supportDataDao.totalHealthClaimedBudget(id);
                BigDecimal fq1 = supportDao.totalHealthSupportValueByVillage(id);
                Integer d = fq.intValue();
                Integer d1 = fq1.intValue();
                Integer rm = d1 - d;
                k = decimalFormat.format(rm);
                return k;
            } catch (Exception e) {
                 return kk;
            }
        }
    
    public String totalEducationRemaingBudget(){
            DecimalFormat decimalFormat = new DecimalFormat("#.##");
            decimalFormat.setGroupingUsed(true);
            decimalFormat.setGroupingSize(3);
            
             User usr = userDao.getUsername(user.getUsername());
             village = usr.getVillage();
             
             Integer id = village.getId();
             String k ="";
             String kk="0";
             try {
                BigDecimal fq = supportDataDao.totalEducationClaimedBudget(id);
                BigDecimal fq1 = supportDao.totalEducationSupportValueByVillage(id);
                Integer d = fq.intValue();
                Integer d1 = fq1.intValue();
                Integer rm = d1 - d;
                k = decimalFormat.format(rm);
                return k;
            } catch (Exception e) {
                 return kk;
            }
        }
    
    public String totalEconomicRemaingBudget(){
            DecimalFormat decimalFormat = new DecimalFormat("#.##");
            decimalFormat.setGroupingUsed(true);
            decimalFormat.setGroupingSize(3);
            
             User usr = userDao.getUsername(user.getUsername());
             village = usr.getVillage();
             
             Integer id = village.getId();
             String k ="";
             String kk="0";
             try {
                BigDecimal fq = supportDataDao.totalEconomicClaimedBudget(id);
                BigDecimal fq1 = supportDao.totalEconomicSupportValueByVillage(id);
                Integer d = fq.intValue();
                Integer d1 = fq1.intValue();
                Integer rm = d1 - d;
                k = decimalFormat.format(rm);
                return k;
            } catch (Exception e) {
                 return kk;
            }
        }
    
    public String totalSupportDataByHealth(){
            DecimalFormat decimalFormat = new DecimalFormat("#.##");
            decimalFormat.setGroupingUsed(true);
            decimalFormat.setGroupingSize(3);
            
             User usr = userDao.getUsername(user.getUsername());
             resident = usr.getResident();
             
             Integer id = resident.getId();
             String k ="";
             String kk="0";
             try {
                BigDecimal fq = supportDataDao.totalHealthSupportDataValueByResident(id);
                Integer d = fq.intValue();
                k = decimalFormat.format(d);
                return k;
            } catch (Exception e) {
                 return kk;
            }
        }
    
    public Integer totalSupportDataHealthConvert(){
            DecimalFormat decimalFormat = new DecimalFormat("#.##");
            decimalFormat.setGroupingUsed(true);
            decimalFormat.setGroupingSize(3);
            
             User usr = userDao.getUsername(user.getUsername());
             resident = usr.getResident();
             
             Integer id = resident.getId();
             Integer k =0;
             Integer kk=0;
             try {
                BigDecimal fq = supportDataDao.totalHealthSupportDataValueByResident(id);
                Integer d = fq.intValue();
                k = d;
                return k;
            } catch (Exception e) {
                 return kk;
            }
        }
    
    
    
    public String totalSupportDataByEducation(){
            DecimalFormat decimalFormat = new DecimalFormat("#.##");
            decimalFormat.setGroupingUsed(true);
            decimalFormat.setGroupingSize(3);
            
             User usr = userDao.getUsername(user.getUsername());
             resident = usr.getResident();
             
             Integer id = resident.getId();
             String k ="";
             String kk="0";
             try {
                BigDecimal fq = supportDataDao.totalEducationSupportDataValueByResident(id);
                Integer d = fq.intValue();
                k = decimalFormat.format(d);
                return k;
            } catch (Exception e) {
                 return kk;
            }
      }
    
    public Integer totalSupportDataEducationConvert(){
            DecimalFormat decimalFormat = new DecimalFormat("#.##");
            decimalFormat.setGroupingUsed(true);
            decimalFormat.setGroupingSize(3);
            
             User usr = userDao.getUsername(user.getUsername());
             resident = usr.getResident();
             
             Integer id = resident.getId();
             Integer k =0;
             Integer kk=0;
             try {
                BigDecimal fq = supportDataDao.totalEducationSupportDataValueByResident(id);
                Integer d = fq.intValue();
                k = d;
                return k;
            } catch (Exception e) {
                 return kk;
            }
        }
    
    public String totalSupportDataByEconomic(){
            DecimalFormat decimalFormat = new DecimalFormat("#.##");
            decimalFormat.setGroupingUsed(true);
            decimalFormat.setGroupingSize(3);
            
             User usr = userDao.getUsername(user.getUsername());
             resident = usr.getResident();
             
             Integer id = resident.getId();
             String k ="";
             String kk="0";
             try {
                BigDecimal fq = supportDataDao.totalEconomicSupportDataValueByResident(id);
                Integer d = fq.intValue();
                k = decimalFormat.format(d);
                return k;
            } catch (Exception e) {
                 return kk;
            }
      }
    
    public Integer totalSupportDataEconomicConvert(){
            DecimalFormat decimalFormat = new DecimalFormat("#.##");
            decimalFormat.setGroupingUsed(true);
            decimalFormat.setGroupingSize(3);
            
             User usr = userDao.getUsername(user.getUsername());
             resident = usr.getResident();
             
             Integer id = resident.getId();
             Integer k =0;
             Integer kk=0;
             try {
                BigDecimal fq = supportDataDao.totalEconomicSupportDataValueByResident(id);
                Integer d = fq.intValue();
                k = d;
                return k;
            } catch (Exception e) {
                 return kk;
            }
        }
    
    public String totalSupportDataClaimedByResident(){
            DecimalFormat decimalFormat = new DecimalFormat("#.##");
            decimalFormat.setGroupingUsed(true);
            decimalFormat.setGroupingSize(3);
            
             User usr = userDao.getUsername(user.getUsername());
             resident = usr.getResident();
             
             Integer id = resident.getId();
             String k ="";
             String kk="0";
             try {
              Integer conv = totalSupportDataHealthConvert()+totalSupportDataEducationConvert()+totalSupportDataEconomicConvert();
                 System.out.println("Conv "+conv);
               k = decimalFormat.format(conv);
                return k;
            } catch (Exception e) {
                 System.out.println("error claimed "+e);
                 return kk;
            }
      }
    
    public String totalHealthSupportByVillage(){
            DecimalFormat decimalFormat = new DecimalFormat("#.##");
            decimalFormat.setGroupingUsed(true);
            decimalFormat.setGroupingSize(3);
            
             User usr = userDao.getUsername(user.getUsername());
             village = usr.getVillage();
             
             Integer id = village.getId();
             String k ="";
             String kk="0";
             try {
                BigDecimal fq = supportDao.totalHealthSupportValueByVillage(id);
                Integer d = fq.intValue();
                k = decimalFormat.format(d);
                return k;
            } catch (Exception e) {
                 return kk;
            }
        }
    
    public String totalEducationSupportByVillage(){
            DecimalFormat decimalFormat = new DecimalFormat("#.##");
            decimalFormat.setGroupingUsed(true);
            decimalFormat.setGroupingSize(3);
            
             User usr = userDao.getUsername(user.getUsername());
             village = usr.getVillage();
             
             Integer id = village.getId();
             String k ="";
             String kk="0";
             try {
                BigDecimal fq = supportDao.totalEducationSupportValueByVillage(id);
                Integer d = fq.intValue();
                k = decimalFormat.format(d);
                return k;
            } catch (Exception e) {
                 return kk;
            }
        }
    
    public String totalEconomicSupportByVillage(){
            DecimalFormat decimalFormat = new DecimalFormat("#.##");
            decimalFormat.setGroupingUsed(true);
            decimalFormat.setGroupingSize(3);
            
             User usr = userDao.getUsername(user.getUsername());
             village = usr.getVillage();
             
             Integer id = village.getId();
             String k ="";
             String kk="0";
             try {
                BigDecimal fq = supportDao.totalEconomicSupportValueByVillage(id);
                Integer d = fq.intValue();
                k = decimalFormat.format(d);
                return k;
            } catch (Exception e) {
                 return kk;
            }
        }
    
    
    
   public static String createAnonymous(String input) {

	        String md5 = null;

	        if (null == input) {
	            return null;
	        }

	        try {

	            //Create MessageDigest object for MD5
	            MessageDigest digest = MessageDigest.getInstance("MD5");

	            //Update input string in message digest
	            digest.update(input.getBytes(), 0, input.length());

	            //Converts message digest value in base 16 (hex) 
	            md5 = new BigInteger(1, digest.digest()).toString(20);

	        } catch (NoSuchAlgorithmException e) {

	            e.printStackTrace();
	        }
	        //System.out.println("encrypted password..."+md5);
	        return md5;
	    }
        public void successMessage(String summary) {
		FacesContext ct = FacesContext.getCurrentInstance();
		ct.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, summary, ""));
	}

	public void errorMessage(String summary) {
		FacesContext ct = FacesContext.getCurrentInstance();
		ct.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, ""));
	}
        
        public void warningMessage(String summary) {
		FacesContext ct = FacesContext.getCurrentInstance();
		ct.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, summary, ""));
	}

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public Sector getSector() {
        return sector;
    }

    public void setSector(Sector sector) {
        this.sector = sector;
    }

    public Cell getCell() {
        return cell;
    }

    public void setCell(Cell cell) {
        this.cell = cell;
    }

    public Village getVillage() {
        return village;
    }

    public void setVillage(Village village) {
        this.village = village;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setProvinces(List<Province> provinces) {
        this.provinces = provinces;
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
    }

    public int getSectorId() {
        return sectorId;
    }

    public void setSectorId(int sectorId) {
        this.sectorId = sectorId;
    }

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

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
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

    public SectorDao getSectorDao() {
        return sectorDao;
    }

    public void setSectorDao(SectorDao sectorDao) {
        this.sectorDao = sectorDao;
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
    
     public List<District> getDistrictsInProvince(){
     Province prov = new ProvinceDao().findOne(Province.class, provinceId);
      List<District> districts = prov.getDistricts().stream().distinct().collect(Collectors.toList());
     return districts;
   }
   
   public List<Sector> getSectorsInDistrict(){
       List<Sector> secs;     
       District  dist = new DistrictDao().findOne(District.class, districtId);
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
   
    public List<Village> getVillages(){
        return villageDao.findAll(Village.class);
    }
    
     public List<Province> getProvinces(){
     return provinceDao.allProvinces();
   }

    public Resident getResident() {
        return resident;
    }

    public void setResident(Resident resident) {
        this.resident = resident;
    }

    public ResidentDao getResidentDao() {
        return residentDao;
    }

    public void setResidentDao(ResidentDao residentDao) {
        this.residentDao = residentDao;
    }

    public List<Resident> getResidents() {
        return residents;
    }
    

    public void setResidents(List<Resident> residents) {
        this.residents = residents;
    }

    public String getVillageName() {
        return villageName;
    }

    public void setVillageName(String villageName) {
        this.villageName = villageName;
    }

    public Numerator getNumerator() {
        return numerator;
    }

    public void setNumerator(Numerator numerator) {
        this.numerator = numerator;
    }

    public NumeratorDao getNumeratorDao() {
        return numeratorDao;
    }

    public void setNumeratorDao(NumeratorDao numeratorDao) {
        this.numeratorDao = numeratorDao;
    }

    public SendSMS getSendsms() {
        return sendsms;
    }

    public void setSendsms(SendSMS sendsms) {
        this.sendsms = sendsms;
    }

    public List<Numerator> getNumerators() {
        return numerators;
    }

    public void setNumerators(List<Numerator> numerators) {
        this.numerators = numerators;
    }

    public Support getSupport() {
        return support;
    }

    public void setSupport(Support support) {
        this.support = support;
    }

    public SupportDao getSupportDao() {
        return supportDao;
    }

    public void setSupportDao(SupportDao supportDao) {
        this.supportDao = supportDao;
    }

    public List<Support> getSupports() {
        return supports;
    }

    public void setSupports(List<Support> supports) {
        this.supports = supports;
    }

    public String getNlast() {
        return nlast;
    }

    public void setNlast(String nlast) {
        this.nlast = nlast;
    }

    public String getNfirst() {
        return nfirst;
    }

    public void setNfirst(String nfirst) {
        this.nfirst = nfirst;
    }

    public String getNgender() {
        return ngender;
    }

    public void setNgender(String ngender) {
        this.ngender = ngender;
    }

    public String getNphone() {
        return nphone;
    }

    public void setNphone(String nphone) {
        this.nphone = nphone;
    }

    public String getNnid() {
        return nnid;
    }

    public void setNnid(String nnid) {
        this.nnid = nnid;
    }

    public String getNstat() {
        return nstat;
    }

    public void setNstat(String nstat) {
        this.nstat = nstat;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public String getSectorName() {
        return sectorName;
    }

    public void setSectorName(String sectorName) {
        this.sectorName = sectorName;
    }

    public String getCellName() {
        return cellName;
    }

    public void setCellName(String cellName) {
        this.cellName = cellName;
    }

    public String getProvinceNam() {
        return provinceNam;
    }

    public void setProvinceNam(String provinceNam) {
        this.provinceNam = provinceNam;
    }

    public String getUsernam() {
        return usernam;
    }

    public void setUsernam(String usernam) {
        this.usernam = usernam;
    }

    public String getPasswrd() {
        return passwrd;
    }

    public void setPasswrd(String passwrd) {
        this.passwrd = passwrd;
    }

    public Allocated getAllocated() {
        return allocated;
    }

    public void setAllocated(Allocated allocated) {
        this.allocated = allocated;
    }

    public AllocatedDao getAllocatedDao() {
        return allocatedDao;
    }

    public void setAllocatedDao(AllocatedDao allocatedDao) {
        this.allocatedDao = allocatedDao;
    }

    public List<Allocated> getAllocates() {
        return allocates;
    }

    public void setAllocates(List<Allocated> allocates) {
        this.allocates = allocates;
    }

    public String getFn() {
        return fn;
    }

    public void setFn(String fn) {
        this.fn = fn;
    }

    public String getLn() {
        return ln;
    }

    public void setLn(String ln) {
        this.ln = ln;
    }

    public String getCat() {
        return cat;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }

    public String getDis() {
        return dis;
    }

    public void setDis(String dis) {
        this.dis = dis;
    }

    public String getDise() {
        return dise;
    }

    public void setDise(String dise) {
        this.dise = dise;
    }

    public String getHi() {
        return hi;
    }

    public void setHi(String hi) {
        this.hi = hi;
    }

    public String getMp() {
        return mp;
    }

    public void setMp(String mp) {
        this.mp = mp;
    }

    public String getEd() {
        return ed;
    }

    public void setEd(String ed) {
        this.ed = ed;
    }

    public String getPh() {
        return ph;
    }

    public void setPh(String ph) {
        this.ph = ph;
    }

    public String getJb() {
        return jb;
    }

    public void setJb(String jb) {
        this.jb = jb;
    }

    public String getIcr() {
        return icr;
    }

    public void setIcr(String icr) {
        this.icr = icr;
    }

    public String getMs() {
        return ms;
    }

    public void setMs(String ms) {
        this.ms = ms;
    }

    public String getCn() {
        return cn;
    }

    public void setCn(String cn) {
        this.cn = cn;
    }

    public Date getDb() {
        return db;
    }

    public void setDb(Date db) {
        this.db = db;
    }

   
    public String getNd() {
        return nd;
    }

    public void setNd(String nd) {
        this.nd = nd;
    }

    public String getGndr() {
        return gndr;
    }

    public void setGndr(String gndr) {
        this.gndr = gndr;
    }

    public List<Resident> getRsdnts() {
        return rsdnts;
    }

    public void setRsdnts(List<Resident> rsdnts) {
        this.rsdnts = rsdnts;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNumeratorName() {
        return numeratorName;
    }

    public void setNumeratorName(String numeratorName) {
        this.numeratorName = numeratorName;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public MessageDao getMessageDao() {
        return messageDao;
    }

    public void setMessageDao(MessageDao messageDao) {
        this.messageDao = messageDao;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public String getSenderCode() {
        return senderCode;
    }

    public void setSenderCode(String senderCode) {
        this.senderCode = senderCode;
    }

    public Notification getNotification() {
        return notification;
    }

    public void setNotification(Notification notification) {
        this.notification = notification;
    }

    public NotificationDao getNotificationDao() {
        return notificationDao;
    }

    public void setNotificationDao(NotificationDao notificationDao) {
        this.notificationDao = notificationDao;
    }

    public List<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
    }

    public String getNumeratorUsername() {
        return numeratorUsername;
    }

    public void setNumeratorUsername(String numeratorUsername) {
        this.numeratorUsername = numeratorUsername;
    }

    public String getNumeratorPhone() {
        return numeratorPhone;
    }

    public void setNumeratorPhone(String numeratorPhone) {
        this.numeratorPhone = numeratorPhone;
    }

    public List<SupportData> getSupportDatas() {
        return supportDatas;
    }

    public void setSupportDatas(List<SupportData> supportDatas) {
        this.supportDatas = supportDatas;
    }

    public SupportData getSupportData() {
        return supportData;
    }

    public void setSupportData(SupportData supportData) {
        this.supportData = supportData;
    }

    public SupportDataDao getSupportDataDao() {
        return supportDataDao;
    }

    public void setSupportDataDao(SupportDataDao supportDataDao) {
        this.supportDataDao = supportDataDao;
    }

    public String getSuppName() {
        return suppName;
    }

    public void setSuppName(String suppName) {
        this.suppName = suppName;
    }

    public String getSuppType() {
        return suppType;
    }

    public void setSuppType(String suppType) {
        this.suppType = suppType;
    }

    public Date getSuppDueDate() {
        return suppDueDate;
    }

    public void setSuppDueDate(Date suppDueDate) {
        this.suppDueDate = suppDueDate;
    }

    public String getSuppValue() {
        return suppValue;
    }

    public void setSuppValue(String suppValue) {
        this.suppValue = suppValue;
    }

    public String getSupperName() {
        return supperName;
    }

    public void setSupperName(String supperName) {
        this.supperName = supperName;
    }

    public Integer getSuppQuantity() {
        return suppQuantity;
    }

    public void setSuppQuantity(Integer suppQuantity) {
        this.suppQuantity = suppQuantity;
    }

   

    public String getSuppFamCateg() {
        return suppFamCateg;
    }

    public void setSuppFamCateg(String suppFamCateg) {
        this.suppFamCateg = suppFamCateg;
    }

    public String getSupperPhone() {
        return supperPhone;
    }

    public void setSupperPhone(String supperPhone) {
        this.supperPhone = supperPhone;
    }

    public AllocatedDao getAlloDao() {
        return alloDao;
    }

    public void setAlloDao(AllocatedDao alloDao) {
        this.alloDao = alloDao;
    }

    public Integer getSuppVal() {
        return suppVal;
    }

    public void setSuppVal(Integer suppVal) {
        this.suppVal = suppVal;
    }

   

   

   
    
    
}
