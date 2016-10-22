import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.io.*;

public class SaveInFile{
	public void writeFile(List<ScrapedResult> list){
		for(ScrapedResult obj:list){
			 Date d=obj.getDate();
			 ArrayList<String> text=obj.getText();
			 File file=new File("scrapper.csv");
			 StringBuffer content=new StringBuffer();
			 Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			 content.append(formatter.format(d));
			 for(Object str:text){
				content.append(","+str.toString());
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
		}
		 
	}
}