package com.gmnsystems.meliza.config;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.gmnsystems.meliza.models.ConsultationModel;
import com.gmnsystems.meliza.models.PatientModel;
import com.gmnsystems.meliza.models.PlanModel;
import com.gmnsystems.meliza.models.RoleModel;
import com.gmnsystems.meliza.models.ScheduleModel;
import com.gmnsystems.meliza.models.SubscriptionModel;
import com.gmnsystems.meliza.models.UserModel;
import com.gmnsystems.meliza.models.enums.ConsultationStatus;
import com.gmnsystems.meliza.models.enums.SubscriptionStatus;
import com.gmnsystems.meliza.repositories.ConsultationRepository;
import com.gmnsystems.meliza.repositories.PatientRepository;
import com.gmnsystems.meliza.repositories.PlanRepository;
import com.gmnsystems.meliza.repositories.RoleRepository;
import com.gmnsystems.meliza.repositories.ScheduleRepository;
import com.gmnsystems.meliza.repositories.SubscriptionRepository;
// import com.gmnsystems.meliza.repositories.SubscriptionRepository;
import com.gmnsystems.meliza.repositories.UserRepository;

// Classe que fará o seed do banco de dados
// da aplicação

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

        // Variável com o dia de hoje
        LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());

        // Chamando repositórios

        @Autowired
        RoleRepository roleRepository;

        @Autowired
        PatientRepository patientRepository;

        @Autowired
        PlanRepository planRepository;

        @Autowired
        SubscriptionRepository subscriptionRepository;

        @Autowired
        ConsultationRepository consultationRepository;

        @Autowired
        ScheduleRepository scheduleRepository;

        @Autowired
        UserRepository userRepository;

        @Override
        public void run(String... args) throws Exception {

                // roles
                RoleModel role_admin = new RoleModel();
                role_admin.setName("ADMIN");
                RoleModel role_user = new RoleModel();
                role_user.setName("USER");
                roleRepository.saveAll(Arrays.asList(role_admin, role_user));

                // Usuários
                UserModel user = new UserModel(
                                null,
                                "Melissa",
                                "melissa@gmail.com",
                                new BCryptPasswordEncoder().encode("senha123"));

                UserModel user2 = new UserModel(
                                null,
                                "Airton Sena",
                                "airtaodamassa@gmail.com",
                                new BCryptPasswordEncoder().encode("senha123"));

                userRepository.saveAll(Arrays.asList(user, user2));

                // Planos
                PlanModel plan = new PlanModel(
                                null,
                                "Mensal",
                                4,
                                400.00,
                                1,
                                today,
                                user);

                planRepository.saveAll(Arrays.asList(plan));

                PlanModel plan2 = new PlanModel(
                                null,
                                "Bimestral",
                                4,
                                400.00,
                                1,
                                today,
                                user2);

                planRepository.saveAll(Arrays.asList(plan2));

                // Pacientes
                PatientModel patient = new PatientModel(
                                null,
                                "Gabriel",
                                "de Menezes Nogueira",
                                "gabrielmenesn18@gmail.com",
                                "41998447206",
                                "12445291925",
                                today,
                                user);

                patientRepository.saveAll(Arrays.asList(patient));

                // Agendas
                ScheduleModel schedule = new ScheduleModel(
                                null,
                                "Aila Pereira",
                                today,
                                user);

                ScheduleModel schedule2 = new ScheduleModel(
                                null,
                                "Melissa Silva",
                                today,
                                user);

                scheduleRepository.saveAll(Arrays.asList(schedule, schedule2));

                user.getSchedules().addAll(Arrays.asList(schedule, schedule2));
                userRepository.save(user);

                ScheduleModel schedule3 = new ScheduleModel(
                                null,
                                "João Paulo",
                                today,
                                user2);

                ScheduleModel schedule4 = new ScheduleModel(
                                null,
                                "Joana Artuso",
                                today,
                                user2);

                scheduleRepository.saveAll(Arrays.asList(schedule3, schedule4));

                user2.getSchedules().addAll(Arrays.asList(schedule3, schedule4));
                userRepository.save(user2);

                // Assinaturas
                SubscriptionModel subscription = new SubscriptionModel(
                                null,
                                today,
                                today.plusMonths(plan.getDurationInMonths()),
                                SubscriptionStatus.ACTIVE,
                                patient,
                                plan,
                                user);

                subscriptionRepository.saveAll(Arrays.asList(subscription));

                SubscriptionModel subscription2 = new SubscriptionModel(
                                null,
                                today,
                                today.plusMonths(plan.getDurationInMonths()),
                                SubscriptionStatus.ACTIVE,
                                patient,
                                plan2,
                                user2);

                subscriptionRepository.saveAll(Arrays.asList(subscription2));

                // consultas
                ConsultationModel consultation = new ConsultationModel(
                                null,
                                LocalDate.parse("2022-10-10"),
                                LocalTime.parse("08:00"),
                                today,
                                ConsultationStatus.WAITING_CONFIRMATION,
                                patient,
                                schedule,
                                user);

                consultationRepository.saveAll(Arrays.asList(consultation));
        }

}