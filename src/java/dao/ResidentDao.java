/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.Resident;
import java.util.HashMap;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import utils.HibernateUtil;

/**
 *
 * @author Gasana
 */
public class ResidentDao extends GenericDao<Resident>{
        public  List<Resident> findPendingResidentsByVillage(Integer village_id) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        String sql="select * from resident where village_id='"+village_id+"' && residentStatus='Allocated'";
        Query q = s.createSQLQuery(sql);
        q.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        List<Resident> presidents = q.list();
        s.close();
        return presidents;
        }
        
        public  List<Resident> findCanceledResidentsByVillage(Integer village_id) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        String sql="select * from resident where village_id='"+village_id+"' && residentStatus='canceled'";
        Query q = s.createSQLQuery(sql);
        q.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        List<Resident> presidents = q.list();
        s.close();
        return presidents;
        }
        public  List<Resident> findResidentsByVillage(Integer village_id) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        String sql="select * from resident where village_id='"+village_id+"'";
        Query q = s.createSQLQuery(sql);
        q.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        List<Resident> presidents = q.list();
        s.close();
        return presidents;
        }
       
        
        public  List<Resident> findFemaleResidentsByVillage(Integer village_id) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        String sql="select * from resident where village_id='"+village_id+"' && gender='Female'";
        Query q = s.createSQLQuery(sql);
        q.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        List<Resident> presidents = q.list();
        s.close();
        return presidents;
        }
        
        
        public  List<Resident> findSingleResidentsByVillage(Integer village_id) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        String sql="select * from resident where village_id='"+village_id+"' && martialStatus='Single'";
        Query q = s.createSQLQuery(sql);
        q.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        List<Resident> presidents = q.list();
        s.close();
        return presidents;
        }
        
         public  List<Resident> findMarriedResidentsByVillage(Integer village_id) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        String sql="select * from resident where village_id='"+village_id+"' && martialStatus='Married'";
        Query q = s.createSQLQuery(sql);
        q.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        List<Resident> presidents = q.list();
        s.close();
        return presidents;
        }
         
        public  List<Resident> findJoblessResidentsByVillage(Integer village_id) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        String sql="select * from resident where village_id='"+village_id+"' && jobStatus='No'";
        Query q = s.createSQLQuery(sql);
        q.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        List<Resident> presidents = q.list();
        s.close();
        return presidents;
        }
        
        public  List<Resident> findCat1ResidentsByVillage(Integer village_id) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        String sql="select * from resident where village_id='"+village_id+"' && category='Category I'";
        Query q = s.createSQLQuery(sql);
        q.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        List<Resident> presidents = q.list();
        s.close();
        return presidents;
        }
        
        public  List<Resident> findCat2ResidentsByVillage(Integer village_id) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        String sql="select * from resident where village_id='"+village_id+"' && category='Category II'";
        Query q = s.createSQLQuery(sql);
        q.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        List<Resident> presidents = q.list();
        s.close();
        return presidents;
        }
        
        public  List<Resident> findCat3ResidentsByVillage(Integer village_id) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        String sql="select * from resident where village_id='"+village_id+"' && category='Category III'";
        Query q = s.createSQLQuery(sql);
        q.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        List<Resident> presidents = q.list();
        s.close();
        return presidents;
        }
        
        public  List<Resident> findCat4ResidentsByVillage(Integer village_id) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        String sql="select * from resident where village_id='"+village_id+"' && category='Category IV'";
        Query q = s.createSQLQuery(sql);
        q.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        List<Resident> presidents = q.list();
        s.close();
        return presidents;
        }
        
        public  List<Resident> findHealthInsurancesResidentsByVillage(Integer village_id) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        String sql="select * from resident where village_id='"+village_id+"' && healthInsurance='None'";
        Query q = s.createSQLQuery(sql);
        q.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        List<Resident> presidents = q.list();
        s.close();
        return presidents;
        }
        
        public  List<Resident> findMaleResidentsByVillage(Integer village_id) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        String sql="select * from resident where village_id='"+village_id+"' && gender='Male'";
        Query q = s.createSQLQuery(sql);
        q.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        List<Resident> presidents = q.list();
        s.close();
        return presidents;
        }
        
        
        public  List<Resident> findActiveAllocatesByVillage(Integer village_id) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        String sql="select * from resident where village_id='"+village_id+"' && gender='Male'";
        Query q = s.createSQLQuery(sql);
        q.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        List<Resident> presidents = q.list();
        s.close();
        return presidents;
        }
        
         
        public Resident getResidentByCode(String code) {
		Session ss = HibernateUtil.getSessionFactory().openSession();
		Query sql = ss.createQuery("FROM Resident r where r.code = ?");
		sql.setString(0, code);
		Resident res = (Resident) sql.uniqueResult();
		ss.close();
		return res;
	}
}
