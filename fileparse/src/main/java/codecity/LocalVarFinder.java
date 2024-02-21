package codecity;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.expr.VariableDeclarationExpr;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class LocalVarFinder extends VoidVisitorAdapter<Void>{
    private File file;
    private List<LocalVarInfo> vars;
    private String path;
    

    /**
     * check to see if the given file path points to a real java file.
     * Then visit all local variables and add them to the vars List.
     * @param path the location of the java file in question
     */
    public LocalVarFinder(String path){
        vars = new ArrayList<>();
        this.path = path;
        try{
            file = new File(path);
            Path fPath = Path.of(path);
            if(file.exists() && Files.isRegularFile(fPath) && isJavaFile()){
                FileInputStream fIn = new FileInputStream(path);

                JavaParser jp = new JavaParser();
                CompilationUnit cu = jp.parse(fIn).getResult().get();
            
                this.visit(cu, null);
            }else{
                System.out.println("Non-java file encountered: "+file.getName());
            }
        }catch(IOException e){
            e.printStackTrace();
            System.err.println("No file big sad :(");
        }
    }

    /**
     * This method is called for every VariableDeclarationExpr visited.
     */
    @Override
    public void visit(VariableDeclarationExpr vde, Void arg){
        super.visit(vde, arg);
        vars.add(new LocalVarInfo(vde.toString(), path));
    }


    /**
     * Identifies if the file has the .java extension
     * @return boolean value that is true iff file has a .java extension
     */
    public boolean isJavaFile(){
        String fName = file.getName();
        return fName.endsWith(".java");
    }

    /**
     * prints all local variable types in the file, in the order encountered
     * @return the string representing all variable types, separated by newlines
     */
    @Override
    public String toString(){
        String total = "";
        for(LocalVarInfo v: vars){
            total = "" + total + v.toString() +"\n";
        }
        total += "Number of local vars: "+vars.size();
        return total;
    }
}
