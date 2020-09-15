package StepdefFiles;

public class ReverseYourName {

	public static void main(String[] args) {
		ReverseYourName obj = new ReverseYourName();
		String nameToBereversed= "Rahul";
		obj.reverseName(nameToBereversed);
	}
	
	
	public void reverseName(String name) {
		
	   char[] arrayOfName=	name.toCharArray();
	   int len= arrayOfName.length;
	   char ch;
	   
	   for(int i =0; i <len/2; i++) {
		   ch= arrayOfName[len-1-i];
		   arrayOfName[len-1-i]= arrayOfName[i];
		   arrayOfName[i]= ch;
	   }
	   
	   for(int i =0; i<len; i++) {
		   System.out.print(arrayOfName[i]);
	   }
		
	}

}
