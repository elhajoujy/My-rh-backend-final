package com.example.myrh;


import com.example.myrh.dto.requests.*;
import com.example.myrh.enums.JobApplicationStatus;
import com.example.myrh.enums.StudyLevel;
import com.example.myrh.enums.UserStatus;
import com.example.myrh.model.*;
import com.example.myrh.repository.*;
import com.example.myrh.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);
    private final JobApplicantRepo jobApplicantRepo;
    private final JobSeekerRepo jobSeekerRepo;
    private final QuestionsRepository questionsRepository;
    private final ProfileRepository profileRepository;
    private final AnswerRepository answerRepository;


    public LoadDatabase(JobApplicantRepo jobApplicantRepo, JobSeekerRepo jobSeekerRepo, QuestionsRepository questionsRepository, ProfileRepository profileRepository, AnswerRepository answerRepository) {
        this.jobApplicantRepo = jobApplicantRepo;
        this.jobSeekerRepo = jobSeekerRepo;
        this.questionsRepository = questionsRepository;
        this.profileRepository = profileRepository;
        this.answerRepository = answerRepository;
    }


    @Bean
    CommandLineRunner initDatabase(
            ICompanyService companyService,
            IAgentService agentService,
            IActivityAreaService profileService,
            ICityService cityService,
            IOfferService offerService,
            JobSeekerRepo jobSeekerRepo,
            IJobSeekerService jobSeekerService,
            IJobApplicantService jobApplicantService) {

        return args -> {

            saveJobSeeker(jobSeekerRepo);

            CompanyReq c1 = new CompanyReq();
            c1.setName("Sofrecom");
            c1.setEmail("sofrecom@orange.com");
            c1.setPassword("12345678");
            c1.setImage("sofrecom.png");

            AgentReq agent = new AgentReq();
            agent.setFirst_name("Abdelmalek");
            agent.setLast_name("Achkif");
            agent.setEmail("agent@gmail.com");
            agent.setPassword("aqwzsxedc");
            agent.setCompany(Company.builder().id(1).build());


            CityReq city = new CityReq();
            city.setName("Casablanca");

            CityReq city2 = new CityReq();
            city2.setName("Nador");

            CityReq city3 = new CityReq();
            city3.setName("Rabat");

            ActivityAreaReq profile = new ActivityAreaReq();
            profile.setDescription("Information Technology");

            OfferReq offer = new OfferReq();
            offer.setTitle("Developpeur / Developpeuse Full stack");
            offer.setDescription("EYSI, entreprise de développement informatique, cherche un stagiaire à" +
                    "partir de BAC +2 pour une durée variant entre 2 à 6 mois.");
            offer.setCompany(Company.builder().id(1).build());
            offer.setCity(City.builder().id(1).build());
            offer.setProfile(ActivityArea.builder().id(1).build());
            offer.setLevel(StudyLevel.BacPlus2);
            offer.setSalary(12000);

            JobSeekerReq jobSeeker = new JobSeekerReq();
            jobSeeker.setFirst_name("El Mehdi");
            jobSeeker.setLast_name("El Hajoujy");
            jobSeeker.setEmail("elmehdi@myrh.com");

            jobSeeker.setPassword("testtest");


            log.info("Preloading Company 1: " + companyService.create(c1).toString());
            log.info("Preloading Agent 1 : " + agentService.create(agent).toString());
            log.info("Preloading City 1 : " + cityService.create(city).toString());
            log.info("Preloading City 2 : " + cityService.create(city2).toString());
            log.info("Preloading City 3 : " + cityService.create(city3).toString());

            log.info("Preloading Profile 1 : " + profileService.create(profile).toString());
            log.info("Preloading Offer 1 : " + offerService.create(offer).toString());
            log.info("Preloading JobSeeker 1 : " + jobSeekerService.create(jobSeeker).toString());
            //log.info("Preloading Job Applicant 1 : " + jobApplicantService.create(jobApplicant).toString());
            saveFakeJobApplication();

        };
    }

    private void saveJobSeeker(JobSeekerRepo jobSeekerRepo) {
        JobSeeker jobSeeker = new JobSeeker();
        jobSeeker.setFirst_name("El Mehdi");
        jobSeeker.setLast_name("El Hajoujy");
        jobSeeker.setEmail("elmahdi311@gmail.com");
        jobSeeker.setPassword("testtest");
        jobSeeker.setStatus(UserStatus.ONLINE);
        jobSeeker = jobSeekerRepo.save(jobSeeker);
        log.info("Preloading JobSeeker  : " + jobSeeker.getId() + " " + jobSeeker.getFirst_name() + " " + jobSeeker.getLast_name());
        addListProfile(jobSeeker);

    }

    private void addListProfile(JobSeeker jobSeeker) {
        List.of("Java Developer", "Php Developer", "JavaScript developer").forEach(
                title -> {
                    Profile profile = new Profile();
                    profile.setName(title);
                    profile.setDescription("description of " + title);
                    profile.setImage("path/to/image");
                    profile = this.profileRepository.save(profile);
                    log.info("Preloading Profile  : " + profile.getId() + " " + profile.getName());
                    addListQuestions(profile);
                    addListJobSeeker(profile, jobSeeker);
                }
        );


    }

    private void addListJobSeeker(Profile profile, JobSeeker jobSeeker) {
        jobSeeker.setProfile(profile);

    }

    private void addListQuestions(Profile profile) {
        List.of("What is Java?", "What is Php?", "What is JavaScript?").forEach(
                title -> {
                    Question question = new Question();
                    question.setTitle(title);
                    question.setDescription("description of " + title);
                    question.setType("type of " + title);
                    question.setProfile(profile);
                    question = this.questionsRepository.save(question);
                    log.info("Preloading Question  : " + question.getId() + " " + question.getTitle());
                    addListAnswers(question);
                }
        );
    }

    private void addListAnswers(Question question) {
        List.of("Java is a programming language", "Php is a programming language", "JavaScript is a programming language").forEach(
                title -> {
                    Answer answer = new Answer();
                    answer.setTitle(title);
                    answer.setDescription("description of " + title);
                    answer.setCorrect(false);
                    answer.setQuestion(question);
                    answer = this.answerRepository.save(answer);
                    log.info("Preloading Answer  : " + answer.getId() + " " + answer.getTitle());
                }
        );
    }

    private void saveFakeJobApplication() {
        JobApplicantId jobApplicantId = new JobApplicantId();
        jobApplicantId.setJobSeeker_id(1);
        jobApplicantId.setOffer_id(1);
        JobApplicant jobApplicant = new JobApplicant();
        jobApplicant.setId(jobApplicantId);
        jobApplicant.setResume("path/to/resume");
        jobApplicant.setStatus(JobApplicationStatus.ACCEPTED);
        jobApplicant.setIsViewed(true);


        //jobApplicant.setJobSeeker(jobSeeker);
        //jobApplicant.setIsViewed(true);
        this.jobApplicantRepo.save(jobApplicant);
        jobApplicant.getId().setJobSeeker_id(2);
        this.jobApplicantRepo.save(jobApplicant);


    }
}