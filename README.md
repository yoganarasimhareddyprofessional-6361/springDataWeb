# 4.  Spring Data Web

## *Spring data JPA is basically used to create an application without any custom business logic, instead it takes the features of [Spring HATEOAS](https://spring.io/projects/spring-hateoas) and [Spring Data JPA](https://spring.io/projects/spring-data-jpa) and automatically combines them together.*

- It also supports RDBMS / NoSql as DB

- Step 1: Create a spring starter project >> add details of project >> JPA,MYSQL,WEB,LOMBOK,Rest Repositories >>finish
- Step2: Create Entity Classes : Student and Location

    ```java
    package com.yog.test.springdataweb.entity;
    
    import jakarta.persistence.Entity;
    import jakarta.persistence.GeneratedValue;
    import jakarta.persistence.GenerationType;
    import jakarta.persistence.Id;
    import lombok.AllArgsConstructor;
    import lombok.Data;
    import lombok.NoArgsConstructor;
    
    @Entity
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public class Location {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int locationId;
        private String name;
        private String region;
        private double lat;
        private double lon;
    }
    ```


```java
package com.yog.test.springdataweb.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String section;
    private int rollNo;

}
```

- Step 3
    - create Repository
        - NOTE : u can use `@RepositoryRestResource(collectionResourceRel = "student-api", path = "student-api")` to define the endpoint for this entity by defining it on repository.

        ```java
        package com.yog.test.springdataweb.repository;
        
        import com.yog.test.springdataweb.entity.Location;
        import org.springframework.data.jpa.repository.JpaRepository;
        
        public interface LocationRepository extends JpaRepository<Location,Integer> {
        }
        ```

        ```java
        package com.yog.test.springdataweb.repository;
        
        import com.yog.test.springdataweb.entity.Student;
        import org.springframework.data.jpa.repository.JpaRepository;
        import org.springframework.data.rest.core.annotation.RepositoryRestResource;
        
        import java.util.List;
        
        @RepositoryRestResource(collectionResourceRel = "student-api", path = "student-api")
        public interface StudentRepository extends JpaRepository<Student, Integer> {
        
            //JPA QUERY
            List<Student> findBySection(String section);
        }
        ```

- Step 4:
    - add application.prperties
    -

    ```java
    spring.application.name=springDataWeb
    # Server Config
    server.port=9995
    
    # Database Config
    spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
    spring.datasource.url=jdbc:mysql://localhost:3306/springRest
    spring.datasource.username=root
    spring.datasource.password=Meow@123456
    
    #DDL-auto
    spring.jpa.hibernate.ddl-auto=update
    # you can have create(creates schema and tables fr you),update (updates if any changes on schema or table configured),none
    
    # ORM Config --> which converts ur method into a Query based on ur DB Drivers.
    spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect
    
    # to see queries on console
    spring.jpa.show-sql=true
    #format sql
    spring.jpa.properties.hibernate.format_sql=true
    
    ```


- Step 5: Start server and open [https://localhost:9995](http://localhost:9995) to see all the posible end point url collections based on entity classes
- [http://localhost:9995](http://localhost:9995/)
-

```java
OUTPUT :

{
  "_links" : {
    "student-api" : {
      "href" : "http://localhost:9995/student-api{?page,size,sort*}",
      "templated" : true
    },
    "locations" : {
      "href" : "http://localhost:9995/locations{?page,size,sort*}",
      "templated" : true
    },
    "profile" : {
      "href" : "http://localhost:9995/profile"
    }
  }
}
```

- http://localhost:9995/student-api
  - 

    ```java
    {
        "_embedded": {
            "student-api": []
        },
        "_links": {
            "self": {
                "href": "http://localhost:9995/student-api?page=0&size=20"
            },
            "profile": {
                "href": "http://localhost:9995/profile/student-api"
            },
            "search": {
                "href": "http://localhost:9995/student-api/search"
            }
        },
        "page": {
            "size": 20,
            "totalElements": 0,
            "totalPages": 0,
            "number": 0
        }
    }
    ```

- U can have any kind of search Queries use any custom JPA Query etc.