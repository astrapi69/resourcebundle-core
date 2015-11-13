package de.alpharogroup.resourcebundle.inspector.search.processor;

import java.util.Properties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * The Class UsedKeysSearchResult keeps the result from the search and the a reference from the
 * search model.
 */
@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsedKeysSearchResult
{

	/** The search bean. */
	private KeySearchBean searchModel;

	/** The used keys. */
	private Properties used = new Properties();

}
