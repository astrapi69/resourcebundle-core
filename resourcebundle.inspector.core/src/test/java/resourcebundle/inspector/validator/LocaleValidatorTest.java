package resourcebundle.inspector.validator;

import java.util.Locale;

import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import com.neovisionaries.i18n.LocaleCode;



public class LocaleValidatorTest {

	@Test
	public void testValidate() {
		// Test string 
		String key = "com.example.gui.window.title";
		String actual = "de";
		AssertJUnit.assertTrue(LocaleValidator.validate(actual));
		Locale l = LocaleCode.getByCode(actual, true).toLocale();
		actual = "de_DE";
		AssertJUnit.assertTrue(LocaleValidator.validate(actual));
	}

}
