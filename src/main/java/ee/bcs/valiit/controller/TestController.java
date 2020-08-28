package ee.bcs.valiit.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/*@RestController
public class TestController {
    @GetMapping()
public Integer test(){
//@RequestParam("fibonacciNth")Integer id
return 3;
    }*/

@RestController
public class TestController {

    /*public int getNthOfFibonacci() {
        int element = 5;
        return fibonacci(element);
    }*/

    ///////FIBONACCI/////////11111111111111
    //Fibonacci meetod parameetriga n
    public static int fibonacci(int n) { //OK
        // TODO
        // Fibonacci jada on fib(n) = fib(n-1) + fib(n-2);
        int[] a = new int[n];
        a[0] = 0;
        a[1] = 1;
        int element = 0;
        for (int i = 0; i < n; i++) {
            if (i > 1) {
                a[i] = a[i - 2] + a[i - 1];
                element = a[i];
                //System.out.println("New a[i] " + a[i]);
            }
        }
        return element;
    }

    ///////2ST ARVUST VÄIKSEM/////////222222222222222222222222
    public static int min(int a, int b) {
        // TODO tagasta a ja b väikseim väärtus
        int min = 0;
        if (a < b) {
            min = a;
        } else {
            min = b;
        }
        return min;
    }

    ///////2ST ARVUST SUUREM/////////222222222222222222222222
    public static int max(int a, int b) {
        // TODO tagasta a ja b suurim väärtus
        int max = 0;
        if (a < b) {
            max = b;
        } else {
            max = a;
        }

        return max;
    }




    //Esimese osa mõtlen välja, see on fikseeritud. Teine osa {Fibonacci} on sissekirjutatav, et muuta lehe sisu
    @GetMapping("/Fiboarvutus/{Fibonacci}")
    //PathVariable peab olema sama mis {Fibonacci} getmappingus. Integer x deklareeritakse kui sissekirjutatud asi,
    public int test(@PathVariable("Fibonacci") int x) {

        //@RequestParam("fibonacciNth")Integer id
        return fibonacci(x); //kogu asi returnib sissekirjutatud osaga tehtava meetodi returnitud väärtuse.
    }

    @GetMapping("/min/{a}/{b}")
    public int miinimum(@PathVariable("a") int x, @PathVariable("b") int y){

        //@RequestParam("fibonacciNth")Integer id
        return min(x, y); //kogu asi returnib sissekirjutatud osaga tehtava meetodi returnitud väärtuse.
    }

    @GetMapping("/max/{a}/{b}")
    public int maksimum(@PathVariable("a") int x, @PathVariable("b") int y){

        //@RequestParam("fibonacciNth")Integer id
        return max(x, y); //kogu asi returnib sissekirjutatud osaga tehtava meetodi returnitud väärtuse.
    }





}
