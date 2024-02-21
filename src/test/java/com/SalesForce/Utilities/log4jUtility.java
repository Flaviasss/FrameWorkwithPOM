package com.SalesForce.Utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class log4jUtility {
	
	 private  Logger log=null;
	 private static log4jUtility ob=null;
	 
	 private  log4jUtility() {
		 
		 
		 
	 }
	 public static log4jUtility getInstance() {
		 if (ob==null) {
			 ob= new log4jUtility();
		 } return ob;
		
		
	 }
	 public void getLogger() {
		 if (log==null) {
			 log=LogManager.getLogger(log4jUtility.class);
			 return;
	 }

	

	 }
	 }
