import java.lang.Math.*;
import java.util.Scanner;


public class DragAnalysis {

	public static void main(String[] args) {
		
		Scanner stdIn = new Scanner(System.in);
		
		double height = heightInput(stdIn);
		double crossArea = crossAreaInput(stdIn);
		double veloc = velocInput(stdIn);
		double degAngle = degAngleInput(stdIn);
		
		double dragCoeff = 0.50;
		
		double pressure = pressureCalculation(height);
		double density = densityPressureCalculation(height);
		double velocX = magnitudeToXComponentForm(veloc, degAngle);
		double velocY = magnitudeToYComponentForm(veloc, degAngle);
		double forceDrag = forceDrag(height, dragCoeff, crossArea, veloc);
		double forceDragDegs = forceDragDegVeloComp(velocX, velocY);
		
		System.out.println();
		System.out.println("VelocX = " + velocX + " m/s");
		System.out.println("VelocY = " + velocY + " m/s");
		System.out.println();
		System.out.println("Pressure = " + pressure + " kPa");
		System.out.println("Density = " + density + " kg/m^3");
		System.out.println("Drag = " + forceDrag + " N");
		System.out.println("Drag Direction = " + forceDragDegs + " degrees");
		
		
		
		
		

	}

	
	public static double magnitudeToXComponentForm(double magnitude, double degDirection) {
		double radAngle = ((degDirection / (180 / Math.PI)));
		double radCos = Math.cos(radAngle);
		
		double xComponentPart = magnitude * radCos;
		return xComponentPart;
	}
	
	
	public static double magnitudeToYComponentForm(double magnitude, double degDirection) {
		double radAngle = ((degDirection / (180 / Math.PI)));
		double radSin = Math.sin(radAngle);
		
		double YComponentPart = magnitude * radSin;
		return YComponentPart;	
	}
	
	
	public static double pressureCalculation(double height) {
		
		double pressure = 0;
		
		if (height < 11000) {
			double temp = (15.04 - (0.00649 * height));
			pressure = (101.29 * Math.pow( ( (temp + 273.1) / 288.08), 5.256));
		}
		
		if (height > 11000 && height < 25000) {
			double temp = (-56.46);
			pressure = 22.65 * (Math.exp(1.73 - (0.000157 * height)));
		}
		
		if (height > 25000) {
			double temp = -131.21 + (0.00299 * height);
			pressure = 2.488 * Math.pow(( (temp + 273.1) / 216.6), (-11.388));
		}
		
		return pressure;
	}
	
	
	public static double densityPressureCalculation(double height) {
		
		double temp = 0;
		double pressure = 0;
		double density = 0;
		
		if (height < 11000) {
			temp = (15.04 - (0.00649 * height));
			pressure = (101.29 * Math.pow( ( (temp + 273.1) / 288.08), 5.256));
		}
		
		if (height > 11000 && height < 25000) {
			temp = (-56.46);
			pressure = 22.65 * (Math.exp(1.73 - (0.000157 * height)));
		}
		
		if (height > 25000) {
			temp = -131.21 + (0.00299 * height);
			pressure = 2.488 * Math.pow(( (temp + 273.1) / 216.6), (-11.388));
		}
		
		density = (pressure / (0.2869 * (temp + 273.1) ) );
		return density;
	}
	
	
	public static double forceDrag(double height, double dragCoeff, double crossArea, double veloc) {
		
		double density = densityPressureCalculation(height);
		
		double forceDrag = ( (1/2) * density * (Math.pow(veloc, 2)) * dragCoeff * crossArea);
		return forceDrag;		
	}
	
	
	public static double forceDragDeg(double degAngle) {
		double degAngleDrag = degAngle + 180;
		return degAngleDrag;
	}
	
	
	public static double forceDragVeloComp(double height, double dragCoeff, double crossArea, double velocX, double velocY) {
		double density = densityPressureCalculation(height);
		double veloc = Math.abs(Math.sqrt( (Math.pow(velocX, 2)) + (Math.pow(velocY, 2)) ));
		double forceDrag = ( (1/2) * density * (Math.pow(veloc, 2)) * dragCoeff * crossArea);
		return forceDrag;
	}
	
	
	public static double forceDragDegVeloComp(double velocX, double velocY) {
		double dragDegRadians = Math.atan( (velocY / velocX) );
		double tempDragDegs = dragDegRadians * (180/Math.PI);
		double finalDragDegs = tempDragDegs + 180;
		return finalDragDegs;
	}
	
	
	public static double heightInput(Scanner stdIn) {
		System.out.println("Please input current height [m]: ");
		double height = stdIn.nextDouble();
		
		while ( !(height >= 0) ) {
			System.out.println("Please input current height [m]: ");
			height = stdIn.nextDouble();
		}
		return height;
	}
	
	public static double crossAreaInput(Scanner stdIn) {
		System.out.println("Please input the cross area [m^2]: ");
		double crossArea = stdIn.nextDouble();
		
		while ( !(crossArea >= 0) ) {
			System.out.println("Please input the cross area [m^2]: ");
			crossArea = stdIn.nextDouble();
		}
		return crossArea;
	}
	
	public static double velocInput(Scanner stdIn) {
		System.out.println("Please input the velocity magnitude [m/s]: ");
		double veloc = stdIn.nextDouble();
		
		while ( !(veloc >= 0) ) {
			System.out.println("Please input the velocity magnitude [m/s]: ");
			veloc = stdIn.nextDouble();
		}
		return veloc;
	}
	
	public static double degAngleInput(Scanner stdIn) {
		System.out.println("Please input degree angle: ");
		double degAngle = stdIn.nextDouble();
		
		while ( !(degAngle >= 0) ) {
			System.out.println("Please input degree angle: ");
			degAngle = stdIn.nextDouble();
		}
		return degAngle;
	}
	
	
	
	
	
	
	
}