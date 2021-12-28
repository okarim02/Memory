package src.Memo;

import javax.swing.*;
import java.awt.*;

public class Fenetre extends JFrame {

    JMenuBar menuBar ;
    JMenu menu;
    JMenuItem nouvellePartie;
    JMenuItem meilleursScores;
    JMenu taille;
    JMenuItem option3;
    JMenuItem option4;
    JMenuItem option5;

    Carte[][] cartes;

    JLabel lTemps;
    JLabel temps;

    JLabel lEssais;
    JLabel essais;

    Model model;
    ControlBouton controlBouton;
    ControlMenu controlMenu;

    JDialog bestScores;
    JLabel scores;
    JLabel score1;
    JLabel score2;
    JLabel score3;

    JDialog gameOver;
    JLabel gameOverText;



    public Fenetre(Model model){
        this.model = model;
        cartes = model.getCartes();
        initAttribut();
        ajouterWidgetVersion();
        pack();
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void restart(){
        this.getContentPane().removeAll();
        cartes=model.getCartes();

        for (Carte[] carte : cartes) {
            for (int j = 0; j < cartes[0].length; j++) {
                carte[j].addActionListener(this.getControlBouton());
            }
        }

        this.ajouterWidgetVersion();

        //cartes=model.getCartes();
        essais.setText(String.valueOf(model.getVie()));
        // reset le temps.
        this.getTemps().setText("0.0");
        this.setVisible(true);

    }

    public void initAttribut() {

        lTemps = new JLabel("Temps : ");
        temps = new JLabel("0.0");
        lEssais = new JLabel("Essais restant : ");
        essais=new JLabel(String.valueOf(model.getVie()));



        gameOver = new JDialog(this,"Game over ...");

        creeMenu();
    }



    public void showBestScores(){
        bestScores = new JDialog(this,"Best scores");
        bestScores.setSize(250,150);

        // Problème par rapport à ',' -> création d'une méthode : settingScores.
        scores = new JLabel("Best scores en " + model.getTailleJeuLignes() + "x" +model.getTailleJeuColonnes() + ": ");
        score1=new JLabel("1 - "+String.valueOf(model.getBestScores()[0]));
        score2=new JLabel("2 - "+String.valueOf(model.getBestScores()[1]));
        score3=new JLabel("3 - "+String.valueOf(model.getBestScores()[2]));


        // scores
        JPanel scoreArea = new JPanel();
        scoreArea.setLayout(new BoxLayout(scoreArea,BoxLayout.Y_AXIS));
        scoreArea.add(scores);
        scoreArea.add(score1);
        scoreArea.add(score2);
        scoreArea.add(score3);

        bestScores.add(scoreArea);
        bestScores.setVisible(true);
    }

    public void showGameOver() {
        gameOverText=new JLabel("Game over ... try again");
        gameOver.add(gameOverText);
        gameOver.setVisible(true);
    }

    public void creeMenu(){
        menuBar = new JMenuBar();
        menu = new JMenu("Option");
        nouvellePartie = new JMenuItem("Nouvelle partie ");
        meilleursScores = new JMenuItem("Meilleurs scores");
        menu.add(nouvellePartie);
        menu.add(meilleursScores);
        menuBar.add(menu);
        taille = new JMenu("Taille jeu ");
        option3 = new JMenuItem("3x3");
        option4 = new JMenuItem("4x4");
        option5 = new JMenuItem("5x5");
        taille.add(option3);taille.add(option4);taille.add(option5);
        menuBar.add(taille);

        setJMenuBar(menuBar);

    }

    public void setControlButton(ControlBouton cb){
        controlBouton = cb;
    }

    public void setControMenu(ControlMenu cm){
        controlMenu = cm;
        nouvellePartie.addActionListener(controlMenu);
        meilleursScores.addActionListener(controlMenu);
        option3.addActionListener(controlMenu);
        option4.addActionListener(controlMenu);
        option5.addActionListener(controlMenu);


    }

    private void ajouterWidgetVersion() {

        JPanel pGeneral = new JPanel();
        pGeneral.setLayout(new BorderLayout());

        JPanel pTemps = new JPanel();
        pTemps.setLayout(new BoxLayout(pTemps,BoxLayout.X_AXIS));
        pTemps.add(lTemps);
        pTemps.add(temps);
        pGeneral.add(pTemps,BorderLayout.NORTH);

        JPanel pCartes = new JPanel(new GridLayout(model.tailleJeuLignes,model.tailleJeuColonnes,1,1));
        //
        for(int i =0 ;i<cartes.length;i++){
            for(int j = 0 ; j <cartes[0].length;j++){
                pCartes.add(cartes[i][j]);
            }
        }

        pGeneral.add(pCartes,BorderLayout.CENTER);

        JPanel pEssaies = new JPanel();
        pEssaies.add(lEssais);
        pEssaies.add(essais);
        pGeneral.add(pEssaies,BorderLayout.SOUTH);


        setContentPane(pGeneral);

    }

    public void pack(){
        setSize(400,400);
        setResizable(false);
        setTitle("Game of emoji!");
    }

    // getter and setter

    public void setMenuBar(JMenuBar menuBar) {
        this.menuBar = menuBar;
    }

    public JMenu getMenu() {
        return menu;
    }

    public void setMenu(JMenu menu) {
        this.menu = menu;
    }

    public Carte[][] getCartes() {
        return cartes;
    }

    public void setCartes(Carte[][] cartes) {
        this.cartes = cartes;
    }

    public JLabel getlTemps() {
        return lTemps;
    }

    public void setlTemps(JLabel lTemps) {
        this.lTemps = lTemps;
    }

    public JLabel getTemps() {
        return temps;
    }

    public void setTemps(JLabel temps) {
        this.temps = temps;
    }

    public JLabel getlEssais() {
        return lEssais;
    }

    public void setlEssais(JLabel lEssais) {
        this.lEssais = lEssais;
    }

    public JLabel getEssais() {
        return essais;
    }

    public void setEssais(JLabel essais) {
        this.essais = essais;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public ControlBouton getControlBouton() {
        return controlBouton;
    }

    public void setControlBouton(ControlBouton controlBouton) {
        this.controlBouton = controlBouton;
    }

    public JDialog getBestScores() {
        return bestScores;
    }

    public void setBestScores(JDialog bestScores) {
        this.bestScores = bestScores;
    }

    public JLabel getScores() {
        return scores;
    }

    public void setScores(JLabel scores) {
        this.scores = scores;
    }

    public JLabel getScore1() {
        return score1;
    }

    public void setScore1(JLabel score1) {
        this.score1 = score1;
    }

    public JLabel getScore2() {
        return score2;
    }

    public void setScore2(JLabel score2) {
        this.score2 = score2;
    }

    public JLabel getScore3() {
        return score3;
    }

    public void setScore3(JLabel score3) {
        this.score3 = score3;
    }

    public JMenuItem getNouvellePartie() {
        return nouvellePartie;
    }

    public void setNouvellePartie(JMenuItem nouvellePartie) {
        this.nouvellePartie = nouvellePartie;
    }

    public JMenuItem getMeilleursScores() {
        return meilleursScores;
    }

    public void setMeilleursScores(JMenuItem meilleursScores) {
        this.meilleursScores = meilleursScores;
    }

    public ControlMenu getControlMenu() {
        return controlMenu;
    }

    public void setControlMenu(ControlMenu controlMenu) {
        this.controlMenu = controlMenu;
    }

    public JDialog getGameOver() {
        return gameOver;
    }

    public void setGameOver(JDialog gameOver) {
        this.gameOver = gameOver;
    }

    public JLabel getGameOverText() {
        return gameOverText;
    }

    public void setGameOverText(JLabel gameOverText) {
        this.gameOverText = gameOverText;
    }


    public JMenu getTaille() {
        return taille;
    }

    public void setTaille(JMenu taille) {
        this.taille = taille;
    }

    public JMenuItem getOption3() {
        return option3;
    }

    public void setOption3(JMenuItem option3) {
        this.option3 = option3;
    }

    public JMenuItem getOption4() {
        return option4;
    }

    public void setOption4(JMenuItem option4) {
        this.option4 = option4;
    }

    public JMenuItem getOption5() {
        return option5;
    }

    public void setOption5(JMenuItem option5) {
        this.option5 = option5;
    }
}
