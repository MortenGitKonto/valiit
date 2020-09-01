package ee.bcs.valiit.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@RestController
public class GameController {

    private final Map<Integer, Integer> game = new HashMap();

    public static int randomGame() {
        // TODO kirjuta mäng mis võtab suvalise numbri 0-100, mille kasutaja peab ära arvama
        // iga kord pärast kasutaja sisestatud täis arvu peab programm ütlema kas number oli suurem või väiksem
        // ja kasutaja peab saama uuesti arvata
        // numbri ära arvamise korral peab programm välja trükkima mitu katset läks numbri ära arvamiseks

        Random random = new Random();
        int arvRandom = random.nextInt(100);
        //System.out.println(arv);
        return arvRandom;
    }

    //////////////////////////////////////////////////////////////////////////////
    @PostMapping("/generateNR")
    public void generateNumber() {

        Random random = new Random();
        int arvRandom = random.nextInt(100);
        game.put(0, arvRandom);
    }

    @GetMapping("/number")
    public int seeNr() {
        return game.get(0);
    }

    @GetMapping("/try/{a}")
    public String myNumber(@PathVariable("a") int x) {
        String ok = "õige!";
        String väike = "vale, proovi väiksemat!";
        String suur = "Vale, proovi suuremat!";

        if (x == game.get(0)) {
            //System.out.println("Õige!");
            return ok;
        } else if (x < game.get(0)) {
            //System.out.println("Vale, proovi suuremat!");
            return suur;
        } else {
            //System.out.println("Vale, proovi väiksemat!");
            return väike;
        }
    }
}
