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
import com.kms.katalon.core.webui.exception.WebElementNotFoundException
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI


class Cart_Page {
    public List<WebElement> getListOfProducts() {
        TestObject listProductTO = new TestObject("listProduct");
        listProductTO.addProperty(
            "xpath",
            ConditionType.EQUALS,
            "//div[@class='inventory_item_name']"
        );
    }
	
	public removeProductButton(String productName) {
		String xpath = String.format("//div[contains(@class,'cart_item')]" +
					"//div[contains(@class,'inventory_item_name') and normalize-space()='%s']" +
					"/following::button[normalize-space()='Remove']",
					productName)
	}
	
	@Keyword
	def removeProductInCart(productsToAdd) {
		for(String products : productsToAdd) {
			List<WebElement> lisftOfProducts = getListOfProducts()
			Boolean found = false 
			for(int i =0; i < listOfProducts.size(); i++) {
				WebElement el = lisftOfProducts.get(i)
				String name = el.getText()
				if(name.equalsIgnoreCase(products)) {
					TestObject removeButton = new TestObject(products)
					removeButton.addProperty('xpath', ConditionType.EQUALS, removeButton(products))
					WebUI.click(removeButton)
					found = true
					break
				}
			}
			if(!found) {
				System.out.println("Product " + name + " tidak ditemukan di cart!")
			}
		}
	}
	
	@Keyword
	def goToCheckoutPage() {
		WebUI.click(findTestObject("Object Repository/Cart Page/checkOutButton"))
	}
	
	@Keyword
	def checkoutProduct(String firstName, String lastName, String postalCode) {
		WebUI.sendKeys(findTestObject("Object Repository/Cart Page/Checkout Product/firstNameLocator"),firstName)
		WebUI.sendKeys(findTestObject("Object Repository/Cart Page/Checkout Product/lastNameLocator"),lastName)
		WebUI.sendKeys(findTestObject("Object Repository/Cart Page/Checkout Product/postalCodeLocator"),postalCode)
	}
	
	@Keyword
	def goToOverviewPage() {
		WebUI.click(findTestObject("Object Repository/Cart Page/Checkout Product/continueButton"))
	}
	
	@Keyword
	def completingOrder() {
		WebUI.click(findTestObject("Object Repository/Cart Page/Checkout Product/finishButton"))
	}
	
	@Keyword
	def continueShoping() {
		WebUI.click(findTestObject("Object Repository/Cart Page/Checkout Product/backToShopingButton"))
	}
	
	@Keyword
	boolean checkProductInCart(productsToAdd) {
		boolean found = false 
		for(String products : productsToAdd) {
			List<WebElement> listOfProducts = getListOfProducts()
			for(int i=0; i<listOfProducts.size(); i++) {
				WebElement el = listOfProducts.get(i)
				String name = el.getText()
				if(name.equalsIgnoreCase(products)) {
					return true
					found = true 
				}
			}
			if(!found) {
				System.out.println("Product " + products + " tidak ditemukan di cart!");
				return false;
			}
		}
	}
}
