/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.Allocated;
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
public class AllocatedDao extends GenericDao<Allocated>{
    public  List<Allocated> findHealthAllocated(Integer village_id) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        String sql="select * from allocated where village_id='"+village_id+"' && status ='Health' && supportStatus='active'";
        Query q = s.createSQLQuery(sql);
        q.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        List<Allocated> allocs = q.list();
        s.close();
        return allocs;
    }
    
    public  List<Allocated> findSupportByUser(Integer resident_id) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        String sql="select * from allocated where resident_id='"+resident_id+"' && status='active'";
        Query q = s.createSQLQuery(sql);
        q.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        List<Allocated> allocs = q.list();
        s.close();
        return allocs;
    }
    
     public  List<Allocated> findAllAllocated(Integer village_id) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        String sql="select * from allocated where village_id='"+village_id+"'";
        Query q = s.createSQLQuery(sql);
        q.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        List<Allocated> allocs = q.list();
        s.close();
        return allocs;
        }
      public  List<Allocated> findAllAllocatedByHealth(Integer village_id) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        String sql="select * from allocated where village_id='"+village_id+"' && status='Health'";
        Query q = s.createSQLQuery(sql);
        q.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        List<Allocated> allocs = q.list();
        s.close();
        return allocs;
        }
       public  List<Allocated> findAllAllocatedByEducation(Integer village_id) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        String sql="select * from allocated where village_id='"+village_id+"' && status='Education'";
        Query q = s.createSQLQuery(sql);
        q.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        List<Allocated> allocs = q.list();
        s.close();
        return allocs;
        }
       public  List<Allocated> findAllAllocatedByEconomic(Integer village_id) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        String sql="select * from allocated where village_id='"+village_id+"' && status='Economic'";
        Query q = s.createSQLQuery(sql);
        q.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        List<Allocated> allocs = q.list();
        s.close();
        return allocs;
        }
      public  List<Allocated> findResidentInHealth(Integer village_id,Integer rid) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        String sql="select * from allocated where village_id='"+village_id+"' && resident_id='"+rid+"' && status='Health'";
        Query q = s.createSQLQuery(sql);
        q.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        List<Allocated> allocs = q.list();
        s.close();
        return allocs;
      }
        
       public  List<Allocated> findResidentInEducation(Integer village_id,Integer rid) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        String sql="select * from allocated where village_id='"+village_id+"' && resident_id='"+rid+"' && status='Education'";
        Query q = s.createSQLQuery(sql);
        q.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        List<Allocated> allocs = q.list();
        s.close();
        return allocs;
        }
        public  List<Allocated> findResidentInEconomic(Integer village_id,Integer rid) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        String sql="select * from allocated where village_id='"+village_id+"' && resident_id='"+rid+"' && status='Economic'";
        Query q = s.createSQLQuery(sql);
        q.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        List<Allocated> allocs = q.list();
        s.close();
        return allocs;
        }  
    
     public  List<Allocated> findCanceledAllocated(Integer village_id) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        String sql="select * from allocated where village_id='"+village_id+"' && supportStatus ='Canceled'";
        Query q = s.createSQLQuery(sql);
        q.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        List<Allocated> allocs = q.list();
        s.close();
        return allocs;
        }
      public  List<Allocated> findRichAllocated(Integer village_id) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        String sql="select * from allocated where village_id='"+village_id+"' && status ='Rich' && supportStatus='active'";
        Query q = s.createSQLQuery(sql);
        q.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        List<Allocated> allocs = q.list();
        s.close();
        return allocs;
        }
    
     public  List<Allocated> findEducationAllocated(Integer village_id) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        String sql="select * from allocated where village_id='"+village_id+"' && status ='Education' && supportStatus='active'";
        Query q = s.createSQLQuery(sql);
        q.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        List<Allocated> allocs = q.list();
        s.close();
        return allocs;
        }
     
     public  List<Allocated> findEconomicAllocated(Integer village_id) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        String sql="select * from allocated where village_id='"+village_id+"' && status ='Economic' && supportStatus='active'";
        Query q = s.createSQLQuery(sql);
        q.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        List<Allocated> allocs = q.list();
        s.close();
        return allocs;
        }
     
     public  List<Allocated> findAllSupportActive(Integer village_id) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        String sql="select * from allocated where village_id='"+village_id+"' && supportStatus ='active'";
        Query q = s.createSQLQuery(sql);
        q.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        List<Allocated> allocs = q.list();
        s.close();
        return allocs;
        }
     
      public  List<Allocated> findAllSupportCanceled(Integer village_id) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        String sql="select * from allocated where village_id='"+village_id+"' && supportStatus ='canceled'";
        Query q = s.createSQLQuery(sql);
        q.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        List<Allocated> allocs = q.list();
        s.close();
        return allocs;
        }
     
     public BigDecimal totalPriceOrderAcceptedByFood(){
        Session s = HibernateUtil.getSessionFactory().openSession();
        String sql="select SUM() as q from customerorder where category='Food' && orderStatus='Accepted'";
        Query q = s.createSQLQuery(sql);
        BigDecimal total = (BigDecimal) q.uniqueResult();
        s.close();
        return total;
		
    }
     
     public Allocated getAllocatedById(Integer id) {
		Session ss = HibernateUtil.getSessionFactory().openSession();
		Query sql = ss.createQuery("FROM Allocated where id = ?");
		sql.setInteger(0, id);
		Allocated all = (Allocated) sql.uniqueResult();
		ss.close();
		return all;
	}
     
     public Allocated getAllocatedByResidentId(Integer id) {
		Session ss = HibernateUtil.getSessionFactory().openSession();
		Query sql = ss.createQuery("FROM Allocated where resident_id = ? ");
		sql.setInteger(0, id);
		Allocated all = (Allocated) sql.uniqueResult();
		ss.close();
		return all;
	}
     
     public Allocated getAllocatedByResidentIdHealth(Integer id) {
		Session ss = HibernateUtil.getSessionFactory().openSession();
		Query sql = ss.createQuery("FROM Allocated where resident_id = ? and status='Health' ");
		sql.setInteger(0, id);
		Allocated all = (Allocated) sql.uniqueResult();
		ss.close();
		return all;
	}
     
     public Allocated getAllocatedByResidentIdEducation(Integer id) {
		Session ss = HibernateUtil.getSessionFactory().openSession();
		Query sql = ss.createQuery("FROM Allocated where resident_id = ? and status='Education' ");
		sql.setInteger(0, id);
		Allocated all = (Allocated) sql.uniqueResult();
		ss.close();
		return all;
	}
     
     public Allocated getAllocatedByResidentIdEconomic(Integer id) {
		Session ss = HibernateUtil.getSessionFactory().openSession();
		Query sql = ss.createQuery("FROM Allocated where resident_id = ? and status='Economic' ");
		sql.setInteger(0, id);
		Allocated all = (Allocated) sql.uniqueResult();
		ss.close();
		return all;
	}
     
 
     
      public Allocated getAllocatedByResidentIdByHealth(Integer id) {
		Session ss = HibernateUtil.getSessionFactory().openSession();
		Query sql = ss.createQuery("FROM Allocated where resident_id = ? AND status='Health'");
		sql.setInteger(0, id);
		Allocated all = (Allocated) sql.uniqueResult();
		ss.close();
		return all;
	}
      public Allocated getAllocatedByResidentIdByEconomic(Integer id) {
		Session ss = HibernateUtil.getSessionFactory().openSession();
		Query sql = ss.createQuery("FROM Allocated where resident_id = ? AND status='Economic'");
		sql.setInteger(0, id);
		Allocated all = (Allocated) sql.uniqueResult();
		ss.close();
		return all;
	}
      public Allocated getAllocatedByResidentIdByEducation(Integer id) {
		Session ss = HibernateUtil.getSessionFactory().openSession();
		Query sql = ss.createQuery("FROM Allocated where resident_id = ? AND status='Education'");
		sql.setInteger(0, id);
		Allocated all = (Allocated) sql.uniqueResult();
		ss.close();
		return all;
	}
       public Allocated getAllocatedByResidentIdByRich(Integer id) {
		Session ss = HibernateUtil.getSessionFactory().openSession();
		Query sql = ss.createQuery("FROM Allocated where resident_id = ? AND status='Rich'");
		sql.setInteger(0, id);
		Allocated all = (Allocated) sql.uniqueResult();
		ss.close();
		return all;
	}
    
}
