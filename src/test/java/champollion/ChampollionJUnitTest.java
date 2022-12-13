package champollion;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Date;

public class ChampollionJUnitTest {
	Enseignant untel;
	UE uml, java;
	Intervention interventiontd,interventioncm,interventiontp,interventionjava;
		
	@BeforeEach
	public void setUp() {
		untel = new Enseignant("untel", "untel@gmail.com");
		uml = new UE("UML");
		java = new UE("Programmation en java");
		interventiontd = new Intervention(new Date(),10,2,new Salle("102",35),TypeIntervention.TD,untel,uml);
		interventioncm =  new Intervention(new Date(),10,2,new Salle("102",35),TypeIntervention.CM,untel,uml);
		interventiontp =  new Intervention(new Date(),10,2,new Salle("102",35),TypeIntervention.TP,untel,uml);
	}
	

	@Test
	public void testNouvelEnseignantSansService() {
		assertEquals(0, untel.heuresPrevues(),
                        "Un nouvel enseignant doit avoir 0 heures prévues");
	}
	
	@Test
	public void testAjouteHeures() {
                // 10h TD pour UML
		untel.ajouteEnseignement(uml, 0, 10, 0);

		assertEquals(10, untel.heuresPrevuesPourUE(uml),
                        "L'enseignant doit maintenant avoir 10 heures prévues pour l'UE 'uml'");

                // 20h TD pour UML
                untel.ajouteEnseignement(uml, 0, 20, 0);
                
		assertEquals(10 + 20, untel.heuresPrevuesPourUE(uml),
                         "L'enseignant doit maintenant avoir 30 heures prévues pour l'UE 'uml'");		
		
	}

	@Test
	public void testHeurePrevues(){
		untel.ajouteEnseignement(uml,0,10,0);
		untel.ajouteEnseignement(java,0,5,0);
		assertEquals(15,untel.heuresPrevues());
	}

	@Test
	public void testAjoutIntervention(){
		untel.ajouteEnseignement(uml,0,10,0);
		untel.ajouterIntervention(interventiontd);
		assertEquals(1,untel.getInterventions().size());
		try{
			untel.ajouterIntervention(interventionjava);
		}catch (Exception e){

		}
	}

	@Test
	public void testHeureAPlanifier(){
		untel.ajouteEnseignement(uml,20,20,20);
		untel.ajouterIntervention(interventiontd);
		untel.ajouterIntervention(interventioncm);
		untel.ajouterIntervention(interventiontp);
		assertEquals(18,untel.resteAPlanifier(uml,TypeIntervention.TD));
		assertEquals(18,untel.resteAPlanifier(uml,TypeIntervention.CM));
		assertEquals(18,untel.resteAPlanifier(uml,TypeIntervention.TP));
		//l'intervention dure 2h donc on enleve 2h des 20h de TD, et la valeur attendue est la temps qu'il reste a planifier en TD
		try{
			//untel n'enseigne pas de java
			untel.resteAPlanifier(java,TypeIntervention.TD);
		}catch (Exception e){

		}
	}

	@Test
	public void testEnSousService(){
		untel.ajouteEnseignement(uml,10,10,10);
		untel.ajouterIntervention(interventiontd);
		assertTrue(untel.enSousService());
	}

	
}
