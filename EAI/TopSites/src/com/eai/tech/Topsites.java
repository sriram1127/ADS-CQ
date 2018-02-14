/**
* The Topsites program implements an application that
* simply displays number of top sites based on the input N
*
* @author  Sriram Subramanian
* @version 1.0
* @since   2017-12-19 
*/

package com.eai.tech;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Topsites {
	public static void main(String[] args) {
		// checking if the input argument is only one
		int n = 0;
		if (args.length == 1) {
			try {
				// checking if the input argument is an integer
				n = Integer.parseInt(args[0]);
				if (n < 1) {
					System.out.println("Please Enter positive Integer");
					return;
				} else if (n > 500) {
					n = 500;
					System.out.println("Only 500 topsites are avialable");
				}
			} catch (Exception e) {
				System.out.println("Input should be an Integer");
				return;
			}
			// checking for the connection
			if (pingHost("http://topsites.eaiti.com")) {
				BufferedReader br = null;
				String line = null;
				URL url = null;
				int index = -1;
				// no of page requests
				int requests = n / 25;
				int no = 0;
				boolean exit = false;
				for (int i = 0; i <= requests && !exit; i++) {
					try {
						url = new URL("http://topsites.eaiti.com/?page=" + i);
						br = new BufferedReader(new InputStreamReader(url.openStream()));
						index = -1;
						boolean siteName = false;
						while ((line = br.readLine()) != null) {
							index = line.indexOf("number");
							if (siteName) {
								++no;
								line = line.substring(line.indexOf('>') + 1, line.lastIndexOf('<')).trim();
								System.out.println(no + " " + line);
								siteName = false;
								if (no >= n) {
									exit = true;
									break;
								}
							} else if (index != -1) {
								line = line.substring(index);
								line = line.substring(line.indexOf('>') + 1, line.indexOf('<')).trim();
								try {
									index = Integer.parseInt(line);
									siteName = true;
								} catch (NumberFormatException e) {
									continue;
								}
							}
						}
					} catch (Exception e) {
						System.out.println(e.getMessage());
					} finally {
						if (br != null) {
							try {
								br.close();
							} catch (IOException e) {
								System.out.println(e.getMessage());
							}
						}
					}
				}
			}

		} else {
			System.out.println("Invalid input");
		}
	}

	// checking for the connection
	public static boolean pingHost(String host) {
		HttpURLConnection connection = null;
		try {
			URL u = new URL(host);
			connection = (HttpURLConnection) u.openConnection();
			connection.setRequestMethod("HEAD");
			return connection.getResponseCode() == 200;
		} catch (Exception e) {
			System.out.println(e.getMessage() + " is not available");
			return false;
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
	}
}
