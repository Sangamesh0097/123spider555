package com.crm.Vtiger.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateDocumentPage {
	//intialization
		public CreateDocumentPage(WebDriver driver)
		{
			PageFactory.initElements(driver, this);
		}

		//declaretion
		@FindBy(name = "notes_title") private WebElement titleEdt;
 
		@FindBy(xpath = "//iframe[contains(@title,'Rich text editor')]") private WebElement frame;
		
		@FindBy(name = "filename") private WebElement fileUpload;
		
		@FindBy(name="button") private WebElement saveButton;

		//utilization
		public WebElement getTitleEdt() {
			return titleEdt;
		}

		public WebElement getFrame() {
			return frame;
		}

		public WebElement getFileUpload() {
			return fileUpload;
		}

		public WebElement getSaveButton() {
			return saveButton;
		}
		
		public void createDocument(String title,String filePath,String frameText) {
			titleEdt.sendKeys(title);
			frame.sendKeys(frameText);
			fileUpload.sendKeys(filePath);
		}

}
