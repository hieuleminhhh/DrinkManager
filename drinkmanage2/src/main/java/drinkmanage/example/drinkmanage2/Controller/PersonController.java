package drinkmanage.example.drinkmanage2.Controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import drinkmanage.example.drinkmanage2.Model.Person;
import drinkmanage.example.drinkmanage2.Service.IPersonService;

@Controller
@RequestMapping("/person")
public class PersonController {
	@Autowired
	private IPersonService iPersonService;  
	@RequestMapping("/login")
	public String login() {
		return "/login";
	}
	@PostMapping("/login")
	public String login(@RequestParam("email") String email, @RequestParam("password") String password, Model model, HttpSession session) {
		Person person = iPersonService.login(email, password);
		if(person != null) {
			session.setAttribute("person", person);
			return "redirect:/drink";
		}
		model.addAttribute("error", "Email or Password is wrong");
		return "login";
	}
	@RequestMapping("/signup")
	public String signup() {
		return "/signup";
	}
	@PostMapping("/signup")
	public String signup(@RequestParam("email") String email, @RequestParam("password") String password,
	@RequestParam("repassword") String repass, @RequestParam("name") String name, @RequestParam("phone") String phone, Model model) {
		if (!password.equals(repass)) {
			model.addAttribute("error", "Password and confirm password does not match");
			return "/signup";
		} else {
			Person person = iPersonService.checkUsernameExits(email);
			if (person == null) {
				person = iPersonService.signup(email, name, password, phone, 0);
				model.addAttribute("error", "Signup suscessfull");
				return "/signup";
			}
			model.addAttribute("error", "Username already exists !!!");
			return "/signup";
		}
	}
	@RequestMapping("/logout")
	@PostMapping
	public String logout( HttpSession session) {
		session.invalidate();
		return "redirect:/home";
	}
	
	
	
}
