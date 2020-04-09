package main

import (
  "fmt"
  "bufio"
  "os"
  "strconv"
  "strings"
)

func main() {
  reader := bufio.NewReader(os.Stdin)
  x, _ := reader.ReadString(' ')
  y, _ := reader.ReadString('\n')
  x = strings.Replace(x, " ", "", -1)
  y = strings.Replace(y, "\n", "", -1)

  num1, e1 := strconv.Atoi(x)
  if e1 != nil {
    fmt.Println("conversion error:", x)
  }
  num2, e2 := strconv.Atoi(y)
  if e2 != nil {
    fmt.Println("conversion error:", y)
  }

  fmt.Println(num1 + num2)
  fmt.Println(num1 - num2)
  fmt.Println(num1 * num2)
  fmt.Println(num1 / num2)
  fmt.Println(num1 % num2)
}
