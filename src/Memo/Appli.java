package src.Memo;

public class Appli {
    public static void main(String[] args) {
        // https://cours-info.iut-bm.univ-fcomte.fr/upload/supports/S2/IHM/projet/memory.pdf
        Model model = new Model();
        Fenetre f = new Fenetre(model);
        // controlleurs.
        ControlBouton controlBouton = new ControlBouton(f,model);
        ControlMenu controlMenu = new ControlMenu(f,model);


    }
}
