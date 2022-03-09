# Code of your exercise

##Answers

La classe répondant à cette problématique est la classe PrivateElementsWithoutGetterPrinter (située dans le dossier javaparser-starter/src/main/java/fr.istic.vv), elle parcours les .java du
dossier fournit en paramètre et produit un fichier <folderName>Analysis.txt contenant la liste des variables privée n'ayant pas de getters
en indiquant dans quelle classes ces variables sont situées. Ce fichier est généré à l'emplacement du code analysé.

Nous nous sommes basés sur les conventions de nommage afin de détecter l'absence de getter.