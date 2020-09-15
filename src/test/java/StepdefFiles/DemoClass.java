package StepdefFiles;

public class DemoClass {

	public static void main(String[] args) {

		
		int [] arr= {20, 100, 30, 15, 25, 55, 6};
		DemoClass demoObj= new DemoClass();
		demoObj.sortNumbers(arr);
		
	}
	
	public void sortNumbers( int [] arr) {
		
		int len= arr.length;
		int temp=0;
		for(int i=0; i < len; i++) {
			for(int j=i+1; j<len; j++) {
				if(arr[j]<arr[i]) {
					temp= arr[j];
					arr[j]= arr[i];
					arr[i]= temp;
				}
					
			}
			System.out.println(arr[i]);
		}	
	}

}
