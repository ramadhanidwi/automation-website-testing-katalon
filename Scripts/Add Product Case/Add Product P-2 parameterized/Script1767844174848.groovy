import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
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


WebUI.callTestCase(findTestCase('General/Login'), [:], FailureHandling.STOP_ON_FAILURE)

String [] productsToAdd = GlobalVariable.productsToAdd


// 1) Buat TestObject dinamis dengan XPath yang sama
TestObject listProductTO = new TestObject('listProduct')
listProductTO.addProperty('xpath', ConditionType.EQUALS, "(//div[@class='inventory_item_name '])")
TestObject listProductInCart = new TestObject('listProductInCart')
listProductInCart.addProperty('xpath', ConditionType.EQUALS, "Object Repository/Cart Page/List Product In Cart")


// 2) Ambil semua WebElement yang cocok (timeout 10 detik)
List<WebElement> listOfProducts = WebUiCommonHelper.findWebElements(listProductTO, 10)
List<WebElement> listOfProductsInCart = WebUiCommonHelper.findWebElements(listProductInCart, 10)

for (String products : productsToAdd) {
	String addProductButton = String.format(
		"//div[contains(@class,'inventory_item')]" +
		"[.//div[contains(@class,'inventory_item_name') and normalize-space(.)='%s']]" +
		"//button[normalize-space(.)='Add to cart']",
		products)
	Boolean found = false
	for(int i =0; i < listOfProducts.size(); i++) {
		WebElement el = listOfProducts.get(i)
		String name = el.getText()
		WebUI.comment("Produk ke-${i+1}: ${name}")
		if(el.getText().equals(products)){
			TestObject btnAddToCart = new TestObject('dynamic_AddToCart_' + products)
			btnAddToCart.addProperty('xpath', ConditionType.EQUALS, addProductButton)
			WebUI.click(btnAddToCart)
			found = true
		}
	}
	if(!found) {
		System.out.println("Data ", products, " Tidak Ditemukan!")
	}
}

WebUI.click(findTestObject('Object Repository/Home Page/cartLinkButton'))

WebUI.delay(10)
Boolean found = false
for(String products : productsToAdd) {
	for(int i; i < listOfProductsInCart; i++) {
		WebElement productInCart = listOfProductsInCart.get(i)
		String nameProductInCart = productInCart.getText()
		if(name.equals(products)) {
			found = true
			return true
		}
	}
	if(!found) {
		System.out.println("Data ", products, " Tidak Ditemukan!")
		return false
	}
}


WebUI.callTestCase(findTestCase('General/Logout and Close Browser'), [:], FailureHandling.STOP_ON_FAILURE)