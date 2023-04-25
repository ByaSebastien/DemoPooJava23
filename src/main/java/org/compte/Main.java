package org.compte;

import org.compte.exceptions.MontantNegatifException;
import org.compte.exceptions.SoldeInsuffisantException;
import org.compte.models.Compte;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        Compte c = new Compte("1", 100);
        c.addPassageEnNegatifSubscriber( toto -> System.out.println("le compte numero " + toto.getNumero() + " est passé en negatif."));
        c.addPassageEnNegatifSubscriber( compte -> System.out.println("Nouveau solde " + compte.getSolde()));
        c.addPassageEnNegatifSubscriber( compte -> System.out.println("c'est quand meme pas mal..."));
        c.addPassageEnNegatifSubscriber( (compte) -> {
            System.out.println("j ai acces au compte numero " + compte.getNumero());
        });
        Compte c2 = new Compte("2",100);




        try {
            c.depot(100);
            c.retrait(150);
            c2.depot(100);
            c2.retrait(150);
            System.out.println("Ok");
        }
        catch(MontantNegatifException e){
            System.out.println("T'es con ou quoi?");
            System.out.println(e.getMessage());
        }
        catch (SoldeInsuffisantException e){
            System.out.println("Va travailler ou faire un formation pour devenir un super dev");
            System.out.println(e.getMessage());
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Finis");
    }
}
