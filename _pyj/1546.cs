using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
 
namespace _1546
{
    class Program
    {
        static void Main(string[] args)
        {
            var n = int.Parse(Console.ReadLine());
            int[] arr = new int[n];
            var input = Console.ReadLine();
            var arr2 = input.Split(' ');
            for (int i = 0; i < arr2.Length; i++) {
                arr[i] = int.Parse(arr2[i]);
            }
 
            int M = 0;
            for (int i = 0; i < arr.Length; i++) {
                if (M < arr[i]) {
                    M = arr[i];
                }
            }
 
            float sum = 0;
            for (int i = 0; i < arr.Length; i++)
            {
                var score = (float)arr[i] / (float)M * 100;
                sum += score;
            }
            Console.WriteLine(sum/(float)n);
        }
    }
}
