package ru.spb.osll.tests;

import jmunit.framework.cldc11.TestSuite;

import ru.spb.osll.tests.JsonRequestsTest;

public class AllTestSuite extends TestSuite {
	public AllTestSuite() {
		super( " A l l T e s t s " );
		add (new JsonRequestsTest() ) ;
	}
}