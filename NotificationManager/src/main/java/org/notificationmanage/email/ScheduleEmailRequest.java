package org.notificationmanage.email;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Data
public class ScheduleEmailRequest {
	
	@Email
	@NotEmpty
	private String email;

	@NotEmpty
	private String subject;

	@NotEmpty
	private String body;

	@NotNull
	private LocalDateTime dateTime;

	@NotNull
	private ZoneId timeZone;
	
}