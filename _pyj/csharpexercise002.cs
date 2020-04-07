using System;

namespace exercise
{
    class Program
    {
        static void Main(string[] args)
        {
            Console.WriteLine(test(53));
            Console.WriteLine(test(30));
            Console.WriteLine(test(51));
            Console.ReadLine();
        }

        public static void test(int n)
        {
            return n > 51 ? (n - 51) * 3 : 51 - n;
        }
    }
}
