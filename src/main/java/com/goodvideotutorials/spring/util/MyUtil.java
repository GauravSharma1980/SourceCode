package com.goodvideotutorials.spring.util;

import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Component
public class MyUtil {
	
	@Autowired
	public MyUtil(MessageSource messageSource) {
		MyUtil.messageSource = messageSource;
	}
	
	public static void flash(RedirectAttributes redirectAttributes,
			String kind, String messageKey) {
		
		redirectAttributes.addFlashAttribute("flashKind", kind);
		redirectAttributes.addFlashAttribute("flashMessage",
				MyUtil.getMessage(messageKey));
		
	}
	
	public static void flash(RedirectAttributes redirectAttributes
			) {
		
		Map<String, ?> flashAttributes = redirectAttributes.getFlashAttributes();
		redirectAttributes.addFlashAttribute("flashKind", flashAttributes.get("flashKind"));
		redirectAttributes.addFlashAttribute("flashMessage",flashAttributes.get("flashMessage"));
		
	}
	
	private static MessageSource messageSource;

	private static String getMessage(String messageKey, Object... args) {
		return messageSource.getMessage(messageKey, args, Locale.getDefault());
	}


}
