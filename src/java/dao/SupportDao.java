/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.Support;
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
public class SupportDao extends GenericDao<Support>{
    
    public  List<Support> findEducationSupportAvailableByVillage(Integer village_id) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        String sql="select * from support where village_id='"+village_id+"' && status='Available' && supportCategory='Education Support'";
        Query q = s.createSQLQuery(sql);
        q.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        List<Support> presidents = q.list();
        s.close();
        return presidents;
        }
    
     public  List<Support> findEducationSupportAvailableByVillageAuthority(Integer village_id) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        String sql="select * from support where village_id='"+village_id+"' && supportCategory='Education Support'";
        Query q = s.createSQLQuery(sql);
        q.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        List<Support> presidents = q.list();
        s.close();
        return presidents;
        }
    
    public  List<Support> findHealthSupportAvailableByVillage(Integer village_id) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        String sql="select * from support where village_id='"+village_id+"' && status='Available' && supportCategory='Health Support'";
        Query q = s.createSQLQuery(sql);
        q.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        List<Support> presidents = q.list();
        s.close();
        return presidents;
        }
    
    public  List<Support> findHealthSupportAvailableByVillageAuthority(Integer village_id) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        String sql="select * from support where village_id='"+village_id+"' && supportCategory='Health Support'";
        Query q = s.createSQLQuery(sql);
        q.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        List<Support> presidents = q.list();
        s.close();
        return presidents;
        }
    
    public  List<Support> findEconomicSupportAvailableByVillage(Integer village_id) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        String sql="select * from support where village_id='"+village_id+"' && status='Available' && supportCategory='Economic Development Support'";
        Query q = s.createSQLQuery(sql);
        q.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        List<Support> presidents = q.list();
        s.close();
        return presidents;
        }
    
    public  List<Support> findEconomicSupportAvailableByVillageAuthority(Integer village_id) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        String sql="select * from support where village_id='"+village_id+"' && supportCategory='Economic Development Support'";
        Query q = s.createSQLQuery(sql);
        q.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        List<Support> presidents = q.list();
        s.close();
        return presidents;
        }
    
    public BigDecimal totalSupportValueByVillage(Integer id){
        Session s = HibernateUtil.getSessionFactory().openSession();
        String sql="select SUM(supportValue) as q from support where village_id='"+id+"'";
        Query q = s.createSQLQuery(sql);
        BigDecimal total = (BigDecimal) q.uniqueResult();
        s.close();
        return total;
		
    }
    
    public BigDecimal totalHealthSupportValueByVillage(Integer id){
        Session s = HibernateUtil.getSessionFactory().openSession();
        String sql="select SUM(supportValue) as q from support where village_id='"+id+"' && supportCategory='Health Support'";
        Query q = s.createSQLQuery(sql);
        BigDecimal total = (BigDecimal) q.uniqueResult();
        s.close();
        return total;
		
    }
    
    public BigDecimal totalHealthSupportQValueByVillage(Integer id){
        Session s = HibernateUtil.getSessionFactory().openSession();
        String sql="select SUM(supportValue) as q from support where village_id='"+id+"' && supportCategory='Health Support'";
        Query q = s.createSQLQuery(sql);
        BigDecimal total = (BigDecimal) q.uniqueResult();
        s.close();
        return total;
		
    }
    
    public BigDecimal totalEducationSupportValueByVillage(Integer id){
        Session s = HibernateUtil.getSessionFactory().openSession();
        String sql="select SUM(supportValue) as q from support where village_id='"+id+"' && supportCategory='Education Support'";
        Query q = s.createSQLQuery(sql);
        BigDecimal total = (BigDecimal) q.uniqueResult();
        s.close();
        return total;
		
    }
    
    public BigDecimal totalEconomicSupportValueByVillage(Integer id){
        Session s = HibernateUtil.getSessionFactory().openSession();
        String sql="select SUM(supportValue) as q from support where village_id='"+id+"' && supportCategory='Economic Development Support'";
        Query q = s.createSQLQuery(sql);
        BigDecimal total = (BigDecimal) q.uniqueResult();
        s.close();
        return total;
		
    }
    
}
