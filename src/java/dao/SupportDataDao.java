/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.SupportData;
import java.math.BigDecimal;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import utils.HibernateUtil;

/**
 *
 * @author Gasana
 */
public class SupportDataDao extends GenericDao<SupportData>{
    
    public  List<SupportData> findHealthClaimed(Integer village_id) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        String sql="select * from SupportData where village_id='"+village_id+"' && supportCategory='Health'";
        Query q = s.createSQLQuery(sql);
        q.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        List<SupportData> allocs = q.list();
        s.close();
        return allocs;
    }
    
    public  List<SupportData> findEducationClaimed(Integer village_id) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        String sql="select * from SupportData where village_id='"+village_id+"' && supportCategory='Education'";
        Query q = s.createSQLQuery(sql);
        q.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        List<SupportData> allocs = q.list();
        s.close();
        return allocs;
    }
    
    public  List<SupportData> findEconomicClaimed(Integer village_id) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        String sql="select * from SupportData where village_id='"+village_id+"' && supportCategory='Economic'";
        Query q = s.createSQLQuery(sql);
        q.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        List<SupportData> allocs = q.list();
        s.close();
        return allocs;
    }
    
    public  List<SupportData> findHealthClaimedHINone(Integer village_id) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        String sql="select * from SupportData where village_id='"+village_id+"' && supportCategory='Health' && healthInsuranceCard='None'";
        Query q = s.createSQLQuery(sql);
        q.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        List<SupportData> allocs = q.list();
        s.close();
        return allocs;
    }
    
    public  List<SupportData> findHealthClaimedHIMituelle(Integer village_id) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        String sql="select * from SupportData where village_id='"+village_id+"' && supportCategory='Health' && healthInsuranceCard='Mituelle'";
        Query q = s.createSQLQuery(sql);
        q.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        List<SupportData> allocs = q.list();
        s.close();
        return allocs;
    }
    
    public  List<SupportData> findHealthClaimedHIRama(Integer village_id) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        String sql="select * from SupportData where village_id='"+village_id+"' && supportCategory='Health' && healthInsuranceCard='Rama'";
        Query q = s.createSQLQuery(sql);
        q.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        List<SupportData> allocs = q.list();
        s.close();
        return allocs;
    }
    
    public  List<SupportData> findHealthClaimedDisabMale(Integer village_id) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        String sql="select * from SupportData where village_id='"+village_id+"' && supportCategory='Health' && gender='Male' && disabled='Yes'";
        Query q = s.createSQLQuery(sql);
        q.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        List<SupportData> allocs = q.list();
        s.close();
        return allocs;
    }
    
    public  List<SupportData> findHealthClaimedDisabFemale(Integer village_id) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        String sql="select * from SupportData where village_id='"+village_id+"' && supportCategory='Health' && gender='Female' && disabled='Yes'";
        Query q = s.createSQLQuery(sql);
        q.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        List<SupportData> allocs = q.list();
        s.close();
        return allocs;
    }
    
    public  List<SupportData> findHealthClaimedDisasFemale(Integer village_id) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        String sql="select * from SupportData where village_id='"+village_id+"' && supportCategory='Health' && gender='Female' && diseases='Yes'";
        Query q = s.createSQLQuery(sql);
        q.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        List<SupportData> allocs = q.list();
        s.close();
        return allocs;
    }
    public  List<SupportData> findHealthClaimedDisasMale(Integer village_id) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        String sql="select * from SupportData where village_id='"+village_id+"' && supportCategory='Health' && gender='Male' && diseases='Yes'";
        Query q = s.createSQLQuery(sql);
        q.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        List<SupportData> allocs = q.list();
        s.close();
        return allocs;
    }
    
    public  List<SupportData> findEducationClaimedEDNoneMale(Integer village_id) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        String sql="select * from SupportData where village_id='"+village_id+"' && supportCategory='Education' && gender='Male' && educationLevel='None'";
        Query q = s.createSQLQuery(sql);
        q.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        List<SupportData> allocs = q.list();
        s.close();
        return allocs;
    }
    public  List<SupportData> findEducationClaimedEDNoneFemale(Integer village_id) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        String sql="select * from SupportData where village_id='"+village_id+"' && supportCategory='Education' && gender='Female' && educationLevel='None'";
        Query q = s.createSQLQuery(sql);
        q.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        List<SupportData> allocs = q.list();
        s.close();
        return allocs;
    }
    
     public  List<SupportData> findEducationClaimedEDPrimaryMale(Integer village_id) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        String sql="select * from SupportData where village_id='"+village_id+"' && supportCategory='Education' && gender='Male' && educationLevel='Primary'";
        Query q = s.createSQLQuery(sql);
        q.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        List<SupportData> allocs = q.list();
        s.close();
        return allocs;
    }
    public  List<SupportData> findEducationClaimedEDPrimaryFemale(Integer village_id) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        String sql="select * from SupportData where village_id='"+village_id+"' && supportCategory='Education' && gender='Female' && educationLevel='Primary'";
        Query q = s.createSQLQuery(sql);
        q.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        List<SupportData> allocs = q.list();
        s.close();
        return allocs;
    }
    
     public  List<SupportData> findEducationClaimedEDSecondaryMale(Integer village_id) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        String sql="select * from SupportData where village_id='"+village_id+"' && supportCategory='Education' && gender='Male' && educationLevel='Secondary'";
        Query q = s.createSQLQuery(sql);
        q.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        List<SupportData> allocs = q.list();
        s.close();
        return allocs;
    }
    public  List<SupportData> findEducationClaimedEDSecondaryFemale(Integer village_id) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        String sql="select * from SupportData where village_id='"+village_id+"' && supportCategory='Education' && gender='Female' && educationLevel='Secondary'";
        Query q = s.createSQLQuery(sql);
        q.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        List<SupportData> allocs = q.list();
        s.close();
        return allocs;
    }
    
    public  List<SupportData> findEconomicClaimedECJobYes(Integer village_id) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        String sql="select * from SupportData where village_id='"+village_id+"' && supportCategory='Economic' && jobstatus='Yes'";
        Query q = s.createSQLQuery(sql);
        q.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        List<SupportData> allocs = q.list();
        s.close();
        return allocs;
    }
     public  List<SupportData> findEconomicClaimedECJobNo(Integer village_id) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        String sql="select * from SupportData where village_id='"+village_id+"' && supportCategory='Economic' && jobstatus='No'";
        Query q = s.createSQLQuery(sql);
        q.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        List<SupportData> allocs = q.list();
        s.close();
        return allocs;
    }
     
    public  List<SupportData> findEconomicClaimedECHY(Integer village_id) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        String sql="select * from SupportData where village_id='"+village_id+"' && supportCategory='Economic' && ownHouse='Yes'";
        Query q = s.createSQLQuery(sql);
        q.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        List<SupportData> allocs = q.list();
        s.close();
        return allocs;
    }
    public  List<SupportData> findEconomicClaimedECHN(Integer village_id) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        String sql="select * from SupportData where village_id='"+village_id+"' && supportCategory='Economic' && ownHouse='No'";
        Query q = s.createSQLQuery(sql);
        q.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        List<SupportData> allocs = q.list();
        s.close();
        return allocs;
    }
    
    public  List<SupportData> findEconomicClaimedECBY(Integer village_id) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        String sql="select * from SupportData where village_id='"+village_id+"' && supportCategory='Economic' && ownBusiness='Yes'";
        Query q = s.createSQLQuery(sql);
        q.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        List<SupportData> allocs = q.list();
        s.close();
        return allocs;
    }
    public  List<SupportData> findEconomicClaimedECBN(Integer village_id) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        String sql="select * from SupportData where village_id='"+village_id+"' && supportCategory='Economic' && ownBusiness='No'";
        Query q = s.createSQLQuery(sql);
        q.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        List<SupportData> allocs = q.list();
        s.close();
        return allocs;
    } 
    
    public  List<SupportData> findAllClaimedSuppors(Integer resident_id) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        String sql="select * from SupportData where resident_id='"+resident_id+"'";
        Query q = s.createSQLQuery(sql);
        q.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        List<SupportData> pnum = q.list();
        s.close();
        return pnum;
        }
    
    public  List<SupportData> findAllClaimedByVillage(Integer village_id) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        String sql="select * from SupportData where village_id='"+village_id+"'";
        Query q = s.createSQLQuery(sql);
        q.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        List<SupportData> pnum = q.list();
        s.close();
        return pnum;
        }
    
    public  List<SupportData> findHealthClaimedByVillage(Integer village_id) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        String sql="select * from SupportData where village_id='"+village_id+"' && supportCategory='Health'";
        Query q = s.createSQLQuery(sql);
        q.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        List<SupportData> pnum = q.list();
        s.close();
        return pnum;
        }
    
    public  List<SupportData> findEducationClaimedByVillage(Integer village_id) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        String sql="select * from SupportData where village_id='"+village_id+"' && supportCategory='Education'";
        Query q = s.createSQLQuery(sql);
        q.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        List<SupportData> pnum = q.list();
        s.close();
        return pnum;
        }
    
    public  List<SupportData> findEonomicClaimedByVillage(Integer village_id) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        String sql="select * from SupportData where village_id='"+village_id+"' && supportCategory='Economic'";
        Query q = s.createSQLQuery(sql);
        q.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        List<SupportData> pnum = q.list();
        s.close();
        return pnum;
        }
    
    public SupportData getSupportDataByKey(String key) {
		Session ss = HibernateUtil.getSessionFactory().openSession();
		Query sql = ss.createQuery("FROM SupportData where residentkey = ?");
		sql.setString(0, key);
		SupportData sd = (SupportData) sql.uniqueResult();
		ss.close();
		return sd;
	}
    
    
    public BigDecimal totalHealthClaimedBudget(Integer village_id){
        Session s = HibernateUtil.getSessionFactory().openSession();
        String sql="select SUM(supportValue) as q from supportdata where village_id='"+village_id+"' && supportCategory='Health'";
        Query q = s.createSQLQuery(sql);
        BigDecimal total = (BigDecimal) q.uniqueResult();
        s.close();
        return total;
		
    }
    
    public BigDecimal totalEducationClaimedBudget(Integer village_id){
        Session s = HibernateUtil.getSessionFactory().openSession();
        String sql="select SUM(supportValue) as q from supportdata where village_id='"+village_id+"' && supportCategory='Education'";
        Query q = s.createSQLQuery(sql);
        BigDecimal total = (BigDecimal) q.uniqueResult();
        s.close();
        return total;
		
    }
    
    public BigDecimal totalEconomicClaimedBudget(Integer village_id){
        Session s = HibernateUtil.getSessionFactory().openSession();
        String sql="select SUM(supportValue) as q from supportdata where village_id='"+village_id+"' && supportCategory='Economic'";
        Query q = s.createSQLQuery(sql);
        BigDecimal total = (BigDecimal) q.uniqueResult();
        s.close();
        return total;
		
    }


    public BigDecimal totalHealthQSupportValueByVillage(Integer id){
        Session s = HibernateUtil.getSessionFactory().openSession();
        String sql="select SUM(quantity) as q from supportdata where resident_id='"+id+"' && supportCategory='Health'";
        Query q = s.createSQLQuery(sql);
        BigDecimal total = (BigDecimal) q.uniqueResult();
        s.close();
        return total;
		
    }
    
    public BigDecimal totalHealthSupportDataValueByResident(Integer id){
        Session s = HibernateUtil.getSessionFactory().openSession();
        String sql="select SUM(supportValue) as q from supportdata where resident_id='"+id+"' && supportCategory='Health'";
        Query q = s.createSQLQuery(sql);
        BigDecimal total = (BigDecimal) q.uniqueResult();
        s.close();
        return total;
		
    }
    
    public BigDecimal totalEducationSupportDataValueByResident(Integer id){
        Session s = HibernateUtil.getSessionFactory().openSession();
        String sql="select SUM(supportValue) as q from supportdata where resident_id='"+id+"' && supportCategory='Education'";
        Query q = s.createSQLQuery(sql);
        BigDecimal total = (BigDecimal) q.uniqueResult();
        s.close();
        return total;	
    }
    
    public BigDecimal totalEconomicSupportDataValueByResident(Integer id){
        Session s = HibernateUtil.getSessionFactory().openSession();
        String sql="select SUM(supportValue) as q from supportdata where resident_id='"+id+"' && supportCategory='Economic'";
        Query q = s.createSQLQuery(sql);
        BigDecimal total = (BigDecimal) q.uniqueResult();
        s.close();
        return total;	
    }
    
    public BigDecimal totalEducationQSupportValueByVillage(Integer id){
        Session s = HibernateUtil.getSessionFactory().openSession();
        String sql="select SUM(quantity) as q from supportdata where resident_id='"+id+"' && supportCategory='Education'";
        Query q = s.createSQLQuery(sql);
        BigDecimal total = (BigDecimal) q.uniqueResult();
        s.close();
        return total;
		
    }
    
    public BigDecimal totalEconomicQSupportValueByVillage(Integer id){
        Session s = HibernateUtil.getSessionFactory().openSession();
        String sql="select SUM(quantity) as q from supportdata where resident_id='"+id+"' && supportCategory='Economic'";
        Query q = s.createSQLQuery(sql);
        BigDecimal total = (BigDecimal) q.uniqueResult();
        s.close();
        return total;
		
    } 
}
