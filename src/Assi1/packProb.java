package Assi1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;


public class packProb {
     
//private static final String L = null;

public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner input=new Scanner (System.in);
		 BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader("train.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         double Gama =2;
         double learningRate=(double) -0.1;

      //   double LearningRate =1;

	     Scanner sc =new Scanner (br);
	     int M= sc.nextInt();  //M is number of Input Nodes
	      int N = sc.nextInt(); //N is number of Output Nodes
	      int K=sc.nextInt();  //  K, the number of training examples
	   // int L= input.nextInt(); //L is number of Hidden Nodes 
		  // Gama =input.nextDouble();
	     // learningRate=input.nextDouble();
	         int L=3;


        System.out.println("K= "+ K);

         System.out.println("M= "+ M );
         System.out.println("N= "+N );
         System.out.println("L= "+ L);

         
         double MSE=0;
         double []error=new double [N];
         double []cumulativeError=new double [N];
         double []backPropagateError=new double [L];
         double [][]Inputs = new double [K][M];
         double [][]actualOutputs = new double [K][N];
         double []Outputs = new double [N];
         double [][]WHidden = new double [L][M];
         double []netHidden=new double [L];
         double []netOutput=new double [N];
         double []I = new double [L];
       
         double [][]WOutput = new double [N][L];
         double sumationError=0;
         double AVGerror=0;
         
          for(int j=0; j< L ;j++)
        { for(int k=0;k<M;k++){
            WHidden[j][k]=-5+(double)(Math.random()* ((10) + 1)) ;
         //   System.out.print( WHidden[j][k]+" ");
        }
        }
        //  System.out.println();
     
          for(int k1=0; k1< N ;k1++)
        { for(int k=0;k<L;k++){
            WOutput[k1][k]=-5+(double)(Math.random()* ((10) + 1)) ;
        }
        }
        
        for(int i=0;i<K;i++ )
        {
            for(int j=0;j<M;j++){
         Inputs[i][j]=sc.nextDouble();
        
        }
        for(int a=0;a<N;a++){

         actualOutputs[i][a]=sc.nextDouble();
        }
        }
      sc.close();

        ArrayList<Double> InputTrain=new ArrayList<Double>();
        ArrayList<Double> InputTest=new ArrayList<Double>();
        ArrayList<Double> OutPutTrain=new ArrayList<Double>();
        ArrayList<Double> OutPutTest=new ArrayList<Double>();

        for (int test=0,tr1=36,tr2=0;test<=180-36;test+=36)
        {        MSE=0;
             for(int i=tr1;i<K;i++ )
            {
            for(int j=0;j<M;j++){
             InputTrain.add(Inputs[i][j]);

        }
            }
        for(int i=tr2;i<test;i++ )
            {
            for(int j=0;j<M;j++){
             InputTrain.add(Inputs[i][j]);

        }
        }
        for( int i= test ;i<test+36;i++)
            {   for(int j=0;j<M;j++){
                InputTest.add(Inputs[i][j]);
            }}
       // _____
        
        
         for(int i=tr1;i<K;i++ )
            {
            for(int j=0;j<N;j++){
             OutPutTrain.add(actualOutputs[i][j]);

        }
            }
        for(int i=tr2;i<test;i++ )
            {
            for(int j=0;j<N;j++){
             OutPutTrain.add(actualOutputs[i][j]);

        }
        }
        for( int i= test ;i<test+36;i++)
            {   for(int j=0;j<N;j++){
                OutPutTest.add(actualOutputs[i][j]);
            }}
        
        
            
        ////// 
        int inputCounter=0;
        int OutPutCounter=0;
        
    for(int x=0; x<(K/5)*4 /*|| MSE<=0.5*/ ; x++)
    {  
    for(int i=0;i<L;i++){ 
    		for(int k=inputCounter, numOfInput=0 ;k<M+inputCounter||numOfInput<M ;k++,numOfInput++){
   
         netHidden[i]+=WHidden[i][numOfInput]*InputTrain.get(k);
    	}
    	}
   
    for(int i=0;i<L;i++){

         I[i]=(double) (1/(1+Math.exp(-Gama*netHidden[i])));
        }
    
 
    for(int i=0;i<N;i++){
    		for(int k=0;k<L;k++){
         netOutput[i]+=WOutput[i][k]*I[k];
        }}
    
    for(int k=0;k<N;k++){
        Outputs[k]=(double) (1/(1+Math.exp(-Gama*netOutput[k])));
    	}
        
        for(int i=0,i1=OutPutCounter;i<N||i1<N+OutPutCounter;i++,i1++){
        error[i]=OutPutTrain.get(i1)-Outputs[i];
        
        cumulativeError[i]=(OutPutTrain.get(i1)*(1-Outputs[i]))*error[i];
        }
        
      for(int i=0; i<N ;i++)
      {for (int j=0;j<L;j++){
    	
          WOutput[i][j]=WOutput[i][j]+(learningRate*cumulativeError[i]*I[j]);
    
      }
      }
    
      for(int i=0;i<L;i++){
    	  double sum=0,X;
      	for(int j=0;j<N;j++){
      		X=WOutput[j][i]*error[j];
      		sum+=X;
      		}
      	backPropagateError[i]=sum*I[i]*(1-I[i]);
      }

      
      for(int i=0; i<L ;i++)
      {for (int j=0,j1=inputCounter;j<M||j1<M+inputCounter;j1++,j++){ 
    	
    	  WHidden[i][j]=WHidden[i][j]+(learningRate*backPropagateError[i]*InputTrain.get(j));
      }
      }  
      for(int i=0 ; i<N ; i++ ){
         
          sumationError+=(error[i]*error[i]);
      }
      MSE=(double) (0.5*sumationError);
      MSE/=(double)x;
      if(MSE<.5){
    	  // write 
	   // System.out.println(MSE);

    	  break;
      }
    inputCounter+=90;
    OutPutCounter+=4;
    }
    System.out.println(MSE);
	   AVGerror+=MSE;
 
 }
System.out.println((AVGerror/5));

    try {
    	
    	PrintWriter F=new PrintWriter("model.txt");
    	F.print(Gama+" ");
    	F.print(learningRate+" ");
    	F.println(L);
    	
    	 F.print(M+" ");
         F.println(N);
    	for(int i=0;i<L;i++)
    	{for(int j=0;j<M;j++){
    		F.print(WHidden[i][j]+" ");
    	}
    	F.println();
    	}
    	F.println();
    	F.println();
    	
    	for(int i=0;i<N;i++)
    	{for(int j=0;j<L;j++){
    		F.println(WOutput[i][j]);
    	}
    	F.println();
    	}
    	
    	F.close();
    	
		//F.write(c);
    	
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    		
  //  System.out.println(MSE);

	}


}

