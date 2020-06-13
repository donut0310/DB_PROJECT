public class createTableQuery {
    static String tableName[] = {"Companies","CampingCars","Customers","ServiceCenters",
					"CarRentInfo","CarCheckList","RepairInfo"};
    static String create[] = {
        "CREATE TABLE `madang`.`Companies` ("
        +"`id` INT NOT NULL AUTO_INCREMENT,"
        +"`name` VARCHAR(45) NULL,"
        +"`address` VARCHAR(45) NULL,"
        +"`phone` VARCHAR(45) NULL,"
        +"`keeperName` VARCHAR(45) NULL,"
        +"`keeperEmail` VARCHAR(45) NULL,"
        +"PRIMARY KEY (`id`))"
        +"ENGINE = InnoDB;",

        "CREATE TABLE `madang`.`CampingCars` ("
        +"`id` INT NOT NULL AUTO_INCREMENT,"
        +"`name` VARCHAR(45) NULL,"
        +"`carNumber` VARCHAR(45) NULL,"
        +"`passengers` INT NULL,"
        +"`madeCp` VARCHAR(45) NULL,"
        +"`madeYear` INT NULL,"
        +"`totalMileage` INT NULL,"
        +"`rentCost` INT NULL,"
        +"`rentCpID` INT NULL,"
        +"`carRegisterDate` VARCHAR(45) NULL,"
        +"PRIMARY KEY (`id`),"
        +"INDEX `rentCpID_idx` (`rentCpID` ASC) VISIBLE,"
        +"CONSTRAINT `rentCpID`"
        +"FOREIGN KEY (`rentCpID`) "
        +"REFERENCES `madang`.`Companies` (`id`) "
        +"ON DELETE NO ACTION "
        +"ON UPDATE NO ACTION)"
        +"ENGINE = InnoDB;",

        "CREATE TABLE `madang`.`ServiceCenters` ("
        +"`id` INT NOT NULL AUTO_INCREMENT,"
        +"`name` VARCHAR(45) NULL,"
        +"`address` VARCHAR(45) NULL,"
        +"`phone` VARCHAR(45) NULL,"
        +"`keeperName` VARCHAR(45) NULL,"
        +"`keeperEmail` VARCHAR(45) NULL,"
        +"PRIMARY KEY (`id`))"
        +"ENGINE = InnoDB;",

        "CREATE TABLE `madang`.`Customers` ("
        +"`licenseNum` VARCHAR(45) NOT NULL,"
        +"`name` VARCHAR(45) NULL,"
        +"`address` VARCHAR(45) NULL,"
        +"`phone` VARCHAR(45) NULL,"
        +"`email` VARCHAR(45) NULL,"
        +"PRIMARY KEY (`licenseNum`))"
        +"ENGINE = InnoDB;"
    };
    
    static String insertSql[] ={ 
        "INSERT INTO Companies (name,address,phone,keeperName,keeperEmail) VALUES ('company1','서울시 강남구 청담동','02-3949-8778', '김민준', 'AB2@naver.com' ),"
        + "('company2','서울시 강남구 개포동','02-7869-9573','박서준', 'zplzp@naver.com'),"
        + "('company3','강원도 춘천시 후평동', '02-4893-9909','이예준', 'p_sd@naver.com'),"
        + "('company4','제주특별자치도 서귀포시 서귀동','02-9712-6774','김도윤', '29381s@gmail.com'),"
        + "('company5','강원도 춘천시 후평동','02-6276-6205','김시유', 'adoz11@naver.com'),"
        + "('company6','서울시 강남구 청담동','02-3199-1477','하주원', 'a0a0@gmail.com'),"
        + "('company7','서울시 강남구 개포동','02-3116-9461','서지호', 'sQ2@naver.com'),"
        + "('company8','경기도 부천시 중동','02-3548-6601','양준우', 'ap2cP@hanmail.net'),"
        + "('company9','경기도 부천시 중동','02-1563-3068','이시후', 'sodn2@aiai.com'),"
        + "('company10','서울시 강남구 청담동','02-3261-8636','김서연', 'asdo@nabe.com'),"
        + "('company11','제주특별자치도 서귀포시 서귀동','02-6230-7534','서지우', 'ap1p@gogo.com'),"
        + "('company12','서울시 강남구 청담동','02-3663-6387','하민서', 'NoNo@pop.com'),"
        + "('company13','제주특별자치도 서귀포시 서귀동','02-4412-6936','설하윤', 'gkdbs2@naver.com'),"
        + "('company14','서울시 마포구 망원동','02-9321-8785','이지민', '328_WK@naver.com'),"
        + "('company15','강원도 춘천시 후평동','02-8896-6280','김현우', 'gusdn@naver.com')",

        "INSERT INTO CampingCars (name,carNumber,passengers,madeCp,madeYear,totalMileage,rentCost,rentCpID, carRegisterDate) VALUES ('car1','37가5286',4,'BMW',1990, 1213, 50000,1,'2019-12-03'),"
        + "('car2', '74하5233',3,'KIA',2010,300, 30000,2,'2019-10-21'),"
        + "('car3', '71허9700',5,'KIA',2019,3040, 50000,3,'2019-04-21'),"
        + "('car4', '67가1793',6,'현대',2019,100, 50000,4,'2019-05-23'),"
        + "('car5', '56나1422',4,'현대',2014,10, 55000,5,'2019-01-30'),"
        + "('car6', '60마5646',4,'짱짱카',2020,15, 100000,6,'2019-01-05'),"
        + "('car7', '51다2063',8,'짱짱카',2019,50, 130000,7,'2019-12-02'),"
        + "('car8', '26라2213',3,'후진카', 1960,50030, 15000,8,'2019-11-11'),"
        + "('car9', '23가7414',4,'짱짱카', 2015,300, 100000,9,'2019-09-23'),"
        + "('car10', '99가5177',4,'현대', 2018, 1005, 50000,10,'2019-11-28'),"
        + "('car11', '81하9017',6,'KIA', 2016,1030, 50000,4,'2019-04-02'),"
        + "('car12', '98루9189',6,'BMW', 2019,5000, 56000,11,'2019-05-03'),"
        + "('car13', '10라5773',8,'짱짱카', 2020, 103, 200000,12,'2019-02-04'),"
        + "('car14', '89리3386',10,'후진카', 1959, 12030, 18000,13,'2019-04-09'),"
        + "('car15', '82깡1236',20,'쌩쌩카', 2020, 23, 380000, 14,'2019-11-02'),"
        + "('car16', '61뽕2285',6,'짱짱카', 2019, 1042, 230000,15, '2020-01-03')",

        "INSERT INTO Customers (licenseNum, name, address, phone, email) VALUES ('18-94-270368-11','김지환', '서울시 양천구 목동', '010-5675-4929', 'jasd@naver.com'), "
        + "('10-41-146773-34','김민정', '서울시 강서구 내발산동', '010-1047-8047', 'as2032@naver.com'),"
        + "('95-00-216977-24','김동화', '서울시 종로구 효제동', '010-2892-7434', 'bobo3@hanmail.net'), "
        + "('34-78-335899-62','김조한', '경기도 용인시 기흥구 신갈동', '010-3343-6158', 'vovo_1@naver.com'), "
        + "('74-64-670521-45','임채정', '강원도 춘천시 후평동', '010-1223-4206', 'asdko@gmail.com'),"
        + "('62-81-882719-61','허준', '경기도 부천시 중동', '010-9416-4112', 'xzxz1@naver.com'),"
        + "('14-91-399539-42','이세라', '서울시 마포구 망원동', '010-2193-3390', '3239_ab@gmail.com'),"
        + "('58-27-643663-91','남동일', '대구광역시 수성구 대흥동', '010-4943-2363', 'donut11@naver.com'), "
        + "('66-04-490211-53','김민관', '인천광역시 남동구 간석동','010-5008-8441', 'wlstjd@gmail.com'), "
        + "('12-92-438294-21','고한서', '충청남도 천안시 서북구 두정동', '010-3392-6287', 'jinbo20@naver.com'),"
        + "('17-16-271828-95','이수민', '전라북도 전주시 완산구 중앙동', '010-6122-1804', 'Posx_2@gmail.com'),"
        + "('64-47-472667-71','김영률', '부산광역시 강서구 명지동', '010-1285-3958', 'ASSSA@naver.com'),"
        + "('35-38-286929-12','김진성', '제주특별자치도 서귀포시 서귀동', '010-8327-1455', '20_jnb@sejo.com'),"
        + "('86-67-929990-35','김성수', '대전광역시 대덕구 법동', '010-3548-9691', '202001@naver.com'),"
        + "('53-22-433396-73','송호철', '서울시 영등포구 문래동', '010-4159-6397', 'alvokS@nave.com')",

        "INSERT INTO ServiceCenters (name,address,phone,keeperName,keeperEmail) VALUES ('center1','서울시 강남구 청담동', '02-3718-5667','정비볌1', 'wjas1@naver.com'), "
        + "('center2','서울시 강남구 개포동','02-3460-4765','정비볌2', 'ba2@naver.com'),"
        + "('center3','제주특별자치도 서귀포시 서귀동','02-2676-5009','정비볌3', '23asd3@naver.com'),"
        + "('center4','제주특별자치도 서귀포시 서귀동','02-7135-3998','정비볌4', 'lqud4@naver.com'),"
        + "('center5','서울시 강남구 개포동','02-8966-8840','정비볌5', '_A_ud5@naver.com'),"
        + "('center6','서울시 강남구 청담동','02-5333-2947','정비볌6', 'bsud6@naver.com'),"
        + "('center7','강원도 춘천시 후평동','02-6439-4631','정비볌7', 'asd27@naver.com'),"
        + "('center8','경기도 부천시 중동','02-1958-1803','정비볌8', 'daud8@naver.com'),"
        + "('center9','강원도 춘천시 후평동','02-3713-1322','정비볌9', 'DDU9@naver.com'),"
        + "('center10','서울시 강남구 청담동','02-3255-7748','정비볌10', 'JPa2s10@naver.com'),"
        + "('center11','제주특별자치도 서귀포시 서귀동','02-5641-3260','정비볌11', 'vaCCd11@naver.com'),"
        + "('center12','서울시 마포구 망원동','02-4204-8025','정비볌12', 'CCA12@naver.com'),"
        + "('center13','강원도 춘천시 후평동','02-5225-2904','정비볌13', 'ww2a13@naver.com'),"
        + "('center14','서울시 마포구 망원동','02-9878-1705','정비볌14', 'wwawwa14@naver.com'),"
        + "('center15','강원도 춘천시 후평동','02-5143-6758','정비볌15', 'vzsAA15@naver.com'),"
        + "('center16','서울시 강남구 개포동','02-4068-9532','정비볌16', 'wad16@naver.com')"
    };
}