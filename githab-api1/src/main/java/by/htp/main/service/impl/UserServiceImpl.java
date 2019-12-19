package by.htp.main.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import by.htp.main.dao.UserDAO;
import by.htp.main.dao.impl.DAOException;
import by.htp.main.entity.User;
import by.htp.main.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDao;

	@Override
	@Transactional
	public User findUserByLogin(String username) throws ServiceException {

		User user;
		try {
			user = userDao.findUserByLogin(username);

		} catch (DAOException e) {
			throw new ServiceException(e);
		}

		return user;
	}

	@Override
	@Transactional
	public User authorization(String username, String password) throws ServiceException {

		User user;
		String newPassword;

		try {
			newPassword = hashPassword(password);
			user = userDao.authorization(username, newPassword);

		} catch (DAOException e) {
			throw new ServiceException(e);
		}

		return user;
	}

	@Override
	@Transactional
	public User registration(String username, String password) throws ServiceException {

		String newPassword;
		User user;

		newPassword = hashPassword(password);

		try {

			user = userDao.registration(username, newPassword);

		} catch (DAOException e) {
			throw new ServiceException(e);
		}

		return user;
	}

	@Override
	@Transactional
	public List<User> viewAllUsers() throws ServiceException {

		List<User> users;

		try {
			users = userDao.viewAllUsers();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return users;
	}

	private String hashPassword(String password) {

		String newPassword = password + "1";
		return newPassword;

	}

	@Override
	@Transactional
	public User findUserByToken(String token) throws ServiceException {
		User user;
		try {
			user = userDao.findUserByToken(token);

		} catch (DAOException e) {
			throw new ServiceException(e);
		}

		return user;
	}

}
