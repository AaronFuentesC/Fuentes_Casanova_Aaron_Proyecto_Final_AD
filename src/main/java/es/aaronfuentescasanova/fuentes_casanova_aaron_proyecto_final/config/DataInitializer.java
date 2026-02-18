package es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.config;

import es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.model.*;
import es.aaronfuentescasanova.fuentes_casanova_aaron_proyecto_final.repository.*;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class DataInitializer {

    private final EquipoRepository equipoRepository;
    private final JugadorRepository jugadorRepository;
    private final EntrenadorRepository entrenadorRepository;
    private final TorneoRepository torneoRepository;
    private final PartidoRepository partidoRepository;

    @Value("${app.data.init:false}")
    private boolean initData;

    @Bean
    CommandLineRunner initData() {
        return args -> {

            if (!initData) {
                return;
            }

            // ======================
            // EQUIPOS ESPAÑOLES
            // ======================
            Equipo barcelona = Equipo.builder()
                    .nombre("FC Barcelona")
                    .ciudad("Barcelona")
                    .pais("España")
                    .estadio("Camp Nou")
                    .fechaFundacion(LocalDate.of(1899, 11, 29))
                    .build();

            Equipo madrid = Equipo.builder()
                    .nombre("Real Madrid")
                    .ciudad("Madrid")
                    .pais("España")
                    .estadio("Santiago Bernabéu")
                    .fechaFundacion(LocalDate.of(1902, 3, 6))
                    .build();

            Equipo atletico = Equipo.builder()
                    .nombre("Atlético de Madrid")
                    .ciudad("Madrid")
                    .pais("España")
                    .estadio("Wanda Metropolitano")
                    .fechaFundacion(LocalDate.of(1903, 4, 26))
                    .build();

            Equipo sevilla = Equipo.builder()
                    .nombre("Sevilla FC")
                    .ciudad("Sevilla")
                    .pais("España")
                    .estadio("Ramón Sánchez Pizjuán")
                    .fechaFundacion(LocalDate.of(1890, 10, 25))
                    .build();



            equipoRepository.saveAll(List.of(barcelona, madrid, atletico, sevilla));

            // ======================
            // EQUIPOS INGLESES
            // ======================

            Equipo manCity = Equipo.builder()
                    .nombre("Manchester City")
                    .ciudad("Manchester")
                    .pais("Inglaterra")
                    .estadio("Etihad Stadium")
                    .fechaFundacion(LocalDate.of(1880, 4, 16))
                    .build();

            Equipo arsenal = Equipo.builder()
                    .nombre("Arsenal FC")
                    .ciudad("Londres")
                    .pais("Inglaterra")
                    .estadio("Emirates Stadium")
                    .fechaFundacion(LocalDate.of(1886, 10, 1))
                    .build();

            Equipo liverpool = Equipo.builder()
                    .nombre("Liverpool FC")
                    .ciudad("Liverpool")
                    .pais("Inglaterra")
                    .estadio("Anfield")
                    .fechaFundacion(LocalDate.of(1892, 6, 3))
                    .build();

            equipoRepository.saveAll(List.of(manCity, arsenal, liverpool));

            // ======================
            // EQUIPOS ALEMANES
            // ======================

            Equipo bayern = Equipo.builder()
                    .nombre("Bayern de Múnich")
                    .ciudad("Múnich")
                    .pais("Alemania")
                    .estadio("Allianz Arena")
                    .fechaFundacion(LocalDate.of(1900, 2, 27))
                    .build();

            Equipo dortmund = Equipo.builder()
                    .nombre("Borussia Dortmund")
                    .ciudad("Dortmund")
                    .pais("Alemania")
                    .estadio("Signal Iduna Park")
                    .fechaFundacion(LocalDate.of(1909, 12, 19))
                    .build();

            Equipo leipzig = Equipo.builder()
                    .nombre("RB Leipzig")
                    .ciudad("Leipzig")
                    .pais("Alemania")
                    .estadio("Red Bull Arena")
                    .fechaFundacion(LocalDate.of(2009, 5, 19))
                    .build();

            equipoRepository.saveAll(List.of(bayern, dortmund, leipzig));

            // ======================
            // EQUIPOS ITALIANOS
            // ======================

            Equipo juventus = Equipo.builder()
                    .nombre("Juventus")
                    .ciudad("Turín")
                    .pais("Italia")
                    .estadio("Allianz Stadium")
                    .fechaFundacion(LocalDate.of(1897, 11, 1))
                    .build();

            Equipo milan = Equipo.builder()
                    .nombre("AC Milan")
                    .ciudad("Milán")
                    .pais("Italia")
                    .estadio("San Siro")
                    .fechaFundacion(LocalDate.of(1899, 12, 16))
                    .build();

            Equipo inter = Equipo.builder()
                    .nombre("Inter de Milán")
                    .ciudad("Milán")
                    .pais("Italia")
                    .estadio("San Siro")
                    .fechaFundacion(LocalDate.of(1908, 3, 9))
                    .build();

            Equipo napoli = Equipo.builder()
                    .nombre("Napoli")
                    .ciudad("Nápoles")
                    .pais("Italia")
                    .estadio("Diego Armando Maradona")
                    .fechaFundacion(LocalDate.of(1926, 8, 25))
                    .build();

            equipoRepository.saveAll(List.of(juventus, milan, inter, napoli));

            // ======================
            // EQUIPOS FRANCESES
            // ======================

            Equipo psg = Equipo.builder()
                    .nombre("Paris Saint-Germain")
                    .ciudad("París")
                    .pais("Francia")
                    .estadio("Parc des Princes")
                    .fechaFundacion(LocalDate.of(1970, 8, 12))
                    .build();

            Equipo marseille = Equipo.builder()
                    .nombre("Olympique de Marseille")
                    .ciudad("Marsella")
                    .pais("Francia")
                    .estadio("Stade Vélodrome")
                    .fechaFundacion(LocalDate.of(1899, 8, 31))
                    .build();

            Equipo lyon = Equipo.builder()
                    .nombre("Olympique Lyonnais")
                    .ciudad("Lyon")
                    .pais("Francia")
                    .estadio("Groupama Stadium")
                    .fechaFundacion(LocalDate.of(1950, 8, 3))
                    .build();

            Equipo monaco = Equipo.builder()
                    .nombre("AS Monaco")
                    .ciudad("Mónaco")
                    .pais("Francia")
                    .estadio("Stade Louis II")
                    .fechaFundacion(LocalDate.of(1924, 8, 23))
                    .build();

            equipoRepository.saveAll(List.of(psg, marseille, lyon, monaco));


            /*
            ******************************************************************************************
            * ****************************************************************************************
            * ****************************************************************************************
            * ****************************************************************************************
             */


            // ======================
            // ENTRENADORES LIGA ESPAÑOLA
            // ======================
            Entrenador xavi = Entrenador.builder()
                    .nombre("Xavi Hernández")
                    .fechaNacimiento(LocalDate.of(1980, 1, 25))
                    .nacionalidad("España")
                    .equipo(barcelona)
                    .build();

            Entrenador ancelotti = Entrenador.builder()
                    .nombre("Carlo Ancelotti")
                    .fechaNacimiento(LocalDate.of(1959, 6, 10))
                    .nacionalidad("Italia")
                    .equipo(madrid)
                    .build();

            Entrenador simone = Entrenador.builder()
                    .nombre("Diego Simeone")
                    .fechaNacimiento(LocalDate.of(1970, 4, 28))
                    .nacionalidad("Argentina")
                    .equipo(atletico)
                    .build();

            Entrenador lopetegui = Entrenador.builder()
                    .nombre("Julen Lopetegui")
                    .fechaNacimiento(LocalDate.of(1966, 8, 28))    // fechaNacimiento)
                    .nacionalidad("España")
                    .equipo(sevilla)
                    .build();


            entrenadorRepository.saveAll(List.of(xavi, ancelotti, simone,lopetegui));

            // ======================
            // ENTRENADORES LIGA INGLESA
            // ======================

            Entrenador guardiola = Entrenador.builder()
                    .nombre("Pep Guardiola")
                    .fechaNacimiento(LocalDate.of(1971, 1, 18))
                    .nacionalidad("España")
                    .equipo(manCity)
                    .build();

            Entrenador arteta = Entrenador.builder()
                    .nombre("Mikel Arteta")
                    .fechaNacimiento(LocalDate.of(1982, 3, 26))
                    .nacionalidad("España")
                    .equipo(arsenal)
                    .build();

            Entrenador klopp = Entrenador.builder()
                    .nombre("Jürgen Klopp")
                    .fechaNacimiento(LocalDate.of(1967, 6, 16))
                    .nacionalidad("Alemania")
                    .equipo(liverpool)
                    .build();

            entrenadorRepository.saveAll(List.of(guardiola, arteta, klopp));

            // ======================
            // ENTRENADORES LIGA ALEMANA
            // ======================

            Entrenador tuchel = Entrenador.builder()
                    .nombre("Thomas Tuchel")
                    .fechaNacimiento(LocalDate.of(1973, 8, 29))
                    .nacionalidad("Alemania")
                    .equipo(bayern)
                    .build();

            Entrenador terzic = Entrenador.builder()
                    .nombre("Edin Terzić")
                    .fechaNacimiento(LocalDate.of(1982, 10, 30))
                    .nacionalidad("Alemania")
                    .equipo(dortmund)
                    .build();

            Entrenador rose = Entrenador.builder()
                    .nombre("Marco Rose")
                    .fechaNacimiento(LocalDate.of(1976, 9, 11))
                    .nacionalidad("Alemania")
                    .equipo(leipzig)
                    .build();

            entrenadorRepository.saveAll(List.of(tuchel, terzic, rose));

            // ======================
            // ENTRENADORES LIGA ITALIANA
            // ======================

            Entrenador allegri = Entrenador.builder()
                    .nombre("Massimiliano Allegri")
                    .fechaNacimiento(LocalDate.of(1967, 8, 11))
                    .nacionalidad("Italia")
                    .equipo(juventus)
                    .build();

            Entrenador pioli = Entrenador.builder()
                    .nombre("Stefano Pioli")
                    .fechaNacimiento(LocalDate.of(1965, 10, 20))
                    .nacionalidad("Italia")
                    .equipo(milan)
                    .build();

            Entrenador inzaghi = Entrenador.builder()
                    .nombre("Simone Inzaghi")
                    .fechaNacimiento(LocalDate.of(1976, 4, 5))
                    .nacionalidad("Italia")
                    .equipo(inter)
                    .build();

            Entrenador garcia = Entrenador.builder()
                    .nombre("Rudi Garcia")
                    .fechaNacimiento(LocalDate.of(1964, 2, 20))
                    .nacionalidad("Francia")
                    .equipo(napoli)
                    .build();

            entrenadorRepository.saveAll(List.of(allegri, pioli, inzaghi, garcia));

            // ======================
            // ENTRENADORES LIGA FRANCESA
            // ======================
            Entrenador luisEnrique = Entrenador.builder()
                    .nombre("Luis Enrique")
                    .fechaNacimiento(LocalDate.of(1970, 5, 8))
                    .nacionalidad("España")
                    .equipo(psg)
                    .build();

            Entrenador gattuso = Entrenador.builder()
                    .nombre("Gennaro Gattuso")
                    .fechaNacimiento(LocalDate.of(1978, 1, 9))
                    .nacionalidad("Italia")
                    .equipo(marseille)
                    .build();

            Entrenador sage = Entrenador.builder()
                    .nombre("Pierre Sage")
                    .fechaNacimiento(LocalDate.of(1979, 7, 1))
                    .nacionalidad("Francia")
                    .equipo(lyon)
                    .build();

            Entrenador hutter = Entrenador.builder()
                    .nombre("Adi Hütter")
                    .fechaNacimiento(LocalDate.of(1970, 2, 11))
                    .nacionalidad("Austria")
                    .equipo(monaco)
                    .build();

            entrenadorRepository.saveAll(List.of(luisEnrique, gattuso, sage, hutter));



            /*
             ******************************************************************************************
             * ****************************************************************************************
             * ****************************************************************************************
             * ****************************************************************************************
             */



            // ======================
            // JUGADORES LIGA ESPAÑOLA
            // ======================
            Jugador pedri = Jugador.builder()
                    .nombre("Pedri")
                    .fechaNacimiento(LocalDate.of(2002, 11, 25))
                    .posicion("Centrocampista")
                    .dorsal(8)
                    .equipo(barcelona)
                    .build();

            Jugador gavi = Jugador.builder()
                    .nombre("Gavi")
                    .fechaNacimiento(LocalDate.of(2004, 8, 5))
                    .posicion("Centrocampista")
                    .dorsal(6)
                    .equipo(barcelona)
                    .build();

            Jugador terStegen = Jugador.builder()
                    .nombre("Marc-André ter Stegen")
                    .fechaNacimiento(LocalDate.of(1992, 4, 30))
                    .posicion("Portero")
                    .dorsal(1)
                    .equipo(barcelona)
                    .build();

            Jugador bellingham = Jugador.builder()
                    .nombre("Jude Bellingham")
                    .fechaNacimiento(LocalDate.of(2003, 6, 29))
                    .posicion("Centrocampista")
                    .dorsal(5)
                    .equipo(madrid)
                    .build();

            Jugador courtois = Jugador.builder()
                    .nombre("Thibaut Courtois")
                    .fechaNacimiento(LocalDate.of(1992, 5, 11))
                    .posicion("Portero")
                    .dorsal(1)
                    .equipo(madrid)
                    .build();

            Jugador felix = Jugador.builder()
                    .nombre("João Félix")
                    .fechaNacimiento(LocalDate.of(1999, 11, 10))
                    .posicion("Delantero")
                    .dorsal(7)
                    .equipo(atletico)
                    .build();

            Jugador oblak = Jugador.builder()
                    .nombre("Jan Oblak")
                    .fechaNacimiento(LocalDate.of(1993, 1, 7))
                    .posicion("Portero")
                    .dorsal(1)
                    .equipo(atletico)
                    .build();

            Jugador bono = Jugador.builder()
                    .nombre("Yassine Bono")
                    .fechaNacimiento(LocalDate.of(1991, 4, 5))
                    .posicion("Portero")
                    .dorsal(13)
                    .equipo(sevilla)
                    .build();

            Jugador ramos = Jugador.builder()
                    .nombre("Sergio Ramos")
                    .fechaNacimiento(LocalDate.of(1986, 3, 30))
                    .posicion("Defensa")
                    .dorsal(4)
                    .equipo(sevilla)
                    .build();

            Jugador enNesyri = Jugador.builder()
                    .nombre("Youssef En-Nesyri")
                    .fechaNacimiento(LocalDate.of(1997, 6, 1))
                    .posicion("Delantero")
                    .dorsal(15)
                    .equipo(sevilla)
                    .build();



            jugadorRepository.saveAll(List.of(pedri, gavi, terStegen, bellingham, courtois, felix, oblak,bono,ramos,enNesyri));

            // ======================
            // JUGADORES LIGA INGLESA
            // ======================

            Jugador ederson = Jugador.builder()
                    .nombre("Ederson Moraes")
                    .fechaNacimiento(LocalDate.of(1993, 8, 17))
                    .posicion("Portero")
                    .dorsal(31)
                    .equipo(manCity)
                    .build();

            Jugador raya = Jugador.builder()
                    .nombre("David Raya")
                    .fechaNacimiento(LocalDate.of(1995, 9, 15))
                    .posicion("Portero")
                    .dorsal(22)
                    .equipo(arsenal)
                    .build();

            Jugador alisson = Jugador.builder()
                    .nombre("Alisson Becker")
                    .fechaNacimiento(LocalDate.of(1992, 10, 2))
                    .posicion("Portero")
                    .dorsal(1)
                    .equipo(liverpool)
                    .build();


            Jugador haaland = Jugador.builder()
                    .nombre("Erling Haaland")
                    .fechaNacimiento(LocalDate.of(2000, 7, 21))
                    .posicion("Delantero")
                    .dorsal(9)
                    .equipo(manCity)
                    .build();

            Jugador saka = Jugador.builder()
                    .nombre("Bukayo Saka")
                    .fechaNacimiento(LocalDate.of(2001, 9, 5))
                    .posicion("Extremo")
                    .dorsal(7)
                    .equipo(arsenal)
                    .build();

            Jugador salah = Jugador.builder()
                    .nombre("Mohamed Salah")
                    .fechaNacimiento(LocalDate.of(1992, 6, 15))
                    .posicion("Extremo")
                    .dorsal(11)
                    .equipo(liverpool)
                    .build();

            jugadorRepository.saveAll(List.of(
                    haaland, saka, salah,
                    ederson, raya, alisson
            ));

            // ======================
            // JUGADORES LIGA ALEMANA
            // ======================

            Jugador kane = Jugador.builder()
                    .nombre("Harry Kane")
                    .fechaNacimiento(LocalDate.of(1993, 7, 28))
                    .posicion("Delantero")
                    .dorsal(9)
                    .equipo(bayern)
                    .build();

            Jugador musiala = Jugador.builder()
                    .nombre("Jamal Musiala")
                    .fechaNacimiento(LocalDate.of(2003, 2, 26))
                    .posicion("Centrocampista")
                    .dorsal(42)
                    .equipo(bayern)
                    .build();

            Jugador brandt = Jugador.builder()
                    .nombre("Julian Brandt")
                    .fechaNacimiento(LocalDate.of(1996, 5, 2))
                    .posicion("Centrocampista")
                    .dorsal(19)
                    .equipo(dortmund)
                    .build();

            Jugador sesko = Jugador.builder()
                    .nombre("Benjamin Šeško")
                    .fechaNacimiento(LocalDate.of(2003, 5, 31))
                    .posicion("Delantero")
                    .dorsal(30)
                    .equipo(leipzig)
                    .build();

            Jugador neuer = Jugador.builder()
                    .nombre("Manuel Neuer")
                    .fechaNacimiento(LocalDate.of(1986, 3, 27))
                    .posicion("Portero")
                    .dorsal(1)
                    .equipo(bayern)
                    .build();

            Jugador burki = Jugador.builder()
                    .nombre("Roman Bürki")
                    .fechaNacimiento(LocalDate.of(1990, 11, 14))
                    .posicion("Portero")
                    .dorsal(1)
                    .equipo(dortmund)
                    .build();

            Jugador gulacsi = Jugador.builder()
                    .nombre("Péter Gulácsi")
                    .fechaNacimiento(LocalDate.of(1990, 5, 6))
                    .posicion("Portero")
                    .dorsal(1)
                    .equipo(leipzig)
                    .build();

            jugadorRepository.saveAll(List.of(kane, musiala, brandt, sesko, neuer, burki, gulacsi));

            // ======================
            // JUGADORES LIGA ITALIANA
            // ======================

            Jugador szczesny = Jugador.builder()
                    .nombre("Wojciech Szczęsny")
                    .fechaNacimiento(LocalDate.of(1990, 4, 18))
                    .posicion("Portero")
                    .dorsal(1)
                    .equipo(juventus)
                    .build();

            Jugador vlahovic = Jugador.builder()
                    .nombre("Dušan Vlahović")
                    .fechaNacimiento(LocalDate.of(2000, 1, 28))
                    .posicion("Delantero")
                    .dorsal(9)
                    .equipo(juventus)
                    .build();

            Jugador maignan = Jugador.builder()
                    .nombre("Mike Maignan")
                    .fechaNacimiento(LocalDate.of(1995, 7, 3))
                    .posicion("Portero")
                    .dorsal(16)
                    .equipo(milan)
                    .build();

            Jugador leao = Jugador.builder()
                    .nombre("Rafael Leão")
                    .fechaNacimiento(LocalDate.of(1999, 6, 10))
                    .posicion("Delantero")
                    .dorsal(10)
                    .equipo(milan)
                    .build();

            Jugador sommer = Jugador.builder()
                    .nombre("Yann Sommer")
                    .fechaNacimiento(LocalDate.of(1988, 12, 17))
                    .posicion("Portero")
                    .dorsal(1)
                    .equipo(inter)
                    .build();

            Jugador lautaro = Jugador.builder()
                    .nombre("Lautaro Martínez")
                    .fechaNacimiento(LocalDate.of(1997, 8, 22))
                    .posicion("Delantero")
                    .dorsal(10)
                    .equipo(inter)
                    .build();

            Jugador meret = Jugador.builder()
                    .nombre("Alex Meret")
                    .fechaNacimiento(LocalDate.of(1997, 3, 22))
                    .posicion("Portero")
                    .dorsal(1)
                    .equipo(napoli)
                    .build();

            Jugador osimhen = Jugador.builder()
                    .nombre("Victor Osimhen")
                    .fechaNacimiento(LocalDate.of(1998, 12, 29))
                    .posicion("Delantero")
                    .dorsal(9)
                    .equipo(napoli)
                    .build();

            jugadorRepository.saveAll(List.of(
                    szczesny, vlahovic,
                    maignan, leao,
                    sommer, lautaro,
                    meret, osimhen
            ));

            // ======================
            // JUGADORES LIGA FRANCESA
            // ======================

            Jugador donnarumma = Jugador.builder()
                    .nombre("Gianluigi Donnarumma")
                    .fechaNacimiento(LocalDate.of(1999, 2, 25))
                    .posicion("Portero")
                    .dorsal(99)
                    .equipo(psg)
                    .build();

            Jugador mbappe = Jugador.builder()
                    .nombre("Kylian Mbappé")
                    .fechaNacimiento(LocalDate.of(1998, 12, 20))
                    .posicion("Delantero")
                    .dorsal(7)
                    .equipo(psg)
                    .build();

            Jugador pauLopez = Jugador.builder()
                    .nombre("Pau López")
                    .fechaNacimiento(LocalDate.of(1994, 12, 13))
                    .posicion("Portero")
                    .dorsal(16)
                    .equipo(marseille)
                    .build();

            Jugador aubameyang = Jugador.builder()
                    .nombre("Pierre-Emerick Aubameyang")
                    .fechaNacimiento(LocalDate.of(1989, 6, 18))
                    .posicion("Delantero")
                    .dorsal(10)
                    .equipo(marseille)
                    .build();

            Jugador lopes = Jugador.builder()
                    .nombre("Anthony Lopes")
                    .fechaNacimiento(LocalDate.of(1990, 10, 1))
                    .posicion("Portero")
                    .dorsal(1)
                    .equipo(lyon)
                    .build();

            Jugador lacazette = Jugador.builder()
                    .nombre("Alexandre Lacazette")
                    .fechaNacimiento(LocalDate.of(1991, 5, 28))
                    .posicion("Delantero")
                    .dorsal(10)
                    .equipo(lyon)
                    .build();

            Jugador kohn = Jugador.builder()
                    .nombre("Philipp Köhn")
                    .fechaNacimiento(LocalDate.of(1998, 4, 2))
                    .posicion("Portero")
                    .dorsal(16)
                    .equipo(monaco)
                    .build();

            Jugador benYedder = Jugador.builder()
                    .nombre("Wissam Ben Yedder")
                    .fechaNacimiento(LocalDate.of(1990, 8, 12))
                    .posicion("Delantero")
                    .dorsal(10)
                    .equipo(monaco)
                    .build();

            jugadorRepository.saveAll(List.of(
                    donnarumma, mbappe,
                    pauLopez, aubameyang,
                    lopes, lacazette,
                    kohn, benYedder
            ));



            /*
             ******************************************************************************************
             * ****************************************************************************************
             * ****************************************************************************************
             * ****************************************************************************************
             */




            // ======================
            // TORNEOS NACIONALES
            // ======================
            Torneo liga = Torneo.builder()
                    .nombre("LaLiga")
                    .temporada("2024/2025")
                    .equipos(List.of(barcelona, madrid, atletico, sevilla))
                    .build();
            Torneo premier = Torneo.builder()
                    .nombre("Premier League")
                    .temporada("2024/2025")
                    .equipos(List.of(manCity, liverpool, arsenal))
                    .build();

            Torneo bundesliga = Torneo.builder()
                    .nombre("Bundesliga")
                    .temporada("2024/2025")
                    .equipos(List.of(bayern, dortmund, leipzig))
                    .build();

            Torneo serieA = Torneo.builder()
                    .nombre("Serie A")
                    .temporada("2024/2025")
                    .equipos(List.of(juventus, milan, inter, napoli))
                    .build();

            Torneo ligue1 = Torneo.builder()
                    .nombre("Ligue 1")
                    .temporada("2024/2025")
                    .equipos(List.of(psg, marseille, lyon, monaco))
                    .build();


            // ======================
            // TORNEOS INTERNACIONALES
            // ======================


            Torneo champions = Torneo.builder()
                    .nombre("Champions League")
                    .temporada("2024/2025")
                    .equipos(List.of(
                            barcelona,
                            madrid,
                            manCity,
                            arsenal,
                            liverpool,
                            bayern,
                            dortmund,
                            inter,
                            milan,
                            napoli,
                            psg,
                            monaco
                    ))
                    .build();
            torneoRepository.saveAll(List.of(liga,bundesliga,premier,serieA,ligue1, champions));


            /*
             ******************************************************************************************
             * ****************************************************************************************
             * ****************************************************************************************
             * ****************************************************************************************
             */



            // ======================
            // PARTIDOS
            // ======================
            Partido clasico = Partido.builder()
                    .fecha(LocalDate.of(2025, 3, 10))
                    .golesLocal(2)
                    .golesVisitante(1)
                    .equipoLocal(barcelona)
                    .equipoVisitante(madrid)
                    .torneo(liga)
                    .build();

            Partido derbi = Partido.builder()
                    .fecha(LocalDate.of(2025, 3, 12))
                    .golesLocal(1)
                    .golesVisitante(0)
                    .equipoLocal(atletico)
                    .equipoVisitante(madrid)
                    .torneo(liga)
                    .build();

            Partido sevillaVsBarca = Partido.builder()
                    .fecha(LocalDate.of(2025, 4, 5))
                    .golesLocal(0)
                    .golesVisitante(3)
                    .equipoLocal(sevilla)
                    .equipoVisitante(barcelona)
                    .torneo(liga)
                    .build();

            Partido champions1 = Partido.builder()
                    .fecha(LocalDate.of(2025, 5, 1))
                    .golesLocal(1)
                    .golesVisitante(1)
                    .equipoLocal(barcelona)
                    .equipoVisitante(atletico)
                    .torneo(champions)
                    .build();

            Partido champions2 = Partido.builder()
                    .fecha(LocalDate.of(2025, 5, 10))
                    .golesLocal(0)
                    .golesVisitante(2)
                    .equipoLocal(madrid)
                    .equipoVisitante(atletico)
                    .torneo(champions)
                    .build();

            partidoRepository.saveAll(List.of(clasico, derbi, sevillaVsBarca, champions1, champions2));

            Partido barcaCity = Partido.builder()
                    .fecha(LocalDate.of(2025, 5, 1))
                    .golesLocal(1)
                    .golesVisitante(1)
                    .equipoLocal(barcelona)
                    .equipoVisitante(manCity)
                    .torneo(champions)
                    .build();

            Partido madridLiverpool = Partido.builder()
                    .fecha(LocalDate.of(2025, 5, 10))
                    .golesLocal(0)
                    .golesVisitante(2)
                    .equipoLocal(madrid)
                    .equipoVisitante(liverpool)
                    .torneo(champions)
                    .build();

            partidoRepository.saveAll(List.of(barcaCity, madridLiverpool));

            Partido bayernBarca = Partido.builder()
                    .fecha(LocalDate.of(2025, 5, 15))
                    .golesLocal(3)
                    .golesVisitante(1)
                    .equipoLocal(bayern)
                    .equipoVisitante(barcelona)
                    .torneo(champions)
                    .build();

            Partido dortmundMadrid = Partido.builder()
                    .fecha(LocalDate.of(2025, 5, 20))
                    .golesLocal(2)
                    .golesVisitante(2)
                    .equipoLocal(dortmund)
                    .equipoVisitante(madrid)
                    .torneo(champions)
                    .build();

            partidoRepository.saveAll(List.of(bayernBarca, dortmundMadrid));

            Partido juveMilan = Partido.builder()
                    .fecha(LocalDate.of(2025, 3, 15))
                    .golesLocal(1)
                    .golesVisitante(1)
                    .equipoLocal(juventus)
                    .equipoVisitante(milan)
                    .torneo(serieA)
                    .build();

            Partido interNapoli = Partido.builder()
                    .fecha(LocalDate.of(2025, 3, 20))
                    .golesLocal(2)
                    .golesVisitante(0)
                    .equipoLocal(inter)
                    .equipoVisitante(napoli)
                    .torneo(serieA)
                    .build();

            partidoRepository.saveAll(List.of(juveMilan, interNapoli));

            Partido psgMarseille = Partido.builder()
                    .fecha(LocalDate.of(2025, 4, 2))
                    .golesLocal(3)
                    .golesVisitante(1)
                    .equipoLocal(psg)
                    .equipoVisitante(marseille)
                    .torneo(ligue1)
                    .build();

            Partido lyonMonaco = Partido.builder()
                    .fecha(LocalDate.of(2025, 4, 8))
                    .golesLocal(2)
                    .golesVisitante(2)
                    .equipoLocal(lyon)
                    .equipoVisitante(monaco)
                    .torneo(ligue1)
                    .build();

            partidoRepository.saveAll(List.of(psgMarseille, lyonMonaco));




        };
    }
}
