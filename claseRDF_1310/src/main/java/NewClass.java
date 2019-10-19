import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import static org.apache.jena.enhanced.BuiltinPersonalities.model;
import org.apache.jena.enhanced.Personality;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Resource;
import static org.apache.jena.vocabulary.DCAT.NS;
import org.apache.jena.vocabulary.RDF;
import org.apache.jena.vocabulary.RDFS;
import org.apache.jena.vocabulary.VCARD;

public class NewClass {
    
 
    private static Resource crearRecurso(String NS,String id, Model model) {
        String resourceURI = NS + id;
        return model.createResource(resourceURI);
    }

    
    public static void main (String [] args){
       String NS="https://www.codigopenalperu/";
       
       Model model = ModelFactory.createDefaultModel();       
       Resource delito = crearRecurso(NS,"Delito",model);
       Resource sansion = crearRecurso(NS,"Sansion",model);
       Resource tvida = crearRecurso(NS,"Titulo Vida Salud",model);
       Resource homicidio = crearRecurso(NS,"Homicidio",model);
       Resource hcalificado = crearRecurso(NS,"H.Calificado",model);
       Resource hculposo = crearRecurso(NS,"H.Culposo",model);
       Resource hsimple = crearRecurso(NS,"H.Simple",model);
       Resource infanticidio = crearRecurso(NS,"Infaticidio",model);
       Resource isuicidio = crearRecurso(NS,"Inst.Suicidio",model);
       Resource hpiadoso = crearRecurso(NS,"H.Piadoso",model);
       Resource hviolenta = crearRecurso(NS,"H.E.Violenta",model);
       Resource parricidio = crearRecurso(NS,"Parricidio",model);
       Resource feminicidio = crearRecurso(NS,"Feminicidio",model);
       Resource sicariato = crearRecurso(NS,"Sicariato",model);
       Resource csicariato = crearRecurso(NS,"Consp.Sicariato",model);
       Resource cvictima = crearRecurso(NS,"CondicionVictima",model);
       
        model.add(tvida,RDFS.subClassOf,delito);
        model.add(homicidio,RDFS.subClassOf,tvida);
        model.add(hcalificado,RDFS.subClassOf,homicidio);
        model.add(hculposo,RDFS.subClassOf,homicidio);
        model.add(hsimple,RDFS.subClassOf, homicidio);
        model.add(infanticidio,RDFS.subClassOf,homicidio);
        model.add(hpiadoso,RDFS.subClassOf,homicidio);
        model.add(hviolenta,RDFS.subClassOf,homicidio);
        
        model.add(parricidio,RDF.type,hsimple);
        model.add(feminicidio,RDF.type,hcalificado);
        model.add(sicariato,RDF.type,hcalificado);
        model.add(csicariato,RDF.type,hcalificado);
        model.add(cvictima,RDF.type,hcalificado);
        
        FileOutputStream output = null;
        try {
            output = new FileOutputStream ("codigopenal.rdf");
        } catch(FileNotFoundException e){
            System.out.println("Ocurrio un error al crear el archivo.");
        }
        model.write(output,"RDF/XML-ABBREV");
    }

       
}