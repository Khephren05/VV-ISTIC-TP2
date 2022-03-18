# Code of your exercise
```java
public class QuestionUn{

    private String x = "truc";
    private String y = "Machin";
    private String z = "chose";

    public void methodeX(){
        syteme.out.println(this.x);
        methodeY();
        methodeZ();

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
```