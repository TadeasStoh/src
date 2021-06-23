package com.company;
import java.util.Map;
import java.util.HashMap;

public class CilDomecek {
    private BarFig barva;

    private Map<Integer, Figs> cil;
    private int pocetFigurek;

    public CilDomecek(BarFig b, int pocFigurek) {
        barva = b;
        pocetFigurek = pocFigurek;

        cil = new HashMap<>(pocetFigurek);
    }

    public boolean jeVolno(int kde) {
        return cil.containsKey(kde);
    }

    public void jitDoCile(Figs kdo, int kam) {
        cil.put(kam, kdo);
        if(cil.size() == pocetFigurek) {
            barva.konecProMe(barva);
        }
    }

    public void posunVCili(Figs kdo, int odkud, int kam) {
        cil.put(kam, kdo);
        cil.remove(odkud);
    }

    public Map<Integer, Figs> getCil() {
        return cil;
    }
}

