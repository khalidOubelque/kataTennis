KATA Tennis Game

-------------Programme ---------------
Le but est de modéliser un match de tennis entre 2 joueurs.
Le programme doit permettre de faire gagner les points par un des 2 joueurs et de déterminer le score, le statut du jeu en cours 
(0-0, 15-0, …. Deuce, advantage) ainsi que le statut de la partie (in Progress, player 1 wins, player 2 wins) selon la format si dessous :

Player  1 :  nom du joueur1
Player  2 : nom du joueur2
Score : (6 -1) (7-5) (1-0)
Current game status : 15-30
Match Status : in progress

Le livrable c'est un Projet Maven qui dispose des tests unitaires (JUnit 4.12) pour tester les differents fonctionnalites du programme.
Les messages error, info ..etc sont logges avec log4j.

En se basant sur une architecture base sur le couplage faible entre les classe, Le projet peut être rapidement converti en projet Spring, 
pour utiliser le conteneur IOC pour l'injection de dependances.

-------------Prérequis----------------

Java 8 -> 11
Maven : pour lancer les TU ->  mvn test

-----------Execution ------------------- 
Pour Tester, on lance la commande : mvn test

Vous trouverez tous les tests sur le  package kata.tennis.TennisGame , tous les tests sont couverts (+5 TU exemples de l'enonce)

Exemple 1 
Player  1 :  nom du joueur1
Player  2 : nom du joueur2
Score : (6 -1) (7-5) (1-0)
Current game status : 15-30
Match Status : in progress
 
Exemple 2
Player  1 :  nom du joueur1
Player  2 : nom du joueur2
Score : (6 -1) (7-5) (0-0)
Current game status : deuce
Match Status : in progress
 
Exemple 3
Player  1 :  nom du joueur1
Player  2 : nom du joueur2
Score : (6 -1) (7-5) (0-0)
Current game status : advantage
Match Status : in progress
 
Exemple 4
Player 1 :  nom du joueur1
Player 2 : nom du joueur2
Score : (6 -1) (7-5) (6-0)
Match Status : Player1 wins
 
Exemple 5
Player 1 :  nom du joueur1
Player 2 : nom du joueur2
Score : (6 -1) (7-5) (2-6) (6-7) (4-6)
Match Status : Player 2 wins
