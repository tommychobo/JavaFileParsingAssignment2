package codecity;

public class LocalVarInfo {
    private String type;
    private String name;
    private String fpath;

    public LocalVarInfo(String declaration, String fpath){
        this.fpath = fpath;
        name = parseName(declaration);
        type = declaration.trim().split(" ")[0];
    }

    /**
     * Take the line declaring the local variable and retrieve the name of the variable.
     * Begin by removing any comments in the declaration.
     * Get the word following the first space in the declaration. This should be the name,
     * but it might have an equals sign or semicolon stuck on the end. To get rid of this,
     * we can loop backwards through the string and chop off the part that is after a character
     * is encountered that cannot be used in variable names.
     * @param dec the variable declaration
     * @return the name of the local variable
     */
    public String parseName(String dec){
        while(dec.contains("/*")){
            int commentInd = dec.indexOf("/*");
            int commentEndInd = dec.indexOf("*/", commentInd);
            if(commentEndInd == -1){
                commentEndInd = dec.length();
            }else{
                commentEndInd += 2;
            }
            dec = dec.substring(0, commentInd)+dec.substring(commentEndInd);
        }
        String name = "";
        if(dec.split(" ").length >= 2){
            name = dec.split(" ")[1];
        }else{
            System.err.println("Local variable declaration has an impossible format");
        }
        for(int i = name.length()-1; i >= 0; i--){
            boolean isAllowedChar;
            char namei = name.charAt(i);
            isAllowedChar = ('a' <= namei && namei <= 'z')
                            || ('A' <= namei && namei <= 'Z')
                            || (i != 0 && '0' <= namei && namei <= '9')
                            || namei == '_' || namei == '$';
            if(!isAllowedChar){
                name = name.substring(0, i);
            }
        }
        return name;
    }


    @Override
    public String toString(){
        return "Type: "+type+", Name: "+name+", Path: "+fpath+"";
    }

    /**
     * type getter
     * @return the variable type
     */
    public String getType(){
        return type;
    }
    public String getName(){
        return name;
    }
    public String getFilePath(){
        return fpath;
    }
}
