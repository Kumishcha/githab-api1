package by.htp.main.service;

import java.util.List;

import by.htp.main.entity.User;
import by.htp.main.service.impl.ServiceException;

public interface UserService {

	User findUserByLogin(String username) throws ServiceException;

	User authorization(String username, String password) throws ServiceException;

	User registration(String username, String password) throws ServiceException;

	List<User> viewAllUsers() throws ServiceException;

	User findUserByToken(String token) throws ServiceException;

}
