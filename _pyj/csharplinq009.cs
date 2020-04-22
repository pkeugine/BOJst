using System;  
using System.Collections.Generic;  
using System.Linq;  
using System.Text;  

class LinqExercise9  
    {  
        static void Main(string[] args)  
        {  
		int i=0;
            List<int> templist = new List<int>();  
            templist.Add(55);  
            templist.Add(200);  
            templist.Add(740);  
            templist.Add(76);  
            templist.Add(230);  
            templist.Add(482);  
            templist.Add(95);  
			
            Console.Write("\nLINQ : Create a list of numbers and display the numbers greater than 80 : "); 
            Console.Write("\n-------------------------------------------------------------------------\n");				
            Console.WriteLine("\nThe members of the list are : ");            
            foreach (var lstnum in templist)  
            {  
                Console.Write(lstnum+" ");  
            }  
            List<int> FilterList = templist.FindAll(x => x > 80 ? true : false);  
            Console.WriteLine("\n\nThe numbers greater than 80 are : ");
            foreach (var num in FilterList)  
            {  
                Console.WriteLine(num);  
            }  
            Console.ReadLine();  
        }  
    }
