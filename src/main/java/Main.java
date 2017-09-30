import Models.AccountingSeat;
import Models.Batch;
import Utils.HibernateUtils;
import com.sun.xml.internal.txw2.output.XmlSerializer;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.xml.bind.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {


    private static boolean isOn = true;
    private static String fileName = "C:\\Users\\jafet\\Documents\\Academico\\MyOrder.xml";

    public  static void getAll(){
        List<AccountingSeat> addressList = new ArrayList<AccountingSeat>();

        Transaction trns = null;

        Session session = HibernateUtils.getSessionFactory().openSession();

        try {
            trns = session.beginTransaction();

            addressList = session.createQuery("from Models.AccountingSeat").list();
        }

        catch (RuntimeException e) {
            System.out.println("ALgo va mal...");
            e.printStackTrace();
        }

        finally {
            session.flush();
            session.close();
        }
        System.out.print("Result");
        System.out.println(addressList.size());

        Batch batch = new Batch();
        batch.setAccountingSeats(addressList);
        batch.setDate(new Date());

        try {

            JAXBContext jaxbContext = JAXBContext.newInstance(Batch.class);
            //class responsible for the process of
            //serializing Java object into XML data
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            //marshalled XML data is formatted with linefeeds and indentation
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            //specify the xsi:schemaLocation attribute value
            //to place in the marshalled XML output
            jaxbMarshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION,
                    "carbono.do");

            //send to console
            jaxbMarshaller.marshal(batch, System.out);
            //send to file system
            OutputStream os = new FileOutputStream(fileName );
            jaxbMarshaller.marshal(batch, os );

        } catch (JAXBException e) {
            e.printStackTrace();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }

    public static void importData(){
        try {

            //create file input stream
            InputStream is = new FileInputStream( fileName );
            //XML and Java binding
            JAXBContext jaxbContext = JAXBContext.newInstance(Batch.class);

            //class responsible for the process of deserializing
            //XML data into Java object
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            Batch orderHeader = (Batch) jaxbUnmarshaller.unmarshal(is);

            //print the response for debugging
            System.out.println("Order Information --->");
            System.out.println("Batch Date: " + orderHeader.getDate());


            List<AccountingSeat> orderDetailList = orderHeader.getAccountingSeats();
            for (int i=0; i<orderDetailList.size(); i++){
                System.out.println("Batch Detail --->");
                AccountingSeat orderDetail = orderDetailList.get(i);
                System.out.println("Number: " + orderDetail.getNumber());
                System.out.println("Amount: " + orderDetail.getAmount());
                System.out.println("Type: " + orderDetail.getType());
                System.out.println("Date: " + orderDetail.getDate());


            }

        } catch (JAXBException e) {
            e.printStackTrace();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args){

        HibernateUtils.getHibernateConfiguration();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while(isOn){
            System.out.println("AccountingSeats - XML Exporter");
            System.out.println("1 - Importar");
            System.out.println("2 - Exportar");
            System.out.print("Seleccion: ");
            String opt = "0";

            try {
                opt = br.readLine();
            } catch (IOException e) {
                //e.printStackTrace();
                System.out.println("Algo ocurrio mal");
            }

            int optInt = 0;

            try {
                optInt = Integer.parseInt(opt);
            }
            catch (Exception e){
                System.out.println("Opcion invalida");
            }

            switch (optInt){
                case  0:
                    break;
                case 1:
                    importData();
                    break;
                case 2:
                    getAll();
                    break;

            }


        }



    }
}
