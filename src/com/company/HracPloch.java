package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class HracPloch {
    public Map<Integer, Figs> plocha = new HashMap<>();
    public int delkaPlochy;

    public List<BarFig> hraci = new ArrayList<>();
    public int pocetHracu;

    public Map<BarFig, StartDomecek> domecky = new HashMap<>();
    public Map<BarFig, CilDomecek> cile = new HashMap<>();

    public int pocetFigurek;
    public Kostka kostka;

    public BarFig praveHraje;

    public HracPloch(int vel, int pocFig, int pocHracu, Kostka k) {
        delkaPlochy = vel;
        pocetFigurek = pocFig;
        pocetHracu = pocHracu;
        kostka = k;

        for(int i = 0; i < pocetHracu; i++) {
            BarFig novyHrac = new BarFig(i, 0, 0);

            hraci.add(novyHrac);

            domecky.put(novyHrac, new StartDomecek(novyHrac, pocetFigurek));
            cile.put(novyHrac, new CilDomecek(novyHrac, pocetFigurek));
        }
    }

    public boolean jeVolno(int kde) {
        return plocha.get(kde) == null;
    }

    public void nasaditFigurku() {
        if(jeVolno(praveHraje.getStartovniPole())) {
            plocha.put(praveHraje.getStartovniPole(), domecky.get(praveHraje).nasaditFig());
        }
        else if(plocha.get(praveHraje.getStartovniPole()).getBarva() != praveHraje) {
            vyhodit(praveHraje.getStartovniPole());
            plocha.put(praveHraje.getStartovniPole(), domecky.get(praveHraje).nasaditFig());
        }
    }

    public void posunFigurky(Figs jaka, int odkud, int kam) {
        if(!cile.get(praveHraje).getCil().containsValue(jaka)) {
            if(jeVolno(spocitanaCesta(kam))) {
                plocha.put(spocitanaCesta(kam), jaka);
                plocha.remove(odkud);
            }
            else if(plocha.get(spocitanaCesta(kam)).getBarva() != praveHraje) {
                vyhodit(spocitanaCesta(kam));
                plocha.put(spocitanaCesta(kam), jaka);
            }
            else if(kam > praveHraje.getVstupDoCile()) {
                int kamDoCile = kam - praveHraje.getVstupDoCile() + 1;
                if(cile.get(praveHraje).jeVolno(kamDoCile)) {
                    cile.get(praveHraje).jeVolno(kamDoCile);
                }
            }
        }
        else {
            if(cile.get(praveHraje).jeVolno(kam)) {
                cile.get(praveHraje).posunVCili(jaka, odkud, kam);
            }
        }
    }

    public void vyhodit(int kde) {
        BarFig koho = plocha.get(kde).getBarva();
        domecky.get(koho).vratitFig(plocha.get(kde));
        plocha.remove(kde);
    }

    public void konecTahu() {
        praveHraje = hraci.get(praveHraje.getPoradi() + 1);
    }

    public void konecHry() {
        // KONEC
    }

    public int spocitanaCesta(int kam) {
        return (kam > delkaPlochy - 1) ? kam - delkaPlochy : kam;

    }
}
