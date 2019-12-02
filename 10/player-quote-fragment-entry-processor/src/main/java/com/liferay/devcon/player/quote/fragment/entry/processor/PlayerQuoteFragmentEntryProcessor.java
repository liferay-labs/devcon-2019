/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.devcon.player.quote.fragment.entry.processor;

import com.liferay.fragment.exception.FragmentEntryContentException;
import com.liferay.fragment.model.FragmentEntryLink;
import com.liferay.fragment.processor.FragmentEntryProcessor;
import com.liferay.fragment.processor.FragmentEntryProcessorContext;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.Random;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Pavel Savinov
 */
@Component(
	immediate = true, property = "fragment.entry.processor.priority:Integer=100",
	service = FragmentEntryProcessor.class
)
public class PlayerQuoteFragmentEntryProcessor
	implements FragmentEntryProcessor {

	@Override
	public String processFragmentEntryLinkHTML(
			FragmentEntryLink fragmentEntryLink, String html,
			FragmentEntryProcessorContext fragmentEntryProcessorContext)
		throws PortalException {

		Document document = _getDocument(html);

		Elements elements = document.getElementsByTag(_TAG);
		
		Random random = new Random();

		elements.forEach(
			element -> {
			   Element quoteText = document.createElement("span");
	  
			   String textClass = element.attr("textClass");

			   quoteText.attr("class", "text-" + textClass);
	  
			   quoteText.text(_QUOTES[random.nextInt(_QUOTES.length)]);
	  
			   element.replaceWith(quoteText);
			});
	  
		 Element bodyElement = document.body();
	  
		 return bodyElement.html();

	}

	@Override
	public void validateFragmentEntryHTML(String html, String configuration)
		throws PortalException {

		Document document = _getDocument(html);

		Elements elements = document.getElementsByTag(_TAG);
		
		Random random = new Random();

		for (Element element : elements) {
			if (Validator.isNull(element.attr("textClass"))) {
				throw new FragmentEntryContentException("Missing 'textClass' attribute!");
			}
		}
	}

	private Document _getDocument(String html) {
		Document document = Jsoup.parseBodyFragment(html);
	 
		Document.OutputSettings outputSettings = new Document.OutputSettings();
	 
		outputSettings.prettyPrint(false);
	 
		document.outputSettings(outputSettings);
	 
		return document;
	 }

	private static final String[] _QUOTES = {
		"A friend asks only for your time not your money.",
		"If you refuse to accept anything but the best, you very often get it.",
		"Today it's up to you to create the peacefulness you long for.",
		"A smile is your passport into the hearts of others.",
		"A good way to keep healthy is to eat more Chinese food.",
		"Your high-minded principles spell success.",
		"The only easy day was yesterday."
	 };
	 
	 private static final String _TAG = "player-quote";

	private static final Log _log = LogFactoryUtil.getLog(
		PlayerQuoteFragmentEntryProcessor.class);

}