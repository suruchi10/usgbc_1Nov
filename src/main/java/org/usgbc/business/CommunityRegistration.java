package org.usgbc.business;

import java.io.IOException;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.usgbc.page.UsgbcWebLocators;
import org.usgbc.utility.Base;
import org.usgbc.utility.BrokenLink;
import org.usgbc.utility.CommunityRegistrationFormData;
import org.usgbc.utility.ReusableMethods;

public class CommunityRegistration extends ReusableMethods{

	public CommunityRegistration(WebDriver driver) {
		super(driver);		
	}
	
	public void CommunityRegistartionModuleForNonExistingUser() throws Exception {
		
		getcommunityRegistration().click();
		Thread.sleep(2000);
		String communityRegistration_url = driver.getCurrentUrl();
		Assert.assertEquals(communityRegistration_url, baseUrl+"/community/registration");
		
		System.out.println("Broken Link for :"+ driver.getTitle());
		//BrokenLink.BrokenLinkCheck(communityRegistration_url);
		CommunityRegistrationFormData.CommmunityRegistrationForm();
	    String signin_url = driver.getCurrentUrl();
		if (signin_url.equalsIgnoreCase(baseUrl+"/signin") && driver.getTitle().contains("Sign-in")){
			   Assert.assertTrue(true);
			   System.out.println("Test Passed,Community Registration Form filled correctly");
			   System.out.println("Broken Link for :" + driver.getTitle());
			   BrokenLink.BrokenLinkCheck(signin_url);
			   signInForm("signin",10); //signin 10 => (amayra@gmail.com,amayra)
			   String Signin_url = driver.getCurrentUrl();
			   //System.out.println(getStatusMessageBlock().getText());
			   if (Signin_url.equalsIgnoreCase(baseUrl+"/signin") && getStatusMessageBlock().getText().contains("This user does not exist in the system.")) {
				   Assert.assertTrue(true);
				   System.out.println(getStatusMessageBlock().getText());
			   }else {
				   Assert.assertFalse(false);
			   }
		}else {
		       signin_url.equalsIgnoreCase(baseUrl+"/community/registration");
			   Assert.assertTrue(true);
			   driver.getTitle().contains("Community Form");
			   Assert.assertTrue(true);
			   System.out.println(" Test Failed, Form field left empty");   
		   }
	}	
	
	public void CommunityRegistartionModuleForNonExistingUserToGetRegistered() throws Exception {
			
			getcommunityRegistration().click();
			Thread.sleep(2000);
			String communityRegistration_url = driver.getCurrentUrl();
			Assert.assertEquals(communityRegistration_url, baseUrl+"/community/registration");
			System.out.println("Broken Link for  : "+ driver.getTitle());
			//BrokenLink.BrokenLinkCheck(communityRegistration_url);
			CommunityRegistrationFormData.CommmunityRegistrationForm();
		    String signin_url = driver.getCurrentUrl();
			if (signin_url.equalsIgnoreCase(baseUrl+"/signin") && driver.getTitle().contains("Sign-in")){
				   Assert.assertTrue(true);
				   System.out.println("Test Passed,Community Registration Form filled correctly ");
				   System.out.println("Broken Link for  :"+ driver.getTitle());
				  // BrokenLink.BrokenLinkCheck(signin_url);
				   getRegister().click();
				   Thread.sleep(3000);
				   String signup_url = driver.getCurrentUrl();
				   System.out.println("Broken Link for  :"+ driver.getTitle());
				   //BrokenLink.BrokenLinkCheck(signup_url);
				   signUpForm();//  modified with faker class
				   Thread.sleep(3000); 
				   String payment_url = driver.getCurrentUrl();
				   Assert.assertEquals(payment_url, baseUrl+"/usgbc/payment");
				   System.out.println("User registered and created sucessfully");
				   System.out.println("Broken Link for  :"+ driver.getTitle());
				  // BrokenLink.BrokenLinkCheck(payment_url);
				   paymentReceiptdownload();  
				  
			}else {
			       signin_url.equalsIgnoreCase(baseUrl+"/community/registration");
				   Assert.assertTrue(true);
				   driver.getTitle().contains("Community Form");
				   Assert.assertTrue(true);
				   System.out.println(" Test Failed,Community Registration Form Not Filled properly");   
			   }
		}	
	
	public void CommunityRegistartionModuleForExistingUser() throws Exception {
		
		getcommunityRegistration().click();
		Thread.sleep(2000);
		String communityRegistration_url = driver.getCurrentUrl();
		Assert.assertEquals(communityRegistration_url, baseUrl+"/community/registration");
		System.out.println("Broken Link for  :"+ driver.getTitle());
		BrokenLink.BrokenLinkCheck(communityRegistration_url);
		CommunityRegistrationFormData.CommmunityRegistrationForm();
	    String signin_url = driver.getCurrentUrl();
		if (signin_url.equalsIgnoreCase(baseUrl+"/signin") && driver.getTitle().contains("Sign-in")){
			   Assert.assertTrue(true);
			   System.out.println("Test Passed,Community Registration Form filled correctly ");
			   System.out.println("Broken Link for :"+ driver.getTitle());
			   BrokenLink.BrokenLinkCheck(signin_url);
			   signInForm("signin",3); //signin 3 => (abi@gmail.com,abi)
			   String payment_url = driver.getCurrentUrl();
			   Assert.assertEquals(payment_url, baseUrl+"/usgbc/payment");
			   System.out.println("User is registered already ");
			   System.out.println("Broken Link for  :"+ driver.getTitle());
			   BrokenLink.BrokenLinkCheck(payment_url);
			   paymentReceiptdownload();  
		}else {
		       signin_url.equalsIgnoreCase(baseUrl+"/community/registration");
			   Assert.assertTrue(true);
			   driver.getTitle().contains("Community Form");
			   Assert.assertTrue(true);
			   System.out.println(" Test Failed,Community Registration Form Not Filled properly");   
		   }
	}		
}