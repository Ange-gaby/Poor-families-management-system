/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.Notification;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import utils.HibernateUtil;

/**
 *
 * @author Gasana
 */
public class NotificationDao extends GenericDao<Notification>{
    public  List<Notification> findUnreadNotificationByVillage(Integer village_id) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        String sql="select * from notification where village_id='"+village_id+"' && status ='sent'";
        Query q = s.createSQLQuery(sql);
        q.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        List<Notification> msgs = q.list();
        s.close();
        return msgs;
        }
    public  List<Notification> findAllNotificationByVillage(Integer village_id) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        String sql="select * from notification where village_id='"+village_id+"'";
        Query q = s.createSQLQuery(sql);
        q.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        List<Notification> msgs = q.list();
        s.close();
        return msgs;
        }
    
    public Notification seenNotification(Integer id) {
		Session ss = HibernateUtil.getSessionFactory().openSession();
		Query sql = ss.createQuery("FROM Notification where id = ?");
		sql.setInteger(0, id);
		Notification msg = (Notification) sql.uniqueResult();
		ss.close();
		return msg;
	}
}
