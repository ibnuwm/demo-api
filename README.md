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
