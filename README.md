# demo-api

This is example starter springboot demo api project

Persiapan Tools dan Alat :

- IDE : Visual Studio Code
- Plugin : Java Extension Pack, Spring boot Extension Pack, Thunder Client

Step-step Update Project :

1. Add CRUD

- menambahkan dependency spring-boot-starter-web, spring-boot-devtools, spring-boot-starter-data-jpa, mysql-connector-java
- membuat folder controller, models(entities dan repos), services, helper
- konfigurasi mysql di application.properties
- Folder models -> entities => (Product)

* menambahkan field/variabel, constructor, setter getter, optional : toString
* Anotasi: @entity, @Table, @Id, @GeneratedValue, @Column
* [implement Seriazible]

- Folder controllers =>

* menambahkan field service, method/function create, findOne, findAll, update, removeOne
* Anotasi: @RestController, @RequestMapping, @Autowired, @PostMapping, @GetMapping, @PutMapping, @DeleteMapping, @RequestBody, @PathVariable

- Folder services =>

* menambahkan field repo, method/function save, findOne, findAll, removeOne, findByName
* Anotasi : @Service, @Transactional, @Autowired

- Folder repos =>

* menambahkan method/function findByNameContains, \*
* [extends CrudRepository]

2. Add Validation

- menambahkan dependency spring-boot-starter-validation
- menambahkan folder dto + class ResponseData
- Folder models -> entities =>

* Anotasi : @NotEmpty

- Folder controllers =>

* mengganti return data Product menjadi ResponseEntity<ResponseData<Product>>
* menambahkan validasi error pada method create dan update

- Folder dto =>

* menambahkan field status, messages, payload
* menambahakan setter getter

3. Relasi Entity JPA

- Menambahkan class models -> entities (Category dan Supplier)
- class Product :

* Menambahkan field Category dan Suppliers
* Relasi Product dan Category adalah Many Product to One Category
* Relasi Product dan Supplier adalah Many to Many, maksudnya : satu product bisa di supply beberapa suppliers dan satu supplier bisa men-supply beberapa product. Dikarenakan masing2 tidak bisa tabel tidak bisa many to many, maka ditambahkan tabel perantara (tbl_product_supplier) yang didefinisikan di @JoinTable
* Anotasi : @ManyToOne, @ManyToMany, @JoinTable, @JoinColumn

- class Category :

* menambahkan field dan setter getter
* Anotasi : @Entity, @Table, @Id, @GeneratedValue, @Column

- class Supplier :

* menambahkan field dan setter getter
* Anotasi : @Entity, @Table, @Id, @GeneratedValue, @Column, @ManyToMany

4. Implementasi Relasi Data JPA join table

- menambahkan dependency modelmapper
- penambahan Controller, Service, Repo, Dto untuk class Supplier dan Category
- Repo Product == Repo Supplier / Category
- DTO Supplier & Category :

* menambahkan field (tanpa id / entities tabel lain), setter getter, validasi
* Anotasi : @NotEmpty, @Email

- Service Product == Service Supplier / Category

* Product : menambahkan method / function addSupplier untuk menambahkan entities supplier ke product

- Controller :

* Product : menambahkan method / function addSupplier dengan Anotasi @PostMapping("/{id}")
* Category / Supplier : penambahan service, model mapper (untuk copy data dari DTO ke entities), parameter diganti dari entities ke DTO.

- Models -> Entities :

* Relasi Category - Product, hanya menambahkan field Category dengan anotasi @ManyToOne di class Product beserta setter getter.

* Relasi data Supplier - Product, bisa menggunakan 2 cara supaya tidak infinite loop data yaitu :

[menambahkan anotasi @JsonManagedReference di Product (Set<Supplier>) dan @JsonBackReference di Supplier (Set<Product>)], hanya saja data bisa ditampilkan lengkap dengan di Product dan data Supplier tidak ditampilkan.

[menambahkan anotasi @JsonidentityInfo di class masing2 dengan hanya menampilkan id di masing2 data supplier/product], cara ini lebih baik.

5. Custom Query Search JPA

- Menambahkan method / function findProductByNameLike, findProductByName, findProductByCategory, findProductBySupplier di Repo Product

* Anotasi : @Query, @PathParam

- Menambahkan method / function findByProductName, findByProductNameLike, findByProductCategory, findByProductSupplier di Service Product

* Anotasi : @Autowire untuk SupplierService untuk pencarian data Supplier findOne

- Menambahkan getProductByName, getProductByNameLike, getProductByCategory, getProductBySupplier di Controller Product

* Anotasi : @PostMapping, @GetMapping

- Menambahkan class Search Data di DTO

6. Derived Query Method Spring Data JPA

- source : https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#reference

- Menambahkan method / function findByEmail, findByNameContainsOrderByIdDesc, findByNameStartingWith, findByNameContainsOrEmailContains di Supplier Repo
- Menambahkan method / function findByEmail, findByNameContains, findByNameStartsWith, findByNameOrEmail di Supplier Service
- Menambahkan method / function findByEmail, findByNameContains, findByNameStartsWith, findByNameOrEmail di Supplier Controller

- Menambahkan field otherSearchKey di class SearchData DTO

7. Paging dan Sorting Spring Data JPA (PagingAndSortingRepository)

- menambahkan method / function findByNameContains dengan return data Page di Category Repo
- menambahkan method / function findByName, saveBatch di Category Service
- menambahkan method / function findByName (menambahkan parameter search data, size, page), createBatch (menyimpan data dalam jumlah yg banyak) di Category Controller

8. Swagger Integration for API Documentation

- menambahkan dependency springfox-boot-starter dan springfox-swagger-ui
- menambahkan file SwaggerConfig di folder swagger
- menambahkan method / function apiInfo dan api
- akses : localhost:8080/swagger-ui/

* Anotasi : @Configuration

9. Spring Security (Authentication)

- menambahkan dependency spring-boot-starter-security
- menambahkan AppUserController (Field : object mapper dan service, method : register)
- menambahkan AppUserService (Field : repo dan BCryptPasswordEncoder, method : loadUserByUsername, registerAppUser)
- menambahkan AppUserRepo (method : findByEmail)
- menambahkan AppUserRole dan AppUser (Field, Anotasi : @Enumerated -> AppUserRole, method : getAuthorities)
- menambahkan WebSecurityConfig (Field : service dan BCryptPasswordEncoder, method : configure dan daoAuthenticationProvider, Anotasi : @Configuration, @EnableWebSecurity, @Bean) di folder security
- menambahkan PasswordEncoder (Anotasi : @Configuration dan @Bean, method : bCryptPasswordEncoder) di folder utils
- menambahkan AppUserData

10. Auditing with Spring JPA

- menambahkan method / function auditorAware, anotasi @EnableJpaAuditing di DemoApiApplication
- menambahkan field id di CategoryData
- menambahkan class BaseEntity (method : createdBy, createdDate, updateBy, updateDate) (anotasi : @CreatedBy, @CreatedDate, @LastModifiedBy, @LastModifiedDate, @Temporal, @MappedSuperclass, @EntityListeners) di folder entities
- menambahkan extends BaseEntity di class Category
- menambahkan validasi method save di CategoryService
- menambahkan class AuditorAwareImpl implement AuditorAware (method getCurrentAuditor)

11. Upload File with Spring MVC

- menambahkan dependency spring-boot-starter-thymeleaf
- menambahkan file index.html dan status.html di folder resources/template
- menambahkan UploadController (field : UPLOADED_PATH, method : index, uploadFile, uploadStatus , Anotasi : @Controller, @GetMapping, @PostMapping)

12. Upload CSV Data ke MYSQL

- menambahkan dependency commons-csv
- menambahkan BookRepo extends jparepository
- menambahkan Book
- menambahkan BookController (method : findAll dan uploadFile)
- menambahkan BookService (method : findAll dan save)
- menambahkan CSVService (Field : Type, Method : hasCSVFormat, csvToBook)

13. Project Lombok

- install extension lombok
- menambahkan dependency lombok
- Anotasi : @Getter, @Setter, @NoArgsConstructor, @Builder, @Data
- Fungsinya : generate sendiri setter getter / constructor / dan method2 lain.
- contoh : class Category

14. Soft Deleted with Spring JPA

- menambahkan method / function create dan remove di BookService
- menambahakan method / function createOne dan removeOne di BookController
- menambahkan field : deleted, Anotasi : @SQLDelete, @Where di class Book

15. Soft Deleted 2 (show deleted data) with Spring JPA

- menambahkan field EntityManager, menambahkan session dan filter pada method findAllBook di BookService
- menambahkan parameter @Requestparam di method findAll pada BookController
- comment @Where dan menambahkan @FilterDef, @Filter di Book

16. Actuator : Monitoring Springboot Application

- source :
  https://github.com/codecentric/spring-boot-admin
  https://codecentric.github.io/spring-boot-admin/2.3.1/#getting-started
- menambahkan dependency spring-boot-starter-actuator dan spring-boot-admin-starter-client
- menambahkan class databaseService & otherService implement healthIndicator (method : health dan isDatabaseHealthGood/isOtherServiceeHealthGood)
- menambahkan admin url 8080 & mengganti port 9090, management, info
- menambahkan MyController dengan dua method
- untuk monitoring aplikasi yg berjalan juga bisa menggunakan command jconsole

17. Transaction Management

- Alur : Validasi norek1 => saldonya cukup => kurangi saldo norek1 => validasi norek2 => tambahkan saldo norek2
- menambahkan scope provided dependency lombok jika terjadi error
- menambahkan RekeningRepo (method / function : findByNorek)
- menambahkan class TransferRequest di folder DTO
- menambahkan Rekening
- menambahkan RekeningService (method / function : create, findAll, transfer)
- menambahkan RekeningController (method / function : create, findAll, transfer)
- Anotasi : @Transaction di class Service

18. Konfigurasi multi environmert

- menambahkan profiles di pom.xml dan set default active profile (dev)
- menambahkan properties dev dan prod
- mengganti dengan spring.profiles.active di application.properties
- setting port dan DB di dev dan prod
- run dev (masuk ke folder target): java -jar [nama_file_jar]
- run prod (masuk ke folder target): java -jar [nama_file_jar] -Pprod => prod disesuaikan dengan activatedProperties

19. Data Caching

ciri2 data caching : ada limitasi, tidak terlalu sering berubah, sering banyak diakses result sama

- menambahkan dependency spring-boot-starter-cache
- menambahkan HeavyService (Anotasi : @Cacheable (untuk method yg akan di cache))
- modifikasi MyController (method : getData, field : HeavyService)
- menambahkan anotasi @EnableCaching di DemoApiApplication

20. Rest Client with Rest Template

- source : https://jsonplaceholder.typicode.com/
  https://jsonplaceholder.typicode.com/users
- menambahkan RestAddress, RestUser, RestCompany di folder DTO
- menambahkan RestClientService (Field : RestTemplate, Method : getUserString, getUserObject, getAll, postUserObject)
- menambahkan RestClientController (Field : RestClientService, Method : findUserStringById, findUserObjectById, findAllUser, postUser)
- menambahkan method : restTemplate di DemoApiApplication

21. Database Encryption

- menambahkan UserDetailEncryptRepo
- menambahakan UserDetailEncryptController
- menambahkan StringAttributeConverter implements AttributeConverter untuk encrypt dan decrypt
- menambahkan UserDetailEncrypt (Anotasi : @Converter dan @ColumnTransform)
- terdapat 2 cara encrypt decrypt ke DB, yaitu pertama menggunakan Converter yang memanggil file SAC untuk proses tersebut (di handle oleh JPA) sehingga bisa digunakan oleh berbagai jenis DB, kedua menggunakan ColumnTransform yang memanfaatkan proses encrypt decrypt yg disediakan oleh DB sehingga hanya bisa digunakan oleh DB tertentu

22. Custom Springboot 1

- menambahkan LOGGER (logging.level di application.properties), @Value (mengambil data di application.properties) di MyController
- menambahkan validasi di RekeningController (@Valid) dan Rekening (@Size)
- menambahkan cara get/post data RestTemplate (exchange, httpheader, httpentity)
- mematikan (spring.main.banner-info)/mengganti fungsi banner (banner.txt)
