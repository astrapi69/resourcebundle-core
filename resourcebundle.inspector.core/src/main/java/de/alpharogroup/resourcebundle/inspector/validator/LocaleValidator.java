package de.alpharogroup.resourcebundle.inspector.validator;

import com.neovisionaries.i18n.LocaleCode;

/**
 * The Class {@link LocaleValidator} validates string values for locale objects.
 */
public class LocaleValidator
{

	/**
	 * Validate the given code.
	 *
	 * @param stringCode
	 *            the string code
	 * @return true, if successful
	 */
	public static boolean validate(final String stringCode)
	{
		return LocaleCode.getByCode(stringCode) != null;
	}

}
