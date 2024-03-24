/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.Message;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import utils.HibernateUtil;

/**
 *
 * @author Gasana
 */
public class MessageDao extends GenericDao<Message>{
    
    public  List<Message> findUnreadMessagesByVillage(Integer village_id) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        String sql="select * from message where village_id='"+village_id+"' && messageStatus ='unread'";
        Query q = s.createSQLQuery(sql);
        q.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        List<Message> msgs = q.list();
        s.close();
        return msgs;
        }
    
     public  List<Message> findReadMessagesByVillage(Integer village_id) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        String sql="select * from message where village_id='"+village_id+"' && messageStatus ='read'";
        Query q = s.createSQLQuery(sql);
        q.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        List<Message> msgs = q.list();
        s.close();
        return msgs;
        }
      public  List<Message> findResidentUnreadMessagesByVillage(Integer id) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        String sql="select * from message where resident_id='"+id+"' && messageStatus ='response'";
        Query q = s.createSQLQuery(sql);
        q.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        List<Message> msgs = q.list();
        s.close();
        return msgs;
        }
     
     public Message getMessageBySenderCode(String code) {
		Session ss = HibernateUtil.getSessionFactory().openSession();
		Query sql = ss.createQuery("FROM Message where senderCode = ?");
		sql.setString(0, code);
		Message msg = (Message) sql.uniqueResult();
		ss.close();
		return msg;
	}

        
    
}
