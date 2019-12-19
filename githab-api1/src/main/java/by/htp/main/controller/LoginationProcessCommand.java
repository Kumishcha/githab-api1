package by.htp.main.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import by.htp.main.entity.User;
import by.htp.main.entity.Version;
import by.htp.main.service.UserService;
import by.htp.main.service.impl.ServiceException;

@Controller
public class LoginationProcessCommand {

	@ExceptionHandler(MyException.class)
	public ModelAndView handleBadFileNameException(HttpServletRequest request, MyException e) {

		ModelAndView modelAndView = new ModelAndView("error");
		modelAndView.addObject("error", e.getMessage());
		return modelAndView;
	}

	@Autowired
	private UserService userService;

	@RequestMapping("/showLoginForm")
	public String showForm(Model theModel) throws MyException {

			User user = new User();
			Version version = new Version();
			
			version.setName("githab-api");
			version.setVersion("1.0");
			version.setDate(new Date());

			theModel.addAttribute("user", user);
			theModel.addAttribute("version", version);

		return "athorization";
	}
	
	@RequestMapping("/go-to-registration-page")
	public String goToRegistrationPageCommand() {

		return "registration";
	}

	@RequestMapping("/go-to-athorization-page")
	public String goToAthorizationPageCommand() {

		return "athorization";
	}

	@GetMapping("/authorization")
	public String authorizationCommand(@ModelAttribute("user") User user, Model model) throws MyException {

		try {
			
			user = userService.authorization(user.getUsername(), user.getPassword());
			
			model.addAttribute("user", user);

			if (user != null) {
				return "client";
			} else {
				model.addAttribute("message", "Try logging again ");
				return "athorization";
			}

		} catch (ServiceException e) {
			throw new MyException(e);
		}
	}

	@GetMapping("/view users list")
	public String viewUsersList(Model model) throws MyException {

		List<User> users;
		try {
			users = userService.viewAllUsers();
			model.addAttribute("users", users);
		} catch (ServiceException e) {
			throw new MyException(e);
		}
		return "athorization";
	}

	@GetMapping("/connected")
	public String connected(@RequestParam("token") String token, Model model) throws MyException {

		User user;
		try {
			user = userService.findUserByToken(token);

			model.addAttribute("user", user);
			model.addAttribute("messageSuccessful", "You have successfully connected");

		} catch (ServiceException e) {
			throw new MyException(e);
		}
		return "connected";
	}

	@GetMapping("/registration")
	public String saveUser(@RequestParam("username") String username, @RequestParam("password") String password,
			@RequestParam("password2") String password2, Model model) throws MyException {

		User user;

		model.addAttribute("username", username);

		try {
			user = userService.findUserByLogin(username);

			if (null != user) {

				model.addAttribute("messageUsernameAlreadyExists", "!!! Such username already exists !!!");

				return "registration";

			} else if (!password.equals(password2)) {

				model.addAttribute("messageWrongPassword", "!!! Password entered incorrectly !!!");

				return "registration";

			} else {

				user = userService.registration(username, password);
				model.addAttribute("user", user);
				model.addAttribute("messageCongratulations", "Congratulations, you are successfully registered!");

				return "client";
			}
		} catch (ServiceException e) {
			throw new MyException(e);
		}
	}

}
