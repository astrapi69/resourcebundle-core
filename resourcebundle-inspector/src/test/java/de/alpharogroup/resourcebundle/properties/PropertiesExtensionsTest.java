/**
 * The MIT License
 *
 * Copyright (C) 2012 Asterios Raptis
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 *  *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *  *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package de.alpharogroup.resourcebundle.properties;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import de.alpharogroup.collections.properties.PropertiesExtensions;
import de.alpharogroup.collections.properties.SortedProperties;
import de.alpharogroup.lang.model.AnnotationModel;
import de.alpharogroup.lang.model.ClassModel;
import de.alpharogroup.lang.model.MethodModel;

/**
 * The unit test class for the class
 * {@link de.alpharogroup.resourcebundle.dep.PropertiesExtensions}.
 *
 * @version 1.0
 * @author Asterios Raptis
 */
public class PropertiesExtensionsTest
{

	private void getClassModels(final Map<String, List<String>> matchedPrefixes,
		final String localeAnnotationName, final Map<String, ClassModel> interfaces,
		final Properties ruProperties)
	{
		for (final Map.Entry<String, List<String>> entry : matchedPrefixes.entrySet())
		{
			final String key = entry.getKey();
			final List<String> propertyKeyValues = entry.getValue();
			ClassModel classModel = null;
			Map<String, MethodModel> methods = null;
			final int lastIndex = key.lastIndexOf(".");
			String packageName = "";
			String interfaceName = null;
			String methodName = "";
			MethodModel method = null;
			if (0 < lastIndex)
			{
				interfaceName = key.substring(lastIndex + 1, key.length());
				if (interfaces.containsKey(interfaceName))
				{
					classModel = interfaces.get(interfaceName);
					methods = classModel.getMethods();
					for (final String value : propertyKeyValues)
					{
						methodName = value.substring(key.length() + 1, value.length());
						method = methods.get(methodName);
						final String propertyValue = (String)ruProperties.get(value);
						final AnnotationModel annotation = new AnnotationModel();
						method.getMethodAnnotations().add(annotation);
						annotation.setName(localeAnnotationName);
						annotation.setValue(propertyValue);
						method.setParameters(
							PropertiesExtensions.getPropertyParameters(propertyValue));
					}
				}
				else
				{
					classModel = new ClassModel();
					classModel.setClassName(interfaceName);
					methods = new LinkedHashMap<>();
					classModel.setMethods(methods);
					packageName = key.substring(0, lastIndex);
					classModel.setPackageName(packageName);
					for (final String value : propertyKeyValues)
					{
						methodName = value.substring(key.length() + 1, value.length());
						method = new MethodModel();
						method.setMethodAnnotations(new ArrayList<AnnotationModel>());
						methods.put(methodName, method);
						method.setMethodName(methodName);
						final String propertyValue = (String)ruProperties.get(value);
						final AnnotationModel annotation = new AnnotationModel();
						method.getMethodAnnotations().add(annotation);
						annotation.setName(localeAnnotationName);
						annotation.setValue(propertyValue);
						method.setParameters(
							PropertiesExtensions.getPropertyParameters(propertyValue));
					}
					interfaces.put(interfaceName, classModel);
				}

			}
			else
			{
				interfaceName = key;
				if (interfaces.containsKey(interfaceName))
				{
					classModel = interfaces.get(interfaceName);
					methods = classModel.getMethods();
					for (final String value : propertyKeyValues)
					{
						methodName = value;
						method = methods.get(methodName);
						final String propertyValue = (String)ruProperties.get(value);
						final AnnotationModel annotation = new AnnotationModel();
						method.getMethodAnnotations().add(annotation);
						annotation.setName(localeAnnotationName);
						annotation.setValue(propertyValue);
						method.setParameters(
							PropertiesExtensions.getPropertyParameters(propertyValue));
					}
				}
				else
				{
					interfaceName = key;
					classModel = new ClassModel();
					methods = new LinkedHashMap<>();
					classModel.setMethods(methods);
					classModel.setClassName(interfaceName);
					for (final String value : propertyKeyValues)
					{
						methodName = value;
						method = new MethodModel();
						method.setMethodAnnotations(new ArrayList<AnnotationModel>());
						methods.put(methodName, method);
						method.setMethodName(methodName);
						final String propertyValue = (String)ruProperties.get(value);
						final AnnotationModel annotation = new AnnotationModel();
						method.getMethodAnnotations().add(annotation);
						annotation.setName(localeAnnotationName);
						annotation.setValue(propertyValue);
						method.setParameters(
							PropertiesExtensions.getPropertyParameters(propertyValue));
					}
					interfaces.put(interfaceName, classModel);
				}
			}
		}
	}

	// @Test
	public void test()
	{

		final Properties enProperties = new SortedProperties();

		enProperties.put("com", "Hello, {0} {1} {2}!");
		enProperties.put("com.example.gui.window.title", "Hello, {0}!");
		enProperties.put("com.example.gui.window.buttons.ok", "OK");
		enProperties.put("com.example.gui.window.buttons.cancel", "Cancel");

		Map<String, List<String>> matchedPrefixes = PropertiesExtensions
			.getMatchedPrefixLists(enProperties);
		String localeAnnotationName = "En";
		final Map<String, ClassModel> interfaces = new LinkedHashMap<>();


		getClassModels(matchedPrefixes, localeAnnotationName, interfaces, enProperties);

		final Properties ruProperties = new SortedProperties();

		ruProperties.put("com", "Привет, {0} {1} {2}!");
		ruProperties.put("com.example.gui.window.title", "Привет, {0}!");
		ruProperties.put("com.example.gui.window.buttons.ok", "Да");
		ruProperties.put("com.example.gui.window.buttons.cancel", "Отмена");

		matchedPrefixes = PropertiesExtensions.getMatchedPrefixLists(ruProperties);
		localeAnnotationName = "Ru";

		getClassModels(matchedPrefixes, localeAnnotationName, interfaces, ruProperties);
		System.out.println(interfaces);
	}

}
