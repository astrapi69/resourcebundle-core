package de.alpharogroup.resourcebundle.inspector.search.processor;

/**
 * The Interface FilterProcessor processes a given model and returns the result.
 *
 * @param <MODEL>
 *            the generic type for the model
 * @param <RESULT>
 *            the generic type for the result
 */
public interface FilterProcessor<MODEL, RESULT>
{

	/**
	 * process the given model.
	 *
	 * @param model
	 *            the model
	 * @return the result
	 */
	RESULT process(final MODEL model);
}