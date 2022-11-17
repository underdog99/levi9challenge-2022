# levi9challenge-2022, ucesnik: Mladen Simeonovic


Build (Apache NetBeans IDE 12):

1. Preuzeti .ZIP datoteku sa GitHub-a
2. Otvoriti Apache NetBeans IDE 12, zatim u gornjem menu, ici na File->Import Project->From ZIP
3. Selektovati preuzeti .ZIP

  + Potrebno je unutar projekta: /src/main/resources/application.properties, promeniti "spring.jpa.hibernate.ddl-auto =  none" na "spring.jpa.hibernate.ddl-auto = create" kako bi Hibernate kreirao potrebnu rel. bazu podataka. Takodje promeniti "spring.datasource.uri", "spring.datasource.username", "spring.datasource.password" da odgovara Vasem okruzenju.
  
4. Projekat ce biti ucitan, potrebno je izvrsiti "Clean and Build"(desni klik na projekat)
5. Pokrenuti projekat (desni klik, Run)

6. Tomcat je startovan, adresa za prisup: localhost:8080

Apache Netbeans, takodje poseduje podrsku za GitHub integraciju, tako da je moguce i klonirati ovaj projekat u IDE-u i izvrsiti build.


///////////////////////////////////////////////////////////////////////////////

Tehnologije za razvoj:

- Spring Boot: Aplikacioni radni okvir za Javu
- Java 11: prog. jezik
- Maven: dependency management
- MySQL: baza podataka
- JPA: ORM
- Tomcat: aplikativni server/kont.
- NetBeans 12 IDE: razvojno okruzenje


