package org.compte.interfaces;

import org.compte.models.Compte;

public interface PassageEnNegatifSubscriber {

    void execute(Compte c);
}
