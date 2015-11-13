package de.alpharogroup.resourcebundle.inspector.search.processor;

import java.util.HashSet;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * The class {@link UnusedKeysSearchResult}.
 */
@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UnusedKeysSearchResult
{

	/** The unused keys. */
	private Set<String> unusedKeys = new HashSet<String>();

}