package com.ef;

public class Parser {
	public static void main(String[] args) {

		String path = System.getProperty("accesslog");
		String startDate = System.getProperty("startDate");
		String rate = System.getProperty("duration");
		String threshold = System.getProperty("threshold");

		if (path != null)
			new FileParser().parseFile(path);
		if (startDate != null && rate != null && threshold != null)
			new IPChecker().checkIP(startDate, rate, threshold);

	}
}
