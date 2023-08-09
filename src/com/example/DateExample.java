package com.example;

import java.time.LocalDate;

public class DateExample {

	public static void main(String[] args) {

		LocalDate ac = LocalDate.of(01, 01, 01);
		System.out.println(ac.getDayOfWeek());
		
		
		LocalDate epoch = LocalDate.of(1970, 01, 01);
		System.out.println(epoch.getDayOfWeek());
	}

}
