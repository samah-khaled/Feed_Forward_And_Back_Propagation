package Assi1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;


public class Feedforward {
     
public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
		    		Scanner input=new Scanner (System.in);

			BufferedReader	br1 = new BufferedReader(new FileReader("train.txt")); // call test 
		     Scanner sc1 =new Scanner (br1);
		     
			BufferedReader	br = new BufferedReader(new FileReader("model.txt"));
		     Scanner sc =new Scanner (br);
	         double Gama =sc.nextDouble();
	         double Eta =sc.nextDouble();
	         int L=sc.nextInt();
	         
		     int M= sc1.nextInt();  //M is number of Input Nodes
		     int N = sc1.nextInt(); //N is number of Output Nodes
		     int K = sc1.nextInt(); 
		     //int L= input.nextInt(); //L is number of Hidden Nodes 
		     //Gama=input.nextDouble();
		     // learningRate=input.nextDouble();

		    
	         double [][]WHidden = new double [L][M];
	         double [][]WOutput = new double [N][L];
	         double [][]Inputs = new double[K] [M];
	         double [][]actualOutputs = new double[K] [N]; 
	         double []Outputs = new double [N];
	         double []netHidden=new double [L];
	         double []netOutput=new double [N];
	         double []I = new double [L];
	         double sumationError=0;
	         double TotalsumationError=0;

             double ValidError=0;
	         double MSE=0;
	         double []error=new double [N];
	         
        ArrayList<Double> InputTrain=new ArrayList<Double>();
        ArrayList<Double> InputTest=new ArrayList<Double>();
        ArrayList<Double> OutPutTrain=new ArrayList<Double>();
        ArrayList<Double> OutPutTest=new ArrayList<Double>();
            
	         for(int i=0;i<L;i++)
             {
            	 for(int j=0;j<M;j++)
            	 {
            		 WHidden[i][j]=sc.nextDouble();
            	 }
             }
             for(int i=0;i<N;i++)
             {
            	 for(int j=0;j<L;j++)
            	 {
            		 WOutput[i][j]=sc.nextDouble();
            	 }
             }
        
	         for (int test=0,tr1=36,tr2=0;test<=180-36;test+=36)
        {       
            
        for( int i= test ;i<test+36;i++)
            {   for(int j=0;j<M;j++){
                InputTest.add(Inputs[i][j]);
            }}
     
        for( int i= test ;i<test+36;i++)
            {   for(int j=0;j<N;j++){
                OutPutTest.add(actualOutputs[i][j]);
            }}
            
            int inputCounter=0;
        int OutPutCounter=0;
             for(int x=0; x<(K/5)  ; x++)
     	    { 
          
            
          for(int i=0;i< L;i++)
          {
        	  for(int j=0,j1=inputCounter;j< M ||j1<M+inputCounter;j++,j1++){
        		  netHidden[i]+=WHidden[i][j]*InputTest.get(j1);
        	  }
          }
          
          for(int j=0;j< L;j++){
 	         I[j]=(double) (1/(1+Math.exp(-Gama*netHidden[j])));

    	  }
          for(int i=0;i<N;i++){
  	    		for(int j=0;j<L;j++){
  	         netOutput[i]+=WOutput[i][j]*I[j];
  	      
  	        }}
          for(int i=0;i<N;i++){
  	        Outputs[i]= (double)(1/(1+Math.exp(-Gama*netOutput[i])));
  	    	}
          
          for(int i=0,i1=OutPutCounter;i<N||i1<N+OutPutCounter;i1++,i++){
  	        error[i]=OutPutTest.get(i1)-Outputs[i];
          }
          
          for(int i=0 ; i<N ; i++ ){
 	         
	          sumationError+=(error[i]*error[i]);
	          TotalsumationError+=error[i];
	      }
          
          System.out.println("Expected_output "+ " Actual_Output   " +" Error    ");
          for(int i=0,i1=OutPutCounter;i<N||i1<N+OutPutCounter;i++,i1++){
       	   System.out.println(Outputs[i] +"   "+OutPutTest.get(i1)+"    "+error[i]);
       	   }
          System.out.println(" Total sumation Error ="+TotalsumationError);
          
     	    }
             MSE=(double) (0.5*sumationError);
   	      MSE/=(double)515;
   	   
   	   System.out.println("MSE= "+MSE);
       ValidError+=MSE;
       
       
        inputCounter+=90;
        OutPutCounter+=4;
        }
	       
	         System.out.println( "Validation =" +(ValidError/5));

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



}

