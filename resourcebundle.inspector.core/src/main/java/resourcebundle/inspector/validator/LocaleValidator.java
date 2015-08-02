package resourcebundle.inspector.validator;

import com.neovisionaries.i18n.LocaleCode;

public class LocaleValidator
{

	public static boolean validate(final String stringCode)
	{
		return LocaleCode.getByCode(stringCode) != null;
	}

}
