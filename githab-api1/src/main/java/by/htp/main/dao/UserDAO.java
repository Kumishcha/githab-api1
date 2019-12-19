package by.htp.main.dao;

import java.util.List;


import by.htp.main.dao.impl.DAOException;
import by.htp.main.entity.User;

public interface UserDAO {

	User findUserByLogin(String username) throws DAOException;

	User registration(String username, String newPassword) throws DAOException;

	User authorization(String username, String password) throws DAOException;
	
	List<User> viewAllUsers() throws DAOException;
	
	User findUserByToken(String token) throws DAOException;
	
}
