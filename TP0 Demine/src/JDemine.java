/**
   POGL : programmation objet et génie logiciel
   Cours d'ouverture du 14 janvier 2019, révisions de Java
   
   @author Thibaut Balabonski @ Université Paris-Sud
   
   L'objectif ici est de réviser les principaux éléments de Java que vous avez
   abordés au premier semestre. On développe pour cela une mini-application
   [JDemine], qui reprend le principe du jeu classique du démineur.
   Ce fichier commenté tient lieu de notes de cours (le code est à la lettre
   près celui que nous avons écrit en classe, seuls les commentaires ont été
   ajoutés).
   
   Ce fichier définit trois classes :
   - [JDemine] est la classe principale de l'application
   - [Terrain] modélise la grille du jeu
   - [Case] modélise les cases de cette grille
   On fournit avec un petit paquet [IG] qui donne des éléments minimaux pour
   interagir avec la bibliothèque graphique de Java (Swing).

   En règle générale, chaque fichier contient exactement une classe, et les
   deux classes [Terrain] et [Case] seraient normalement placées dans deux
   fichiers [Terrain.java] et [Case.java].
   Ici on conserve tout dans le même fichier pour une présentation linéaire
   de ces notes.
*/

/**
   Avant de commencer : on intègre dans notre développement quelques classes
   qui seront en charge de l'interface graphique :
   - la classe [java.awt.Color] de la bibliothèque standard de Java
   - les classes [Fenetre], [Grille] et [ZoneCliquable] du paquet [IG], une
     bibliothèque maison pour épurer l'accès à quelques éléments de la
     bibliothèque graphique standard de Java.
*/
import java.awt.Color;
import IG.Fenetre;
import IG.ZoneCliquable;
import IG.Grille;

/**
   Un fichier de code Java porte l'extention [.java], et contient en général
   une unique classe publique, de nom correspondant, introduite par la
   mention
     public class C { ... }
   où [C] est le nom donné à la classe.

   Avant exécution, le programme doit être compilé, c'est-à-dire traduit en
   langage machine. En l'occurrence, la traduction ne se fait pas vers le
   langage assembleur de votre machine physique, mais vers le "bytecode" de
   la machine virtuelle Java (JVM). Cette machine virtuelle simule un
   ordinateur. Cette technique facilite la diffusion des applications Java :
   les développeurs n'ont besoin de s'adapter qu'à une architecture cible (la
   JVM), sans se soucier de l'environnement réel des utilisateurs.

   Dans un terminal, on demande la compilation avec la commande
     javac JDemine.java
   Au passage, cette commande vérifie certains éléments de cohérence du
   programme, et indique les éventuelles erreurs relevées.
   Le programme compilé prend la forme d'un nouveau fichier [JDemine.class].
   Dans Eclipse, ceci correspond à la commande "Build".
*/
public class JDemine {

    /**
       La méthode [main] décrit le programme principal.
       Sa signature [public static void main(String[])] est fixée par
       convention, nous détaillerons sa signification au prochain cours.
       
       Dans un terminal on exécute le programme, c'est-à-dire le code de
       la méthode [main] par la commande
         java JDemine
       Attention, ceci ne peut se faire qu'en présence du fichier
       [JDemine.class], c'est-à-dire après la compilation.
       Dans Eclipse, ceci correspond à la commande "Run".
    */
    public static void main(String[] args) {
	/**
	   On définit une variable locale [x] de type [T] par
	   la déclaration
	     T x;
	   On peut en outre affecter une valeur à cette variable en étendant
	   la ligne de la façon suivante
	     T x = ...
	   En l'occurrence, on crée un nouvel objet (on dit aussi une instance)
	   avec le mot clé [new], suivi du nom de la classe concernée appliqué
	   à d'éventuels paramètres.

	   On obtient une fenêtre graphique [f] intitulée "JDemine" et un
	   terrain [t] de hauteur 6 et de largeur 9.
        */
        Fenetre f = new Fenetre("JDemine");
        Terrain t = new Terrain(8, 6);
	/**
	   On appelle une méthode [m] d'un objet [obj] avec une expression de
	   la forme [obj.m(...)], en fournissant entre parenthèses les
	   paramètres donnés à [m].

	   Les méthodes [ajouteElement] et [dessineFenetre] sont définies dans
	   la classe [Fenetre]. La première permet d'intégrer de nouveaux
	   éléments (ici le terrain [t]) à l'affichage, et la deuxième provoque
	   l'affichage de la fenêtre et de l'ensemble de ses éléments.
	*/
        f.ajouteElement(t);
        f.dessineFenetre();
    }
}

/**
   Lors de la définition d'une nouvelle classe [C], on peut déclarer qu'elle
   étend une classe pré-existante [D], avec la mention
     class C extends D { ... }
   Dans ce cas, les fonctionnalités de la classe [D] sont intégrées à la
   classe [C]. Les implications de cette déclaration sont plus subtiles qu'il
   n'y paraît, on reviendra en détail plus tard dans le semestre sur ce que
   cela signifie exactement (deuxième quinzaine de mars).

   Ici, [Terrain] va hériter des fonctionnalités de la classe [Grille],
   c'est-à-dire d'une capacité à intégrer plusieurs éléments graphique disposés
   selon une grille à deux dimensions.
*/
class Terrain extends Grille {

    /**
       La définition d'une classe contient trois types d'éléments :
       - des attributs, qui sont des données attachées à chaque instance de
         la classe
       - des constructeurs, qui permettent de créer et d'initialiser des
         instances de la classe
       - des méthodes, qui permettent d'agir sur les instances
    */

    /**
       La déclaration d'un attribut [a] de type [T] a la forme
         T a;
       Cette déclaration est généralement précédée d'une indication de
       visibilité [private], indiquant que l'attribut n'est pas accessible
       depuis les autres classes. On peut déclarer plusieurs attributs du
       même type à la fois en les séparant par des virgules.

       La convention selon laquelle les attributs sont en général privés est
       l'un des aspects du concept d'encapsulation, qui est central en
       programmation objet (ainsi que dans un certain nombre d'autres styles
       de programmation). De manière générale, on distingue dans chaque objet
       et chaque structure de données des aspects publics définissant la
       manière dont un utilisateur extérieur peut interagir avec l'objet (on
       parle pour cela d'interface, essentiellement constituée de méthodes dans
       le cas de la programmation objet) et des aspects privés liés à la
       réalisation de l'objet ou de la structure (dont souvent les attributs).
       L'objectif est d'obtenir une certaine modularité et adaptativité du
       code : on peut modifier des choses en profondeur à des fins
       d'amélioration ou de correction de bugs sans que cela n'affecte les
       utilisateurs tant que l'interface est respectée.

       Note sur les conventions de nommage : le code n'étant pas uniquement
       écrit pour être exécuté, mais aussi pour être lu, on préférera utiliser
       des noms significatifs. Quand un nom d'attribut est composé de plusieurs
       mots, on met en majuscule la première lettre de chaque mot (sauf pour
       le premier mot, seuls les noms de classe commençant par une majuscule).

       Ici on définit d'abord deux attributs entiers indiquant la hauteur et la
       largeur du terrain.
    */
    private int hauteur, largeur;

    /**
       On note [ T[] ] le type des tableaux d'éléments de type [T].
       Le type d'un tableau à deux dimensions est donc noté [ T[][] ], avec
       un parenthésage implicite [ (T[])[] ] : on parle d'un tableau dont les
       éléments sont eux mêmes des tableaux (d'élément de type [T]).
    */
    private Case[][] terrain;

    /**
       Un constructeur porte systématiquement le nom de sa classe, a
       généralement une visibilité [public], et peut prendre des paramètres
       dont il précise les types.

       Ici le constructeur de la classe [Terrain] attend deux paramètres
       entiers [h] et [l] définissant la hauteur et la largeur du terrain à
       créer.
    */
    public Terrain(int hauteur, int largeur) {

	/**
	   Lorsqu'un classe [C] étend une classe pré-existante [D], les
	   constructeurs de [C] peuvent commencer par un appel aux
	   constructeurs de [D], qui s'écrit
	     super( ... );
	   Ici, on transmet au constructeur de la classe [Grille] les
	   dimensions de la grille.
	*/
        super(hauteur, largeur);

	/**
	   Le constructeur est ensuite en charge d'initialiser les attributs
	   de l'objet en cours de création.

	   Un attribut [a] de l'objet courant est désigné par [this.a].
	   Le [this] est optionnel mais son usage est recommandé.

	   À savoir : un attribut non initialisé aura généralement par défaut
	   une valeur équivalente à [0] ou [null]. On recommande de ne pas se
	   reposer sur ce fait.
	*/
        this.hauteur = hauteur;
        this.largeur = largeur;

	/**
	   On crée un tableau de [n] éléments de type [T] avec l'expression
	     new T[n]
	   Si le tableau a plusieurs dimensions, on enchaîne les crochets.
	   Cette déclaration ne créé que le tableau lui-même, et n'initialise
	   pas les éléments qu'il contient.

	   Ici, on initialise l'attribut [terrain] avec un tableau de hauteur
	   [hauteur] et de largeur [largeur], destiné à contenir des objets de
           type [Case] qui ne sont pas encore définis mais qu'on va ensuite
           pouvoir créer et ajouter.
	*/
        this.terrain = new Case[hauteur][largeur];

        
	/**
	   La boucle inconditionnelle [for] permet la répétition d'un bloc
	   de code. On y déclare une variable de boucle [int i], sa valeur
	   initiale [=0], une condition de poursuite de boucle [i<hauteur]
	   à vérifier avant chaque tour et une instruction de mise à jour de la
	   variable [i++] à appliquer à la fin de chaque tour.

	   Ici, on emboîte deux boucles, dont les indices [i] et [j]
	   correspondent aux ordonnées et aux abscisses de notre grille à deux
	   dimensions. On parcourt ainsi chaque ligne du tableau, et chaque
	   élément de chaque ligne, pour initialiser l'ensemble du tableau.

	   Remarque : indépendamment de nos attributs [hauteur] et [largeur],
	   on peut connaître la longueur d'un tableau [t] avec l'expression
	   [t.length]. Dans un tableau de tableaux, on obtient donc la longueur
	   de la [i]-ème ligne avec [t[i].length].
	*/
        for (int i=0; i<hauteur; i++) {
            for (int j=0; j<largeur; j++) {
                /**
                   Définition d'une variable booléenne [mine] à utiliser dans
                   la construction d'une nouvelle case, indiquant si ladite case
                   doit contenir une mine ou non.

                   La méthode [Math.random()] renvoie un nombre flottant de
                   type [double], compris entre 0 et 1.
                */
                boolean mine = Math.random() < 0.25;

                /**
                   Création d'une nouvelle case, par appel au constructeur de
                   la classe [Case]. On donne en paramètres : les coordonnées,
                   le booléen indiquant si la case doit être piégée ou non, et
                   une référence au terrain lui-même ([this]) qui permettra à
                   une case de faire appel à des méthodes du terrain.
                */
                Case c = new Case(i, j, mine, this);
                
		/**
		   On affecte la valeur d'une expression [e] à l'indice [i]
		   d'un tableau [t] avec une instruction
		     t[i] = e;
		   En cas de tableaux à plusieurs dimensions on enchaîne les
		   indices entre crochets.

		   En l'occurrence, la prochaine instruction place la case [c]
		   à l'indice [j] de la [i]-ème ligne de [terrain].
		*/
                terrain[i][j] = c;

                
                /**
                   On appelle une méthode [m] de l'objet courant avec
                   l'expression
                     this.m( ... )
                   
                   En l'occurrence, la méthode [ajouteElement], qui ajoute la
                   case passée en paramètre à la liste des objets à afficher,
                   est définie dans la classe [Grille], et l'objet courant de
                   classe [Terrain] y a accès car la classe [Terrain] étend la
                   classe [Grille].
                */
                this.ajouteElement(c);

		/**
		   Alternativement, on aurait pu inclure directement le calcul
                   du booléen à l'appel au constructeur :
		     Case c = new Case(x, y, Math.random() < 0.25, this);

		   On a également une forme générale d'expression conditionnelle
		   qui combine un test [c] et deux expressions [e₁] et [e₂] :
		     (c)?e₁:e₂
		   Si le test [c] vaut [true], alors le résultat sera celui de
		   l'expression [e₁], sinon le résultat sera celui de
		   l'expression [e₂]. L'expression booléenne
		     Math.random() < 0.25
		   est donc équivalente à
		     (Math.random()<0.25)?true:false
		   ou encore à
		     (Math.random()>=0.25)?false:true

                   Enfin, la variable [mine] aurait pu être d'abord simplement
                   déclarée, puis initialisée lors d'une instruction
                   conditionnelle :
                     boolean mine;
                     if (Math.random() < 0.25) {
                       mine = true;
                     } else {
                       mine = false;
                     }
		*/
            }
        }
    }

    /**
       La définition d'une méthode ressemble à celle d'un constructeur, mais
       permet d'utiliser un nom quelconque et précise un éventuel type de
       résultat (ou indique [void] quand aucun résultat n'est produit).

       Ici, la méthode [nombreVoisinesPiegees] prend en paramètres deux entiers
       représentant les coordonnées d'une case, et renvoie une valeur entière
       indiquant le nombre de voisines piégées de cette case.

       Cette méthode sera appelée par les cases lorsque celles-ci auront besoin
       d'afficher le nombre de leurs voisines piégées après un clic.
       Elle doit donc être visible par des objets extérieurs, avec une
       visibilité [protected] ou [public].
    */
    public int nombreVoisinesPiegees(int x, int y) {
	// Initialisation d'une variable à 0
        int nb = 0;
	// Pour chaque case dans le carré de 3x3 autour des coordonnées
	// considérées...
        for (int i=x-1; i<=x+1; i++) {
            for (int j=y-1; j<=y+1; j++) {
		// si ces coordonnées sont valides et correspondent à une
		// case piégée, alors on incrémente le compteur.
                if (coordonneesLegales(i, j) &&
                    this.terrain[i][j].estPiegee()) {
                    nb++;
                }
		/**
		   Remarques : l'opérateur [&&] est paresseux, et n'évaluera
		   pas son deuxième argument si le premier vaut déjà [false].
		   Le code précédent est donc équivalent à :
		     if (coordonneesLegales(i, j)) {
		       if (this.terrain[i][j].estPiegee()) { nb++; }
		     }
		   et on s'assure qu'on n'essaiera jamais de lire le tableau
		   à des coordonnées inadaptées.
		*/
                    
                /**
                   Les accès à des attributs, indices de tableaux ou méthodes
                   peuvent s'enchaîner. Pour chaque paire valide de coordonnées
                   [i, j], l'instruction précédente accède à la case ayant ces
                   coordonnées dans le tableau [terrain] de l'objet courant
                   [this], puis appelle la méthode [estPiegee()] de cette case.
	   
                   Remarque : l'attribut [estPiegee] est déclaré dans la
                   classe [Case] avec la visibilité [private] et est donc
                   accessible seulement via la méthode "getter" [estPiegee()].
                */
            }
        }
	// À la fin, on renvoie le nombre obtenu
        return nb;
    }
    /**
       Remarque : contrairement à ce qui était prétendu au début, l'invocation
       [nombreVoisinesPiegees(x, y)] ne renvoie pas seulement le nombre de
       voisines piégées de la case de coordonnées [x, y], mais compte aussi
       cette case.

       Question : pourquoi peut-on se permettre cette approximation ?
       On reparlera de ce genre de considérations à partir de mi-février.
     */
    
    /**
       Des coordonnées sont valides si elles sont convenablement encadrées
       par les dimensions du terrain.
       Rappel : les indices d'un tableau [t] vont de [0] à [t.length - 1].

       Cette méthode est destinée à un usage interne, on peut en restreindre la
       visibilité avec une déclaration [private].
    */
    private boolean coordonneesLegales(int i, int j) {
        return i >= 0 && i < this.hauteur
            && j >= 0 && j < this.largeur;
    }
    

    /**
       BONUS MÉTHODOLOGIE NON PRÉSENTÉ EN CLASSE.

       La méthode [main] des classes annexes peut être utilisée pour effectuer
       de petits tests unitaires.
       Après compilation, on peut exécuter ce test en exécutant la classe
       actuelle, c'est-à-dire [Terrain]. Dans un terminal, cela se traduit
       par la ligne de commande
         java Terrain

       Consigne générale : incluez ce genre de tests dans tous vos
       développements.
    */
    public static void main(String[] args) {
	Terrain t = new Terrain(3, 4);
	t.terrain[0][0] = new Case(0, 0, true, t);
	t.terrain[0][1] = new Case(0, 1, false, t);
	t.terrain[0][2] = new Case(0, 2, false, t);
	t.terrain[1][0] = new Case(1, 0, false, t);
	t.terrain[1][1] = new Case(1, 1, true, t);
	t.terrain[1][2] = new Case(1, 2, false, t);
	// Affichage d'un compte rendu du test.
	System.out.println("Voisines de (0, 1): "
			   + t.nombreVoisinesPiegees(0, 1)
			   + " (attendu : 2)");
	/**
	   Variante possible : utiliser une assertion pour interrompre le
	   programme en cas d'échec.
	     assert t.compteVoisines(0, 1) == 2 : "Erreur compte voisines";
	     System.out.println("Test classe Terrain OK");

	   Dans ce cas, il faut exécuter le
	   programme avec l'option -ea ("execute assertions"), c'est-à-dire
	     java -ea Terrain
	*/
    }

}

/**
   La classe [Case] étend la classe abstraite [ZoneCliquable] fournie dans
   l'interface [IG]. Cela va permettre de rendre les cases réactives aux clics,
   mais demande de définir deux méthodes [clicGauche] et [clicDroit].
   Au passage, l'extension de [ZoneCliquable] donne aussi accès à une méthode
   [changeTexte] permettant de choisir l'éventuel texte affiché dans la case.
*/
class Case extends ZoneCliquable {

    /**
       À chaque case on associe ses coordonnées, un booléen indiquant la
       présence ou l'absence d'une mine, un booléen (finalement non utilisé en
       classe) indiquant si la case a déjà été cliquée, et une référence vers
       le terrain pour le calcul du nombre de voisines piégées.
    */
    private int x, y;
    private boolean estPiegee;
    private boolean dejaCliquee;
    private Terrain terrain;

    /** 
	La méthode publique [estPiegee] permet d'accéder à la valeur de
	l'attribut [piegee]. L'attribut [piegee] étant déclaré privé, on ne
	peut y accéder librement que depuis l'intérieur de la classe [Case],
	et les méthodes de la classe [Terrain] par exemple sont forcées
	d'utiliser cette méthode d'accès.

	Ce genre de méthodes, qu'on appelle aussi "getter", est à introduire
	en fonction des besoins. 
    */
    public boolean estPiegee() {
        return this.estPiegee;
    }

    /**
       Le constructeur prend en paramètres les coordonnées de la case, un
       booléen indiquant la présence ou l'absence d'une mine, et une référence
       vers le terrain.
    */ 
    public Case(int x, int y, boolean mine, Terrain terrain) {
        
        /**
	   Première étape : appeler le constructeur de [ZoneCliquable],
	   auquel on transmet le texte à afficher initialemenet (la chaîne
	   vide) ainsi que les dimensions de la case (40x40 pixels).
	*/
        super("", 40, 40);

	/**
	  Deuxième étape : initialisation des attributs avec les valeurs
	  fournies. Exception : l'attribut [dejaCliquee] est initialisé à la
          valeur [false] dans tous les cas.
	*/
        this.x = x;
        this.y = y;
        this.estPiegee = mine;
        this.dejaCliquee = false;
        this.terrain = terrain;

        /**
           Alternativement, la valeur de l'attribut [dejaCliquee] aurait pu être
           fixée dès la déclaration de cet attribut en remplaçant la ligne
             private boolean dejaCliquee;
           par la ligne
             private boolean dejaCliquee = false;
           Dans ce cas il n'aurait plus été nécessaire d'initialiser cette
           valeur dans le constructeur : on aurait omis la ligne
             this.dejaCliquee = false;
        */
    }

    /**
       La méthode [clicGauche] colore la case en rouge si la case était
       piégée, et affiche sinon le nombre de voisines piégées.
    */
    public void clicGauche() {
        if (this.estPiegee) {
	    /**
	       La méthode [setBackground] est récupérée de la bibliothèque
	       standard de Java via [ZoneCliquable], et change la couleur de
	       fond d'un élément graphique.
	    */
            setBackground(Color.RED);
            // Plus fin de partie à gérer
        } else {
	    /**
	       Remarque : la méthode [changeTexte] attend en paramètre une
	       chaîne de caractères. Or, le nombre de voisines piégées est
	       un nombre entier. On applique donc une fonction de conversion
	       proposée par la bibliothèque [Integer] de Java.
	    */
            int nb = this.nombreVoisinesPiegees();
            this.changeTexte(Integer.toString(nb));
        }
    }

    /**
       Seul le terrain a accès à toutes les cases et est donc apte à calculer le
       nombre des voisines piégées. La méthode [nombreVoisinesPiegees] de la
       case fait donc appel à la méthode correspondante du terrain, en lui
       fournissant en paramètres ses coordonnées.
    */
    public int nombreVoisinesPiegees() {
        return this.terrain.nombreVoisinesPiegees(x, y);
    }

    /**
       La méthode [clicDroit] colore la case en bleu.
    */
    public void clicDroit() {
        setBackground(Color.BLUE);
    }
}
