package by.htp.main.dao.impl;

import java.util.List;
import java.util.Random;

import javax.persistence.PersistenceException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import by.htp.main.dao.UserDAO;
import by.htp.main.entity.User;

@Repository
public class SQLUserDAO implements UserDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public User findUserByLogin(String username) throws DAOException {

		List<User> users;
		User user;

		Session currentSession = sessionFactory.getCurrentSession();

		try {
			users = (List<User>) currentSession.createQuery("from User where username= :paramName")
					.setParameter("paramName", username).list();

			if (users.isEmpty()) {
				return null;
			} else {
				user = users.get(0);
				return user;
			}
		} catch (PersistenceException e) {

			throw new DAOException(e);

		}
	}

	@Override
	public User registration(String username, String newPassword) throws DAOException {

		Session currentSession = sessionFactory.getCurrentSession();

		User newUser = new User();

		try {

			newUser.setUsername(username);
			newUser.setPassword(newPassword);

			currentSession.saveOrUpdate(newUser);

			newUser.setToken(generateToken());

		} catch (PersistenceException e) {

			throw new DAOException(e);

		}
		return newUser;
	}

	@SuppressWarnings("unchecked")
	@Override
	public User authorization(String username, String password) throws DAOException {

		List<User> users;
		User user;
		
		Session currentSession = sessionFactory.getCurrentSession();

		try {
			users = (List<User>) currentSession
					.createQuery("from User where username= :paramName and password= :paramPassword")
					.setParameter("paramName", username).setParameter("paramPassword", password).list();

			if (users.isEmpty()) {
				return null;
			} else {
				user = users.get(0);
				user.setToken(generateToken());
				return user;
			}
		} catch (PersistenceException e) {

			throw new DAOException(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> viewAllUsers() throws DAOException {

		List<User> users;

		Session currentSession = sessionFactory.getCurrentSession();

		try {
			users = (List<User>) currentSession.createQuery("from User").list();

			if (users.isEmpty()) {
				return null;
			}
		} catch (PersistenceException e) {

			throw new DAOException(e);
		}
		return users;
	}

	private String generateToken() {

		String symbols = "abcdefghijklmnopqrstuvwxyz";

		int length = 10;
		Random r = new Random();
		StringBuilder str = new StringBuilder();

		for (int i = 0; i < length; i++) {
			str.append(symbols.charAt(r.nextInt(symbols.length() - 1)));
		}

		return str.toString();

	}

	@SuppressWarnings("unchecked")
	@Override
	public User findUserByToken(String token) throws DAOException {
		List<User> users;
		User user;

		Session currentSession = sessionFactory.getCurrentSession();

		try {
			users = (List<User>) currentSession.createQuery("from User where token= :paramName")
					.setParameter("paramName", token).list();

			if (users.isEmpty()) {
				return null;
			} else {
				user = users.get(0);
				return user;
			}
		} catch (PersistenceException e) {

			throw new DAOException(e);

		}
	}

}
