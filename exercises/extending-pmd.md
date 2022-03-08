# Extending PMD

Use XPath to define a new rule for PMD to prevent complex code. The rule should detect the use of three or more nested `if` statements in Java programs so it can detect patterns like the following:

```Java
if (...) {
    ...
    if (...) {
        ...
        if (...) {
            ....
        }
    }

}
```
Notice that the nested `if`s may not be direct children of the outer `if`s. They may be written, for example, inside a `for` loop or any other statement.
Write below the XML definition of your rule.

You can find more information on extending PMD in the following link: https://pmd.github.io/latest/pmd_userdocs_extending_writing_rules_intro.html, as well as help for using `pmd-designer` [here](https://github.com/selabs-ur1/VV-TP2/blob/master/exercises/designer-help.md).

Use your rule with different projects and describe you findings below. See the [instructions](../sujet.md) for suggestions on the projects to use.

## Answer

L'expression que nous avons implémenté est la suivante :
//IfStatement[.//IfStatement[.//IfStatement]]
Elle relève bien la présence de 3 If imbriqués dans le code suivant : 

public class QuestionUn{

    private String x = "truc";
    private String y = "Machin";
    private String z = "chose";

    public void methodeX(){
        if(x!= y){
            if(x=="lol"){
                if(y=="paslol"){
                    if(z=="plouf"){
                        syteme.out.println("plifplouf");
                    }
                }
        
            }
        syteme.out.println(this.x);
        methodeY();
        methodeZ();
        }
    }
    public void methodeY(){
        system.out.println(this.y);
        methodeX();
        methodeY();
    }
    public void methodeZ(){
        system.out.println(this.z);
        methodeX();
        methodeY();
    }

    // Evidement on a une loop infinit
}
On relève donc 2 matchs puisque l'on a 4 if imbriqués. 
En revanche l'export du fichier de règle XML est bugué.

