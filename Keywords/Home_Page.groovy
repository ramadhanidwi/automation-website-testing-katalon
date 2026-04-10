import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testcase.TestCaseFactory
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testdata.TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords

import internal.GlobalVariable

import org.openqa.selenium.WebElement
import org.openqa.selenium.WebDriver
import org.openqa.selenium.By

import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import com.kms.katalon.core.webui.driver.DriverFactory

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObjectProperty

import com.kms.katalon.core.mobile.helper.MobileElementCommonHelper
import com.kms.katalon.core.util.KeywordUtil

import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import org.openqa.selenium.WebElement
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.common.WebUiCommonHelper
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.testobject.ConditionType

class Home_Page {

	String [] productsToAdd = GlobalVariable.productsToAdd

    public List<WebElement> getListOfProducts() {
        TestObject listProductTO = new TestObject("listProduct");
        listProductTO.addProperty(
            "xpath",
            ConditionType.EQUALS,
            "//div[@class='inventory_item_name ']"
        );

        return WebUiCommonHelper.findWebElements(listProductTO, 10);
    }
	
	public getAddToCartButton(String productName) {
				String xpath = String.format(
					"//div[contains(@class,'inventory_item')]" +
					"//div[contains(@class,'inventory_item_name') and normalize-space()='%s']" +
					"/following::button[normalize-space()='Add to cart']",
					productName
				)
				return xpath
	}
	

	public removeProductButton(String productName) {
		String xpath = String.format("//div[contains(@class,'inventory_item')]" +
					"//div[contains(@class,'inventory_item_name') and normalize-space()='%s']" +
					"/following::button[normalize-space()='Remove']",
					productName)
	}


	@Keyword
	def addProductOnHomePage(productsToAdd) {
		Boolean found = false
		for (String products : productsToAdd) {
			List<WebElement> listOfProducts = getListOfProducts()
			for(int i =0; i < listOfProducts.size(); i++) {
				WebElement el = listOfProducts.get(i)
				String name = el.getText()
				if(name.equals(products)){
					TestObject btnAddToCart = new TestObject(products)
					btnAddToCart.addProperty('xpath', ConditionType.EQUALS, getAddToCartButton(products))
					WebUI.click(btnAddToCart)
					found = true
					break
				}
			}
			if(!found) {
				System.out.println("Data ", products, " Tidak Ditemukan!")
			}
		}}
	
	
	@Keyword
	def removeProductOnHomePage(productsToAdd) {
		List<WebElement> lisftOfProducts = getListOfProducts()
		for(String products : productsToAdd) {
			Boolean found = false 
			for(int i=0; i< listOfProducts.size(); i++) {
				WebElement el = lisftOfProducts.get(i)
				String name = el.getText()
				if(el.getText().equals(products)) {
					TestObject btnRemove = new TestObject(products)
					btnRemove.addProperty('xpath', ConditionType.EQUALS, removeProductButton(products))
					WebUI.click(btnRemove)
					fond = true
					break
				}	
			}
			if(!found) {
				System.out.println("Data ", products, " Tidak Ditemukan!")
			}
		}
	}
	
	@Keyword
	public goToCartPage() {
		WebUI.click(findTestObject("Object Repository/Home Page/cartLinkButton"))
	}

	/**
	 * Click element
	 * @param to Katalon test object
	 */
//	@Keyword
//	def removeProductOnHomePage(productsToAdd) {
//		for (String products : productsToAdd) {
//			Boolean found = false
//			for (int i = 0; i < listOfProducts.size())
//		}
//	}
}