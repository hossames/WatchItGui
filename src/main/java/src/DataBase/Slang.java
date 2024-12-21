package src.DataBase;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.*;

public class Slang {
    private Scanner sc;
    private final String FilePath;
    private String Line;
    public final char[] Code;
    private final List<String>data;
    private Integer i = 0;
    private ReturnedData returnedData;

    public Slang(File file, String Code) {
        this.Code=Code.toCharArray();
        try {
            sc = new Scanner(file);
        }catch(FileNotFoundException e) {
            System.out.println(file.getName() + "Not found");
        }
        this.FilePath=file.getAbsolutePath();
        returnedData=new ReturnedData();
        data=new ArrayList<>();
    }

    public void Reset (){
        returnedData=new ReturnedData();
    }

    public void SplitLine(){
        data.clear();
        String[] LineData = Line.split(",");
        Collections.addAll(data, LineData);
    }

    public String NextLine(){
        return sc.nextLine();
    }

    public boolean notNull(){
        return sc.hasNextLine();
    }

    public Float ParseFloat(String s){
        try {
            return Float.parseFloat(s);
        }catch(NumberFormatException e){
            System.out.println("\u001B[41m"+"Error in ParseFloat: " + FilePath + "-------" + Arrays.toString(Code));
            return 0F;
        }
    }

    public Long ParseLong(String s){
        try {
            return Long.parseLong(s);
        }catch(NumberFormatException e){
            System.out.println("\u001B[41m"+"Error in ParseLong: " + FilePath + "-------" + Arrays.toString(Code));
            return 0L;
        }
    }

    public int ParseInt(String s){
        try {
            return Integer.parseInt(s);
        }catch(NumberFormatException e){
            System.out.println("\u001B[41m"+"Error in ParseInt: " + FilePath + "-------" + Arrays.toString(Code));
            return 0;
        }
    }

    public Date ParseDate(String s){
        try {
            return DateFormat.getInstance().parse(s);
        }catch (ParseException e){
            System.out.println("Parsing Date in "+ FilePath + " is Failed");
        }
        return null;
    }

    public void KeyWords(char c,boolean loop){
        try {
            if (!loop) {
                switch (c) {
                    case 'n':
                        Line = NextLine();
                        data.clear();
                        i = 0;
                        data.add(Line);
                        break;
                    case 's':
                        SplitLine();
                        i = 0;
                        break;
                    case 'S':
                        returnedData.strings.add(data.get(i++));
                        break;
                    case 'f':
                        returnedData.floats.add(ParseFloat(data.get(i++)));
                        break;
                    case 'd':
                        returnedData.dates.add(ParseDate(data.get(i++)));
                        break;
                    case 'i':
                        returnedData.integers.add(ParseInt(data.get(i++)));
                        break;
                    case 'l':
                        returnedData.longs.add(ParseLong(data.get(i++)));
                        break;
                }
            } else {
                switch (c) {
                    case 'S':
                        returnedData.stringLists.getLast().add(data.get(i++));
                        break;
                    case 'f':
                        returnedData.floatLists.getLast().add(ParseFloat(data.get(i++)));
                        break;
                    case 'd':
                        returnedData.dateLists.getLast().add(ParseDate(data.get(i++)));
                        break;
                    case 'i':
                        returnedData.integerLists.getLast().add(ParseInt(data.get(i++)));
                        break;
                    case 'l':
                        returnedData.longLists.getLast().add(ParseLong(data.get(i++)));
                        break;
                }
            }
        }catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Slang Code Error");
        }
    }

    public void Create(char c){

        switch (c) {
            case 'S':
                returnedData.stringLists.add(new ArrayList<>());
                break;
            case 'f':
                returnedData.floatLists.add(new ArrayList<>());
                break;
            case 'd':
                returnedData.dateLists.add(new ArrayList<>());
                break;
            case 'i':
                returnedData.integerLists.add(new ArrayList<>());
                break;
            case 'l':
                returnedData.longLists.add(new ArrayList<>());
                break;
        }
    }

    public void Build(char[] Code){
        try {
            for (int j = 0; j < Code.length; j++) {
                if (Code[j] == 'o') {
                    Long size = ParseLong(data.get(i++));
                    j++;
                    Create(Code[j]);
                    for (int k = 0; k < size; k++) {
                        KeyWords(Code[j], true);
                    }
                } else if (Code[j] == 'w') {
                    j++;
                    long sz = 0;
                    StringBuilder s = new StringBuilder();
                    while (Code[j] >= '0' && Code[j] <= '9') {
                        sz *= 10;
                        sz += Code[j] - '0';
                        j++;
                    }
                    while (Code[j] != 'W') {
                        s.append(Code[j]);
                        j++;
                    }
                    for (int k = 0; k < sz; k++) {
                        Build(s.toString().toCharArray());
                    }
                } else {
                    KeyWords(Code[j], false);
                }
            }
        }catch (ArrayIndexOutOfBoundsException e){
            System.out.println("\u001B[41m"+"Slang Code Error");
        }
    }
    public ReturnedData Run(char[] Code){
        Reset();
        Build(Code);
        return returnedData;
    }
}
