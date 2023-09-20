package com.sirma.javacourse.collections.pagebean;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class contains logic for splitting a list of elements into pages
 * and supports different actions with the pages, like traversing through them or changing the page size dynamically.
 */
public class ElementPageManager {

	private static final Logger LOGGER = LoggerFactory.getLogger(ElementPageManager.class);

	private int pageSize;
	private List<Object> pages;
	private int currentPage;

	public ElementPageManager(int pageSize, ArrayList<Object> elements) {
		this.pageSize = pageSize;
		this.pages = elements;
		this.currentPage = -1;
	}

	/**
	 * Sets the page size to the given page size and updates the instance's fields accordingly, resetting the current page and updating the last page index.
	 * @param pageSize the new page size.
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
		this.currentPage = -1;
	}

	/**
	 * Returns a list containing the elements of the next page and updates the current page number.
	 * @return The next page as an {@code ArrayList}, or null if there is no next page or if there are no pages.
	 */
	public ArrayList<Object> next() {
		if (!hasNext()) {
			LOGGER.error("There is no next page!");
			return null;
		}
		this.currentPage++;
		return getPage();
	}

	/**
	 * Returns a list containing the elements of the previous page and updates the current page number.
	 * @return The previous page as an {@code ArrayList}, or null if there is no previous page or if there are no pages.
	 */
	public ArrayList<Object> previous() {
		if (!hasPrevious()) {
			LOGGER.error("There is no previous page!");
			return null;
		}
		this.currentPage--;
		return getPage();
	}

	/**
	 * Sets the current page to the first page and returns it.
	 * @return An {@code ArrayList} containing the elements of the first page, null if there is no first page.
	 */
	public ArrayList<Object> firstPage() {
		if (CollectionUtils.isEmpty(pages)) {
			LOGGER.error("There is no first page!");
			return null;
		}
		currentPage = 0;
		return getPage();
	}

	/**
	 * Sets the current page to the last page and returns it.
	 * @return An {@code ArrayList} containing the elements of the last page, null if there is no last page.
	 */
	public ArrayList<Object> lastPage() {
		if (CollectionUtils.isEmpty(pages)) {
			LOGGER.error("There is no last page!");
			return null;
		}
		currentPage = (int) Math.ceil(pages.size() / (double) pageSize) - 1;
		return getPage();
	}

	/**
	 * Returns the current page number. Note, the number is not based on array indexing, so the counting starts from 1, and not 0.
	 * @return the number of the current page.
	 */
	public int getCurrentPageNumber() {
		return currentPage + 1;
	}

	/**
	 * Returns the page specified by the currentPage field.
	 * @return the current page under the form of an {@code ArrayList}.
	 */
	private ArrayList<Object> getPage() {
		int start = pageSize * currentPage;
		int end = start + pageSize;
		if (end >= pages.size()) {
			end = pages.size();
		}
		return new ArrayList<>(pages.subList(start, end));
	}

	/**
	 * Checks whether there is a next page after the current page.
	 * @return true if there is a next page, false if there isn't.
	 */
	private boolean hasNext() {
		if (CollectionUtils.isEmpty(pages)) {
			return false;
		}
		int lastPage = (int) Math.ceil(pages.size() / (double) pageSize) - 1;
		return (currentPage < lastPage);
	}

	/**
	 * Checks whether there is a previous page before the current page.
	 * @return true if there is a previous page, false if there isn't.
	 */
	private boolean hasPrevious() {
		return (currentPage > 0 && !CollectionUtils.isEmpty(pages));
	}
}
