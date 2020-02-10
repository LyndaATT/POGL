import java.util.ArrayList;

public class Circuit {

    public static void main(String[] args) {
        Circuit c = new Circuit();
         Noeud n1 = c.creeMultiplication(c.creeConstante(2),
                                      c.creeEntree());
         c.sortie = n1;
         System.out.println("Ceci doit afficher 2 : " + c.calcule(1));
         System.out.println("Ceci doit afficher 12 : " + c.calcule(6));
         Noeud n2 = c.creeMultiplication(n1,
                                      c.creeAddition(c.creeConstante(1),
                                                     n1));
         c.sortie = n2;
         System.out.println("Ceci doit afficher 6 : " + c.calcule(1));
         System.out.println("Ceci doit afficher 156 : " + c.calcule(6));
         System.out.println("Ceci doit afficher 2 : " + c.nbNoeudsMult());
         System.out.println("Ceci doit afficher 10 : " + c.nbOpEffectuees());
         Circuit p20 = expRapide(20);
         System.out.println("Ceci doit afficher 7 : " + p20.nbNoeudsMult());
         System.out.println("Ceci doit afficher 1048576 : " + p20.calcule(2));
         System.out.println("Ceci doit afficher 51 : " + p20.nbOpEffectuees());
         Circuit p20m = expRapideMemoisee(20);
         System.out.println("Ceci doit afficher 7 : " + p20m.nbNoeudsMult());
         System.out.println("Ceci doit afficher 1048576 : " + p20m.calcule(4));
       System.out.println("Ceci doit afficher la puissance 20 de 4 : " + p20m.calcule(3));
        System.out.println("Ceci doit afficher 7 : " + p20m.nbOpEffectuees());
         System.out.print("Ceci doit afficher ((2 * x) * (1 + (2 * x))) : ");
         c.affiche();
    }



    /**
     * Valeur de la variable dont dépend le calcul
     */
    private int entree;

    /**
     * Dernier noeud, calculant le résultat
     */
    private Noeud sortie;

    /**
     * Ensemble des noeuds
     */
    private ArrayList<Noeud> noeuds;

    /**
     * Constructeur.
     * N'initialise pas l'entrée : la méthode calcule s'en chargera avant
     * chaque calcul. La sortie n'est pas initialisée non plus pour éviter
     * un problème de circularité.
     */
    private Circuit() {
        this.noeuds = new ArrayList<Noeud>();
    }

    /**
     * Ajout d'un noeud à la liste
     * @param n Noeud à ajouter
     */
    private void ajouteNoeud(Noeud n) {
        noeuds.add(n);
    }


    // Création de noeuds avec ajout direct
    /**
     * Création d'un noeud de valeur constante
     * @param n Valeur calculée par le noeud
     */
    public Noeud creeConstante(int n) {
        Noeud c = new Constante(n);
        this.ajouteNoeud(c);
        return c;
    }

    /**
     *
     * @return
     */
    public  Noeud creeEntree(){
        Noeud n = new Entree(this);
        this.ajouteNoeud(n);
        return n;
    }

    /**
     *
     * @param n1
     * @param n2
     * @return
     */
    public Noeud creeAddition(Noeud n1, Noeud n2) {
        Noeud add = new Addition(n1, n2);
        this.ajouteNoeud(add);
        return add;
    }

    /**
     *
     * @param n1
     * @param n2
     * @return
     */
    public Noeud creeMultiplication(Noeud n1, Noeud n2){
        Noeud mul = new Multiplication(n1,n2);
        this.ajouteNoeud(mul);
        return mul;
    }
    /**
     * Donne la valeur de l'entrée, dont auront besoin certains noeuds
     */
    public int litEntree() {
        return this.entree;
    }

    /**
     * Initialise la variable d'entrée et calcule le résultat
     * @param e Valeur affectée à la variable d'entrée
     * @return Valeur calculée par le circuit
     */
    public int calcule(int e) {
        this.entree = e;
        return this.sortie.valeur();
    }

    /**
     * Construction d'un ensemble de noeuds utilisant la technique
     * d'exponentiation rapide
     * @param c Circuit auquel rattacher les noeuds créés
     * @param n Puissance calculée
     * @return Noeud principal
     */
    public static Noeud expRapide(Circuit c, int n) {
        if (n == 0) {
            // x^0 = 1
            return (c.creeConstante(1));
        } else if (n % 2 == 0) {
            // Si n pair, x^n = (x^{n/2})^2
            Noeud e = expRapide(c, n/2);
            return (c.creeMultiplication(e, e));
        } else {
            // Si n impair, x^n = x*((x^{n/2})^2)
            Noeud e = expRapide(c, n/2);
            return (c.creeMultiplication(c.creeEntree(),
                    c.creeMultiplication(e, e)));
        }
    }

    /**
     * Construction d'un circuit utilisant la technique d'exponentiation rapide
     * @param n Puissance calculée
     * @return Circuit calculant la n-ème puissance de sa variable d'entrée
     */
    public static Circuit expRapide(int n) {
        // On crée d'abord un circuit vide...
        Circuit c = new Circuit();
        // puis on y ajoute des nœuds, et on connecte le dernier à la sortie.
        c.sortie = expRapide(c, n);
        return c;
    }

    /**
     *
     * @param c
     * @param n
     * @return
     */
    public static Noeud expRapideMemoisee(Circuit c, int n) {
        if (n == 0) {
            // x^0 = 1
            return (c.creeConstante(1));
        } else if (n % 2 == 0) {
            // Si n pair, x^n = (x^{n/2})^2
            Noeud e = expRapideMemoisee(c, n/2);
            return (c.creeMultiplicationMemoisee(e, e));
        } else {
            // Si n impair, x^n = x*((x^{n/2})^2)
            Noeud e = expRapideMemoisee(c, n/2);
            return (c.creeMultiplicationMemoisee(c.creeEntree(),
                    c.creeMultiplication(e, e)));
        }
    }

    /**
     *
     * @param n1
     * @param n2
     * @return
     */
    public Noeud creeMultiplicationMemoisee(Noeud n1, Noeud n2) {
        Noeud m = new MultiplicationMemoisee(n1, n2);
        this.ajouteNoeud(m);
        return m;
    }

    /**
     *
     * @param n
     * @return
     */
    public static Circuit expRapideMemoisee(int n) {
        Circuit c = new Circuit();
        c.sortie = expRapideMemoisee(c, n);
        return c;
    }

    // Pour réinitialiser, on fait une boucle sur les nœuds et on délègue à
    // chaque nœud la tâche de se réinitialiser si besoin.
    public void reInit() {
        for (Noeud n : noeuds) { n.reInit(); }
    }

    /**
     * on commence de la sortie -> sources jusqu'à arriver aux csts et entrées
     */
    public void affiche(){
       System.out.println(this.sortie);
   }

    /**
     *
     * @return nombre de multiplications faites
     */
   public int nbNoeudsMult(){
       int nb = 0;
       for(Noeud n : this.noeuds){
           if(n.multiBool()){nb++;}
       } return nb;
   }

    /**
     *
     * @return nombre d'operations effectuées
     */
   public int nbOpEffectuees(){
        int nb =0;
        for(Noeud n : this.noeuds){ nb+= n.nbOpEffectuees();}
        return nb;
   }
}



/*********************** Noeuds ********************************/


abstract class Noeud {
    abstract public int valeur();
    public boolean multiBool() {return false;}
    private int nbOp;
    public int nbOpEffectuees() {return this.nbOp; }
    public void incrNbOp() {this.nbOp++;}
    public void reInit() {}
}
abstract  class NoeudBinaire extends Noeud{
    private Noeud source1, source2;
    public NoeudBinaire(Noeud s1, Noeud s2) {
        this.source1 = s1;
        this.source2 = s2;
    }
    // recuperer les attributs
    public int valeurSource1() { return this.source1.valeur();}
    public int valeurSource2() { return this.source2.valeur();}
    abstract public String opString();
    public String toString() { return "(" + this.source1 + this.opString() + this.source2 + ")";}
    public void reInit() {}
}

/**
 * Class d'un noeud donnant une valeur constante
 */
class Constante extends Noeud {
    private int cst;
    public Constante(int c) { this.cst = c; }
    public int valeur() { return this.cst; }
    public String toString() {
            return String.valueOf(this.cst);
        }
}

/**
 * Class d'un noeud d'entree d'un circuit
 */
class Entree extends Noeud{
    private Circuit circuit;
    public Entree(Circuit c){ this.circuit = c;}
    public int valeur(){ return this.circuit.litEntree();}
    public String toString() {
        return "x";
    }
}

/**
 * CLass qui permet de decrire l'addition de noeuds
 */
class Addition extends NoeudBinaire {
    public Addition(Noeud n1, Noeud n2) {super(n1, n2);}
    public int valeur() {
        this.incrNbOp();
        return (this.valeurSource1() + this.valeurSource2());
    }
    public String opString() {return " + "; }

}

/**
 * Class qui permet de decrire la multiplication de noeuds
 */
class Multiplication extends NoeudBinaire{
    public Multiplication(Noeud n1, Noeud n2) { super(n1, n2); }
    public int valeur() {
        this.incrNbOp();
        return (this.valeurSource1() * this.valeurSource2());
    }
    public String opString() {  return " * "; }
    public boolean multiBool(){ return true;}

}

/**
 * Class qui permet de decrire la soustraction entre noeuds
 */
class Soustraction extends NoeudBinaire{
    public Soustraction(Noeud n1, Noeud n2) { super(n1, n2);}
    public int valeur() {
        this.incrNbOp();
        return (this.valeurSource1() - this.valeurSource2());
    }
    public String opString() {  return " - "; }

}

class MultiplicationMemoisee extends Multiplication {
    private boolean evaluee =false; // permet de savoir si un noeud est deja evalué ou pas, lors de l'initialisation on considere que le noeud n'est pas evalué d'ou le fait d'avoir un false
    private int mem; // c'est là où on stocke le resultat de la multiplication entre les deux noeuds
    public MultiplicationMemoisee(Noeud n1, Noeud n2) { super(n1, n2); }
    public int valeur() {
        if (!this.evaluee) {
            // ici le noeud n'est pas encore evalué donc on appelle la methode de la classe mere valeur afin de calculer la multiplication
            this.mem = super.valeur();
            this.evaluee = true;
        }
        return this.mem;
    }
   public void reInit() {  this.evaluee=false; }
}
