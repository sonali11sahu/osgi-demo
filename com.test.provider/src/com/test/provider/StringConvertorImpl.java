package com.test.provider;

import java.util.Random;

import org.osgi.service.component.annotations.Component;

import com.test.api.StringConvertor;

@Component
public class StringConvertorImpl implements StringConvertor {

	@Override
	public String invert(String input) {
		return new StringBuilder(input).reverse().toString();
	}

	@Override
	public String upperCase(String input) {
		// TODO Auto-generated method stub
		return input.toUpperCase();
				//.toString();
	}

	@Override
	public String lowerCase(String input) {
		// TODO Auto-generated method stub
		return input.toLowerCase();
	}

	@Override
	public int length(String input) {
		// TODO Auto-generated method stub
		return input.length();
	}

	@Override
	public String random() {
		// TODO Auto-generated method stub
		
		String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        
        while (salt.length() < Math.random()*10) { // random length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;
		
		//return String.valueOf(Math.round(Math.random()*100));
	}

}