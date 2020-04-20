using System;  
using System.Collections.Generic;  
using System.Linq;  
using System.Text;  


class LinqExercise11 
    {  
        static void Main(string[] args)  
      {  
          
        List<int> templist = new List<int>();
        templist.Add(5);
        templist.Add(7);
        templist.Add(13);
        templist.Add(24);
        templist.Add(6);
        templist.Add(9);
        templist.Add(8);
        templist.Add(7);

        Console.Write("\nLINQ : Display top nth  records from the list : "); 
        Console.Write("\n----------------------------------------------\n");
        
           Console.WriteLine("\nThe members of the list are : ");            
            foreach (var lstnum in templist)  
            {  
                Console.WriteLine(lstnum+" ");  
            }          
        
        Console.Write("How many records you want to display ? : ");  
        int n= Convert.ToInt32(Console.ReadLine()); 

        templist.Sort();
        templist.Reverse();

        Console.Write("The top {0} records from the list are : \n",n);  
        foreach (int topn in templist.Take(n))
            {
                Console.WriteLine(topn);
            }
        }  
    }
