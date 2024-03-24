/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.User;
import org.hibernate.Query;
import org.hibernate.Session;
import utils.HibernateUtil;

/**
 *
 * @author Gasana
 */
public class UserDao extends GenericDao<User>{
    public User getUsername(String username) {
		Session ss = HibernateUtil.getSessionFactory().openSession();
		Query sql = ss.createQuery("FROM User u where u.username = ?");
		sql.setString(0, username);
		User use = (User) sql.uniqueResult();
		ss.close();
		return use;
	}
    
    public User getNumerator(Integer id) {
		Session ss = HibernateUtil.getSessionFactory().openSession();
		Query sql = ss.createQuery("FROM User u where u.numerator_id = ?");
		sql.setInteger(0, id);
		User use = (User) sql.uniqueResult();
		ss.close();
		return use;
	}
}
