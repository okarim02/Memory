package src.Memo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlMenu implements ActionListener {

    Fenetre fen;
    Model model;

    public ControlMenu(Fenetre f,Model m ){
        fen=f;
        model=m;
        fen.setControMenu(this);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        if(fen.getMeilleursScores().equals(actionEvent.getSource())) {
            fen.showBestScores();
        }

        if(fen.getNouvellePartie().equals(actionEvent.getSource())){
            model.restart();
            fen.restart();
        }

        if(actionEvent.getSource()==(fen.getOption3())){
            model.setActivePiegeCard(true);
            set(3);
        }else if(actionEvent.getSource()==(fen.getOption4())){
            model.setActivePiegeCard(false);
            set(4);

        }else if(actionEvent.getSource()==(fen.getOption5())){
            model.setActivePiegeCard(true);
            set(5);
        }

    }

    public void set(int diff){
        model.setTailleJeuLignes(diff);model.setTailleJeuColonnes(diff);
        model.restart();
        fen.restart();
        System.out.println("changement mode : " + model.getTailleJeuLignes() + "x"+model.getTailleJeuColonnes());
    }

}
