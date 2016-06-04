package com.nts.cleancode.collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.nts.cleancode.collections.List;
import com.nts.cleancode.collections.Set;

public class SetTest {
	private AbstractCollection empty;
	private Set oneElement;
	private Set manyElement;

	@Before
	public void setUp() {
		empty = new Set();
		oneElement = new Set();
		oneElement.add("sophia");
		manyElement = new Set();
		manyElement.add("josh");
		manyElement.add("sasha");
	}

	@Test
	public void isEmpty() {
		assertTrue(empty.isEmpty());
		assertTrue(!oneElement.isEmpty());
	}

	@Test
	public void size() {
		assertEquals(0, empty.size());
		assertEquals(1, oneElement.size());
		assertTrue(manyElement.size() > 1);
	}

	@Test
	public void contains() {
		assertTrue(manyElement.contains("josh"));
		assertTrue(!manyElement.contains("tracy"));
	}

	@Test
	public void noDuplicates() {
		oneElement.add("sophia");
		assertEquals(1, oneElement.size());
	}

	@Test
	public void remove() {
		assertTrue(oneElement.remove("sophia"));
		assertEquals(0, oneElement.size());
		assertTrue(manyElement.remove("josh"));
		assertEquals(1, manyElement.size());
	}

	@Test
	public void removeCollapsesSet() {
		manyElement.add("tracy");
		assertEquals(3, manyElement.size());
		manyElement.remove("sasha");
		assertEquals(2, manyElement.size());
		assertEquals("tracy", manyElement.get(1));
	}

	@Test
	public void addAll() {
		oneElement.addAll(manyElement);
		assertEquals(3, oneElement.size());
	}

	@Test
	public void addAllWithList() {
		List list = new List();
		list.add("dave");
		oneElement.addAll(list);
		assertEquals(2, oneElement.size());
	}

	@Test
	public void addAllWithDuplicates() {
		AbstractCollection newSet = new Set();
		newSet.add("josh");
		manyElement.addAll(newSet);
		assertEquals(2, manyElement.size());
	}

	@Test
	public void addAllWithDuplicatesInList() {
		List newList = new List();
		newList.add("josh");
		manyElement.addAll(newList);
		assertEquals(2, manyElement.size());
	}

	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void getWhenIndexOutOfBounds() {
		empty.get(12);
	}

	@Test
	public void expandability() {
		AbstractCollection expandableSet = new Set();
		assertEquals(10, expandableSet.capacity());
		for (int i = 0; i < 11; i++)
			expandableSet.add(i);
		assertEquals(11, expandableSet.size());
		assertEquals(20, expandableSet.capacity());
	}

	@Test
	public void readOnlyOnAdd() {
		oneElement.setReadOnly(true);
		oneElement.add("eva");
		assertEquals(1, oneElement.size());
	}

	@Test
	public void readOnlyOnRemove() {
		oneElement.setReadOnly(true);
		oneElement.remove("sophia");
		assertEquals(1, oneElement.size());
	}

	@Test
	public void readOnlyOnAddAll() {
		oneElement.setReadOnly(true);
		oneElement.addAll(manyElement);
		assertEquals(1, oneElement.size());
	}
}
