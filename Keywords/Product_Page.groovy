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
import com.kms.katalon.core.webui.common.WebUiCommonHelper
import com.kms.katalon.core.webui.exception.WebElementNotFoundException
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI


class Product_Page {
	public List<WebElement> getListOfProducts() {
		TestObject listProductTO = new TestObject("listProduct");
		listProductTO.addProperty(
			"xpath",
			ConditionType.EQUALS,
			"//div[@class='inventory_item_name ']"
		);

		return WebUiCommonHelper.findWebElements(listProductTO, 10);
	}
	
	public getProductCart(String productName) {
		String xpath = String.format("//div[contains(@class,'inventory_item')]" +
			"//div[contains(@class,'inventory_item_name') and normalize-space()='%s']",
			productName)
		return xpath
	}
	
//	public getAddToCartButton(String productName) {
//		String xpath = String.format(
//			"//div[contains(@class,'inventory_item')]" +
//			"//div[contains(@class,'inventory_item_name') and normalize-space()='%s']" +
//			"/following::button[normalize-space()='Add to cart']",
//			productName
//		)
//		return xpath
//}
	
	@Keyword
	def addProductOnProductPage(productsToAdd) {
		Boolean found = false
		for(String products : productsToAdd) {
			List<WebElement> listOfProducts = getListOfProducts()
			for(int i = 0; i< listOfProducts.size(); i++) {
				WebElement el = listOfProducts.get(i)
				String name = el.getText()
				if(name.equalsIgnoreCase(products)) {
					TestObject btnProduct = new TestObject(products)
					btnProduct.addProperty('xpath',ConditionType.EQUALS, getProductCart(products))
					WebUI.click(btnProduct)
					WebUI.click(findTestObject("Object Repository/Product Page/addToCartButton"))
					WebUI.click(findTestObject("Object Repository/Product Page/backToBerandaButton"))
					found = true
					break
				}
			}
			if(!found) {
				System.out.println("Data ", products, " Tidak Ditemukan!")
			}
		}
	}
	
	@Keyword
	def removeProductOnProductPage(productsToAdd) {
		Boolean found = false
		for(String products : productsToAdd) {
			List<WebElement> listOfProducts = getListOfProducts()
			for(int i = 0; i< listOfProducts.size(); i++) {
				WebElement el = listOfProducts.get(i)
				String name = el.getText()
				if(name.equalsIgnoreCase(products)) {
					TestObject btnProduct = new TestObject(products)
					btnProduct.addProperty('xpath',ConditionType.EQUALS, getProductCart(products))
					WebUI.click(btnProduct)
					WebUI.click(findTestObject("Object Repository/Product Page/removeButton"))
					WebUI.click(findTestObject("Object Repository/Product Page/backToBerandaButton"))
					found = true
					break
				}
			}
			if(!found) {
				System.out.println("Data ", products, " Tidak Ditemukan!")
			}
		}
	}
}