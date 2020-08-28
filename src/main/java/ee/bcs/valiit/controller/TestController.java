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

    ///////FIBONACCI/////////
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


    ///////FIBONACCI/////////

    //Esimese osa mõtlen välja, see on fikseeritud. Teine osa {Fibonacci} on sissekirjutatav, et muuta lehe sisu
    @GetMapping("/Fiboarvutus/{Fibonacci}")
    //PathVariable peab olema sama mis {Fibonacci} getmappingus. Integer x deklareeritakse kui sissekirjutatud asi,
    public Integer test(@PathVariable("Fibonacci") Integer x) {


        //@RequestParam("fibonacciNth")Integer id
        return fibonacci(x); //kogu asi returnib sissekirjutatud osaga tehtava meetodi returnitud väärtuse.
    }
}
