package com.apps.demo;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * 
 * @author gauravpd13
 *
 */

public class TriangleChallenge {
	
	//type of triangles
	enum TypeOfTriangle{
		EQUILATERAL, INVALID, ISOSCELES, SCALENE
	}
	
	//list of exceptions
	enum Exceptions{
		NOT_A_NUMBER("Input entered is not a number. Program Exits!"),
		NON_POSITIVE("Input entered is not a positive number. Program Exits!"),
		INVALID_CASE("Input provided does not make up a triangle!"),
		NO_EXCEPTION("OK");	
		private String msg;
		Exceptions (String val){
			msg = val;
		}
		String getMsg(){
			return msg;
		}
	}
	
	//This value can be made configurable by adding it to a properties file, therefore in case of any change we would not require any java code change
	public static final int NO_OF_DECIMALS = 2;
	
	//round the double value to no. of decimals specified
	public double roundOff(double val){
		long factor = (long) Math.pow(10, NO_OF_DECIMALS);
		//Shift the decimal with correct number of places to the right
		double retVal = val * factor;
		//Round to the nearest integer.
		long tmp = Math.round(retVal);
		//Shift the decimal with correct number of places to the left
		return (double) tmp/factor;
	}
	
	public String process(){
		//no of decimal places allowed figure
		System.out.println("The no of decimal places allowed for the length of sides is : " + NO_OF_DECIMALS);
		double[] sides =  null;
		try{
			//Capture inputs
			sides = getInputs();
			//sort the inputs, this will help to simplify the validations
			Arrays.sort(sides);
			//Inputs entered are...
			System.out.println("Input Provided (sorted):"+Arrays.toString(sides));
			//validate inputs
			Exceptions ex = validations(sides);
			if(ex.equals(Exceptions.NO_EXCEPTION)){
				//Identify the type of triangle
				TypeOfTriangle triangle = classify(sides);
				return triangle.toString();
			}else{
				return ex.getMsg();
			}
		}catch(NoSuchElementException e){
			return "Exception Occurred :"+Exceptions.NOT_A_NUMBER.getMsg();
		}catch(Exception ex){
			return "Exception Occurred :"+ex.getMessage();
		}
	}
	
	public double[] getInputs() throws NoSuchElementException{
		double[] sides = new double[3];
		//input lengths of the sides
		try(Scanner sc = new Scanner(System.in)){
			for(int i=0;i<sides.length;i++){
				System.out.println("Enter length for side :"+(i+1));
				//round off and assign to array values
				sides[i] = roundOff(sc.nextDouble());
			}
		}catch(NoSuchElementException e){
			//if the input is not a number
			throw e;
		}
		return sides;
	}
	
	private Exceptions validations (double[] sides){
		//check if any negative number or zero as input
		if(sides[0] <=0){
			return Exceptions.NON_POSITIVE;
		}
		//if larger side is greater than sum of rest two
		if(sides[0] + sides[1] < sides[2]){
			return Exceptions.INVALID_CASE;
		}
		//no exceptions
		return Exceptions.NO_EXCEPTION;
	}
	
	private TypeOfTriangle classify(double[] sides){
		//in a sorted array if first and last is same value, then its equilateral
		if(BigDecimal.valueOf(sides[0]).equals(BigDecimal.valueOf(sides[2]))){
			return TypeOfTriangle.EQUILATERAL;
		}else if(BigDecimal.valueOf(sides[0]).equals(BigDecimal.valueOf(sides[1])) 
				|| BigDecimal.valueOf(sides[1]).equals(BigDecimal.valueOf(sides[2]))){
		//if either first and second or second and third are same then isosceles
			return TypeOfTriangle.ISOSCELES;
		}else{
			//else scalene
			return TypeOfTriangle.SCALENE;
		}
	}
	
	public static void main(String[] args) {
		//start
		System.out.println("TriangleChallenge...");
		TriangleChallenge tc = new TriangleChallenge();
		System.out.println(tc.process());
		//end
	}
}
