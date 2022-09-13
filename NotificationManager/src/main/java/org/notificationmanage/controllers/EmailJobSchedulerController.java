package org.notificationmanage.controllers;

import org.notificationmanage.email.EmailJob;
import org.notificationmanage.email.EmailRequest;
import org.notificationmanage.email.ScheduleEmailResponse;
import org.notificationmanage.services.EmailRequestService;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import javax.validation.Valid;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.UUID;

@Controller
public class EmailJobSchedulerController {
	private static final Logger logger = LoggerFactory.getLogger(EmailJobSchedulerController.class);

	@Autowired
	private Scheduler scheduler;

	@Autowired
	private EmailRequestService service;

	@PostMapping("/scheduleEmail")
	public String scheduleEmail(@Valid @RequestBody @ModelAttribute("emailRequest") EmailRequest emailRequest) {
		service.save(emailRequest);
		try {
			ZonedDateTime dateTime = ZonedDateTime.of(LocalDateTime.parse(emailRequest.getDateTime()),
					emailRequest.getTimeZone());
			if (dateTime.isBefore(ZonedDateTime.now())) {
				new ScheduleEmailResponse(false, "dateTime must be after current time");
			}

			JobDetail jobDetail = buildJobDetail(emailRequest);
			Trigger trigger = buildJobTrigger(jobDetail, dateTime);
			scheduler.scheduleJob(jobDetail, trigger);

			new ScheduleEmailResponse(true, jobDetail.getKey().getName(), jobDetail.getKey().getGroup(),
					"Email Scheduled Successfully!");

		} catch (SchedulerException ex) {
			logger.error("Error scheduling email", ex);

			new ScheduleEmailResponse(false, "Error scheduling email. Please try later!");

		}
		return "redirect:/email/index";
	}

	private JobDetail buildJobDetail(EmailRequest emailRequest) {
		JobDataMap jobDataMap = new JobDataMap();

		jobDataMap.put("email", emailRequest.getEmail());
		jobDataMap.put("subject", emailRequest.getSubject());
		jobDataMap.put("body", emailRequest.getBody());

		return JobBuilder.newJob(EmailJob.class).withIdentity(UUID.randomUUID().toString(), "email-jobs")
				.withDescription("Send Email Job").usingJobData(jobDataMap).storeDurably().build();
	}

	private Trigger buildJobTrigger(JobDetail jobDetail, ZonedDateTime startAt) {
		return TriggerBuilder.newTrigger().forJob(jobDetail)
				.withIdentity(jobDetail.getKey().getName(), "email-triggers").withDescription("Send Email Trigger")
				.startAt(Date.from(startAt.toInstant()))
				.withSchedule(SimpleScheduleBuilder.simpleSchedule().withMisfireHandlingInstructionFireNow()).build();
	}
}
