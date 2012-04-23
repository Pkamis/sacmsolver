import java.util.*;
import java.lang.*;

class RubixDataStructureClass
{

int i,j,k,l,m;           //index variables
static  int[][][] a;     //data structure
static  int n;           //size of cube getting from main 


RubixDataStructureClass(int n)
{

  this.n=n;
  a= new int[10][n+10][n+10];
  //************  RED=1 ** ORANGE=2 **  WHITE=3 ** YELLOW=4 **  BLUE=5 ** GREEN=6  ********************
  //intial loading of rubiks cube in data structure

  for(i=1;i<=6;i++)
  {
    for(j=1;j<=n;j++)
    {
       for(k=1;k<=n;k++)
       {

         a[i][j][k]=i;

       }

     }
  }
}



void printData()         //will print the data 
{
for(i=1;i<=6;i++)
  {
   
   for(j=1;j<=n;j++)
    {

    for(k=1;k<=n;k++)
       {
              if(a[i][j][k]==1) System.out.print("RED");
         else if(a[i][j][k]==2) System.out.print("ORA");
         else if(a[i][j][k]==3) System.out.print("WHI");
         else if(a[i][j][k]==4) System.out.print("YEL");
         else if(a[i][j][k]==5) System.out.print("BLU");
         else if(a[i][j][k]==6) System.out.print("GRE");
         System.out.print("\t");
       }
    
       System.out.print("\n");
     
    }
     System.out.println("\n");

 }

}

public void  xyLayer(int degree,int direction,int layer)
{
  int[] temp=new int[n+10];
  int[][] t=new int[n+10][n+10];

if( (degree==90 && direction==-1) ||(degree==270 && direction==1))   //90 ANTI-CLOCK  (or)   270 CLOCK
{
  for(l=1;l<=n;l++)
    temp[l]=a[6][layer][l]; //temp=f6;
  for(l=1;l<=n;l++)
    a[6][layer][l]=a[3][layer][l]; //f6=f3;
  for(l=1;l<=n;l++)
    a[3][layer][l]=a[5][layer][l]; //f3=f5;
  for(l=1;l<=n;l++)
    a[5][layer][l]=a[4][layer][l]; //f5=f4;
  for(l=1;l<=n;l++)
    a[4][layer][l]=temp[l]; //f4=temp;
  if(layer==1 ||layer==n)
  {   
     int face;
     if(layer==1)  
     {
        face=1;
        for(i=1;i<=n;i++)
        {
           for(j=1;j<=n;j++)
           {
             t[n-j+1][i]=a[face][i][j]; 
           }
        }
     }
     else 
     {
        face=2;
        for(i=1;i<=n;i++)
        {
          for(j=1;j<=n;j++)
          {
            t[j][n-i+1]=a[face][i][j];
          }
        }
     }
    for(i=1;i<=n;i++)
       {
        for(j=1;j<=n;j++)
         {
          a[face][i][j]=t[i][j]; 
         }
       }

   }
 }//Ending of If 90 anti clock aor 270 clock

else if( (degree==180 && direction==-1) ||(degree==180 && direction==1))   //180 ANTI-CLOCK  (or)   180 CLOCK
{
  for(l=1;l<=n;l++)
    temp[l]=a[3][layer][l];          //temp=f3;
  for(l=1;l<=n;l++)
    a[3][layer][l]=a[4][layer][l];   //f3=f4;
  for(l=1;l<=n;l++)
    a[4][layer][l]=temp[l];          //f4=temp;
  for(l=1;l<=n;l++)
    temp[l]=a[5][layer][l];          //temp=f5;
  for(l=1;l<=n;l++)
    a[5][layer][l]=a[6][layer][l];   //f5=f6;
  for(l=1;l<=n;l++)
    a[6][layer][l]=temp[l];         //f6=temp;
  if(layer==1 ||layer==n)
  {
     int face;
     if(layer==1)  face=1;    else face=2;
     for(i=1;i<=n;i++)
     {
        for(j=1;j<=n;j++)
        {
          t[n-i+1][n-j+1]=a[face][i][j];
        }
     }
     for(i=1;i<=n;i++)
     {
       for(j=1;j<=n;j++)
         {
          a[face][i][j]=t[i][j];
         }
     }

   }
 }//Ending of If 180 anti-clock (or) 180 clock

if( (degree==270 && direction==-1) ||(degree==90 && direction==1))   //270 ANTI-CLOCK  (or)   90 CLOCK
{
  for(l=1;l<=n;l++)
    temp[l]=a[5][layer][l];        //temp=f5;
  for(l=1;l<=n;l++)
    a[5][layer][l]=a[3][layer][l]; //f5=f3;
  for(l=1;l<=n;l++)
    a[3][layer][l]=a[6][layer][l]; //f3=f6;
  for(l=1;l<=n;l++)
    a[6][layer][l]=a[4][layer][l]; //f6=f4;
  for(l=1;l<=n;l++)
    a[4][layer][l]=temp[l];       //f4=temp;
  if(layer==1 ||layer==n)
  {
     int face;
     if(layer==1)
     {
        face=1;
        for(i=1;i<=n;i++)
        {
           for(j=1;j<=n;j++)
           {
             t[j][n-i+1]=a[face][i][j];
           }
        }
     }
     else
     {
        face=2;
        for(i=1;i<=n;i++)
        {
          for(j=1;j<=n;j++)
          {
            t[n-j+1][i]=a[face][i][j];
          }
        }
     }

     for(i=1;i<=n;i++)
       {
        for(j=1;j<=n;j++)
         {
          a[face][i][j]=t[i][j];
         }
       }

   }
 }//Ending of If 270 anti-clock or 90 clock

}//ending of xyLayer method



public void  yzLayer(int degree,int direction,int layer) //Layer is corresponding to ********  j index *****
{
  int[] temp=new int[n+10];
  int[][] t=new int[n+10][n+10];
  
  if((degree==90 && direction==-1) || (degree==270 && direction==1)) //90 anti-clock  or 270 clock
  {
     if(layer==1 ||layer==n)
     {
       int face;
       if(layer==1)
       {  
          //yz1 plane  ******* F3 changes ********
          for(l=n;l>=1;l--)  temp[l]=a[5][l][n];                     //temp=f5
          for(l=1,m=n;(l<=n)&&(m>=1);l++,m--) a[5][m][n]=a[1][n][l];  //f5=f1
          for(l=1;l<=n;l++) a[1][n][l]=a[6][l][1];                    //f1=f6
          for(l=1,m=n;(l<=n)&&(m>=1);l++,m--) a[6][m][1]=a[2][1][l];   //f6=f2
          for(l=1;l<=n;l++)    a[2][1][l]=temp[l];                     //f2=temp
          // F3 changes  copying into 't'  and then again 't'  to  'a'    
          for(i=1;i<=n;i++)
          {
            for(j=1;j<=n;j++)  t[n-j+1][i]=a[3][i][j];
          }
         for(i=1;i<=n;i++)
          {
            for(j=1;j<=n;j++)   a[3][i][j]=t[i][j];
          }
        }
        else //Layer is n 
        { 
          //yzn plane  ******* F4 changes ********
          for(l=n;l>=1;l--)  temp[l]=a[5][l][1];                     //temp=f5
          for(l=1,m=n;(l<=n)&&(m>=1);l++,m--) a[5][m][1]=a[1][1][l];  //f5=f1
          for(l=1;l<=n;l++) a[1][1][l]=a[6][l][n];                    //f1=f6
          for(l=n,m=1;(l>=1)&&(m<=n);l--,m++) a[6][m][n]=a[2][n][l];   //f6=f2
          for(l=n;l>=1;l--)    a[2][n][l]=temp[l];                     //f2=temp
          // F4 changes  copying into 't'  and then again 't'  to  'a'
          for(i=1;i<=n;i++)
          {
            for(j=1;j<=n;j++)  t[j][n-i+1]=a[4][i][j];
          }
          for(i=1;i<=n;i++)
          {
           for(j=1;j<=n;j++)   a[4][i][j]=t[i][j];
          }
        } 
       } //if ending checking for layer is '1' or 'n' 
       else // that is 1<layer<n
       {    //yzk where 1<k<n

         for(l=n;l>=1;l--)  temp[l]=a[5][l][n-layer+1];                              //temp=f5

         for(l=1,m=n;(l<=n)&&(m>=1);l++,m--) a[5][m][n-layer+1]=a[1][n-layer+1][l];  //f5=f1

         for(l=1;l<=n;l++) a[1][n-layer+1][l]=a[6][l][layer];                    //f1=f6

         for(l=1,m=n;(l<=n)&&(m>=1);l++,m--) a[6][m][layer]=a[2][layer][l];          //f6=f2

         for(l=n;l>=1;l--)    a[2][layer][l]=temp[l];                            //f2=temp
       }
     }//degree 90 anti-clock or 270 clock 
     
    else if((degree==180 && direction==-1) ||(degree==180 && direction==1)) //YZ 180 anti-clock 180 clock
    {
      if(layer==1 ||layer==n)
      {
        int face;
        if(layer==1)
        {
           //yz1 plane  ******* F3 changes ********
           face=3;    
           
           //swap f1 and f2
           for(l=1;l<=n;l++)  temp[l]=a[1][n][l];                       //temp=f1
           for(l=n,m=1;(l>=1)&&(m<=n);l--,m++) a[1][n][m]=a[2][1][l];   //f1=f2
           for(l=1,m=n;(l<=n)&&(m>=1);l++,m--) a[2][1][m]=temp[l];     //f2=temp
           
           //swap f5 f6
           for(l=1;l<=n;l++)  temp[l]=a[5][l][n];                       //temp=f5
           for(l=n,m=1;(l>=1)&&(m<=n);l--,m++) a[5][m][n]=a[6][l][1];   //f5=f6
           for(l=n,m=1;(l>=1)&&(m<=n);l--,m++) a[6][m][1]=temp[l];     //f6=temp
 
           for(i=1;i<=n;i++)
           {
             for(j=1;j<=n;j++)
             {
               t[n-i+1][n-j+1]=a[face][i][j];
             }
           }
           for(i=1;i<=n;i++)
           {
             for(j=1;j<=n;j++)
             {
               a[face][i][j]=t[i][j];
             }
           }
         }
         
         else //Layer is n
         {
         //yzn plane  ******* F4 changes ********
           face=4;

           //swap f1 and f2
           for(l=n;l>=1;l--)  temp[l]=a[1][1][l];                       //temp=f1
           for(l=1,m=n;(m>=1)&&(l<=n);m--,l++) a[1][1][m]=a[2][n][l];   //f1=f2
           for(l=n,m=1;(m<=n)&&(l>=1);m++,l--) a[2][n][m]=temp[l];     //f2=temp

           //swap f5 f6
           for(l=n;l>=1;l--)  temp[l]=a[5][l][1];                       //temp=f5
           for(m=n,l=1;(m>=1)&&(l<=n);m--,l++) a[5][m][1]=a[6][l][n];   //f5=f6
           for(l=n,m=1;(l>=1)&&(m<=n);l--,m++) a[6][m][n]=temp[l];     //f6=temp

           for(i=1;i<=n;i++)
           {
             for(j=1;j<=n;j++)
             {
               t[n-i+1][n-j+1]=a[face][i][j];
             }
           }
           for(i=1;i<=n;i++)
           {
             for(j=1;j<=n;j++)
             {
               a[face][i][j]=t[i][j];
             }
           }

         } //else closing
       } //if ending checking for layer is '1' or 'n'
       
       else  // that is 1<layer<n
       {     //yzk where 1<k<n
         
           //swap f1 and f2
           for(l=1;l<=n;l++)  temp[l]=a[1][n-layer+1][l];                                       //temp=f1
           for(l=n,m=1;(l>=1)&&(m<=n);l--,m++) a[1][n-layer+1][m]=a[2][layer][l];               //f1=f2
           for(l=1,m=n;(l<=n)&&(m>=1);l++,m--) a[2][layer][m]=temp[l];                         //f2=temp

           //swap f5 f6
           for(l=1;l<=n;l++)  temp[l]=a[5][l][n-layer+1];                                           //temp=f5
           for(l=n,m=1;(l>=1)&&(m<=n);l--,m++) a[5][m][n-layer+1]=a[6][l][n-layer+1];               //f5=f6
           for(l=1,m=n;(l<=n)&&(m>=1);l++,m--) a[6][m][layer]=temp[l];                         //f6=temp
  
       }
     }//degree 180 anti-clock or 180 clock
     
    else if( (degree==270 && direction==-1) ||(degree==90 && direction==1)) //270 anti-clock or 90 clock
     {
       if(layer==1 ||layer==n)
       {
         int face;
         if(layer==1)
         {
           //yz1 plane  ******* F3 changes ********
           face=3;

           
           for(l=1;l<=n;l++)  temp[l]=a[6][l][1];                          //temp=f6
           for(l=1;(l<=n);l++) a[6][l][1]=a[1][n][l];                      //f6=f1
           for(l=1,m=n;(l<=n)&&(m>=1);l++,m--) a[1][n][m]=a[5][l][n];      //f1=f5
           for(l=1;(l<=n);l++) a[5][l][n]=a[2][1][l];                      //f5=f2
           for(l=1,m=n;(m>=1)&&(l<=n);l++,m--) a[2][1][m]=temp[l];         //f2=temp

           for(i=1;i<=n;i++)
           {
             for(j=1;j<=n;j++)
             {
               t[j][n-i+1]=a[face][i][j];
             }
           }
           for(i=1;i<=n;i++)
           {
             for(j=1;j<=n;j++)
             {
               a[face][i][j]=t[i][j];
             }
           }
         }
       else
       {

      //yzn plane  ******* F4 changes ********
           face=4;
           for(l=1;l<=n;l++)  temp[l]=a[6][l][n];                          //temp=f6
           for(l=1;(l<=n);l++) a[6][l][n]=a[1][1][l];                      //f6=f1
           for(l=1,m=n;(l<=n)&&(m>=1);l++,m--) a[1][1][m]=a[5][l][1];      //f1=f5
           for(l=1;(l<=n);l++) a[5][l][1]=a[2][n][l];                      //f5=f2
           for(l=1,m=n;(m>=1)&&(l<=n);l++,m--) a[2][n][m]=temp[l];         //f2=temp

           for(i=1;i<=n;i++)
           {
             for(j=1;j<=n;j++)
             {
               t[n-j+1][i]=a[face][i][j];
             }
           }
           for(i=1;i<=n;i++)
           {
             for(j=1;j<=n;j++)
             {
               a[face][i][j]=t[i][j];
             }
           }
       }

      } //ending of 1 or n checking 

     else //1<layer<n
    {
   
          
           for(l=1;l<=n;l++)         temp[l]=a[6][l][layer];                                      //temp=f6
           for(l=1;(l<=n);l++)       a[6][l][layer]=a[1][n-layer+1][l];                           //f6=f1
           for(l=1,m=n;(l<=n)&&(m>=1);l++,m--) a[1][n-layer+1][m]=a[5][l][n-layer+1];             //f1=f5
           for(l=1;l<=n;l++)  a[5][l][n-layer+1]=a[2][layer][l];                                  //f5=f2
           for(l=1,m=n;(l<=n)&&(m>=1);l++,m--) a[2][layer][m]=temp[l];                            //f2=temp
  


    }

   }//ending of 270 anti-clock or 90 clock
    
}//ending of method yzLayer

public void xzLayer(int degree,int direction,int layer)
{
  
int[] temp=new int[n+10];
  int[][] t=new int[n+10][n+10];

  if((degree==90 && direction==-1) || (degree==270 && direction==1)) //90 anti-clock  or 270 clock
  {
     if(layer==1 ||layer==n)
     {
       int face;
       if(layer==1)
       {
          //xz1 plane  **** F5 changes *****
          for(l=1;l<=n;l++)    temp[l]=a[1][l][1];                       //temp=f1
          for(l=1;(l<=n);l++)  a[1][l][1]=a[3][l][1];                    //f1=f3
          for(l=1;l<=n;l++)    a[3][l][1]=a[2][l][1];                    //f3=f2
          for(l=1,m=n;(l<=n)&&(m>=1);l++,m--) a[2][m][1]=a[4][l][n];     //f2=f4
          for(l=1,m=n;(l<=n)&&(m>=1);l++,m--)    a[4][m][n]=temp[l];     //f4=temp
       
         // F5  changes  copying into 't'  and then again 't'  to  'a'
          for(i=1;i<=n;i++)
          {
            for(j=1;j<=n;j++)  t[n-j+1][i]=a[5][i][j];
          }
         for(i=1;i<=n;i++)
          {
            for(j=1;j<=n;j++)   a[5][i][j]=t[i][j];
          }
        
       }
       else //Layer is n
       {
          //xzn plane  ******* F6 changes ********
          for(l=1;l<=n;l++)  temp[l]=a[1][l][n];                       //temp=f1
          for(l=1;(l<=n);l++) a[1][l][n]=a[3][l][n];                   //f1=f3
          for(l=1;l<=n;l++) a[3][l][n]=a[2][l][n];                     //f3=f2
          for(l=1,m=n;(m>=1)&&(l<=n);l++,m--) a[2][m][n]=a[4][l][1];   //f2=f4
          for(l=1,m=n;(m>=1)&&(l<=n);m--,l++)    a[4][m][1]=temp[l];                     //f4=temp
          // F4 changes  copying into 't'  and then again 't'  to  'a'
          for(i=1;i<=n;i++)
          {
            for(j=1;j<=n;j++)  t[j][n-i+1]=a[6][i][j];
          }
          for(i=1;i<=n;i++)
          {
           for(j=1;j<=n;j++)   a[6][i][j]=t[i][j];
          }
        }
       
     } //if ending checking for layer is '1' or 'n'
    
     else //1<layer<k
    {  

           for(l=1;l<=n;l++)         temp[l]=a[1][l][layer];                                     //temp=f1
           for(l=1;(l<=n);l++)       a[1][l][layer]=a[3][l][layer];                              //f1=f3
           for(l=1;l<=n;l++)         a[3][l][layer]=a[2][l][layer];                              //f3=f2
           for(l=1,m=n;(l<=n)&&(m>=1);l++,m--) a[2][m][layer]=a[4][l][n-layer+1];                //f2=f4
           for(l=1,m=n;(l<=n)&&(m>=1);l++,m--) a[4][m][n+layer-1]=temp[l];                       //f4=temp

    } 

 }//Chcking of 90 anti-clock and 270 clocl ending


 else if((degree==180 && direction==-1) || (degree==180 && direction==1)) //180 anti-clock  or 180 clock
  {
     if(layer==1 ||layer==n)
     {
       int face;
       if(layer==1)
       {
          //xz1 plane  **** F5 changes *****
          for(l=1;l<=n;l++)    temp[l]=a[1][l][1];                        //temp=f1
          for(l=1;(l<=n);l++)  a[1][l][1]=a[2][l][1];                     //f1=f2
          for(l=1;l<=n;l++)    a[2][l][1]=temp[l];                        //f2=temp
          for(l=1;l<=n;l++)    temp[l]=a[3][l][1];                        //temp=f3
          for(l=1,m=n;(l<=n)&&(m>=1);l++,m--) a[3][m][1]=a[4][l][n];      //f3=f4
          for(l=1,m=n;(l<=n)&&(m>=1);l++,m--)    a[4][m][n]=temp[l];      //f4=temp
          

         // F5  changes  copying into 't'  and then again 't'  to  'a'
          for(i=1;i<=n;i++)
          {
            for(j=1;j<=n;j++)  t[n-i+1][n-j+1]=a[5][i][j];
          }
         for(i=1;i<=n;i++)
          {
            for(j=1;j<=n;j++)   a[5][i][j]=t[i][j];
          }
       } 
      else
      {
          //xzn plane  **** F6 changes *****
          for(l=1;l<=n;l++)    temp[l]=a[1][l][n];                        //temp=f1
          for(l=1;(l<=n);l++)  a[1][l][n]=a[2][l][n];                     //f1=f2
          for(l=1;l<=n;l++)    a[2][l][n]=temp[l];                        //f2=temp
          for(l=1;l<=n;l++)    temp[l]=a[3][l][n];                        //temp=f3
          for(l=1,m=n;(l<=n)&&(m>=1);l++,m--) a[3][m][n]=a[4][l][1];      //f3=f4
          for(l=1,m=n;(l<=n)&&(m>=1);l++,m--)    a[4][m][1]=temp[l];      //f4=temp
          

         // F6  changes  copying into 't'  and then again 't'  to  'a'
          for(i=1;i<=n;i++)
          {
            for(j=1;j<=n;j++)  t[n-i+1][n-j+1]=a[6][i][j];
          }
         for(i=1;i<=n;i++)
          {
            for(j=1;j<=n;j++)   a[6][i][j]=t[i][j];
          }
       } 

     } //checking of 1 or n ending

    else //1<layer<n 

     {  

          for(l=1;l<=n;l++)    temp[l]=a[1][l][layer];                          //temp=f1
          for(l=1;(l<=n);l++)  a[1][l][layer]=a[2][l][layer];                   //f1=f2
          for(l=1;l<=n;l++)    a[2][l][layer]=temp[l];                          //f2=temp
          for(l=1;l<=n;l++)    temp[l]=a[3][l][layer];                          //temp=f3
          for(l=1,m=n;(l<=n)&&(m>=1);l++,m--) a[3][m][layer]=a[4][l][n-layer+1]; //f3=f4
          for(l=1,m=n;(l<=n)&&(m>=1);l++,m--)    a[4][m][n-layer+1]=temp[l];             //f4=temp
     }
   
  } //checking of 180 anticlock and 180 clock


else if((degree==270 && direction==-1) || (degree==90 && direction==1)) //270 anti-clock  or 90 clock
  {
     if(layer==1 ||layer==n)
     {
       int face;
       if(layer==1)
       {
          //xz1 plane  **** F5 changes *****
          for(l=1;l<=n;l++)    temp[l]=a[3][l][1];                                        //temp=f3
          for(l=1;(l<=n);l++)  a[3][l][1]=a[1][l][1];                                     //f3=f1
          for(l=1,m=n;(l<=n)&&(m>=1);l++,m--)    a[1][m][1]=a[4][l][n];                   //f1=f4
          for(l=1,m=n;(l<=n)&&(m>=1);l++,m--)  a[4][m][n]=a[2][l][1];                      //f4=f2
          for(l=1;(l<=n);l++)                   a[2][l][1]=temp[l];                      //f2=temp

         // F5  changes  copying into 't'  and then again 't'  to  'a'
          for(i=1;i<=n;i++)
          {
            for(j=1;j<=n;j++)  t[j][n-i+1]=a[5][i][j];
          }
         for(i=1;i<=n;i++)
          {
            for(j=1;j<=n;j++)   a[5][i][j]=t[i][j];
          }

       }
        else //Layer is n
       {
          //xzn plane  ******* F6 changes ********
          for(l=1;l<=n;l++)  temp[l]=a[3][l][n];                                        //temp=f3
          for(l=1;(l<=n);l++) a[3][l][n]=a[1][l][n];                                     //f3=f1
          for(l=1,m=n;(l<=n)&&(m>=1);l++,m--) a[1][m][n]=a[4][l][1];                     //f1=f4
          for(l=1,m=n;(m>=1)&&(l<=n);l++,m--) a[4][m][1]=a[2][l][n];                     //f4=f2
          for(l=1;(l<=n);l++)                 a[2][l][n]=temp[l];                        //f2=temp
          // F4 changes  copying into 't'  and then again 't'  to  'a'
          for(i=1;i<=n;i++)
          {
            for(j=1;j<=n;j++)  t[n-j+1][i]=a[6][i][j];
          }
          for(i=1;i<=n;i++)
          {
           for(j=1;j<=n;j++)   a[6][i][j]=t[i][j];
          }
        }

     } //if ending checking for layer is '1' or 'n'

     else   //1<layer<n
    {

           for(l=1;l<=n;l++)                    temp[l]=a[3][l][layer];                        //temp=f3
           for(l=1;(l<=n);l++)                  a[3][l][layer]=a[1][l][layer];                 //f3=f1
           for(l=1,m=n;(l<=n)&&(m>=1);l++,m--)  a[1][m][layer]=a[4][l][layer];                 //f1=f4
           for(l=1,m=n;(l<=n)&&(m>=1);l++,m--)  a[4][m][n-layer+1]=a[2][l][layer];             //f4=f2
           for(l=1;(l<=n);l++)                  a[2][l][layer]=temp[l];                            //f2=temp

    }

 }//Chcking of 270 anti-clock and 90 clocl ending


}//xzLayer Ending

}//Ending of Myclass 



class RubixCube
{
  public static void main(String args[])
  {
    
   RubixDataStructureClass obj=new RubixDataStructureClass(Integer.parseInt(args[0]));  //loading cube 
   String choice;
   Scanner s=new Scanner(System.in);
 
   //Line 639 @@@@@@@@@@@@@@@@@@@@@@@@@@@ Here Android team will Provide the ATTRIBUTES OF MOVE @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
    do
     {
       System.out.println("Enter Axis Layer Direction Degree:");
       String axis = s.next();
       int layer=s.nextInt();
       int dir=s.nextInt();
       int degree=s.nextInt();
       if(axis.compareTo("XY")==0||axis.compareTo("xy")==0||axis.compareTo("YX")==0||axis.compareTo("yx")==0)
       {
         obj.xyLayer(degree,dir,layer);
       }
      else if(axis.compareTo("YZ")==0||axis.compareTo("yz")==0 || axis.compareTo("ZY")==0 || axis.compareTo("zy")==0)
       {
         obj.yzLayer(degree,dir,layer);
       }
      else if(axis.compareTo("XZ")==0||axis.compareTo("xz")==0 || axis.compareTo("ZX")==0 || axis.compareTo("zx")==0)
       {
         obj.xzLayer(degree,dir,layer);
       }
       else 
       { 
         System.out.println("last move  unidentified");
       }
       
       System.out.println("*******************");
       obj.printData();
       System.out.println("Do you want to continue  y | n ");
       choice=s.next();
     } while(choice.compareTo("y")==0||choice.compareTo("Y")==0);


     

  }
}
