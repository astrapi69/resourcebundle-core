package de.alpharogroup.resourcebundle.locale;

import java.io.Serializable;

import de.alpharogroup.lang.object.CloneObjectExtensions;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

/**
 * This class represents a resource bundle key.
 */
@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResourceBundleKey implements Serializable
{

	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The resource bundle key.
	 */
	@NonNull
	private String key;

	/**
	 * The parameters of the resource bundle key.
	 */
	private Object[] parameters;

	/**
	 * The default value.
	 */
	private String defaultValue;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object clone()
	{
		return CloneObjectExtensions.cloneObjectQuietly(this);
	}

}
