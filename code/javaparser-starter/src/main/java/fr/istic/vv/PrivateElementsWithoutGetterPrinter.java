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
