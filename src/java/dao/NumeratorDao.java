/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.Numerator;
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
public class NumeratorDao extends GenericDao<Numerator>{
     public  List<Numerator> findNumeratorsByVillage(Integer village_id) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        String sql="select * from numerator where village_id='"+village_id+"' && status ='Active'";
        Query q = s.createSQLQuery(sql);
        q.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        List<Numerator> pnum = q.list();
        s.close();
        return pnum;
        }
     
      public  List<Numerator> findDisabledNumeratorsByVillage(Integer village_id) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        String sql="select * from numerator where village_id='"+village_id+"' && status ='Disabled'";
        Query q = s.createSQLQuery(sql);
        q.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        List<Numerator> pnum = q.list();
        s.close();
        return pnum;
        }
     
         public Numerator getNumeratorById(Integer id) {
		Session ss = HibernateUtil.getSessionFactory().openSession();
		Query sql = ss.createQuery("FROM Numerator num where num.id = ?");
		sql.setInteger(0, id);
		Numerator num = (Numerator) sql.uniqueResult();
		ss.close();
		return num;
	}
         
         public Numerator getNumeratorByVillageId(Integer id) {
		Session ss = HibernateUtil.getSessionFactory().openSession();
		Query sql = ss.createQuery("FROM Numerator where village_id = ?");
		sql.setInteger(0, id);
		Numerator num = (Numerator) sql.uniqueResult();
		ss.close();
		return num;
	}
         
       
}
