package org.notificationmanager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AppController {

	@Autowired
	private NotificationService service; 
	
	@RequestMapping("/")
	public String viewHomePage(Model model) {
		List<Notification> listNotifications = service.listAll();
		model.addAttribute("listNotifications", listNotifications);
		
		return "index";
	}
	
	@RequestMapping("/new")
	public String showNewNotificationPage(Model model) {
		Notification notification = new Notification();
		model.addAttribute("notification", notification);
		
		return "new_notification";
	}
	
	@PostMapping(value = "/save")
	public String saveNotification(@ModelAttribute("notification") Notification notification) {
		service.save(notification);
		
		return "redirect:/";
	}
	
	@RequestMapping("/edit/{id}")
	public ModelAndView showEditNotificationPage(@PathVariable(name = "id") int id) {
		ModelAndView mav = new ModelAndView("edit_notification");
		Notification notification = service.get(id);
		mav.addObject("notification", notification);
		
		return mav;
	}
	
	@DeleteMapping("/delete/{id}")
	public String deleteNotification(@PathVariable(name = "id") int id) {
		service.delete(id);
		return "redirect:/";		
	}
}
