package fileHandling;

import POJO.ScrapedResult;
import jdk.nashorn.api.scripting.JSObject;
import org.json.simple.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Date;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.io.*;

public class SaveInFile{
	public void writeFile(ArrayList<ScrapedResult> list){

<<<<<<< HEAD
        File file=new File("/scrapperNew.txt");
=======
        File file=new File("/scrapper.txt");
>>>>>>> 5f56adfef32fc08506ff3ccc5694ac42f3d6168e
        StringBuffer content=new StringBuffer();
        JSONObject jsonObject = new JSONObject();
        for (ScrapedResult scr:
             list) {
            jsonObject.put(scr.getDate(),scr.getText());
            content.append(jsonObject);
        }
        BufferedWriter bw=null;
        FileWriter fw=null;
        try{
            fw=new FileWriter(file.getName(),true);
            bw=new BufferedWriter(fw);
            bw.write("\n"+content.toString());
            bw.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }

//		for(ScrapedResult obj:list){
//			 String d = obj.getDate();
//			 ArrayList<String> text=obj.getText();
//			 //ArrayList<String> text = new ArrayList<String>();
//			 //text.add("HOLAAAA");
//			 File file=new File("/scrapper.csv");
//			 StringBuffer content=new StringBuffer();
//			 //Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//			 content.append(d);
//			 for(Object str:text){
//				content.append(","+str.toString());
//			}
//			 BufferedWriter bw=null;
//			 FileWriter fw=null;
//			 try{
//				fw=new FileWriter(file.getName(),true);
//				bw=new BufferedWriter(fw);
//				bw.write("\n"+content.toString());
//				bw.close();
//				}
//			catch(IOException e){
//				e.printStackTrace();
//			}
//		}
		 
	}
}