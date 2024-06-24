create database QuickMecEngineers;

use QuickMecEngineers;

create table customer(
                         customer_id varchar(6)primary key,
                         customer_name varchar(25),
                         address varchar(20),
                         contact varchar(10)
);

insert into customer values ("C001","Kasun","Galle",0759843615);
insert into customer values ("C002","Supun","Moratuwa",0756479958);

create table supplier(
                         supplier_id varchar(6)primary key ,
                         name varchar(25),
                         location varchar(20),
                         contact int(10)
);

insert into supplier values ("S001","Spare Enterprices","Colombo",0796654123);
insert into supplier values ("S002","Nishan Enterprices","Colombo",0796265123);

create table spare(
                      Spare_id varchar(6)primary key,
                      Name varchar(25),
                      count varchar(10),
                      price varchar(10),
                      supplier_id varchar(6),
                      foreign key(supplier_id) references supplier(supplier_id) on update cascade on delete cascade
);

insert into spare values ("SP001","Piston Rings",10,5000,"S001");
insert into spare values ("SP002","Main Bearing",9,5000,"S002");

create table defect(
                       defect_id varchar(6)primary key,
                       description varchar(100),
                       price varchar(10),
                       Spare_id varchar(6),
                       foreign key(Spare_id) references spare(Spare_id) on update cascade on delete cascade
);

insert into defect values ("D001","Removing & Refitting Cylinder Liner",10000,"SP001");
insert into defect values ("D002","Refilling Main Bearing Parent Bore",15000,"SP002");

create table employee(
                         Emp_id varchar(6)primary key,
                         Name varchar(25),
                         attendence varchar(5),
                         address varchar(20),
                         contact varchar(10),
                         salary varchar(10),
                         cost varchar(10)
);

insert into employee values ("E000","Sadakan","Yes","Panadura",0789542165,25000,2500);
insert into employee values ("E001","Kushan","No","Piliyandala",0789559465,26000,1500);
insert into employee values ("E002","Krishantha","Yes","Kalutara",0775984265,27000,3500);
insert into employee values ("E003","Supun","No","Galle",0759812345,28000,1000);
insert into employee values ("E004","Ajith","Yes","Panadura",0725982146,29000,1200);
insert into employee values ("E005","Vajira","No","Colombo",0716902789,30000,1400);
insert into employee values ("E006","Saman","Yes","Payagala",0760132879,31000,2300);
insert into employee values ("E007","Namal","Yes","Panadura",0759803647,32000,2500);
insert into employee values ("E008","Janaka","Yes","Panadura",0759503647,32000,2500);
insert into employee values ("E009","Bernard","Yes","Panadura",0759893647,32000,2500);
insert into employee values ("E010","Geethal","Yes","Panadura",0759803647,32000,2500);
insert into employee values ("E011","Dayarathna","Yes","Panadura",0779803647,32000,2500);
insert into employee values ("E012","Nuwan","Yes","Panadura",0719803647,32000,2500);

create table job(
                    job_No varchar(6)primary key,
                    Model varchar(25),
                    Date date,
                    customer_id varchar(6),
                    foreign key(customer_id) references customer(customer_id) on update cascade on delete cascade,
                    item_count varchar(10),
                    defect_id varchar(6),
                    foreign key(defect_id) references defect(defect_id) on update cascade on delete cascade,
                    defect_Name varchar(100),
                    Spare_id varchar(6),
                    foreign key(Spare_id) references spare(Spare_id) on update cascade on delete cascade,
                    Spare_Name varchar(30),
                    Spare_count varchar(10),
                    Emp_id varchar(10),
                    foreign key(Emp_id) references employee(Emp_id) on update cascade on delete cascade,
                    Emp_Name varchar(30)
);

create table item(
                     item_No varchar(6)primary key,
                     Name varchar(25),
                     Item_count varchar(10),
                     defect_id varchar(6),
                     foreign key(defect_id) references defect(defect_id) on update cascade on delete cascade
);

insert into item values ("I001","Block","10","D001");
insert into item values ("I002","Crank","15","D002");

create table payment(
                        payment_id  varchar(10)primary key,
                        job_No varchar(6),
                        foreign key(job_No) references job(job_No) on update cascade on delete cascade,
                        defect_total_cost varchar(15),
                        employee_total_cost varchar(15),
                        spare_total_cost varchar(15),
                        total_cost varchar(20)
);

create table job_details(
                            item_No varchar(6),
                            foreign key(item_No)references item(item_No)on update cascade on delete cascade,
                            Model varchar(25),
                            job_No varchar(6),
                            foreign key(job_No)references job(job_No) on update cascade on delete cascade
);

create table machine(
                        machine_id varchar(6)primary key ,
                        date date,
                        Emp_id varchar(6),
                        foreign key(Emp_id)references employee(Emp_id)on update cascade on delete cascade
);

insert into machine values ("M001","2015/05/25","E001");
insert into machine values ("M002","2014/07/15","E002");

create table spare_supplier_detail(
                                      Spare_id varchar(6),
                                      foreign key(Spare_id)references spare(Spare_id)on update cascade on delete cascade,
                                      supplier_id varchar(6),
                                      foreign key(supplier_id)references supplier(supplier_id)on update cascade on delete cascade
);

create table credential(
                           username varchar(25),
                           password varchar(10)

);

insert into credential values ('nimsi', '123');