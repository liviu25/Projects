package Repository;

import Domain.Nota;
import Validator.Validator;
import Validator.ValidationException;

import java.io.File;
import java.time.LocalDateTime;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

public class NoteXMLRepository extends NoteRepository {
    private String fileN;

    public NoteXMLRepository(Validator<Nota> v, String fileN) {
        super(v);
        this.fileN = fileN;
        loadFromFile();
    }

    private Nota createNotaFromElement(Element notaElement)
    {
        Nota nota=new Nota();
        String id=notaElement.getAttribute("notaID");
        nota.setID(id);

        String studentID=notaElement.getElementsByTagName("studentID").item(0).getTextContent();
        String temaID=notaElement.getElementsByTagName("temaID").item(0).getTextContent();
        int valoare=Integer.parseInt(notaElement.getElementsByTagName("valoare").item(0).getTextContent());
        String sdata=String.valueOf(notaElement.getElementsByTagName("data").item(0).getTextContent());
        LocalDateTime data=LocalDateTime.parse(sdata);

        nota.setIdStudent(studentID);
        nota.setIdTema(temaID);
        nota.setValoare(valoare);
        nota.setData(data);

        return nota;
    }

    private void loadFromFile()
    {
        try {
            Document document= DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(fileN);
            Element root=document.getDocumentElement();
            NodeList notaElements=root.getChildNodes();
            for(int i=0; i<notaElements.getLength();i++)
            {
                Node notaElement=notaElements.item(i);
                if (notaElement.getNodeType() == Node.ELEMENT_NODE){
                    Nota b=createNotaFromElement((Element)notaElement); //cast
                    super.save(b);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Element createElementFromNota(Document document, Nota nota) {
        Element element=document.createElement("nota");
        element.setAttribute("notaID",nota.getID());

        Element studentID=document.createElement("studentID");
        studentID.setTextContent(nota.getIdStudent());
        element.appendChild(studentID);

        Element temaID=document.createElement("temaID");
        temaID.setTextContent(nota.getIdTema());
        element.appendChild(temaID);

        Element valoare=document.createElement("valoare");
        valoare.setTextContent(String.valueOf(nota.getValoare()));
        element.appendChild(valoare);

        Element data=document.createElement("data");
        data.setTextContent(String.valueOf(nota.getData()));
        element.appendChild(data);


        return element;
    }

    private void writeToFile(){
        try {
        //create an empty Document
            Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
        //add the list elements to Document
            Element root=document.createElement("note");
            document.appendChild(root);
            findAll().forEach(nota -> {
                Element notaElement=createElementFromNota(document, nota);
                root.appendChild(notaElement);
            });
        //write Document to file
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(new DOMSource(document), new StreamResult(fileN));
        }
        catch (Exception ex){}
    }

    @Override
    public Nota save(Nota nota) throws ValidationException {
        nota=super.save(nota);
        writeToFile();
        return nota;
    }

    @Override
    public Nota update(Nota nota) throws ValidationException {
        nota=super.update(nota);
        writeToFile();
        return nota;
    }

    @Override
    public Nota delete(String s) throws ValidationException{
        Nota nota=super.delete(s);
        writeToFile();
        return nota;
    }
}
