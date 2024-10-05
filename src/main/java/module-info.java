/**
 * The MIT License
 *
 * Copyright (C) 2015 Asterios Raptis
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute,
 * sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions: * The above copyright notice and this
 * permission notice shall be included in all copies or substantial portions of the Software. * THE
 * SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT
 * LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
module io.github.astrapisixtynine.resourcebundle.core
{
	requires static lombok;
	requires file.worker;
	requires org.apache.commons.io;
	requires io.github.astrapisixtynine.silly.collection;
	requires silly.io.main;
	requires io.github.astrapisixtynine.throwable;
	requires io.github.astrapisixtynine.silly.bean;
	requires io.github.astrapisixtynine.jobj.core;

	exports io.github.astrapi69.resourcebundle.config;
	exports io.github.astrapi69.resourcebundle.file.namefilter;
	exports io.github.astrapi69.resourcebundle.inspector.core;
	exports io.github.astrapi69.resourcebundle.inspector.io;
	exports io.github.astrapi69.resourcebundle.inspector.search;
	exports io.github.astrapi69.resourcebundle.inspector.search.processor;
	exports io.github.astrapi69.resourcebundle.inspector.validator;
	exports io.github.astrapi69.resourcebundle.locale;
	exports io.github.astrapi69.resourcebundle.properties;

}