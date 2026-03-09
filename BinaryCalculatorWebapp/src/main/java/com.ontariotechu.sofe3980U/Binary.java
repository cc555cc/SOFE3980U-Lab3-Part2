package com.ontariotechu.sofe3980U;

/**
 * Unsigned integer Binary variable
 *
 */
public class Binary
{
	private String number="0";  // string containing the binary value '0' or '1'
	/**
	* A constructor that generates a binary object.
	*
	* @param number a String of the binary values. It should contain only zeros or ones with any length and order. otherwise, the value of "0" will be stored.   Trailing zeros will be excluded and empty string will be considered as zero.
	*/
	public Binary(String number) {
		if (number == null || number.isEmpty()) {
			this.number = "0"; // Default to "0" for null or empty input
			return;
		}
	
		// Validate the binary string (only '0' or '1' allowed)
		for (int i = 0; i < number.length(); i++) {
			char ch = number.charAt(i);
			if (ch != '0' && ch != '1') {
				this.number = "0"; // Default to "0" for invalid input
				return;
			}
		}
	
		// Remove leading zeros
		int beg;
		for (beg = 0; beg < number.length(); beg++) {
			if (number.charAt(beg) != '0') {
				break;
			}
		}
	
		// If all digits are '0', ensure number is "0"
		this.number = (beg == number.length()) ? "0" : number.substring(beg);
	
		// Ensure empty strings are replaced with "0"
		if (this.number.isEmpty()) {
			this.number = "0";
		}
	}
	/**
	* Return the binary value of the variable
	*
	* @return the binary value in a string format.
	*/
	public String getValue()
	{
		return this.number;
	}
	/**
	* Adding two binary variables. For more information, visit <a href="https://www.wikihow.com/Add-Binary-Numbers"> Add-Binary-Numbers </a>.
	*
	* @param num1 The first addend object
	* @param num2 The second addend object
	* @return A binary variable with a value of <i>num1+num2</i>.
	*/
	public static Binary add(Binary num1,Binary num2)
	{
		// the index of the first digit of each number
		int ind1=num1.number.length()-1;
		int ind2=num2.number.length()-1;
		//initial variable
		int carry=0;
		String num3="";  // the binary value of the sum
		while(ind1>=0 ||  ind2>=0 || carry!=0) // loop until all digits are processed
		{
			int sum=carry; // previous carry
			if(ind1>=0){ // if num1 has a digit to add
				sum += (num1.number.charAt(ind1)=='1')? 1:0; // convert the digit to int and add it to sum
				ind1--; // update ind1
			}
			if(ind2>=0){ // if num2 has a digit to add
				sum += (num2.number.charAt(ind2)=='1')? 1:0; // convert the digit to int and add it to sum
				ind2--; //update ind2
			}
			carry=sum/2; // the new carry
			sum=sum%2;  // the resultant digit
			num3 =( (sum==0)? "0":"1")+num3; //convert sum to string and append it to num3
		}
		Binary result=new Binary(num3);  // create a binary object with the calculated value.
		return result;
		
	}

	/**
	* Perform pad left for a binary number for n times. For more information, visit <a href="https://www.wikihow.com/Add-Binary-Numbers"> Add-Binary-Numbers </a>.
	*
	* @param str binary number in string
	* @param n number to pad left
	* @return a binary number in string pad left by n
	*/
	private static String padLeft(String str, int n){
		while(str.length() < n) {
			str = "0" + str;
		}
		return str;
	}

	/**
	* Perform left shift for a binary for n times. For more information, visit <a href="https://www.wikihow.com/Add-Binary-Numbers"> Add-Binary-Numbers </a>.
	*
	* @param x binary number 
	* @param n number to left shift
	* @return a binary number left shifted for n times
	*/
	private static Binary leftShift(Binary x, int n){
		String output = x.number;
		for(int i = 0; i < n; i++){
			output += "0";
		}
		return new Binary(output);
	}

	/**
	* Return the union of two binary numbers. For more information, visit <a href="https://www.wikihow.com/Add-Binary-Numbers"> Add-Binary-Numbers </a>.
	*
	* @param num1 first binary number 
	* @param num2 second binary number 
	* @return the union of the two numbers
	*/
	public static Binary or(Binary num1, Binary num2){
		String a = num1.number;
		String b = num2.number;
		String result = "";

		int maxLength = Math.max(a.length(), b.length());
		a = padLeft(a,maxLength);
		b = padLeft(b, maxLength);
		
		for(int i = 0; i < maxLength; i++){
			if(a.charAt(i) == '1' || b.charAt(i) =='1'){
				result += "1";
			}else{
				result += "0";
			}
		}
		return new Binary(result);
	}

	/**
	* Return the intersection number of two binary numbers. For more information, visit <a href="https://www.wikihow.com/Add-Binary-Numbers"> Add-Binary-Numbers </a>.
	*
	* @param num1 first binary number 
	* @param num2 second binary number 
	* @return the intersection of the two numbers
	*/
	public static Binary and(Binary num1, Binary num2){
		String a = num1.number;
		String b = num2.number;
		String result = "";

		int maxLength = Math.max(a.length(), b.length());
		a = padLeft(a,maxLength);
		b = padLeft(b, maxLength);
		
		for(int i = 0; i < maxLength; i++){
			if(a.charAt(i) == '1' && b.charAt(i) =='1'){
				result += "1";
			}else{
				result += "0";
			}
		}
		return new Binary(result);
	}

	/**
	* Return the product of two binary numbers. For more information, visit <a href="https://www.wikihow.com/Add-Binary-Numbers"> Add-Binary-Numbers </a>.
	*
	* @param num1 first binary number 
	* @param num2 second binary number 
	* @return the product of the two numbers
	*/
	public static Binary multiply(Binary num1, Binary num2){
		String multiplier = num2.number;
		Binary result = new Binary("0");
		int shiftCount = 0;
		for(int i = multiplier.length() - 1; i >= 0; i--){
			if(multiplier.charAt(i) == '1'){
				Binary temp = leftShift(num1,shiftCount);
				result = add(result,temp);
			}
			shiftCount++;
		}
		return result;
	}
}	
