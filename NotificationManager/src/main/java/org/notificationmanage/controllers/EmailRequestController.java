package org.notificationmanage.controllers;

import java.util.Collection;
import java.util.List;

import org.notificationmanage.email.EmailRequest;
import org.notificationmanage.entities.User;
import org.notificationmanage.repositories.EmailRequestRepository;
import org.notificationmanage.repositories.UserRepository;
import org.notificationmanage.services.EmailRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/email")
@Controller
public class EmailRequestController {

	@Autowired
	private EmailRequestService service;

	@Autowired
	private EmailRequestRepository repo;

	@RequestMapping("/index")
	public String viewHomePage(Model model) {
		List<EmailRequest> findByUser = service.listAll();
		model.addAttribute("findByUser", findByUser);

		return "index";
	}

	@RequestMapping("/new")
	public String showNewEmailRequestPage(Model model) {
		EmailRequest emailRequest = new EmailRequest();
		model.addAttribute("emailRequest", emailRequest);

		return "new_email";
	}

	@PostMapping(value = "/save")
	public String saveEmailRequest(@ModelAttribute("emailRequest") EmailRequest emailRequest) {
		service.save(emailRequest);

		return "redirect:/";
	}

	@RequestMapping("/edit/{id}")
	public ModelAndView showEditEmailRequestPage(@PathVariable(name = "id") int id) {
		ModelAndView mav = new ModelAndView("edit_email");
		EmailRequest emailRequest = service.get(id);
		mav.addObject("emailRequest", emailRequest);

		return mav;
	}

	@DeleteMapping("/delete/{id}")
	public String deleteEmailRequest(@PathVariable(name = "id") int id) {
		service.delete(id);
		return "redirect:/";
	}
}
