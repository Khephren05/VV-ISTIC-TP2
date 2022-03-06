# TCC *vs* LCC

Explain under which circumstances *Tight Class Cohesion* (TCC) and *Loose Class Cohesion* (LCC) metrics produce the same value for a given Java class. Build an example of such as class and include the code below or find one example in an open-source project from Github and include the link to the class below. Could LCC be lower than TCC for any given class? Explain.

## Answer

TCC sert à réaliser une mesure de la cohésion des classes, des valeurs élevées (>70%) révèlent que la classe en question 
ne comporte qu'une méthode basique, la rendant difficile à diviser en sous composants. 
En revanche une valeur faible (<50%) indique que la classe fait trop de choses différentes et qu'il est nécéssaire de la 
diviser. 

Le TCC est défini comme le rapport entre le nombre de paires de nœuds directement connectés dans le graphe et le nombre total de paires de nœuds.

LCC est le nombre de paires de nœuds connectés (directement ou indirectement) à toutes les paires de nœuds.

Pour avoir le même score TCC et LCC dans une même classe il faut que chaque noeud soit connecté à chaque noeuds, de ce fait on 
as autant de connexion directes (produisant le score TCC) que de connexion indirecte (produisant le score LCC). 

Le code présent dans QuestionUn.java devrait engendrer un score de TCC égale à celui de LCC (une classe vide aurait fait l'affaire).
En revanche LCC ne sera jamais inférieur à TCC car elle prend en compte toutes les relations mise en evidences par TCC plus 
les relations indirectes. Au mieux LCC peut être égale à TCC. 