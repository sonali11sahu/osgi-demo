package com.test.command;

import org.apache.felix.service.command.CommandProcessor;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.test.api.StringConvertor;

@Component(
		property= {
			CommandProcessor.COMMAND_SCOPE + ":String=test",
			CommandProcessor.COMMAND_FUNCTION + ":String=invert",
			CommandProcessor.COMMAND_FUNCTION + ":String=upper",
			CommandProcessor.COMMAND_FUNCTION + ":String=lower",
			CommandProcessor.COMMAND_FUNCTION + ":String=length",
			CommandProcessor.COMMAND_FUNCTION + ":String=random"},
		service=StringConvertorCommand.class
	)
	public class StringConvertorCommand {

		@Reference
		private StringConvertor str;
		
		public void invert(String input) {
			System.out.println(str.invert(input));
		}
		public void lower(String input) {
			System.out.println(str.lowerCase(input));
		}
		public void upper(String input) {
			System.out.println(str.upperCase(input));
		}
		public void length(String input) {
			System.out.println(str.length(input));
		}
		public void random() {
			System.out.println(str.random());
		}
	}