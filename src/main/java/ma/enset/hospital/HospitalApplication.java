package ma.enset.hospital;

import ma.enset.hospital.entities.Medecin;
import ma.enset.hospital.entities.Patient;
import ma.enset.hospital.entities.RendezVous;
import ma.enset.hospital.entities.StatusRDV;
import ma.enset.hospital.repositories.MedecinRepository;
import ma.enset.hospital.repositories.PatientRepository;
import ma.enset.hospital.repositories.RendezVousRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.stream.Stream;

@SpringBootApplication
public class HospitalApplication {

	public static void main(String[] args) {

		SpringApplication.run(HospitalApplication.class, args);
	}
	@Bean
	CommandLineRunner start(PatientRepository patientRepository, MedecinRepository medecinRepository, RendezVousRepository rendezVousRepository){
		return args -> {
			Stream.of("badr" , "fedwa" , "bouchra", "mohammed")
					.forEach( name ->{
							Patient patient = new Patient();
							patient.setNom(name);
		                 	patient.setDateDeNaissance(new Date());
		                 	patient.setMalade(false);
		                	patientRepository.save(patient);
					});

			Stream.of("reda" , "aymen" , "salah", "yassmine")
					.forEach( name ->{
						Medecin medecin = new Medecin();
						medecin.setNom(name);
						medecin.setEmail(name+"@gmail.com");
						medecin.setSpecialite(Math.random()>0.5? "Cardio" : "Dentiste");

						medecinRepository.save(medecin);
					});

			Patient patient = patientRepository.findById(1L).orElse(null);
			Patient patient1 = patientRepository.findByNom("badr");

			Medecin medecin = medecinRepository.findByNom("reda");

			RendezVous rendezVous = new RendezVous();
			rendezVous.setData(new Date());
			rendezVous.setStatus(StatusRDV.PENDING);
			rendezVous.setMedecin(medecin);
			rendezVous.setPatient(patient);
			rendezVousRepository.save(rendezVous);







		};

	}


}
