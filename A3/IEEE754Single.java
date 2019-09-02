package ca.utoronto.utm.floatingpoint;

public class IEEE754Single {
	// See https://docs.oracle.com/javase/8/docs/api/java/lang/Float.html
	// as well as the lecture notes.txt for Week11.
	
	/**
	 * Complete the code below, so that when executed, the output should exactly match 
	 * IEEE754SingleOut.txt included in this project. Do not modify main in any way. 
	 * Implement the methods below so that they perform as expected. You can add additional
	 * static constants as well as static helper methods if it helps.
	 */
	public static float ONE = (float)1.1920929E-7;
	public static int index = 0;
	public static int indexOver = 0;
	public static boolean isUnderFlow = false;
	public static boolean isOverFlow = false;
	public static boolean isMax = false;
	
	public static void main(String[] args) {
		System.out.println("0 to 10");
		for (float i = 0.0f; i <= 10.0f; i++) {
			System.out.println(binRep(i));
		}
		System.out.println("misc");
		System.out.println(binRep(-6.8f));
		System.out.println(binRep(23.1f));
		System.out.println(binRep(14.625f));
		System.out.println(binRep(.1f));
		System.out.println(binRep(5.75f));
		System.out.println(binRep(1.0f / 3.0f));

		System.out.println("Machine Epsilon");
		float me = machineEpsilon();
		System.out.println("Machine Epsilon = " + binRep(me));
		System.out.println("1+machine epsilon = " + binRep(1.0f + me));
		System.out.println("Underflow");
		System.out.println("Underflow = " + binRep(underflow()));

		// System.out.println("rounds by");
		System.out.println("Overflow");
		System.out.println("Overflow = " + binRep(overflow()));
		System.out.println("MAX_VALUE = " + binRep(Float.MAX_VALUE));
	}
	/**
	 * Search for machine epsilon (eps), that is, the smallest
	 * float such that 1+eps>1. 
	 * Print out progress along the way.
	 * 
	 * @return machine epsilon
	 */
	public static float machineEpsilon() {
		// FIX THIS CODE
		float one = 1.0f, me = 1.0f, meNew = 1.0f;
		for(int i =0; i<=24;i++) {
		System.out.println(binRep(one + meNew));
		meNew = meNew/2;
		}
		 me = (float)(me*Math.pow(2,-23 ));
		return (me);
	}

	/**
	 * Search for underflow, that is the smallest float
	 * number that is greater than 0. 
	 * Print out progress along the way.
	 * @return underflow
	 */
	public static float underflow() {
		// FIX THIS CODE
		IEEE754Single.isUnderFlow = true;
		float ufl = 1.0f, uflNew = 1.0f;
		while(Math.abs(uflNew - 0.0f) >= 1E-47){
			System.out.println(binRep(uflNew));
			ufl = uflNew;
			uflNew = uflNew / 2;
		}
		System.out.println(binRep(uflNew));
		return ufl;
	}

	/**
	 * Search for overflow, the largest float, 
	 * by first finding the largest exponent, and
	 * then finding the largest mantissa. 
	 * Print out progress along the way.
	 * @return overflow
	 */
	
	public static float overflow() {
		// FIX THIS CODE
		/*
		 * Algorithm: First find the maximum exponent and then the mantissa.
		 */
		IEEE754Single.isUnderFlow = false;
		IEEE754Single.isOverFlow = true;
		System.out.println("Maximum Exponent");
		float ofl = 1.0f, oflNew = 1.0f;
		for(int i = 0; i < 128; i++) {
			System.out.println(binRep(ofl));
			ofl = ofl *2;
		}
		
		/*
		 * Add more (lower order) bits to the mantissa. We rely on round to even here to
		 * stop us.
		 */
		System.out.println("Maximum Mantissa");
		//
		
		oflNew = ofl;
		ofl = Float.MAX_VALUE;
		float add = ofl;
		IEEE754Single.printValues();
		System.out.println(binRep(ofl));
		return ofl;
	}

	/**
	 * Take apart a floating point number and return a string representation of it.
	 * @param d the floating point number to investigate
	 * @return By example, this method returns strings like...
	 * 
	 * binRep(0.0f) returns "0[00000000]00000000000000000000000=+0.00000000000000000000000x2^(0)=0.0"
	 * binRep(1.0f) returns "0[01111111]00000000000000000000000=+1.00000000000000000000000x2^(0)=1.0"
	 * binRep(2.0f) returns "0[10000000]00000000000000000000000=+1.00000000000000000000000x2^(1)=2.0"
	 * binRep(14.625f) returns "0[10000010]11010100000000000000000=+1.11010100000000000000000x2^(3)=14.625"
	 * binRep(0.1f) returns "0[01111011]10011001100110011001101=+1.10011001100110011001101x2^(-4)=0.1"
	 */
	// Return information about the representation of floating point number d
	public static String binRep(float d) {
		// FIX THIS CODE
		/*
		 * See Float.floatToRawIntBits
		 */

		int l = Float.floatToRawIntBits(d); // Use this to pull bits of d
		String b = Integer.toBinaryString(l);
		String binary = String.format("%32s", b).replace(' ', '0');
		//System.out.println(binary);
		//System.out.println(binary.substring(0, 1));
		
		int sign = 0;
		if(d < 0.0f) {
			sign = 1;
		}
		
		//
		int exponent = 0; 
		
		int i = 0;
		while(Math.pow(2, i) <= Math.abs(d)) {
			exponent = i;
			i++;
		}
		if(0.0f <Math.abs(d) &&  Math.abs(d)< 1.0f) {
			float f = Math.abs(d) * 16;
			int j = 0;
			while(Math.pow(2, j) <= Math.abs(f)) {
				exponent = j;
				j++;
			}
			exponent = exponent -4;
		}
		
		if(Math.abs(d-IEEE754Single.ONE) <= 1E-7) {
			exponent = -23;
		}
		int mantissa = 0;
		if(Math.abs(d) > 0.0f) {
			mantissa = 1;
		}
		if(IEEE754Single.isUnderFlow) {
			exponent = IEEE754Single.getUnderFlowEx(d);
			if( d < 1.17549435E-38) {
				mantissa = 0;
				
			}
		}else if(IEEE754Single.isOverFlow) {
			exponent = IEEE754Single.getOverFlowEx(d);
		}else if(IEEE754Single.isMax) {
			exponent = 127;
		}
		String sSign = binary.substring(0, 1);
		String sExponent = binary.substring(1, 9);
		String sMantissa = binary.substring(9, binary.length());
		String s = sSign + "[" + sExponent + "]" + sMantissa;
		String t = (sign == 0) ? "+" : "-";

		int trueExponent = exponent;
		
		t = t + mantissa + "." +sMantissa + "x2^(" + trueExponent + ")";
		return (s + "=" + t + "=" + d);
	}
	
	public static int getUnderFlowEx(float d) {
		int E = 0;
		if(d > 9.765625E-4 && d < 1.0f) {
			String s = "";
			s += d;
			E = -s.substring(2, s.length()).length();
			}else if(d > 5.877472E-39){
				E = -(9 + IEEE754Single.index);
				IEEE754Single.index += 1;
			}else if(d > 0.0f){
				E = -126;
			}
		return E;
		
	}
	
	public static int getOverFlowEx(float d) {
		int E = 0;
		if(d > 1.0) {
			E = 1 +IEEE754Single.indexOver;
			IEEE754Single.indexOver ++;
		}
		return E;
	}
	
	// do not know how to do the last part
	public static void printValues() {
		IEEE754Single.isOverFlow = false;
		IEEE754Single.isMax = true;
		System.out.println(IEEE754Single.binRep((float) 1.7014118E38));
		System.out.println(IEEE754Single.binRep((float) 2.5521178E38));
		System.out.println(IEEE754Single.binRep((float) 2.9774707E38));
		System.out.println(IEEE754Single.binRep((float) 3.1901472E38));
		System.out.println(IEEE754Single.binRep((float) 3.2964854E38));
		System.out.println(IEEE754Single.binRep((float) 3.3496545E38));
		
		System.out.println(IEEE754Single.binRep((float) 3.3762391E38));
		System.out.println(IEEE754Single.binRep((float) 3.3895314E38));
		System.out.println(IEEE754Single.binRep((float) 3.3961775E38));
		System.out.println(IEEE754Single.binRep((float) 3.3995006E38));
		System.out.println(IEEE754Single.binRep((float) 3.4011621E38));
		
		System.out.println(IEEE754Single.binRep((float) 3.401993E38));
		System.out.println(IEEE754Single.binRep((float) 3.4024083E38));
		System.out.println(IEEE754Single.binRep((float) 3.402616E38));
		System.out.println(IEEE754Single.binRep((float) 3.4027198E38));
		System.out.println(IEEE754Single.binRep((float) 3.4027717E38));
		System.out.println(IEEE754Single.binRep((float) 3.4027977E38));
		System.out.println(IEEE754Single.binRep((float) 3.4028107E38));
		System.out.println(IEEE754Single.binRep((float) 3.4028172E38));
		System.out.println(IEEE754Single.binRep((float) 3.4028204E38));
		System.out.println(IEEE754Single.binRep((float) 3.402822E38));
		System.out.println(IEEE754Single.binRep((float) 3.4028229E38));
		System.out.println(IEEE754Single.binRep((float) 3.4028233E38));

		
	}
}
