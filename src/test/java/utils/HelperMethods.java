package utils;

import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;

@Getter
@Setter
public class HelperMethods {

	WebDriver driver;

	public HelperMethods(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

}
