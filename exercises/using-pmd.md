# Using PMD

Pick a Java project from Github (see the [instructions](../sujet.md) for suggestions). Run PMD on its source code using any ruleset. Describe below an issue found by PMD that you think should be solved (true positive) and include below the changes you would add to the source code. Describe below an issue found by PMD that is not worth solving (false negative). Explain why you would not solve this issue.

## Answer

Nous avons deployé le l'analyse PMD sur le code récupéré ici : https://github.com/apache/commons-math
L'analyse est située dans le fichier error.html généré par PMD. 

L'analyse PMD a permis de mettre en evidence cette erreur : 	An instanceof check is being performed on the caught exception. Create a separate catch clause for this exception type.
Le develloper a réalisé un catch prenant plusieurs types d'exeptions, mais réalise une traitement spécifique pour un des types d'exeption. Il faudrait réaliser un catch pour chacunes des exeptions. 


