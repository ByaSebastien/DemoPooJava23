package org.compte.models;

import org.compte.exceptions.MontantNegatifException;
import org.compte.exceptions.SoldeInsuffisantException;
import org.compte.interfaces.PassageEnNegatifSubscriber;

import java.util.ArrayList;
import java.util.List;

public class Compte {

    private final List<PassageEnNegatifSubscriber> passageEnNegatifEvent = new ArrayList<>();

    private String numero;

    public String getNumero() {
        return numero;
    }

    private double solde;

    private double ligneDeCredit;


    public Compte(String numero, double ligneDeCredit){

        this.numero = numero;
        this.ligneDeCredit = ligneDeCredit;
        this.solde = 0;
    }

    private void setSolde(double solde){

        this.solde = solde;
    }

    public double getSolde() {

        return solde;
    }

    private void setLigneDeCredit(double ligneDeCredit) throws MontantNegatifException {

        if(ligneDeCredit < 0)
            throw new MontantNegatifException("Ligne de crédit doit etre positive");
        this.ligneDeCredit = ligneDeCredit;
    }

    public double getLigneDeCredit() {
        return ligneDeCredit;
    }

    public void retrait(double montant) throws Exception{

        double ancientSolde = getSolde();

        if(montant < 0)
            throw new MontantNegatifException("Le montant doit etre positif");
        if(solde - montant < -getLigneDeCredit())
            throw new SoldeInsuffisantException();

        setSolde(getSolde() - montant);

        if(ancientSolde > 0 && getSolde() < 0)
            raisePassageEnNegatifEvent();
    }

    public void depot(double montant) throws Exception{

        if(montant < 0)
            throw new MontantNegatifException();

        setSolde(getSolde() + montant);
    }

    public void addPassageEnNegatifSubscriber(PassageEnNegatifSubscriber subscriber){

        passageEnNegatifEvent.add(subscriber);
    }

    public void removePassageEnNegatifSubscriber(PassageEnNegatifSubscriber subscriber){

        passageEnNegatifEvent.remove(subscriber);
    }

    public void raisePassageEnNegatifEvent(){

        for (PassageEnNegatifSubscriber subscriber : passageEnNegatifEvent){

            subscriber.execute(this);
        }
    }
}
