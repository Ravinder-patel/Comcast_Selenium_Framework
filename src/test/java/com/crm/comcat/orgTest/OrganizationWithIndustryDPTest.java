package com.crm.comcat.orgTest;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.crm.GenericLibrary.BaseClass;
import com.crm.comcat.objectrepositorylib.CreateNewOrganizationPage;
import com.crm.comcat.objectrepositorylib.HomaPage;
import com.crm.comcat.objectrepositorylib.OrganizationsPage;
import com.crm.comcat.objectrepositorylib.OrganizatonInformationPage;


public class OrganizationWithIndustryDPTest extends BaseClass{


	/**
	 * Method to create new Organization
	 * @throws EncryptedDocumentException
	 * @throws InvalidFormatException
	 * @throws IOException
	 */
	@Test(groups = "smokeTest",dataProvider = "orgTestData")
	public void createOrgTest(String orgName , String industry) throws EncryptedDocumentException, InvalidFormatException, IOException {

		Assert.fail();
		//Navigating to OganizaionPage
		HomaPage hp = new HomaPage(driver);
		hp.getOrganizationLink().click();
		String expTitle="Organizations";
		wLib.waitForElementVisible(driver, expTitle);
		String actualTitle=driver.getTitle();
		Assert.assertTrue(actualTitle.contains(expTitle),"Not navigated to Organisation");
		Reporter.log("Navigated to Organization",true);

		//Navigating to create new Organization Page
		OrganizationsPage op = new OrganizationsPage(driver);
		op.getCreateOrgImg().click();
		expTitle="Organizations";
		wLib.waitForElementVisible(driver, expTitle);
		actualTitle=driver.getTitle();

		Assert.assertTrue(actualTitle.contains(expTitle),"Not navigated to create new Organization");
		Reporter.log("Navigated to create new Organization",true);

		//Creating new organization
		CreateNewOrganizationPage cp = new CreateNewOrganizationPage(driver);
		cp.createOrg(orgName,industry);

		OrganizatonInformationPage orgInfo = new OrganizatonInformationPage(driver);
		wLib.waitForElementVisible(driver, orgInfo.getsuccessfullMsg());
		String actualMsg = orgInfo.getsuccessfullMsg().getText();
		Assert.assertTrue(actualMsg.contains(orgName));
		Reporter.log("Organization created Successfully:Pass",true);
	}

	@DataProvider
	public Object[][] orgTestData() throws EncryptedDocumentException, InvalidFormatException, IOException {
		Object obj[][] = new Object[4][2];
		/*for(int i=0;i<4;i++) {
			for(int j=0;j<1;j++) {
				obj[i][j] = eLib.getDataFromExcel("CreateOrganisation", i+1, 2);


			}
		}
		for(int i=0;i<4;i++) {
			for(int j=1;j<2;j++) {
				obj[i][j] = eLib.getDataFromExcel("CreateOrganisation", i+1, 5);


			}
		}*/

		for(int i=0;i<4;i++) {
			int column=2;
			for(int j=0;j<2;j++) {
				obj[i][j] = eLib.getDataFromExcel("CreateOrganisation", i+5, column);
				column=column+3;
			}
		}

		return obj;

	}
}
