package model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Date;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.transform.stream.StreamResult;

public class PurchaseOrderUtility
{

	public static void generatePurchaseOrder(String username, ShoppingCart cart, String directory)
	{
		System.out.println("file exists: " + new File(directory + "/test").exists());
		try
		{
			String filename = getPOFileName(username, directory);
			// the PO number will be greatestnumber + 1
			ArrayList<ShoppingCartItemWrapper> itemsWrapped = new ArrayList<>();
			for (ShoppingCartItem item : cart.getShoppingCart().values())
			{
				itemsWrapped.add(new ShoppingCartItemWrapper(item));
			}

			PurchaseOrder p = new PurchaseOrder(getPONumber(username, directory), new Date(), new CustomerWrapper("cse23116", "cse23116"),
					itemsWrapped, cart.computeSubTotal(), cart.computeShippingCost(), cart.computeTax(),
					cart.computeGrandTotal());
			StringWriter output = new StringWriter();
			output.write("\n");

			JAXBContext contextObj = JAXBContext.newInstance(PurchaseOrder.class);
			Marshaller marshallerObj = contextObj.createMarshaller();
			marshallerObj.setProperty(Marshaller.JAXB_FRAGMENT, true);
			marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			// marshallerObj.setSchema(schema);
			System.out.println("directory: " + directory + File.separator + filename);
			marshallerObj.marshal(p, new StreamResult(output));
			String po = directory + File.separator + filename;
			FileWriter fw = new FileWriter(po);
			fw.write("<?xml version='1.0'?>\n");
			fw.write("<?xml-stylesheet type='text/xsl' href='PO.xsl'?>");
			fw.write(output.toString());
			fw.close();
			System.out.println("The file was written: " + new File(directory + File.separator + filename).exists());
		} catch (Exception e)
		{
			System.out.println("Something went wrong with generating the purchase order!");
			e.printStackTrace();

		}
	}

	private static String getPOFileName(String username, String directory)
	{
		int poNumber = getPONumber(username, directory);
		if (poNumber < 10)
		{
			return "po" + username + "_0" + (poNumber) + ".xml";
		} else
		{
			return "po" + username + "_" + (poNumber) + ".xml";
		}

	}
	private static int getPONumber(String username, String directory) {
		File purchaseFolder = new File(directory);
		System.out.println(purchaseFolder.isDirectory());
		int greatestNumber = 0;
		for (File f : purchaseFolder.listFiles())
		{
			
			String poName = f.getName();
			int i = poName.lastIndexOf('.');
			if (i > 0) {
			    String extension = poName.substring(i+1);
			    if (!extension.equals("xml"))
				{
					continue;
				}
			}
			if (poName.contains(".")) // get rid of the extension name
				poName = poName.substring(0, poName.lastIndexOf('.'));
			int beginningOfUsername = 2;
			int endOfUsername = f.getName().lastIndexOf("_");
			System.out.println("Name: " + poName.substring(beginningOfUsername, endOfUsername));
			if (poName.substring(beginningOfUsername, endOfUsername).equals(username))
			{
				int number = Integer.parseInt(poName.substring(endOfUsername + 1));
				if (number > greatestNumber)
					greatestNumber = number;
			}
		}
		return greatestNumber + 1;
	}
}
