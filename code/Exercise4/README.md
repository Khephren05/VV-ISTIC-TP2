# Code of your exercise

package fr.istic.vv;

```java
import com.github.javaparser.Problem;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitor;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.github.javaparser.utils.SourceRoot;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) throws IOException {
        if(args.length == 0) {
            System.err.println("Should provide the path to the source code");
            System.exit(1);
        }

        File file = new File(args[0]);
        if(!file.exists() || !file.isDirectory() || !file.canRead()) {
            System.err.println("Provide a path to an existing readable directory");
            System.exit(2);
        }

        SourceRoot root = new SourceRoot(file.toPath());
        PrivateElementsWithoutGetterPrinter printer = new PrivateElementsWithoutGetterPrinter(args[0]);
        root.parse("", (localPath, absolutePath, result) -> {
            result.ifSuccessful(unit -> unit.accept(printer, null));
            return SourceRoot.Callback.Result.DONT_SAVE;
        });
    }


}
```
```java
package fr.istic.vv;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.*;
import com.github.javaparser.ast.visitor.VoidVisitorWithDefaults;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PrivateElementsWithoutGetterPrinter extends VoidVisitorWithDefaults<Void> {
private String pathToResult;
private String fileName;
private File myObj;

    public PrivateElementsWithoutGetterPrinter(String path) {
        this.pathToResult = path;
        this.fileName = pathToResult + "Analysis.txt";
        try {
            File myObj = new File(fileName);
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    @Override
    public void visit(CompilationUnit unit, Void arg) {
        for (TypeDeclaration<?> type : unit.getTypes()) {
            type.accept(this, null);
        }
    }

    public void visitTypeDeclaration(TypeDeclaration<?> declaration, Void arg) {

        if (!declaration.isPublic()) return;
        List<String> variableList = new ArrayList<>();
        List<String> issuesVariable = new ArrayList<>();
        // On parcours les fields pour trouver ceux qui sont private et on les met dans la liste
        for (FieldDeclaration var : declaration.getFields()) {
            if (var.isPrivate()) {
                variableList.add(var.getVariable(0).getName().toString());
                //System.out.println(var.getVariable(0).getName());
            }
        }
        if (!variableList.isEmpty()) {
            issuesVariable.addAll(variableList);
            System.out.println("Looking for privates fields without getters : ");
            for (String varName : variableList) {
                for (MethodDeclaration method : declaration.getMethods()) {
                    if (method.getNameAsString().equalsIgnoreCase("get" + varName)) {
                        issuesVariable.remove(varName);
                    }
                }
            }
        }
        if (!issuesVariable.isEmpty()) {
            try {
                FileWriter myWriter = new FileWriter(fileName, true);
                myWriter.write("Located in package : " + pathToResult + "\n");
                myWriter.write("Located in class : " + declaration.getNameAsString() + "\n");
                System.out.println(declaration.getNameAsString());
                myWriter.write("The code analysis revealed " + issuesVariable.size() + " privates instances " +
                        "variables without getters such as : \n");
                for (String result : issuesVariable) {
                    myWriter.write(result + "\n");
                }
                myWriter.close();
                System.out.println("Successfully wrote to the file.");
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
    }

    @Override
    public void visit(ClassOrInterfaceDeclaration declaration, Void arg) {
        visitTypeDeclaration(declaration, arg);
    }

    @Override
    public void visit(EnumDeclaration declaration, Void arg) {
        visitTypeDeclaration(declaration, arg);
    }

    @Override
    public void visit(MethodDeclaration declaration, Void arg) {
        if (!declaration.isPublic()) return;
        //System.out.println("  " + declaration.getDeclarationAsString(true, true));
    }
}
```


##Answers

La classe répondant à cette problématique est la classe PrivateElementsWithoutGetterPrinter (située dans le dossier javaparser-starter/src/main/java/fr.istic.vv), elle parcours les .java du
dossier fournit en paramètre et produit un fichier <folderName>Analysis.txt contenant la liste des variables privée n'ayant pas de getters
en indiquant dans quelle classes ces variables sont situées. Ce fichier est généré à l'emplacement du code analysé.

Nous nous sommes basés sur les conventions de nommage afin de détecter l'absence de getter.