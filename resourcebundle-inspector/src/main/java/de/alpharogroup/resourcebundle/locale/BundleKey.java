package de.alpharogroup.resourcebundle.locale;

import java.io.Serializable;
import java.util.Locale;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * The class {@link BundleKey}.
 */
@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BundleKey implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 5908869747802483688L;

	/** The base name. */
	private String baseName;

	/** The locale. */
	private Locale locale;

	/** The resource bundle key. */
	private ResourceBundleKey resourceBundleKey;

}
