using System;
using System.Collections.Generic;
using System.Linq;

namespace exercises
{
  class Program
  {
    static void Main(string[] args)
    {
      List<int> myList = test(new List<int>(new int[] { 10, 22, 35, 47, 53, 67 }));
      foreach (var i in myList)
      {
        Console.Write(i.ToString() + " ");
      }
    }
    public static List<int> test(List<int> nums)
    {
      return nums.Where(n => n % 10 < 7).ToList();
    }
  }
}
