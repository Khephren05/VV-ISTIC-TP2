# No getter!

With the help of JavaParser implement a program that obtains the private fields of public classes that have no public getter in a Java project. 

A field has a public getter if, in the same class, there is a public method that simply returns the value of the field and whose name is `get<name-of-the-field>`.

For example, in the following class:

```Java

class Person {
    private int age;
    private String name;
    
    public String getName() { return name; }

    public boolean isAdult() {
        return age > 17;
    }
}
```

`name` has a public getter, while `age` doesn't.

The program should take as input the path to the source code of the project. It should produce a report in the format of your choice (TXT, CSV, Markdown, HTML, etc.) that lists for each detected field: its name, the name of the declaring class and the package of the declaring class.

Include in this repository the code of your application. Remove all unnecessary files like compiled binaries. See the [instructions](../sujet.md) for suggestions on the projects to use.

*Disclaimer* In a real project not all fields need to be accessed with a public getter.

java -jar javaparser-starter-1.0-jar-with-dependencies.jar /Users/francoisrousseaux/Documents/M2\ CCN/VV/VV-ISTIC-TP2/exercises

##Answers 

La classe répondant à cette problématique est la classe [PrivateElementsWithoutGetterPrinter](../code/javaparser-starter/src/main/java/fr/istic/vv/PrivateElementsWithoutGetterPrinter.java), elle parcours les .java du 
dossier fournit en paramètre et produit un fichier <folderName>Analysis.txt contenant la liste des variables privée n'ayant pas de getters
en indiquant dans quelle classes ces variables sont situées. Ce fichier est généré à l'emplacement du code analysé. 

Nous nous sommes basés sur les conventions de nommage afin de détecter l'absence de getter.