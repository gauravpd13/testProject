package com.apps.demo.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.apps.demo.TriangleChallenge;

/**
 * 
 * @author gauravpd13
 *
 */

//assuming all inputs to be always rounded off to decimals place defined.
public class TriangleChallengeTest {
	
	TriangleChallenge tc;
	TriangleChallenge spyTC;
	
	@Before
	public void setUp(){
		tc = new TriangleChallenge();
		spyTC = Mockito.spy(tc);
	}
	
	private void mockInputs(double[] sides){
		Mockito.doReturn(sides).when(spyTC).getInputs();
	}
	
	@Test
	public void tc_testNegativeInputs1(){
		double[] sides = {10.0, -1.0, 2.0};
		mockInputs(sides);
		Assert.assertEquals("Input entered is not a positive number. Program Exits!", spyTC.process());
	}
	
	@Test
	public void tc_testNegativeInputs2(){
		double[] sides = {6.0, 1.0, -2.0};
		mockInputs(sides);
		Assert.assertEquals("Input entered is not a positive number. Program Exits!", spyTC.process());
	}
	
	@Test
	public void tc_testZeroInputs1(){
		double[] sides = {16.0, 0.0, 2.0};
		mockInputs(sides);
		Assert.assertEquals("Input entered is not a positive number. Program Exits!", spyTC.process());
	}
	
	@Test
	public void tc_testEquilateral1(){
		double[] sides = {16.0, 16.0, 16.0};
		mockInputs(sides);
		Assert.assertEquals("EQUILATERAL", spyTC.process());
	}
	
	@Test
	public void tc_testEquilateral2(){
		double[] sides = {6.17, 6.17, 6.17};
		mockInputs(sides);
		Assert.assertEquals("EQUILATERAL", spyTC.process());
	}
	
	@Test
	public void tc_testIsosceles1(){
		double[] sides = {8, 10, 8};
		mockInputs(sides);
		Assert.assertEquals("ISOSCELES", spyTC.process());
	}
	
	@Test
	public void tc_testIsosceles2(){
		double[] sides = {13.69, 9.23, 9.23};
		mockInputs(sides);
		Assert.assertEquals("ISOSCELES", spyTC.process());
	}
	
	@Test
	public void tc_testIsosceles3(){
		double[] sides = {6.17, 6.17, 6.17};
		mockInputs(sides);
		Assert.assertEquals("ISOSCELES", spyTC.process());
	}
	
	@Test
	public void tc_testScalene1(){
		double[] sides = {6.17, 3.58, 8.38};
		mockInputs(sides);
		Assert.assertEquals("SCALENE", spyTC.process());
	}
	
	@Test
	public void tc_testScalane2(){
		double[] sides = {8, 6, 4};
		mockInputs(sides);
		Assert.assertEquals("SCALENE", spyTC.process());
	}
	
	@Test
	public void tc_testInvalid1(){
		double[] sides = {6.17, 1, 1};
		mockInputs(sides);
		Assert.assertEquals("Input provided does not make up a triangle!", spyTC.process());
	}
	
	@Test
	public void tc_testInvalid2(){
		double[] sides = {1.17, 16.14, 29.17};
		mockInputs(sides);
		Assert.assertEquals("Input provided does not make up a triangle!", spyTC.process());
	}
	
}
