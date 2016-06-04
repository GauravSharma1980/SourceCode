package com.goodvideotutorials.spring.controllers;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.goodvideotutorials.spring.dto.SignupForm;
import com.goodvideotutorials.spring.mail.MailSender;
import com.goodvideotutorials.spring.mail.MockMailSender;
import com.goodvideotutorials.spring.services.UserService;
import com.goodvideotutorials.spring.util.MyUtil;
import com.goodvideotutorials.spring.validators.SignupFormValidator;

@Controller
public class RootController {
	
	private static final Logger logger = LoggerFactory.getLogger(RootController.class);
	
	private MailSender mailSender;
	private UserService userService;
	private SignupFormValidator signupFormValidator;

	
	@Autowired
	public RootController(MailSender mailSender, UserService userService,
			SignupFormValidator signupFormValidator) {
		this.mailSender = mailSender;
		this.userService = userService;
		this.signupFormValidator = signupFormValidator;
	}
	
	@InitBinder("signupForm")
	protected void initSignupBinder(WebDataBinder binder) {
		binder.setValidator(signupFormValidator);
	}

	
//	@RequestMapping("/")
//	public String home() throws MessagingException {
//		
//		//mailSender.send("abc@example.com", "Hello, World", "Mail from spring");		
//		return "home";
//		
//	}
	
	
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signup(Model model) {
		
		model.addAttribute(new SignupForm());
		
		return "signup";
		
	}
	
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String signup(@ModelAttribute("signupForm") @Valid SignupForm signupForm,
			BindingResult result, RedirectAttributes redirectAttributes) {
		
		if (result.hasErrors())
			return "signup";
		
		userService.signup(signupForm);
		
		redirectAttributes.addAttribute("flashKind","success");
		redirectAttributes.addAttribute("flashMessage","Hi!! You Are IN!!!!");
		
		MyUtil.flash(redirectAttributes, "success", "signupSuccess");
		
		//MyUtil.flash(redirectAttributes);
		
		return "redirect:/";

	}


}
