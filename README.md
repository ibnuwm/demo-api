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
