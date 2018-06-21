package wkrjsystem.utils;

import java.io.IOException;

import com.aspose.cells.Workbook;
import com.aspose.words.Document;
import com.aspose.words.License;
import com.aspose.words.SaveFormat;

public class ConvertWordToPdfByAspose
{
  public  String word2pdf(String yFile,String path)
    throws IOException
  {
    String result = "sb";

   // String rn = realname.split("\\.")[0];
    String pdffile = path + ".pdf";
    try
    {
      try
      {
        String license_path = getClass().getClassLoader().getResource("/").getPath() + "../../"
          +"license.xml";
        License aposeLic = new License();
        aposeLic.setLicense(license_path);
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }

      Document doc = new Document(yFile);

      doc.save(pdffile, SaveFormat.PDF);
      
      result = "cg";
    }
    catch (Exception e)
    {
      System.out.println(e);
    }
    return result;
  }
  
  public  String excel2pdf(String yFile,String path)
	  {
	    String result = "sb";

	   // String rn = realname.split("\\.")[0];
	    String pdffile = path + ".pdf";
	    try
	    {
	      try
	      {
	        String license_path = getClass().getClassLoader().getResource("/").getPath() + "../../"
	          +"license.xml";
	        com.aspose.cells.License aposeLic = new com.aspose.cells.License();
	        aposeLic.setLicense(license_path);
	      }
	      catch (Exception e)
	      {
	        e.printStackTrace();
	      }

	      Workbook wb = new Workbook(yFile);

	      wb.save(pdffile, com.aspose.cells.SaveFormat.PDF);
	      
	      result = "cg";
	    }
	    catch (Exception e)
	    {
	      System.out.println(e);
	    }
	    return result;
	  }
}